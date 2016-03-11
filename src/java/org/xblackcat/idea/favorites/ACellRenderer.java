package org.xblackcat.idea.favorites;

import com.intellij.ui.JBColor;

import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author xBlackCat
 */
abstract class ACellRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        FavoriteFolder ff = (FavoriteFolder) value;

        if (ff.isIconValid()) {
            setForeground(JBColor.BLACK);
            setBackground(JBColor.WHITE);
        } else {
            setForeground(JBColor.RED);
            setBackground(JBColor.WHITE);
        }

        setValue(ff);
    }

    protected abstract void setValue(FavoriteFolder ff);
}
