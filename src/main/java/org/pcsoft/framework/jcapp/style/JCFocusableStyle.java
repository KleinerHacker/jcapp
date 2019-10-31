package org.pcsoft.framework.jcapp.style;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCFocusableStyle extends JCStyle {
    private final ObjectProperty<JConsoleColorPair> focusColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                    JConsoleColor.DEFAULT_BACKGROUND
            )
    );
    private final ObjectProperty<JConsoleColorPair> disableColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.White),
                    JConsoleColor.DEFAULT_BACKGROUND
            )
    );

    public JConsoleColorPair getFocusColor() {
        return focusColor.get();
    }

    public ObjectProperty<JConsoleColorPair> focusColorProperty() {
        return focusColor;
    }

    public void setFocusColor(JConsoleColorPair focusColor) {
        this.focusColor.set(focusColor);
    }

    public JConsoleColorPair getDisableColor() {
        return disableColor.get();
    }

    public ObjectProperty<JConsoleColorPair> disableColorProperty() {
        return disableColor;
    }

    public void setDisableColor(JConsoleColorPair disableColor) {
        this.disableColor.set(disableColor);
    }
}
