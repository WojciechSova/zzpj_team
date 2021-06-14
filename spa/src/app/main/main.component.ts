import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from "../services/auth.service";

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.less']
})
export class MainComponent implements OnInit {

    constructor(private router: Router,
                private authService: AuthService) {
    }

    ngOnInit(): void {
    }

    logOut(): void {
        this.authService.signOut();
        this.router.navigate(['']);
    }
}
