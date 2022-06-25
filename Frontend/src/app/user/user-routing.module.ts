import { registerLocaleData } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { RegistrarUsuarioComponent } from '../registrar/registrar-usuario/registrar-usuario.component';
import { LayoutComponent } from './layout/layout.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductSearchComponent } from './products/product-search/product-search.component';
import { ProductViewComponent } from './products/product-view/product-view.component';
import { CartViewComponent } from './shoppingCart/cart-view/cart-view.component';
import {ProcesoCompraComponent} from "./shoppingCart/proceso-compra/proceso-compra.component";
import { PedidoListComponent } from './pedidos/listar-pedidos/listar-pedidos.component';

const routes: Routes = [
  {

    path:'',
    component: LayoutComponent,
    children: [
      {
        path: '',
        component: ProductListComponent,
      },
      

      {
        path: ':id/view',
        component: ProductViewComponent
      },
      {
        path: 'carrito',
        component: CartViewComponent
      },
      {
        path: 'pago',
        component: ProcesoCompraComponent
      },
      {
        path:':nombreP/buscarPorNombre',
        component: ProductSearchComponent
      },
    ]
  },
  {
    path: 'pedidos',
    component: LayoutComponent,
    children: [
      {
        path: 'mispedidos',
        component: PedidoListComponent,
      },
      
    ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
