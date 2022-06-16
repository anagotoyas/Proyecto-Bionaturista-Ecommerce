import { Component, OnInit } from '@angular/core';
import { __core_private_testing_placeholder__ } from '@angular/core/testing';
import { MatTableDataSource } from '@angular/material/table';
import { EstadoPedidoService } from '../../shared/estado-pedido.service';
import { EstadoPedido } from '../../shared/estadoPedido.model';
import { Pedido } from '../../shared/pedidos.model';
import { PedidosService } from '../../shared/pedidos.service';

@Component({
  selector: 'app-pedidos-list',
  templateUrl: './pedidos-list.component.html',
  styleUrls: ['./pedidos-list.component.css']
})
export class PedidosListComponent implements OnInit {
  displayedColumns: string[] = [ 'pedido', 'cliente','fecha', 'estado', 'acciones'];
  dataSource: MatTableDataSource<Pedido>;

  cantidadPedidos:0
  estados: EstadoPedido[];
  constructor(
    private pedidosService: PedidosService,
    private estadoPedido: EstadoPedidoService) { }

  ngOnInit(): void {
    this.getPedidos();
  }

  getPedidos(){
    this.pedidosService.getAllPedidos().subscribe((data:any) => {
      this.dataSource = new MatTableDataSource(data['body']);
      this.cantidadPedidos=data['body'].length;
      
    })
  }
  getEstadosPedido(){
    this.estadoPedido.getAllEstadoPedidos().subscribe((data:any)=>{
      this.estados=data['body']
      console.log(this.estados);
    })
  }
  verPedido(idPedido:any){
   console.log(idPedido);
    window.location.replace(`admin/pedidos/${idPedido}`);
  }

  

}
