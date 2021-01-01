package cn.itcast.dao;


import itcast.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberDao {

    @Select("SELECT * FROM member WHERE id=#{id}")
    public abstract Member findById(Integer id) throws Exception;
}
