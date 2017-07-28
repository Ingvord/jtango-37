package org.tango.client;

import fr.esrf.Tango.DevFailed;
import fr.esrf.TangoApi.CallBack;
import fr.esrf.TangoApi.DeviceProxy;
import fr.esrf.TangoApi.events.EventData;
import fr.esrf.TangoDs.TangoConst;
import org.tango.utils.DevFailedUtils;

/**
 * @author Igor Khokhriakov <igor.khokhriakov@hzg.de>
 * @since 7/28/17
 */
public class Client {
    public static void main(String[] args) throws Exception {
        DeviceProxy root = new DeviceProxy("tango://hzgxenvtest:10000/development/root/0");
        DeviceProxy fwd = new DeviceProxy("tango://hzgxenvtest:10000/development/fwd/0");

        root.subscribe_event("dbl", TangoConst.CHANGE_EVENT, new CallBack() {
            @Override
            public void push_event(EventData evt) {
                try {
                    System.out.print("root/");
                    System.out.print(evt.attr_value.getName());
                    System.out.print(" = ");
                    System.out.println(evt.attr_value.extractDouble());
                } catch (DevFailed devFailed) {
                    DevFailedUtils.printDevFailed(devFailed);
                }
            }
        }, new String[0]);

        fwd.subscribe_event("fwd-attr", TangoConst.CHANGE_EVENT, new CallBack() {
            @Override
            public void push_event(EventData evt) {
                try {
                    System.out.println(evt.attr_value.getName());
                } catch (DevFailed devFailed) {
                    DevFailedUtils.printDevFailed(devFailed);
                }
            }
        }, new String[0]);

        while (true) ;
    }
}
