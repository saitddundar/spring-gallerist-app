package com.saitdundar.controller.impl;


import com.saitdundar.controller.IRestSaledCarController;
import com.saitdundar.controller.RestBaseController;
import com.saitdundar.controller.RootEntity;
import com.saitdundar.dto.DtoSaledCar;
import com.saitdundar.dto.DtoSaledCarIU;
import com.saitdundar.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("rest/api/saled-car")
    public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

        @Autowired
        private ISaledCarService saledCarService;

        @PostMapping("/purchase")
        @Override
        public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtosaledcarIU) {
            return ok(saledCarService.buyCar(dtosaledcarIU));
        }
    }
