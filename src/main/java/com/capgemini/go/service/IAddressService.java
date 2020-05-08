package com.capgemini.go.service;
import java.util.List;

import com.capgemini.go.entities.Address;

public interface IAddressService {

	boolean deleteAddress(Address addressDTO);
	boolean updateAddress(Address addressDTO);
	boolean addAddress(Address addressDTO);
    List<Address>viewAllAddress(String addressId);
    Address findById(String addressId);
		
	
}
