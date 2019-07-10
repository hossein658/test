import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { ICustomer, Customer } from 'app/shared/model/customer.model';
import { CustomerService } from './customer.service';
import { IBank } from 'app/shared/model/bank.model';
import { BankService } from 'app/entities/bank';

@Component({
  selector: 'jhi-customer-update',
  templateUrl: './customer-update.component.html'
})
export class CustomerUpdateComponent implements OnInit {
  isSaving: boolean;

  banks: IBank[];
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    fatherNmae: [],
    nationalCode: [],
    birthDate: [],
    idNumber: [],
    genderType: [],
    address: [],
    bankId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected customerService: CustomerService,
    protected bankService: BankService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ customer }) => {
      this.updateForm(customer);
    });
    this.bankService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IBank[]>) => mayBeOk.ok),
        map((response: HttpResponse<IBank[]>) => response.body)
      )
      .subscribe((res: IBank[]) => (this.banks = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(customer: ICustomer) {
    this.editForm.patchValue({
      id: customer.id,
      firstName: customer.firstName,
      lastName: customer.lastName,
      fatherNmae: customer.fatherNmae,
      nationalCode: customer.nationalCode,
      birthDate: customer.birthDate,
      idNumber: customer.idNumber,
      genderType: customer.genderType,
      address: customer.address,
      bankId: customer.bankId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const customer = this.createFromForm();
    if (customer.id !== undefined) {
      this.subscribeToSaveResponse(this.customerService.update(customer));
    } else {
      this.subscribeToSaveResponse(this.customerService.create(customer));
    }
  }

  private createFromForm(): ICustomer {
    return {
      ...new Customer(),
      id: this.editForm.get(['id']).value,
      firstName: this.editForm.get(['firstName']).value,
      lastName: this.editForm.get(['lastName']).value,
      fatherNmae: this.editForm.get(['fatherNmae']).value,
      nationalCode: this.editForm.get(['nationalCode']).value,
      birthDate: this.editForm.get(['birthDate']).value,
      idNumber: this.editForm.get(['idNumber']).value,
      genderType: this.editForm.get(['genderType']).value,
      address: this.editForm.get(['address']).value,
      bankId: this.editForm.get(['bankId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICustomer>>) {
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
}
