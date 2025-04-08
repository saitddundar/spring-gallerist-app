package com.saitdundar.controller;

import com.saitdundar.dto.DtoCustomer;
import com.saitdundar.dto.DtoCustomerIU;


public interface IRestCustomerController {
    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
