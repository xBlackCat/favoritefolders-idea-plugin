package org.xblackcat.idea.favorites;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.PropertyKey;

class FavoriteFoldersBundle extends DynamicBundle {
    @NonNls
    protected static final String PATH_TO_BUNDLE = "messages.FavoriteFoldersBundle";
    private static final FavoriteFoldersBundle INSTANCE = new FavoriteFoldersBundle();

    private FavoriteFoldersBundle() {
        super(PATH_TO_BUNDLE);
    }

    public static String message(@PropertyKey(resourceBundle = PATH_TO_BUNDLE) String key, Object... params) {
        return INSTANCE.getMessage(key, params);
    }
}
