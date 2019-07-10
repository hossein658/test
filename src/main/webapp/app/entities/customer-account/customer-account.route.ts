import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CustomerAccount } from 'app/shared/model/customer-account.model';
import { CustomerAccountService } from './customer-account.service';
import { CustomerAccountComponent } from './customer-account.component';
import { CustomerAccountDetailComponent } from './customer-account-detail.component';
import { CustomerAccountUpdateComponent } from './customer-account-update.component';
import { CustomerAccountDeletePopupComponent } from './customer-account-delete-dialog.component';
import { ICustomerAccount } from 'app/shared/model/customer-account.model';

@Injectable({ providedIn: 'root' })
export class CustomerAccountResolve implements Resolve<ICustomerAccount> {
  constructor(private service: CustomerAccountService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICustomerAccount> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CustomerAccount>) => response.ok),
        map((customerAccount: HttpResponse<CustomerAccount>) => customerAccount.body)
      );
    }
    return of(new CustomerAccount());
  }
}

export const customerAccountRoute: Routes = [
  {
    path: '',
    component: CustomerAccountComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'CustomerAccounts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CustomerAccountDetailComponent,
    resolve: {
      customerAccount: CustomerAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CustomerAccounts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CustomerAccountUpdateComponent,
    resolve: {
      customerAccount: CustomerAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CustomerAccounts'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CustomerAccountUpdateComponent,
    resolve: {
      customerAccount: CustomerAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CustomerAccounts'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const customerAccountPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CustomerAccountDeletePopupComponent,
    resolve: {
      customerAccount: CustomerAccountResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'CustomerAccounts'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
