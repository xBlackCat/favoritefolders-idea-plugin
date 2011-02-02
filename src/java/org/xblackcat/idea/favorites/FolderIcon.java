package org.xblackcat.idea.favorites;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author xBlackCat
 */

public enum FolderIcon {
    Default(IconLoader.getIcon("/nodes/folder.png")),
    DataSource(IconLoader.getIcon("/nodes/DataSource.png")),
    Jar(IconLoader.getIcon("/nodes/jarDirectory.png")),
    JavaDoc(IconLoader.getIcon("/nodes/javaDocFolder.png")),
    Source(IconLoader.getIcon("/nodes/sourceFolder.png")),
    Home(IconLoader.getIcon("/nodes/homeFolder.png")),
    Test(IconLoader.getIcon("/nodes/testSourceFolder.png")),
    Up(IconLoader.getIcon("/nodes/upFolder.png"));

    private final Icon icon;

    FolderIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public static FolderIcon getIcon(String iconName) {
        try {
            return FolderIcon.valueOf(iconName);
        } catch (IllegalArgumentException e) {
            return Default;
        }
    }
}
