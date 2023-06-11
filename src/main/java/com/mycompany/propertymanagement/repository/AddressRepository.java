package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {}
