import { NgModule } from '@angular/core';

import { PillpalServerSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [PillpalServerSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [PillpalServerSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class PillpalServerSharedCommonModule {}
