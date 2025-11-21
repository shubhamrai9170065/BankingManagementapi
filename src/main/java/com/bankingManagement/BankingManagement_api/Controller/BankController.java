package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.BankDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Service.BankService;
import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import com.bankingManagement.BankingManagement_api.request.BankRequest;
import com.bankingManagement.BankingManagement_api.updateRequest.BankUpdateRequest;
import jakarta.servlet.annotation.HandlesTypes;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequestMapping("/api/v1/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanksDetails() {
        List<BankDTO> bank;

        try {
            bank = bankService.getAllBanks();
            log.info("Details of bank is {}", bank);
        } catch (BankDetailsNotFound e) {
            log.info("No record exist for banks");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Some exception occur during fetching the details of banks");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankDTO> getByIdDetails(@PathVariable("id") int id) {
        BankDTO bank;
        try {
            bank = bankService.getById(id);
            log.info("Details of bank for id: {} is: {}", id, bank);
        } catch (BankDetailsNotFound e) {
            log.info("No record exist for id: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Some exception occured during fetching details by id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BankDTO>> getAllByNameDetails(@PathVariable("name") String name) {
        List<BankDTO> banks;

        try {
            banks = bankService.getByName(name);
            log.info("Bank details for name: {} is: {}", name, banks);
        } catch (BankDetailsNotFound e) {
            log.error("No bank record eixst for name: {}", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.info("Some exception occur during fetching the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<BankDTO>> getAllBankDetailsAddress(@PathVariable("address") String address) {
        List<BankDTO> banks;
        try {
            banks = bankService.getByAddress(address);
            log.info("Bank details for address: {} is {}", address, banks);
        } catch (BankDetailsNotFound e) {
            log.error("No bank registered for address: {}", address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some exception occured during fetching the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @GetMapping("/nameOrAddress/{name}/{address}")
    public ResponseEntity<List<BankDTO>> getAllByBankNameOrBankAddress(@PathVariable("name") String name,
                                                                       @PathVariable("address") String address) {
        List<BankDTO> banks;
        try {
            banks = bankService.getByBankNameOrBankAddress(name, address);
            log.info("Bank detail for name: {} or address: {} is: {}", name, address, banks);
        } catch (BankDetailsNotFound e) {
            log.error("No bank details for name: {} or address: {}", name, address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some exception occur during fetching bank details on the basis of name: {} or address: {}", name, address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @GetMapping("/nameAndAddress/{name}/{address}")
    public ResponseEntity<List<BankDTO>> getAllBankByNameAndAddress(@PathVariable("name") String name,
                                                                    @PathVariable("address") String address) {
        List<BankDTO> banks;
        try {
            banks = bankService.getByBankNameAndBankAddress(name, address);
            log.info("Bank Details on basis of name: {} and address: {} is: {}", name, address, banks);
        } catch (BankDetailsNotFound e) {
            log.error("No record eixst for name: {} and address: {}", name, address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some exception occur during the fetching of data on basis of name: {} and address: {}", name, address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    // CONTROLLER FOR DELELTE ENDPOINTS

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankById(@PathVariable("id") int id) {
        log.info("Deletion operation  started in controller");
        String str;
        try {
            str = bankService.deleteBankById(id);
            log.info("Deletion operation successfully completed");
        } catch (BankDetailsNotFound e) {
            log.error("Id doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some exception occur during delete by id: {}", id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteBankByName(@PathVariable("name") String name) {
        log.info("Deletion task is started in controller");
        String str;
        try {
            str = bankService.deleteBankByName(name);
            log.info("Deletion operation run without any exception");
        } catch (BankDetailsNotFound e) {
            log.error("No bank record exist for name: {} ", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some exception occur during perform delete operation by name");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @DeleteMapping("/address/{address}")
    public ResponseEntity<String> deleteBankByAddress(@PathVariable("address") String address) {
        log.info("Deletion operation is started in controller layer");
        String str;
        try {
            str = bankService.deleteBankByAddress(address);
            log.info("Deleteion operation successfully completed in controller layer");
        } catch (BankDetailsNotFound e) {
            log.error("No bank record exist for address: {}", address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some excepiton occur during the performing during delete operatin by adress: {}", address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str, HttpStatus.OK);
    }


    @DeleteMapping("/nameOrAddress/{name}/{address}")
    public ResponseEntity<String> deleteBankByNameOrAddress(@PathVariable("name") String name,
                                                            @PathVariable("address") String address) {
        log.info("Delete by name or address opeation is stared in controller layer");
        String str;
        try {
            str = bankService.deleteBankByNameOrAddress(name, address);
            log.info("Delete by name or address operation successfully completed in controller layer");
        } catch (BankDetailsNotFound e) {
            log.error("No bank record exist for name: {} or address: {}", name, address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Some exception occur during delete by name: {} or address: {}", name, address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Delete by name or address operation successfully exectued", HttpStatus.OK);
    }

    @DeleteMapping("/nameAndAddress/{name}/{address}")
    public ResponseEntity<String> deleteBankByNameAndAddress(@PathVariable("name") String name,
                                                             @PathVariable("address") String address) {
        log.info("Delete by name and address operation is started in controller layer");
        String str;
        try{
            str = bankService.deleteBankByNameAndAddress(name,address);
            log.info("Delete by name and address operation successfully completed in controller layer");
        }catch(BankDetailsNotFound e){
            log.error("No bank record exist for name: {} and address: {}",name,address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("Some exception occur during delete by name: {} and address:{}",name,address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Delete by name and address operation succesfully executed",HttpStatus.OK);
    }


    // CONTROLLER FOR POST ENDPOINT TO CREATE BANK RECORD

    @PostMapping
    public ResponseEntity<BankDTO> createBankRecord(@RequestBody @Valid BankRequest bankRequest){
        log.info("Create bank record operation is started in controller layer");
        BankDTO bankDTO;
        try{
            bankDTO = bankService.createBank(bankRequest);
            log.info("Create bank record operation successfully complete in controller layer");
        } catch (BankDetailsNotFound e){
            log.error("Bank request object is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            log.error("Some exception occur during creating bank record");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bankDTO,HttpStatus.OK);
    }

    // CONTROLLER FOR PUT ENDPOINT TO UPDATE BANK RECORD

    @PutMapping
    public ResponseEntity<BankDTO> udateBankRecord(@RequestBody BankUpdateRequest bankUpdateRequest){
        if(bankUpdateRequest == null){
            log.error("Bank update request object is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankDTO bankDTO;
        try{
            bankDTO = bankService.updateBank(bankUpdateRequest);
            log.info("Update bank record operation successfully completed in controller layer");
        }catch (BankDetailsNotFound e){
            log.error("No bank record exist for id: {}", bankUpdateRequest.getBankCode());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during updating bank record");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bankDTO,HttpStatus.OK);
    }

}

