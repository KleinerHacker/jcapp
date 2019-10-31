package org.pcsoft.framework.jcapp.style.component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jcapp.style.JCStyle;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public final class JCLabelStyle extends JCStyle {
    private final ObjectProperty<JConsoleColorPair> mnemonicColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                    JConsoleColor.DEFAULT_BACKGROUND
            )
    );
    private final ObjectProperty<JConsoleColorPair> focusColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    //Inverted
                    JConsoleColor.DEFAULT_BACKGROUND,
                    JConsoleColor.DEFAULT_FOREGROUND
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

    public JConsoleColorPair getMnemonicColor() {
        return mnemonicColor.get();
    }

    public ObjectProperty<JConsoleColorPair> mnemonicColorProperty() {
        return mnemonicColor;
    }

    public void setMnemonicColor(JConsoleColorPair mnemonicColor) {
        this.mnemonicColor.set(mnemonicColor);
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
