package org.xblackcat.idea.favorites;

import javax.swing.*;

interface IIconGetter {
    Icon getIcon();

    String getName();

    boolean isCustom();
}
