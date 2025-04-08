package com.saitdundar.controller;

import com.saitdundar.dto.DtoAccount;
import com.saitdundar.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
