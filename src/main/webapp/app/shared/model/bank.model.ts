import { ICustomer } from 'app/shared/model/customer.model';
import { ICustomerAccount } from 'app/shared/model/customer-account.model';

export interface IBank {
  id?: number;
  branchCode?: string;
  branchName?: string;
  customers?: ICustomer[];
  customerAccounts?: ICustomerAccount[];
}

export class Bank implements IBank {
  constructor(
    public id?: number,
    public branchCode?: string,
    public branchName?: string,
    public customers?: ICustomer[],
    public customerAccounts?: ICustomerAccount[]
  ) {}
}
