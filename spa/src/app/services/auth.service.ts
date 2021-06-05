import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import jwtDecode from 'jwt-decode';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private readonly url: string;

    constructor(private http: HttpClient,
                private cookieService: CookieService,
                private router: Router) {
        this.url = 'http://localhost:8080';
    }

    auth(login: string, password: string): any {
        return this.http.post(this.url + '/auth', {
            login,
            password
        }, { observe: 'body', responseType: 'text' });
    }

    public setSession(token: string): void {
        this.cookieService.set('token', token);
        this.decodeTokenInfo(token);
    }

    decodeTokenInfo(token: string): void {
        const tokenInfo: any = jwtDecode(token);
        this.cookieService.set('login', tokenInfo.sub);
        this.cookieService.set('accessLevel', tokenInfo.auth);
        this.cookieService.set('exp', tokenInfo.exp);
    }

    signOut(): void {
        this.router.navigate(['/']);
        this.cookieService.delete('token');
        this.cookieService.delete('login');
        this.cookieService.delete('accessLevel');
        this.cookieService.delete('exp');
    }
}
