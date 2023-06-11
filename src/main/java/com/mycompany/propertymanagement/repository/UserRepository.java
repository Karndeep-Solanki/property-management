package com.mycompany.propertymanagement.repository;

import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    // Springframework.guru/spring-data-jpa-query (check this website to find other way to make query methods)


    // No need to write manualy @Query, because Spring will automatically understand based on the name  of method
    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);

    Optional<UserEntity> findByOwnerEmail(String email);



}
