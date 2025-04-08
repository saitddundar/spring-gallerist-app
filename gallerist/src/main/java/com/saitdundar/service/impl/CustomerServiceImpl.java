package com.saitdundar.service.impl;

import com.saitdundar.dto.DtoAccount;
import com.saitdundar.dto.DtoAddress;
import com.saitdundar.dto.DtoCustomer;
import com.saitdundar.dto.DtoCustomerIU;
import com.saitdundar.exception.BaseException;
import com.saitdundar.exception.ErrorMessage;
import com.saitdundar.exception.MessageType;
import com.saitdundar.model.Account;
import com.saitdundar.model.Address;
import com.saitdundar.model.Customer;
import com.saitdundar.repository.AccountRepository;
import com.saitdundar.repository.AddressRepository;
import com.saitdundar.repository.CustomerRepository;
import com.saitdundar.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private Customer createCustomer(DtoCustomerIU dtoCustomerIU){

        Optional<Address> optionalAddress= addressRepository.findById(dtoCustomerIU.getAddressId());
        if(optionalAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
        }
        Optional<Account> optionalAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if(optionalAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));

        }
        Customer customer = new Customer();
        customer.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCustomerIU,customer);

        customer.setAddress(optionalAddress.get());
        customer.setAccount(optionalAccount.get());
        return customer;
    }



    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer,dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(),dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(),dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);
        return dtoCustomer;
    }
}
