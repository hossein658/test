package com.my.app.service.mapper;

import com.my.app.domain.Bank;
import com.my.app.service.dto.BankDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Bank} and its DTO {@link BankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankMapper extends EntityMapper<BankDTO, Bank> {


    @Mapping(target = "customers", ignore = true)
    @Mapping(target = "removeCustomer", ignore = true)
    @Mapping(target = "customerAccounts", ignore = true)
    @Mapping(target = "removeCustomerAccount", ignore = true)
    Bank toEntity(BankDTO bankDTO);

    default Bank fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bank bank = new Bank();
        bank.setId(id);
        return bank;
    }
}
