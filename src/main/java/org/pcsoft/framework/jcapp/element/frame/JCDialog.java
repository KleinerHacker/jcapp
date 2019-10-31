package org.pcsoft.framework.jcapp.element.frame;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.style.frame.JCDialogStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.JCHorizontalAlignment;
import org.pcsoft.framework.jcapp.type.JCVerticalAlignment;

public final class JCDialog extends JCTitledFrame<JCDialogStyle> {
    public JCDialog() {
        setHorizontalAlignment(JCHorizontalAlignment.Left);
        setVerticalAlignment(JCVerticalAlignment.Top);
    }

    public JCDialog(String title) {
        this();
        setTitle(title);
    }

    public JCDialog(String title, int width, int height) {
        this(title);
        setPrefWidth(width);
        setPrefHeight(height);
    }

    @Override
    protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
        return false;
    }

    @Override
    protected JCDialogStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getDialogStyle();
    }

    @Override
    protected JCDialogStyle getDefaultStyle() {
        return new JCDialogStyle();
    }
}
