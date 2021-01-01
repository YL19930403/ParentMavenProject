package cn.itcast.monitor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MethodMonitorEventPublisher {

    private List<MethodMonitorEventListener> listeners = new ArrayList<>();

    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent eventObject = new MethodMonitorEvent(this);
        publishEvent("begin", eventObject);
        //模拟方法执行，休眠5秒
        TimeUnit.SECONDS.sleep(5);
        publishEvent("end", eventObject);
    }

    private void publishEvent(String status, MethodMonitorEvent event){
        // 避免在事件处理期间，监听器被移除，这里为了安全做个复制操作
        List<MethodMonitorEventListener> listeners = new ArrayList<>(this.listeners);

        for (MethodMonitorEventListener listener : listeners){
            if ("begin".equals(status)){
                listener.onMethodBegin(event);
            }else {
                listener.onMethodEnd(event);
            }
        }
    }

    public void addEventListener(MethodMonitorEventListener listener){}

    public void removeEventListener(MethodMonitorEventListener listener){}

    public void removeAllListeners(){

    }
}
