package com.saitdundar.service;

import com.saitdundar.dto.DtoCar;
import com.saitdundar.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
