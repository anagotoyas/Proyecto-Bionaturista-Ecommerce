import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../shared/product.model';
import { ProductService } from '../shared/product.service';
import { MatIconModule } from '@angular/material/icon';


@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  dataSource: Product[];

  constructor(private activeRoute: ActivatedRoute, private productService: ProductService) { }

  msg: any;
  producto: any;

  ngOnInit(): void {
    this.buscarProducto();

  }

  buscarProducto(){
    const params = this.activeRoute.snapshot.params;
    this.productService.buscarProducto(params['nombreP']).subscribe((data:any) => {
      this.dataSource = data['body'];
      this.producto = params['nombreP'];
      console.log(data['body']);
      

      if(data['body'].length==0){
        this.msg="No se han encontrado resultados de su búsqueda.";
      }

    })
  }

  mover(idProducto:number){
    window.location.replace(`user/${idProducto}/view`);
  }

}
