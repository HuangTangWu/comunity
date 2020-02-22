package com.zhang.comunity.provider;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.zhang.comunity.dto.AccessTokenDTO;
import com.zhang.comunity.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO dto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String jsonString = JSON.toJSONString(dto);
        RequestBody body = RequestBody.create(jsonString,mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String token_id=string.split("&")[0].split("=")[1];
            return token_id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.github.com/user")
                    .header("Authorization","token "+accessToken)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String jsonMsg= response.body().string();
                GithubUser githubUser= JSON.parseObject(jsonMsg,GithubUser.class);
                if(0==githubUser.getId()&&githubUser.getName()==null){
                    return null;
                }
                return  githubUser;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
}
