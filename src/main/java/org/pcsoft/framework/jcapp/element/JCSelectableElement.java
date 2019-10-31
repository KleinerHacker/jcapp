package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jcapp.style.JCSelectableStyle;

public abstract class JCSelectableElement<S extends JCSelectableStyle> extends JCFocusableElement<S> {
    private final BooleanProperty selected = new SimpleBooleanProperty();

    public boolean isSelected() {
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
