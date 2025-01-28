package com.hariSolution.product;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductRestClient {

    @Value("${application.config.product-url}")
    private String productUrl;

    private final RestTemplate restTemplate;

    public ProductRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDetails getProductFromProductService(Integer productId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Integer> requestEntity = new HttpEntity<>(productId, headers);

        ParameterizedTypeReference<ProductDetails> responseType = new ParameterizedTypeReference<ProductDetails>() {};

        ResponseEntity<ProductDetails> responseEntity = restTemplate.exchange(
                productUrl + "/get/{product-id}",
                HttpMethod.GET,
                requestEntity,
                responseType,
                productId // The actual product ID is passed here in the URL
        );

        // Check if response is an error
        if (responseEntity.getStatusCode().isError()) {
            throw new RuntimeException("An error occurred while processing the product (ID: " + productId + "): " + responseEntity.getStatusCode());
        }

        // Return the body of the response
        return responseEntity.getBody();
    }
}
