import { Component, OnInit } from '@angular/core';
import { CookieService } from '../../service/cookie.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  product: any;
  productId: string | null = '';

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

    console.log(`${this.productId}`);

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
    console.log(this.product);
  }

}
