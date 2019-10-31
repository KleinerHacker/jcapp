package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.pcsoft.framework.jcapp.style.JCSelectorStyle;

public abstract class JCSelectorElement<S extends JCSelectorStyle> extends JCMnemonicElement<S> {
    private final BooleanProperty checked = new SimpleBooleanProperty();

    public JCSelectorElement() {
        checked.addListener(o -> refresh());
    }

    public boolean isChecked() {
        return checked.get();
    }

    public BooleanProperty checkedProperty() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked.set(checked);
    }
}
