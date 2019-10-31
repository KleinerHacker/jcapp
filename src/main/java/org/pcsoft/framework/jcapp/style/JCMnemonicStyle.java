package org.pcsoft.framework.jcapp.style;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCMnemonicStyle extends JCFocusableStyle {
    private final ObjectProperty<JConsoleColorPair> mnemonicColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                    JConsoleColor.DEFAULT_BACKGROUND
            )
    );

    public JConsoleColorPair getMnemonicColor() {
        return mnemonicColor.get();
    }

    public ObjectProperty<JConsoleColorPair> mnemonicColorProperty() {
        return mnemonicColor;
    }

    public void setMnemonicColor(JConsoleColorPair mnemonicColor) {
        this.mnemonicColor.set(mnemonicColor);
    }
}
