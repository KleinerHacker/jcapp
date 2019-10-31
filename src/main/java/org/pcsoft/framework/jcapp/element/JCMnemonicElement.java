package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.pcsoft.framework.jcapp.base.MnemonicMember;
import org.pcsoft.framework.jcapp.style.JCMnemonicStyle;

public abstract class JCMnemonicElement<S extends JCMnemonicStyle> extends JCFocusableElement<S> implements MnemonicMember {
    private final StringProperty text = new SimpleStringProperty();
    private final ObjectProperty<Character> mnemonic = new SimpleObjectProperty<>();

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
}
