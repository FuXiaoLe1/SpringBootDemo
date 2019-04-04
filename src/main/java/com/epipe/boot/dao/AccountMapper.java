package com.epipe.boot.dao;

import com.epipe.boot.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    List<Account> selectAll();
}