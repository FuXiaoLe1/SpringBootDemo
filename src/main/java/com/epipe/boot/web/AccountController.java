package com.epipe.boot.web;

//import com.alibaba.fastjson.JSONObject;

import com.epipe.boot.entity.Account;
import com.epipe.boot.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
/**
 * 用户创建某账户信息	POST	/accounts/
 * 用户修改某账户信息	PUT	/accounts
 * 用户删除某账户信息	DELETE	/accounts/:id/
 * 用户获取所有的某账户信息 GET /accounts
 *  用户获取某一账户信息  GET /accounts/:id
 * Created by fangzhipeng on 2017/4/17.
 * 官方文档：http://swagger.io/docs/specification/api-host-and-base-path/
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiOperation(value="获取银行账户列表", notes="获取银行账户列表")
    @RequestMapping(value={""}, method= RequestMethod.GET)
    public List<Account> getAccount() {
        List<Account> book = accountService.findAccountList();
        return book;
    }

    @ApiOperation(value="创建账户", notes="创建账户")
    @ApiImplicitParam(name = "account", value = "账户详细实体", required = true, dataType = "Account")
    @RequestMapping(value="", method=RequestMethod.POST)
    public String postAccount(@RequestBody Account account) {
        accountService.add(account);
        return "success";
    }
    @ApiOperation(value="获账户细信息", notes="根据url的id来获取详细信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Account getAccount(@PathVariable Integer id) {
        return accountService.findAccount(id);
    }

    @ApiOperation(value="更新信息", notes="根据url的id来指定更新账户信息")
    @ApiImplicitParam(name = "account", value = "账户详细实体", required = true, dataType = "Account")
    @RequestMapping(value="", method= RequestMethod.PUT)
    public String putAccount(@RequestBody Account account) {
        accountService.update(account);
        return "success";
    }
    @ApiOperation(value="删除图书", notes="根据url的id来指定删除账户")
    @ApiImplicitParam(name = "id", value = "账户ID", required = true, dataType = "Integer",paramType = "path")
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteAccount(@PathVariable Integer id) {
        accountService.delete(id);
        return "success";
    }

    @ApiOperation(value="调用远程方法测试", notes="调用远程方法测试")
    @ApiImplicitParam(name = "account", value = "账户详细实体", required = true, dataType = "Account")
    @RequestMapping(value="/remote", method=RequestMethod.POST)
    public Object remoteAccount(@RequestBody Account account) {
        Object result = accountService.callRemote(account);
        return result;
    }

    @ApiIgnore//使用该注解忽略这个API 测试是否项目正常启动
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }

}
