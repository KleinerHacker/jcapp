package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.BooleanProperty;

public interface SelectableMember {
    void setSelected(boolean selected);
    BooleanProperty selectedProperty();
    boolean isSelected();
}
