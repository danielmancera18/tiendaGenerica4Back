package co.Grupo4.mintic.tiendaG4.productos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.Grupo4.mintic.tiendaG4.productos.model.Producto;
import co.Grupo4.mintic.tiendaG4.productos.repository.ProductoRepository;

@CrossOrigin(origins = "*")
@RestController
//@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	ProductoRepository productorepo;
	
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> getAllProductos(@RequestParam(required = false) String codigo){
		try {
			List<Producto> productos = new ArrayList<Producto>();
			
			if(codigo == null) {
				productorepo.findAll().forEach(productos::add);
			}else {
				productorepo.findByCodigo(codigo).forEach(productos::add);
			}
			
			if(productos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(productos,HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/productos/{codigo}")
	public ResponseEntity<List<Producto>> findByCodigo(@PathVariable("codigo") String codigo){
		try {
			List<Producto> productos = productorepo.findByCodigo(codigo);
			if(productos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(productos,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@PostMapping("/productos")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
		
		try {
			Producto _producto = productorepo.save(new Producto(producto.getCodigo(),producto.getNit(),
					producto.getNombre(),producto.getPrecioCompra(),producto.getIvaCompra(),
					producto.getPrecioVenta()));
			return new ResponseEntity<>(_producto,HttpStatus.CREATED);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/productos/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable("id") String id ,@RequestBody Producto producto){
		Optional<Producto> productoData = productorepo.findById(id);
		
		if(productoData.isPresent()) {
			Producto _producto = productoData.get();
			_producto.setCodigo(producto.getCodigo());
			_producto.setNit(producto.getNit());
			_producto.setNombre(producto.getNombre());
			_producto.setPrecioCompra(producto.getPrecioCompra());
			_producto.setIvaCompra(producto.getIvaCompra());
			_producto.setPrecioVenta(producto.getPrecioVenta());
			return new ResponseEntity<>(productorepo.save(_producto),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/productos/delete")
	public ResponseEntity<HttpStatus> deleteAllProductos(){
		try {
			productorepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
}

















