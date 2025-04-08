package com.saitdundar.controller;

import com.saitdundar.dto.CurrencyRatesItems;
import com.saitdundar.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);

}
