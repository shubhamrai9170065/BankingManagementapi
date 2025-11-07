package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BankDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.BankMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.BankRepoistry;
import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service

public class BankServiceImplementataion implements BankService {

    @Autowired
    private BankRepoistry bankRepository;

    @Autowired
    private BankMapper bankMapper;
    @Override
    public List<BankDTO> findAllBanks() throws BankDetailsNotFound {
       log.info("Fetching all bank details from the repository");
        log.info("Inside the BankController.findAll");
        List<Bank> bankList = bankRepository.findAll();
        if (CollectionUtils.isEmpty(bankList)) {
            log.error("Bank details not exist");
            throw new BankDetailsNotFound("Bank details not found");
        }

        return bankList.stream().map(BankMapper::convertBankToBankDTO).collect(Collectors.toList());
    }
    @Override
    public BankDTO findBankById(int bankCode) throws BankDetailsNotFound {
        log.info("Fetching data of single employee by bankCode is {}",bankCode);
       Optional<Bank> bank = bankRepository.findById(bankCode);
       if(bank.isEmpty()){
           log.error("bankCode does't exists");
           throw new BankDetailsNotFound();
       }
        return bankMapper.convertBankToBankDTO(bank.get());  // bank.get() give value from optional
    }

    public List<BankDTO> findBankByNameMethod(String bankName) throws BankDetailsNotFound{
        log.info("Fetching bank details by bank name: {}", bankName);
        List<Bank> banks = bankRepository.findByBankName(bankName);
        if(CollectionUtils.isEmpty(banks)){
            log.error("Bank details not fouund for the name: {}",bankName);
             throw new BankDetailsNotFound("Bank details not found for the name: " + bankName);
        }
        return banks.stream().map(BankMapper::convertBankToBankDTO).collect(Collectors.toList());
    }

    public List<BankDTO> findBankByAddressMethod(String bankAddress) throws BankDetailsNotFound{
        log.info("Fetching address details by bank address: {}", bankAddress);
        List<Bank> banks = bankRepository.findByBankAddress(bankAddress);
        if(CollectionUtils.isEmpty(banks)){
            log.error("Bank details not found for the address: {}",bankAddress);
            throw new BankDetailsNotFound("Bank details not found for the address: ");
        }
        return banks.stream().map(BankMapper::convertBankToBankDTO).collect(Collectors.toList());
    }

   public List<BankDTO> findBanknameOrBankAddressMethod(String name, String address) throws BankDetailsNotFound {
        log.info("Fetching details of bank on basis of bank name and bank address");
        List<Bank> banks = bankRepository.findByBankNameOrBankAddress(name, address);
        if(CollectionUtils.isEmpty(banks)){
            log.error("No bank details exist for name: {} or address: {} ",name, address);
            throw new BankDetailsNotFound("No bank details exist for this name and address");
        }
        return banks.stream().map(BankMapper::convertBankToBankDTO).collect(Collectors.toList());
   }

   public List<BankDTO> findByBankNameAndBankAddressMethod(String name, String address) throws BankDetailsNotFound {
        log.info("Fetching the bank details on the basis of bank name and bank address");
        List<Bank> banks = bankRepository.findByBankNameAndBankAddress(name,address);
        if(CollectionUtils.isEmpty(banks)){
            log.error("No bank details exist for name: {} and address {}",name,address);
            throw  new BankDetailsNotFound("Detials not exist for this name and address");
        }
        return banks.stream().map(BankMapper::convertBankToBankDTO).collect(Collectors.toList());
   }

}
