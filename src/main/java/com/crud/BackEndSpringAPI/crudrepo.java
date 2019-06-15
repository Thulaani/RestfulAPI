package com.crud.BackEndSpringAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface crudrepo extends JpaRepository<User, Integer>{

}
