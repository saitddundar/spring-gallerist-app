package com.saitdundar.controller;


import com.saitdundar.dto.DtoGalleristCar;
import com.saitdundar.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {
    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
