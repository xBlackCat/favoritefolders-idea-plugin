package org.xblackcat.idea.favorites;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationAction;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xBlackCat
 */
abstract class AFavoritesContainer implements PersistentStateComponent<Element> {
    protected List<FavoriteFolder> favorites = new ArrayList<>();

    List<FavoriteFolder> getFavorites() {
        return favorites;
    }

    void setFavorites(List<FavoriteFolder> favorites) {
        this.favorites = new ArrayList<>(favorites.size());

        this.favorites.addAll(favorites);

        Utils.updateAllFavorites();
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
    public void initializeComponent() {
        boolean showNoIcon = false;
        boolean showNoFolder = false;

        for (FavoriteFolder folder : favorites) {
            showNoIcon |= !folder.isIconValid();
            showNoFolder |= !folder.isFileValid();
        }

        if (showNoIcon || showNoFolder) {
            showNotify(showNoFolder);
        }
    }

    @Override
    public void loadState(Element foldersConfig) {
        favorites = new ArrayList<>();
        for (Element e : foldersConfig.getChildren("folder")) {
            String url = e.getAttributeValue("url");

            if (StringUtils.isBlank(url)) {
                continue;
            }

            String icon = e.getAttributeValue("icon");
            String name = e.getAttributeValue("name");
            FavoriteFolder folder = new FavoriteFolder(name, url, icon);
            favorites.add(folder);
        }
    }

    private void showNotify(boolean showNoFolder) {
        String titleKey = showNoFolder ? "FavoriteFolder.WarningTip.noFolder.title" : "FavoriteFolder.WarningTip.noIcon.title";
        String tipKey = showNoFolder ? "FavoriteFolder.WarningTip.noFolder" : "FavoriteFolder.WarningTip.noIcon";
        String actionKey = showNoFolder ? "FavoriteFolder.WarningTip.noFolder.action" : "FavoriteFolder.WarningTip.noIcon.action";

        // Icon wasn't found - notify user.
        final Notification notification = new Notification(
                "FavoriteFolders",
                FavoriteFoldersBundle.message(titleKey),
                FavoriteFoldersBundle.message(tipKey),
                NotificationType.WARNING
        );
        notification.addAction(new NotificationAction(FavoriteFoldersBundle.message(actionKey)) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e, @NotNull Notification notification) {
                notification.expire();

                AConfigPane configurable = getConfigPane();
                ShowSettingsUtil.getInstance().editConfigurable(getProject(), configurable);

                // Check again
                for (FavoriteFolder ff : favorites) {
                    if (!ff.isIconValid()) {
                        showNotify(false);
                        return;
                    } else if (!ff.isFileValid()) {
                        showNotify(true);
                        return;
                    }
                }

            }
        });

        Project targetProject = getProject() == null || getProject().isDefault() ? null : getProject();
        Notifications.Bus.notify(notification, targetProject);
    }


    protected abstract AConfigPane getConfigPane();

    public abstract Project getProject();
}
