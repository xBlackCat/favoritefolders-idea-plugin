package org.xblackcat.idea.favorites;

import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xBlackCat
 */

enum FolderIcon implements IIconGetter {
    Default(IconLoader.getIcon("/nodes/folder.png")),
    Custom(IconLoader.getIcon("/nodes/newFolder.png")),
    DataSource(IconLoader.getIcon("/nodes/DataSource.png")),
    Jar(IconLoader.getIcon("/nodes/jarDirectory.png")),
    JavaDoc(IconLoader.getIcon("/nodes/javaDocFolder.png")),
    Source(IconLoader.getIcon("/nodes/sourceFolder.png")),
    Home(IconLoader.getIcon("/nodes/homeFolder.png")),
    Test(IconLoader.getIcon("/nodes/testSourceFolder.png")),
    Up(IconLoader.getIcon("/nodes/upFolder.png")),
    Classes(IconLoader.getIcon("/nodes/class.png")),
    Folders(IconLoader.getIcon("/nodes/copyOfFolder.png")),
    Deploy(IconLoader.getIcon("/nodes/deploy.png")),
    Beans(IconLoader.getIcon("/nodes/ejb.png")),
    Extracted(IconLoader.getIcon("/nodes/extractedFolder.png")),
    Ant(IconLoader.getIcon("/nodes/keymapAnt.png")),
    FolderMap(IconLoader.getIcon("/nodes/keymapMainMenu.png")),
    Tools(IconLoader.getIcon("/nodes/keymapTools.png")),
    Misc(IconLoader.getIcon("/nodes/keymapOther.png")),
    Module(IconLoader.getIcon("/nodes/ModuleClosed.png")),
    Group(IconLoader.getIcon("/nodes/moduleGroupClosed.png")),
    ModuleJar(IconLoader.getIcon("/nodes/moduleJar.png")),
    Scroll(IconLoader.getIcon("/nodes/namedBunch.png")),
    JarContainer(IconLoader.getIcon("/nodes/ppJar.png")),
    JDK(IconLoader.getIcon("/nodes/ppJdkClosed.png")),
    Libraries(IconLoader.getIcon("/nodes/ppLibClosed.png")),
    Resources(IconLoader.getIcon("/nodes/resourceBundleClosed.png")),
    Unknown(IconLoader.getIcon("/nodes/unknownJdkClosed.png")),
    Web(IconLoader.getIcon("/nodes/webFolderClosed.png")),
    ;

    private final Icon icon;

    FolderIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public boolean isCustom() {
        return false;
    }

    public static IIconGetter loadIcon(String url) throws IOException {
        VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(url);
        if (file == null) {
            // File no longer exists - return common custom folder icon.
            return Custom;
        }

        ImageIcon icon = new ImageIcon(file.contentsToByteArray());

        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        if (height > 16 || width > 16) {
            BufferedImage im = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

            if (height == width) {
                im.getGraphics().drawImage(icon.getImage(), 0, 0, 16, 16, null);
            } else if (height > width) {
                int newHeight = (int) ((height * 16.) / width);
                im.getGraphics().drawImage(icon.getImage(), 0, 0, 16, newHeight, null);
            } else {
                int newWidth = (int) ((width * 16.) / height);
                im.getGraphics().drawImage(icon.getImage(), 0, 0, newWidth, 16, null);
            }


            icon = new ImageIcon(im);
        }

        return new CustomIcon(url, icon);
    }

    public static IIconGetter getIcon(String iconName) {
        try {
            return FolderIcon.valueOf(iconName);
        } catch (IllegalArgumentException e) {
            // Fall through - check external icon
        }

        // Check if the name is uri
        try {
            return loadIcon(iconName);
        } catch (IOException e) {
            // Fall through
        }

        return Default;
    }

    private static final class CustomIcon implements IIconGetter {
        private final String url;
        private final Icon icon;

        private CustomIcon(String url, Icon icon) {
            this.url = url;
            this.icon = icon;
        }

        @Override
        public Icon getIcon() {
            return icon;
        }

        @Override
        public String getName() {
            return url;
        }

        @Override
        public boolean isCustom() {
            return true;
        }
    }
}
