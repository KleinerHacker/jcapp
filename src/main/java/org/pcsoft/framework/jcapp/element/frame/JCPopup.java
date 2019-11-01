package org.pcsoft.framework.jcapp.element.frame;

import org.pcsoft.framework.jcapp.style.frame.JCPopupStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.JCBounds;

public final class JCPopup extends JCFrame<JCPopupStyle> {
    public JCPopup() {
        setVisible(false);
    }

    public JCPopup(int width, int height) {
        this();
        setPrefWidth(width);
        setPrefHeight(height);
    }

    @Override
    public void setLeft(int left) {
        super.setLeft(left);
    }

    @Override
    public void setTop(int top) {
        super.setTop(top);
    }

    @Override
    public void measure(JCBounds bounds) {
        super.measure(bounds);
    }

    @Override
    protected void remeasure(JCBounds bounds) {
        setWidth(getPrefWidth());
        setHeight(getPrefHeight());
    }

    @Override
    protected JCPopupStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getPopupStyle();
    }

    @Override
    protected JCPopupStyle getDefaultStyle() {
        return new JCPopupStyle();
    }
}
