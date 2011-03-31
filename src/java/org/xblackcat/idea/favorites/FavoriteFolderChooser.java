package org.xblackcat.idea.favorites;

import com.intellij.ide.ui.ListCellRendererWrapper;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.FixedSizeButton;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author xBlackCat
 */
class FavoriteFolderChooser {
    private boolean canceled = true;
    private VirtualFile selectedFolder = null;
    private IIconGetter icon = FolderIcon.Default;
    private String name;

    private JComponent mainPanel;
    private final JTextField favoriteNameField;

    public FavoriteFolderChooser(FavoriteFolder initFolder) {
        if (initFolder != null) {
            selectedFolder = initFolder.getFile();
            icon = initFolder.getIcon();
            name = initFolder.getName();
        }

        JPanel centerPane = new JPanel(new BorderLayout(5, 5));

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(centerPane, BorderLayout.NORTH);

        final JComboBox iconSelector = new JComboBox(FolderIcon.values());

        final JTextField filePathField = new JTextField(selectedFolder == null ? null : selectedFolder.getPresentableUrl(), 40);
        filePathField.setEditable(false);
        FixedSizeButton browseDirectoryButton = new FixedSizeButton(iconSelector);
        TextFieldWithBrowseButton.MyDoClickAction.addTo(browseDirectoryButton, filePathField);
        browseDirectoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, true, false, true, false);
                VirtualFile[] files = FileChooser.chooseFiles(mainPanel, descriptor, selectedFolder);
                if (files.length != 0) {
                    selectedFolder = files[0];
                    filePathField.setText(selectedFolder.getPresentableUrl());
                }
            }
        });


        JPanel labelsPane = new JPanel(new GridLayout(0, 1));

        centerPane.add(labelsPane, BorderLayout.WEST);

        labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectFolderName.label")));
        labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectFolder.label")));
        labelsPane.add(new JLabel(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.SelectIcon.label")));

        JPanel fieldsPane = new JPanel(new GridLayout(0, 1));

        centerPane.add(fieldsPane, BorderLayout.CENTER);

        favoriteNameField = new JTextField(name);

        fieldsPane.add(favoriteNameField);

        JPanel selectFolderPane = new JPanel(new BorderLayout(5, 5));
        selectFolderPane.add(filePathField, BorderLayout.CENTER);
        selectFolderPane.add(browseDirectoryButton, BorderLayout.EAST);
        fieldsPane.add(selectFolderPane);

        iconSelector.getModel().setSelectedItem(icon);
        iconSelector.setRenderer(new ListCellRendererWrapper<IIconGetter>(iconSelector) {
            @Override
            public void customize(JList jList, IIconGetter value, int i, boolean b, boolean b1) {
                setIcon(value.getIcon());
                setText(value.isCustom() ? "<Custom>" : value.getName());
            }
        });
        iconSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IIconGetter newIcon = (IIconGetter) iconSelector.getSelectedItem();

                if (newIcon == FolderIcon.Custom) {
                    newIcon = null;

                    FileChooserDescriptor iconChooser = new FileChooserDescriptor(true, false, true, false, true, false);
                    VirtualFile s;
                    if (icon.isCustom()) {
                        s = VirtualFileManager.getInstance().findFileByUrl(icon.getName());
                    } else {
                        s = null;
                    }

                    iconSelector.hidePopup();
                    VirtualFile[] files = FileChooser.chooseFiles(mainPanel, iconChooser, s);
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
                        JOptionPane.showMessageDialog(mainPanel, FavoriteFoldersBundle.message("FavoriteFolder.ErrorDialog.invalidImage"));
                        iconSelector.getModel().setSelectedItem(icon);
                        return;
                    }
                }

                icon = newIcon;
                iconSelector.getModel().setSelectedItem(icon);
            }
        });

        FixedSizeButton browseIconButton = new FixedSizeButton(iconSelector);
        browseIconButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iconSelector.setSelectedItem(FolderIcon.Custom);
            }
        });

        JPanel selectIconPane = new JPanel(new BorderLayout(5, 5));
        selectIconPane.add(iconSelector, BorderLayout.CENTER);
        selectIconPane.add(browseIconButton, BorderLayout.EAST);
        fieldsPane.add(selectIconPane);
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
}
