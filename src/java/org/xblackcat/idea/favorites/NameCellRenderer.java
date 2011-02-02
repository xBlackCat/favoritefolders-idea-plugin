package org.xblackcat.idea.favorites;

import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author xBlackCat
 */
class NameCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (StringUtils.isBlank((String) value)) {
            setText("<Default>");
        } else {
            setText((String) value);
        }
        setIcon(null);

        return this;
    }
}
