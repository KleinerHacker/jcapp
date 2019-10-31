package org.pcsoft.framework.jcapp.element.frame;

import javafx.beans.property.ObjectProperty;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jcapp.element.JCContentContainer;
import org.pcsoft.framework.jcapp.element.JCElement;
import org.pcsoft.framework.jcapp.style.frame.JCFrameStyle;
import org.pcsoft.framework.jconsole.JConsole;

public abstract class JCFrame<S extends JCFrameStyle> extends JCContentContainer<S> {
    public JCElement getContent() {
        return content.get();
    }

    public ObjectProperty<JCElement> contentProperty() {
        return content;
    }

    public void setContent(JCElement content) {
        this.content.set(content);
    }

    @Override
    protected void repaint() {
        JConsole.VISUAL.setColor(getStyle().getColor());

        JConsole.CURSOR.gotoXY(getLeft(), getTop());
        JConsole.print(getStyle().getBorder().getUpperLeft());
        JConsole.print(StringUtils.repeat(getStyle().getBorder().getHorizontal() + "", getWidth() - 2));
        JConsole.print(getStyle().getBorder().getUpperRight());

        for (int i = 1; i < getHeight(); i++) {
            JConsole.CURSOR.gotoXY(getLeft(), getTop() + i);
            JConsole.print(getStyle().getBorder().getVertical());
            JConsole.print(StringUtils.repeat(" ", getWidth() - 2));
            JConsole.print(getStyle().getBorder().getVertical());
        }

        JConsole.CURSOR.gotoXY(getLeft(), getTop() + getHeight());
        JConsole.print(getStyle().getBorder().getLowerLeft());
        JConsole.print(StringUtils.repeat(getStyle().getBorder().getHorizontal() + "", getWidth() - 2));
        JConsole.print(getStyle().getBorder().getLowerRight());
    }
}
