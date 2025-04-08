package com.saitdundar.controller.impl;


import com.saitdundar.controller.IRestCurrencyRatesController;
import com.saitdundar.controller.RestBaseController;
import com.saitdundar.controller.RootEntity;
import com.saitdundar.dto.CurrencyRatesResponse;
import com.saitdundar.service.ICurrencyRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/")
public class RestCurrencyRatesController extends RestBaseController implements IRestCurrencyRatesController{

    @Autowired
    private ICurrencyRatesService currencyRatesService;


    @GetMapping("/currency-rates    ")
    @Override
    public RootEntity<CurrencyRatesResponse> getCurrencyRates(
            @RequestParam("startDate") String startDate,  @RequestParam("endDate") String endDate) {
        return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
    }
}
