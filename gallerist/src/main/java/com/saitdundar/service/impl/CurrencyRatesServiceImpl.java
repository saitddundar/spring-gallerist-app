package com.saitdundar.service.impl;

import com.saitdundar.dto.CurrencyRatesResponse;
import com.saitdundar.exception.BaseException;
import com.saitdundar.exception.ErrorMessage;
import com.saitdundar.exception.MessageType;
import com.saitdundar.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {
    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        String rootURL = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A";
        String type = "json";

        String endpoint = rootURL + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "l2uMnpjBGq");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        // burada else eklemezsek missing return statement hatası veriyor.

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<CurrencyRatesResponse>() {
                    });
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, "Non-success response code: " + response.getStatusCode()));
            }
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
        }
    }
}