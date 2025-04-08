package com.saitdundar.service;

import com.saitdundar.dto.DtoSaledCar;
import com.saitdundar.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
