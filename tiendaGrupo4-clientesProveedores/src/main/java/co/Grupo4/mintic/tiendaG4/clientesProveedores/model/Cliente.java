package co.Grupo4.mintic.tiendaG4.clientesProveedores.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Cliente {
	
	@Id
	private String id;
	
	private String cedula;
	private String nombreCompleto;
	private String direccion;
	private String telefono;
	private String correoElectronico;
	//===================================================== constructor
	public Cliente() {
	}
	
	public Cliente(String cedula, String nombreCompleto, String direccion, String telefono, 
			String correoElectronico) {
		super();
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
	}

	//===================================================== methods
	public String getId() {
		return id;
	}
	public String getCedula() {
		return cedula;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	//=====================================================
	public void setId(String id) {
		this.id = id;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	//===================================================== string
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cedula=" + cedula + ", nombreCompleto=" + nombreCompleto + ", direccion="
				+ direccion + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + "]";
	}

}
