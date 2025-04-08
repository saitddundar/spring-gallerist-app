package com.saitdundar.service.impl;


import com.saitdundar.dto.DtoAddress;
import com.saitdundar.dto.DtoAddressIU;
import com.saitdundar.model.Address;
import com.saitdundar.repository.AddressRepository;
import com.saitdundar.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {


    @Autowired
    AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU){
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU,address);
        return address;

    }


    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {

        DtoAddress dtoAddress = new DtoAddress();

        Address savedAddress=  addressRepository.save(createAddress(dtoAddressIU));

        BeanUtils.copyProperties(savedAddress,dtoAddress);
        return dtoAddress;
    }
}
