package org.pcsoft.framework.jcapp.element.component;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.base.MnemonicMember;
import org.pcsoft.framework.jcapp.element.JCElement;
import org.pcsoft.framework.jcapp.element.JCFocusableElement;
import org.pcsoft.framework.jcapp.style.component.JCLabelStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.utils.JConsoleUtils;
import org.pcsoft.framework.jconsole.JConsole;

public final class JCLabel extends JCElement<JCLabelStyle> implements MnemonicMember {
    private final StringProperty text = new SimpleStringProperty();
    private final ObjectProperty<Character> mnemonic = new SimpleObjectProperty<>();
    private final ObjectProperty<JCFocusableElement> linkedElement = new SimpleObjectProperty<>();

    private final BooleanBinding focused;

    public JCLabel() {
        text.addListener(o -> invalidate());
        mnemonic.addListener(o -> refresh());

        focused = Bindings.createBooleanBinding(() -> linkedElement.get() != null && linkedElement.get().isFocused(), linkedElement);
        focused.addListener(o -> refresh());
    }

    public JCLabel(String text) {
        this();
        setText(text);
    }

    public JCLabel(String text, char mnemonic) {
        this(text);
        setMnemonic(mnemonic);
    }

    @Override
    public String getText() {
        return text.get();
    }

    @Override
    public StringProperty textProperty() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text.set(text);
    }

    @Override
    public Character getMnemonic() {
        return mnemonic.get();
    }

    @Override
    public ObjectProperty<Character> mnemonicProperty() {
        return mnemonic;
    }

    @Override
    public void setMnemonic(Character mnemonic) {
        this.mnemonic.set(mnemonic);
    }

    public JCFocusableElement getLinkedElement() {
        return linkedElement.get();
    }

    public ObjectProperty<JCFocusableElement> linkedElementProperty() {
        return linkedElement;
    }

    public void setLinkedElement(JCFocusableElement linkedElement) {
        this.linkedElement.set(linkedElement);
    }

    public boolean isFocused() {
        return focused.get();
    }

    public BooleanBinding focusedProperty() {
        return focused;
    }

    @Override
    protected void repaint() {
        JConsole.CURSOR.gotoXY(getLeft(), getTop());
        JConsole.VISUAL.setColor(getStyle().getColor());
        JConsoleUtils.printAccentString(getText(), getMnemonic(), getStyle().getMnemonicColor());
    }

    @Override
    protected boolean onKey(NativeKeyEvent e) {
        if (linkedElement.get() != null && mnemonic.get() != null) {
            if ((e.getModifiers() & NativeKeyEvent.ALT_MASK) != 0 && (Character.toLowerCase(mnemonic.get()) + "").equals(NativeKeyEvent.getKeyText(e.getKeyCode()))) {
                linkedElement.get().setFocused(true);
                return true;
            }
        }

        return false;
    }

    @Override
    protected JCLabelStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getLabelStyle();
    }

    @Override
    protected JCLabelStyle getDefaultStyle() {
        return new JCLabelStyle();
    }
}
