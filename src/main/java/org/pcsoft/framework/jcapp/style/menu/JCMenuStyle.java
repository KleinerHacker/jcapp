package org.pcsoft.framework.jcapp.style.menu;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.pcsoft.framework.jcapp.style.JCFocusableStyle;
import org.pcsoft.framework.jcapp.type.JCBorder;
import org.pcsoft.framework.jcapp.type.JCSeparator;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public class JCMenuStyle extends JCFocusableStyle {
    private final ObjectProperty<JConsoleColorPair> mnemonicColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createHighlighted(JConsoleColor.ColorValue.Red),
                    JConsoleColor.DEFAULT_BACKGROUND
            )
    );
    private final ObjectProperty<JConsoleColorPair> selectionColor = new SimpleObjectProperty<>(
            new JConsoleColorPair(
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                    JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
            )
    );

    private final ObjectProperty<JCBorder> popupBorder = new SimpleObjectProperty<>(JCBorder.SINGLE);
    private final ObjectProperty<JCSeparator> popupSeparator = new SimpleObjectProperty<>(JCSeparator.SINGLE);

    public JConsoleColorPair getMnemonicColor() {
        return mnemonicColor.get();
    }

    public ObjectProperty<JConsoleColorPair> mnemonicColorProperty() {
        return mnemonicColor;
    }

    public void setMnemonicColor(JConsoleColorPair mnemonicColor) {
        this.mnemonicColor.set(mnemonicColor);
    }

    public JConsoleColorPair getSelectionColor() {
        return selectionColor.get();
    }

    public ObjectProperty<JConsoleColorPair> selectionColorProperty() {
        return selectionColor;
    }

    public void setSelectionColor(JConsoleColorPair selectionColor) {
        this.selectionColor.set(selectionColor);
    }

    public JCBorder getPopupBorder() {
        return popupBorder.get();
    }

    public ObjectProperty<JCBorder> popupBorderProperty() {
        return popupBorder;
    }

    public void setPopupBorder(JCBorder popupBorder) {
        this.popupBorder.set(popupBorder);
    }

    public JCSeparator getPopupSeparator() {
        return popupSeparator.get();
    }

    public ObjectProperty<JCSeparator> popupSeparatorProperty() {
        return popupSeparator;
    }

    public void setPopupSeparator(JCSeparator popupSeparator) {
        this.popupSeparator.set(popupSeparator);
    }
}
