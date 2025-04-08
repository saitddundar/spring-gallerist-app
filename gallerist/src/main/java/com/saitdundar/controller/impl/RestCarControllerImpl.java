package com.saitdundar.controller.impl;

import com.saitdundar.controller.IRestCarController;
import com.saitdundar.controller.RestBaseController;
import com.saitdundar.controller.RootEntity;
import com.saitdundar.dto.DtoCar;
import com.saitdundar.dto.DtoCarIU;
import com.saitdundar.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {

        return ok(carService.saveCar(dtoCarIU)) ;
    }


}
