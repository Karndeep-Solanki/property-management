package com.mycompany.propertymanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PROPERTY_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="PROPERTY_TITLE",nullable = false)
    private String title;
    private String description;
    //private String ownerName;

    @Column(name="EMAIL",nullable = false)
    //private String ownerEmail;
    private Double price;
    private String address;

    //@ManyToOne // many property belongs to one user
    @ManyToOne(fetch = FetchType.LAZY)//it will not fetch the user data while fatching property (by default its EAGER, that fetches)
    @JoinColumn(name="USER_ID",nullable = false) // Foreign key:new column "USER_ID" will be created in propety table, and the value of UserEntity's Primary key is stored here as foreign key
    private UserEntity userEntity;

}
