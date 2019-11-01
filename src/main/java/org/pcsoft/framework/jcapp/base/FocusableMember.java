package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;

public interface FocusableMember {
    void requestFocus();
    ReadOnlyBooleanProperty focusedProperty();
    boolean isFocused();

    void setFocusable(boolean focusable);
    BooleanProperty focusableProperty();
    boolean isFocusable();
}
