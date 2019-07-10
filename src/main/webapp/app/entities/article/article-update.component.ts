import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IArticle, Article } from 'app/shared/model/article.model';
import { ArticleService } from './article.service';
import { ICustomerAccount } from 'app/shared/model/customer-account.model';
import { CustomerAccountService } from 'app/entities/customer-account';

@Component({
  selector: 'jhi-article-update',
  templateUrl: './article-update.component.html'
})
export class ArticleUpdateComponent implements OnInit {
  isSaving: boolean;

  customeraccounts: ICustomerAccount[];
  dateTransactionDp: any;

  editForm = this.fb.group({
    id: [],
    dateTransaction: [],
    debtorAmount: [],
    creditorAmount: [],
    transActionType: [],
    description: [],
    customerAccountId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected articleService: ArticleService,
    protected customerAccountService: CustomerAccountService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ article }) => {
      this.updateForm(article);
    });
    this.customerAccountService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICustomerAccount[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICustomerAccount[]>) => response.body)
      )
      .subscribe((res: ICustomerAccount[]) => (this.customeraccounts = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(article: IArticle) {
    this.editForm.patchValue({
      id: article.id,
      dateTransaction: article.dateTransaction,
      debtorAmount: article.debtorAmount,
      creditorAmount: article.creditorAmount,
      transActionType: article.transActionType,
      description: article.description,
      customerAccountId: article.customerAccountId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const article = this.createFromForm();
    if (article.id !== undefined) {
      this.subscribeToSaveResponse(this.articleService.update(article));
    } else {
      this.subscribeToSaveResponse(this.articleService.create(article));
    }
  }

  private createFromForm(): IArticle {
    return {
      ...new Article(),
      id: this.editForm.get(['id']).value,
      dateTransaction: this.editForm.get(['dateTransaction']).value,
      debtorAmount: this.editForm.get(['debtorAmount']).value,
      creditorAmount: this.editForm.get(['creditorAmount']).value,
      transActionType: this.editForm.get(['transActionType']).value,
      description: this.editForm.get(['description']).value,
      customerAccountId: this.editForm.get(['customerAccountId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IArticle>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackCustomerAccountById(index: number, item: ICustomerAccount) {
    return item.id;
  }
}
