import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICustomerAccount } from 'app/shared/model/customer-account.model';

type EntityResponseType = HttpResponse<ICustomerAccount>;
type EntityArrayResponseType = HttpResponse<ICustomerAccount[]>;

@Injectable({ providedIn: 'root' })
export class CustomerAccountService {
  public resourceUrl = SERVER_API_URL + 'api/customer-accounts';

  constructor(protected http: HttpClient) {}

  create(customerAccount: ICustomerAccount): Observable<EntityResponseType> {
    return this.http.post<ICustomerAccount>(this.resourceUrl, customerAccount, { observe: 'response' });
  }

  update(customerAccount: ICustomerAccount): Observable<EntityResponseType> {
    return this.http.put<ICustomerAccount>(this.resourceUrl, customerAccount, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICustomerAccount>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICustomerAccount[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
