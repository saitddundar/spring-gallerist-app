package com.saitdundar.service;

import com.saitdundar.dto.DtoAccount;
import com.saitdundar.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);


}


