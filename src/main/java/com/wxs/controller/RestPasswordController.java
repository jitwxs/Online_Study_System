package com.wxs.controller;

import com.wxs.controller.common.SHA1Utils;
import com.wxs.exception.CustomException;
import com.wxs.po.Userlogin;
import com.wxs.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 修改密码Controller层
 */

@Controller
public class RestPasswordController {

    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    /**
     * 重置当前账户密码
     * @param oldPassword
     * @param password1
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/passwordRest", method = {RequestMethod.POST})
    public String passwordRest(String oldPassword, String password1) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        Userlogin userlogin = userloginService.findByName(username);

        if (!SHA1Utils.validatePassword(oldPassword,userlogin.getPassword())) {
            throw new CustomException("旧密码不正确");
        } else {
            userlogin.setPassword(SHA1Utils.entryptPassword(password1));
            userloginService.updateByName(username, userlogin);
        }

        return "redirect:/logout";
    }

}
