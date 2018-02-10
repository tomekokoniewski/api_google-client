package com.infoshareacademy.api;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class GoogleTranslate {

    private final String API_KEY;

    public GoogleTranslate(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String generateUrl(String address, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(address).append("?");

        params.forEach((key, value) -> {
                sb.append(key).append("=").append(value);
                sb.append("&");
        });

        return sb.toString();
    }

    public String translate(String input, String source, String target) {

        String address = "https://translation.googleapis.com/language/translate/v2";

        Map<String, String> params = new HashMap<>();
        params.put("source", source);
        params.put("target", target);
        params.put("key", API_KEY);
        params.put("q", input);

        String url = generateUrl(address, params);

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        Response response = webTarget.request().get();

        String result = response.readEntity(String.class);
        response.close();

        return result;
    }
}