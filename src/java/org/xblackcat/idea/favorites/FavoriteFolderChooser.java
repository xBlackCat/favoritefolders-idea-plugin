package org.xblackcat.idea.favorites;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.FixedSizeButton;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author xBlackCat
 */
class FavoriteFolderChooser {
    private boolean canceled = true;
    private VirtualFile selectedFolder = null;
    private FolderIcon icon = FolderIcon.Default;
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

        final JTextField filePathField = new JTextField(selectedFolder == null ? null : selectedFolder.getPresentableUrl(), 40);
        filePathField.setEditable(false);
        FixedSizeButton browseDirectoryButton = new FixedSizeButton(filePathField);
        TextFieldWithBrowseButton.MyDoClickAction.addTo(browseDirectoryButton, filePathField);
        browseDirectoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, true, false, true, false);
                VirtualFile[] files = FileChooser.chooseFiles(mainPanel, descriptor);
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

        final JComboBox iconSelector = new JComboBox(FolderIcon.values());
        iconSelector.setSelectedItem(icon);
        iconSelector.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                FolderIcon i = (FolderIcon) value;

                setIcon(i.getIcon());
                setText(i.name());

                return this;
            }
        });
        iconSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                icon = (FolderIcon) iconSelector.getSelectedItem();
            }
        });

        fieldsPane.add(iconSelector);
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
