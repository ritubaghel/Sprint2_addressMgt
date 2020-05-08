package com.capgemini.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.go.dao.IAddressDao;
import com.capgemini.go.entities.Address;
import com.capgemini.go.exceptions.AddressNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    private IAddressDao addressDao;

    public IAddressDao getAddressDao() {
        return addressDao;
    }

    @Autowired
    public void setAddressDao(IAddressDao dao) {
        this.addressDao = dao;
    }

    @Override
	public boolean addAddress(Address addressDTO) {
		addressDTO = addressDao.save(addressDTO);
		return true;
	}

	@Override
	public boolean deleteAddress(Address addressDTO) {
		String id = addressDTO.getAddressId();
		Address address = findById(id);
		addressDao.delete(addressDTO);
		return true;
	}

	@Override
	public boolean updateAddress(Address addressDTO) {
		String id = addressDTO.getAddressId();
		Address address = findById(id);
		addressDTO = addressDao.save(addressDTO);
		return true;
	}

	@Override
	public List<Address> viewAllAddress(String addressId) {
		 List<Address>list= addressDao.findAll();
	      return list;
	}
	@Override
	public Address findById(String addressId) {
		Optional<Address> optional = addressDao.findById(addressId);
		if (optional.isPresent()) {
			Address question = optional.get();
			return question;
		}
		throw new AddressNotFoundException("Address not found for id=" + addressId);
	}
	

}
