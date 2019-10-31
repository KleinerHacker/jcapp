package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import org.pcsoft.framework.jcapp.style.JCContainerStyle;

public abstract class JCChildrenContainer<S extends JCContainerStyle> extends JCContainer<S> {
    protected final ReadOnlyListProperty<JCElement> children =
            new ReadOnlyListWrapper<JCElement>(FXCollections.observableArrayList()).getReadOnlyProperty();

    protected JCChildrenContainer() {
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
        children.addListener((ListChangeListener<JCElement>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (final JCElement element : c.getAddedSubList()) {
                        element.setParent(this);
                        element.themeProperty().bind(themeProperty());
                        element.dirtyProperty().addListener(dirtyListener);
                        element.invalidProperty().addListener(invalidationListener);
                    }
                }
                if (c.wasRemoved()) {
                    for (final JCElement element : c.getRemoved()) {
                        element.setParent(null);
                        element.themeProperty().unbind();
                        element.dirtyProperty().removeListener(dirtyListener);
                        element.invalidProperty().removeListener(invalidationListener);
                    }
                }
            }

            invalidate();
        });
        dirtyProperty().addListener((v, o, n) -> {
            if (n) {
                for (final JCElement element : children) {
                    element.refresh();
                }
            }
        });
        invalidProperty().addListener((v, o, n) -> {
            if (n) {
                for (final JCElement element : children) {
                    element.invalidate();
                }
            }
        });
    }

    @Override
    public void paint() {
        super.paint();

        if (isVisible()) {
            paintChildren();
        }
    }

    protected void paintChildren() {
        for (final JCElement element : children.get()) {
            element.paint();
        }
    }
}
