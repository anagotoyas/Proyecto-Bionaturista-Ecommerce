import { Usuario } from "src/app/shared/usuario.model"
import { InfoEnvioModel } from "src/app/user/shoppingCart/shared/infoEnvio.model"
import { Producto } from "../products/shared/product.model"
import { EstadoPedido } from "./estadoPedido.model"

export class Pedido{
    idPedido: number
    usuario:Usuario
    infoEnvio: InfoEnvioModel
    montoPago:number
    fechaPedido: string
    fechaEntrega: string
    metodoPago:string
    estadoPedido:EstadoPedido
    costoEnvio:number
    subtotal: number
    productosPedido: Producto[]
}