import { Component, OnInit } from '@angular/core';
import { CookieService } from '../../service/cookie.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  product: any;
  productId: string | null = '';

  productInCartQuantity: number = 1;

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private router: Router,
    private activatedRouter: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.fetchProduct();
  }

  fetchProduct() {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });

    this.activatedRouter.paramMap.subscribe(params => {
      this.productId = params.get('id');
    });

    this.http.get<any>(`http://localhost:9090/api/v1/data/products/${this.productId}`, { headers })
      .subscribe(data => {
        this.product = data;
      }, error => {
        if (error.status !== 200) {
          this.router.navigate(['/login']);
        } else {
          console.error('Error fetching products', error);
        }
      });
  }

  addProductToCart(): void {
    const token = this.cookieService.getCookie('authToken');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }
  
    const headers = new HttpHeaders({
      'Authorization': `${token}`
    });
  
    const body = { productId: this.productId, quantity: this.productInCartQuantity };
  
    this.http.post<any>('http://localhost:9090/api/v1/data/order/add', body, { headers })
      .subscribe(
        (data) => {
          this.router.navigate(['/products']);
        },
        (error) => {
          if (error.status === 403) {
            console.error('Authorization error', error);
          } else {
            console.error('Unknown error occurred', error);
          }
        }
      );
  }
  

}
