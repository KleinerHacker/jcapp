package org.pcsoft.framework.jcapp.type;

import java.util.Objects;

public final class JCSize {
    public static JCSize ZERO = new JCSize(0, 0);
    public static JCSize ONE = new JCSize(1, 1);

    private final int width, height;

    public JCSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JCSize add(JCSize size) {
        return new JCSize(width + size.width, height + size.height);
    }

    public JCSize sub(JCSize size) {
        return new JCSize(width - size.width, height - size.height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JCSize jcSize = (JCSize) o;
        return width == jcSize.width &&
                height == jcSize.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "JCSize{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
