package com.saitdundar.controller.impl;


import com.saitdundar.controller.IRestAccountController;
import com.saitdundar.controller.RootEntity;
import com.saitdundar.dto.DtoAccount;
import com.saitdundar.dto.DtoAccountIU;
import com.saitdundar.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.saitdundar.controller.RootEntity.ok;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl implements IRestAccountController {


    @Autowired
    private IAccountService accountService;


    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return  ok(accountService.saveAccount(dtoAccountIU));
    }
}
