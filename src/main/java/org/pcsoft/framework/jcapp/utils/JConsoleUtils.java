package org.pcsoft.framework.jcapp.utils;

import org.apache.commons.lang.StringUtils;
import org.pcsoft.framework.jconsole.JConsole;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public final class JConsoleUtils {
    public static void printAccentString(String s, Character mnemonic, JConsoleColorPair color) {
        if (mnemonic != null) {
            final String text = StringUtils.replaceOnce(s, mnemonic + "", "<<" + mnemonic + ">>");
            JConsole.printAccent(text, color);
        } else {
            JConsole.print(s);
        }
    }

    private JConsoleUtils() {
    }
}
