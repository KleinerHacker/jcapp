package org.pcsoft.framework.jcapp.element;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import org.apache.commons.lang.NotImplementedException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.base.InvalidationMember;
import org.pcsoft.framework.jcapp.style.JCStyle;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.*;

public abstract class JCElement<S extends JCStyle> implements InvalidationMember {
    private final ReadOnlyIntegerWrapper leftWrapper = new ReadOnlyIntegerWrapper(), topWrapper = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerWrapper widthWrapper = new ReadOnlyIntegerWrapper(), heightWrapper = new ReadOnlyIntegerWrapper();
    private final ReadOnlyIntegerProperty left = leftWrapper.getReadOnlyProperty(), top = topWrapper.getReadOnlyProperty();
    private final ReadOnlyIntegerProperty width = widthWrapper.getReadOnlyProperty(), height = heightWrapper.getReadOnlyProperty();

    private final IntegerProperty prefWidth = new SimpleIntegerProperty(Integer.MAX_VALUE), prefHeight = new SimpleIntegerProperty(Integer.MAX_VALUE);
    private final IntegerProperty minWidth = new SimpleIntegerProperty(1), minHeight = new SimpleIntegerProperty(1);
    private final IntegerProperty maxWidth = new SimpleIntegerProperty(Integer.MAX_VALUE), maxHeight = new SimpleIntegerProperty(Integer.MAX_VALUE);
    private final ObjectProperty<JCHorizontalAlignment> horizontalAlignment = new SimpleObjectProperty<>(JCHorizontalAlignment.Stretch);
    private final ObjectProperty<JCVerticalAlignment> verticalAlignment = new SimpleObjectProperty<>(JCVerticalAlignment.Stretch);
    private final ObjectBinding<JCBounds> bounds;

    private final BooleanProperty visible = new SimpleBooleanProperty(true);

    private final ObjectProperty<S> style = new SimpleObjectProperty<>();
    private final ObjectProperty<JCTheme> theme = new SimpleObjectProperty<>();

    private final ReadOnlyObjectWrapper<JCContainer> parentWrapper = new ReadOnlyObjectWrapper<>();
    private final ReadOnlyObjectProperty<JCContainer> parent = parentWrapper.getReadOnlyProperty();

    private final ReadOnlyBooleanWrapper dirtyWrapper = new ReadOnlyBooleanWrapper(true);
    private final ReadOnlyBooleanProperty dirty = dirtyWrapper.getReadOnlyProperty();
    private final ReadOnlyBooleanWrapper invalidWrapper = new ReadOnlyBooleanWrapper(true);
    private final ReadOnlyBooleanProperty invalid = invalidWrapper.getReadOnlyProperty();

    public JCElement() {
        minWidth.addListener(o -> invalidate());
        minHeight.addListener(o -> invalidate());
        prefWidth.addListener(o -> invalidate());
        prefHeight.addListener(o -> invalidate());
        maxWidth.addListener(o -> invalidate());
        maxHeight.addListener(o -> invalidate());
        horizontalAlignment.addListener(o -> invalidate());
        verticalAlignment.addListener(o -> invalidate());
        bounds = Bindings.createObjectBinding(
                () -> JCBounds.createWithSize(getLeft(), getTop(), getWidth(), getHeight()),
                left, top, width, height
        );

        visible.addListener(o -> invalidate());

        invalid.addListener(o -> refresh());

        parent.addListener(o -> invalidate());

        theme.addListener((v, o, n) -> {
            if (n != null) {
                setStyle(extractStyleFromTheme(n));
            } else {
                setStyle(getDefaultStyle());
            }
        });
        style.addListener((v, o, n) -> {
            if (n == null)
                throw new IllegalArgumentException("Style cannot be NULL");

            refresh();
        });
        setStyle(getDefaultStyle());
    }

    public int getLeft() {
        return left.get();
    }

    public ReadOnlyIntegerProperty leftProperty() {
        return left;
    }

    protected void setLeft(int left) {
        leftWrapper.set(left);
    }

    public int getTop() {
        return top.get();
    }

    public ReadOnlyIntegerProperty topProperty() {
        return top;
    }

    protected void setTop(int top) {
        topWrapper.set(top);
    }

    public int getWidth() {
        return width.get();
    }

    public ReadOnlyIntegerProperty widthProperty() {
        return width;
    }

    protected void setWidth(int width) {
        widthWrapper.set(width);
    }

    public int getHeight() {
        return height.get();
    }

    public ReadOnlyIntegerProperty heightProperty() {
        return height;
    }

    protected void setHeight(int height) {
        heightWrapper.set(height);
    }

    public int getPrefWidth() {
        return prefWidth.get();
    }

    public IntegerProperty prefWidthProperty() {
        return prefWidth;
    }

    public void setPrefWidth(int prefWidth) {
        this.prefWidth.set(prefWidth);
    }

    public int getPrefHeight() {
        return prefHeight.get();
    }

    public IntegerProperty prefHeightProperty() {
        return prefHeight;
    }

    public void setPrefHeight(int prefHeight) {
        this.prefHeight.set(prefHeight);
    }

    public int getMinWidth() {
        return minWidth.get();
    }

    public IntegerProperty minWidthProperty() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth.set(minWidth);
    }

    public int getMinHeight() {
        return minHeight.get();
    }

    public IntegerProperty minHeightProperty() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight.set(minHeight);
    }

    public int getMaxWidth() {
        return maxWidth.get();
    }

    public IntegerProperty maxWidthProperty() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth.set(maxWidth);
    }

    public int getMaxHeight() {
        return maxHeight.get();
    }

    public IntegerProperty maxHeightProperty() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight.set(maxHeight);
    }

    public JCHorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment.get();
    }

    public ObjectProperty<JCHorizontalAlignment> horizontalAlignmentProperty() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(JCHorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment.set(horizontalAlignment);
    }

    public JCVerticalAlignment getVerticalAlignment() {
        return verticalAlignment.get();
    }

    public ObjectProperty<JCVerticalAlignment> verticalAlignmentProperty() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(JCVerticalAlignment verticalAlignment) {
        this.verticalAlignment.set(verticalAlignment);
    }

    public JCBounds getBounds() {
        return bounds.get();
    }

    public ObjectBinding<JCBounds> boundsProperty() {
        return bounds;
    }

    public boolean isVisible() {
        return visible.get();
    }

    public BooleanProperty visibleProperty() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible.set(visible);
    }

    public S getStyle() {
        return style.get();
    }

    public ObjectProperty<S> styleProperty() {
        return style;
    }

    public void setStyle(S style) {
        this.style.set(style);
    }

    public JCTheme getTheme() {
        return theme.get();
    }

    public ObjectProperty<JCTheme> themeProperty() {
        return theme;
    }

    public void setTheme(JCTheme theme) {
        this.theme.set(theme);
    }

    public JCContainer getParent() {
        return parent.get();
    }

    public ReadOnlyObjectProperty<JCContainer> parentProperty() {
        return parent;
    }

    void setParent(JCContainer container) {
        parentWrapper.set(container);
    }

    @Override
    public boolean isDirty() {
        return dirty.get();
    }

    @Override
    public ReadOnlyBooleanProperty dirtyProperty() {
        return dirty;
    }

    @Override
    public void refresh() {
        dirtyWrapper.set(true);
    }

    @Override
    public boolean isInvalid() {
        return invalid.get();
    }

    @Override
    public ReadOnlyBooleanProperty invalidProperty() {
        return invalid;
    }

    @Override
    public void invalidate() {
        invalidWrapper.set(true);
    }

    //region Paint (Dirty)
    public void paint() {
        if (!isDirty() || !isVisible())
            return;

        repaint();
        dirtyWrapper.set(false);
    }

    protected abstract void repaint();
    //endregion

    //region Measure (Invalid)
    public void measure(JCBounds bounds) {
        if (!isInvalid() || !isVisible())
            return;

        remeasure(bounds);
        invalidWrapper.set(false);
    }

    /**
     * Measure the element, see {@link #leftProperty()}, {@link #topProperty()}, {@link #widthProperty()}, {@link #heightProperty()}
     * @param bounds Bounds of area to fill with this element
     * @return The new bounds of this element
     */
    protected void remeasure(JCBounds bounds) {
        final JCSize size = calculateSize(bounds);
        final JCPoint point = calculatePoint(bounds, size);

        setLeft(point.getX());
        setTop(point.getY());
        setWidth(size.getWidth());
        setHeight(size.getHeight());
    }

    protected JCPoint calculatePoint(JCBounds bounds, JCSize size) {
        final int left;
        switch (getHorizontalAlignment()) {
            case Stretch:
            case Left:
                left = bounds.getLeft();
                break;
            case Center:
                left = bounds.getCenterX() - size.getWidth() / 2;
                break;
            case Right:
                left = bounds.getRight() - size.getWidth();
                break;
            default:
                throw new NotImplementedException();
        }

        final int top;
        switch (getVerticalAlignment()) {
            case Stretch:
            case Top:
                top = bounds.getTop();
                break;
            case Center:
                top = bounds.getCenterY() - size.getHeight() / 2;
                break;
            case Bottom:
                top = bounds.getBottom() - size.getHeight();
                break;
            default:
                throw new NotImplementedException();
        }

        return new JCPoint(left, top);
    }

    protected JCSize calculateSize(JCBounds bounds) {
        final int width;
        switch (getHorizontalAlignment()) {
            case Stretch:
                width = Math.min(getMaxWidth(), bounds.getWidth());
                break;
            case Left:
            case Center:
            case Right:
                width = Math.max(getMinWidth(), Math.min(getPrefWidth(), bounds.getWidth()));
                break;
            default:
                throw new NotImplementedException();
        }

        final int height;
        switch (getVerticalAlignment()) {
            case Stretch:
                height = Math.min(getMaxHeight(), bounds.getHeight());
                break;
            case Top:
            case Center:
            case Bottom:
                height = Math.max(getMinHeight(), Math.min(getPrefHeight(), bounds.getHeight()));
                break;
            default:
                throw new NotImplementedException();
        }

        return new JCSize(width, height);
    }
    //endregion

    //region Style
    protected abstract S extractStyleFromTheme(JCTheme theme);
    protected abstract S getDefaultStyle();
    //endregion

    //region Input
    public final boolean fireKey(NativeKeyEvent nativeKeyEvent)  {
        return onKey(nativeKeyEvent);
    }
    protected abstract boolean onKey(NativeKeyEvent nativeKeyEvent);
    //endregion
}
