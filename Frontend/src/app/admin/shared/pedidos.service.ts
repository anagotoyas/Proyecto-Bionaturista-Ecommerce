import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Producto } from '../products/shared/product.model';
import { Pedido } from './pedidos.model';
@Injectable({
  providedIn: 'root'
})
export class PedidosService {

  private apiBase:string = environment.apiBase;

  constructor(private http:HttpClient) { }

  getAllPedidos(){
    return this.http.get<Pedido[]>(`${this.apiBase}/pedidos`)
  }

  verPedido(idPedido: number){
    return this.http.get<Pedido>(this.apiBase+`/pedidos/${idPedido}`)  
  }

  verProductos(idPedido:any){
    return this.http.get<Producto[]>(`${this.apiBase}/pedidos/${idPedido}/productos`)
  }

  editarPedido(pedido: Pedido){
    return this.http.put(`${this.apiBase}/pedidos`, pedido)
  }

}
