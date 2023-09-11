package com.softline.simplerestclient.service;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetadataServiceImpl implements Metadata {

    private final AuthorizeService authorizeService;

    @Autowired
    public MetadataServiceImpl(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }


    @Override
    public void downloadLogs() {
        try {
            String url = "https://omega-api-stage.softline.com/api/odata";

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            // Устанавливаем заголовки
            httpGet.addHeader("Accept", "application/json;odata.metadata=minimal;odata.streaming=true");
            httpGet.addHeader("Authorization", "Bearer " + authorizeService.getToken());

            // Отправляем запрос
            CloseableHttpResponse response = httpClient.execute(httpGet);

            // Обрабатываем ответ
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println(responseBody);

            // Закрываем ресурсы
            response.close();
            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

