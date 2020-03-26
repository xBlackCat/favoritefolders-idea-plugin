package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;

/**
 * @author xBlackCat
 */

@State(
        name = "FavoriteFolders",
        storages = {
                @Storage("$APP_CONFIG$/favoriteFolders.xml")
        }
)
@Service
public class FavoriteFoldersPlugin extends AFavoritesContainer {
    public FavoriteFoldersPlugin() {
    }

    @Override
    protected AConfigPane getConfigPane() {
        return new FavoriteFoldersConfigPane();
    }

    @Override
    public Project getProject() {
        return null;
    }

}
