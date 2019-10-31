package org.pcsoft.framework.jcapp.style.theme;

import org.pcsoft.framework.jcapp.type.JCBorder;
import org.pcsoft.framework.jcapp.type.JCSelector;
import org.pcsoft.framework.jcapp.type.JCSeparator;
import org.pcsoft.framework.jconsole.type.JConsoleColor;
import org.pcsoft.framework.jconsole.type.JConsoleColorPair;

public final class JCThemes {
    public static final JCTheme TURBO_PASCAL = new JCTheme();

    static {
        buildTurboPascalTheme();
    }

    private static void buildTurboPascalTheme() {
        TURBO_PASCAL.getDesktopStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Blue),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getDesktopStyle().setBackgroundCharacter('░');

        TURBO_PASCAL.getMenuStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getMenuStyle().setMnemonicColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Red),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getMenuStyle().setSelectionColor(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Blue)
                )
        );
        TURBO_PASCAL.getMenuStyle().setPopupBorder(JCBorder.SINGLE);
        TURBO_PASCAL.getMenuStyle().setPopupSeparator(JCSeparator.SINGLE);

        TURBO_PASCAL.getPopupStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getPopupStyle().setBorder(JCBorder.SINGLE);

        TURBO_PASCAL.getWindowStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Blue)
                )
        );
        TURBO_PASCAL.getWindowStyle().setTitleColor(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Yellow)
                )
        );
        TURBO_PASCAL.getWindowStyle().setBorder(JCBorder.DOUBLE);

        TURBO_PASCAL.getDialogStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getDialogStyle().setTitleColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getDialogStyle().setBorder(JCBorder.DOUBLE);

        TURBO_PASCAL.getStatusBarStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );

        TURBO_PASCAL.getLabelStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getLabelStyle().setMnemonicColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Red),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getLabelStyle().setFocusColor(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getLabelStyle().setDisableColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );

        TURBO_PASCAL.getCheckBoxStyle().setColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getCheckBoxStyle().setMnemonicColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Red),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getCheckBoxStyle().setFocusColor(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getCheckBoxStyle().setDisableColor(
                new JConsoleColorPair(
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Black),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.White)
                )
        );
        TURBO_PASCAL.getCheckBoxStyle().setSelectorColor(
                new JConsoleColorPair(
                        JConsoleColor.createHighlighted(JConsoleColor.ColorValue.White),
                        JConsoleColor.createDefault(JConsoleColor.ColorValue.Green)
                )
        );
        TURBO_PASCAL.getCheckBoxStyle().setSelector(JCSelector.CORNER);
    }

    private JCThemes() {
    }
}
