package com.zhang.comunity.controller;

import com.zhang.comunity.dto.AccessTokenDTO;
import com.zhang.comunity.dto.GithubUser;
import com.zhang.comunity.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO =new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_id);
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(client_secret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        return "index";
    }
}
