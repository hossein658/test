import { Moment } from 'moment';

export const enum TransActionType {
  DEBTOR = 'DEBTOR',
  CREDITOR = 'CREDITOR'
}

export interface IArticle {
  id?: number;
  dateTransaction?: Moment;
  debtorAmount?: number;
  creditorAmount?: number;
  transActionType?: TransActionType;
  description?: string;
  customerAccountId?: number;
}

export class Article implements IArticle {
  constructor(
    public id?: number,
    public dateTransaction?: Moment,
    public debtorAmount?: number,
    public creditorAmount?: number,
    public transActionType?: TransActionType,
    public description?: string,
    public customerAccountId?: number
  ) {}
}
