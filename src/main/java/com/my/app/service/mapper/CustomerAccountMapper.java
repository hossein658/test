package com.my.app.service.mapper;

import com.my.app.domain.CustomerAccount;
import com.my.app.service.dto.CustomerAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link CustomerAccount} and its DTO {@link CustomerAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class, CustomerMapper.class})
public interface CustomerAccountMapper extends EntityMapper<CustomerAccountDTO, CustomerAccount> {

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "customer.id", target = "customerId")
    CustomerAccountDTO toDto(CustomerAccount customerAccount);

    @Mapping(target = "articles", ignore = true)
    @Mapping(target = "removeArticle", ignore = true)
    @Mapping(source = "bankId", target = "bank")
    @Mapping(source = "customerId", target = "customer")
    CustomerAccount toEntity(CustomerAccountDTO customerAccountDTO);

    default CustomerAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setId(id);
        return customerAccount;
    }
}
