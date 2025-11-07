package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.BankDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Service.BankService;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
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

    //http://127.0.0.1:8080/api/v1/banks GET
    @GetMapping
    public ResponseEntity<List<BankDTO>> findAll(){
        log.info("Inside the BankController.findAll");
        List<BankDTO> bankTOS;
        try{
            bankTOS = bankService.findAllBanks();
            log.info("Bank details:{}", bankTOS);
        }catch (BankDetailsNotFound ex){
            log.error("Bank details not exist");
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.error("Exception while getting the bank details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankController.findAll()");
        return new ResponseEntity<>(bankTOS, HttpStatus.OK);
    }

    @GetMapping("/{bankCode}")
    public ResponseEntity<BankDTO> BankById(@PathVariable("bankCode") int bankCode){
        log.info("Inside the bankController.findBankById");
        BankDTO bankDto;
        try{
            bankDto = bankService.findBankById(bankCode);
            log.info("Bank details at {} is {}",bankCode,bankDto);
        } catch (BankDetailsNotFound e){
            log.error("Bank details not exist for this code");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e1){
            log.error("Getting exceptions while getting the bank dtails by id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankController.BankById()");
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BankDTO>> findByName(@RequestParam("bankName") String bankName){
//    @GetMapping("/name/{bankName}")
//            public ResponseEntity<List<BankDTO>> findByName(@PathVariable("bankName") String bankName){
        log.info("Inside the BankController.findByName");
        List<BankDTO> bankDto;

        try{
           bankDto = bankService.findBankByNameMethod(bankName);
           log.info("Bank details for name {} is {}", bankName, bankDto);
        } catch(BankDetailsNotFound e){
            log.error("Bank details not exsist for this name");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error("Getting exception while getting the bank details by name");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bankDto,HttpStatus.OK);
    }

    @GetMapping("/address/{bankAddress}")
    public ResponseEntity<List<BankDTO>> findbyAddress(@PathVariable("bankAddress") String bankAddress){
        log.info("Inside the BankController.findByAddress");
        List<BankDTO> bankDto;

        try{
            bankDto = bankService.findBankByAddressMethod(bankAddress);
            log.info("Bank details for address {} is {}", bankAddress, bankDto);
        } catch(BankDetailsNotFound e){
            log.error("Bank details not exsist for this address");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error("Getting exception while getting the bank details by address");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(bankDto,HttpStatus.OK);
    }

    @GetMapping("/bankNameOrbankAddress/{name}/{address}")
    public ResponseEntity<List<BankDTO>> getByBankNameOrBankAddress(@PathVariable("name") String name, @PathVariable("address") String address) {
        log.info("Fetching of data is start in bank controller by using findBankByBankNameOrBankAddress");
        List<BankDTO> banks;
        try{
            banks = bankService.findBanknameOrBankAddressMethod(name,address);
            log.info("Details of bank on basis of bankName and bankAddress is: " + banks);
        }catch(BankDetailsNotFound e){
            log.error("No data exist for this name : {} or for address {}",name,address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception is generated while fetching the data");
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of getByBankNameOrBankAdddress()");
        return new ResponseEntity<>(banks,HttpStatus.OK);
    }

    @GetMapping("/bankNameAndBankAddress/{name}/{address}")
    public ResponseEntity<List<BankDTO>> findByBankNameAndBankAddress(@PathVariable("name") String name, @PathVariable("address") String address){
        log.info("Fetching the data i bankController on the basis of name and address");
        List<BankDTO> banks;
        try{
            banks = bankService.findByBankNameAndBankAddressMethod(name,address);
            log.info("Bank details on the basis of name: {} and address: {} is: {}",name,address,banks);
        }catch(BankDetailsNotFound ex){
            log.error("Bank details not exist for name: {} and address: {}",name,address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fecthing the error");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(banks,HttpStatus.OK);
    }
}
