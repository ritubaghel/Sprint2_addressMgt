package com.capgemini.go.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.capgemini.go.dto.AddressDto;
import com.capgemini.go.entities.Address;
import com.capgemini.go.exceptions.AddressNotFoundException;
import com.capgemini.go.service.IAddressService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/addresses")
@Validated // required for validating path variables
public class AddressRestController {

    private static final Logger Log= LoggerFactory.getLogger(AddressRestController.class);

    @Autowired
    private IAddressService service;

   @PostMapping("/add")
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDto addressdto){
	   Address address =  convertFromDto(addressdto);
	    boolean isAdded=service.addAddress(address);
        ResponseEntity<Address>response=new ResponseEntity<>(address, HttpStatus.OK);
        return response;
   }

  


	@GetMapping("/remove")
	public ResponseEntity<Address> deleteAddress(@RequestBody @Valid AddressDto addressdto) {	
		Address address =  convertFromDto(addressdto);
		Boolean isRemoved = service.deleteAddress(address);
		ResponseEntity<Address> response = new ResponseEntity<>(address, HttpStatus.OK);
		return response;
	}

	@PutMapping("/update")
	public ResponseEntity<Address> updateCartItem(@RequestBody @Valid AddressDto addressdto) {
		Address address =  convertFromDto(addressdto);
		Boolean isupdated = service.updateAddress(address);
		ResponseEntity<Address> response = new ResponseEntity<>(address, HttpStatus.OK);
		return response;
	}

	
	public Address  convertFromDto(AddressDto dto) {
		Address address = new Address();
		address.setBuildingNo(dto.getBuildingNo());
		address.setCity(dto.getCity());
		address.setField(dto.getField());
		address.setRetailerId(dto.getRetailerId());
		address.setState(dto.getState());
		address.setZip(dto.getZip());
		return address;
	}

   @ExceptionHandler(AddressNotFoundException.class)
   public ResponseEntity<String>handleAddressNotFound(AddressNotFoundException ex){
       Log.error("address not found exception",ex);
       String msg=ex.getMessage();
       ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
       return response;
   }

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<String>handleConstraintViolate(ConstraintViolationException ex){
      Log.error("constraint violation",ex);
      String msg=ex.getMessage();
      ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.BAD_REQUEST);
      return response;
   }

   @ExceptionHandler(Throwable.class)
   public ResponseEntity<String>handleAll(Throwable ex){
     Log.error("exception caught",ex);
     String msg=ex.getMessage();
     ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.INTERNAL_SERVER_ERROR);
     return response;
   }


}
