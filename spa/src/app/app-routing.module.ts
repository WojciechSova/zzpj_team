import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { AccountsComponent } from './accounts/accounts.component';
import { AddAccountComponent } from './add-account/add-account.component';
import { EditUserComponent } from './edit-user/edit-user.component';

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'main', component: MainComponent },
    { path: 'accounts', component: AccountsComponent },
    { path: 'accounts/add', component: AddAccountComponent },
    { path: 'accounts/edit/:login', component: EditUserComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
