import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bank',
        loadChildren: './bank/bank.module#PillpalServerBankModule'
      },
      {
        path: 'customer',
        loadChildren: './customer/customer.module#PillpalServerCustomerModule'
      },
      {
        path: 'customer-account',
        loadChildren: './customer-account/customer-account.module#PillpalServerCustomerAccountModule'
      },
      {
        path: 'article',
        loadChildren: './article/article.module#PillpalServerArticleModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PillpalServerEntityModule {}
