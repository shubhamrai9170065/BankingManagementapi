package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.LoanDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Repoistry.LoanReposistry;
import com.bankingManagement.BankingManagement_api.Service.LoanService;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v4/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoanDetails(){
        List<LoanDTO> loans;
        try{
            loans = loanService.getAlLoans();
            log.info("All details of loan is: {}",loans);
        }catch(LoanDetailsNotFound e){
            log.error("No loan details existed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching all the details of loan");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getBranchDetailsById(@PathVariable("id") int id){
        log.info("Fetching of loan details by id is started");
        LoanDTO loan;
        try{
            loan = loanService.getLoanById(id);
            log.info("Loan details for id: {} is: {}",id,loan);
        }catch(LoanDetailsNotFound e){
            log.error("No laon record exist for id: {}",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching loan details by id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<LoanDTO>> getLoanDetailsByType(@PathVariable("type") String type){
        log.info("Fetching loan details based on loan type");
        List<LoanDTO> loans;
        try{
            loans = loanService.getLoanByType(type);
            log.info("Loan details for type: {} is: {}",type,loans);
        }catch(LoanDetailsNotFound e){
            log.error("No loan record exist for type: {}",type);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("some exception occur during fetching the loan details based on type: {}",type);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }
}
