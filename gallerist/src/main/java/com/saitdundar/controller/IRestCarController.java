package com.saitdundar.controller;

import com.saitdundar.dto.DtoCar;
import com.saitdundar.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
