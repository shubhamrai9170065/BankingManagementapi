package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Bank;
import com.bankingManagement.BankingManagement_api.entity.Branch;
import com.bankingManagement.BankingManagement_api.model.BankDTO;
import com.bankingManagement.BankingManagement_api.model.BranchDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BankMapper {

    public static BankDTO convertBankToBankDTO(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBankCode(bank.getBankCode());
        bankDTO.setBankName(bank.getBankName());
        bankDTO.setBankAddress(bank.getBankAddress());

        // Map all branch entities to DTOs
        Set<BranchDTO> branchDToSet = bank.getBranch()
                .stream()
                .map(BankMapper::convertToBranchDTO)
                .collect(Collectors.toSet());

        bankDTO.setBranches(branchDToSet);
        return bankDTO;
    }

    // Convert single Branch entity to BranchDTO
    public static BranchDTO convertToBranchDTO(Branch branch) {
        BranchDTO branchDto = new BranchDTO();
        branchDto.setBranchID(branch.getBranchID());
        branchDto.setBranchName(branch.getBranchName());
        branchDto.setBranchAddress(branch.getBranchAddress());
        return branchDto;
    }
}
