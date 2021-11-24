package co.Grupo4.mintic.tiendaG4.productos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
public class Producto {
	
	@Id
	private String id;

	private String codigo;
	private String nombre;
	private String nit;
	private double precioCompra,ivaCompra,precioVenta;
	
	//===================================================== constructors
	public Producto() {
	}


	public Producto(String codigo, String nit, String nombre, double precioCompra, double ivaCompra,
			double precioVenta) 
	{
		super();
		this.codigo = codigo;
		this.nit = nit;
		this.nombre = nombre;
		this.precioCompra = precioCompra;
		this.ivaCompra = ivaCompra;
		this.precioVenta = precioVenta;
	} 
	//===================================================== methods

	public String getId() {
		return id;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getNit() {
		return nit;
	}
	public String getNombre() {
		return nombre;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public double getIvaCompra() {
		return ivaCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}


	public void setId(String id) {
		this.id = id;
	}
	//=====================================================

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public void setIvaCompra(double ivaCompra) {
		this.ivaCompra = ivaCompra;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	//===================================================== string

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", nit=" + nit + ", nombre=" + nombre + ", precioCompra="
				+ precioCompra + ", ivaCompra=" + ivaCompra + ", precioVenta=" + precioVenta + "]";
	}
	
}