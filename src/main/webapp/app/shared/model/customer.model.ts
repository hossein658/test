import { Moment } from 'moment';
import { ICustomerAccount } from 'app/shared/model/customer-account.model';

export const enum GenderType {
  MALE = 'MALE',
  FEMALE = 'FEMALE'
}

export interface ICustomer {
  id?: number;
  firstName?: string;
  lastName?: string;
  fatherNmae?: string;
  nationalCode?: string;
  birthDate?: Moment;
  idNumber?: number;
  genderType?: GenderType;
  address?: string;
  customerAccounts?: ICustomerAccount[];
  bankId?: number;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public fatherNmae?: string,
    public nationalCode?: string,
    public birthDate?: Moment,
    public idNumber?: number,
    public genderType?: GenderType,
    public address?: string,
    public customerAccounts?: ICustomerAccount[],
    public bankId?: number
  ) {}
}
