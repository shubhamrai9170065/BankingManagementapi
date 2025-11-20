package com.bankingManagement.BankingManagement_api.Mapper;

import com.bankingManagement.BankingManagement_api.entity.Branch;
import com.bankingManagement.BankingManagement_api.model.BranchDTO;

public class BranchMapper {

    public static BranchDTO convertBranchToBranchDTO(Branch branch){
        BranchDTO branchDto = new BranchDTO();
        branchDto.setBranchID(branch.getBranchID());
        branchDto.setBranchName(branch.getBranchName());
        branchDto.setBranchAddress(branch.getBranchAddress());
        return branchDto;
    }
}
