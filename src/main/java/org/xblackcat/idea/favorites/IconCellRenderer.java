package org.xblackcat.idea.favorites;

import com.intellij.ui.JBColor;

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
            setBackground(JBColor.RED);
        }
    }
}
