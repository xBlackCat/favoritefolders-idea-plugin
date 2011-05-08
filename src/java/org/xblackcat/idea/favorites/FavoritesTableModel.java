package org.xblackcat.idea.favorites;

import com.intellij.ui.AddEditRemovePanel;

import javax.swing.*;

/**
 * @author xBlackCat
 */

class FavoritesTableModel extends AddEditRemovePanel.TableModel<FavoriteFolder> {
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Icon";
            case 1:
                return "Name";
            case 2:
                return "Folder";
        }

        return "";
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Icon.class;
            case 1:
                return String.class;
            case 2:
                return FavoriteFolder.class;
        }

        return null;
    }

    @Override
    public Object getField(FavoriteFolder o, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return o.getIcon().getIcon();
            case 1:
                return o.getName();
            case 2:
                return o;
        }

        return null;
    }
}
