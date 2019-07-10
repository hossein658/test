import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IBank, Bank } from 'app/shared/model/bank.model';
import { BankService } from './bank.service';

@Component({
  selector: 'jhi-bank-update',
  templateUrl: './bank-update.component.html'
})
export class BankUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    branchCode: [],
    branchName: []
  });

  constructor(protected bankService: BankService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ bank }) => {
      this.updateForm(bank);
    });
  }

  updateForm(bank: IBank) {
    this.editForm.patchValue({
      id: bank.id,
      branchCode: bank.branchCode,
      branchName: bank.branchName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const bank = this.createFromForm();
    if (bank.id !== undefined) {
      this.subscribeToSaveResponse(this.bankService.update(bank));
    } else {
      this.subscribeToSaveResponse(this.bankService.create(bank));
    }
  }

  private createFromForm(): IBank {
    return {
      ...new Bank(),
      id: this.editForm.get(['id']).value,
      branchCode: this.editForm.get(['branchCode']).value,
      branchName: this.editForm.get(['branchName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBank>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
