package org.xblackcat.idea.favorites;

import com.intellij.ui.AddEditRemovePanel;

/**
 * @author xBlackCat
 */

class FavoritesTableModel extends AddEditRemovePanel.TableModel<FavoriteFolder> {
    @Override
    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Icon";
            case 1 -> "Name";
            case 2 -> "Folder";
            default -> "";
        };

    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Class<FavoriteFolder> getColumnClass(int columnIndex) {
        return FavoriteFolder.class;
    }

    @Override
    public Object getField(FavoriteFolder o, int columnIndex) {
        return o;
    }
}
