import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PillpalServerSharedModule } from 'app/shared';
import {
  BankComponent,
  BankDetailComponent,
  BankUpdateComponent,
  BankDeletePopupComponent,
  BankDeleteDialogComponent,
  bankRoute,
  bankPopupRoute
} from './';

const ENTITY_STATES = [...bankRoute, ...bankPopupRoute];

@NgModule({
  imports: [PillpalServerSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [BankComponent, BankDetailComponent, BankUpdateComponent, BankDeleteDialogComponent, BankDeletePopupComponent],
  entryComponents: [BankComponent, BankUpdateComponent, BankDeleteDialogComponent, BankDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PillpalServerBankModule {}
