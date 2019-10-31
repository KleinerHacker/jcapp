package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.BooleanProperty;

public interface DisableMember {
    void setDisabled(boolean disable);
    BooleanProperty disabledProperty();
    boolean isDisabled();
}
