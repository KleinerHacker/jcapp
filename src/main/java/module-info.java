module org.pcsoft.framework.jcapp {
    requires org.pcsoft.framework.jconsole;
    requires javafx.base;
    requires org.pcsoft.jfex.commons;
    requires jnativehook;
    requires commons.lang;
    requires java.logging;
    requires slf4j.api;

    exports org.pcsoft.framework.jcapp;
    exports org.pcsoft.framework.jcapp.base;
    exports org.pcsoft.framework.jcapp.element;
    exports org.pcsoft.framework.jcapp.element.component;
    exports org.pcsoft.framework.jcapp.element.frame;
    exports org.pcsoft.framework.jcapp.element.menu;
    exports org.pcsoft.framework.jcapp.element.pane;
    exports org.pcsoft.framework.jcapp.style;
    exports org.pcsoft.framework.jcapp.style.component;
    exports org.pcsoft.framework.jcapp.style.frame;
    exports org.pcsoft.framework.jcapp.style.menu;
    exports org.pcsoft.framework.jcapp.style.pane;
    exports org.pcsoft.framework.jcapp.style.theme;
    exports org.pcsoft.framework.jcapp.type;
}