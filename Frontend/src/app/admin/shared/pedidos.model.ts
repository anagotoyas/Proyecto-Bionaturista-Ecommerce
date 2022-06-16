import { Usuario } from "src/app/shared/usuario.model"
import { EstadoPedido } from "./estadoPedido.model"

export class Pedido{
    idPedido: number
    usuario:Usuario
    montoPago:number
    fechaPedido: string
    fechaEntrega: string
    metodoPago:string
    estadoPedido:EstadoPedido
    costoEnvio:number
    subtotal: number
    
}