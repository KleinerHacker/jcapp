package org.pcsoft.framework.jcapp.element.menu;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.apache.commons.lang.StringUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.element.JCElement;
import org.pcsoft.framework.jcapp.element.JCFocusableElement;
import org.pcsoft.framework.jcapp.element.JCSelectableElement;
import org.pcsoft.framework.jcapp.element.frame.JCPopup;
import org.pcsoft.framework.jcapp.style.JCSelectableStyle;
import org.pcsoft.framework.jcapp.style.JCStyle;
import org.pcsoft.framework.jcapp.style.menu.JCMenuStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.JCBounds;
import org.pcsoft.framework.jcapp.type.JCSeparator;
import org.pcsoft.framework.jcapp.utils.JConsoleUtils;
import org.pcsoft.framework.jconsole.JConsole;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class JCMenuBar extends JCFocusableElement<JCMenuStyle> {
    private final ReadOnlyListProperty<JCMenu> items =
            new ReadOnlyListWrapper<JCMenu>(FXCollections.observableArrayList(
                    o -> new Observable[]{o.textProperty(), o.selectedProperty(), o.mnemonicProperty()}
            )).getReadOnlyProperty();

    private final List<JCPopup> popupList = new ArrayList<>();

    public JCMenuBar(JCMenu... subMenu) {
        items.addListener((ListChangeListener<JCMenu>) c -> refresh());
        items.setAll(subMenu);
    }

    public ObservableList<JCMenu> getItems() {
        return items.get();
    }

    public ReadOnlyListProperty<JCMenu> itemsProperty() {
        return items;
    }

    @Override
    public void paint() {
        super.paint();

        if (isVisible()) {
            for (final JCPopup popup : popupList) {
                popup.paint();
            }
        }
    }

    @Override
    public void repaint() {
        JConsole.CURSOR.goHome();
        JConsole.VISUAL.setColor(getStyle().getColor());
        JConsole.clearLine();

        for (final JCMenu item : items) {
            if (item.isSelected()) {
                JConsole.VISUAL.setColor(getStyle().getSelectionColor());
                JConsole.print(" " + item.getText() + " ");
            } else {
                JConsole.VISUAL.setColor(getStyle().getColor());
                JConsoleUtils.printAccentString(" " + item.getText() + " ", item.getMnemonic(), getStyle().getMnemonicColor());
            }
        }
    }

    @Override
    public void measure(JCBounds bounds) {
        super.measure(bounds);

        if (isVisible()) {
            for (final JCPopup popup : popupList) {
                popup.measure(bounds);
            }
        }
    }

    @Override
    public boolean onKey(NativeKeyEvent e) {
        if ((e.getModifiers() & NativeKeyEvent.ALT_MASK) != 0) {
            final String keyChar = NativeKeyEvent.getKeyText(e.getKeyCode()).toUpperCase();
            for (final JCMenu menu : items) {
                if (menu.getMnemonic() != null) {
                    if (keyChar.equals(Character.toUpperCase(menu.getMnemonic()) + "")) {
                        selectMenu(menu);
                        return true;
                    }
                }
            }

            return false;
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE && !popupList.isEmpty()) {
            popupList.remove(popupList.size() - 1);
            selectMenu(null);

            return true;
        }

        return false;
    }

    @Override
    protected JCMenuStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getMenuStyle();
    }

    @Override
    protected JCMenuStyle getDefaultStyle() {
        return new JCMenuStyle();
    }

    private void selectMenu(JCMenu menu) {
        for (final JCMenu item : items) {
            if (item == menu)
                continue;
            item.setSelected(false);
        }

        if (menu != null) {
            menu.setSelected(true);

            final JCPopup popup = new JCPopup(calculatePopupWidth(menu), calculatePopupHeight(menu));
            popup.setLeft(calculatePopupX(menu));
            popup.setTop(2);
            popup.setVisible(true);
            popup.themeProperty().bind(themeProperty());
            popupList.add(popup);
        }
    }

    private int calculatePopupHeight(JCMenu menu) {
        return menu.getItems().size() + 2;
    }

    private int calculatePopupWidth(JCMenu menu) {
        final int width;
        if (!menu.getItems().isEmpty()) {
            width = menu.getItems().stream().max(Comparator.comparingInt(o -> o.getText().length())).get().getText().length() + 2;
        } else {
            width = 25;
        }
        return width;
    }

    private int calculatePopupX(JCMenu menu) {
        int counter = 1;
        for (final JCMenu item : items) {
            if (item == menu)
                return counter;
            counter += item.getText().length();
        }

        return counter;
    }

    //region UI Menu Elements
    private static final class InternalMenuLabel extends JCSelectableElement<InternalMenuLabelStyle> {
        private final StringProperty text = new SimpleStringProperty();
        private final ObjectProperty<Character> mnemonic = new SimpleObjectProperty<>();

        public InternalMenuLabel() {
            text.addListener(o -> refresh());
            mnemonic.addListener(o -> refresh());
        }

        public String getText() {
            return text.get();
        }

        public StringProperty textProperty() {
            return text;
        }

        public void setText(String text) {
            this.text.set(text);
        }

        public Character getMnemonic() {
            return mnemonic.get();
        }

        public ObjectProperty<Character> mnemonicProperty() {
            return mnemonic;
        }

        public void setMnemonic(Character mnemonic) {
            this.mnemonic.set(mnemonic);
        }

        @Override
        protected void repaint() {
            JConsole.CURSOR.gotoXY(getLeft(), getTop());
            JConsole.VISUAL.setColor(getStyle().getColor());

            JConsole.print(text.get());
        }

        @Override
        protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
            return false;
        }

        @Override
        protected InternalMenuLabelStyle extractStyleFromTheme(JCTheme theme) {
            final InternalMenuLabelStyle style = new InternalMenuLabelStyle();
            style.setColor(theme.getMenuStyle().getColor());
            style.setMnemonicColor(theme.getMenuStyle().getMnemonicColor());
            style.setSelectionColor(theme.getMenuStyle().getSelectionColor());

            return style;
        }

        @Override
        protected InternalMenuLabelStyle getDefaultStyle() {
            return new InternalMenuLabelStyle();
        }
    }

    private static final class InternalMenuLabelStyle extends JCSelectableStyle {
        private final ObjectProperty<JConsoleColorPair> mnemonicColor = new SimpleObjectProperty<>(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.DEFAULT_BACKGROUND
                )
        );

        public JConsoleColorPair getMnemonicColor() {
            return mnemonicColor.get();
        }

        public ObjectProperty<JConsoleColorPair> mnemonicColorProperty() {
            return mnemonicColor;
        }

        public void setMnemonicColor(JConsoleColorPair mnemonicColor) {
            this.mnemonicColor.set(mnemonicColor);
        }
    }

    private static final class InternalMenuSeparator extends JCElement<InternalMenuSeparatorStyle> {
        @Override
        protected void repaint() {
            JConsole.CURSOR.gotoXY(getLeft() - 1, getTop());
            JConsole.VISUAL.setColor(getStyle().getColor());

            JConsole.print(getStyle().getSeparator().getLeftCross());
            JConsole.print(StringUtils.repeat(getStyle().getSeparator().getLine() + "", getParent().getPrefWidth() - 2));
            JConsole.print(getStyle().getSeparator().getRightCross());
        }

        @Override
        protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
            return false;
        }

        @Override
        protected InternalMenuSeparatorStyle extractStyleFromTheme(JCTheme theme) {
            final InternalMenuSeparatorStyle style = new InternalMenuSeparatorStyle();
            style.setColor(theme.getMenuStyle().getColor());
            style.setSeparator(theme.getMenuStyle().getPopupSeparator());

            return style;
        }

        @Override
        protected InternalMenuSeparatorStyle getDefaultStyle() {
            return new InternalMenuSeparatorStyle();
        }
    }

    private static final class InternalMenuSeparatorStyle extends JCStyle {
        private final ObjectProperty<JCSeparator> separator = new SimpleObjectProperty<>(JCSeparator.SINGLE);

        public JCSeparator getSeparator() {
            return separator.get();
        }

        public ObjectProperty<JCSeparator> separatorProperty() {
            return separator;
        }

        public void setSeparator(JCSeparator separator) {
            this.separator.set(separator);
        }
    }
    //endregion
}
