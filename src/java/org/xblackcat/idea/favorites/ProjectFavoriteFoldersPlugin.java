package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */

@State(
        name = "ProjectFavoriteFolders",
        storages = {
                @Storage(
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
        Utils.updateFavorites();
    }

    @Override
    public void projectOpened() {
        Utils.updateFavorites();
    }

    @Override
    public void projectClosed() {
        Utils.updateFavorites();
    }

    @Override
    public Project getProject() {
        return project;
    }
}
