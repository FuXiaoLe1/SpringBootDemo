package com.epipe.boot.service;

import com.epipe.boot.dao.AccountMapper;
import com.epipe.boot.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RestTemplate restTemplate;

    public int add(Account account) {
        return accountMapper.insertSelective(account);
    }

    public int update(Account account) {
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    public int delete(Integer id) {
        return accountMapper.deleteByPrimaryKey(id);
    }

    public Account findAccount(Integer id) {
        return accountMapper.selectByPrimaryKey(id);
    }

    public List findAccountList() {
        return accountMapper.selectAll();
    }

    public Object  callRemote(Account account){
        try{
            ResponseEntity<String> returnObject = restTemplate.postForEntity("http://localhost:8080/accounts",account, String.class);
            return returnObject;
        }catch (Exception e){
            e.printStackTrace();
            return "调用远程方法失败";
        }
    }

}
