package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */

@State(
        name = "ProjectFavoriteFolders",
        storages = {
                @Storage(
                        id = "default",
                        file = "$PROJECT_FILE$"
                ),
                @Storage(
                        id = "dir",
                        file = "$PROJECT_CONFIG_DIR$/projectfavoriteFolders.xml",
                        scheme = StorageScheme.DIRECTORY_BASED
                )
        }
)
public class ProjectFavoriteFoldersPlugin extends AFavoritesContainer implements ProjectComponent {
    private static final Logger LOG = Logger.getInstance("#org.xblackcat.idea.favorites.FavoriteFoldersPlugin");
    private final Project project;

    public ProjectFavoriteFoldersPlugin(Project project) {
        this.project = project;
    }

    @NotNull
    public String getComponentName() {
        return "ProjectFavoriteFoldersPlugin";
    }

    @Override
    protected AConfigPane getConfigPane() {
        return new ProjectFavoriteFoldersConfigPane(project);
    }

    @Override
    protected void updateFavorites(boolean firstRun) {
        Utils.reregisterFavorites();
    }

    @Override
    public void projectOpened() {
        Utils.reregisterFavorites();
    }

    @Override
    public void projectClosed() {
        Utils.reregisterFavorites();
    }

    @Override
    public Project getProject() {
        return project;
    }
}
