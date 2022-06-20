import { Component, OnInit } from '@angular/core';
import { _MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Pedido } from '../../shared/pedidos.model';
import { PedidosService } from '../../shared/pedidos.service';
@Component({
  selector: 'app-pedido-edit',
  templateUrl: './pedido-edit.component.html',
  styleUrls: ['./pedido-edit.component.css']
})
export class PedidoEditComponent implements OnInit {

  usuario: any;
  dataSource: any;
  dataSource2:any;
  fechaPedido: any;
  idPedido:any;
  nombre:any;
  displayedColumns: string[] = [ 'id','producto','cantidad','precio', 'Eliminar'];

  constructor(
    private pedidosService: PedidosService,
    private activeRoute: ActivatedRoute,
    private router: Router,
  ) { 
    
    this.idPedido=this.activeRoute.snapshot.paramMap.get('idPedido')
    
  }

  getPedido(){
    this.usuario = Number(sessionStorage.getItem('key'))
    
    this.pedidosService.verPedido(this.idPedido).subscribe((data:any)=>{
      this.dataSource = data['body'];
      this.nombre = data['body'].usuario.nombreUsuario;   
    })
  }

  ngOnInit(): void {
    this.getPedido();
  }

  editPedido(pedido:Pedido){
    console.log(pedido);
    this.pedidosService.editarPedido(pedido).subscribe(() => {
      this.router.navigate(['/admin/pedidos']);
    }, 
      (error:any) => {}
    );
  }
  

 



}

