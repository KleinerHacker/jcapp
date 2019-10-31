package org.pcsoft.framework.jcapp.style.theme;

import org.pcsoft.framework.jcapp.style.*;
import org.pcsoft.framework.jcapp.style.component.JCCheckBoxStyle;
import org.pcsoft.framework.jcapp.style.component.JCLabelStyle;
import org.pcsoft.framework.jcapp.style.frame.JCDialogStyle;
import org.pcsoft.framework.jcapp.style.frame.JCPopupStyle;
import org.pcsoft.framework.jcapp.style.frame.JCWindowStyle;
import org.pcsoft.framework.jcapp.style.menu.JCMenuStyle;
import org.pcsoft.framework.jcapp.style.pane.JCDesktopStyle;

public final class JCTheme {
    private JCDesktopStyle desktopStyle = new JCDesktopStyle();

    private JCPopupStyle popupStyle = new JCPopupStyle();
    private JCWindowStyle windowStyle = new JCWindowStyle();
    private JCDialogStyle dialogStyle = new JCDialogStyle();

    private JCMenuStyle menuStyle = new JCMenuStyle();
    private JCStatusBarStyle statusBarStyle = new JCStatusBarStyle();

    private JCLabelStyle labelStyle = new JCLabelStyle();
    private JCCheckBoxStyle checkBoxStyle = new JCCheckBoxStyle();

    public JCDesktopStyle getDesktopStyle() {
        return desktopStyle;
    }

    public void setDesktopStyle(JCDesktopStyle desktopStyle) {
        this.desktopStyle = desktopStyle;
    }

    public JCPopupStyle getPopupStyle() {
        return popupStyle;
    }

    public void setPopupStyle(JCPopupStyle popupStyle) {
        this.popupStyle = popupStyle;
    }

    public JCWindowStyle getWindowStyle() {
        return windowStyle;
    }

    public void setWindowStyle(JCWindowStyle windowStyle) {
        this.windowStyle = windowStyle;
    }

    public JCDialogStyle getDialogStyle() {
        return dialogStyle;
    }

    public void setDialogStyle(JCDialogStyle dialogStyle) {
        this.dialogStyle = dialogStyle;
    }

    public JCMenuStyle getMenuStyle() {
        return menuStyle;
    }

    public void setMenuStyle(JCMenuStyle menuStyle) {
        this.menuStyle = menuStyle;
    }

    public JCStatusBarStyle getStatusBarStyle() {
        return statusBarStyle;
    }

    public void setStatusBarStyle(JCStatusBarStyle statusBarStyle) {
        this.statusBarStyle = statusBarStyle;
    }

    public JCLabelStyle getLabelStyle() {
        return labelStyle;
    }

    public void setLabelStyle(JCLabelStyle labelStyle) {
        this.labelStyle = labelStyle;
    }

    public JCCheckBoxStyle getCheckBoxStyle() {
        return checkBoxStyle;
    }

    public void setCheckBoxStyle(JCCheckBoxStyle checkBoxStyle) {
        this.checkBoxStyle = checkBoxStyle;
    }
}
