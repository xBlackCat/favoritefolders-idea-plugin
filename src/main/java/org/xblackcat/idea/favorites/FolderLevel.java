package org.xblackcat.idea.favorites;

/**
 * 31.05.12 11:29
 *
 * @author xBlackCat
 */
enum FolderLevel {
    Global,
    Project;

    @Override
    public String toString() {
        return FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.Level." + name());
    }
}
