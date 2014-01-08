package org.xblackcat.idea.favorites;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.HyperlinkEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xBlackCat
 */
abstract class AFavoritesContainer implements BaseComponent, PersistentStateComponent<Element> {
    protected List<FavoriteFolder> favorites = new ArrayList<>();

    List<FavoriteFolder> getFavorites() {
        return favorites;
    }

    void setFavorites(List<FavoriteFolder> favorites) {
        this.favorites = new ArrayList<>(favorites.size());

        this.favorites.addAll(favorites);

        updateFavorites(false);
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

    @SuppressWarnings({"unchecked"})
    @Override
    public void loadState(Element foldersConfig) {
        favorites = new ArrayList<>();
        boolean showNoIcon = false;
        boolean showNoFolder = false;

        for (Element e : foldersConfig.getChildren("folder")) {
            String url = e.getAttributeValue("url");

            if (StringUtils.isBlank(url)) {
                continue;
            }

            String icon = e.getAttributeValue("icon");
            String name = e.getAttributeValue("name");
            FavoriteFolder folder = new FavoriteFolder(name, url, icon);
            favorites.add(folder);

            showNoIcon |= !folder.isIconValid();
            showNoFolder |= !folder.isFileValid();
        }

        updateFavorites(true);

        if (showNoIcon || showNoFolder) {
            showNotify(showNoFolder);
        }
    }

    private void showNotify(boolean showNoFolder) {
        String titleKey = showNoFolder ? "FavoriteFolder.WarningTip.noFolder.title" : "FavoriteFolder.WarningTip.noIcon.title";
        String tipKey = showNoFolder ? "FavoriteFolder.WarningTip.noFolder" : "FavoriteFolder.WarningTip.noIcon";

        // Icon wasn't found - notify user.
        final Notification notification = new Notification(
                "FavoriteFolders",
                FavoriteFoldersBundle.message(titleKey),
                FavoriteFoldersBundle.message(tipKey),
                NotificationType.WARNING,
                new NotificationListener() {
                    @Override
                    public void hyperlinkUpdate(@NotNull Notification notification, @NotNull HyperlinkEvent event) {
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

                }
        );

        Project targetProject = getProject() == null || getProject().isDefault() ? null : getProject();
        Notifications.Bus.notify(notification, targetProject);
    }


    protected abstract AConfigPane getConfigPane();

    protected abstract void updateFavorites(boolean firstRun);

    public void initComponent() {
    }

    public void disposeComponent() {
        favorites.clear();
        updateFavorites(false);
    }

    public abstract Project getProject();
}
