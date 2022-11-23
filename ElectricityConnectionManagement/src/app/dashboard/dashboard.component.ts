import { Component, OnInit, ViewChild } from '@angular/core';
import { ConnReq } from '../model/ConnReq';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { Router } from '@angular/router';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  dtOptions: DataTables.Settings = {};
  connReqs: ConnReq[] = [];
  connReqsOnScreen: ConnReq[] = [];
  search: string = "";
  searchDrpDwn: string = "ID";
  suggestions:Set<string> = new Set<string>();
  suggestedConnreqs: ConnReq[] = [];
  filterStart: string = "";
  filterEnd: string = "";

  // We use this trigger because fetching the list of persons can be quite long,
  // thus we ensure the data is fetched before rendering
  dtTrigger: Subject<any> = new Subject<any>();
  dtTriggerCopy: Subject<any> = new Subject<any>();

  @ViewChild(DataTableDirective, { static: false })
  dtElement!: DataTableDirective;

  constructor(private httpClient: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.dtOptions = {};
    this.connReqs = [];
    this.connReqsOnScreen = [];
    this.search = "";
    this.searchDrpDwn = "ID";
    this.filterStart = "";
    this.filterEnd = "";
    this.suggestions = new Set<string>();
    this.dtOptions = {
      pagingType: 'full_numbers',
      processing: true,
      searching: false
    };

  }

  ngAfterViewInit(): void {
    // this.dtTrigger.next(this.connReqsOnScreen);
    this.httpClient.get<ConnReq[]>("http://localhost:4444/requests").subscribe({
      next: (response) => {
        this.connReqs = response;
        this.connReqsOnScreen = response;
        this.dtTrigger.next(this.connReqs);
      },
      error: (error) => {
      }
    });
  }

  getSuggestions() {
    this.suggestions = new Set<string>();
    if(this.searchDrpDwn=="ID") {
      for(let connReq of this.connReqs) {
        if(connReq.id.toString().startsWith(this.search)) {
          this.suggestions.add(connReq.id.toString())
          if(this.suggestions.size == 10) break;
        }
      }
    }
    else if(this.searchDrpDwn=="Name") {
      for(let connReq of this.connReqs) {
        if(connReq.name.startsWith(this.search)) {
          this.suggestions.add(connReq.name)
          if(this.suggestions.size == 10) break;
        }
      }
    }
    else if(this.searchDrpDwn=="PIN") {
      for(let connReq of this.connReqs) {
        if(connReq.pinCode.startsWith(this.search)) {
          this.suggestions.add(connReq.pinCode)
          if(this.suggestions.size == 10) break;
        }
      }
    }
  }

  showRecord() {
    console.log(this.search)
    if(this.search == "") {
      this.connReqsOnScreen = this.connReqs;
      this.rerender();
      this.dtTrigger.next(this.connReqsOnScreen);
      return;
    }
    let searchItem: ConnReq[] = []
    if(this.searchDrpDwn=="ID"){
      for(let connReq of this.connReqs) {
        if(connReq.id.toString().startsWith(this.search)) 
          searchItem.push(connReq);
      }
    }
    else if(this.searchDrpDwn=="Name") {
      for(let connReq of this.connReqs) {
        if(connReq.name.startsWith(this.search))
          searchItem.push(connReq);
      }
    }
    else if(this.searchDrpDwn=="PIN") {
      for(let connReq of this.connReqs) {
        if(connReq.pinCode.startsWith(this.search))
          searchItem.push(connReq);
      }
    }
    this.connReqsOnScreen = searchItem
    this.rerender();
    this.dtTrigger.next(this.connReqsOnScreen);
  }

  filterData() {
    this.search = ""
    this.suggestions = new Set<string>();
    this.suggestedConnreqs = [];
    if (this.filterStart != "" && this.filterEnd != "") {
      this.httpClient.get<ConnReq[]>("http://localhost:4444/requests/"+this.filterStart+"/"+this.filterEnd).subscribe({
        next: (response) => {
          // this.rerender();
          this.connReqs = response;
          this.connReqsOnScreen = response;
          this.rerender();
          this.dtTrigger.next(this.connReqs);
        },
        error: (error) => {
          // this.rerender();
          this.connReqs = [];
          this.connReqsOnScreen = [];
          this.rerender();
          this.dtTrigger.next(this.connReqs);
        }
      });
    }
  }

  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first
      dtInstance.destroy();
      // Call the dtTrigger to rerender again
    });
  }

  getConnDetails(connReq: ConnReq){
    localStorage.setItem("Request", JSON.stringify(connReq));
    this.router.navigateByUrl("/connreq");
  }

  saveDataInLocal() {
    localStorage.setItem("Data", JSON.stringify(this.connReqs));
    this.router.navigateByUrl("/stats");
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

}
