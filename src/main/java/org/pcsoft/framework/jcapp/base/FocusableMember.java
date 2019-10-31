package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.BooleanProperty;

public interface FocusableMember {
    void setFocused(boolean focus);
    BooleanProperty focusedProperty();
    boolean isFocused();

    void setFocusable(boolean focusable);
    BooleanProperty focusableProperty();
    boolean isFocusable();
}
