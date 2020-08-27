package com.techprimers.stock.stockservice.resource;

import com.techprimers.stock.stockservice.exception.QuoteNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    private final RestTemplate restTemplate;
    public StockResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String REST_DB_SERVICE_BASE_URL = "http://db-service:8300/rest/db/";

    @GetMapping("/all")
    public List<String> getStock() {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange(
                REST_DB_SERVICE_BASE_URL, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                });

        return quoteResponse.getBody();
    }

    @GetMapping("/{username}")
    public List<String> getStockByUsername(@PathVariable("username") final String userName) {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange(
                REST_DB_SERVICE_BASE_URL + userName, HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                });

        List<String> quotes = quoteResponse.getBody();
        if (quotes.isEmpty())
            throw new QuoteNotFoundException("Username Not Found on Quotes [ " + userName + " ]");

        return quotes;
    }

}
