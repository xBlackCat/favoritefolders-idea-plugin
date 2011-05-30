package org.xblackcat.idea.favorites;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @author xBlackCat
 */
class FolderCellRenderer extends ACellRenderer {
    @Override
    protected void setValue(FavoriteFolder ff) {
        if (ff.isFileValid()) {
            setText(ff.getFile().getPath());
        } else {
            setText(ff.getUrl());
            setForeground(Color.white);
            setBackground(Color.red);
        }

        setToolTipText(getText());
    }
}
