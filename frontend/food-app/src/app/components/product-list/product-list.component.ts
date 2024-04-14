import { Component } from '@angular/core';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {
  products: any[];

  constructor() {
    this.products = [
      { id: 1, name: 'Product 1', price: 10.99 },
      { id: 2, name: 'Product 2', price: 20.99 },
      { id: 3, name: 'Product 3', price: 30.99 },
    ];
  }
}
