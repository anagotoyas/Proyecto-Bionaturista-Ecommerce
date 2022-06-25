import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/shared/usuario.model';
import { UsuarioService } from 'src/app/shared/usuario.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})

export class LayoutComponent implements OnInit {

  nombre:any;
  isShown: boolean = false;
  isShown2: boolean = false;
  dataSource:Usuario
  constructor(
    private router: Router,
    private usuarioService: UsuarioService) { }

  ngOnInit(): void {

    if(sessionStorage.getItem('key') == null){
      console.log(sessionStorage.getItem('key'))
      this.isShown =! this.isShown;
      console.log(this.isShown);
    }
    
    else {
      
      this.isShown2 =! this.isShown2;
    }

    this.verUsuario()
  }
  irCarrito(){
    if(sessionStorage.getItem('key')==null){
      const ok = confirm('Debes iniciar sesión para poder ver los items de tu carrito de compras');
    if(ok){
      
      window.location.replace(`../login`)
    }
    }
    else{
      window.location.replace(`../carrito`);
    }
   
    

  }
  irPedidos(){
    if(sessionStorage.getItem('key')==null){
      const ok = confirm('Debes iniciar sesión para poder ver tus pedidos');
    if(ok){
      
      window.location.replace(`../login`)
    }
    }
    else{
      window.location.replace(`../pedidos/mispedidos`);
    }
   
    

  }

  buscarProducto(){
    var inputValue = (<HTMLInputElement>document.getElementById('nombreProducto')).value;
    inputValue = inputValue.toLowerCase();
    window.location.replace(`../${inputValue}/buscarPorNombre`);
    
  }

  verUsuario(){
    if(sessionStorage.getItem('key')!=null || sessionStorage.getItem('key')!=undefined|| sessionStorage.getItem('key')!=''){
      this.usuarioService.obtenerUsuarioPorID(Number(sessionStorage.getItem('key'))).subscribe((data:any)=>{
        this.dataSource=data['body'];
        this.nombre=this.dataSource.nombreUsuario;
       
        
      })

    }
  
    
  }
  cerrarSesion(){
    const ok = confirm('¿Estás seguro de cerrar sesión?');
    if(ok){
      sessionStorage.clear();
      window.location.replace(`../`)
    }
   
   
  }
}
