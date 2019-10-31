package org.pcsoft.framework.jcapp.element.pane;

import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.ObservableList;
import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jcapp.element.JCChildrenContainer;
import org.pcsoft.framework.jcapp.element.JCElement;
import org.pcsoft.framework.jcapp.style.pane.JCPaneStyle;
import org.pcsoft.framework.jconsole.JConsole;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Base class for all panes
 * @param <S>
 */
public abstract class JCPane<S extends JCPaneStyle> extends JCChildrenContainer<S> {
    //region Static Layout Methods
    private static final Map<JCElement, MetaData> metaDataMap = new HashMap<>();

    /**
     * Put value to layout meta data based on given element
     * @param element Layout value is for this element
     * @param clazz Class of {@link MetaData} to use
     * @param setter Setter function to set value into meta data
     * @param <MD> Type of meta data
     */
    protected static <MD extends MetaData>void put(JCElement element, Class<MD> clazz, Consumer<MD> setter) {
        setter.accept(get(element, clazz));
    }

    /**
     * Get value from layout meta data based on given element
     * @param element Layout value is from this element
     * @param clazz Class of {@link MetaData} to use
     * @param getter Getter function to get value from meta data
     * @param <MD> Type of meta data
     * @param <T> Type of return value
     * @return Value in meta data for given element
     */
    protected static <MD extends MetaData,T>T get(JCElement element, Class<MD> clazz, Function<MD,T> getter) {
        return getter.apply(get(element, clazz));
    }

    private static <MD extends MetaData>MD get(JCElement element, Class<MD> clazz) {
        if (!metaDataMap.containsKey(element)) {
            try {
                metaDataMap.put(element, clazz.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        }

        return (MD) metaDataMap.get(element);
    }
    //endregion

    public ObservableList<JCElement> getChildren() {
        return children.get();
    }

    public ReadOnlyListProperty<JCElement> childrenProperty() {
        return children;
    }

    @Override
    protected void repaint() {
        JConsole.VISUAL.setColor(getStyle().getColor());

        for (int i = 0; i <= getHeight(); i++) {
            JConsole.CURSOR.gotoXY(getLeft(), getTop() + i);
            JConsole.print(StringUtils.repeat(" ", getWidth()));
        }
    }

    /**
     * Basic class for Layout Meta Data, see {@link #put(JCElement, Class, Consumer)}. {@link #get(JCElement, Class, Function)}
     */
    protected static abstract class MetaData {

    }
}
