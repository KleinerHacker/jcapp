package org.pcsoft.framework.jcapp.element;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.style.JCToggleStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JCToggleElement<S extends JCToggleStyle> extends JCMnemonicElement<S> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JCToggleElement.class);

    private final ReadOnlyBooleanWrapper checkedWrapper = new ReadOnlyBooleanWrapper();
    private final ReadOnlyBooleanProperty checked = checkedWrapper.getReadOnlyProperty();
    private final ObjectProperty<JCToggleGroup> toggleGroup = new SimpleObjectProperty<>();

    public JCToggleElement() {
        checked.addListener(o -> refresh());
        toggleGroup.addListener((v, o, n) -> {
            LOGGER.debug("Notify toggle group change");
            if (o != null) {
                n.getElementList().remove(this);
                checkedWrapper.unbind();
            }
            if (n != null) {
                if (!n.getElementList().contains(this)) {
                    n.getElementList().add(this);
                }
                checkedWrapper.bind(n.currentElementProperty().isEqualTo(this));
            }

            refresh();
        });
    }

    public boolean isChecked() {
        return checked.get();
    }

    public ReadOnlyBooleanProperty checkedProperty() {
        return checked;
    }

    public void check() {
        LOGGER.info("Try check element {}", getId());
        if (getToggleGroup() == null) {
            checkedWrapper.set(true);
        } else {
            getToggleGroup().check(this);
        }
    }

    public void uncheck() {
        LOGGER.info("Try uncheck element {}", getId());
        if (getToggleGroup() == null) {
            checkedWrapper.set(false);
        } else {
            getToggleGroup().uncheck(this);
        }
    }

    public void toggleCheck() {
        LOGGER.info("Try toggle element {}", getId());
        if (isChecked()) {
            uncheck();
        } else {
            check();
        }
    }

    public JCToggleGroup getToggleGroup() {
        return toggleGroup.get();
    }

    public ObjectProperty<JCToggleGroup> toggleGroupProperty() {
        return toggleGroup;
    }

    public void setToggleGroup(JCToggleGroup toggleGroup) {
        this.toggleGroup.set(toggleGroup);
    }

    @Override
    protected boolean onKey(NativeKeyEvent e) {
        if (isFocused() && e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
            toggleCheck();
            return true;
        }

        return super.onKey(e);
    }

    //region Toggle Group
    public static final class JCToggleGroup {
        private static final Logger LOGGER = LoggerFactory.getLogger(JCToggleGroup.class);

        private final ReadOnlyListProperty<JCToggleElement> elementList =
                new ReadOnlyListWrapper<JCToggleElement>(FXCollections.observableArrayList()).getReadOnlyProperty();
        private final ReadOnlyIntegerWrapper currentIndexWrapper = new ReadOnlyIntegerWrapper(0);
        private final ReadOnlyIntegerProperty currentIndex = currentIndexWrapper.getReadOnlyProperty();
        private final ObjectBinding<JCToggleElement> currentElement = Bindings.createObjectBinding(
                () -> currentIndex.get() < 0 || currentIndex.get() >= elementList.size() ? null : elementList.get(currentIndex.get()),
                currentIndex
        );

        public JCToggleGroup() {
            elementList.addListener((ListChangeListener<JCToggleElement>) c -> {
                LOGGER.debug("Notify toggle list change");
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (final JCToggleElement element : c.getAddedSubList()) {
                            element.setToggleGroup(this);
                        }
                    }
                    if (c.wasRemoved()) {
                        for (final JCToggleElement element : c.getRemoved()) {
                            element.setToggleGroup(null);
                        }
                    }
                }

                validateIndex();
            });
        }

        public ObservableList<JCToggleElement> getElementList() {
            return elementList.get();
        }

        public ReadOnlyListProperty<JCToggleElement> elementListProperty() {
            return elementList;
        }

        public int getCurrentIndex() {
            return currentIndex.get();
        }

        public ReadOnlyIntegerProperty currentIndexProperty() {
            return currentIndex;
        }

        public JCToggleElement getCurrentElement() {
            return currentElement.get();
        }

        public ObjectBinding<JCToggleElement> currentElementProperty() {
            return currentElement;
        }

        private void validateIndex() {
            LOGGER.info("Validate index");
            if (elementList.isEmpty()) {
                currentIndexWrapper.set(-1);
                return;
            }

            if (currentIndex.get() >= elementList.size()) {
                currentIndexWrapper.set(elementList.size() - 1);
            }
        }

        private void check(JCToggleElement element) {
            LOGGER.info("Check element {}", element.getId());
            currentIndexWrapper.set(elementList.indexOf(element));
        }

        private void uncheck(JCToggleElement element) {
            LOGGER.info("Uncheck element {}", element.getId());
            if (currentElement.get() != element)
                return;

            currentIndexWrapper.set(-1);
        }
    }
    //endregion
}
