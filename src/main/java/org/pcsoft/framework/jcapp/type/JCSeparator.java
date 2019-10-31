package org.pcsoft.framework.jcapp.type;

public final class JCSeparator {
    public static final JCSeparator NONE = new JCSeparator(' ', ' ', ' ');
    public static final JCSeparator SINGLE = new JCSeparator('├', '┤', '─');
    public static final JCSeparator DOUBLE = new JCSeparator('╠', '╣', '═');

    private final char leftCross, rightCross, line;

    public JCSeparator(char leftCross, char rightCross, char line) {
        this.leftCross = leftCross;
        this.rightCross = rightCross;
        this.line = line;
    }

    public char getLeftCross() {
        return leftCross;
    }

    public char getRightCross() {
        return rightCross;
    }

    public char getLine() {
        return line;
    }
}
