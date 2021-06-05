import { Component } from '@angular/core';
import { AuthService } from "../services/auth.service";
import { Router } from "@angular/router";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.less']
})
export class LoginComponent {

    login = '';
    password = '';
    error = false;

    constructor(private authService: AuthService,
                private router: Router) {

    }

    auth(): void {
        this.authService.auth(this.login, this.password).subscribe(
            (response: string) => {
                this.authService.setSession(response);
                this.router.navigateByUrl('/main');
            },
            () => this.error = true
        )
    }
}
