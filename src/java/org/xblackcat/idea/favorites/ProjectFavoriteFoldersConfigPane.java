package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */
class ProjectFavoriteFoldersConfigPane extends AConfigPane {
    private final Project project;

    public ProjectFavoriteFoldersConfigPane(Project project) {
        this.project = project;
    }

    @Override
    protected AFavoritesContainer getFavoritesContainer() {
        return ServiceManager.getService(project, ProjectFavoriteFoldersPlugin.class);
    }

    @NotNull
    @Override
    public String getId() {
        return "projectfavoritefolders";
    }
}
