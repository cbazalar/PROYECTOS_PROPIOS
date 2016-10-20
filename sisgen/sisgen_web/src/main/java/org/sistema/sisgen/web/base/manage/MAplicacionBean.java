package org.sistema.sisgen.web.base.manage;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.sistema.framework.dao.model.Base;
import org.sistema.framework.web.base.manage.MBaseAbstractJSF;


@ManagedBean
@ApplicationScoped
public class MAplicacionBean extends MBaseAbstractJSF {

  private static final long serialVersionUID = -2403138958014741453L;
  private List listaMeses;			
	
	
	@PostConstruct
	public void init() {
    	log.debug("Ingreso MAplicacion Init Post Constructor");
    	
    
        if (log.isInfoEnabled()) {
            log.info("drop-down initialization complete [OK]");
        }
        
        // obtenemos la lista de Meses
		this.cargarListaMeses();
				
		// Cargamos las constantes en el contexto de la aplicacion
		try{
			this.cargarConstantes();
			
	        if (log.isInfoEnabled()) {
	            log.info("Carga de constantes [OK]");
	        }
			
		}catch (Exception ex) {	
			log.error("ERROR (cargarConstantes):" + ex.getMessage());
		}
    }
	

	/**
	 * Carga Lista de Meses
	 */
	public void cargarListaMeses() {
		this.listaMeses = new ArrayList();
		
		for(int i=1; i<=12; i++)
		{
			Base mes = new Base();
			String codigo = Integer.valueOf(100+i).toString().substring(1);
					
			mes.setCodigo(codigo);
			mes.setDescripcion(this.getResourceMessage(String.format("form.mes-%s", codigo)));
			
			listaMeses.add(mes);
		}
	}
	
    /*
	  * Se encargar de cargar los constantes en el contexto de la aplicación
	  */
	 public void cargarConstantes() throws Exception {
		 Class c = Class.forName("org.sistema.sisgen.dao.Constants");
		 Field[] fields = c.getDeclaredFields();
		 
	     AccessibleObject.setAccessible(fields, true);
	
	     for (int i = 0; i < fields.length; i++) {
	         if (fields[i].getType().getName().equals("java.lang.String")) {	
	        	 this.getServletContext().setAttribute(fields[i].getName(), fields[i].get(null));
	         }
	     }	 
	 }


	
	
		 
	/* GET -SET ATributos */ 
	 
	 /**
	 * @return the listaMeses
	 */
	public List getListaMeses() {
		return listaMeses;
	}


	/**
	 * @param listaMeses the listaMeses to set
	 */
	public void setListaMeses(List listaMeses) {
		this.listaMeses = listaMeses;
	}
		
			
	
	
}
