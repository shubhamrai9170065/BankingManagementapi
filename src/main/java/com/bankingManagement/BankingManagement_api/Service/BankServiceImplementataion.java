package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BankDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.BankMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.BankRepoistry;
import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import com.bankingManagement.BankingManagement_api.request.BankRequest;
import com.bankingManagement.BankingManagement_api.updateRequest.BankUpdateRequest;
import jakarta.transaction.Transactional;
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
    private BankRepoistry bankRepoistry;

    @Override
    public List<BankDTO> getAllBanks() throws  BankDetailsNotFound {
        log.info("Fetching detalis of all banks from sevice layer");
        List<Bank> bank = bankRepoistry.findAll();

        if(CollectionUtils.isEmpty(bank)){
            log.info("No record exist for bank");
            throw new BankDetailsNotFound("No record exist");
        }

        List<BankDTO> bankDto = bank.stream().map(BankMapper::convertBankToBankTo).collect(Collectors.toList());
        return bankDto;
    }

   public BankDTO getById(int id) throws BankDetailsNotFound {
        log.info("Fetching data on the basis of id in service layer");
        Optional<Bank> bank = bankRepoistry.findById(id);
        if(bank.isEmpty()){
            log.info("No bank record exist for id: {}",id);
            throw new BankDetailsNotFound("No record exist for this id");
        }
        return BankMapper.convertBankToBankTo(bank.get());
    }

    public List<BankDTO> getByName(String name) throws BankDetailsNotFound {
        log.info("Fetching data on the basis of bank name in serive layer");
        List<Bank> bank = bankRepoistry.findByBankName(name);

        if(CollectionUtils.isEmpty(bank)){
            log.info("No bank details exist for name: {}",name);
            throw new BankDetailsNotFound("No bank details exist for this name");
        }
        List<BankDTO> banks = bank.stream().map(BankMapper::convertBankToBankTo).collect(Collectors.toList());
        return banks;
    }

    public List<BankDTO> getByAddress(String address) throws BankDetailsNotFound {
        log.info("Fetching data on the baisis of bank address in service layer");
        List<Bank> bank = bankRepoistry.findByBankAddress(address);
        if(CollectionUtils.isEmpty(bank)){
            String str = "No record exist for address: " + address;
            log.error(str);
            throw new BankDetailsNotFound(str);
        }
        List<BankDTO> bankDto = bank.stream().map(BankMapper::convertBankToBankTo).collect(Collectors.toList());
        return bankDto;
    }

    public List<BankDTO> getByBankNameOrBankAddress(String name, String address) throws BankDetailsNotFound {
        log.info("Fetching data on the basis of bank name or bank address in service layer");
        List<Bank> bank = bankRepoistry.findByBankNameOrBankAddress(name,address);
        if(CollectionUtils.isEmpty(bank)){
            String str = "No record for name : " + name + " or address: " + address;
            log.error(str);
            throw new BankDetailsNotFound(str);
        }
        List<BankDTO> bankDto = bank.stream().map(BankMapper::convertBankToBankTo).collect(Collectors.toList());
        return bankDto;
    }

    public List<BankDTO> getByBankNameAndBankAddress(String name, String address) throws BankDetailsNotFound {
        log.info("Fetching of bank details on basis of name: {} and address: {}",name,address);
        List<Bank> banks = bankRepoistry.findByBankNameAndBankAddress(name,address);

        if(CollectionUtils.isEmpty(banks)){
            String str = "No record exist for name: " + name + " and address: " + address;
            log.error(str);
            throw new BankDetailsNotFound(str);
        }
        List<BankDTO> bankDto = banks.stream().map(BankMapper::convertBankToBankTo).collect(Collectors.toList());
        return bankDto;
    }

    //DELETEION OPERATION IS STARTED

    @Transactional
    public String deleteBankById(int id) throws BankDetailsNotFound {
        log.info("Delete by id operation is started in service layer");
        Optional<Bank> bank = bankRepoistry.findById(id);
        if(bank.isEmpty()){
            log.error("Bank id not eixst");
            throw new BankDetailsNotFound("Id doesn't exist");
        }
        bankRepoistry.deleteById(id);
        return "Record deteled successfully which have id " + id;
    }

    @Transactional
    public String deleteBankByName(String name) throws BankDetailsNotFound {
        log.info("Delete by name opeation is started in service layer");
        List<Bank> bank = bankRepoistry.findByBankName(name);
        if(CollectionUtils.isEmpty(bank)){
            log.info("No bank record exist for name: {}",name);
            throw new BankDetailsNotFound("No bank record exist");
        }
        bankRepoistry.deleteAllByBankName(name);
        return "Record delete by name " + name + " is completed";
    }

    @Transactional
    public String deleteBankByAddress(String address) throws BankDetailsNotFound {
        log.info("Delee by address operation is started in serice layer");
        List<Bank> bank = bankRepoistry.findByBankAddress(address);
        if(CollectionUtils.isEmpty(bank)){
            log.error("No bank record exist for address: {}", address);
            throw new BankDetailsNotFound("No bank record exist for this address");
        }
        bankRepoistry.deleteAllByBankAddress(address);
        return "Record delete by address: " + address + " is complerted";
    }

    @Transactional
    public String deleteBankByNameOrAddress(String name, String address) throws BankDetailsNotFound {
        log.info("Delete by name or address operaation is started in service layer");
        List<Bank> bank = bankRepoistry.findByBankNameOrBankAddress(name,address);
        if(CollectionUtils.isEmpty(bank)){
            log.error("No bank record exist for name: {} or address: {}",name,address);
            throw new BankDetailsNotFound("No bank record exist for this name or address");
        }
        bankRepoistry.deleteAllByBankNameOrBankAddress(name,address);
        return "Record delete by name: " + name + " or address: " + address + " is completed";
    }

    @Transactional
    public String deleteBankByNameAndAddress(String name, String address) throws BankDetailsNotFound {
        log.info("Delete by name and address operation is started in servie layer");
        List<Bank> bank = bankRepoistry.findByBankNameAndBankAddress(name,address);
        if(CollectionUtils.isEmpty(bank)){
            log.error("No bank record exist for name: {} and address: {}",name,address);
            throw new BankDetailsNotFound("No bank record exist for this name and address");
        }
        bankRepoistry.deleteAllByBankNameAndBankAddress(name,address);
        return "Record delete by name: " + name + " and address: " + address + " is completed";
    }

    // POST OPERATION
    public BankDTO createBank(BankRequest bankRequest) throws BankDetailsNotFound {
        log.info("create bank operation is started in service layer");
        if(bankRequest == null){
            log.error("Bank request object is null");
            throw new BankDetailsNotFound("Bank request object is null");
        }
        Bank bank = BankMapper.convertBankRequestToBank(bankRequest);
        bankRepoistry.save(bank);  // inserting data in bank table
        return BankMapper.convertBankToBankTo(bank);
    }

    // PUT OPERATION

    public BankDTO updateBank(BankUpdateRequest bankUpdateRequest) throws BankDetailsNotFound {
        log.info("Update bank operation is started in service layer");

        Optional<Bank> optionalBank = bankRepoistry.findById(bankUpdateRequest.getBankCode());
        if(optionalBank.isEmpty()) {
            log.error("No bank record exist for id: {}", bankUpdateRequest.getBankCode());
            throw new BankDetailsNotFound("No bank record exist for this id");
        }
        Bank bank = optionalBank.get();
        if (bankUpdateRequest.getBankName() != null) {
            bank.setBankName(bankUpdateRequest.getBankName());
        }
        if(bankUpdateRequest.getBankAddress() != null){
            bank.setBankAddress(bankUpdateRequest.getBankAddress());
        }

        bankRepoistry.save(bank);
        return BankMapper.convertBankToBankTo(bank);
    }
}
