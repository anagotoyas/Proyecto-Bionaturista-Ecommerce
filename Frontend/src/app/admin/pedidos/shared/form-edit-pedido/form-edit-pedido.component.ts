import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { _MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Producto } from 'src/app/admin/products/shared/product.model';
import { EstadoPedidoService } from 'src/app/admin/shared/estado-pedido.service';
import { EstadoPedido } from 'src/app/admin/shared/estadoPedido.model';
import { Pedido } from 'src/app/admin/shared/pedidos.model';
import { PedidosService } from 'src/app/admin/shared/pedidos.service';
import { InfoEnvioModel } from 'src/app/user/shoppingCart/shared/infoEnvio.model';
import { ShoppingCartService } from 'src/app/user/shoppingCart/shared/shopping-cart.service';


@Component({
  selector: 'app-form-edit-pedido',
  templateUrl: './form-edit-pedido.component.html',
  styleUrls: ['./form-edit-pedido.component.css']
})
export class FormEditPedidoComponent implements OnInit {
  
  form:FormGroup;
  infoEnvio: InfoEnvioModel;
  usuario: any;
  subtotal: any;
  estadoPedido:any;
  metodoPago:any;
  montoPago:any;
  dataSource2: _MatTableDataSource<Producto>
  fechaEntrega = new Date();
  fechaPedido: any;
  user: any;
  pedidito: Pedido;
  nombre:any;
  idPedido:any;
  estados:EstadoPedido[];
  productosPedido: Producto[];
  displayedColumns: string[] = [ 'id','producto','cantidad','precio'];

  @Input() pedido: Pedido = new Pedido();
  @Output() onSubmit: EventEmitter<any> = new EventEmitter();
  constructor(private formBuilder: FormBuilder,
    private pedidosService: PedidosService,
    private activeRoute: ActivatedRoute,
    private estadoPedidoService: EstadoPedidoService,
    private carritoService: ShoppingCartService,
    private router: Router) { 
      
    }


    getPedido(){
      const params = this.activeRoute.snapshot.params;
      this.idPedido = params['idPedido']

      
      this.pedido = new Pedido();
      this.getProductos(this.idPedido);

      this.pedidosService.verPedido(this.idPedido).subscribe((data:any)=>{
        this.pedido = data['body'];
        this.usuario = data['body'].usuario;
        this.infoEnvio = data['body'].infoEnvio;
        console.log(this.infoEnvio);
        this.nombre = data['body'].usuario.nombreUsuario;
        this.fechaPedido = data['body'].fechaPedido;
        this.productosPedido = data['body'].productosPedido;
        console.log(this.productosPedido)
       
      })
    }

 

  ngOnInit(): void {
    this.getPedido();
    this.getEstadosPedido();
    
    this.form = this.formBuilder.group({
      idPedido: [
        this.pedido.idPedido = Number(this.idPedido),
      ],
      usuario:[
        this.pedido.usuario = this.pedido.usuario
      ],
      infoEnvio: [
        this.pedido.infoEnvio = this.pedido.infoEnvio
      ],
      montoPago: [
        this.pedido.montoPago = this.pedido.montoPago,
      ],
      fechaPedido: [
        this.pedido.fechaPedido,
      ],
      fechaEntrega: [ 
        this.pedido.fechaEntrega,
      ],
      metodoPago: [
        this.pedido.metodoPago , 
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(100),
        ],
      ],
      estadoPedido: [
        this.pedido.estadoPedido ,
        [
          Validators.required,
        ],
      ],
      costoEnvio: [
        this.pedido.costoEnvio = this.pedido.costoEnvio,
        [
          Validators.required,
        ],
      ],
      subtotal: [
        this.pedido.subtotal = this.pedido.subtotal,
      ],
      
    });
  }

  update(){
    this.onSubmit.emit(this.form.value);
  
  }
  
  getEstadosPedido(){
    this.estadoPedidoService.getAllEstadoPedidos().subscribe((data:any) => {
      this.estados = data['body'];
    })
  }

  

  getProductos(idPedido:number){
    this.pedidosService.verProductos(idPedido).subscribe((data:any)=>{
      this.dataSource2=data
      console.log(this.dataSource2);
    })
  }


}