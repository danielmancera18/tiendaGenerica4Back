package co.Grupo4.mintic.tiendaG4.clientesProveedores;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TiendaGrupo4ClientesProveedoresApplication.class);
	}

}
