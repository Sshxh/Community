package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //根据用户的id找到User类型的数据
    User selectById(int id);

    //根据用户的姓名找到User类型的数据
    User selectByName(String username);

    //根据用户的email找到用户数据
    User selectByEmail(String email);

    int insertUser(User user);


    //这个是当你成功被注册的时候 就会设置你这个用户id的status 来表示这个User是否是一个可以使用的状态
    int updateStatus(int id, int status);

    int updateHeader(int id, String headerUrl);

    int updatePassword(int id, String password);

}
