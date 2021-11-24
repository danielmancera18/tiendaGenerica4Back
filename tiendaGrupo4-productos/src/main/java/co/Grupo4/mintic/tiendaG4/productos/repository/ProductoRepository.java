package co.Grupo4.mintic.tiendaG4.productos.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.Grupo4.mintic.tiendaG4.productos.model.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {
	List<Producto> findByCodigo(String codigo);
}
