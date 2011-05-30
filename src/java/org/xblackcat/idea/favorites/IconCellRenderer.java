package org.xblackcat.idea.favorites;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author xBlackCat
 */
class IconCellRenderer extends ACellRenderer {
    public IconCellRenderer() {
        super();

        setHorizontalAlignment(CENTER);
    }

    @Override
    protected void setValue(FavoriteFolder ff) {
        setText(null);
        IIconGetter icon = ff.getIcon();
        setIcon(icon.getIcon());
        setToolTipText(icon.getName());

        if (!ff.isIconValid()) {
            setBackground(Color.RED);
        }
    }
}
