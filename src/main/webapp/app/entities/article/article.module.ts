import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PillpalServerSharedModule } from 'app/shared';
import {
  ArticleComponent,
  ArticleDetailComponent,
  ArticleUpdateComponent,
  ArticleDeletePopupComponent,
  ArticleDeleteDialogComponent,
  articleRoute,
  articlePopupRoute
} from './';

const ENTITY_STATES = [...articleRoute, ...articlePopupRoute];

@NgModule({
  imports: [PillpalServerSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ArticleComponent,
    ArticleDetailComponent,
    ArticleUpdateComponent,
    ArticleDeleteDialogComponent,
    ArticleDeletePopupComponent
  ],
  entryComponents: [ArticleComponent, ArticleUpdateComponent, ArticleDeleteDialogComponent, ArticleDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PillpalServerArticleModule {}
