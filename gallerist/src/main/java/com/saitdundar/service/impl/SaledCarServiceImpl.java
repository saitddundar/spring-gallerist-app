package com.saitdundar.service.impl;


import com.saitdundar.dto.*;
import com.saitdundar.enums.CarStatusType;
import com.saitdundar.exception.BaseException;
import com.saitdundar.exception.ErrorMessage;
import com.saitdundar.exception.MessageType;
import com.saitdundar.model.Car;
import com.saitdundar.model.Customer;
import com.saitdundar.model.SaledCar;
import com.saitdundar.repository.CarRepository;
import com.saitdundar.repository.CustomerRepository;
import com.saitdundar.repository.GalleristRepository;
import com.saitdundar.repository.SaledCarRepository;
import com.saitdundar.service.ICurrencyRatesService;
import com.saitdundar.service.ICustomerService;
import com.saitdundar.service.ISaledCarService;
import com.saitdundar.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    public BigDecimal convertToUSD(Customer customer){

        CurrencyRatesResponse currencyRatesResponse= currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()),DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;
    }
    public boolean checkCarStatus(Long carId){
         Optional<Car> optionalCar= carRepository.findById(carId);
         if(optionalCar.isPresent()&&optionalCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())){
            return false;
        }
            return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car){
        BigDecimal customerUSDAmount = convertToUSD(customer);
        BigDecimal remainingCustomerUsdAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRates = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd=  new BigDecimal(currencyRates.getItems().get(0).getUsd());
        return remainingCustomerUsdAmount.multiply(usd);
    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU){
        Optional<Customer> optionalCustomer= customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if(optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCustomerId().toString()));
        }
        Optional<Car> optionalCar= carRepository.findById(dtoSaledCarIU.getCarId());
        if(optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerUSDAmount=convertToUSD(optionalCustomer.get());
        if(customerUSDAmount.compareTo(optionalCar.get().getPrice())==0 || optionalCar.get().getPrice().compareTo(customerUSDAmount)>0){
            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU){

        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());
        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
        if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED,dtoSaledCarIU.getCarId().toString())) ;
        }

        if(!checkAmount(dtoSaledCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH,""));
        }


        SaledCar savedSaledCar= saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer,car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);
    }


    public DtoSaledCar toDTO(SaledCar saledCar){
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer= new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(saledCar,dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(),dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(),dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(),dtoCar);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);

        return dtoSaledCar;
    }
}
