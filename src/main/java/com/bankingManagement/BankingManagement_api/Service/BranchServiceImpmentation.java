package com.bankingManagement.BankingManagement_api.Service;

import com.bankingManagement.BankingManagement_api.Exception.BranchDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Mapper.BranchMapper;
import com.bankingManagement.BankingManagement_api.Repoistry.BranchReposistry;
import com.bankingManagement.BankingManagement_api.entity.Branch;
import jakarta.transaction.Transactional;
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
    private BranchReposistry branchRepository;

    @Autowired
    private BranchMapper branchMapper;

    public List<BranchDTO> getAllBranches() throws BranchDetailsNotFound {
        log.info("Fetching data of branche entity");
        List<Branch> branch = branchRepository.findAll();
        if(CollectionUtils.isEmpty(branch)){
            log.error("Branch details not found");
            throw new BranchDetailsNotFound("Branch doesn't exist");
        }
        return branch.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
    }
    public BranchDTO getById(int id) throws BranchDetailsNotFound{
        log.info("Fetching data by branch id");
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isEmpty()){
            log.error("Id {} not exist",id);
            throw new BranchDetailsNotFound("Branch id not exist in db");
        }
        return branchMapper.convertBranchToBranchDTO(branch.get());
    }

    @Override
    public List<BranchDTO> getByBranchAddressOrBranchId(String address, int id) throws BranchDetailsNotFound {
        log.info("Fetching branch by address: {} or ID: {}", address, id);
        List<Branch> branches = branchRepository.findByBranchAddressOrBranchID(address, id);

        if (CollectionUtils.isEmpty(branches)) {
            log.error("No branch found for address: {} or ID: {}", address, id);
            throw new BranchDetailsNotFound("No branch found for given address or ID");
        }

        return branches.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
    }

    public String deletebyIdMethod(int id) throws BranchDetailsNotFound {
        log.info("Trying to delete the data by id");
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isEmpty()){
            log.error("id is not eixst");
            throw new BranchDetailsNotFound("Id is not exist");
        }
         branchRepository.deleteById(id);
        return "Data deleted by id is successfully resolve";
    }

    @Transactional
    public String deleteByBranchNameMethod(String name) throws BranchDetailsNotFound {
        log.info("Deleting branch data by name: {}", name);
        List<Branch> branch = branchRepository.findByBranchName(name);
        if(CollectionUtils.isEmpty(branch)){
            log.error("Branch name doesn't exist");
            throw new BranchDetailsNotFound("Branch name is not exist");
        }

             branchRepository.deleteAllByBranchName(name);


        log.info("Successfully deleted  branch record(s) with name: {}", name);
        return "Successfully deleted  branch record(s) with name: " + name;
    }

    public List<BranchDTO> getByBranchNameAndBranchAddress(String name, String address) throws BranchDetailsNotFound{
        log.info("Fetching data on the basis of branchName and BranchAddress");
        List<Branch> branches = branchRepository.findByBranchNameAndBranchAddress(name,address);
        if(CollectionUtils.isEmpty(branches)){
            log.error("Branch details not eixst for name {} and address {}",name, address);
            throw new BranchDetailsNotFound("Branch details not exist");
        }
        return branches.stream().map(BranchMapper::convertBranchToBranchDTO).collect(Collectors.toList());
    }
}
