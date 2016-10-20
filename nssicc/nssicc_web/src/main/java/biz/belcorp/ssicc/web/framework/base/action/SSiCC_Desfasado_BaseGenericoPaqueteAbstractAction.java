package biz.belcorp.ssicc.web.framework.base.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazBaseCompuestaService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BaseInterfazHiloAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public abstract class SSiCC_Desfasado_BaseGenericoPaqueteAbstractAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2269193623465807457L;
	private List allInterfazPaquete;
	private String[] allInterfazPaqueteSeleccionadas;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setBeforeInitAtributes()
	 */
	@Override
	protected void setBeforeViewAtributes() throws Exception {
		this.formInterfaz = this.devuelveFormInterfaz();
		this.formInterfaz.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		this.listaBusqueda = new ArrayList();
		this.interfazExecutionService = (InterfazExecutionService) getBean("sisicc.interfazExecutionService");
		this.interfazService = (InterfazService) getBean("sisicc.interfazService");
		this.historicoService = (HistoricoService) getBean("sisicc.historicoService");
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
		this.mensajeEnEjecucion = "";
		if (this.mPantallaPrincipalBean != null) {
			this.mPantallaPrincipalBean.setCurrentMenu(this.parametrosPantalla.get("codigoMenu"));
		}
		BaseInterfazForm f = (BaseInterfazForm) this.formInterfaz;
		f.setParametrosPantalla(this.parametrosPantalla);
		    
		/* Obteniendo valoes base a partir del MENU */
		if (this.validarParametriaMenu) {
			if (this.verificarValidacionParametriaMenu()) {
				
				/* Obteniendo datos de la Interfaz */
				this.obtenerDatosInterfaz();
				
				/* Verificando si es Proceso BATCH */
				String codigoProcesoBatch = (String)this.parametrosPantalla.get("codigoProcesoBatch");	
				this.formInterfaz.setCodigoProcesoBatch(codigoProcesoBatch);
				if (StringUtils.isNotBlank(codigoProcesoBatch)) {
					this.esProcesoBatch = true;
					this.codigoProcesoBatch = codigoProcesoBatch;
				}
				
				/* Seteando Criteria */
				Map<String, Object> criteria = new HashMap<String, Object>();
				criteria.put("codigoPais", this.formInterfaz.getCodigoPais());  
		      	criteria.put("codigoSistema", this.formInterfaz.getCodigoSistema());
				criteria.put("codigoInterfaz", this.formInterfaz.getCodigoInterfaz());
				if (StringUtils.isNotBlank(codigoProcesoBatch)) {
					criteria.put("codigoProcesoBatch", this.formInterfaz.getCodigoProcesoBatch());
					
					/* Verificando que no se este ejecutando Proceso BATCH */
					this.verificarProcesoBatchEnEjecucion(criteria);
					
					/* Vrificando que no se este ejecutando Proceso BATCH Dependientes */
					if (!this.enEjecucion)
						this.verificarProcesoBatchEnEjecucionDependientes(criteria);
				}
				
				/* Obteniendo Lista de Interfaces y Lista de Archivos del Directorio de Entrada */
				this.obtenerListaInterfaces(criteria);
				this.organizarTreeListaArchivosEntrada();
				
				/* Obtener Consulta de Procesos Batch */
				this.obtenerConsultaProcesoBatch(criteria);
			}	
		 }
	}
	
	
	@Override
	public void obtenerListaInterfaces(Map<String, Object> criteria) throws Exception {
		String error = "";
		InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");	
		
		/* Obteniendo datos de la Interfaz */
		Interfaz interfaz = this.formInterfaz.getInterfaz();
		if (StringUtils.isBlank(this.formInterfaz.getCheckAllListaInterfaces()))
			this.formInterfaz.setCheckAllListaInterfaces(Constants.NO);
				
		List listPaquete = interfazService.getComponentesCompuestaInterfazPaquete(interfaz.getInterfazPK());
		log.info("Interfaces Compuestas empaquetadas =" + listPaquete);
		if (listPaquete == null) {
			// Interfaces Deshabilitadas
			error = this.getResourceMessage("interfazSiCC.error.interfaz.desabilitada");
			throw new Exception(error);
		} 
		else {
			List<Interfaz> listaInterfazArchivos = (List<Interfaz>) criteria.get("listaInterfazArchivos"); 
			
			//Obtiene los archivos asociados a cada interfaz del paquete
			String[] listaInterfaces = new String[listPaquete.size()];
			String[] listaInterfacesSeleccionadas = new String[listPaquete.size()];
			
			for (int i = 0; i < listPaquete.size(); i++) {
				Interfaz interf = (Interfaz)listPaquete.get(i);
				interf.setArchivos(this.getListArchivos(interf));
				listaInterfaces[i] = interf.getCodigo();
				listaInterfacesSeleccionadas[i] = interf.getCodigo();
			}
			
			
			this.formInterfaz.setListaInterfaces(listaInterfaces);
			this.formInterfaz.setListaInterfazArchivos(listPaquete);
			this.formInterfaz.setListaInterfacesSeleccionadas(listaInterfacesSeleccionadas);
			this.formInterfaz.setCheckAllListaInterfaces(Constants.SI);	
			//this.formInterfaz.setListaSeleccionadas(listaInterfazArchivos);
			this.allInterfazPaquete = listPaquete;
			this.allInterfazPaqueteSeleccionadas = listaInterfacesSeleccionadas;
			
		}	
		
	}
	
	/**
	 * Obtiene Lista de de Archivos de la Interfaz de Entrada y lo setea al Form
	 * @param criteria
	 */
	public void obtenerListaArchivosEntrada(Map<String, Object> criteria)  {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'obtenerListaArchivosEntrada' method");
		}
		try {
			this.obtenerListaInterfaces(criteria);
		}
		catch(Exception e) {
			String mensajeError = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", mensajeError);
			return;
		}
		if (log.isWarnEnabled()) {
			log.warn("Fin 'obtenerListaInterfaces' method");
		}
		
	}
	
	
	
	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param interfaz
	 * @param request
	 * @return
	 */
	protected List getListArchivos(Interfaz interfaz) throws Exception {
		String mensaje = this.getResourceMessage("mensaje.error.noExisteArchivo");
		List archivosList = new ArrayList();			
		InterfazParams interfazParams = new InterfazParams();
		interfazParams.setInterfaz(interfaz);
		archivosList = interfazParams.getListArchivosEntrada();		
		if(archivosList.size() == 0){
			LabelArchivos labelArchivos = new LabelArchivos();
			labelArchivos.setNombreArchivo(mensaje);
			archivosList.add(labelArchivos);
		}	
		else {
			if (interfaz.getFlagValidarCargaPrevia().equals(Constants.SI)){
				
				HistoricoService service = (HistoricoService) getBean("sisicc.historicoService");
				Map criteria = new HashMap();
				
				
	          	criteria.put("codigoPais",interfaz.getCodigoPais());  
	          	criteria.put("codigoSistema",interfaz.getCodigoSistema());
				criteria.put("codigoInterfaz",interfaz.getCodigo());
				criteria.put("ejecucionSatisfactoria",Constants.SI);
				
				for (int i = 0; i < archivosList.size(); i++) {
					LabelArchivos labelArchivos = (LabelArchivos)archivosList.get(i);
					criteria.put("nombreArchivoOriginal",labelArchivos.getNombreArchivo());
					int size = service.getHistoricosByCriteria(criteria).size();
					labelArchivos.setObservacion("");
					
					
					if (size>0)	{
						labelArchivos.setObservacion( this.getResourceMessage("mensaje.error.archivoCargadoAntes"));
					}
				}
			}
			
		}
		return archivosList;
	}

	
	
	/**
	 * @param actionEvent
	 */
	public void executeInterfaz(ActionEvent actionEvent) {
		BaseInterfazForm f = (BaseInterfazForm) this.formInterfaz;
		try {
		
			Map<String, Object> params = new HashMap<String, Object>();
			params = this.prepareParamsBeforeExecute(params, f);
			String codigoInterfaz = (String)params.get("codigoInterfaz");	
			
			//
			InterfazBaseCompuestaService interfazBaseCompuestaService =(InterfazBaseCompuestaService)getBean("sisicc.interfazBaseCompuestaService"); 
			SSiCC_Desfasado_BaseInterfazHiloAbstractService baseInterfazService  = (SSiCC_Desfasado_BaseInterfazHiloAbstractService) interfazBaseCompuestaService.getInterfazImplementation(codigoInterfaz);			   	
			//
	
			//verificamos interface si esta en ejecucion o si viene los parametros nullos y verifica lote
			String mensajeError = baseInterfazService.verificaParamsBeforeExecute(params);
			
			if(StringUtils.isNotEmpty(mensajeError)){
				this.addError("Error" , mensajeError);
				return;
			}
			
			if (StringUtils.isBlank(f.getCheckAllListaInterfaces()))
				f.setCheckAllListaInterfaces(Constants.NO);
			
			
			this.ejecucionEnHilo = this.esProcesoBatch = this.enEjecucion = true;
			baseInterfazService.executeInterfaz(params);
			
			/* Retornando a la pagina */
			String paginaConsultaBatch = Constants.NO;
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.enEjecucion = false;
			this.obtenerConsultaProcesoBatch(this.paramfiltros);
		}
					
		return ;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais =  this.mPantallaPrincipalBean.getCurrentCountry();		
		BaseInterfazForm f = (BaseInterfazForm) this.formInterfaz;
	
		//se manda los parametros del form y se invoca al service
		params = BeanUtils.describe(form);
		//los nesesarios
		params.put("usuario", usuario);
		params.put("codigoUsuario",usuario.getLogin());
		params.put("pais", pais);
		params.put("codigoPais",pais.getCodigo());
		params.put("descripcionPais",pais.getDescripcion());
		params.put("indicadorEjecucion",Constants.INDICADOR_EJECUCION_PROCESO_BATCH_SI);
		params.put("codigoEstadoProceso",Constants.CODIGO_PROCESO_BATCH_EN_EJECUCION);
		params.put("recomendacionError", "    ");
		params.put("indicadorMultiLote", Constants.NO);		
		
		params.put("listaInterfaces", f.getListaInterfaces());
		params.put("listaInterfacesSeleccionadas", f.getListaInterfacesSeleccionadas());

		
		/* Codigo de Interfaz */
		String codigoInterfaz = f.getCodigoInterfaz();
		params.put("codigoInterfaz", codigoInterfaz);
		
		String codigoSistema = f.getCodigoSistema();
		if (StringUtils.isBlank(codigoSistema)) {
			if (StringUtils.isNotBlank(codigoInterfaz)) {
				codigoSistema = codigoInterfaz.substring(0, 3);
			    params.put("codigoSistema", codigoSistema);
			}    
		}
		
		/* Codigo de Proceso Batch */
		String codigoProcesoBatch = f.getCodigoProcesoBatch();
		params.put("codigoProcesoBatch", codigoProcesoBatch);
				
		String mostrarPaginaConsultaBatch = Constants.NO;
		params.put("mostrarPaginaConsultaBatch", mostrarPaginaConsultaBatch);
		params.put("listaInterfacesSeleccionadas", this.allInterfazPaqueteSeleccionadas);
		return params;
	}
	
	
	
	/* GET - SET */
	
	/**
	 * @return the allInterfazPaquete
	 */
	public List getAllInterfazPaquete() {
		return allInterfazPaquete;
	}


	/**
	 * @param allInterfazPaquete the allInterfazPaquete to set
	 */
	public void setAllInterfazPaquete(List allInterfazPaquete) {
		this.allInterfazPaquete = allInterfazPaquete;
	}


	/**
	 * @return the allInterfazPaqueteSeleccionadas
	 */
	public String[] getAllInterfazPaqueteSeleccionadas() {
		return allInterfazPaqueteSeleccionadas;
	}


	/**
	 * @param allInterfazPaqueteSeleccionadas the allInterfazPaqueteSeleccionadas to set
	 */
	public void setAllInterfazPaqueteSeleccionadas(
			String[] allInterfazPaqueteSeleccionadas) {
		this.allInterfazPaqueteSeleccionadas = allInterfazPaqueteSeleccionadas;
	}	 
	
	
	

}
