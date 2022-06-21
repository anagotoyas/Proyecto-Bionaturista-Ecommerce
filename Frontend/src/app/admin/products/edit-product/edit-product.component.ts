import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductService} from "../shared/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../../user/products/shared/product.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Categoria} from "../../shared/categoria.model";
import {Compuesto} from "../../shared/compuesto.model";
import {CategoriaService} from "../../shared/categoria.service";
import {CompuestoService} from "../../shared/compuesto.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {

  form:FormGroup;

  product: Product = new Product();

  categorisazo: any;
  categorias: Categoria[];

  compuestazo:any;
  compuestos: Compuesto[];

  constructor(
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private router: Router,
    private categoriaService:CategoriaService,
    private compuestoService: CompuestoService,
    private activatedRoute: ActivatedRoute
  ) { }

  loadProduct(){
    var idProducto:number = parseInt(<string>this.activatedRoute.snapshot.paramMap.get('id'))
    this.productService.getProductById(idProducto).subscribe(
      (data:any)=>{
        this.product = data['body']
        console.log(data['body'])
      }
    )

  }

  getAllCategorias(){
    this.categoriaService.getAllCategorias().subscribe((data)=>{
      this.categorisazo=data;
      this.categorias=this.categorisazo.body;
      console.log(this.categorias);

    })
  }

  getAllCompuestos(){
    this.compuestoService.getAllCompuestos().subscribe((data)=>{
      console.log(data);


      this.compuestazo=data;
      this.compuestos=this.compuestazo.body;
      console.log(this.compuestos);

    });
  }

  ngOnInit(): void {
    this.loadProduct()
    this.getAllCategorias();
    this.getAllCompuestos();
    this.form=this.formBuilder.group({
      nombreP:[
        this.product.nombreP,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(70),
        ],
      ],
      descripcionP:[
        this.product.descripcionP,
        [
          Validators.maxLength(1000),
        ],
      ],
      precioP:[
        this.product.precioP,
        [
          Validators.required,
        ],
      ],
      stockP:[
        this.product.stockP,
        [
          Validators.required,
        ],
      ],
      compuesto:[
        this.product.compuesto,
        [
          Validators.required,
        ]
      ],
      categoria:[
        this.product.categoria,
        [
          Validators.required,
        ],
      ],
      imagenP:[
        this.product.imagenP,
      ],
    })
  }

  save(){
    console.log(this.product);
    this.productService.create(this.product).subscribe(
      ()=>{
        this.router.navigate(['admin/productos']);

        Swal.fire({
          icon: 'success',
          title: 'Producto actualizado',
          showConfirmButton: false,
          timer: 1500
        })

      },
      (error:any) => {

        Swal.fire({
          icon: 'error',
          title: 'No se ha podido actualizar :c',
          showConfirmButton: false,
          timer: 1500
        })

      }
    )
  }


}
