package com.saitdundar.controller;

import com.saitdundar.dto.DtoGallerist;
import com.saitdundar.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
