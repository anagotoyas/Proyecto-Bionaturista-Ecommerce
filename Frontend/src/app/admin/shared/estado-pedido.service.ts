import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { EstadoPedido } from './estadoPedido.model';

@Injectable({
  providedIn: 'root'
})
export class EstadoPedidoService {
  private apiBase:string = environment.apiBase;
  constructor(private http:HttpClient) { }

  getAllEstadoPedidos(){
    return this.http.get<EstadoPedido[]>(`${this.apiBase}/estados`)
  }
}
