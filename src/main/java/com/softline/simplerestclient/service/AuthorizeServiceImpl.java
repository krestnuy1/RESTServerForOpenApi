package com.softline.simplerestclient.service;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class AuthorizeServiceImpl implements AuthorizeService {
    @Override
    public String getToken() {
        try {
            String url = "put your URL here";
            String clientId = "put your id here";
            String clientSecret = "put your secret for api here";
            String scope = "TemplateTestApiScope";

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            // Устанавливаем заголовки
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes()));
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

            // Устанавливаем параметры запроса
            StringEntity params = new StringEntity("grant_type=client_credentials&scope=" + scope);
            httpPost.setEntity(params);

            // Отправляем запрос
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // Обрабатываем ответ
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(responseBody);
            String accessToken = jsonObject.getString("access_token");
            System.out.println(accessToken);
            // Закрываем ресурсы
            response.close();
            httpClient.close();

            return accessToken;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "exception";
    }
}

