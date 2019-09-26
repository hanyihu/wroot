package com.vic.wroot.common.authentication;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vic.wroot.common.authentication.annotation.Authorization;
import com.vic.wroot.common.authentication.annotation.CurrentUser;
import com.vic.wroot.common.authentication.manager.TokenManager;

/**
* 获取和删除token的请求地址，在Restful设计中其实就对应着登录和退出登录的资源映射
*/
//@Controller("tokenTest")
@RequestMapping("/tokens")
public class TestController {

   @Autowired
   private TokenManager tokenManager;

   @RequestMapping(method = RequestMethod.POST)
   public Object login(@RequestParam String username, @RequestParam String password) {

     /*  User user = userRepository.findByUsername(username);
       if (user == null ||  //未注册
               !user.getPassword().equals(password)) {  //密码错误
           //提示用户名或密码错误
           return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
       }*/
       //生成一个token，保存用户登录状态
       TokenModel model = tokenManager.createToken(123);
       return model;
   }

   @RequestMapping(method = RequestMethod.DELETE)
   @Authorization
   public Object logout(@CurrentUser Object user) {
       tokenManager.deleteToken(123);
       return null;
   }

}