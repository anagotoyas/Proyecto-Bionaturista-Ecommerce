import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
  irCarrito(){
    window.location.replace(`user/carrito`);

  }

  buscarProducto(){
    var inputValue = (<HTMLInputElement>document.getElementById('nombreProducto')).value;
    inputValue = inputValue.toLowerCase();
    window.location.replace(`user/${inputValue}/buscarPorNombre`);
    
  }
}
