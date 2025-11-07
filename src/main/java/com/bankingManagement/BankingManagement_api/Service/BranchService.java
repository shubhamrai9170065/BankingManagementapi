package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BranchDetailsNotFound;
import com.bankingManagement.BankingManagement_api.model.BranchDTO;
import java.util.List;

public interface BranchService {
    List<BranchDTO> getAllBranches() throws BranchDetailsNotFound;
    BranchDTO getById(int id) throws BranchDetailsNotFound;
    String deletebyIdMethod(int id) throws BranchDetailsNotFound;
    List<BranchDTO> getByBranchAddressOrBranchId(String name, int id) throws BranchDetailsNotFound;
    String deleteByBranchNameMethod(String name) throws BranchDetailsNotFound;
    List<BranchDTO> getByBranchNameAndBranchAddress(String name, String address) throws BranchDetailsNotFound;
}
