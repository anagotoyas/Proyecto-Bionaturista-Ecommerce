import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Pedido } from './pedido.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {
  private apiBase: string = environment.apiBase;
  constructor( private http:HttpClient) {}

  getAllPedidos(){
    return this.http.get<Pedido[]>(`${this.apiBase}/pedidos`);
  }
  getMisPedidos(id: number){
    return this.http.get<any>(`${this.apiBase}/pedidos/listarPorIdUsuario?usuario=${id}`);
  }
  getPedidoById(idPedido:number){
    return this.http.get<Pedido[]>(`${this.apiBase}/pedidos/${idPedido}`);
  }
  
  listarPedidosPorIdUsuario(idUsuario: number, idPedido: number){
    return this.http.get(`${this.apiBase}/pedidos/${idUsuario}`);
  }
  
   
}