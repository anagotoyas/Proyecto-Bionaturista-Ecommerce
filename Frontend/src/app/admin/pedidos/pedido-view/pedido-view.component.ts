import { Component, OnInit } from '@angular/core';
import { _MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { Producto } from '../../products/shared/product.model';
import { Pedido } from '../../shared/pedidos.model';
import { PedidosService } from '../../shared/pedidos.service';
import {InfoEnvioModel} from "../../../user/shoppingCart/shared/infoEnvio.model";

@Component({
  selector: 'app-pedido-view',
  templateUrl: './pedido-view.component.html',
  styleUrls: ['./pedido-view.component.css']
})
export class PedidoViewComponent implements OnInit {
  dataSource: Pedido;
  dataSource2: _MatTableDataSource<Producto>
  fechaEntrega:any;
  infoEnvio: InfoEnvioModel;
  displayedColumns: string[] = [ 'id','producto','cantidad','precio'];
  constructor(
    private pedidosService: PedidosService,
    private activeRoute: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.verPedido();

  }



  verPedido(){
    const params = this.activeRoute.snapshot.params;
    this.pedidosService.verPedido(params['idPedido']).subscribe((data:any)=>{
      this.dataSource = data['body'];
      console.log(data['body']);
      var date = data['body'].fechaPedido;
      this.infoEnvio = data['body'].infoEnvio
      var datearray = date.split("/");
      var newdate = datearray[1] + '/' + datearray[0] + '/' + datearray[2];
      this.fechaEntrega=this.addDays(newdate);
      this.verProductos(this.dataSource.idPedido);


    })
  }
   addDays(date:any) {
    var result = new Date(date);
    result.setDate(result.getDate() + 7);
    var fecha=(result.toLocaleDateString("en-US"));
    var datearray = fecha.split("/");
    var newdate = datearray[1] + '/' + datearray[0] + '/' + datearray[2];
    return newdate;
  }

  verProductos(idPedido:number){
    this.pedidosService.verProductos(idPedido).subscribe((data:any)=>{
      this.dataSource2=data
      console.log(this.dataSource2);
    })
  }



}
