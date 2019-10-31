package org.pcsoft.framework.jcapp.type;

import java.util.Objects;

public final class JCPoint {
    public static final JCPoint ZERO = new JCPoint(0, 0);
    public static final JCPoint ONE = new JCPoint(1, 1);

    private final int x, y;

    public JCPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public JCPoint add(JCPoint p) {
        return new JCPoint(x + p.x, y + p.y);
    }

    public JCPoint sub(JCPoint p) {
        return new JCPoint(x - p.x, y - p.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JCPoint jcPoint = (JCPoint) o;
        return x == jcPoint.x &&
                y == jcPoint.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "JCPoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
