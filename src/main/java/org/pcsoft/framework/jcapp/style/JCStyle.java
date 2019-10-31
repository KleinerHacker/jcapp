package org.pcsoft.framework.jcapp.style;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCStyle {
    private final ObjectProperty<JConsoleColorPair> color = new SimpleObjectProperty<>(JConsoleColorPair.DEFAULT);

    public JConsoleColorPair getColor() {
        return color.get();
    }

    public ObjectProperty<JConsoleColorPair> colorProperty() {
        return color;
    }

    public void setColor(JConsoleColorPair color) {
        this.color.set(color);
    }
}
