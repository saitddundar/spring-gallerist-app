package com.saitdundar.service.impl;

import com.saitdundar.dto.DtoCar;
import com.saitdundar.dto.DtoCarIU;
import com.saitdundar.model.Car;
import com.saitdundar.repository.CarRepository;
import com.saitdundar.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;
    private Car createCar(DtoCarIU dtoCarIU) {

        Car car = new Car();
        car.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCarIU, car);

        return car;
    }



    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {

        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }
}
