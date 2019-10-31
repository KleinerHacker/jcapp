package org.pcsoft.framework.jcapp.element.frame;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jcapp.style.frame.JCTitledFrameStyle;
import org.pcsoft.framework.jconsole.JConsole;

public abstract class JCTitledFrame<S extends JCTitledFrameStyle> extends JCFrame<S> {
    private final StringProperty title = new SimpleStringProperty();

    public JCTitledFrame() {
        title.addListener(o -> refresh());
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    protected void repaint() {
        super.repaint();

        if (!StringUtils.isEmpty(title.get())) {
            final String title = " " + this.title.get() + " ";
            JConsole.CURSOR.gotoXY(getBounds().getCenterX() - title.length() / 2, getTop());
            JConsole.print(title);
        }
    }
}
