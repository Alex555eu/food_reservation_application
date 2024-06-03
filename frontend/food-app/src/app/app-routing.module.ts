import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ProductListComponent } from './components/product-list/product-list.component';
import { ProductComponent } from './components/product/product.component';
import { CartComponent } from './components/cart/cart.component';
import { UserComponent } from './components/user/user.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { AdminComponent } from './components/admin/admin.component';
import { RegisterComponent } from './components/register/register.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'products', component: ProductListComponent },
  { path: 'products/:id', component: ProductComponent },
  { path: 'cart', component: CartComponent },
  { path: 'user', component: UserComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'admin/order-list', component: OrderListComponent },
  { path: '', redirectTo: '/products', pathMatch: 'full' }, 
  { path: '**', redirectTo: '/products' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
