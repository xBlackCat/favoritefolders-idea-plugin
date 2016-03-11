package org.xblackcat.idea.favorites;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogBuilder;
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
    private static final PluginId PLUGIN_ID = PluginManager.getPluginByClassName(FavoriteFoldersPlugin.class.getName());
    private static final PluginId PROJECT_PLUGIN_ID = PluginManager.getPluginByClassName(ProjectFavoriteFoldersPlugin.class.getName());

    private Utils() {
    }

    static void updateFavorites() {
        Application app = ApplicationManager.getApplication();
        updateFavorites(app.getComponent(FavoriteFoldersPlugin.class), false);
    }

    static void updateFavorites(AFavoritesContainer component, boolean skipProjectFavorites) {
        removeFavorites();

        int nextIndex = registerFavorites(component.getFavorites(), 1, PLUGIN_ID);

        if (skipProjectFavorites) {
            return;
        }

        ProjectManager projectManager = ProjectManager.getInstance();
        Project[] openProjects = projectManager.getOpenProjects();
        if (openProjects.length > 0) {
            for (Project p : openProjects) {
                List<FavoriteFolder> favorites = p.getComponent(ProjectFavoriteFoldersPlugin.class).getFavorites();
                nextIndex = registerFavorites(favorites, nextIndex, PROJECT_PLUGIN_ID);
            }
        }
    }

    private static void addShortCut(int id) {
        KeyStroke stroke = null;
        if (id < 7) {
            stroke = KeyStroke.getKeyStroke(id + KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK);
        } else if (id == 7) {
            stroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK);
        } else if (id < 17) {
            stroke = KeyStroke.getKeyStroke(id + KeyEvent.VK_0 - 7, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        } else if (id == 17) {
            stroke = KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
        }

        if (stroke != null) {
            KeymapManager km = KeymapManager.getInstance();
            Keymap keymap = km.getKeymap(KeymapManager.DEFAULT_IDEA_KEYMAP);

            if (keymap != null) {
                KeyboardShortcut shortcut = new KeyboardShortcut(stroke, null);
                keymap.addShortcut(ACTION_PREFIX + id, shortcut);
            }
        }
    }

    private static void removeFavorites() {
        ActionManager am = ActionManager.getInstance();
        KeymapManager km = KeymapManager.getInstance();
        Keymap keymap = km.getKeymap(KeymapManager.DEFAULT_IDEA_KEYMAP);

        DefaultActionGroup group = (DefaultActionGroup) am.getAction("FileChooserToolbar");

        String[] actionIds = am.getActionIds(ACTION_PREFIX);
        for (String actionId : actionIds) {
            group.remove(am.getAction(actionId));
            am.unregisterAction(actionId);
            if (keymap != null) {
                keymap.removeAllActionShortcuts(actionId);
            }
        }
    }

    private static int registerFavorites(List<FavoriteFolder> favorites, int startIndex, PluginId pluginId) {
        ActionManager am = ActionManager.getInstance();

        DefaultActionGroup group = (DefaultActionGroup) am.getAction("FileChooserToolbar");

        int id = startIndex;
        Constraints constraint = new Constraints(Anchor.BEFORE, "FileChooser.NewFile");
        for (FavoriteFolder fi : favorites) {
            if (!fi.isFileValid()) {
                continue;
            }

            GoToFavoriteFolder a = new GoToFavoriteFolder(fi);
            am.registerAction(ACTION_PREFIX + id, a, pluginId);
            addShortCut(id);

            group.add(a, constraint);

            ++id;
        }

        if (favorites.size() > 0) {
            group.add(Separator.getInstance(), constraint);
        }

        return id;
    }

    static FavoriteFolderChooser selectFolder(FavoriteFolder folder, Project project) {
        FavoriteFolderChooser dialog = new FavoriteFolderChooser(folder, project);

        final DialogBuilder builder = new DialogBuilder(project);
        builder.setPreferredFocusComponent(dialog.getFileLine());
        builder.setCenterPanel(dialog.getMainPanel());
        builder.setTitle(FavoriteFoldersBundle.message("FavoriteFolder.AddDialog.title"));
        builder.showModal(true);
        builder.getDialogWrapper().setResizable(false);

        if (builder.getDialogWrapper().isOK()) {
            return dialog;
        } else {
            return null;
        }
    }
}
