package com.zhang.comunity.provider;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.zhang.comunity.dto.AccessTokenDTO;
import com.zhang.comunity.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Timeout;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhang Zeming
 * @version 1.0
 * @date 2019/12/13 15:16
 */
@Slf4j
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO dto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();;
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
            log.info("获取的token_id-------------》"+token_id);
            return token_id;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();

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
                log.error("github对象获取失败-----------》"+e.getMessage()+"****");
            }
            return null;
    }
}
