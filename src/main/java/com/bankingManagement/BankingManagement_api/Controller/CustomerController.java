package com.bankingManagement.BankingManagement_api.Controller;

import com.bankingManagement.BankingManagement_api.Exception.CustomerDetailsNotFound;
import com.bankingManagement.BankingManagement_api.Service.CustomerService;
import com.bankingManagement.BankingManagement_api.model.CustomerDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Log4j2
@RestController
@RequestMapping("/api/v4/customers")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAllCustomers(){
        log.info("Inside the CustomerController.findAllCustomers");
        List<CustomerDTO> customerDTOS;
        try{
            customerDTOS = customerService.getAllCustomers();
            log.info("Customer details:{}", customerDTOS);
        }catch (CustomerDetailsNotFound ex){
            log.info("Customer details not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1){
            log.info("Exception while getting the customer details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of CustomerController.findAllCustomers()");
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId") int customerId){
        CustomerDTO customer;
        try{
            customer = customerService.getCustomerById(customerId);
            log.info("Customer details at {} is {}",customerId,customer);
        } catch(CustomerDetailsNotFound ex){
            log.error("Customer details not exist for this id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.error("Getting exceptions while getting the customer details by id");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @GetMapping("/customerName/{name}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByName(@PathVariable("name") String name){
        List<CustomerDTO> namedto;

        try{
            namedto = customerService.getCustomerByNameMethod(name);
            log.info("customer name is {}", name);
        } catch(CustomerDetailsNotFound e){
            log.error("Customer detials is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.info("Some exception were occur");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(namedto,HttpStatus.OK);
    }

    @GetMapping("/customerAddress/{address}")
    public ResponseEntity<List<CustomerDTO>> getCustomerByAddress(@PathVariable("address") String address){
        List<CustomerDTO> addressDto;

        try{
            addressDto = customerService.getCustomerByAddressMethod(address);
            log.info("Customer address is {}", address);
        } catch(CustomerDetailsNotFound e){
            log.error("Customer details is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.info("Some exception were occur");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(addressDto,HttpStatus.OK);
    }
    @GetMapping("/phoneNumber/{phoneNumber}")
    public ResponseEntity<CustomerDTO> getCustomerByPhone(@PathVariable("phoneNumber") long phoneNumber){
        CustomerDTO customerDto;
        try{
            customerDto = customerService.getCustomerByPhoneNumber(phoneNumber);
            log.info("Customer phone number is {}", phoneNumber);
        } catch(CustomerDetailsNotFound ex){
            log.info("Customer details not exist in db");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception e){
            log.info("Some exception were occur");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerDTO>> findCustomerByNameAndAddress(@PathVariable("name") String name,
                                                                          @PathVariable("address") String address){
        log.info("Fetching data from customer entity based on their name and address");
        List<CustomerDTO> customer;
        try{
            customer = customerService. getCustomerByCustomerNameOrCustomerAddress(name,address);
            log.info("Customer detail based on their name and address is {}",customer);
        }catch(CustomerDetailsNotFound ex){
            log.info("Customer details not found for this name or on this address");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            log.info("some exception were occured during fetching the data");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

}
