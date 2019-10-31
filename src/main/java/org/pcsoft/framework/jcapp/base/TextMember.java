package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.StringProperty;

public interface TextMember {
    void setText(String text);
    StringProperty textProperty();
    String getText();
}
