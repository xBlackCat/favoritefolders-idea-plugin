package org.xblackcat.idea.favorites;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author xBlackCat
 */
class IconCellRenderer extends DefaultTableCellRenderer {
    public IconCellRenderer() {
        super();

        setHorizontalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setText(null);
        setIcon((Icon) value);

        return this;
    }
}
