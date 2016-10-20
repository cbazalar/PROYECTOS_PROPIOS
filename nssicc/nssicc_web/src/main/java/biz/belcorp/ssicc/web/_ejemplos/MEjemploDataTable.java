package biz.belcorp.ssicc.web._ejemplos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF;
 
@ManagedBean
@SessionScoped
public class MEjemploDataTable extends MBaseSistemaAbstractJSF {
	
	private static final long serialVersionUID = -2912189198545373403L;
	 
	private List listaUsuarios; 
	
	private String mensajeI18nPruebaService;
	private String mensajeI18nPruebaDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewtAtributes()
	 */
	public void setViewAtributes() {
		log.debug("ini post Construct MEjemploDataTable");
		UsuarioService usuarioService = (UsuarioService)this.getBeanService("usuarioService");
		
		Map criteria = new HashMap();
		String countryCode = this.mPantallaPrincipalBean.getCountryCode();	
		criteria.put("codigoPaisUsuario", countryCode);
		log.debug("countryCode: " + countryCode);
		
		listaUsuarios = usuarioService.getUsuariosByCriteria(criteria);
		log.debug("Lista de Usuarios: " + listaUsuarios.size());
		log.debug("fin post Construct MEjemploDataTable");
		
		this.mensajeI18nPruebaService = usuarioService.getKeyMessage("mantenimientoMENConfiguracionProcesosForm.cabecera.existe.orden.ejecucion");
		this.mensajeI18nPruebaDAO = usuarioService.getKeyMessageDAO("procesoMAVCargaMasivaForm.error.noFormatoExcel");
	}
	
	/**
	 * Ir a la Pagina Siguiente
	 * @return
	 */
	public String irPaginaSiguiente() {
		return "ejemploDatatableParte2";
	}

	/**
	 * @return the listaUsuarios
	 */
	public List getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios the listaUsuarios to set
	 */
	public void setListaUsuarios(List listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public String getMensajeI18nPruebaService() {
		return mensajeI18nPruebaService;
	}

	public void setMensajeI18nPruebaService(String mensajeI18nPruebaService) {
		this.mensajeI18nPruebaService = mensajeI18nPruebaService;
	}

	public String getMensajeI18nPruebaDAO() {
		return mensajeI18nPruebaDAO;
	}

	public void setMensajeI18nPruebaDAO(String mensajeI18nPruebaDAO) {
		this.mensajeI18nPruebaDAO = mensajeI18nPruebaDAO;
	}
	
	
		
		
}
