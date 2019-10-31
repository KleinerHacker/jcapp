package org.pcsoft.framework.jcapp.style;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jcapp.type.JCSelector;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCSelectorStyle extends JCMnemonicStyle {
    private final ObjectProperty<JCSelector> selector = new SimpleObjectProperty<>(JCSelector.CORNER);
    private final ObjectProperty<JConsoleColorPair> selectorColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
            )
    );

    public JCSelector getSelector() {
        return selector.get();
    }

    public ObjectProperty<JCSelector> selectorProperty() {
        return selector;
    }

    public void setSelector(JCSelector selector) {
        this.selector.set(selector);
    }

    public JConsoleColorPair getSelectorColor() {
        return selectorColor.get();
    }

    public ObjectProperty<JConsoleColorPair> selectorColorProperty() {
        return selectorColor;
    }

    public void setSelectorColor(JConsoleColorPair selectorColor) {
        this.selectorColor.set(selectorColor);
    }
}
