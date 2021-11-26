package co.Grupo4.mintic.tiendaG4.clientesProveedores.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "proveedores")
public class Proveedor {
	
	@Id
	private String id;
	
	private String nit;
	private String nombre;
	private String direccion;
	private String telefono;
	private String ciudad;
	
	//===================================================== constructor
	public Proveedor() {
	}
	
	public Proveedor(String nit, String nombre, String direccion, String telefono, String ciudad) {
		super();
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.ciudad = ciudad;
	}	
	//===================================================== methods
	public String getId() {
		return id;
	}
	public String getNit() {
		return nit;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getCiudad() {
		return ciudad;
	}
	//=====================================================
	public void setId(String id) {
		this.id = id;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	//===================================================== string
	@Override
	public String toString() {
		return "Proveedor [id=" + id + ", nit=" + nit + ", nombre=" + nombre + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", ciudad=" + ciudad + "]";
	}
	
}
