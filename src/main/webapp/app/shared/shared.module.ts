import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { PillpalServerSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [PillpalServerSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [PillpalServerSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PillpalServerSharedModule {
  static forRoot() {
    return {
      ngModule: PillpalServerSharedModule
    };
  }
}
