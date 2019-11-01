package org.pcsoft.framework.jcapp.element.component;

import org.pcsoft.framework.jcapp.element.JCToggleElement;
import org.pcsoft.framework.jcapp.style.component.JCRadioButtonStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.utils.JConsoleUtils;
import org.pcsoft.framework.jconsole.JConsole;

public final class JCRadioButton extends JCToggleElement<JCRadioButtonStyle> {
    public JCRadioButton() {
    }

    public JCRadioButton(String text) {
        this();
        setText(text);
    }

    public JCRadioButton(String text, char mnemonic) {
        this(text);
        setMnemonic(mnemonic);
    }

    @Override
    protected void repaint() {
        JConsole.CURSOR.gotoXY(getLeft(), getTop());

        if (isDisabled()) {
            JConsole.VISUAL.setColor(getStyle().getDisableColor());
        } else if (isChecked()) {
            JConsole.VISUAL.setColor(getStyle().getCheckingColor());
        } else {
            JConsole.VISUAL.setColor(getStyle().getSelectorColor());
        }
        JConsole.print(getStyle().getSelector().getLeft());
        JConsole.print(isChecked() ? getStyle().getSelector().getActive() : getStyle().getSelector().getInactive());
        JConsole.print(getStyle().getSelector().getRight());

        if (isDisabled()) {
            JConsole.VISUAL.setColor(getStyle().getDisableColor());
        } else if (isFocused()) {
            JConsole.VISUAL.setColor(getStyle().getFocusColor());
        } else {
            JConsole.VISUAL.setColor(getStyle().getColor());
        }
        JConsoleUtils.printAccentString(" " + getText(), getMnemonic(), getStyle().getMnemonicColor());
    }

    @Override
    protected JCRadioButtonStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getRadioButtonStyle();
    }

    @Override
    protected JCRadioButtonStyle getDefaultStyle() {
        return new JCRadioButtonStyle();
    }
}
