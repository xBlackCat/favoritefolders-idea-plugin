package org.xblackcat.idea.favorites;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author xBlackCat
 */
public abstract class ACellRenderer extends DefaultTableCellRenderer {
    @Override
    protected void setValue(Object value) {
        FavoriteFolder ff = (FavoriteFolder) value;

        if (ff.isIconValid()) {
            setForeground(Color.black);
            setBackground(Color.white);
        } else {
            setForeground(Color.red);
            setBackground(Color.white);
        }

        setValue(ff);
    }

    protected abstract void setValue(FavoriteFolder ff);
}
