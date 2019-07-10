import { IArticle } from 'app/shared/model/article.model';

export const enum Status {
  ACTIVE = 'ACTIVE',
  INACTIVE = 'INACTIVE',
  BLOCKED = 'BLOCKED'
}

export const enum AccountType {
  LONG = 'LONG',
  SHORT = 'SHORT',
  CURRENT = 'CURRENT',
  GARZ = 'GARZ'
}

export interface ICustomerAccount {
  id?: number;
  accountNumber?: string;
  status?: Status;
  accountType?: AccountType;
  balance?: number;
  articles?: IArticle[];
  bankId?: number;
  customerId?: number;
}

export class CustomerAccount implements ICustomerAccount {
  constructor(
    public id?: number,
    public accountNumber?: string,
    public status?: Status,
    public accountType?: AccountType,
    public balance?: number,
    public articles?: IArticle[],
    public bankId?: number,
    public customerId?: number
  ) {}
}
