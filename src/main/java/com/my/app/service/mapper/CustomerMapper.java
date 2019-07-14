package com.my.app.service.mapper;

import com.my.app.domain.Customer;
import com.my.app.service.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Customer} and its DTO {@link CustomerDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class})
public interface CustomerMapper extends EntityMapper<CustomerDTO, Customer> {

    @Mapping(source = "bank.id", target = "bankId")
    CustomerDTO toDto(Customer customer);

    @Mapping(target = "customerAccounts", ignore = true)
    @Mapping(target = "removeCustomerAccount", ignore = true)
    @Mapping(source = "bankId", target = "bank")
    Customer toEntity(CustomerDTO customerDTO);

    default Customer fromId(Long id) {
        if (id == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(id);
        return customer;
    }
}
