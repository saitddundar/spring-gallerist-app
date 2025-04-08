package com.saitdundar.controller;


import com.saitdundar.dto.DtoAddress;
import com.saitdundar.dto.DtoAddressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
