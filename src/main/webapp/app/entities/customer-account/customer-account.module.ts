import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PillpalServerSharedModule } from 'app/shared';
import {
  CustomerAccountComponent,
  CustomerAccountDetailComponent,
  CustomerAccountUpdateComponent,
  CustomerAccountDeletePopupComponent,
  CustomerAccountDeleteDialogComponent,
  customerAccountRoute,
  customerAccountPopupRoute
} from './';

const ENTITY_STATES = [...customerAccountRoute, ...customerAccountPopupRoute];

@NgModule({
  imports: [PillpalServerSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CustomerAccountComponent,
    CustomerAccountDetailComponent,
    CustomerAccountUpdateComponent,
    CustomerAccountDeleteDialogComponent,
    CustomerAccountDeletePopupComponent
  ],
  entryComponents: [
    CustomerAccountComponent,
    CustomerAccountUpdateComponent,
    CustomerAccountDeleteDialogComponent,
    CustomerAccountDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PillpalServerCustomerAccountModule {}
