import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ConnReq } from '../model/ConnReq';

@Component({
  selector: 'app-connection-request',
  templateUrl: './connection-request.component.html',
  styleUrls: ['./connection-request.component.css']
})
export class ConnectionRequestComponent implements OnInit {

  connReq!: ConnReq;
  connectionForm!: FormGroup

  constructor(private formBuilder: FormBuilder, private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.connReq = JSON.parse(localStorage.getItem("Request")||"");
    console.log(this.connReq);
    this.connectionForm = this.formBuilder.group({
      name: [this.connReq.name, Validators.required],
      gender: [this.connReq.gender, Validators.required],
      district: [this.connReq.district, Validators.required],
      state: [this.connReq.state, Validators.required],
      pinCode: [this.connReq.pinCode, Validators.required],
      govtId: [this.connReq.govtId, Validators.required],
      idNumber: [this.connReq.idNumber, Validators.required],
      ownership: [this.connReq.ownership, Validators.required],
      category: [this.connReq.category, Validators.required],
      loadAppl: [this.connReq.loadAppl, [Validators.required, Validators.max(200)]],
      status: [this.connReq.status, Validators.required],
      reviewerId: [this.connReq.reviewerId, Validators.required],
      reviewerName: [this.connReq.reviewerName, Validators.required],
      comments: [this.connReq.comments, Validators.required],
      dateOfApplication: [this.connReq.dateOfApplication, Validators.required],
    })
  }

  update(){
    let id = this.connReq.id;
    let dateOfApplication = this.connReq.dateOfApplication;
    let dateOfApproval = this.connReq.dateOfApproval;
    this.connReq = this.connectionForm.value
    this.connReq.modifiedDate = new Date();
    if(this.connReq.status == "Approved")
      this.connReq.dateOfApproval = new Date();
    else
      this.connReq.dateOfApproval = dateOfApproval;
    this.connReq.id = id;
    this.connReq.dateOfApplication = dateOfApplication;
    console.log(this.connReq);
    this.httpClient.put<any>("http://localhost:4444/requests",this.connReq).subscribe({
      next: 
        (response)=>{
          window.prompt(response.message)
          this.router.navigateByUrl("/dashboard")
        }
    });
  }

  delete(){
    this.httpClient.delete<any>("http://localhost:4444/requests/"+this.connReq.id).subscribe({
      next: 
        (response)=>{
          window.prompt(response.message)
          this.router.navigateByUrl("/dashboard")
        }
    });
  }

  ngOnDestroy(): void {
    localStorage.removeItem("Request");
  }

}
