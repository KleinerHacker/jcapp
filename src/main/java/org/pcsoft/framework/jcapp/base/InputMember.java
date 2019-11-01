package org.pcsoft.framework.jcapp.base;

import org.jnativehook.keyboard.NativeKeyEvent;

public interface InputMember {
    boolean fireKey(NativeKeyEvent e);
}
