import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddConnectionComponent } from './add-connection/add-connection.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth.guard';
import { ConnectionRequestComponent } from './connection-request/connection-request.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { StatsComponent } from './stats/stats.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'dashboard', component: DashboardComponent},
  {path:'addconn', component: AddConnectionComponent},
  {path:'stats', component: StatsComponent},
  {path:'connreq', component: ConnectionRequestComponent, canActivate: [AuthGuard]},
  {path:'**', redirectTo:''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
