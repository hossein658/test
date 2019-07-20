package com.my.app.repository;

import com.my.app.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c " +
        "inner join c.bank b " +
        "where b.branchCode = :branchCode")
    Page<Customer> findByBranchCode(Pageable page , @Param("branchCode") String branchCode);

    Optional<Customer> findByIdNumber(Integer idNumber);
}
