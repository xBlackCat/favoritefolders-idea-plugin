package org.xblackcat.idea.favorites;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author xBlackCat
 */
class FavoriteFolder {
    @NotNull
    private final String url;
    private final VirtualFile file;
    private final IIconGetter icon;
    private final String name;

    private FavoriteFolder(String name, VirtualFile file, @NotNull String url, IIconGetter icon) {
        this.name = name;
        this.file = file;
        this.icon = icon;
        this.url = url;
    }

    FavoriteFolder(String name, String url, String iconName) {
        this(name, VirtualFileManager.getInstance().findFileByUrl(url), url, FolderIcon.getIcon(iconName));
    }

    FavoriteFolder(String name, VirtualFile file, IIconGetter icon) {
        this(name, file, file.getUrl(), icon);
    }

    @Nullable
    public VirtualFile getFile() {
        return file;
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    public IIconGetter getIcon() {
        return icon;
    }

    public boolean isFileValid() {
        return file != null && file.isValid();
    }

    public String getName() {
        return name;
    }
}
