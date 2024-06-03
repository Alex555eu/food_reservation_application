import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { CookieService } from '../../service/cookie.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ FormsModule, CommonModule ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  name: string = '';
  surname: string = '';
  email: string = '';
  password: string = '';
  repeatPassword: string = '';
  errorOccurred: boolean = false;

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ){}

  register() {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });

    if (this.password !== this.repeatPassword) {
      this.errorOccurred = true;
      return;
    }

    const body = { firstName: this.name, lastName: this.surname, emailAddress: this.email, password: this.password };
    this.http.post<any>(`http://localhost:9090/api/v1/auth/register`, body, { headers })
      .subscribe(data => {
        this.router.navigate(['/products']);
      }, error => {
        if (error.status !== 200) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }

}
