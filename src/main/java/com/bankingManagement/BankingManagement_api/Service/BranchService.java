package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BranchDetailsNotFound;
import com.bankingManagement.BankingManagement_api.entity.Branch;
import com.bankingManagement.BankingManagement_api.model.BranchDTO;
import java.util.List;

public interface BranchService {

    //GET OPERATION METHOD
    List<BranchDTO> getAllBranch() throws BranchDetailsNotFound;
    BranchDTO getBranchById(int id) throws BranchDetailsNotFound;
    List<BranchDTO> getBranchByBranchName(String name) throws  BranchDetailsNotFound;
    List<BranchDTO> getBranchByBranchAddress(String address) throws BranchDetailsNotFound;
    List<BranchDTO> getBranchByNameOrAddress(String name, String address) throws BranchDetailsNotFound;
    List<BranchDTO> getBranchByNameAndAddress(String name, String address) throws BranchDetailsNotFound;

    // DELETE OPERATION METHOD
    String deleteBranchById(int id) throws BranchDetailsNotFound;
    String deleteBranchByName(String name) throws BranchDetailsNotFound;
    String deleteBranchByAddress(String address) throws BranchDetailsNotFound;
    String deleteBranchByNameOrAddress(String name, String address) throws BranchDetailsNotFound;
}
