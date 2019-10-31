package org.pcsoft.framework.jcapp.element.pane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.element.JCElement;
import org.pcsoft.framework.jcapp.style.pane.JCAnchorPaneStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.JCBounds;
import org.pcsoft.framework.jcapp.type.JCPoint;

public final class JCAnchorPane extends JCPane<JCAnchorPaneStyle> {
    public static void setLeft(JCElement element, int left) {
        put(element, AnchorMetaData.class, md -> md.setLeft(left));
    }

    public static int getLeft(JCElement element) {
        return get(element, AnchorMetaData.class, AnchorMetaData::getLeft);
    }

    public static void setTop(JCElement element, int top) {
        put(element, AnchorMetaData.class, md -> md.setTop(top));
    }

    public static int getTop(JCElement element) {
        return get(element, AnchorMetaData.class, AnchorMetaData::getTop);
    }

    public static void setLeftTop(JCElement element, JCPoint point) {
        put(element, AnchorMetaData.class, md -> {
            md.setLeft(point.getX());
            md.setTop(point.getY());
        });
    }

    public static JCPoint getLeftTop(JCElement element) {
        return get(element, AnchorMetaData.class, md -> new JCPoint(md.getLeft(), md.getTop()));
    }

    @Override
    public void measure(JCBounds bounds) {
        super.measure(bounds);

        if (isVisible()) {
            for (final JCElement element : children.get()) {
                element.measure(JCBounds.createWithPoints(getBounds().getLeftTop().add(getLeftTop(element)), getBounds().getRightBottom()));
            }
        }
    }

    @Override
    protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
        return false;
    }

    @Override
    protected JCAnchorPaneStyle extractStyleFromTheme(JCTheme theme) {
        return new JCAnchorPaneStyle();
    }

    @Override
    protected JCAnchorPaneStyle getDefaultStyle() {
        return new JCAnchorPaneStyle();
    }

    protected static final class AnchorMetaData extends JCPane.MetaData {
        private final IntegerProperty left = new SimpleIntegerProperty(1), top = new SimpleIntegerProperty(1);

        public int getLeft() {
            return left.get();
        }

        public IntegerProperty leftProperty() {
            return left;
        }

        public void setLeft(int left) {
            this.left.set(left);
        }

        public int getTop() {
            return top.get();
        }

        public IntegerProperty topProperty() {
            return top;
        }

        public void setTop(int top) {
            this.top.set(top);
        }
    }
}
