import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICustomerAccount, CustomerAccount } from 'app/shared/model/customer-account.model';
import { CustomerAccountService } from './customer-account.service';
import { IBank } from 'app/shared/model/bank.model';
import { BankService } from 'app/entities/bank';
import { ICustomer } from 'app/shared/model/customer.model';
import { CustomerService } from 'app/entities/customer';

@Component({
  selector: 'jhi-customer-account-update',
  templateUrl: './customer-account-update.component.html'
})
export class CustomerAccountUpdateComponent implements OnInit {
  isSaving: boolean;

  banks: IBank[];

  customers: ICustomer[];

  editForm = this.fb.group({
    id: [],
    accountNumber: [],
    status: [],
    accountType: [],
    balance: [],
    bankId: [],
    customerId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected customerAccountService: CustomerAccountService,
    protected bankService: BankService,
    protected customerService: CustomerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customerAccount }) => {
      this.updateForm(customerAccount);
    });
    this.bankService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBank[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBank[]>) => response.body)
      )
      .subscribe((res: IBank[]) => (this.banks = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.customerService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICustomer[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICustomer[]>) => response.body)
      )
      .subscribe((res: ICustomer[]) => (this.customers = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(customerAccount: ICustomerAccount) {
    this.editForm.patchValue({
      id: customerAccount.id,
      accountNumber: customerAccount.accountNumber,
      status: customerAccount.status,
      accountType: customerAccount.accountType,
      balance: customerAccount.balance,
      bankId: customerAccount.bankId,
      customerId: customerAccount.customerId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customerAccount = this.createFromForm();
    if (customerAccount.id !== undefined) {
      this.subscribeToSaveResponse(this.customerAccountService.update(customerAccount));
    } else {
      this.subscribeToSaveResponse(this.customerAccountService.create(customerAccount));
    }
  }

  private createFromForm(): ICustomerAccount {
    return {
      ...new CustomerAccount(),
      id: this.editForm.get(['id']).value,
      accountNumber: this.editForm.get(['accountNumber']).value,
      status: this.editForm.get(['status']).value,
      accountType: this.editForm.get(['accountType']).value,
      balance: this.editForm.get(['balance']).value,
      bankId: this.editForm.get(['bankId']).value,
      customerId: this.editForm.get(['customerId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomerAccount>>) {
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

  trackBankById(index: number, item: IBank) {
    return item.id;
  }

  trackCustomerById(index: number, item: ICustomer) {
    return item.id;
  }
}
