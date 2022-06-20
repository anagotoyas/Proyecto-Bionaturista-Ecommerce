import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PedidoEditComponent } from '../admin/pedidos/pedido-edit/pedido-edit.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { HeaderComponent } from './header/header.component';
import { ListProductsComponent } from './list-products/list-products.component';
import { PedidoViewComponent } from './pedidos/pedido-view/pedido-view.component';
import { PedidosListComponent } from './pedidos/pedidos-list/pedidos-list.component';
import { NewProductComponent } from './products/new-product/new-product.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { VentasComponent } from './ventas/ventas.component';

const routes: Routes = [

  {
    
    path:'',
    component: AdminHomeComponent,
    children: [
      {
        path: '',
        component: ListProductsComponent,
      },
      {
        path: 'ventas',
        component: VentasComponent,
      },
      {
        path: 'productos',
        component: ListProductsComponent
      },
      {
        path: 'productos/crear',
        component: NewProductComponent
      },
      {
        path: 'pedidos',
        component: PedidosListComponent
      },
      {
        path: 'pedidos/:idPedido',
        component: PedidoViewComponent
      },
      {
        path: 'pedidos/:idPedido/edit',
        component: PedidoEditComponent
      },
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
