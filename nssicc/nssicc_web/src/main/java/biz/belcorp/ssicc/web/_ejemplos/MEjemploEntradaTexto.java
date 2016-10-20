package biz.belcorp.ssicc.web._ejemplos;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import biz.belcorp.ssicc.dao.model.EjemploProducto;
import biz.belcorp.ssicc.service._ejemplos.EjemploService;
import biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF;

@ManagedBean
@SessionScoped
public class MEjemploEntradaTexto extends MBaseSistemaAbstractJSF  {

  private static final long serialVersionUID = -2403138958014741654L;
  private String nombre;
  private String direccion; 
  private String mail;   
  private Date fecha;

	/**
	 * @return the nombre 
	 */
	@Size(min = 3, max = 40)
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	@NotNull
	@Size(min = 5, max = 50)
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
    
    private EjemploProducto producto;        
    private EjemploService ejemploService = (EjemploService)this.getBeanService("ssicc.ejemploService");

    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	public void setViewAtributes() {
        log.debug("post Construct MEjemploEntradaTexto");
        this.nombre = ejemploService.devuelveNombre(2);
    }
    
    /**
	 * Ir a la Pagina Anterior
	 * @return
	 */
	public String irPaginaAnterior() {
		return "ejemploEntradaTexto";
	}
	
	/**
	 * Ir a la Pagina Siguiente
	 * @return
	 */
	public String irPaginaSiguiente() {
		return "ejemploEntradaTextoParte2";
	}
	
	
   
 	/**
 	 * @param ejemploService
 	 */
 	public void setEjemploService(EjemploService ejemploService) {
		this.ejemploService = ejemploService;
	}

	
	/**
	 * @return the producto
	 */
	public EjemploProducto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(EjemploProducto producto) {
		this.producto = producto;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
