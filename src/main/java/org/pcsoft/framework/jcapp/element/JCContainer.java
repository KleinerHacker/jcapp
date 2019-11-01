package org.pcsoft.framework.jcapp.element;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.base.InputMember;
import org.pcsoft.framework.jcapp.style.JCContainerStyle;
import org.pcsoft.framework.jfex.commons.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JCContainer<S extends JCContainerStyle> extends JCElement<S> {
    private final ReadOnlyObjectProperty<JCFocusManager> focusManager =
            new ReadOnlyObjectWrapper<>(new JCFocusManager()).getReadOnlyProperty();

    public JCFocusManager getFocusManager() {
        return focusManager.get();
    }

    public ReadOnlyObjectProperty<JCFocusManager> focusManagerProperty() {
        return focusManager;
    }

    @Override
    protected boolean onKey(NativeKeyEvent nativeKeyEvent) {
        if (getFocusManager().fireKey(nativeKeyEvent))
            return true;

        return super.onKey(nativeKeyEvent);
    }

    //region Focus Manager
    public static final class JCFocusManager implements InputMember {
        private static final Logger LOGGER = LoggerFactory.getLogger(JCFocusManager.class);

        private final ReadOnlyListProperty<JCElement> elementList =
                new ReadOnlyListWrapper<JCElement>(FXCollections.observableArrayList()).getReadOnlyProperty();
        private final ReadOnlyIntegerWrapper currentIndexWrapper = new ReadOnlyIntegerWrapper(0);
        private final ReadOnlyIntegerProperty currentIndex = currentIndexWrapper.getReadOnlyProperty();
        private final ObjectProperty<JCElement> currentElement;

        private final List<JCFocusableElement> focusableElementList = new ArrayList<>();

        private JCFocusManager() {
            elementList.addListener((ListChangeListener<JCElement>) c -> {
                while (c.next()) {
                    if (c.wasRemoved()) {
                        focusableElementList.removeAll(
                                c.getRemoved().stream()
                                        .filter(item -> item instanceof JCFocusableElement)
                                        .map(item -> (JCFocusableElement) item)
                                        .collect(Collectors.toList())
                        );
                    }
                    if (c.wasAdded()) {
                        focusableElementList.addAll(
                                c.getAddedSubList().stream()
                                        .filter(item -> item instanceof JCFocusableElement)
                                        .map(item -> (JCFocusableElement) item)
                                        .collect(Collectors.toList())
                        );
                    }
                }

                validateIndex();
            });

            currentElement = Properties.createProperty(
                    currentIndexWrapper,
                    number -> number.intValue() < 0 ? null : number.intValue() >= focusableElementList.size() ? null : focusableElementList.get(number.intValue()),
                    element -> focusableElementList.indexOf(element)
            );
        }

        protected ObservableList<JCElement> getElementList() {
            return elementList.get();
        }

        protected ReadOnlyListProperty<JCElement> elementListProperty() {
            return elementList;
        }

        public JCElement getCurrentElement() {
            return currentElement.get();
        }

        public ObjectProperty<JCElement> currentElementProperty() {
            return currentElement;
        }

        public int getCurrentIndex() {
            return currentIndex.get();
        }

        public ReadOnlyIntegerProperty currentIndexProperty() {
            return currentIndex;
        }

        public void setCurrentElement(JCElement currentElement) {
            this.currentElement.set(currentElement);
        }

        public void gotoNext() {
            LOGGER.info("Goto next focusable element");
            if (currentIndexWrapper.get() + 1 >= focusableElementList.size()) {
                currentIndexWrapper.set(0);
                return;
            }

            currentIndexWrapper.set(currentIndexWrapper.get() + 1);
        }

        public void gotoPrev() {
            LOGGER.info("Goto prev focusable element");
            if (currentIndexWrapper.get() - 1 <= 0) {
                currentIndexWrapper.set(focusableElementList.size() - 1);
                return;
            }

            currentIndexWrapper.set(currentIndexWrapper.get() - 1);
        }

        @Override
        public boolean fireKey(NativeKeyEvent e) {
            if (e.getKeyCode() == NativeKeyEvent.VC_TAB) {
                LOGGER.info("TAB press detected");
                gotoNext();
                return true;
            }

            return false;
        }

        private void validateIndex() {
            LOGGER.info("Validate index");
            if (focusableElementList.isEmpty()) {
                currentIndexWrapper.set(-1);
                return;
            }

            if (currentIndex.get() >= focusableElementList.size()) {
                currentIndexWrapper.set(focusableElementList.size() - 1);

            }
        }
    }
    //endregion
}
