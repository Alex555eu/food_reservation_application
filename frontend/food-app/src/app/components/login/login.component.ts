import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from '../../service/cookie.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  errorOccurred: boolean = false;

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }


  login(): void {
    const body = { email: this.email, password: this.password };

    this.http.post<any>('http://localhost:9090/api/v1/auth/validate', body)
      .subscribe(
        (data) => {
          this.cookieService.setCookie("authToken", data.token, 1);
          this.router.navigate(['/products']);
        },
        (error) => {
          if (error.status === 403) {
            this.errorOccurred = true;
          } else {
            console.error('Unknown error occurred', error);
          }
        }
      );
  }
}
