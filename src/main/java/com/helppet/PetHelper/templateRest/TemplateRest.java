package com.helppet.PetHelper.templateRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TemplateRest {

    @Autowired
    private RestTemplate restTemplate;  
    // Método genérico para chamadas GET
    public <T> T getForObject(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }

    // Método genérico para chamadas POST
    public <T> T postForObject(String url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }

    // Método genérico para chamadas PUT
    public <T> ResponseEntity<T> putForObject(String url, Object request, Class<T> responseType) {
        restTemplate.put(url, request);
        return ResponseEntity.ok().build();
    }

    // Método genérico para chamadas DELETE
    public void delete(String url) {
        restTemplate.delete(url);
    }
}
