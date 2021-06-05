import { Injectable } from '@angular/core';
import {
    HttpBackend,
    HttpClient,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    private httpClient: HttpClient;

    constructor(handler: HttpBackend,
                private authService: AuthService,
                private cookieService: CookieService) {
        this.httpClient = new HttpClient(handler);
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (parseInt(this.cookieService.get('exp')) > new Date().getTime() / 1000) {
            const authReq = req.clone({setHeaders: {Authorization: 'Bearer ' + this.cookieService.get('token')}});
            return next.handle(authReq);
        }
        return next.handle(req);
    }
}
