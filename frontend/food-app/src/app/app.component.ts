import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'food-app';
  showNavbar: boolean = true;

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((e) => {
      if (e instanceof NavigationEnd) {
        this.checkRoute(e.urlAfterRedirects);
      }
    })
  }

  checkRoute(url: string) {
    // Hide the navbar for the login/register route
    if (url === '/login' || url === '/register') {
      this.showNavbar = false;
    } else {
      this.showNavbar = true;
    }
  }
}
