package biz.belcorp.ssicc.web.framework.base.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.HistoricoAuditoria;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.framework.thread.BaseHiloInterfaz;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * Clase ManageBeans Abstracta, contiene metodos comunes para el flujo de ejecucion
 * de Interfaces
 * @author cbazalar
 *
 */
public abstract class BaseInterfazAbstractAction extends MBaseSistemaAbstractJSF  {

	private static final long serialVersionUID = -4069920993316975221L;

	//Bean donde se colocaran los criterios de Busqueda
	protected BaseInterfazForm formInterfaz;  
	
	//List donde se guardara el resultado de la busqueda y será mostrada en el Datatable
	protected List listaBusqueda;  
	protected boolean mostrarListaBusqueda = false;
	protected boolean mostrarBotonBuscar = false;
	protected boolean mostrarBotonExecute = true;
	protected boolean ejecucionEnHilo = true;
	
	//Atributo DataTableModel usado en el Datatable la cual contiene la lista guardada en listaBusqueda
	protected DataTableModel datatableBusqueda; 
	
	//Map con los parametros que se le envia a la Interfaz
	protected Map<String, Object> paramfiltros;
	
	// Service de Ejecucion de Interfaces
	protected InterfazExecutionService interfazExecutionService;
	protected InterfazService interfazService;
	protected HistoricoService historicoService;

	protected boolean esProcesoBatch = false;
	protected boolean enEjecucion = false;
	protected boolean esInterfazEntrada = false;
	protected boolean esInterfazSalida = false;
	protected boolean esPaquete = false;
	protected boolean esUnitaria = false;
	protected boolean esSeleccionable = false;
	protected boolean mostrarListaArchivosEntrada = false;
	protected boolean validarListaArchivosEntrada = true;
	protected boolean validarListaInterfaceSalida = true;

	
	protected String mensajeEnEjecucion;
			
	protected TreeNode rootListaArchivosEntrada;
	protected TreeNode rootHorizontalListaInterfaz;
	
	protected List listaProcesoBatchActual;
	protected List listaHistoricoInterfaz;
	protected boolean validacionPrevia = false;
	
	protected List<Interfaz> listaProcesosAdicionales;
	protected List<Interfaz> listaProcesosAdicionalesSeleccionadas;
	protected boolean mostrarMensajeInterfazOK = true;
	protected boolean mostrarBotonActualizarDatos = true;
	
	protected boolean mostrarPaginacionDatatableSpinner = false;
	protected int nroPaginacionDatatable = 10; 
	protected List<Interfaz> listaSeleccionadas;

	protected String mensajeConfirmacionEjecucion;
	protected String indicadorActualizarProcesoBatchEnAction = Constants.NO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setBeforeInitAtributes()
	 */
	@Override
	protected void setBeforeViewAtributes() throws Exception {
		super.setBeforeViewAtributes();
		this.formInterfaz = this.devuelveFormInterfaz();
		this.formInterfaz.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		this.listaBusqueda = new ArrayList();
		this.interfazExecutionService = (InterfazExecutionService) getBean("sisicc.interfazExecutionService");
		this.interfazService = (InterfazService) getBean("sisicc.interfazService");
		this.historicoService = (HistoricoService) getBean("sisicc.historicoService");
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
		this.mensajeEnEjecucion = "";
		this.mensajeConfirmacionEjecucion = this.getResourceMessage("confirm.execute.interfaz");
		
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
				//this.organizarTreeHorizontalListaInterfaz();
				
				/* Obtener Consulta de Procesos Batch */
				this.obtenerConsultaProcesoBatch(criteria);
			}	
		 }
		if (!this.esProcesoBatch) {
			this.mostrarBotonActualizarDatos = false;
		}
	}

	/**
	 * Obtiene datos de la Interfaz
	 */
	protected void obtenerDatosInterfaz() throws Exception  {
		String codigoPais = this.formInterfaz.getCodigoPais();
		String codigoSistema = this.formInterfaz.getCodigoSistema();
		String codigoInterfaz = this.formInterfaz.getCodigoInterfaz();
		InterfazPK interfazPK = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
		Interfaz interfaz = this.interfazService.getInterfaz(interfazPK);
		if (interfaz == null) {
			String mensajeError = "No se encontro datos en BD para la Interfaz " + codigoInterfaz +". Revisar la configuración en la Tabla BAS_INTER";
			throw new Exception(mensajeError);
		}
		
		this.formInterfaz.setInterfaz(interfaz);
		if (StringUtils.isNotBlank(interfaz.getDescripcion()))
			this.formInterfaz.setDescripcion(interfaz.getDescripcion());
		else
			this.formInterfaz.setDescripcion("");
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_ENTRADA)){
			this.esInterfazEntrada = true;
		}
		if(interfaz.getTipo().equals(Constants.INTERFAZ_TIPO_SALIDA)){
			this.esInterfazSalida = true;
		}
		if(interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_PAQUETE)){
			this.esPaquete = true;
		}
		if(interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)){
			this.esUnitaria = true;
		}
		if (StringUtils.isNotBlank(interfaz.getIndicadorSeleccion()))
			if(interfaz.getIndicadorSeleccion().equals(Constants.SI)){
				this.esSeleccionable = true;
			}
	}
	
	
	/**
	 * Metodo Poll que actualiza los datos de la Interfaz en la Pantalla
	 */
	public void actualizarDatos(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'actualizarDatos' method");
		}
		
		/* Seteando Criteria */
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("codigoPais", this.formInterfaz.getCodigoPais());  
      	criteria.put("codigoSistema", this.formInterfaz.getCodigoSistema());
		criteria.put("codigoInterfaz", this.formInterfaz.getCodigoInterfaz());
		
		if (StringUtils.isNotBlank(this.codigoProcesoBatch)) {
			criteria.put("codigoProcesoBatch", this.formInterfaz.getCodigoProcesoBatch());
		
			/* Verificando que no se este ejecutando Proceso BATCH */
			this.verificarProcesoBatchEnEjecucion(criteria);
			
			/* Verificando que no se este ejecutando Proceso BATCH Dependientes */
			if (!this.enEjecucion)
				this.verificarProcesoBatchEnEjecucionDependientes(criteria);
			
			/* Obtener Consulta de Procesos Batch */
			this.obtenerConsultaProcesoBatch(criteria);
		}
		
		//this.obtenerListaInterfaces(criteria);
		this.obtenerListaArchivosEntrada(criteria);
		this.organizarTreeListaArchivosEntrada();
		
		if (log.isWarnEnabled()) {
			log.warn("Fin 'actualizarDatos' method");
		}
		
	}
	
	/**
	 *
	 */
	public void actualizarDatosEnEjecucion() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'actualizarDatosEnEjecucion' method");
		}
		
		/* Seteando Criteria */
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("codigoPais", this.formInterfaz.getCodigoPais());  
      	criteria.put("codigoSistema", this.formInterfaz.getCodigoSistema());
		criteria.put("codigoInterfaz", this.formInterfaz.getCodigoInterfaz());
		
		if (StringUtils.isNotBlank(this.codigoProcesoBatch)) {
			criteria.put("codigoProcesoBatch", this.formInterfaz.getCodigoProcesoBatch());
		
			/* Verificando que no se este ejecutando Proceso BATCH */
			this.verificarProcesoBatchEnEjecucion(criteria);
			
			/* Obtener Consulta de Procesos Batch */
			this.obtenerConsultaProcesoBatch(criteria);
		}
		
		//this.obtenerListaInterfaces(criteria);
		this.obtenerListaArchivosEntrada(criteria);
		this.organizarTreeListaArchivosEntrada();
	
		if (log.isWarnEnabled()) {
			log.warn("Fin 'actualizarDatosEnEjecucion' method");
		}
		
	}

	/**
	 * Obtiene consulta de Procesos Batch
	 * @param criteria
	 */
	public void obtenerConsultaProcesoBatch(Map<String, Object> criteria) {
		String codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		ProcesoBatchService service = (ProcesoBatchService) this.getBeanService("scsicc.procesoBatchService");    		
		this.listaProcesoBatchActual = service.getProcesoBatchActuByCriteria(criteria);
		
		if (this.listaProcesoBatchActual != null && this.listaProcesoBatchActual.size() > 0) {
			ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) this.listaProcesoBatchActual.get(0); 
			criteria.put("idProcesoBatch", procesoBatchActu.getIdProcesoBatch());
			this.listaHistoricoInterfaz = this.historicoService.getHistoricosLotesMultiHilo(criteria);
		}
	
	}
	
	/**
	 * Validando que la parametria ingresada en el MENU sea la correcta
	 * @param criteria
	 */
	protected boolean verificarValidacionParametriaMenu() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verificarValidacionParametriaMenu' method");
		}
		
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		this.formInterfaz.setCodigoPais(codigoPais);
		if (StringUtils.isBlank(codigoPais)) {
			String error = this.getResourceMessage("interfaz.error.codigoPais");
			this.addError("Error: ", error);
			return false;
		}
		
		String codigoInterfaz = (String)this.parametrosPantalla.get("codigoInterfaz");
		this.formInterfaz.setCodigoInterfaz(codigoInterfaz);
		if (StringUtils.isBlank(codigoInterfaz)) {
			String error = this.getResourceMessage("interfaz.error.codigoInterfaz");
			this.addError("Error: ", error);
			return false;
		}
		
		String codigoSistema = (String)this.parametrosPantalla.get("codigoSistema");
		if (StringUtils.isBlank(codigoSistema)) {
			if (StringUtils.isNotBlank(codigoInterfaz)) {
				codigoSistema = codigoInterfaz.substring(0, 3);
			}    
		}
		this.formInterfaz.setCodigoSistema(codigoSistema);
		if (StringUtils.isBlank(codigoSistema)) {
			String error = this.getResourceMessage("interfaz.error.codigoSistema");
			this.addError("Error: ", error);
			return false;
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Fin 'verificarValidacionParametriaMenu' method");
		}
		return true;
    }

	/**
	 * Verificando que no se este ejecutando Proceso BATCH para el caso de PAQUETE DE INTERFACES
	 * @param criteria
	 */
	public void verificarProcesoBatchEnEjecucion(Map<String, Object> criteria) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verificarProcesoBatchEnEjecucion' method");
		}
		this.enEjecucion = false;
		String codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		String ejecucion = this.interfazExecutionService.verificarProcesoBatchEnEjecucion(criteria);
		if (StringUtils.isNotBlank(ejecucion)) {
			this.enEjecucion = true;
			this.mensajeEnEjecucion = ejecucion;
		}
		
		if (log.isWarnEnabled()) {
			log.warn("Fin 'verificarProcesoBatchEnEjecucion' method");
		}
	}
	
	/**
	 * Verificando que no se este ejecutando Proceso BATCH Dependientes para el caso de PAQUETE DE INTERFACES
	 * @param criteria
	 */
	public void verificarProcesoBatchEnEjecucionDependientes(Map<String, Object> criteria) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'verificarProcesoBatchEnEjecucionDependientes' method");
		}
		this.enEjecucion = false;
		String codigoProcesoBatch = (String) criteria.get("codigoProcesoBatch");
		if (StringUtils.isBlank(codigoProcesoBatch)) return;
		
		String ejecucion = this.interfazExecutionService.verificarProcesoBatchEnEjecucionDependientes(criteria);
		if (StringUtils.isNotBlank(ejecucion)) {
			this.enEjecucion = true;
			this.mensajeEnEjecucion = ejecucion;	
			//this.addWarn("Warning: ", ejecucion);	
			
		}
		if (log.isWarnEnabled()) {
			log.warn("Fin 'verificarProcesoBatchEnEjecucionDependientes' method");
		}
	}

	
	/**
	 * Obtiene Lista de de Archivos de la Interfaz de Entrada y lo setea al Form
	 * @param criteria
	 */
	public void obtenerListaArchivosEntrada(Map<String, Object> criteria) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'obtenerListaArchivosEntrada' method");
		}
		String mensajeError = "";
		try {
			criteria = this.interfazExecutionService.obtenerListaInterfaces(criteria);
			mensajeError = (String) criteria.get("mensajeError");
			if (StringUtils.isNotBlank(mensajeError)) {
				this.addError("Error: ", mensajeError);
				return;
			}
			List<Interfaz> listaInterfazArchivos = (List<Interfaz>) criteria.get("listaInterfazArchivos"); 
			this.formInterfaz.setListaInterfazArchivos(listaInterfazArchivos);

			/* Adicionando Procesos */
			this.listaProcesosAdicionales  = this.setObtenerListaProcesos();
			if(this.listaProcesosAdicionales != null && this.listaProcesosAdicionales.size() > 0) {
				for(int i=0; i < listaProcesosAdicionales.size(); i++) {
					listaInterfazArchivos.add(i, this.listaProcesosAdicionales.get(i));
				}
				
				this.formInterfaz.setListaInterfazArchivos(listaInterfazArchivos);
			}
		}
		catch(Exception e) {
			mensajeError = this.obtieneMensajeErrorException(e);
			this.addError("Error: ", mensajeError);
			return;
		}
			
	
		if (log.isWarnEnabled()) {
			log.warn("Fin 'obtenerListaInterfaces' method");
		}
		
	}
	

	/**
	 * Obtiene Lista de Interfaces y Lista de Archivos y lo setea en el FORM
	 * @param criteria
	 */
	public void obtenerListaInterfaces(Map<String, Object> criteria) throws Exception {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'obtenerListaInterfaces' method");
		}
		criteria = this.interfazExecutionService.obtenerListaInterfaces(criteria);
		
		String mensajeError = (String) criteria.get("mensajeError");
		if (StringUtils.isNotBlank(mensajeError)) {
			this.addError("Error: ", mensajeError);
			return;
		}
		else {
			String[] listaInterfaces = (String[]) criteria.get("listaInterfaces"); 
			String[] listaInterfacesSeleccionadas = (String[]) criteria.get("listaInterfacesSeleccionadas"); 
			List<Interfaz> listaInterfazArchivos = (List<Interfaz>) criteria.get("listaInterfazArchivos"); 
			//Arrays.sort(listaInterfacesSeleccionadas);
			
			this.formInterfaz.setListaInterfaces(listaInterfaces);
			this.formInterfaz.setListaInterfacesSeleccionadas(listaInterfacesSeleccionadas);
			this.formInterfaz.setListaInterfazArchivos(listaInterfazArchivos);
			this.formInterfaz.setListaSeleccionadas(listaInterfazArchivos);
			this.listaSeleccionadas = listaInterfazArchivos;
			
			
			/* Adicionando Procesos */
			this.listaProcesosAdicionales  = this.setObtenerListaProcesos();
			if(this.listaProcesosAdicionales != null && this.listaProcesosAdicionales.size() > 0) {
				for(int i=0; i < listaProcesosAdicionales.size(); i++) {
					listaInterfazArchivos.add(i, this.listaProcesosAdicionales.get(i));
				}
				
				this.formInterfaz.setListaInterfazArchivos(listaInterfazArchivos);
				this.formInterfaz.setListaSeleccionadas(listaInterfazArchivos);
				this.listaSeleccionadas = listaInterfazArchivos;
			}
			
			
		}
		if (log.isWarnEnabled()) {
			log.warn("Fin 'obtenerListaInterfaces' method");
		}
		
	}
	
	
	public List<Interfaz> setObtenerListaProcesos() {
		return null;
	}
	/**
	 * Organiza la lista de Archivos de Entrada en formato TREENODE para su visualizacion
	 */
	protected final void organizarTreeListaArchivosEntrada() {
		this.mostrarListaArchivosEntrada = false;
		if (!this.esInterfazEntrada) return;
		List<Interfaz> listaInterfaz = this.formInterfaz.getListaInterfazArchivos();
		if (listaInterfaz.size() <= 0)  return;
		
		this.rootListaArchivosEntrada = new DefaultTreeNode("rootListaArchivosEntrada", null);
		for (int i=0; i <listaInterfaz.size(); i++) {
			Interfaz interfaz = listaInterfaz.get(i);
			
			LabelArchivos labelInterfaz = new LabelArchivos();
			labelInterfaz.setNombreArchivo(interfaz.getCodigo() + " - " + interfaz.getDescripcion());
			labelInterfaz.setEsArchivo(false);
			TreeNode tinterfaz = new DefaultTreeNode(labelInterfaz, rootListaArchivosEntrada);
			tinterfaz.setExpanded(true);
			
			List listaLabelArchivos = interfaz.getArchivos();
			for (int x=0; x <listaLabelArchivos.size(); x++) {
				LabelArchivos labelArchivos = (LabelArchivos) listaLabelArchivos.get(x);
				TreeNode tArchivos = new DefaultTreeNode(labelArchivos, tinterfaz);
				tArchivos.setExpanded(true);
			}
			
		}
		this.mostrarListaArchivosEntrada = true;
	}
	
	
	/**
	 * Organiza Lista de Interfaces del Paquete en TREE Horizontal
	 */
	private void organizarTreeHorizontalListaInterfaz() {
		Interfaz interfazForm = this.formInterfaz.getInterfaz();
		if(interfazForm.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA)) return;
		
		List<Interfaz> listaInterfaz = this.formInterfaz.getListaInterfazArchivos();
		if (listaInterfaz.size() <= 0)  return;
		
		/* Obteniendo los Niveles */
		Base baseRoot = new Base();
		baseRoot.setCodigo(interfazForm.getCodigo());
		baseRoot.setDescripcion(interfazForm.getCodigo());
		this.rootHorizontalListaInterfaz = new DefaultTreeNode(baseRoot, null);
		this.rootHorizontalListaInterfaz.setExpanded(true);
		Long nivelHilo = new Long(-1);
		List<TreeNode> listaTreeNodeNivel = new ArrayList<TreeNode>();
		for (int i=0; i <listaInterfaz.size(); i++) {
			Interfaz interfaz = listaInterfaz.get(i);
			Long nivelHiloFor = interfaz.getNivelHilo();
			
			if (nivelHilo.longValue() != nivelHiloFor.longValue()) {
				nivelHilo = nivelHiloFor;
				String key = nivelHiloFor.toString();
				Base base = new Base();
				base.setCodigo(key);
				base.setDescripcion("Nivel " + nivelHiloFor.longValue());
				TreeNode tinterfazNivel = new DefaultTreeNode(base, this.rootHorizontalListaInterfaz);
				tinterfazNivel.setExpanded(true);
				listaTreeNodeNivel.add(tinterfazNivel);
			}
	
		}
		
		/* Obteniendo los Hilos */
		nivelHilo = new Long(-1);
		Long ordenHilo = new Long(-1);
		List<TreeNode> listaTreeNodeHilo = new ArrayList<TreeNode>();
		for (int i=0; i <listaInterfaz.size(); i++) {
			Interfaz interfaz = listaInterfaz.get(i);
			Long nivelHiloFor = interfaz.getNivelHilo();
			Long ordenHiloFor = interfaz.getOrdenHilo();
			
			
			if (nivelHiloFor.longValue() != nivelHilo.longValue() || ordenHiloFor.longValue() != ordenHilo.longValue()) {
				ordenHilo = ordenHiloFor;
				nivelHilo = nivelHiloFor;
				int busca = -1;
				for(int x=0; x <listaTreeNodeNivel.size(); x++) {
					TreeNode tinterfazNivel = listaTreeNodeNivel.get(x);
					Base base = (Base)tinterfazNivel.getData();
					if (base.getCodigo().equals(nivelHiloFor.toString()) ) {
						busca = x;
						break;
					}
				}
				if (busca > -1) {
					String key = nivelHiloFor.toString() + "_" + ordenHiloFor.toString();
					Base base = new Base();
					base.setCodigo(key);
					base.setDescripcion("Hilo " + ordenHiloFor.longValue());
					
					TreeNode tinterfazHilo = new DefaultTreeNode(base, listaTreeNodeNivel.get(busca));
					tinterfazHilo.setExpanded(true);
					listaTreeNodeHilo.add(tinterfazHilo);
				}
			}
			
		}
		
		/* Obteniendo las interfaces */
		for (int i=0; i <listaInterfaz.size(); i++) {
			Interfaz interfaz = listaInterfaz.get(i);
			Long nivelHiloFor = interfaz.getNivelHilo();
			Long ordenHiloFor = interfaz.getOrdenHilo();
			
			int busca = -1;
			for(int x=0; x <listaTreeNodeHilo.size(); x++) {
				TreeNode tinterfazHilo = listaTreeNodeHilo.get(x);
				Base base = (Base)tinterfazHilo.getData();
				if (base.getCodigo().equals(nivelHiloFor.toString() + "_" + ordenHiloFor.toString()) ) {
					busca = x;
					break;
				}
			}
			if (busca > -1) {
				String key = interfaz.getCodigo();
				Base base = new Base();
				base.setCodigo(key);
				base.setDescripcion(interfaz.getCodigo() + " - " + interfaz.getDescripcion());
				TreeNode tinterfaz = new DefaultTreeNode(base, listaTreeNodeHilo.get(busca));
				tinterfaz.setExpanded(true);
			}
		}
		
	}
	
	
	/**
	 *  Metodo que limpia la Grilla de Busqueda
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void limpiarFind(ActionEvent actionEvent) {
		try {	
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.setLimpiarFind();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 *  Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo AJAX
	 */
	public void find(ActionEvent actionEvent) {
		this.find();
	}
	
	public void find(String mensaje) {
		this.find();
		this.addInfo("Info: ", mensaje);
	}
	
	
	/**
	 * Metodo principal que efectua la busqueda en base a los filtros seleccionados en la pantalla
	 * de Busqueda
	 * Para las busquedas tipo ACTION
	 */
	public String find() {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'find' method");
		}
    	if(!this.validarFind()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return null;
		}
		
		try {			
			this.datatableBusqueda = null;
			this.paramfiltros = new HashMap<String, Object>();
			this.paramfiltros = BeanUtils.describe(this.formInterfaz);
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			if ((this.listaBusqueda == null) ||(this.listaBusqueda.size() == 0)){
				this.addWarn("Warning: ", this.getResourceMessage("errors.datos.fuentes.busqueda"));
			}
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		if (log.isWarnEnabled()) {
			log.warn("Finish 'find' method");
		}
		return null;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar Busqueda
	 * @return
	 */
	private boolean validarFind(){
		boolean validacion = true;	
		String lsMensajeError = this.setValidarFind();
		if (StringUtils.isNotBlank(lsMensajeError)) {
			validacion = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return validacion;
	}
	
	/**
	 * Devuelve Mensaje de error personalizado de validacion extra antes de realizar busqueda
	 * @return
	 */
	public String setValidarFind(){
		return "";
	}
	
	
	/**
	 * Aqui se debe asociar la clase FORM al Manage Beans, la cual internamente se asociara al
	 * atributo formInterfaz
	 * @return
	 * @throws Exception
	 */
	protected abstract BaseInterfazForm devuelveFormInterfaz() throws Exception;
	
	/**
	 * Hook method para la ejecucion de la Busqueda. Esta implementacion devuelve una lista con los valores
	 * respectivos de acuerdo a los filtros seleccionados. Dicho metodo es obligatorio sobreescribirlo 
	 * La busqueda por defecto es por AJAX
	 * @throws Exception
	 */
	protected List setFindAttributes() throws Exception {
		return null;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		// Paso todos los parametros al map
		params = super.prepareParamsBeforeExecute(this.paramfiltros, form);
		
		BaseInterfazForm f = (BaseInterfazForm) form;
		boolean existenProcesosAdicionales = false;
		if (this.listaProcesosAdicionales != null && this.listaProcesosAdicionales.size()  > 0 ) {
			existenProcesosAdicionales = true;
		}
		this.listaProcesosAdicionalesSeleccionadas = new ArrayList<Interfaz>();
		
		if (!this.esSeleccionable) {
			this.formInterfaz.setListaSeleccionadas(this.listaSeleccionadas);
		}
		List<Interfaz> listaSeleccionadas = this.formInterfaz.getListaSeleccionadas();
		String[] listaInterfacesSeleccionadas;
		if (listaSeleccionadas == null || listaSeleccionadas.size() <=0) {
			listaInterfacesSeleccionadas = new String[0];
		}
		else  {
			listaInterfacesSeleccionadas = new String[listaSeleccionadas.size()];
			for(int i=0; i < listaSeleccionadas.size(); i++) {
				Interfaz interfaz = listaSeleccionadas.get(i);
				if (existenProcesosAdicionales) {
					boolean encontro= false;
					for(int x=0; x < this.listaProcesosAdicionales.size(); x++ ) {
						String codigoInterfaz = interfaz.getCodigo();
						String codigoProceso = this.listaProcesosAdicionales.get(x).getCodigo();
						if (codigoInterfaz.equals(codigoProceso)) {
							encontro = true;
							this.listaProcesosAdicionalesSeleccionadas.add(this.listaProcesosAdicionales.get(x));
							break;
						}
					}
					if (!encontro) {
						listaInterfacesSeleccionadas[i] = interfaz.getCodigo();
					}
				}
				else 
					listaInterfacesSeleccionadas[i] = interfaz.getCodigo();
			}
		}
		f.setListaInterfacesSeleccionadas(listaInterfacesSeleccionadas);
		
		params.put("listaInterfaces", f.getListaInterfaces());
		params.put("listaInterfacesSeleccionadas", f.getListaInterfacesSeleccionadas());
		params.put("listaProcesosAdicionalesSeleccionadas", listaProcesosAdicionalesSeleccionadas);
		params.put("validarListaArchivosEntrada", Constants.SI);
		if (!this.validarListaArchivosEntrada) {
			params.put("validarListaArchivosEntrada", Constants.NO);
		}
		params.put("validarListaInterfaceSalida", Constants.SI);
		if (!this.validarListaInterfaceSalida) {
			params.put("validarListaInterfaceSalida", Constants.NO);
		}
		params.put("indicadorActualizarProcesoBatchEnAction", this.indicadorActualizarProcesoBatchEnAction);
		return params;
	}
	
	/**
	 * Realiza validaciones previas antes de la Ejecucion de la Interfaz
	 * @param actionEvent
	 */
	public void validarExecuteInterfaz(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeInterfaz' method");
		}
		
		if(!this.validarInterfaz()){
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			return ;
		}
		this.getRequestContext().execute("PF('confirmationDialogGenerarInterfaz').show()");
		return;
	}	
	
	
	/**
	 * Ejecucion de Interfaz
	 */
	public void executeInterfaz() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeInterfaz' method");
		}
	
		/* Validaciones Previas e Insertarndo en PROCESO BATCH en caso sea necesario */
		try {	
			
			/* Insertando la parametria necesaria para la ejecucion de la Interfaz */
			this.paramfiltros = this.prepareParamsBeforeExecute(this.paramfiltros, this.formInterfaz);
			
			/* Ejecutando las validaciones previas antes de la Ejecución de la Interfaz */
			this.paramfiltros = this.interfazExecutionService.executeInterfazValidacionesPrevias(this.paramfiltros);
			
			/* Insertando Registro en Tabla de PROCESO BATCH */
			if (StringUtils.isNotBlank(this.codigoProcesoBatch)) {
				this.paramfiltros.put("codigoProcesoBatch",this.codigoProcesoBatch);
				this.paramfiltros.put("descripcionEtapaProceso",Constants.NUMERO_ETAPA_PROCESO_BATCH_DEFAULT);
				this.paramfiltros = this.interfazExecutionService.insertProceBatch(this.paramfiltros);
			}
			log.info("queryParams=" + this.paramfiltros);
		}
		catch (Exception e) {
			String lsMensajeError = this.obtieneMensajeErrorException(e);
			this.setMensajeAlertaDefault(lsMensajeError);
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
			this.addError("Error", lsMensajeError);
			
			this.enEjecucion = false;
			this.obtenerConsultaProcesoBatch(this.paramfiltros);
			return;
		}
		
		/* Invocando Hilo */
		if (this.ejecucionEnHilo && this.esProcesoBatch) {
			BaseHiloInterfaz hilo = new BaseHiloInterfaz(this, this.paramfiltros);
			hilo.start();
			this.enEjecucion = true;
		}
		else {
			this.enEjecucion = true;
			try {
				this.executeHilo(this.paramfiltros);
				Map<String, Object> criteria = new HashMap<String, Object>();
				criteria.put("codigoPais", this.formInterfaz.getCodigoPais());  
		      	criteria.put("codigoSistema", this.formInterfaz.getCodigoSistema());
				criteria.put("codigoInterfaz", this.formInterfaz.getCodigoInterfaz());
				this.obtenerListaArchivosEntrada(criteria);
				this.organizarTreeListaArchivosEntrada();
			}
			catch(Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
				this.enEjecucion = false;
				this.obtenerConsultaProcesoBatch(this.paramfiltros);
				return;
			}
			this.enEjecucion = false;
		}
		
		/* SI ES PROCESO BATCH */
		if (this.esProcesoBatch) {
			
			/* Obtener Consulta de Procesos Batch */
			try {			
				Map<String, Object> criteria = new HashMap<String, Object>();
				criteria.put("codigoPais", this.formInterfaz.getCodigoPais());  
		      	criteria.put("codigoSistema", this.formInterfaz.getCodigoSistema());
				criteria.put("codigoInterfaz", this.formInterfaz.getCodigoInterfaz());
				criteria.put("codigoProcesoBatch", this.formInterfaz.getCodigoProcesoBatch());
				
				long timeWait = 1000;//POR DEFAULT 1Sg
				Thread.sleep(timeWait);
				
				if (StringUtils.isNotBlank(this.codigoProcesoBatch)) 
					this.obtenerConsultaProcesoBatch(criteria);
				
			}
			catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
				this.enEjecucion = false;
				this.obtenerConsultaProcesoBatch(this.paramfiltros);
				return;
			}
		}
	
		if (log.isWarnEnabled()) {
			log.warn("Finish 'executeInterfaz' method");
		}	
	}
	
	
		
	
	
	/**
	 * @param actionEvent
	 */
	public void executeInterfaz(ActionEvent actionEvent) {
		this.executeInterfaz();
	}
	
	
	/**
	 * Ejecuta la Interfaz SiSiCC en un THREAD (HILO) APARTE
	 * @param params
	 * @throws Exception
	 */
	public void executeHilo(Map<String, Object> params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeHilo' method");
		}
		HistoricoAuditoria historicoAuditoria = new HistoricoAuditoria();
		historicoAuditoria.setCodigoAccion(this.ACCION_EJECUTAR);
		this.grabarAuditoriaUsuarioIni(historicoAuditoria, this.codigoMenu);
		
		/* Ejecucion de Procesos Previos a la generación de Interfaz, antes de insertar en BAS_HISTO_LOTES */
		try {
			params = this.executeProcessBeforeInterfaz(params);
			log.info("queryParams=" + params);
		}
		catch (Exception e) {
				this.interfazExecutionService.updateInterfazRegistroProcesoBatch(params, e);
				this.actualizarDatosEnEjecucion();
				return;
		}
		
		/* Verificando la ejecucion de la Interfaz */
		if (!continueExecuteInterfaz(params)) {
			this.interfazExecutionService.updateInterfazRegistroProcesoBatch(params);
			this.actualizarDatosEnEjecucion();
			return;
		}	
		String ejecutarSoloProcesoAdicional = (String) params.get("ejecutarSoloProcesoAdicional");
		if (StringUtils.isNotBlank(ejecutarSoloProcesoAdicional) && ejecutarSoloProcesoAdicional.equals(Constants.SI)) {
			this.interfazExecutionService.updateInterfazRegistroProcesoBatch(params);
			this.actualizarDatosEnEjecucion();
			return;
		}
		
		/* Before Interfaz */
		this.beforeExecuteInterfaz(params);
		
		/* Ejecucion de generación de Interfaz */
		InterfazExecutionResult interfazExecutionResult = this.interfazExecutionService.executeInterfaz(params);
		
		/* After Interfaz */
		try {
			this.afterExecuteInterfaz(params, interfazExecutionResult);
			if (StringUtils.equals(Constants.SI, this.indicadorActualizarProcesoBatchEnAction)) {
				this.interfazExecutionService.updateInterfazRegistroProcesoBatch(params);
			}
		}
		catch(Exception e) {
			this.interfazExecutionService.updateInterfazRegistroProcesoBatch(params, e);
			try {
				
				InterfazParams interfazParams = (InterfazParams) params.get("interfazParams");
				Historico historicoEjecucion = interfazParams.getHistorico();
				this.updateHistoricoOnExceptionGral(historicoEjecucion, e);
				this.saveHistorico(interfazParams);
			}
			catch(Exception ex) {
				
			}
			this.actualizarDatosEnEjecucion();
		}
		
		
		/* Final Interfaz */
		String numeroLote = (String) params.get("numeroLote");
		if (!this.esProcesoBatch && StringUtils.isNotBlank(numeroLote)) {
			Map<String, Object> criteria = new HashMap<String, Object>();
			criteria.put("codigoPais", this.formInterfaz.getCodigoPais());  
	      	criteria.put("codigoSistema", this.formInterfaz.getCodigoSistema());
			criteria.put("numeroLote", numeroLote);
			int nroInterfaces = this.formInterfaz.getListaInterfacesSeleccionadas().length ;
			String[] listaInterfaces = new String[nroInterfaces + 1];
			for (int i=0 ; i< nroInterfaces ; i++) {
				listaInterfaces[i] = this.formInterfaz.getListaInterfacesSeleccionadas()[i];
			}
			listaInterfaces[nroInterfaces] = this.formInterfaz.getCodigoInterfaz();
			criteria.put("listaInterfacesSeleccionadas", listaInterfaces);
			this.listaHistoricoInterfaz = this.historicoService.getHistoricosLotesMultiHiloLote(criteria);
			this.enEjecucion = false;
		}
		
		//this.afterExecuteInterfazAction(this.paramfiltros);
		if (!this.esProcesoBatch) {
			String error = "";
			try {
				error = (String)params.get("error");
			}
			catch (Exception e) {
				error = "";
			}
			if (StringUtils.isBlank(error) ) {
				if (this.mostrarMensajeInterfazOK)
					this.addInfo("Información", this.getResourceMessage("interfaz.concluido"));
			}
			else 
				this.addError("Error", error);
		}
		
		log.debug("Fin 'executeHilo' method");
		return;		
	}
	
	/**
	 * Hook method para la ejecucion de la Interfaz. Esta implementacion
	 * devuelve siempre true y siempre se ejecuta la Interfaz. En caso que la
	 * Interfaz no se deba ejecutar debido a algun valor en los parametros se
	 * puede sobrescribir este metodo.
	 * 
	 * @param params
	 *            parametros de la Interfaz
	 * @return true si se va a ejecutar la Interfaz, false en caso de que no se
	 *         ejecute
	 */
	protected boolean continueExecuteInterfaz(Map<String, Object> params) {
		return true;
	}
	
	/**
     * Hook method, invocado antes de la llamada a
     * 'interfazExecutionService.executeInterfaz' dentro del metodo
     * 'executeInterfaz'.
     * 
     * @param params
     *            parametros de la interfaz
     */
    protected void beforeExecuteInterfaz(Map params) {
    }
	
	
	/**
	 * Hook metodo. Utilizado para invocar proceso previos a la ejecucion de la interfaz.
	 * Asimismo dicho metodo actualiza la lista de Procesos Batch Activos de manera que coloca al 
	 * proceso en estado de Ejecucion  
	 */
	protected Map<String, Object> executeProcessBeforeInterfaz(Map<String, Object> params) throws Exception {
		return params;
	}
	
   
    
    /**
	 * Hook method, invocado despues de la llamada a
	 * 'interfazExecutionService.executeInterfaz' dentro del metodo
	 * 'executeInterfaz'.
	 * 
	 * @param params
	 *            parametros de la interfaz
	 */
    protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception {
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion de la interfaz
	 * @return
	 */
	private boolean validarInterfaz(){
		this.validacionPrevia = true;	
		String lsMensajeError = this.setValidarInterfaz();
		
		if (this.esSeleccionable) {
			List<Interfaz> listaSeleccionadas = this.formInterfaz.getListaSeleccionadas();
			if (listaSeleccionadas == null || listaSeleccionadas.size() <=0) {
				String keyMensaje = this.getResourceMessage("interfaz.sinSeleccionInterfazPaquete");
				this.validacionPrevia = false;	
				this.setMensajeAlertaDefault(keyMensaje);
				return this.validacionPrevia;
			}
		}
		
		if (StringUtils.isNotBlank(lsMensajeError)) {
			this.validacionPrevia = false;	
			this.setMensajeAlertaDefault(lsMensajeError);
		}
		return this.validacionPrevia;
	}
	
	/**
	 * Realiza acciones personalizadas de validación previa, de existir, antes de realizar la verificacion de ejecucion de la interfaz
	 * Devuelve Mensaje de error personalizado de validacion extra antes de la verificación de la ejecución del reporte 
	 * @return
	 */
	public String setValidarInterfaz(){
		return "";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF#setObtenerPaginaAyudaPantalla()
	 */
	protected String setObtenerPaginaAyudaPantalla() {
		return "/pages/ayuda/consultaAyudaInterfaces.xhtml";
	}
	
	
	
	/**
	 * Proceso de Anular Proceso Batch
	 * @param actionEvent
	 */
	public void anularProcesoBath(ActionEvent actionEvent) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		if(log.isDebugEnabled())			
			log.debug("Ingreso anularProcesoBath");
		Map criteria = new HashMap();
    	Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		if (this.listaProcesoBatchActual != null && this.listaProcesoBatchActual.size() > 0) {
			ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) this.listaProcesoBatchActual.get(0); 
			criteria.put("codigoSistema", procesoBatchActu.getCodigoSistema());
	    	criteria.put("codigoProcesoBatch", this.codigoProcesoBatch);
	    	criteria.put("numeroLote", procesoBatchActu.getNumeroLote());
			criteria.put("codigoPais", pais.getCodigo());
			
			if (this.validationSuccessfulProcesoBatch(criteria)){
			    		    	    	
		    	criteria.put("indicadorEjecucion", Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO);		
		    	criteria.put("descripcionLog", this.getResourceMessage("consultaBASProcesoBatchActuaSearchForm.logError"));
				criteria.put("codigoEstadoProceso", Constants.CODIGO_PROCESO_BATCH_ERROR);
							    	
				procesoBatchService.updateProcesoBatchActu(criteria, usuario);
			}
			this.actualizarDatos(actionEvent) ;
		}
	}	
	
	
	/**
	 * Método pque valida que el proceso este en ejecución
	 * @param request
	 * @param criteria
	 * @return
	 */
	private boolean validationSuccessfulProcesoBatch(Map criteria) {
		boolean isOk = true;

		ProcesoBatchService service = (ProcesoBatchService) getBean("scsicc.procesoBatchService");		
		ProcesoBatchActu procesoBatch = (ProcesoBatchActu) service.getProcesoBatchActuByCriteria(criteria).get(0);
				
		if (procesoBatch.getIndicadorEjecucion().equals(Constants.INDICADOR_EJECUCION_PROCESO_BATCH_NO))
		{
			this.addError("Error: ", this.getResourceMessage("errors.ejecucion.procesoBatchActu"));
			isOk = false;
		}

		return isOk;
	}
	
	
	
	/**
	 * @param historico
	 * @param e
	 */
	protected final void updateHistoricoOnExceptionGral(Historico historico, Exception e) {
		historico.setFlagError(Constants.SI);
		
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_LOGICA_NEGOCIO);

		if (e.getCause() != null) {
			historico.setDescripcionError(e.getCause()
					.toString());
		} else
			historico.setDescripcionError(e.getMessage());
		
	}
	
	
	/**
	 * Guarda la informacion del Historico en base de datos.
	 * Se guarda en una transaccion aparte
	 * 
	 * @param historico
	 *            Histórico a guardar
	 */
	protected final void saveHistorico(InterfazParams interfazParams) {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveHistorico' method");
		try {
			Historico historico = interfazParams.getHistorico();
			historico.setFechaFinProceso(new Timestamp(System
					.currentTimeMillis()));
			historico.setHistoricoFileName(interfazParams.getHistoricoFileName());
			log.debug(historico);
			this.historicoService.updateHistorico(historico, interfazParams
					.getUsuario());
			log.info("Se actualizo el historico en la base de datos");
		} catch (Exception e) {
			log.error("Error al grabar el Historico.");
			log.error(e.getMessage());
		}
	}

	
	/* GET - SET ATRIBUTOS */	

	/**
	 * @return the listaBusqueda
	 */
	public List getListaBusqueda() {
		return listaBusqueda;
	}

	/**
	 * @param listaBusqueda the listaBusqueda to set
	 */
	public void setListaBusqueda(List listaBusqueda) {
		this.listaBusqueda = listaBusqueda;
	}

	
	/**
	 * @return the datatableBusqueda
	 */
	public DataTableModel getDatatableBusqueda() {
		return datatableBusqueda;
	}


	/**
	 * @param datatableBusqueda the datatableBusqueda to set
	 */
	public void setDatatableBusqueda(DataTableModel datatableBusqueda) {
		this.datatableBusqueda = datatableBusqueda;
	}

	

	/**
	 * @return the mostrarListaBusqueda
	 */
	public boolean isMostrarListaBusqueda() {
		return mostrarListaBusqueda;
	}


	/**
	 * @param mostrarListaBusqueda the mostrarListaBusqueda to set
	 */
	public void setMostrarListaBusqueda(boolean mostrarListaBusqueda) {
		this.mostrarListaBusqueda = mostrarListaBusqueda;
	}


	/**
	 * @return the formInterfaz
	 */
	public BaseInterfazForm getFormInterfaz() {
		return formInterfaz;
	}


	/**
	 * @param formInterfaz the formInterfaz to set
	 */
	public void setFormInterfaz(BaseInterfazForm formInterfaz) {
		this.formInterfaz = formInterfaz;
	}


	/**
	 * @return the paramfiltros
	 */
	public Map<String, Object> getParamfiltros() {
		return paramfiltros;
	}


	/**
	 * @param paramfiltros the paramfiltros to set
	 */
	public void setParamfiltros(Map<String, Object> paramfiltros) {
		this.paramfiltros = paramfiltros;
	}


	/**
	 * @return the interfazExecutionService
	 */
	public InterfazExecutionService getInterfazExecutionService() {
		return interfazExecutionService;
	}


	/**
	 * @param interfazExecutionService the interfazExecutionService to set
	 */
	public void setInterfazExecutionService(
			InterfazExecutionService interfazExecutionService) {
		this.interfazExecutionService = interfazExecutionService;
	}


	/**
	 * @return the esProcesoBatch
	 */
	public boolean isEsProcesoBatch() {
		return esProcesoBatch;
	}


	/**
	 * @param esProcesoBatch the esProcesoBatch to set
	 */
	public void setEsProcesoBatch(boolean esProcesoBatch) {
		this.esProcesoBatch = esProcesoBatch;
	}


	/**
	 * @return the enEjecucion
	 */
	public boolean isEnEjecucion() {
		return enEjecucion;
	}


	/**
	 * @param enEjecucion the enEjecucion to set
	 */
	public void setEnEjecucion(boolean enEjecucion) {
		this.enEjecucion = enEjecucion;
	}


	/**
	 * @return the interfazService
	 */
	public InterfazService getInterfazService() {
		return interfazService;
	}


	/**
	 * @param interfazService the interfazService to set
	 */
	public void setInterfazService(InterfazService interfazService) {
		this.interfazService = interfazService;
	}


	/**
	 * @return the esInterfazEntrada
	 */
	public boolean isEsInterfazEntrada() {
		return esInterfazEntrada;
	}


	/**
	 * @param esInterfazEntrada the esInterfazEntrada to set
	 */
	public void setEsInterfazEntrada(boolean esInterfazEntrada) {
		this.esInterfazEntrada = esInterfazEntrada;
	}


	/**
	 * @return the esInterfazSalida
	 */
	public boolean isEsInterfazSalida() {
		return esInterfazSalida;
	}


	/**
	 * @param esInterfazSalida the esInterfazSalida to set
	 */
	public void setEsInterfazSalida(boolean esInterfazSalida) {
		this.esInterfazSalida = esInterfazSalida;
	}


	/**
	 * @return the mostrarListaArchivosEntrada
	 */
	public boolean isMostrarListaArchivosEntrada() {
		return mostrarListaArchivosEntrada;
	}


	/**
	 * @param mostrarListaArchivosEntrada the mostrarListaArchivosEntrada to set
	 */
	public void setMostrarListaArchivosEntrada(boolean mostrarListaArchivosEntrada) {
		this.mostrarListaArchivosEntrada = mostrarListaArchivosEntrada;
	}


	/**
	 * @return the rootListaArchivosEntrada
	 */
	public TreeNode getRootListaArchivosEntrada() {
		return rootListaArchivosEntrada;
	}


	/**
	 * @param rootListaArchivosEntrada the rootListaArchivosEntrada to set
	 */
	public void setRootListaArchivosEntrada(TreeNode rootListaArchivosEntrada) {
		this.rootListaArchivosEntrada = rootListaArchivosEntrada;
	}


	/**
	 * @return the esPaquete
	 */
	public boolean isEsPaquete() {
		return esPaquete;
	}


	/**
	 * @param esPaquete the esPaquete to set
	 */
	public void setEsPaquete(boolean esPaquete) {
		this.esPaquete = esPaquete;
	}


	/**
	 * @return the esUnitaria
	 */
	public boolean isEsUnitaria() {
		return esUnitaria;
	}


	/**
	 * @param esUnitaria the esUnitaria to set
	 */
	public void setEsUnitaria(boolean esUnitaria) {
		this.esUnitaria = esUnitaria;
	}


	/**
	 * @return the esSeleccionable
	 */
	public boolean isEsSeleccionable() {
		return esSeleccionable;
	}


	/**
	 * @param esSeleccionable the esSeleccionable to set
	 */
	public void setEsSeleccionable(boolean esSeleccionable) {
		this.esSeleccionable = esSeleccionable;
	}

	/**
	 * @return the rootHorizontalListaInterfaz
	 */
	public TreeNode getRootHorizontalListaInterfaz() {
		return rootHorizontalListaInterfaz;
	}

	/**
	 * @param rootHorizontalListaInterfaz the rootHorizontalListaInterfaz to set
	 */
	public void setRootHorizontalListaInterfaz(TreeNode rootHorizontalListaInterfaz) {
		this.rootHorizontalListaInterfaz = rootHorizontalListaInterfaz;
	}

	/**
	 * @return the listaProcesoBatchActual
	 */
	public List getListaProcesoBatchActual() {
		return listaProcesoBatchActual;
	}

	/**
	 * @param listaProcesoBatchActual the listaProcesoBatchActual to set
	 */
	public void setListaProcesoBatchActual(List listaProcesoBatchActual) {
		this.listaProcesoBatchActual = listaProcesoBatchActual;
	}

	/**
	 * @return the mensajeEnEjecucion
	 */
	public String getMensajeEnEjecucion() {
		return mensajeEnEjecucion;
	}

	/**
	 * @param mensajeEnEjecucion the mensajeEnEjecucion to set
	 */
	public void setMensajeEnEjecucion(String mensajeEnEjecucion) {
		this.mensajeEnEjecucion = mensajeEnEjecucion;
	}

	/**
	 * @return the listaHistoricoInterfaz
	 */
	public List getListaHistoricoInterfaz() {
		return listaHistoricoInterfaz;
	}

	/**
	 * @param listaHistoricoInterfaz the listaHistoricoInterfaz to set
	 */
	public void setListaHistoricoInterfaz(List listaHistoricoInterfaz) {
		this.listaHistoricoInterfaz = listaHistoricoInterfaz;
	}

	/**
	 * @return the validacionPrevia
	 */
	public boolean isValidacionPrevia() {
		return validacionPrevia;
	}

	/**
	 * @param validacionPrevia the validacionPrevia to set
	 */
	public void setValidacionPrevia(boolean validacionPrevia) {
		this.validacionPrevia = validacionPrevia;
	}

	/**
	 * @return the mostrarBotonBuscar
	 */
	public boolean isMostrarBotonBuscar() {
		return mostrarBotonBuscar;
	}

	/**
	 * @param mostrarBotonBuscar the mostrarBotonBuscar to set
	 */
	public void setMostrarBotonBuscar(boolean mostrarBotonBuscar) {
		this.mostrarBotonBuscar = mostrarBotonBuscar;
	}

	/**
	 * @return the mostrarBotonExecute
	 */
	public boolean isMostrarBotonExecute() {
		return mostrarBotonExecute;
	}

	/**
	 * @param mostrarBotonExecute the mostrarBotonExecute to set
	 */
	public void setMostrarBotonExecute(boolean mostrarBotonExecute) {
		this.mostrarBotonExecute = mostrarBotonExecute;
	}

	/**
	 * @return the ejecucionEnHilo
	 */
	public boolean isEjecucionEnHilo() {
		return ejecucionEnHilo;
	}

	/**
	 * @param ejecucionEnHilo the ejecucionEnHilo to set
	 */
	public void setEjecucionEnHilo(boolean ejecucionEnHilo) {
		this.ejecucionEnHilo = ejecucionEnHilo;
	}

	/**
	 * @return the listaProcesosAdicionales
	 */
	public List<Interfaz> getListaProcesosAdicionales() {
		return listaProcesosAdicionales;
	}

	/**
	 * @param listaProcesosAdicionales the listaProcesosAdicionales to set
	 */
	public void setListaProcesosAdicionales(List<Interfaz> listaProcesosAdicionales) {
		this.listaProcesosAdicionales = listaProcesosAdicionales;
	}

	/**
	 * @return the listaProcesosAdicionalesSeleccionadas
	 */
	public List<Interfaz> getListaProcesosAdicionalesSeleccionadas() {
		return listaProcesosAdicionalesSeleccionadas;
	}

	/**
	 * @param listaProcesosAdicionalesSeleccionadas the listaProcesosAdicionalesSeleccionadas to set
	 */
	public void setListaProcesosAdicionalesSeleccionadas(
			List<Interfaz> listaProcesosAdicionalesSeleccionadas) {
		this.listaProcesosAdicionalesSeleccionadas = listaProcesosAdicionalesSeleccionadas;
	}

	/**
	 * @return the mostrarPaginacionDatatableSpinner
	 */
	public boolean isMostrarPaginacionDatatableSpinner() {
		return mostrarPaginacionDatatableSpinner;
	}

	/**
	 * @param mostrarPaginacionDatatableSpinner the mostrarPaginacionDatatableSpinner to set
	 */
	public void setMostrarPaginacionDatatableSpinner(
			boolean mostrarPaginacionDatatableSpinner) {
		this.mostrarPaginacionDatatableSpinner = mostrarPaginacionDatatableSpinner;
	}

	/**
	 * @return the nroPaginacionDatatable
	 */
	public int getNroPaginacionDatatable() {
		return nroPaginacionDatatable;
	}

	/**
	 * @param nroPaginacionDatatable the nroPaginacionDatatable to set
	 */
	public void setNroPaginacionDatatable(int nroPaginacionDatatable) {
		this.nroPaginacionDatatable = nroPaginacionDatatable;
	}

	/**
	 * @return the mostrarMensajeInterfazOK
	 */
	public boolean isMostrarMensajeInterfazOK() {
		return mostrarMensajeInterfazOK;
	}

	/**
	 * @param mostrarMensajeInterfazOK the mostrarMensajeInterfazOK to set
	 */
	public void setMostrarMensajeInterfazOK(boolean mostrarMensajeInterfazOK) {
		this.mostrarMensajeInterfazOK = mostrarMensajeInterfazOK;
	}

	/**
	 * @return the mostrarBotonActualizarDatos
	 */
	public boolean isMostrarBotonActualizarDatos() {
		return mostrarBotonActualizarDatos;
	}

	/**
	 * @param mostrarBotonActualizarDatos the mostrarBotonActualizarDatos to set
	 */
	public void setMostrarBotonActualizarDatos(boolean mostrarBotonActualizarDatos) {
		this.mostrarBotonActualizarDatos = mostrarBotonActualizarDatos;
	}

	/**
	 * @return the validarListaArchivosEntrada
	 */
	public boolean isValidarListaArchivosEntrada() {
		return validarListaArchivosEntrada;
	}

	/**
	 * @param validarListaArchivosEntrada the validarListaArchivosEntrada to set
	 */
	public void setValidarListaArchivosEntrada(boolean validarListaArchivosEntrada) {
		this.validarListaArchivosEntrada = validarListaArchivosEntrada;
	}

	/**
	 * @return the mensajeConfirmacionEjecucion
	 */
	public String getMensajeConfirmacionEjecucion() {
		return mensajeConfirmacionEjecucion;
	}

	/**
	 * @param mensajeConfirmacionEjecucion the mensajeConfirmacionEjecucion to set
	 */
	public void setMensajeConfirmacionEjecucion(String mensajeConfirmacionEjecucion) {
		this.mensajeConfirmacionEjecucion = mensajeConfirmacionEjecucion;
	}

	/**
	 * @return the indicadorActualizarProcesoBatchEnAction
	 */
	public String getIndicadorActualizarProcesoBatchEnAction() {
		return indicadorActualizarProcesoBatchEnAction;
	}

	/**
	 * @param indicadorActualizarProcesoBatchEnAction the indicadorActualizarProcesoBatchEnAction to set
	 */
	public void setIndicadorActualizarProcesoBatchEnAction(
			String indicadorActualizarProcesoBatchEnAction) {
		this.indicadorActualizarProcesoBatchEnAction = indicadorActualizarProcesoBatchEnAction;
	}

	
	
}
