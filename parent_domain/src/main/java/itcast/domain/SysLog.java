package itcast.domain;


import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {

    private Integer id;

    private Date visit_time;

    private String user_name;

    private String ip;

    private String url;

    private Date execution_time;

    private String method;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getExecution_time() {
        return execution_time;
    }

    public void setExecution_time(Date execution_time) {
        this.execution_time = execution_time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
