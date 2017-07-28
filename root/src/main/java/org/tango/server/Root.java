package org.tango.server;

import org.tango.server.annotation.Attribute;
import org.tango.server.annotation.Device;

/**
 * @author Igor Khokhriakov <igor.khokhriakov@hzg.de>
 * @since 7/28/17
 */
@Device
public class Root {
    @Attribute(isPolled = true, checkChangeEvent = false, pollingPeriod = 100)
    private double dbl;

    public static void main(String[] args) {
        ServerManager.getInstance().start(args, Root.class);
    }

    public double getDbl() {
        return dbl;
    }

    public void setDbl(double dbl) {
        this.dbl = dbl;
    }
}
