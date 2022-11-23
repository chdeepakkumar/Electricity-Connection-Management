import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConnReq } from '../model/ConnReq';

@Component({
  selector: 'app-add-connection',
  templateUrl: './add-connection.component.html',
  styleUrls: ['./add-connection.component.css']
})
export class AddConnectionComponent implements OnInit {

  connReq!: ConnReq
  newConnectionForm!: FormGroup

  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.connReq = new ConnReq();
    this.newConnectionForm = this.formBuilder.group({
      name: [null, Validators.required],
      gender: [null, Validators.required],
      district: [null, Validators.required],
      state: [null, Validators.required],
      pinCode: [null, Validators.required],
      govtId: [null, Validators.required],
      idNumber: [null, Validators.required],
      ownership: [null, Validators.required],
      category: [null, Validators.required],
      loadAppl: [null, [Validators.required, Validators.max(200)]],
      status: [null, Validators.required],
      reviewerId: [null, Validators.required],
      reviewerName: [null, Validators.required],
      comments: [null, Validators.required],
    })
  }

  add(){
    this.connReq = this.newConnectionForm.value;
    this.connReq.dateOfApplication = new Date();
    this.connReq.modifiedDate = new Date();
    this.httpClient.post<any>("http://localhost:4444/requests",this.connReq).subscribe({
      next: 
        (response)=>{
          window.prompt(response.message)
          this.router.navigateByUrl("/dashboard")
        }
    });
  }

}
