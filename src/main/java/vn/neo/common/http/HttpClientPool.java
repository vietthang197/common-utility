package vn.neo.common.http;

import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class HttpClientPool {
    private static final PoolingHttpClientConnectionManager POOLING_CONN_MANAGER = new PoolingHttpClientConnectionManager();
    private static final CloseableHttpClient client = HttpClients
            .custom().setConnectionManager(POOLING_CONN_MANAGER)
            .build();

    public static String sendPostSOAP(String request, String urlApi, int timeout) throws IOException {
        HttpPost httpPost = new HttpPost(urlApi);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        StringEntity stringEntity = new StringEntity(request, StandardCharsets.UTF_8);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "text/xml");
        httpPost.setHeader("SOAPAction", "");
        httpPost.setEntity(stringEntity);
        StringBuilder response = new StringBuilder();
        InputStreamReader isr = null;
        BufferedReader in = null;
        try (
                CloseableHttpResponse httpResponse = client.execute(httpPost);
        ) {
            InputStream inputStream = httpResponse.getEntity().getContent();
            isr = new InputStreamReader(inputStream);
            in = new BufferedReader(isr);
            String value = null;
            while ((value = in.readLine()) != null) {
                response.append(value);
            }

            in.close();
            return response.toString();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
    }


    public static String sendPost(String request, String urlApi, int timeout, String contentType) throws IOException {
        HttpPost httpPost = new HttpPost(urlApi);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        StringEntity stringEntity = new StringEntity(request, StandardCharsets.UTF_8);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        httpPost.setEntity(stringEntity);
        StringBuilder response = new StringBuilder();
        InputStreamReader isr = null;
        BufferedReader in = null;
        OutputStream out = null;
        try (
                CloseableHttpResponse httpResponse = client.execute(httpPost);
        ) {
            InputStream inputStream = httpResponse.getEntity().getContent();
            isr = new InputStreamReader(inputStream);
            in = new BufferedReader(isr);
            String value = null;
            while ((value = in.readLine()) != null) {
                response.append(value);
            }

            in.close();
            return response.toString();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static String sendGet(String request, String urlApi, int timeout, String contentType) throws IOException {
        HttpGet httpGet = new HttpGet(urlApi);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        httpGet.setConfig(requestConfig);
        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, contentType);
        StringBuilder response = new StringBuilder();
        InputStreamReader isr = null;
        BufferedReader in = null;
        try (
                CloseableHttpResponse httpResponse = client.execute(httpGet);
        ) {
            InputStream inputStream = httpResponse.getEntity().getContent();
            isr = new InputStreamReader(inputStream);
            in = new BufferedReader(isr);
            String value = null;
            while ((value = in.readLine()) != null) {
                response.append(value);
            }

            in.close();
            return response.toString();
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException ignored) {
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
