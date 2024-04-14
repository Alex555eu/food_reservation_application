import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  login() {
    // Perform login logic here (e.g., send login request to backend)
    console.log('Username:', this.username);
    console.log('Password:', this.password);
  }
}
