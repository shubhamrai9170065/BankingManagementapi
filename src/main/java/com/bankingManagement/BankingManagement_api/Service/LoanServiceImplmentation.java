package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.LoanDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.LoanMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.LoanReposistry;
import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.LazyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class LoanServiceImplmentation implements LoanService {

    @Autowired
    private LoanReposistry loanReposistry;
    @Autowired
    private LoanMapper loanMapper;

    public List<LoanDTO> getAllLoans() throws LoanDetailsNotFound {
        log.info("Fetching all loan details from the repository");
        List<Loan> loanList = loanReposistry.findAll();

        if(CollectionUtils.isEmpty(loanList)){
            log.info("Loan details not exist");
            throw new LoanDetailsNotFound("Loan details not found");
        }
        return loanList.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
    }

    public LoanDTO getByLaonId(int id) throws LoanDetailsNotFound{
        log.info("Fetching the data on the basis loan id");
        Optional<Loan> loan = loanReposistry.findById(id);
        if(loan.isEmpty()){
            log.error("Laon id doesn't exist");
            throw new LoanDetailsNotFound("Laon id doesn't exist");
        }
        return LoanMapper.convertLoanToLoanDTO(loan.get());
    }

   public List<LoanDTO> getByLoanType(String loanType) throws LoanDetailsNotFound {
        log.info("Fetching loan details on the basis of loantype");
        List<Loan> loans = loanReposistry.findByLoanType(loanType);
        if(CollectionUtils.isEmpty(loans)){
            log.error("No details exist for loantype: {}",loanType);
            throw new LoanDetailsNotFound("No details exist for this loantype");
        }
        return loans.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
   }
public List<LoanDTO> getByLoanAmount(long amount) throws LoanDetailsNotFound {
        log.info("Fetching data on basis of loan amount");
        List<Loan> loans = loanReposistry.findByLoanAmount(amount);
        if(CollectionUtils.isEmpty(loans)){
            log.error("No details exist for this amount");
            throw new LoanDetailsNotFound("No details exists");
        }
        return loans.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
}
    public List<LoanDTO> getByLoanTypeOrLoanAmount(String loanType, long amount) throws LoanDetailsNotFound {
        log.info("Fetching data from loan on the basis of loanType or amount");
        List<Loan> loans = loanReposistry.findByLoanTypeOrLoanAmount(loanType,amount);
        if(CollectionUtils.isEmpty(loans)){
            log.error("No record exist for loanType: {} or amount: {}",loanType,amount);
            throw new LoanDetailsNotFound("No record exist for this loanType or amount");
        }
        return loans.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
    }

    public List<LoanDTO> findByLoanTypeAndLoanAmountMethod(String loantype, long amount) throws LoanDetailsNotFound{
       log.error("fetching data from loan on the basis of loantype and amount");
       List<Loan> loans = loanReposistry.findByLoanTypeAndLoanAmount(loantype,amount);
       if(CollectionUtils.isEmpty(loans)){
           log.error("No data exist for loanType: {} and amount: {}",loantype,amount);
           throw new LoanDetailsNotFound("No record exist");
       }
       return loans.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
    }
}
