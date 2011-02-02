package org.xblackcat.idea.favorites;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author xBlackCat
 */
class FolderCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        FavoriteFolder ff = (FavoriteFolder) value;

        if (ff.getFile() == null) {
            setForeground(Color.RED);
            setText(ff.getUrl());
        } else {
            setText(ff.getFile().getPath());
        }

        return this;
    }
}
