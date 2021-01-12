package cn.itcast.dao;

import itcast.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface ISysLogDao {

    @Insert("INSERT INTO `sys_log`(visit_time, user_name, ip, url, execution_time, method) VALUES(#{visit_time}, #{user_name}, #{ip}, #{url}, #{execution_time}, #{method})")
    public abstract void save(SysLog sysLog) throws Exception;
}
