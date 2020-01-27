package org.xblackcat.idea.favorites;

import com.intellij.ide.ApplicationInitializedListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class AppInitializerListener implements ApplicationInitializedListener {
    @Override
    public void componentsInitialized() {
        Application app = ApplicationManager.getApplication();
        final FavoriteFoldersPlugin plugin = app.getComponent(FavoriteFoldersPlugin.class);
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
