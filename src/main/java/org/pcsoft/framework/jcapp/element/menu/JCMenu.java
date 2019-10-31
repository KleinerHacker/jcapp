package org.pcsoft.framework.jcapp.element.menu;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public final class JCMenu extends JCMenuBase {
    private final ReadOnlyListProperty<JCMenuBase> items =
            new ReadOnlyListWrapper<JCMenuBase>(FXCollections.observableArrayList()).getReadOnlyProperty();

    public JCMenu(JCMenuBase... subMenu) {
        items.addListener((ListChangeListener<JCMenuBase>) c -> refresh());
        items.setAll(subMenu);
    }

    public JCMenu(String name, JCMenuBase... subMenu) {
        this(subMenu);
        setText(name);
    }

    public JCMenu(String name, char mnemonic, JCMenuBase... subMenu) {
        this(name, subMenu);
        setMnemonic(mnemonic);
    }

    public ObservableList<JCMenuBase> getItems() {
        return items.get();
    }

    public ReadOnlyListProperty<JCMenuBase> itemsProperty() {
        return items;
    }
}
