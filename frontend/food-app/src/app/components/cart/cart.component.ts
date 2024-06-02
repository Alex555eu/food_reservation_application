import { CommonModule, Location } from '@angular/common';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { CookieService } from '../../service/cookie.service';


@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {

  info: string = '';
  items: any[] = [];

   stores: any[] = [];
   selectedStore: any;

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.fetch();
    this.fetchStores();
  }

  fetch(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    this.http.get<any>(`http://localhost:9090/api/v1/data/order/`, { headers })
      .subscribe(data => {
        this.items = data;
      }, error => {
        if (error.status === 403) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }


  fetchStores(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    this.http.get<any>(`http://localhost:9090/api/v1/data/store/`, { headers })
      .subscribe(data => {
        this.stores = data;
        this.selectedStore = this.stores[0].id;
      }, error => {
        if (error.status === 403) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }


  removeFromCart(orderItemId: string): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    this.http.delete<any>(`http://localhost:9090/api/v1/data/order/${orderItemId}`, { headers })
      .subscribe(data => {
        window.location.reload();
      }, error => {
        if (error.status === 403) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }


  clearCart(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    this.http.delete<any>(`http://localhost:9090/api/v1/data/order/`, { headers })
      .subscribe(data => {
        window.location.reload();
      }, error => {
        if (error.status === 403) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }


  getTotalPrice(): void {
    return this.items[0].orderDetails.total;
  }


  checkout(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
    const body = { storeId: this.selectedStore.id };
    this.http.put<any>(`http://localhost:9090/api/v1/data/order/make`, body, { headers })
      .subscribe(data => {
        window.location.reload();
      }, error => {
        if (error.status === 403) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
    alert('Proceeding to checkout!');
  }
}
