package biz.belcorp.ssicc.web;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF;


@ManagedBean
@ApplicationScoped
public class MAplicacionBean extends MBaseAbstractJSF {

    private static final long serialVersionUID = -2403138958014741453L;
    
	private LookupService lookupService = (LookupService)this.getBeanService("lookupService");
	private InterfazSiCCService interfazSiCCService = (InterfazSiCCService)this.getBeanService("sisicc.interfazSiCCService"); 
				
	private List listaPaises;        
	private List listaMeses;
	private List listaIdiomas;
	private List listaTipoAcciones;
	private List listaTipoBloqueoUsuario;
	private List listaAnyosCampanya;
	private List listaMarcas;
	private List listaComision;
	
	
		
	/* themes */
	private Map<String, String> themes;  
    //private List<Them> advancedThemes;  
	
	
	@PostConstruct
	public void init() {
    	log.debug("Ingreso MAplicacion Init Post Constructor");
    	
    	
    	
    	// obtenemos la lista con todos los paises
    	if(log.isDebugEnabled()) {
            log.debug("Cargando paises...");
        }
		this.listaPaises = lookupService.getAllPaises();
		if(log.isDebugEnabled()) {
            log.debug("Carga de paises [OK]");
        }

        // obtenemos la lista con todos los idiomas
        if(log.isDebugEnabled()) {
            log.debug("Cargando idiomas...");
        }
        this.listaIdiomas = lookupService.getAllIdiomas();
        if(log.isDebugEnabled()) {
            log.debug("Carga de idiomas [OK]");
        }
        
        // obtenemos la lista con todos los tipos de acciones de bloqueo
        if(log.isDebugEnabled()) {
            log.debug("Cargando tipos de acciones...");
        }
        this.listaTipoAcciones = lookupService.getAllTiposAcciones();
        if(log.isDebugEnabled()) {
            log.debug("Carga de tipos acciones [OK]");
        }
        //	

        // obtenemos la lista con todos los tipos de bloqueo
        if(log.isDebugEnabled()) {
            log.debug("Cargando tipos bloqueo usuario...");
        }
        this.listaTipoBloqueoUsuario = lookupService.getAllTiposBloqueoUsuario();
        if(log.isDebugEnabled()) {
            log.debug("Carga de tipos bloqueo usuario [OK]");
        }
        //	

        if (log.isInfoEnabled()) {
            log.info("drop-down initialization complete [OK]");
        }
        
        // obtenemos la lista de Meses
		this.cargarListaMeses();
		
		// obtenemos la lista años para el control campaña
		this.cargarAnyosCampanya();
		
		// Obtenemos la lista de las marcas
		this.listaMarcas = this.interfazSiCCService.getMarcas();
		
		//Obtenemos la lista de comisiones
		this.listaComision = this.interfazSiCCService.getComision();
				
		// Cargamos las constantes en el contexto de la aplicacion
		try{
			this.cargarConstantes();
			
	        if (log.isInfoEnabled()) {
	            log.info("Carga de constantes [OK]");
	        }
			
		}catch (Exception ex) {	
			log.error("ERROR (cargarConstantes):" + ex.getMessage());
		}
		
		this.cargarThemes();
		//
		log.debug("Ingreso MAplicacion Init Post Constructor Lista Paises: " + listaPaises.size());		
    }
	
	/**
	 * Carga Lista de themes de Primefaces 
	 */
	private void cargarThemes() {
		themes = new TreeMap<String, String>();  
		themes.put("Afternoon", "afternoon");  
		themes.put("Afterdark", "afterdark");  
		themes.put("Afterwork", "afterwork");  
		themes.put("Humanity", "humanity");  
		themes.put("Pepper-Grinder", "pepper-grinder");  
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
		 Class c = Class.forName("biz.belcorp.ssicc.dao.Constants");
		 Field[] fields = c.getDeclaredFields();
		 
	     AccessibleObject.setAccessible(fields, true);
	
	     for (int i = 0; i < fields.length; i++) {
	         if (fields[i].getType().getName().equals("java.lang.String")) {	
	        	 this.getServletContext().setAttribute(fields[i].getName(), fields[i].get(null));
	         }
	     }	 
	 }
	
	 /**
	 * Carga Años de Campaña
	 */
	public void cargarAnyosCampanya()
	 {
		 int anyoActual = Calendar.getInstance().get(Calendar.YEAR);

		 listaAnyosCampanya = new ArrayList();
		 for(int i=anyoActual - 20; i <= anyoActual + 20; i++)
		 {			 
			 Base anyo = new Base();
			 anyo.setCodigo(Integer.toString(i));
			 anyo.setDescripcion(Integer.toString(i));
			 listaAnyosCampanya.add(anyo);
		 }		 
	 }
	
		 
	/* GET -SET ATributos */ 
	/**
	 * @return the listaPaises  
	 */
	public List getListaPaises() {
		return listaPaises;
	}


	/**
	 * @param listaPaises the listaPaises to set
	 */
	public void setListaPaises(List listaPaises) {
		this.listaPaises = listaPaises;
	}


	/**
	 * @return the listaMeses
	 */
	public List getListaMeses() {
		return listaMeses;
	}


	/**
	 * @return the listaIdiomas
	 */
	public List getListaIdiomas() {
		return listaIdiomas;
	}


	/**
	 * @param listaIdiomas the listaIdiomas to set
	 */
	public void setListaIdiomas(List listaIdiomas) {
		this.listaIdiomas = listaIdiomas;
	}


	/**
	 * @return the listaTipoAcciones
	 */
	public List getListaTipoAcciones() {
		return listaTipoAcciones;
	}


	/**
	 * @param listaTipoAcciones the listaTipoAcciones to set
	 */
	public void setListaTipoAcciones(List listaTipoAcciones) {
		this.listaTipoAcciones = listaTipoAcciones;
	}


	/**
	 * @return the listaTipoBloqueoUsuario
	 */
	public List getListaTipoBloqueoUsuario() {
		return listaTipoBloqueoUsuario;
	}


	/**
	 * @param listaTipoBloqueoUsuario the listaTipoBloqueoUsuario to set
	 */
	public void setListaTipoBloqueoUsuario(List listaTipoBloqueoUsuario) {
		this.listaTipoBloqueoUsuario = listaTipoBloqueoUsuario;
	}


	/**
	 * @param listaMeses the listaMeses to set
	 */
	public void setListaMeses(List listaMeses) {
		this.listaMeses = listaMeses;
	}


	/**
	 * @return the listaAnyosCampanya
	 */
	public List getListaAnyosCampanya() {
		return listaAnyosCampanya;
	}


	/**
	 * @param listaAnyosCampanya the listaAnyosCampanya to set
	 */
	public void setListaAnyosCampanya(List listaAnyosCampanya) {
		this.listaAnyosCampanya = listaAnyosCampanya;
	}

	/**
	 * @return the themes
	 */
	public Map<String, String> getThemes() {
		return themes;
	}

	/**
	 * @param themes the themes to set
	 */
	public void setThemes(Map<String, String> themes) {
		this.themes = themes;
	}
	
	/**
	 * @return the listaMarcas
	 */
	public List getListaMarcas() {
		return listaMarcas;
	}

	/**
	 * @param listaMarcas the listaMarcas to set
	 */
	public void setListaMarcas(List listaMarcas) {
		this.listaMarcas = listaMarcas;
	}
	
	/**
	 * @return the listaComision
	 */
	public List getListaComision() {
		return listaComision;
	}

	/**
	 * @param listaComision the listaComision to set
	 */
	public void setListaComision(List listaComision) {
		this.listaComision = listaComision;
	}

	
	
	
}
