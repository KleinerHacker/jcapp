package org.pcsoft.framework.jcapp.element.frame;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.style.frame.JCWindowStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;

public class JCWindow extends JCTitledFrame<JCWindowStyle> {
    public JCWindow() {

    }

    public JCWindow(String title) {
        this();
        setTitle(title);
    }

    public JCWindow(String title, int width, int height) {
        this(title);
        setPrefWidth(width);
        setPrefHeight(height);
    }

    @Override
    protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
        return false;
    }

    @Override
    protected JCWindowStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getWindowStyle();
    }

    @Override
    protected JCWindowStyle getDefaultStyle() {
        return new JCWindowStyle();
    }
}
