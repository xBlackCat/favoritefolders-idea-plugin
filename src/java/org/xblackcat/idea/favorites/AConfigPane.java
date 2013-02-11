package org.xblackcat.idea.favorites;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.ui.AddEditRemovePanel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xBlackCat
 */
abstract class AConfigPane extends BaseConfigurable implements SearchableConfigurable {
    private FavoriteFoldersConfigForm configForm;

    @Nls
    @Override
    public String getDisplayName() {
        return "Favorite Folders";
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public JComponent createComponent() {
        if (configForm == null) {
            AFavoritesContainer plugin = getFavoritesContainer();
            configForm = new FavoriteFoldersConfigForm(plugin);
        }

        return configForm;
    }

    protected abstract AFavoritesContainer getFavoritesContainer();

    @Override
    public void apply() throws ConfigurationException {
        if (configForm != null) {
            configForm.apply();
        }
    }

    @Override
    public void reset() {
        if (configForm != null) {
            configForm.reset();
        }
    }

    @Override
    public void disposeUIResources() {
        configForm = null;
    }

    @Override
    public Runnable enableSearch(String option) {
        return null;
    }

    private class FavoriteFoldersConfigForm extends JPanel {
        private final List<FavoriteFolder> favoriteList = new ArrayList<FavoriteFolder>();
        private final FavoritesTableModel model = new FavoritesTableModel();
        private final AFavoritesContainer plugin;
        private final AddEditRemovePanel<FavoriteFolder> favoritesPanel;

        public FavoriteFoldersConfigForm(AFavoritesContainer plugin) {
            super(new BorderLayout());
            this.plugin = plugin;

            favoritesPanel = new AddEditRemovePanel<FavoriteFolder>(model, favoriteList, "Favorite folders list") {
                @Override
                protected FavoriteFolder addItem() {
                    return editItem(null);
                }

                @Override
                protected boolean removeItem(FavoriteFolder o) {
                    setModified(true);
                    return true;
                }

                @Override
                protected FavoriteFolder editItem(@Nullable FavoriteFolder o) {
                    FavoriteFolderChooser dialog = Utils.selectFolder(o, favoritesPanel, false);
                    if (dialog == null) {
                        return null;
                    }

                    setModified(true);
                    return dialog.getSelectedFolder();
                }
            };
            favoritesPanel.setRenderer(0, new IconCellRenderer());
            favoritesPanel.setRenderer(1, new NameCellRenderer());
            favoritesPanel.setRenderer(2, new FolderCellRenderer());

            TableColumn column = favoritesPanel.getTable().getColumnModel().getColumn(0);
            column.setWidth(40);
            column.setMaxWidth(40);
            column.setResizable(false);

            add(favoritesPanel, BorderLayout.CENTER);
        }

        void reset() {
            favoritesPanel.setData(plugin.getFavorites());
            setModified(false);
        }

        void apply() {
            plugin.setFavorites(favoritesPanel.getData());
            setModified(false);

            UISettings.getInstance().fireUISettingsChanged();
        }
    }
}
