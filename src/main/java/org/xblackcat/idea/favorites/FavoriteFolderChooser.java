package org.xblackcat.idea.favorites;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.FixedSizeButton;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.SimpleListCellRenderer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author xBlackCat
 */
class FavoriteFolderChooser {
    private static final FileChooserDescriptor FAVORITE_FOLDER_DESCRIPTOR = new FileChooserDescriptor(
            false,
            true,
            true,
            false,
            true,
            false
    );
    private static final FileChooserDescriptor FAVORITE_ICON_DESCRIPTOR = new FileChooserDescriptor(true, false, true, false, true, false);

    private VirtualFile selectedFolder = null;

    private IIconGetter icon = FolderIcon.Default;
    private final JComponent mainPanel;
    private final JTextField favoriteNameField;
    private final EnumComboBoxModel<FolderLevel> levelModel = new EnumComboBoxModel<>(FolderLevel.class);

    public FavoriteFolderChooser(FavoriteFolder initFolder, final Project project, boolean fromToolbar) {
        if (initFolder != null) {
            selectedFolder = initFolder.getFile();
            icon = initFolder.getIcon();
        }

        JPanel centerPane = new JPanel(new BorderLayout(5, 5));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(centerPane, BorderLayout.NORTH);

        final ComboBox<FolderIcon> iconSelector = new ComboBox<>(FolderIcon.values(), 18);

        final JTextField filePathField = new JTextField(selectedFolder == null ? null : selectedFolder.getPresentableUrl(), 40);
        filePathField.setEditable(false);
        FixedSizeButton browseDirectoryButton = new FixedSizeButton(iconSelector);
        TextFieldWithBrowseButton.MyDoClickAction.addTo(browseDirectoryButton, filePathField);
        browseDirectoryButton.addActionListener(e -> onDirectoryBrowseClick(project, filePathField));


        JPanel labelsPane = new JPanel(new GridLayout(0, 1, 5, 5));

        centerPane.add(labelsPane, BorderLayout.WEST);

        labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectFolderName.label")));
        labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectFolder.label")));
        labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectIcon.label")));

        if (fromToolbar && project != null) {
            labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectFolderLevel.label")));
        }

        JPanel fieldsPane = new JPanel(new GridLayout(0, 1, 5, 5));

        centerPane.add(fieldsPane, BorderLayout.CENTER);

        favoriteNameField = new JTextField();
        if (initFolder != null) {
            favoriteNameField.setText(initFolder.getName());
        }

        fieldsPane.add(favoriteNameField);

        JPanel selectFolderPane = new JPanel(new BorderLayout(5, 5));
        selectFolderPane.add(filePathField, BorderLayout.CENTER);
        selectFolderPane.add(browseDirectoryButton, BorderLayout.EAST);
        fieldsPane.add(selectFolderPane);

        iconSelector.getModel().setSelectedItem(icon);
        iconSelector.setRenderer(
                new SimpleListCellRenderer<IIconGetter>() {
                    @Override
                    public void customize(@NotNull JList jList, IIconGetter value, int i, boolean b, boolean b1) {
                        setIcon(value.getIcon());
                        setText(value.isCustom() ? "<Custom>" : value.getName());
                    }
                }
        );
        iconSelector.addActionListener(e -> onIconSelected(project, iconSelector));

        FixedSizeButton browseIconButton = new FixedSizeButton(iconSelector);
        browseIconButton.addActionListener(e -> iconSelector.setSelectedItem(FolderIcon.Custom));

        JPanel selectIconPane = new JPanel(new BorderLayout(5, 5));
        selectIconPane.add(iconSelector, BorderLayout.CENTER);
        selectIconPane.add(browseIconButton, BorderLayout.EAST);
        fieldsPane.add(selectIconPane);

        if (project != null) {
            if (fromToolbar) {
                JPanel cover = new JPanel(new BorderLayout());
                cover.add(new ComboBox<>(levelModel), BorderLayout.WEST);
                levelModel.setSelectedItem(FolderLevel.Global);
                fieldsPane.add(cover);
            } else {
                levelModel.setSelectedItem(FolderLevel.Project);
            }
        }
    }

    private void onDirectoryBrowseClick(Project project, JTextField filePathField) {
        VirtualFile[] files = FileChooser.chooseFiles(FAVORITE_FOLDER_DESCRIPTOR, mainPanel, project, selectedFolder);
        if (files.length != 0) {
            selectedFolder = files[0];
            filePathField.setText(selectedFolder.getPresentableUrl());
        }
    }

    private void onIconSelected(Project project, ComboBox<FolderIcon> iconSelector) {
        IIconGetter newIcon = (IIconGetter) iconSelector.getSelectedItem();

        if (newIcon == FolderIcon.Custom) {
            newIcon = null;

            VirtualFile s;
            if (icon.isCustom()) {
                s = VirtualFileManager.getInstance().findFileByUrl(icon.getName());
            } else {
                s = null;
            }

            iconSelector.hidePopup();
            VirtualFile[] files = FileChooser.chooseFiles(FAVORITE_ICON_DESCRIPTOR, mainPanel, project, s);
            if (files.length == 0) {
                iconSelector.getModel().setSelectedItem(icon);
                return;
            }

            try {
                newIcon = FolderIcon.loadIcon(files[0].getUrl());
            } catch (IOException e1) {
                // Fall through: if icon can not be loaded - show error lately
            }

            if (newIcon == null) {
                // Show error
                JOptionPane.showMessageDialog(
                        mainPanel,
                        FavoriteFoldersBundle.message("FavoriteFolder.ErrorDialog.invalidImage")
                );
                iconSelector.getModel().setSelectedItem(icon);
                return;
            }
        }

        icon = newIcon;
        iconSelector.getModel().setSelectedItem(icon);
    }

    public JComponent getMainPanel() {
        return mainPanel;
    }

    public FavoriteFolder getSelectedFolder() {
        return selectedFolder == null ? null : new FavoriteFolder(favoriteNameField.getText(), selectedFolder, icon);
    }

    public JComponent getFileLine() {
        return favoriteNameField;
    }

    public FolderLevel getTargetLevel() {
        return levelModel.getSelectedItem();
    }
}
