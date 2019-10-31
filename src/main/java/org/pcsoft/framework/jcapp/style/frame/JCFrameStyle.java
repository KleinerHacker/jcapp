package org.pcsoft.framework.jcapp.style.frame;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jcapp.style.JCContainerStyle;
import org.pcsoft.framework.jcapp.type.JCBorder;

public abstract class JCFrameStyle extends JCContainerStyle {
    private final ObjectProperty<JCBorder> border = new SimpleObjectProperty<>(JCBorder.SINGLE);

    public JCBorder getBorder() {
        return border.get();
    }

    public ObjectProperty<JCBorder> borderProperty() {
        return border;
    }

    public void setBorder(JCBorder border) {
        this.border.set(border);
    }
}
