package org.pcsoft.framework.jcapp.style.pane;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public final class JCDesktopStyle extends JCPaneStyle {
    private final ObjectProperty<Character> backgroundCharacter = new SimpleObjectProperty<>(' ');

    public Character getBackgroundCharacter() {
        return backgroundCharacter.get();
    }

    public ObjectProperty<Character> backgroundCharacterProperty() {
        return backgroundCharacter;
    }

    public void setBackgroundCharacter(Character backgroundCharacter) {
        this.backgroundCharacter.set(backgroundCharacter);
    }
}
