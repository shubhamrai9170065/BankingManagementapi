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
@RequestMapping("/api/v2/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranche(){
        List<BranchDTO> branch;
        try{
            branch = branchService.getAllBranch();
            log.info("Branch details is: {}",branch);
        }catch(BranchDetailsNotFound e){
            log.error("No branch record exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("Some exception occur during fetching all branches details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable("id") int id){
        BranchDTO branch;
        try{
            branch = branchService.getBranchById(id);
            log.info("Bank details for id: {} is: {}",id,branch);
        } catch(BranchDetailsNotFound e){
            log.error("No branch record exist for id: {}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Sum exception occur during fetching the branch by id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(branch, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BranchDTO>> getBranchByName(@PathVariable("name") String name){
        List<BranchDTO> branch;
        try{
            branch = branchService.getBranchByBranchName(name);
            log.info("Branch details for name: {} is: {}",name,branch);
        }catch (BranchDetailsNotFound e){
            log.error("No branch record exist for name: {}",name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("some exception may occur during fetching the branch details by name");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }

    @GetMapping("/address/{address}")
    public ResponseEntity<List<BranchDTO>> getBranchByAddress(@PathVariable("address") String address) {
        List<BranchDTO> branch;
        try{
            branch = branchService.getBranchByBranchAddress(address);
            log.info("Branch details for address: {} is: {}",address,branch);
        }catch (BranchDetailsNotFound e){
            log.error("No branch details exist for address: {}",address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching the branch details based on address");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }

    @GetMapping("/nameOrAddress/{name}/{address}")
    public ResponseEntity<List<BranchDTO>> getBranchByNameOrAddress(@PathVariable("name") String name,
                                                                    @PathVariable("address") String address){
        List<BranchDTO> branch;
        try{
            branch = branchService.getBranchByNameOrAddress(name,address);
            log.info("Branch details for name: {} or address: {}",name,address);
        } catch(BranchDetailsNotFound e){
            log.error("No branch record exist for name: {} or address: {}",name,address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching the data on basis of name: {} or address: {}",name,address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }

    @GetMapping("/nameAndAddress/{name}/{address}")
    public ResponseEntity<List<BranchDTO>> getBranchByNameAndAddress(@PathVariable("name") String name,
                                                                     @PathVariable("address") String address){
        List<BranchDTO> branch;
        try{
            branch = branchService.getBranchByNameAndAddress(name,address);
            log.info("Branch record on basis of name: {} and address: {} is: {}",name,address,branch);
        }catch(BranchDetailsNotFound e){
            log.error("No branch record exsit for name: {} and adress: {}",name,address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.info("Some exception occur during fetching the record for name: {} and address: {}",name,address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(branch,HttpStatus.OK);
    }

    // DELETE OPERATIONS
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBranchById(@PathVariable("id") int id){
        log.info("Deleting branch record for id: {} started in controller layeer",id);
        String str;
        try{
            str = branchService.deleteBranchById(id);
            log.info("Branch record for id: {} deleted successfully",id);
        }catch(BranchDetailsNotFound e){
            log.error("No branc record exist for id: {}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e) {
            log.error("Some exception occur during deleting the branch record for id: {}",id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteBranchByName(@PathVariable("name") String name){
        log.info("Deleting branch record for name: {} operation started in controller layer",name);
        String str;
        try{
            str = branchService.deleteBranchByName(name);
            log.info("Branch record for name: {} deleted successfully",name);
        }catch(BranchDetailsNotFound e){
            log.error("No branch record exist for name: {}",name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            log.error("Some exception occur during deleting the branch record for name: {}",name);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/address/{address}")
    public ResponseEntity<String> delleteBranchByAddress(@PathVariable("address") String address){
        log.info("Deleting branch record for address: {} operation started in controller layer",address);
        String str;
        try{
            str = branchService.deleteBranchByAddress(address);
            log.info("Branch record for address: {} deleted successfully",address);
        }catch(BranchDetailsNotFound e){
            log.error("No branch record exist for address: {}",address);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during deleting the branch record for address: {}",address);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

}
