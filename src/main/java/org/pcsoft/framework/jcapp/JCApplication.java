package org.pcsoft.framework.jcapp;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.pcsoft.framework.jcapp.element.pane.JCDesktop;
import org.pcsoft.framework.jcapp.style.theme.JCTheme;
import org.pcsoft.framework.jcapp.type.JCBounds;
import org.pcsoft.framework.jconsole.JConsole;
import org.pcsoft.framework.jconsole.type.JConsoleInputMode;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class JCApplication {
    //region Static Area
    private static final ExecutorService executorService;
    private static JCApplication currentApplication;

    static {
        executorService = Executors.newSingleThreadExecutor(r -> {
            final Thread thread = new Thread(r);
            thread.setName("JCApp UI Thread");
            thread.setDaemon(true);

            return thread;
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (currentApplication != null) {
                currentApplication.exit();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    protected static void launch(Class<? extends JCApplication> clazz, String[] args) {
        if (currentApplication != null)
            throw new IllegalStateException("JCApp already started");

        try {
            currentApplication = clazz.getConstructor().newInstance();
            currentApplication.isRunning.set(true);

            JConsole.setInputMode(JConsoleInputMode.Direct);
            JConsole.CURSOR.setCursorVisible(false);
            JConsole.clearScreen();
            JConsole.CURSOR.goHome();

            try {
                final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                logger.setLevel(Level.WARNING);
                logger.setUseParentHandlers(false);

                GlobalScreen.registerNativeHook();
            } catch (NativeHookException e) {
                throw new IllegalStateException("Unable to register hook", e);
            }
            GlobalScreen.addNativeKeyListener(new NativeKeyAdapter() {
                @Override
                public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
                    currentApplication.onKey(nativeKeyEvent);
                }
            });

            executorService.submit(() -> {
                try {
                    currentApplication.start();

                    while (currentApplication.isRunning.get()) {
                        JConsole.startBackBuffering();
                        try {
                            currentApplication.paint();
                        } finally {
                            JConsole.endBackBuffering();
                        }

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    currentApplication.stop();
                    currentApplication = null;

                    JConsole.VISUAL.resetColor();
                    JConsole.clearScreen();
                    JConsole.CURSOR.setCursorVisible(true);
                    JConsole.CURSOR.goHome();
                    JConsole.resetInputMode();

                    if (GlobalScreen.isNativeHookRegistered()) {
                        try {
                            GlobalScreen.unregisterNativeHook();
                        } catch (NativeHookException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }).get();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException("Unable to startup JCApp", e);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        if (currentApplication == null)
            throw new IllegalStateException("No JCApp started");

        currentApplication.exit();
    }

    public static void setTheme(JCTheme theme) {
        if (currentApplication == null)
            throw new IllegalStateException("No JCApp started");

        currentApplication.updateTheme(theme);
    }
    //endregion

    private final JCDesktop desktop = new JCDesktop();
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    protected JCApplication() {
    }

    protected abstract void start();

    protected void stop() {
    }

    public final void exit() {
        isRunning.set(false);
    }

    public final JCDesktop getDesktop() {
        return desktop;
    }

    public final void updateTheme(JCTheme theme) {
        desktop.setTheme(theme);
    }

    private void paint() {
        desktop.measure(JCBounds.createWithSize(1, 1, 80, 25));
        desktop.paint();
    }

    private void onKey(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_X && (nativeKeyEvent.getModifiers() & NativeKeyEvent.ALT_MASK) != 0) {
            exit();
            return;
        }

        desktop.fireKey(nativeKeyEvent);
    }
}
