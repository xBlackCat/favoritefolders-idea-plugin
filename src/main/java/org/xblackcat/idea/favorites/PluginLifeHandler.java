package org.xblackcat.idea.favorites;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.ide.ApplicationInitializedListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 *
 */
public class PluginLifeHandler implements AppLifecycleListener {
    @Override
    public void appFrameCreated(@NotNull List<String> commandLineArgs) {
        Application app = ApplicationManager.getApplication();
        final FavoriteFoldersPlugin plugin = app.getService(FavoriteFoldersPlugin.class);
        Utils.updateFavorites(plugin);

        app.getMessageBus().connect().subscribe(ProjectManager.TOPIC, new ProjectManagerListener() {
            @Override
            public void projectOpened(@NotNull Project project) {
                Utils.updateAllFavorites();
            }

            @Override
            public void projectClosed(@NotNull Project project) {
                Utils.updateAllFavorites();
            }
        });
    }
}
