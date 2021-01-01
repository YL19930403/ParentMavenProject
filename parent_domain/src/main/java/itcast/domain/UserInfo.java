package itcast.domain;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {
    private Integer id;

    private String user_name;

    private String email;

    private String password;

    private String phone_no;

    private Integer status;

    private List<RoleInfo> roleInfos;

    private String status_cn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatus_cn() {
        if (status == 0){
            return "未开启";
        } else {
            return "开启";
        }
    }

    public void setStatus_cn(String status_cn) {
        this.status_cn = status_cn;
    }

    public List<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }
}
