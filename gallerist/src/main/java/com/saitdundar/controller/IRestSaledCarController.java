package com.saitdundar.controller;

import com.saitdundar.dto.DtoSaledCar;
import com.saitdundar.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtosaledcarIU);
}
