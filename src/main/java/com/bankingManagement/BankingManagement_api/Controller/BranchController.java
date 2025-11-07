package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.BranchDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Service.BranchService;
import com.bankingManagement.BankingManagement_api.model.BranchDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Log4j2
@RestController
@RequestMapping("/api/v3/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> findAllBranches(){
        log.info("Inside the BranchController.findAll");
        List<BranchDTO> branchDTOS;
        try{
            branchDTOS = branchService.getAllBranches();
            log.info("Branch details:{}", branchDTOS);
        }catch (BranchDetailsNotFound ex){
            log.info("Branch details not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.info("Exception while getting the branch details");
            ex1.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BranchController.findAll()");
        return new ResponseEntity<>(branchDTOS,HttpStatus.OK);
    }
  @GetMapping("/{branchId}")
    public ResponseEntity<BranchDTO> findByIdBranches(@PathVariable("branchId") int id){
        log.info("Inside the branchController id method");
        BranchDTO branchDto;
        try{
           branchDto =  branchService.getById(id);
           log.info("branch details is {} ", branchDto);
        } catch(BranchDetailsNotFound e){
            log.error("Branch id doesn't eixsts");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            log.error("Some exception is occured during fethcing");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("Branch details is {} ", branchDto);
        return new ResponseEntity<>(branchDto,HttpStatus.OK);
    }

    @DeleteMapping("/{BranchId}")
    public ResponseEntity<String> deleteByIdMethod(@PathVariable("BranchId") int BranchId){
        String str;
        try{
            str = branchService.deletebyIdMethod( BranchId);
            log.info(str);
        }catch (BranchDetailsNotFound e){
            log.error("Id not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception is generated while delete the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/name/{branchName}")
    public ResponseEntity<String> deleteByNameMethod(@PathVariable("branchName") String branchName) {
        String str;
        try {
            str = branchService.deleteByBranchNameMethod(branchName);
            log.info("Deleting operation successfully doing there work");
        } catch(BranchDetailsNotFound e){
            log.error("branch name is not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception is occur during delete the data");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @GetMapping("/addressOrId/{address}/{id}")
    public ResponseEntity<List<BranchDTO>> getByBranchAddressOrBranchId(
            @PathVariable("address") String address,
            @PathVariable("id") int id) {
        log.info("Fetching data on the of branchAddress and branchId");
        List<BranchDTO> branches;
        try {
            branches = branchService.getByBranchAddressOrBranchId(address, id);
            log.info("Branch details is: {}", branches);
        } catch (BranchDetailsNotFound ex) {
            log.error("Bank details not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("some exception is occur during fetching the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branches, HttpStatus.OK);
    }

    @GetMapping("/nameAndAddress/{name}/{address}")
    public ResponseEntity<List<BranchDTO>> getByBranchNameAndBranchAddress(
            @PathVariable("address") String address,
            @PathVariable("name") String name){
        log.info("Fetching data on the basis of branchAddress and branchName");
        List<BranchDTO> branch;
        try{
            branch = branchService.getByBranchNameAndBranchAddress(name,address);
            log.info("Branch details on the basis of name and address is {}", branch);
        } catch(BranchDetailsNotFound ex){
            log.error("branch details not exist for address and name");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("some exception is generate while fetching the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }
}
