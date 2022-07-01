package org.xblackcat.idea.favorites;

import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

/**
 * @author xBlackCat
 */

class FavoriteFoldersConfigPane extends AConfigPane {
    FavoriteFoldersConfigPane() {
        super(null);
    }

    @Override
    protected AFavoritesContainer getFavoritesContainer() {
        return ApplicationManager.getApplication().getService(FavoriteFoldersPlugin.class);
    }

    @NotNull
    @Override
    public String getId() {
        return "favoritefolders.global";
    }

    @Nls
    @Override
    public String getDisplayName() {
        return FavoriteFoldersBundle.message("FavoriteFolder.Config.title.global");
    }
}
