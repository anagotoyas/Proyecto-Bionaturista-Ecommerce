import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() toggleSidebarForMe: EventEmitter<any> = new EventEmitter();

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  signOut(){
    const ok = confirm('¿Estás seguro de cerrar sesión?');
    if(ok){
      sessionStorage.clear();
      window.location.replace(`../`)
    }
  }

  toggleSidebar() {
    this.toggleSidebarForMe.emit();
  }

}
