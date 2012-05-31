package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */

@State(
        name = "FavoriteFolders",
        storages = {
                @Storage(
                        id = "other",
                        file = "$APP_CONFIG$/favoriteFolders.xml"
                )
        }
)
public class FavoriteFoldersPlugin extends AFavoritesContainer implements ApplicationComponent {
    public FavoriteFoldersPlugin() {
    }

    @NotNull
    public String getComponentName() {
        return "FavoriteFoldersPlugin";
    }

    @Override
    protected AConfigPane getConfigPane() {
        return new FavoriteFoldersConfigPane();
    }

    @Override
    protected void updateFavorites(boolean firstRun) {
        Utils.updateFavorites(this, firstRun);
    }

    @Override
    public Project getProject() {
        return null;
    }

}
