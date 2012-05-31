package org.xblackcat.idea.favorites;

import org.apache.commons.lang.StringUtils;

/**
 * @author xBlackCat
 */
class NameCellRenderer extends ACellRenderer {
    @Override
    protected void setValue(FavoriteFolder value) {
        if (StringUtils.isBlank(value.getName())) {
            setText("<Default>");
            setToolTipText("");
        } else {
            setText(value.getName());
            setToolTipText(getText());
        }
        setIcon(null);
    }
}
