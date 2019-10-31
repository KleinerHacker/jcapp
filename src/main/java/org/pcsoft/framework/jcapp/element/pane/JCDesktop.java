package org.pcsoft.framework.jcapp.element.pane;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import org.apache.commons.lang.StringUtils;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.element.JCElement;
import org.pcsoft.framework.jcapp.element.JCStatusBar;
import org.pcsoft.framework.jcapp.element.menu.JCMenuBar;
import org.pcsoft.framework.jcapp.style.pane.JCDesktopStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.JCBounds;
import org.pcsoft.framework.jcapp.type.JCPoint;
import org.pcsoft.framework.jconsole.JConsole;
import org.pcsoft.framework.jconsole.utils.AsciiUtils;

/**
 * Basic pane for {@link org.pcsoft.framework.jcapp.JCApplication}, Entry Point
 */
public final class JCDesktop extends JCPane<JCDesktopStyle> {
    //region Static Layout Methods
    public static void setLeft(JCElement element, int left) {
        put(element, DesktopMetaData.class, md -> md.setLeft(left));
    }

    public static int getLeft(JCElement element) {
        return get(element, DesktopMetaData.class, DesktopMetaData::getLeft);
    }

    public static void setTop(JCElement element, int top) {
        put(element, DesktopMetaData.class, md -> md.setTop(top));
    }

    public static int getTop(JCElement element) {
        return get(element, DesktopMetaData.class, DesktopMetaData::getTop);
    }

    public static void setLeftTop(JCElement element, JCPoint point) {
        put(element, DesktopMetaData.class, md -> {
            md.setLeft(point.getX());
            md.setTop(point.getY());
        });
    }

    public static JCPoint getLeftTop(JCElement element) {
        return get(element, DesktopMetaData.class, md -> new JCPoint(md.getLeft(), md.getTop()));
    }
    //endregion

    private final ObjectProperty<JCMenuBar> menuBar = new SimpleObjectProperty<>();
    private final ObjectProperty<JCStatusBar> statusBar = new SimpleObjectProperty<>();

    public JCDesktop() {
        final ChangeListener<Boolean> dirtyListener = (v, o, n) -> {
            if (n) {
                refresh();
            }
        };
        final ChangeListener<Boolean> invalidListener = (v, o, n) -> {
            if (n) {
                invalidate();
            }
        };
        final ChangeListener<JCElement> elementChangeListener = (v, o, n) -> {
            if (o != null) {
                o.themeProperty().unbind();
                o.dirtyProperty().removeListener(dirtyListener);
                o.invalidProperty().removeListener(invalidListener);
            }
            if (n != null) {
                n.themeProperty().bind(themeProperty());
                n.dirtyProperty().addListener(dirtyListener);
                n.invalidProperty().addListener(invalidListener);
            }

            invalidate();
        };

        menuBar.addListener(o -> refresh());
        menuBar.addListener(elementChangeListener);

        statusBar.addListener(o -> refresh());
        statusBar.addListener(elementChangeListener);

        //If desktop is dirty, all children must be dirty
        dirtyProperty().addListener((v, o, n) -> {
            if (n) {
                if (getMenuBar() != null) {
                    getMenuBar().refresh();
                }
                if (getStatusBar() != null) {
                    getStatusBar().refresh();
                }
            }
        });
    }

    public JCMenuBar getMenuBar() {
        return menuBar.get();
    }

    public ObjectProperty<JCMenuBar> menuBarProperty() {
        return menuBar;
    }

    public void setMenuBar(JCMenuBar menuBar) {
        this.menuBar.set(menuBar);
    }

    public JCStatusBar getStatusBar() {
        return statusBar.get();
    }

    public ObjectProperty<JCStatusBar> statusBarProperty() {
        return statusBar;
    }

    public void setStatusBar(JCStatusBar statusBar) {
        this.statusBar.set(statusBar);
    }

    //region Paint
    @Override
    protected void repaint() {
        JConsole.CURSOR.goHome();
        JConsole.VISUAL.setColor(getStyle().getColor());

        final String line = StringUtils.repeat( AsciiUtils.getAscii(getStyle().getBackgroundCharacter()) + "", 80);
        for (int y = 0; y < 25; y++) {
            if (y == 24) {
                JConsole.print(line);
            } else {
                JConsole.println(line);
            }
        }

        JConsole.CURSOR.goHome();
    }

    @Override
    protected void paintChildren() {
        super.paintChildren();

        if (menuBar.get() != null) {
            menuBar.get().paint();
        }
        if (statusBar.get() != null) {
            statusBar.get().paint();
        }
    }
    //endregion

    //region Measure
    @Override
    public void measure(JCBounds bounds) {
        super.measure(bounds);

        if (isVisible()) {
            if (menuBar.get() != null) {
                menuBar.get().measure(bounds);
            }
            if (statusBar.get() != null) {
                statusBar.get().measure(bounds);
            }

            for (final JCElement element : children.get()) {
                element.measure(JCBounds.createWithPoints(getBounds().getLeftTop().add(getLeftTop(element)), getBounds().getRightBottom()));
            }
        }
    }
    //endregion

    //region Input
    @Override
    public boolean onKey(NativeKeyEvent nativeKeyEvent) {
        if (menuBar.get() != null) {
            if (menuBar.get().fireKey(nativeKeyEvent))
                return true;
        }
        if (statusBar.get() != null) {
            if (statusBar.get().fireKey(nativeKeyEvent))
                return true;
        }

        return false;
    }
    //endregion

    //region Style
    @Override
    protected JCDesktopStyle extractStyleFromTheme(JCTheme theme) {
        return theme.getDesktopStyle();
    }

    @Override
    protected JCDesktopStyle getDefaultStyle() {
        return new JCDesktopStyle();
    }
    //endregion

    //region Layout Meta Data
    protected static final class DesktopMetaData extends JCPane.MetaData {
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
    //endregion
}
