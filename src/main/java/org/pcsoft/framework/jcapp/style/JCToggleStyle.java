package org.pcsoft.framework.jcapp.style;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCToggleStyle extends JCMnemonicStyle {
    private final ObjectProperty<JConsoleColorPair> checkingColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
            )
    );

    public JConsoleColorPair getCheckingColor() {
        return checkingColor.get();
    }

    public ObjectProperty<JConsoleColorPair> checkingColorProperty() {
        return checkingColor;
    }

    public void setCheckingColor(JConsoleColorPair checkingColor) {
        this.checkingColor.set(checkingColor);
    }
}
