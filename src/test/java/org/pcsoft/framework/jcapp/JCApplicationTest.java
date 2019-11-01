package org.pcsoft.framework.jcapp;

import org.pcsoft.framework.jcapp.element.JCStatusBar;
import org.pcsoft.framework.jcapp.element.component.JCCheckBox;
import org.pcsoft.framework.jcapp.element.component.JCLabel;
import org.pcsoft.framework.jcapp.element.component.JCRadioButton;
import org.pcsoft.framework.jcapp.element.frame.JCDialog;
import org.pcsoft.framework.jcapp.element.menu.JCMenu;
import org.pcsoft.framework.jcapp.element.menu.JCMenuBar;
import org.pcsoft.framework.jcapp.element.menu.JCMenuItem;
import org.pcsoft.framework.jcapp.element.menu.JCMenuSeparator;
import org.pcsoft.framework.jcapp.element.pane.JCAnchorPane;
import org.pcsoft.framework.jcapp.element.pane.JCDesktop;
import org.pcsoft.framework.jcapp.style.theme.JCThemes;

public class JCApplicationTest extends JCApplication {

    public static void main(String[] args) {
        launch(JCApplicationTest.class, args);
    }

    @Override
    protected void start() {
        updateTheme(JCThemes.TURBO_PASCAL);

        final JCMenuBar menuBar = new JCMenuBar(
                new JCMenu(
                        "File", 'F',
                        new JCMenuItem("New", 'N'),
                        new JCMenuItem("Open...", 'O'),
                        new JCMenuSeparator(),
                        new JCMenuItem("Save", 'S'),
                        new JCMenuItem("Save as...", 'a'),
                        new JCMenuSeparator(),
                        new JCMenuItem("Exit", 'E')
                ),
                new JCMenu(
                        "Help", 'H',
                        new JCMenuItem("Theme", 'T'),
                        new JCMenuItem("About...", 'A')
                )
        );
        getDesktop().setMenuBar(menuBar);

        final JCStatusBar statusBar = new JCStatusBar();
        getDesktop().setStatusBar(statusBar);

        final JCDialog dialog = buildDialog();
        JCDesktop.setLeft(dialog, 10);
        JCDesktop.setTop(dialog, 5);
        getDesktop().getChildren().add(dialog);
    }

    private JCDialog buildDialog() {
        final JCDialog dialog = new JCDialog("Demo Elements", 50, 10);
        final JCAnchorPane pane = new JCAnchorPane();

        final JCLabel label = new JCLabel("A Label", 'b');
        JCAnchorPane.setLeft(label, 1);
        JCAnchorPane.setTop(label, 1);
        pane.getChildren().add(label);

        final JCCheckBox checkBox = new JCCheckBox("A Check Box", 'o');
        JCAnchorPane.setLeft(checkBox, 1);
        JCAnchorPane.setTop(checkBox, 2);
        pane.getChildren().add(checkBox);

        final JCRadioButton radioButton = new JCRadioButton("A Radio Button", 'R');
        JCAnchorPane.setLeft(radioButton, 1);
        JCAnchorPane.setTop(radioButton, 3);
        pane.getChildren().add(radioButton);

        dialog.setContent(pane);
        dialog.setVisible(true);
        return dialog;
    }
}