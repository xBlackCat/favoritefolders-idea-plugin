package org.xblackcat.idea.favorites;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooserPanel;
import com.intellij.openapi.fileChooser.FileSystemTree;
import com.intellij.openapi.fileChooser.actions.FileChooserAction;
import com.intellij.openapi.vfs.VirtualFile;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */

class GoToFavoriteFolderAction extends FileChooserAction {
    private static final Logger LOG = Logger.getInstance("#org.xblackcat.idea.favorites.GoToFavoriteFolder");

    private final FavoriteFolder favoriteFolder;

    GoToFavoriteFolderAction(FavoriteFolder folder) {
        super(getText(folder), getDescription(folder), folder.getIcon().icon());
        this.favoriteFolder = folder;
    }

    @NotNull
    private static String getDescription(FavoriteFolder folder) {
        if (folder.getFile() != null) {
            return FavoriteFoldersBundle.message("action.FavoriteFolder.description", folder.getFile().getPath());
        } else {
            return "";
        }
    }

    private static String getText(FavoriteFolder folder) {
        if (!StringUtils.isBlank(folder.getName()) || folder.getFile() == null) {
            return folder.getName();
        } else {
            return FavoriteFoldersBundle.message("action.FavoriteFolder.text", folder.getFile().getPath());
        }
    }

    protected void actionPerformed(final FileSystemTree fileSystemTree, @NotNull AnActionEvent e) {
        final VirtualFile path = getFavoriteFolder();

        LOG.assertTrue(path != null);
        fileSystemTree.select(path, () -> fileSystemTree.expand(path, null));
    }

    @Override
    protected void update(@NotNull FileChooserPanel panel, @NotNull AnActionEvent e) {
        final Presentation presentation = e.getPresentation();
        final VirtualFile path = getFavoriteFolder();
        presentation.setEnabled(path != null);
        presentation.setVisible(true);
    }

    protected void update(@NotNull FileSystemTree fileSystemTree, AnActionEvent e) {
        final Presentation presentation = e.getPresentation();
        final VirtualFile path = getFavoriteFolder();
        presentation.setEnabled(path != null && fileSystemTree.isUnderRoots(path));
        presentation.setVisible(true);
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


    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}
