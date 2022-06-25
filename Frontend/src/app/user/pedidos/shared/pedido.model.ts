import { Usuario } from "src/app/shared/usuario.model";
import { estadoPedido } from "./estado-pedido.model";

export class Pedido{
    idPedido: number;
    usuario: Usuario;
    montoPago: number;
    fechaPedido: Date;
    fechaEntrega: Date;
    metodoPago: String;
    estadoPedido: estadoPedido;
    costoEnvio: number;
    subTotal: number;
  }