package ru.job4j.quoters;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 01.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class ProfilingController implements ProfilingControllerMBean{

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
