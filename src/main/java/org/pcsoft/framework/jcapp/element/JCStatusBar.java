package org.pcsoft.framework.jcapp.element;

import org.pcsoft.framework.jcapp.style.JCStatusBarStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jconsole.JConsole;

public final class JCStatusBar extends JCElement<JCStatusBarStyle> {
    @Override
    protected void repaint() {
        JConsole.CURSOR.gotoXY(0,25);
        JConsole.VISUAL.setColor(getStyle().getColor());
        JConsole.clearLine();
    }

    @Override
    protected JCStatusBarStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getStatusBarStyle();
    }

    @Override
    protected JCStatusBarStyle getDefaultStyle() {
        return new JCStatusBarStyle();
    }
}
