package co.Grupo4.mintic.tiendaG4.clientesProveedores.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.Grupo4.mintic.tiendaG4.clientesProveedores.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
	
	List<Cliente> findByCedula(String cedula);
}
