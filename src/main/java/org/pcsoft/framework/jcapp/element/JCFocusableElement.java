package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jcapp.base.FocusableMember;
import org.pcsoft.framework.jcapp.style.JCFocusableStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JCFocusableElement<S extends JCFocusableStyle> extends JCElement<S> implements FocusableMember {
    private static final Logger LOGGER = LoggerFactory.getLogger(JCFocusableElement.class);

    private final ReadOnlyBooleanWrapper focusedWrapper = new ReadOnlyBooleanWrapper(false);
    private final ReadOnlyBooleanProperty focused = focusedWrapper.getReadOnlyProperty();
    private final BooleanProperty focusable = new SimpleBooleanProperty(true);
    private final BooleanProperty disabled = new SimpleBooleanProperty(false);

    public JCFocusableElement() {
        parentProperty().addListener((v, o, n) -> {
            LOGGER.debug("Detect new parent");
            if (o != null) {
                focusedWrapper.unbind();
                focusedWrapper.set(false);
            }
            if (n != null) {
                focusedWrapper.bind(n.getFocusManager().currentElementProperty().isEqualTo(this));
            }
        });
        focused.addListener(o -> refresh());
        focusable.addListener(o -> refresh());
        disabled.addListener(o -> refresh());
    }

    @Override
    public boolean isFocused() {
        return focused.get();
    }

    @Override
    public ReadOnlyBooleanProperty focusedProperty() {
        return focused;
    }

    @Override
    public void requestFocus() {
        LOGGER.info("Try to request focus (parent set: {})", getParent() != null);
        if (getParent() == null)
            return;

        getParent().getFocusManager().setCurrentElement(this);
    }

    @Override
    public boolean isFocusable() {
        return focusable.get();
    }

    @Override
    public BooleanProperty focusableProperty() {
        return focusable;
    }

    @Override
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
