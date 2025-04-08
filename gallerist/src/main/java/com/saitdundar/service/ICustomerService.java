package com.saitdundar.service;


import com.saitdundar.dto.DtoCustomer;
import com.saitdundar.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
