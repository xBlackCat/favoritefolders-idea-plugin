package org.xblackcat.idea.favorites;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileSystemTree;
import com.intellij.openapi.fileChooser.actions.FileChooserAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */

class AddFavoriteFolder extends FileChooserAction {
    private static final Logger LOG = Logger.getInstance("#org.xblackcat.idea.favorites.AddFavoriteFolder");

    protected void actionPerformed(final FileSystemTree fileSystemTree, AnActionEvent e) {
        final VirtualFile path = fileSystemTree.getSelectedFile();

        LOG.assertTrue(path != null);
        FavoriteFolder template = new FavoriteFolder(
                path.getName(),
                path,
                FolderIcon.Default
        );

        FavoriteFolderChooser chooser = Utils.selectFolder(template, e.getProject(), true);

        if (chooser == null) {
            return;
        }
        FavoriteFolder folder = chooser.getSelectedFolder();

        if (folder == null) {
            return;
        }

        final AFavoritesContainer plugin;
        switch (chooser.getTargetLevel()) {
            case Project -> {
                Project project = e.getProject();
                LOG.assertTrue(project != null);
                plugin = project.getService(ProjectFavoriteFoldersPlugin.class);
            }
            case Global -> plugin = ApplicationManager.getApplication().getService(FavoriteFoldersPlugin.class);
            default -> throw new RuntimeException("Unexpected");
        }

        plugin.getFavorites().add(folder);
        Utils.updateAllFavorites();

        UISettings.getInstance().fireUISettingsChanged();
    }

    protected void update(FileSystemTree fileSystemTree, AnActionEvent e) {
        final Presentation presentation = e.getPresentation();
        presentation.setEnabled(fileSystemTree.selectionExists());
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.EDT;
    }
}
