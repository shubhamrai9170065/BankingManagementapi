package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.LoanDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Service.LoanService;
import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.ReplicateScaleFilter;
import java.util.List;
@Log4j2
@RestController
@RequestMapping("/api/v5/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> findAllLoans(){
        log.info("Inside the LoanController.findAll");
        List<LoanDTO> loanDTOS;
        try{
            loanDTOS = loanService.getAllLoans();
            log.info("Loan details:{}", loanDTOS);
        }catch (LoanDetailsNotFound ex){
            log.info("Loan details not exist");
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.info("Exception while getting the loan details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of LoanController.findAll()");
        return new ResponseEntity<>(loanDTOS, HttpStatus.OK);
    }

    @GetMapping("/{laonId}")
    public ResponseEntity<LoanDTO> findById(@PathVariable("laonId") int laonId){
        log.info("Fetching details of loan by id");
        LoanDTO loans;
        try{
            loans = loanService.getByLaonId(laonId);
            log.info("Details of loan based on id {},",loans);
        }catch(LoanDetailsNotFound ex){
            log.error("Loan id doesn't exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(Exception e){
            log.error("Exception occur while fetching the data from id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }

    @GetMapping("/loanType/{loanType}")
    public ResponseEntity<List<LoanDTO>> getByLoanType(@PathVariable("loanType") String loanType){
        log.info("fetching of data is start by loanController and getByloanType()");
        List<LoanDTO> loans;
        try{
            loans = loanService.getByLoanType(loanType);
            log.info("Laon details is {}",loans);
        }catch(LoanDetailsNotFound ex){
            log.error("No details for loanType: {}",loanType);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("some exception occur while fetching the data by loantype");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }
    @GetMapping("/amount/{amount}")
    public ResponseEntity<List<LoanDTO>> getByLoanAmount(@PathVariable("amount") long amount){
        log.info("fetching of data is started");
        List<LoanDTO> loans;
        try{
            loans = loanService.getByLoanAmount(amount);
            log.info("Laon details for amount: {} is: {}",amount,loans);
        }catch(LoanDetailsNotFound ex){
            log.error("No loan exist for amount: {}",amount);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some error occur while fetching the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }
    @GetMapping("/loanTypeOrAmount/{loanType}/{amount}")
    public ResponseEntity<List<LoanDTO>> getByLoanTypeOrLoanAmount(@PathVariable("loanType") String loanType, @PathVariable("amount") long amount) {
        log.info("Fetching data is started on the basis of getByLaonTypeorLaonAmount");
        List<LoanDTO> loans;

        try{
            loans = loanService.getByLoanTypeOrLoanAmount(loanType,amount);
            log.info("the loan details on basis of loantype or amount is: {}",loans );
        }catch(LoanDetailsNotFound ex){
            log.error("No record exist on for loantype: {} or loanAmount: {}",loanType,amount);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during fetching the data on basis of loanType or amount");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }

    @GetMapping("/loanTypeAndAmount/{loanType}/{amount}")
    public ResponseEntity<List<LoanDTO>> getByLoanTypeAndLoanAmount(@PathVariable("loanType") String loanType, @PathVariable("amount") long amount){
        log.info("Fetching of data is started");
        List<LoanDTO> loans;
        try{
            loans = loanService.findByLoanTypeAndLoanAmountMethod(loanType,amount);
            log.info("Loan details is: {}",loans);
        }catch(LoanDetailsNotFound ex){
            log.error("No details exist for loanType: {} and amount: {}",loanType,amount);
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.info("Some exception occur during fetching the error");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loans,HttpStatus.OK);
    }

    @DeleteMapping("/{loanId}")
    public ResponseEntity<String> deleteByLoanId(@PathVariable("loanId") int loanId){
        log.info("Deletions is started inside the loanController");
        String str;
        try{
            str = loanService.deleteByLoanId(loanId);
            log.info(str);
        } catch(LoanDetailsNotFound ex){
            log.error("loan id is not present");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            log.error("Some error occur during the deletion operation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/loanType/{loanType}")
    public ResponseEntity<String> deleteByLoanType(@PathVariable("loanType") String loanType){
        String str;
        try{
            str = loanService.deleteByLoanType(loanType);
            log.info("Deletion operation is successfully done");
        } catch(LoanDetailsNotFound ex){
            log.error("No loanType exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception occur during deletion operation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/loanAmount/{loanAmount}")
    public ResponseEntity<String> deleteByLoanAmount(@PathVariable("loanAmount") long loanAmount){
        String str;
        try{
            str = loanService.deleteByLoanAmount(loanAmount);
            log.info("Deletion operation is successfully done");
        }catch(LoanDetailsNotFound ex){
            log.error("No details exist for this loanAmount");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception is occur during deletion operation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/loanTypeOrLoanAmount/{loanType}/{loanAmount}")
    public ResponseEntity<String> deleteByLoanTypeOrLoanAmount(@PathVariable("loanType") String loanType,
                                                               @PathVariable("loanAmount") long loanAmount){
        String str;
        try{
            str = loanService.deleteByLoanTypeOrLoanAmount(loanType,loanAmount);
            log.info("Deletion operation is successfully");
        } catch(LoanDetailsNotFound ex){
            log.error("No record is present for loanType: {} Or loanAmount: {}",loanType,loanAmount);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.error("Some exception occur during performing the deletion operation");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

    @DeleteMapping("/loanTypeAndLoanAmount/{loanType}/{loanAmount}")
    public ResponseEntity<String> deleteByLoanTypeAndLoanAmount(@PathVariable("loanType") String loanType, @PathVariable("loanAmount") long loanAmount) {
        String str;
        try{
            str = loanService.deleteByLoanTypeAndLoanAmount(loanType,loanAmount);
            log.info("Deletion of data is succesffully done");
        } catch(LoanDetailsNotFound ex){
            log.error("No record is exist for loanType And loanAmount");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Some exception is occur during deletion of record");
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(str,HttpStatus.OK);
    }

}
