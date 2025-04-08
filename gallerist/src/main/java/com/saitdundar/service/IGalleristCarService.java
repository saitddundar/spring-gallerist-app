package com.saitdundar.service;


import com.saitdundar.dto.DtoGalleristCar;
import com.saitdundar.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
