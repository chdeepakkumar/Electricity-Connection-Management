import { Component, OnInit, ViewChild } from '@angular/core';
import { ApexAxisChartSeries, ApexChart, ApexNonAxisChartSeries, ApexResponsive, ApexTitleSubtitle, ApexXAxis, ChartComponent } from 'ng-apexcharts';
import { ConnReq } from '../model/ConnReq';

export type ChartOptionsStatus = {
  series: ApexAxisChartSeries;
  chart: ApexChart;
  xaxis: ApexXAxis;
  title: ApexTitleSubtitle;
};

export type ChartOptionsCategory = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  labels: any;
  title: ApexTitleSubtitle;
};

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.css']
})
export class StatsComponent implements OnInit {
  @ViewChild("chart") chartStatus!: ChartComponent;
  public chartOptionsStatus!: ChartOptionsStatus;

  @ViewChild("chart") chartCategory!: ChartComponent;
  public chartOptionsCategory!: ChartOptionsCategory;
  
  dataBasedOnStatus: Array<number> = [0,0,0,0];
  dataBasedOnCategory = [0,0];

  connReqs: ConnReq[] = [];

  constructor() { }
   

  ngOnInit(): void {
    this.connReqs = JSON.parse(localStorage.getItem("Data") || "")
    this.processData();
    this.chartOptionsStatus = {
      series: [
        {
          name: "Status",
          data: this.dataBasedOnStatus
        }
      ],
      chart: {
        height: 350,
        type: "bar"
      },
      title: {
        text: "Stats based on Status"
      },
      xaxis: {
        categories: ["Approved", "Pending", "Rejected", "Released"]
      }
    };
    this.chartOptionsCategory = {
      series: this.dataBasedOnCategory,
      chart: {
        height: 350,
        type: "donut"
      },
      labels: ["Commercial", "Residential"],
      title: {
        text: "Stats based on Category"
      }
    };
  }

  processData() {
    for(let connReq of this.connReqs){
      if(connReq.category == "Commercial") this.dataBasedOnCategory[0] += 1;
      if(connReq.category == "Residential") this.dataBasedOnCategory[1] += 1;
      if(connReq.status == "Approved") this.dataBasedOnStatus[0] += 1;
      if(connReq.status == "Pending") this.dataBasedOnStatus[1] += 1;
      if(connReq.status == "Rejected") this.dataBasedOnStatus[2] += 1;
      if(connReq.status == "Released") this.dataBasedOnStatus[3] += 1;
    }
  }

  ngOnDestroy(): void {
    localStorage.removeItem("Data");
  }

}
