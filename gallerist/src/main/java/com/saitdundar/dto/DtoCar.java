package com.saitdundar.dto;



import com.saitdundar.enums.CarStatusType;
import com.saitdundar.enums.CurrencyType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class DtoCar extends DtoBase {

    private String licensePlate;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;

}
