package org.pcsoft.framework.jcapp.style.frame;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public abstract class JCTitledFrameStyle extends JCFrameStyle {
    private final ObjectProperty<JConsoleColorPair> titleColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    //Inverted
                    JConsoleColor.DEFAULT_BACKGROUND,
                    JConsoleColor.DEFAULT_FOREGROUND
            )
    );

    public JConsoleColorPair getTitleColor() {
        return titleColor.get();
    }

    public ObjectProperty<JConsoleColorPair> titleColorProperty() {
        return titleColor;
    }

    public void setTitleColor(JConsoleColorPair titleColor) {
        this.titleColor.set(titleColor);
    }
}
