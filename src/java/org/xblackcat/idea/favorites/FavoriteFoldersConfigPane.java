package org.xblackcat.idea.favorites;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.ui.AddEditRemovePanel;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xBlackCat
 */

public class FavoriteFoldersConfigPane extends BaseConfigurable implements SearchableConfigurable {
    private FavoriteFoldersConfigForm configForm;

    @Nls
    @Override
    public String getDisplayName() {
        return "Favorite Folders";
    }

    @Override
    public Icon getIcon() {
        return null;
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public JComponent createComponent() {
        if (configForm == null) {
            FavoriteFoldersPlugin plugin = ServiceManager.getService(FavoriteFoldersPlugin.class);
            configForm = new FavoriteFoldersConfigForm(plugin);
        }

        return configForm;
    }

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

    @NotNull
    @Override
    public String getId() {
        return "favorites";
    }

    private class FavoriteFoldersConfigForm extends JPanel {
        private final List<FavoriteFolder> favoriteList = new ArrayList<FavoriteFolder>();
        private final FavoritesTableModel model = new FavoritesTableModel();
        private final FavoriteFoldersPlugin plugin;
        private final AddEditRemovePanel<FavoriteFolder> favoritesPanel;

        public FavoriteFoldersConfigForm(FavoriteFoldersPlugin plugin) {
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
                protected FavoriteFolder editItem(FavoriteFolder o) {
                    FavoriteFolder folder = selectFolder(o);
                    if (folder == null) {
                        return null;
                    }

                    setModified(true);
                    return folder;
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

        private FavoriteFolder selectFolder(FavoriteFolder folder) {
            FavoriteFolderChooser dialog = new FavoriteFolderChooser(folder);

            final DialogBuilder builder = new DialogBuilder(favoritesPanel);
            builder.setPreferedFocusComponent(dialog.getFileLine());
            builder.setCenterPanel(dialog.getMainPanel());
            builder.setTitle(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.title"));
            builder.showModal(true);
            builder.getDialogWrapper().setResizable(false);

            if (builder.getDialogWrapper().isOK()) {
                return dialog.getSelectedFolder();
            } else {
                return null;
            }
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
