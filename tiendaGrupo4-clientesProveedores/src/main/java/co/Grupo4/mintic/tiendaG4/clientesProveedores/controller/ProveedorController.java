package co.Grupo4.mintic.tiendaG4.clientesProveedores.controller;

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

import co.Grupo4.mintic.tiendaG4.clientesProveedores.model.Proveedor;
import co.Grupo4.mintic.tiendaG4.clientesProveedores.repository.ProveedorRepository;

//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
	
	@Autowired
	ProveedorRepository proveedorrepo;
	
	@GetMapping("/buscarproveedores")
	public ResponseEntity<List<Proveedor>> getAllProveedores(@RequestParam(required = false) String nit){
		try {
			List<Proveedor> proveedores = new ArrayList<Proveedor>();
			
			if(nit == null) {
				proveedorrepo.findAll().forEach(proveedores::add);
			}else {
				proveedorrepo.findByNit(nit).forEach(proveedores::add);
			}
			
			if(proveedores.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(proveedores, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscarproveedores/{nit}")
	public ResponseEntity<List<Proveedor>> findByNit(@PathVariable("nit")String nit){
		try {
			List<Proveedor> proveedores = proveedorrepo.findByNit(nit);
			
			if(proveedores.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(proveedores, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping("/crearproveedor")
	public ResponseEntity<Proveedor> createProveedor(@RequestBody Proveedor proveedor){
		try {
			Proveedor _proveedor = proveedorrepo.save(new Proveedor(proveedor.getNit(),proveedor.getNombre(),
					proveedor.getDireccion(),proveedor.getTelefono(),proveedor.getCiudad()));
			return new ResponseEntity<>(_proveedor,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/editarproveedor/{id}")
	public ResponseEntity<Proveedor> updateProveedor(@PathVariable("id") String id,@RequestBody Proveedor proveedor){
		
		Optional<Proveedor> proveedorData = proveedorrepo.findById(id);
		
		if(proveedorData.isPresent()) {
			Proveedor _proveedor = proveedorData.get();
			_proveedor.setNit(proveedor.getNit());
			_proveedor.setNombre(proveedor.getNombre());
			_proveedor.setDireccion(proveedor.getDireccion());
			_proveedor.setTelefono(proveedor.getTelefono());
			_proveedor.setCiudad(proveedor.getCiudad());
			return new ResponseEntity<>(proveedorrepo.save(_proveedor),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteAllProveedores(){
		try {
			proveedorrepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProveedor(@PathVariable("id") String id) {
	  try {
	    proveedorrepo.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}	
	
}
