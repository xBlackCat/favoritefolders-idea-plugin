package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */
class ProjectFavoriteFoldersConfigPane extends AConfigPane {
    ProjectFavoriteFoldersConfigPane(Project project) {
        super(project);
    }

    @Override
    protected AFavoritesContainer getFavoritesContainer() {
        return ServiceManager.getService(project, ProjectFavoriteFoldersPlugin.class);
    }

    @NotNull
    @Override
    public String getId() {
        return "favoritefolders.project";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return FavoriteFoldersBundle.message("FavoriteFolder.Config.title.project");
    }
}
