package com.springmvc.service;

import com.springmvc.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loginSql(User user);
}
