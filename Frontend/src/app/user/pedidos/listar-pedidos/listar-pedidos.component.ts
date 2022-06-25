import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Usuario } from 'src/app/shared/usuario.model';

import { Pedido } from '../shared/pedido.model';
import { PedidoService } from '../shared/pedido.service';


@Component({
  selector: 'app-listar-pedidos',
  templateUrl: './listar-pedidos.component.html',
  styleUrls: ['./listar-pedidos.component.css']
})
export class PedidoListComponent implements OnInit {
  usuario =new Usuario();
  public prueba: Array<any> = [];
  estado: any;
  
  
  displayedColumns: string[] = ['nPedido', 'fechaPedido',  'importeTotal', 'estado'];
  dataSource: MatTableDataSource<Pedido>;
  

  constructor(private pedidoService: PedidoService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getMisPedidos();
  }

  getMisPedidos(){
    this.pedidoService.getMisPedidos(Number(sessionStorage.getItem('key'))).subscribe((data:any) => {
      this.dataSource = new MatTableDataSource(data['body']);
      console.log(Number(sessionStorage.getItem('idUsuario')));
      this.estado=data['body'].estadoPedido.nombreEstado;
    })

    /*
    this.historiaService.getMisHistorias(this.usuario).subscribe(data =>{
      console.log(this.usuario)
      console.log(data);
    })
    */
  }
  applyFilter(value: string){
    this.dataSource.filter = value.trim().toLowerCase();
  }

  mover(idProducto:number){
    window.location.replace(`${idProducto}/view`);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // dataSource1 : MatTableDataSource <Product>;
  // displayedColumns: string[] = ['idProducto','nombreP', 'precioP', 'Ver' ];
  // constructor(
  //   private productService: ProductService
  // ) { }

  // ngOnInit(): void {
  //   this.getAllProducts();
    
  // }

  // getAllProducts(){
  //   this.productService.getAllProducts().subscribe((data: any)=>{
  //     console.log(data['body']);
  //     this.dataSource1 = new MatTableDataSource (data['body']);
  //   })
  // }
  // applyFilter(value: string) {
  //   this.dataSource1.filter = value.trim().toLowerCase();
  // }

}
