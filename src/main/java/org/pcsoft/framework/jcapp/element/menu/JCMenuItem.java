package org.pcsoft.framework.jcapp.element.menu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.function.Consumer;

public final class JCMenuItem extends JCMenuBase {
    private final ObjectProperty<Consumer<JCMenuItem>> onAction = new SimpleObjectProperty<>();

    public JCMenuItem() {
    }

    public JCMenuItem(String name) {
        this();
        setText(name);
    }

    public JCMenuItem(String name, char mnemonic) {
        this(name);
        setMnemonic(mnemonic);
    }

    public Consumer<JCMenuItem> getOnAction() {
        return onAction.get();
    }

    public ObjectProperty<Consumer<JCMenuItem>> onActionProperty() {
        return onAction;
    }

    public void setOnAction(Consumer<JCMenuItem> onAction) {
        this.onAction.set(onAction);
    }
}
