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
        return FavoriteFolder.class;
    }

    @Override
    public Object getField(FavoriteFolder o, int columnIndex) {
        return o;
    }
}
