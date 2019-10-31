package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jcapp.style.JCFocusableStyle;

public abstract class JCFocusableElement<S extends JCFocusableStyle> extends JCElement<S> {
    private final BooleanProperty focused = new SimpleBooleanProperty();
    private final BooleanProperty focusable = new SimpleBooleanProperty();
    private final BooleanProperty disabled = new SimpleBooleanProperty();

    public boolean isFocused() {
        return focused.get();
    }

    public BooleanProperty focusedProperty() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused.set(focused);
    }

    public boolean isFocusable() {
        return focusable.get();
    }

    public BooleanProperty focusableProperty() {
        return focusable;
    }

    public void setFocusable(boolean focusable) {
        this.focusable.set(focusable);
    }

    public boolean isDisabled() {
        return disabled.get();
    }

    public BooleanProperty disabledProperty() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled.set(disabled);
    }
}
