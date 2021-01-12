package cn.itcast.service;


import itcast.domain.SysLog;

public interface ISysLogService {
    public abstract void save(SysLog sysLog) throws Exception;
}
