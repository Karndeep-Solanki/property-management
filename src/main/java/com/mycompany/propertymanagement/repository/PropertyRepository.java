package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity,Long> {

    List <PropertyEntity> findAllByUserEntityId (Long userId);// Spring data Jpa automatically write query according the method name

//@Query("SELECT p FROM propertyEntity p WHERE p.userEntity.id = :userId")
//List <PropertyEntity> findAllByUserEntityId (@Param("userId") Long userId);
}
