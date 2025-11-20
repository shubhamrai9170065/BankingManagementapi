package com.bankingManagement.BankingManagement_api.Service;


import com.bankingManagement.BankingManagement_api.Exception.LoanDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.LoanMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.LoanReposistry;
import com.bankingManagement.BankingManagement_api.entity.Loan;
import com.bankingManagement.BankingManagement_api.model.LoanDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
public class LoanServiceImplmentation implements LoanService {

    @Autowired
    private LoanReposistry loanReposistry;

    public List<LoanDTO> getAlLoans() throws LoanDetailsNotFound {
        log.info("Fetching all details of loan");
        List<Loan> loan = loanReposistry.findAll();

        if(CollectionUtils.isEmpty(loan)){
            log.error("No loan details exist");
            throw new LoanDetailsNotFound("No loan details exist");
        }
        List<LoanDTO> loanDto = loan.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
        return loanDto;
    }

    public LoanDTO getLoanById(int id) throws LoanDetailsNotFound {
        log.info("Fetchign all details of loan by id");
        Optional<Loan> loans = loanReposistry.findById(id);
        if(loans.isEmpty()){
            String str = "No loan details exist for id: " + id;
            log.error(str);
            throw new LoanDetailsNotFound(str);
        }

        return LoanMapper.convertLoanToLoanDTO(loans.get());
    }

    public List<LoanDTO> getLoanByType(String type) throws  LoanDetailsNotFound {
        log.info("Fetching loan details based on type");
        List<Loan> loans = loanReposistry.findByLoanType(type);
        if(CollectionUtils.isEmpty(loans)){
            log.error("No loan record exist for type: {}",type);
            throw new LoanDetailsNotFound("No loan record existed");
        }
        List<LoanDTO> loanDto = loans.stream().map(LoanMapper::convertLoanToLoanDTO).collect(Collectors.toList());
        return loanDto;
    }
}
