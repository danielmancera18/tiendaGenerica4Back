package co.Grupo4.mintic.tiendaG4.clientesProveedores.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.Grupo4.mintic.tiendaG4.clientesProveedores.model.Proveedor;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
	
	List<Proveedor> findByNit(String nit);
}
