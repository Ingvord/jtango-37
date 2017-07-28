package org.tango.server;

import fr.esrf.Tango.DevFailed;
import org.tango.server.annotation.Device;
import org.tango.server.annotation.DeviceManagement;
import org.tango.server.annotation.DynamicManagement;
import org.tango.server.annotation.Init;
import org.tango.server.attribute.ForwardedAttribute;
import org.tango.server.device.DeviceManager;
import org.tango.server.dynamic.DynamicManager;

/**
 * @author Igor Khokhriakov <igor.khokhriakov@hzg.de>
 * @since 7/28/17
 */
@Device
public class Fwd {

    @DeviceManagement
    private DeviceManager deviceManager;
    @DynamicManagement
    private DynamicManager dynamicManager;

    public static void main(String[] args) {
        ServerManager.getInstance().start(args, Fwd.class);
    }

    public void setDeviceManager(DeviceManager deviceManager) {
        this.deviceManager = deviceManager;
    }

    public void setDynamicManager(DynamicManager dynamicManager) {
        this.dynamicManager = dynamicManager;
    }

    @Init
    public void init() throws DevFailed {
        dynamicManager.addAttribute(new ForwardedAttribute("tango://hzgxenvtest:10000/development/root/0/dbl", "fwd-attr", ""));
    }
}
