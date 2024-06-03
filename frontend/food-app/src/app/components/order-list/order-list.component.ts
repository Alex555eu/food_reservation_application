import { CommonModule } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CookieService } from '../../service/cookie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-list',
  standalone: true,
  imports: [ CommonModule ],
  templateUrl: './order-list.component.html',
  styleUrl: './order-list.component.css'
})
export class OrderListComponent implements OnInit {

  items: Item[] = [];

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
    this.http.get<any>(`http://localhost:9090/api/v1/admin/all-orders`, { headers })
      .subscribe(data => {
        this.items = data.map((item: any) => ({
          title: item.placementDate,
          details: item,
          store: item.store,
          expanded: false
        }));
      }, error => {
        if (error.status !== 200) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }

  toggleItem(item: any) {
    item.expanded = !item.expanded;
  }

  getDetailKeys(details: { [key: string]: string }): string[] {
    return Object.keys(details);
  }
  

}

interface Details {
  [key: string]: string;
}


interface Item {
  title: string;
  details: Details;
  store: Item;
  expanded: boolean;
}