package org.xblackcat.idea.favorites;

import javax.swing.*;

interface IIconGetter {
    Icon icon();

    String getName();

    boolean isCustom();
}
