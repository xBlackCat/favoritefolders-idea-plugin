package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
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

    public ProjectFavoriteFoldersPlugin() {
    }

    @NotNull
    public String getComponentName() {
        return "ProjectFavoriteFoldersPlugin";
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
}