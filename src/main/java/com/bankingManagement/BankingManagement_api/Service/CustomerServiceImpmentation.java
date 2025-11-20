package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.CustomerDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Repoistry.CustomerReposistory;
import com.bankingManagement.BankingManagement_api.entity.Customer;
import com.bankingManagement.BankingManagement_api.model.CustomerDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class CustomerServiceImpmentation implements CustomerService {

}
