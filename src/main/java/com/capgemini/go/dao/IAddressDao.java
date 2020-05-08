package com.capgemini.go.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.capgemini.go.entities.Address;
import java.util.List;
public interface IAddressDao extends JpaRepository<Address,String> {
    
}
