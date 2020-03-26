package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.ServiceManager;
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
        return ServiceManager.getService(FavoriteFoldersPlugin.class);
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
