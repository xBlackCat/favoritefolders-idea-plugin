package org.xblackcat.idea.favorites;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
public class FavoriteFoldersPlugin implements ApplicationComponent, PersistentStateComponent<Element> {
    private static final Logger LOG = Logger.getInstance("#org.xblackcat.idea.favorites.FavoriteFoldersPlugin");
    @NonNls
    public static final String ACTION_PREFIX = "FavoriteFolder.Favorite_";

    private List<FavoriteFolder> favorites = new ArrayList<FavoriteFolder>();

    public FavoriteFoldersPlugin() {
    }

    public void initComponent() {
    }

    public void disposeComponent() {
        unregisterFavorites();
    }

    private void registerFavorites() {
        ActionManager am = ActionManager.getInstance();

        DefaultActionGroup group = (DefaultActionGroup) am.getAction("FileChooserToolbar");

        int id = 1;
        Constraints constraint = new Constraints(Anchor.BEFORE, "FileChooser.NewFile");
        for (FavoriteFolder fi : favorites) {
            if (!fi.isFileValid()) {
                continue;
            }

            GotoFavoriteFolder a = new GotoFavoriteFolder(fi);
            am.registerAction(ACTION_PREFIX + id, a);
            addShortCut(id);

            group.add(a, constraint);

            ++id;
        }

        if (favorites.size() > 0) {
            group.add(Separator.getInstance(), constraint);
        }
    }

    private void unregisterFavorites() {
        ActionManager am = ActionManager.getInstance();
        KeymapManager km = KeymapManager.getInstance();
        Keymap keymap = km.getKeymap(KeymapManager.DEFAULT_IDEA_KEYMAP);

        DefaultActionGroup group = (DefaultActionGroup) am.getAction("FileChooserToolbar");

        String[] actionIds = am.getActionIds(ACTION_PREFIX);
        for (String actionId : actionIds) {
            group.remove(am.getAction(actionId));
            am.unregisterAction(actionId);
            keymap.removeAllActionShortcuts(actionId);
        }
    }

    private void addShortCut(int id) {
        KeyStroke stroke = null;
        if (id < 7) {
            stroke = KeyStroke.getKeyStroke(id + KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK);
        } else if (id == 7) {
            stroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
        } else if (id < 17) {
            stroke = KeyStroke.getKeyStroke(id + KeyEvent.VK_0 - 7, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        } else if (id == 17) {
            stroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK);
        }

        if (stroke != null) {
            KeymapManager km = KeymapManager.getInstance();
            Keymap keymap = km.getKeymap(KeymapManager.DEFAULT_IDEA_KEYMAP);

            KeyboardShortcut shortcut = new KeyboardShortcut(stroke, null);
            keymap.addShortcut(ACTION_PREFIX + id, shortcut);
        }
    }

    @NotNull
    public String getComponentName() {
        return "FavoriteFoldersPlugin";
    }

    @Override
    public Element getState() {
        // Make config
        Element root = new Element("favorites");

        for (FavoriteFolder ff : favorites) {
            Element e = new Element("folder");
            e.setAttribute("url", ff.getUrl());
            e.setAttribute("icon", ff.getIcon().getName());
            if (!StringUtils.isBlank(ff.getName())) {
                e.setAttribute("name", ff.getName());
            }

            root.addContent(e);
        }

        return root;
    }

    @Override
    public void loadState(Element foldersConfig) {
        favorites = new ArrayList<FavoriteFolder>();

        for (Element e : (List<Element>) foldersConfig.getChildren("folder")) {
            String url = e.getAttributeValue("url");

            if (StringUtils.isBlank(url)) {
                continue;
            }

            String icon = e.getAttributeValue("icon");
            String name = e.getAttributeValue("name");
            favorites.add(new FavoriteFolder(name, url, icon));
        }

        registerFavorites();
    }

    List<FavoriteFolder> getFavorites() {
        return favorites;
    }

    void setFavorites(List<FavoriteFolder> favorites) {
        this.favorites = new ArrayList<FavoriteFolder>(favorites.size());

        this.favorites.addAll(favorites);

        unregisterFavorites();
        registerFavorites();
    }
}
