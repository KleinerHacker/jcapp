package org.pcsoft.framework.jcapp.style;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCSelectableStyle extends JCFocusableStyle {
    private final ObjectProperty<JConsoleColorPair> selectionColor = new SimpleObjectProperty<>(
            //Inverted
            new JConsoleColorPair(
                    JConsoleColor.DEFAULT_BACKGROUND,
                    JConsoleColor.DEFAULT_FOREGROUND
            )
    );

    public JConsoleColorPair getSelectionColor() {
        return selectionColor.get();
    }

    public ObjectProperty<JConsoleColorPair> selectionColorProperty() {
        return selectionColor;
    }

    public void setSelectionColor(JConsoleColorPair selectionColor) {
        this.selectionColor.set(selectionColor);
    }
}
