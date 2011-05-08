package org.xblackcat.idea.favorites;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xBlackCat
 */
public abstract class AFavoritesContainer implements BaseComponent, PersistentStateComponent<Element> {
    protected List<FavoriteFolder> favorites = new ArrayList<FavoriteFolder>();

    List<FavoriteFolder> getFavorites() {
        return favorites;
    }

    void setFavorites(List<FavoriteFolder> favorites) {
        this.favorites = new ArrayList<FavoriteFolder>(favorites.size());

        this.favorites.addAll(favorites);

        updateFavorites();
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

        updateFavorites();
    }

    protected abstract void updateFavorites();

    public void initComponent() {
    }

    public void disposeComponent() {
        favorites.clear();
        updateFavorites();
    }
}
