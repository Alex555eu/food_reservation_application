import { CookieService } from './../../service/cookie.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [ FormsModule, CommonModule ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {

  user = {
    name: '',
    surname: '',
    email: '',
    role: ''
  };


  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ){}

  ngOnInit(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    this.http.get<any>(`http://localhost:9090/api/v1/data/user/`, { headers })
      .subscribe(data => {
        this.user.name = data.firstName;
        this.user.surname = data.lastName;
        this.user.email = data.emailAddress;
        this.user.role = data.authorities[0].authority;
      }, error => {
        if (error.status !== 200) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }



  onSubmit() {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    const body = { firstName: this.user.name, lastName: this.user.surname, emailAddress: this.user.email };
    this.http.put<any>(`http://localhost:9090/api/v1/data/user/`, body, { headers })
      .subscribe(data => {
      }, error => {
        if (error.status !== 200) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }


  logout() {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    this.http.post<any>(`http://localhost:9090/api/v1/auth/logout`, { headers })
      .subscribe(data => {
      }, error => {
        if (error.status !== 200) {
          
        } else {
          console.error('Error fetching products', error);
        }
      });
      //this.router.navigate(['/login']);
  }

}
