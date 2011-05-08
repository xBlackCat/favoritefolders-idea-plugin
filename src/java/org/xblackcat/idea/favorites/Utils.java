package org.xblackcat.idea.favorites;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author xBlackCat
 */
final class Utils {
    @NonNls
    private static final String ACTION_PREFIX = "FavoriteFolder.Favorite_";

    private Utils() {
    }

    static void reregisterFavorites() {
        reregisterFavorites(false);
    }

    static void reregisterFavorites(boolean skipProjectFavorites) {
        Application app = ApplicationManager.getApplication();
        reregisterFavorites(app.getComponent(FavoriteFoldersPlugin.class), skipProjectFavorites);
    }

    static void reregisterFavorites(AFavoritesContainer component, boolean skipProjectFavorites) {
        unregisterFavorites();

        int nextIndex = registerFavorites(component.getFavorites(), 1);

        if (skipProjectFavorites) {
            return;
        }

        ProjectManager projectManager = ProjectManager.getInstance();
        Project[] openProjects = projectManager.getOpenProjects();
        if (openProjects.length > 0) {
            for (Project p : openProjects) {
                List<FavoriteFolder> favorites = p.getComponent(ProjectFavoriteFoldersPlugin.class).getFavorites();
                nextIndex = registerFavorites(favorites, nextIndex);
            }
        } else {
            List<FavoriteFolder> favorites = projectManager
                    .getDefaultProject()
                    .getComponent(ProjectFavoriteFoldersPlugin.class)
                    .getFavorites();
            registerFavorites(favorites, nextIndex);
        }
    }

    private static void addShortCut(int id) {
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

    private static void unregisterFavorites() {
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

    private static int registerFavorites(List<FavoriteFolder> favorites, int startIndex) {
        ActionManager am = ActionManager.getInstance();

        DefaultActionGroup group = (DefaultActionGroup) am.getAction("FileChooserToolbar");

        int id = startIndex;
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

        return id;
    }
}
