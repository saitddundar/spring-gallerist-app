package com.saitdundar.service.impl;

import com.saitdundar.dto.DtoAddress;
import com.saitdundar.dto.DtoGallerist;
import com.saitdundar.dto.DtoGalleristIU;
import com.saitdundar.exception.BaseException;
import com.saitdundar.exception.ErrorMessage;
import com.saitdundar.exception.MessageType;
import com.saitdundar.model.Address;
import com.saitdundar.model.Gallerist;
import com.saitdundar.repository.AddressRepository;
import com.saitdundar.repository.GalleristRepository;
import com.saitdundar.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {

        Optional<Address> optAddress=  addressRepository.findById(dtoGalleristIU.getAddressId());
        if(optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));

        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());



        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());

        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);


        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }
}
