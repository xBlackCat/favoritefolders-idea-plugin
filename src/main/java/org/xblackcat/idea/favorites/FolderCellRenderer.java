package org.xblackcat.idea.favorites;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;

/**
 * @author xBlackCat
 */
class FolderCellRenderer extends ACellRenderer {
    @Override
    protected void setValue(FavoriteFolder ff) {
        if (ff.isFileValid()) {
            final VirtualFile file = ff.getFile();
            if (file != null) {
                setText(file.getPath());
            } else {
                setText(null);
            }
        } else {
            setText(ff.getUrl());
            setForeground(JBColor.WHITE);
            setBackground(JBColor.RED);
        }

        setToolTipText(getText());
    }
}
