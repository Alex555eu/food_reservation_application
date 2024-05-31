import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { CookieService } from '../../service/cookie.service';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  products: any[] = [];

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });

    this.http.get<any[]>('http://localhost:9090/api/v1/data/products/all', { headers })
      .subscribe(data => {
        this.products = data;
      }, error => {
        if (error.status !== 200) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }
}
