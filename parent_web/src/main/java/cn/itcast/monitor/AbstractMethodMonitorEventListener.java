package cn.itcast.monitor;


public class AbstractMethodMonitorEventListener implements MethodMonitorEventListener{

    @Override
    public void onMethodBegin(MethodMonitorEvent event) {
        event.timeStamp = System.currentTimeMillis();
    }

    @Override
    public void onMethodEnd(MethodMonitorEvent event) {
        long duration = System.currentTimeMillis() - event.timeStamp;
        System.out.println("耗时"+duration);
    }
}
