package org.pcsoft.framework.jcapp.element.menu;

import javafx.beans.property.*;
import org.pcsoft.framework.jcapp.base.InvalidationMember;
import org.pcsoft.framework.jcapp.base.MnemonicMember;
import org.pcsoft.framework.jcapp.base.SelectableMember;

public abstract class JCMenuBase implements InvalidationMember, SelectableMember, MnemonicMember {
    private final StringProperty text = new SimpleStringProperty();
    private final ObjectProperty<Character> mnemonic = new SimpleObjectProperty<>();
    private final BooleanProperty selected = new SimpleBooleanProperty();

    private final ReadOnlyBooleanWrapper dirtyWrapper = new ReadOnlyBooleanWrapper(true);
    private final ReadOnlyBooleanProperty dirty = dirtyWrapper.getReadOnlyProperty();

    public JCMenuBase() {
        text.addListener(o -> refresh());
        mnemonic.addListener(o -> refresh());
        selected.addListener(o -> refresh());
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

    @Override
    public boolean isSelected() {
        return selected.get();
    }

    @Override
    public BooleanProperty selectedProperty() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    @Override
    public boolean isDirty() {
        return dirty.get();
    }

    @Override
    public ReadOnlyBooleanProperty dirtyProperty() {
        return dirty;
    }

    @Override
    public void refresh() {
        dirtyWrapper.set(true);
    }

    @Override
    public final boolean isInvalid() {
        return false;
    }

    @Override
    public final ReadOnlyBooleanProperty invalidProperty() {
        return new ReadOnlyBooleanWrapper(false).getReadOnlyProperty();
    }

    @Override
    public final void invalidate() {
        //Empty
    }
}
