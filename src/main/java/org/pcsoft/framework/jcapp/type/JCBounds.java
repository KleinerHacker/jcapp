package org.pcsoft.framework.jcapp.type;

import java.util.Objects;

public final class JCBounds {
    public static JCBounds createWithSize(int left, int top, int width, int height) {
        return new JCBounds(left, top, width, height);
    }

    public static JCBounds createWithSize(JCPoint point, JCSize size) {
        return createWithSize(point.getX(), point.getY(), size.getWidth(), size.getHeight());
    }

    public static JCBounds createWithPoints(int left, int top, int right, int bottom) {
        return new JCBounds(left, top, right - left, bottom - top);
    }

    public static JCBounds createWithPoints(JCPoint leftTop, JCPoint rightBottom) {
        return createWithPoints(leftTop.getX(), leftTop.getY(), rightBottom.getX(), rightBottom.getY());
    }

    public static JCBounds createWithCenter(int centerX, int centerY, int width, int height) {
        return new JCBounds(centerX - width / 2, centerY - height / 2, width, height);
    }

    public static JCBounds createWithCenter(JCPoint center, JCSize size) {
        return createWithCenter(center.getX(), center.getY(), size.getWidth(), size.getHeight());
    }

    private final int left, top;
    private final int width, height;

    private JCBounds(int left, int top, int width, int height) {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public JCPoint getLeftTop() {
        return new JCPoint(getLeft(), getTop());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JCSize getSize() {
        return new JCSize(width, height);
    }

    public int getRight() {
        return left + width;
    }

    public int getBottom() {
        return top + height;
    }

    public JCPoint getRightBottom() {
        return new JCPoint(getRight(), getBottom());
    }

    public int getCenterX() {
        return left + width / 2;
    }

    public int getCenterY() {
        return top + height / 2;
    }

    public JCPoint getCenter() {
        return new JCPoint(getCenterX(), getCenterY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JCBounds jcBounds = (JCBounds) o;
        return left == jcBounds.left &&
                top == jcBounds.top &&
                width == jcBounds.width &&
                height == jcBounds.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, top, width, height);
    }

    @Override
    public String toString() {
        return "JCBounds{" +
                "left=" + left +
                ", top=" + top +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
