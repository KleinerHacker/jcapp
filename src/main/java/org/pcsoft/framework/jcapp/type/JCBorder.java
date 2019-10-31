package org.pcsoft.framework.jcapp.type;

public final class JCBorder {
    public static final JCBorder NONE = new JCBorder(' ', ' ', ' ', ' ', ' ', ' ');
    public static final JCBorder SINGLE = new JCBorder('─', '│', '┌', '┐', '└', '┘');
    public static final JCBorder DOUBLE = new JCBorder('═', '║', '╔', '╗', '╚', '╝');

    private final char horizontal, vertical;
    private final char upperLeft, upperRight, lowerLeft, lowerRight;

    public JCBorder(char horizontal, char vertical, char upperLeft, char upperRight, char lowerLeft, char lowerRight) {
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.upperLeft = upperLeft;
        this.upperRight = upperRight;
        this.lowerLeft = lowerLeft;
        this.lowerRight = lowerRight;
    }

    public char getHorizontal() {
        return horizontal;
    }

    public char getVertical() {
        return vertical;
    }

    public char getUpperLeft() {
        return upperLeft;
    }

    public char getUpperRight() {
        return upperRight;
    }

    public char getLowerLeft() {
        return lowerLeft;
    }

    public char getLowerRight() {
        return lowerRight;
    }
}
