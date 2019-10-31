package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.ObjectProperty;

public interface MnemonicMember extends TextMember{
    void setMnemonic(Character mnemonic);
    ObjectProperty<Character> mnemonicProperty();
    Character getMnemonic();
}
