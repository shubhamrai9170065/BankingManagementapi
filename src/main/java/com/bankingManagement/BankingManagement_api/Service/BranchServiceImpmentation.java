package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BranchDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.BranchMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.BranchReposistry;
import com.bankingManagement.BankingManagement_api.entity.Branch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankingManagement.BankingManagement_api.model.BranchDTO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class BranchServiceImpmentation  implements BranchService {

    @Autowired
    private BranchReposistry branchReposistry;

    public List<BranchDTO> getAllBranch() throws BranchDetailsNotFound {
        log.info("Fetching detail of branch in service layer");
        List<Branch> branch = branchReposistry.findAll();

        if(CollectionUtils.isEmpty(branch)){
            log.error("No branch record exist");
            throw new BranchDetailsNotFound("No branch details exist now");
        }

        List<BranchDTO> branches = branch.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
        return branches;
    }

    public BranchDTO getBranchById(int id) throws BranchDetailsNotFound {
        log.info("Fetching branch details based on id");
        Optional<Branch> branch = branchReposistry.findById(id);
        if(branch.isEmpty()){
            String str = "No record exist for id: " + id;
            log.error(str);
            throw new BranchDetailsNotFound(str);
        }

        return BranchMapper.convertBranchToBranchDTO(branch.get());
    }

    public List<BranchDTO> getBranchByBranchName(String name) throws  BranchDetailsNotFound {
        log.info("Fetching branch details based on name");
        List<Branch> branch = branchReposistry.findByBranchName(name);
        if(CollectionUtils.isEmpty(branch)){
            String str = "No branch details exist for name: " + branch;
            log.error(str);
            throw  new BranchDetailsNotFound(str);
        }
        List<BranchDTO> branchDto = branch.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
        return branchDto;
    }

    public List<BranchDTO> getBranchByBranchAddress(String address) throws BranchDetailsNotFound {
        log.info("fetching branch details no basis of branch address");
        List<Branch> branch = branchReposistry.findByBranchAddress(address);
        if(CollectionUtils.isEmpty(branch)){
            String str = "No branch record exist for address: " + address;
            log.error(str);
            throw new BranchDetailsNotFound(str);
        }

        List<BranchDTO> branches = branch.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
        return branches;
    }

    public List<BranchDTO> getBranchByNameOrAddress(String name, String address) throws BranchDetailsNotFound {
        log.info("Fetching branch details on basis of name: {} or address: {}",name,address);
        List<Branch> branch = branchReposistry.findByBranchNameOrBranchAddress(name,address);
        if(CollectionUtils.isEmpty(branch)){
            String str = "No branch record exist for name:  " + name + "or address: " + address;
            log.error(str);
            throw new BranchDetailsNotFound(str);
        }
        List<BranchDTO> branches = branch.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
        return branches;
    }

    public List<BranchDTO> getBranchByNameAndAddress(String name, String address) throws BranchDetailsNotFound {
        log.info("Fetching of branch Details on basis of name: {} and address: {}",name,address);
        List<Branch> branch = branchReposistry.findByBranchNameAndBranchAddress(name,address);
        if(CollectionUtils.isEmpty(branch)){
            String str = "No branch details exist for name: " + name + " and address: " + address;
            log.info(str);
            throw new BranchDetailsNotFound(str);
        }
        List<BranchDTO> branches = branch.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
        return branches;
    }
}
