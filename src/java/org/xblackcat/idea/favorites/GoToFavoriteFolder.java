package org.xblackcat.idea.favorites;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileSystemTree;
import com.intellij.openapi.fileChooser.actions.FileChooserAction;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.lang.StringUtils;

/**
 * @author xBlackCat
 */

@SuppressWarnings({"ComponentNotRegistered"})
class GoToFavoriteFolder extends FileChooserAction {
    private static final Logger LOG = Logger.getInstance("#org.xblackcat.idea.favorites.GoToFavoriteFolder");

    private final FavoriteFolder favoriteFolder;

    @SuppressWarnings("ConstantConditions")
    GoToFavoriteFolder(FavoriteFolder folder) {
        super(
                StringUtils.isBlank(folder.getName()) ?
                        FavoriteFoldersBundle.message("action.FileChooser.FavoriteFolder.text", folder.getFile().getPath()) :
                        folder.getName(),
                FavoriteFoldersBundle.message("action.FileChooser.FavoriteFolder.description", folder.getFile().getPath()),
                folder.getIcon().getIcon()
        );
        this.favoriteFolder = folder;
    }

    protected void actionPerformed(final FileSystemTree fileSystemTree, AnActionEvent e) {
        final VirtualFile path = getFavoriteFolder();

        LOG.assertTrue(path != null);
        fileSystemTree.select(path, () -> fileSystemTree.expand(path, null));
    }

    protected void update(FileSystemTree fileSystemTree, AnActionEvent e) {
        final Presentation presentation = e.getPresentation();
        final VirtualFile path = getFavoriteFolder();
        presentation.setEnabled(path != null && fileSystemTree.isUnderRoots(path));
    }

    private VirtualFile getFavoriteFolder() {
        return validated(favoriteFolder.getFile());
    }

    private static VirtualFile validated(final VirtualFile file) {
        if (file == null || !file.isValid()) {
            return null;
        }
        return file;
    }

}
