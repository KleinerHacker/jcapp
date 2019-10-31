package org.pcsoft.framework.jcapp.base;

import javafx.beans.property.ReadOnlyBooleanProperty;

/**
 * Interface for member to invalidate
 */
public interface InvalidationMember {
    /**
     * Is current element dirty?
     * @return
     */
    boolean isDirty();

    /**
     * Property for {@link #isDirty()}
     * @return
     */
    ReadOnlyBooleanProperty dirtyProperty();

    /**
     * Force refreshing element (make {@link #isDirty()} <code>true</code>)
     */
    void refresh();

    /**
     * Is current element invalid? (new measure expected)
     * @return
     */
    boolean isInvalid();

    /**
     * Property for {@link #isInvalid()}
     * @return
     */
    ReadOnlyBooleanProperty invalidProperty();

    /**
     * Force invalidation of element (set {@link #isInvalid()} to <code>true</code>, force {@link #refresh()}, too)
     */
    void invalidate();
}
