package org.pcsoft.framework.jcapp.element.component;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.element.JCSelectorElement;
import org.pcsoft.framework.jcapp.style.component.JCCheckBoxStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.utils.JConsoleUtils;
import org.pcsoft.framework.jconsole.JConsole;

public final class JCCheckBox extends JCSelectorElement<JCCheckBoxStyle> {
    public JCCheckBox() {
    }

    public JCCheckBox(String text) {
        this();
        setText(text);
    }

    public JCCheckBox(String text, char mnemonic) {
        this(text);
        setMnemonic(mnemonic);
    }

    @Override
    protected void repaint() {
        JConsole.CURSOR.gotoXY(getLeft(), getTop());

        JConsole.VISUAL.setColor(getStyle().getSelectorColor());
        JConsole.print(getStyle().getSelector().getLeft());
        JConsole.print(isChecked() ? getStyle().getSelector().getActive() : getStyle().getSelector().getInactive());
        JConsole.print(getStyle().getSelector().getRight());

        JConsole.VISUAL.setColor(getStyle().getColor());
        JConsoleUtils.printAccentString(" " + getText(), getMnemonic(), getStyle().getMnemonicColor());
    }

    @Override
    protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
        return false;
    }

    @Override
    protected JCCheckBoxStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getCheckBoxStyle();
    }

    @Override
    protected JCCheckBoxStyle getDefaultStyle() {
        return new JCCheckBoxStyle();
    }
}
