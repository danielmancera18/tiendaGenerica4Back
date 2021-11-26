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

import co.Grupo4.mintic.tiendaG4.clientesProveedores.model.Cliente;
import co.Grupo4.mintic.tiendaG4.clientesProveedores.repository.ClienteRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienterepo;
	
	@GetMapping("/buscarclientes")
	public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam(required = false) String cedula){
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			
			if(cedula == null) {
				clienterepo.findAll().forEach(clientes::add);
			}else {
				clienterepo.findByCedula(cedula).forEach(clientes::add);
			}
			
			if(clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/buscarclientes/{cedula}")
	public ResponseEntity<List<Cliente>> findByCedula(@PathVariable("cedula")String cedula){
		try {
			List<Cliente> clientes = clienterepo.findByCedula(cedula);
			
			if(clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
			
		}catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping("/crearcliente")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
		try {
			Cliente _cliente = clienterepo.save(new Cliente(cliente.getCedula(),cliente.getNombreCompleto(),
					cliente.getDireccion(),cliente.getTelefono(),cliente.getCorreoElectronico()));
			return new ResponseEntity<>(_cliente,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/editarcliente/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") String id,@RequestBody Cliente cliente){
		
		Optional<Cliente> clienteData = clienterepo.findById(id);
		
		if(clienteData.isPresent()) {
			Cliente _cliente = clienteData.get();
			_cliente.setCedula(cliente.getCedula());
			_cliente.setNombreCompleto(cliente.getNombreCompleto());
			_cliente.setDireccion(cliente.getDireccion());
			_cliente.setTelefono(cliente.getTelefono());
			_cliente.setCorreoElectronico(cliente.getCorreoElectronico());
			return new ResponseEntity<>(clienterepo.save(_cliente),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteAllClientes(){
		try {
			clienterepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("id") String id) {
	  try {
	    clienterepo.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
}
