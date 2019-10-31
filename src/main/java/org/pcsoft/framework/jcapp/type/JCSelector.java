package org.pcsoft.framework.jcapp.type;

public final class JCSelector {
    public static final JCSelector CORNER = new JCSelector('[', ']', 'X', ' ');
    public static final JCSelector ROUND = new JCSelector('(', ')', '■', ' ');

    private final char left, right;
    private final char active, inactive;

    public JCSelector(char left, char right, char active, char inactive) {
        this.left = left;
        this.right = right;
        this.active = active;
        this.inactive = inactive;
    }

    public char getLeft() {
        return left;
    }

    public char getRight() {
        return right;
    }

    public char getActive() {
        return active;
    }

    public char getInactive() {
        return inactive;
    }
}
