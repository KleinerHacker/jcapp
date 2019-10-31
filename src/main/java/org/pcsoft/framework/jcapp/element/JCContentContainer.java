package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import org.pcsoft.framework.jcapp.style.JCContainerStyle;
import org.pcsoft.framework.jcapp.type.JCBounds;
import org.pcsoft.framework.jcapp.type.JCPoint;
import org.pcsoft.framework.jcapp.type.JCSize;

public abstract class JCContentContainer<S extends JCContainerStyle> extends JCContainer<S> {
    protected final ObjectProperty<JCElement> content = new SimpleObjectProperty<>();

    public JCContentContainer() {
        final ChangeListener<Boolean> dirtyListener = (v, o, n) -> {
            if (n) {
                refresh();
            }
        };
        final ChangeListener<Boolean> invalidationListener = (v, o, n) -> {
            if (n) {
                invalidate();
            }
        };
        content.addListener((v, o, n) -> {
            if (o != null) {
                o.setParent(null);
                o.themeProperty().unbind();
                o.dirtyProperty().removeListener(dirtyListener);
                o.invalidProperty().removeListener(invalidationListener);
            }
            if (n != null) {
                n.setParent(this);
                n.themeProperty().bind(themeProperty());
                n.dirtyProperty().addListener(dirtyListener);
                n.invalidProperty().addListener(invalidationListener);
            }

            invalidate();
        });
        dirtyProperty().addListener((v, o, n) -> {
            if (n && content.get() != null) {
                content.get().refresh();
            }
        });
        invalidProperty().addListener((v, o, n) -> {
            if (n && content.get() != null) {
                content.get().invalidate();
            }
        });
    }

    @Override
    public void paint() {
        super.paint();

        if (isVisible()) {
            paintContent();
        }
    }

    protected void paintContent() {
        if (content.get() == null)
            return;

        content.get().paint();
    }

    @Override
    public void measure(JCBounds bounds) {
        super.measure(bounds);

        if (isVisible() && content.get() != null) {
            content.get().measure(JCBounds.createWithSize(
                    getBounds().getLeftTop().add(JCPoint.ONE), getBounds().getSize().sub(new JCSize(2, 2))
            ));
        }
    }
}
