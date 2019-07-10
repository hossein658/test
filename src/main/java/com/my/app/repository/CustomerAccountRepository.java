package com.my.app.repository;

import com.my.app.domain.CustomerAccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomerAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

}
