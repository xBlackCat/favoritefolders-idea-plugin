package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;

/**
 * @author xBlackCat
 */

@State(
        name = "ProjectFavoriteFolders",
        storages = {
                @Storage("$PROJECT_FILE$")
        }
)
@Service
public class ProjectFavoriteFoldersPlugin extends AFavoritesContainer {
    private final Project project;

    public ProjectFavoriteFoldersPlugin(Project project) {
        this.project = project;
    }

    @Override
    protected AConfigPane getConfigPane() {
        return new ProjectFavoriteFoldersConfigPane(project);
    }

    @Override
    public Project getProject() {
        return project;
    }
}
