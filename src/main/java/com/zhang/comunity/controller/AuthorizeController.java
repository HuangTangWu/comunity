package com.zhang.comunity.controller;

import com.zhang.comunity.dto.AccessTokenDTO;
import com.zhang.comunity.dto.GithubUser;
import com.zhang.comunity.entity.User;
import com.zhang.comunity.mapper.UserMapper;
import com.zhang.comunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 */
@Controller
public class AuthorizeController {
    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.client.redirect_uri}")
    private String redirect_id;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state, HttpSession session){
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_id);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null){
            List<User> userList=userMapper.getUsers();
//            System.out.println(userList);
            User user = new User();
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modify(user.getGmt_create());
            userMapper.addUser(user);
            session.setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            return "/";
        }
    }
}
