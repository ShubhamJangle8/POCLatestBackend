package com.ikea.resourceallocation.requestscrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ikea.resourceallocation.requestscrud.model.Request;

/**
 * @author Ubakara Anthony F
 */
@Repository
public interface RequestCrudRepo extends JpaRepository<Request, String> {

}
