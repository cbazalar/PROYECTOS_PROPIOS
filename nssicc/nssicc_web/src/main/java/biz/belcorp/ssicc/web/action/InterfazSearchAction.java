package biz.belcorp.ssicc.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.ComponenteInterfazPaquete;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.service.DelimitadorService;
import biz.belcorp.ssicc.service.FormatoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.LookupService;
import biz.belcorp.ssicc.service.ParametroInterfazService;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.TipoFormatoArchivoService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.form.InterfazForm;
import biz.belcorp.ssicc.web.form.InterfazSearchForm;
import biz.belcorp.ssicc.web.form.ParametroInterfazForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class InterfazSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -7048420666258651959L;
	private List listaSistemas;  
	private Boolean tieneEstructura;  
	
	private List listaSistemasMante;
	private List listaDelimitadores; 
	private List listaFormatos;
	private List listaTiposFormatoArchivo;
	private List listaExtensionesArchivo;
	private List listaExtensionesLogError;
		
	private DualListModel<Base> listaInterfaces;	
	protected DataTableModel dataTableParametros;
	protected Object[] parametrosInterfazSeleccionado;
	
	protected List parametrosInterfazForm;
	private List listInterfacesPaquete;
	
	@ManagedProperty(value="#{estructuraArchivoAction}")
	protected EstructuraArchivoAction estructuraArchivoAction;
	
	@ManagedProperty(value = "#{interfazComponentesPaqueteAction}")
	protected InterfazComponentesPaqueteAction interfazComponentesPaqueteAction;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoBASConfiguracionInterfazForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		InterfazSearchForm searchForm = new InterfazSearchForm();
		return searchForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}

		InterfazSearchForm searchForm = (InterfazSearchForm) this.formBusqueda;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);

		// La busqueda solo la realizaremos en las interfaces activas
		criteria.put("estado", Constants.ESTADO_ACTIVO);

		// Modificamos los valores que requieren el caracter '%'
		if (StringUtils.isNotBlank(searchForm.getCodigoSistema())) {
			criteria.put("codigoSistema", searchForm.getCodigoSistema());
		}
		if (StringUtils.isNotBlank(searchForm.getCodigoInterfaz())) {
			criteria.put("codigoInterfaz", searchForm.getCodigoInterfaz() + "%");
		}
		if (StringUtils.isNotBlank(searchForm.getTipoInterfaz())) {
			criteria.put("tipoInterfaz", searchForm.getTipoInterfaz());
		}
		if (StringUtils.isNotBlank(searchForm.getDescripcionInterfaz())) {
			criteria.put("descripcionInterfaz", searchForm
					.getDescripcionInterfaz()
					+ "%");
		}

		if (log.isDebugEnabled()) {
			log.debug(criteria.toString());
		}

		InterfazService service = (InterfazService) this.getBeanService("sisicc.interfazService");

		List lista = service.getInterfacesByCriteria(criteria);
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' method");
		}

		// Creamos las instancias de los objetos a usar
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Interfaz interfazSeleccionada = (Interfaz)this.beanRegistroSeleccionado;

		if (log.isDebugEnabled()) {
			log.debug("Id seleccionado de la lista: " + interfazSeleccionada.getCodigo());
		}
		
		// Todas las excepciones son capturadas por ActionExceptionHandler
		InterfazService service = (InterfazService) getBean("sisicc.interfazService");
		InterfazPK pk = new InterfazPK(interfazSeleccionada.getCodigoPais(), interfazSeleccionada.getCodigoSistema(), interfazSeleccionada.getCodigo());
		service.removeInterfaz(pk, usuario);
		return true;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		SistemaService service = (SistemaService) this.getBeanService("sisicc.sistemaService");

		InterfazSearchForm searchForm = (InterfazSearchForm) this.formBusqueda;
		searchForm.setCodigoSistema("");
        // Asignamos el codigo del pais de logeo
		searchForm.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());

		Map criteria = BeanUtils.describe(searchForm);
		// La busqueda solo la realizaremos en los sistemas activos
		criteria.put("estado", Constants.ESTADO_ACTIVO);
				
		this.listaSistemas = service.getSistemasByCriteria(criteria);
		
		this.mostrarBotonConsultar = false;
		this.setKeyMensajeAlertaDefault("interfazList.error.estructura");
		this.mostrarCabeceraFija = false;
		
		this.salirGrabarPantallaPadre = true;
		this.limpiarFindDatatable= false;
	}

	/**
	 * @return the listaSistemas
	 */
	public List getListaSistemas() {
		return listaSistemas;
	}

	/**
	 * @param listaSistemas the listaSistemas to set
	 */
	public void setListaSistemas(List listaSistemas) {
		this.listaSistemas = listaSistemas;
	}
	
	/**
	 * @return the tieneEstructura
	 */
	public Boolean getTieneEstructura() {
		return tieneEstructura;
	}

	/**
	 * @param tieneEstructura the tieneEstructura to set
	 */
	public void setTieneEstructura(Boolean tieneEstructura) {
		this.tieneEstructura = tieneEstructura;
	}

	/**
	 * Invoca al mantenimiento de Estructura de Archivos
	 * @param actionEvent
	 */
	public void estructuraArchivo(ActionEvent actionEvent){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'estructuraArchivo' method");
        }
        
        this.tieneEstructura = false;
		if (!this.verificarRegistroSeleccionado()) {
			return;
		}
		/* Redireccionando a la pagina respectiva */
		try{
			Interfaz interfazbusqueda = (Interfaz)this.beanRegistroSeleccionado;
			if(StringUtils.equals(interfazbusqueda.getTipoGeneracion(), Constants.TIPO_GENERACION_UNITARIA_DESCRIPCION)) { 
				this.accion = this.ACCION_MODIFICAR;
				this.tieneEstructura = true;
				this.estructuraArchivoAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
				this.estructuraArchivoAction.setInterfazbusqueda(interfazbusqueda);
				this.estructuraArchivoAction.edit(actionEvent);
				this.redireccionarPagina("mantenimientoBASEstructuraArchivoForm");
			}	
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		log.debug("Valor tieneEstructura = " + this.tieneEstructura);
		return;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoBASConfiguracionInterfazList";
	}


	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		boolean resultado = false;
		// Extraemos atributos y parmetros a usar
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		boolean isNew = interfazForm.isNewRecord();

		// Extreamos el usuario de la sesin
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		InterfazService service = (InterfazService) getBean("sisicc.interfazService");
		ParametroInterfazService pService = (ParametroInterfazService) getBean("sisicc.parametroInterfazService");

		String valor = interfazForm.getTipoGeneracion();
		
		if(log.isDebugEnabled())
			log.debug("VALOR DE LA GENERACION " + valor);
		
		Interfaz interfaz = new Interfaz();
		if (StringUtils.isBlank(interfazForm.getFlagEnvioArchivo())) {
			interfazForm.setFlagDirectorioEntradaSalida(null);
			interfazForm.setFlagDirectorioHistorico(null);
			interfazForm.setFlagDirectorioLog(null);
		}
		else {
			if(!interfazForm.getFlagEnvioArchivo().equalsIgnoreCase(Constants.ENVIO_MIXTO)){
				interfazForm.setFlagDirectorioEntradaSalida(null);
				interfazForm.setFlagDirectorioHistorico(null);
				interfazForm.setFlagDirectorioLog(null);
			}		
		}
		BeanUtils.copyProperties(interfaz, interfazForm);

		try {
			// Si se esta insertando
			if (this.accion.equals(this.ACCION_NUEVO)) {
				// Si es una interfaz de tipo paquete
				if (valor.equalsIgnoreCase(Constants.NO)) {
					interfaz.setTipoGeneracion(Constants.TIPO_GENERACION_PAQUETE);
					interfaz.setExtensionArchivo(Constants.CODIGO_EXTENSION_ARCHIVO_TXT);
					interfaz.setExtensionLogErrores(Constants.CODIGO_EXTENSION_ARCHIVO_ERR);
					
					// Insertamos la interface
					service.insertInterfaz(interfaz, usuario);

					//Insertamos los componentes
					insertarComponentes(service, interfaz, usuario);
				}
				// Si es una interfaz unitaria
				else {
					interfaz.setTipoGeneracion(Constants.TIPO_GENERACION_UNITARIA);
					service.insertInterfaz(interfaz, usuario);
				}
			}
			// Si estamos modificando
			else {
				// Si es una interfaz de tipo paquete
				if (valor.equalsIgnoreCase(Constants.NO)) {
					interfaz.setTipoGeneracion(Constants.TIPO_GENERACION_PAQUETE);
					interfaz.setExtensionArchivo(Constants.CODIGO_EXTENSION_ARCHIVO_TXT);
					interfaz.setExtensionLogErrores(Constants.CODIGO_EXTENSION_ARCHIVO_ERR);

					// Actualizamos la informacion de la interfaz
					service.updateInterfaz(interfaz, usuario);

					// Creamos la referencia a la llave primaria
					InterfazPK pk = new InterfazPK(interfaz.getCodigoPais(), interfaz.getCodigoSistema(), interfaz.getCodigo());

					// Primero eliminamos los componentes de la interfaz
					service.removeComponentesInterfazPaquete(pk);

					// e insertamos los componentes de la interfaz
					insertarComponentes(service, interfaz, usuario);
				} else {
					interfaz.setTipoGeneracion(Constants.TIPO_GENERACION_UNITARIA);
					service.updateInterfaz(interfaz, usuario);
				}
			}

			// Agregamos los parametros
			InterfazPK pk = new InterfazPK(interfaz.getCodigoPais(), interfaz.getCodigoSistema(), interfaz.getCodigo());
			// Primero eliminamos los existentes.
			pService.removeParametrosByPKInterfaz(pk);
			// Luego insertamos
            if (this.parametrosInterfazForm != null && this.parametrosInterfazForm.size()>0) {
            	for(int i=0; i<parametrosInterfazForm.size(); i++)
            	{
            		ParametroInterfazForm parametroInterfazForm = (ParametroInterfazForm)parametrosInterfazForm.get(i);            		
					ParametroInterfaz parametro = new ParametroInterfaz();
					
					BeanUtils.copyProperties(parametro, parametroInterfazForm);
					parametro.setCodigoPais(interfaz.getCodigoPais());
					parametro.setCodigoSistema(interfaz.getCodigoSistema());
					parametro.setCodigoInterfaz(interfaz.getCodigo());
					parametro.setCodigo(StringUtils.right((new Integer(101 + i)).toString(), 2));
					parametro.setEstado(Constants.ESTADO_ACTIVO);
					pService.insertParametroInterfaz(parametro, usuario);
    			} 
    		}
            
			resultado = true;
			
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
		}
		
		return resultado;		
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		InterfazForm interfazForm = new InterfazForm();

		// Obtenemos ls referencias a los services
		SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
		DelimitadorService delimitadorService = (DelimitadorService) getBean("sisicc.delimitadorService");
		FormatoService formatoService = (FormatoService) getBean("sisicc.formatoService");
		TipoFormatoArchivoService tipoFormatoArchivoService = (TipoFormatoArchivoService) getBean("sisicc.tipoFormatoArchivoService");
		InterfazService service = (InterfazService) getBean("sisicc.interfazService");
		LookupService lookupService = (LookupService) getBean("lookupService");

        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        interfazForm.setCodigoPais(pais.getCodigo());
        Sistema sistema = new Sistema();
        sistema.setCodigoPais(pais.getCodigo());
        
        this.listaSistemasMante = sistemaService.getSistemas(sistema);
        this.listaDelimitadores = delimitadorService.getDelimitadores(null);
        this.listaFormatos = formatoService.getFormatos(null);
        this.listaTiposFormatoArchivo = tipoFormatoArchivoService.getTiposFormatoArchivo(null);
        this.listaExtensionesArchivo = lookupService.getExtensionesArchivo();
        this.listaExtensionesLogError = lookupService.getExtensionesLog();
        	
        interfazForm.setFlagHabilitado(Constants.SI);
        interfazForm.setTipoGeneracion(Constants.SI);
        interfazForm.setFlagEnvioArchivo(Constants.ENVIO_FTP);
        interfazForm.setFlagExtensionArchivo(Constants.NO);
        interfazForm.setFlagDelimitadorCampos(Constants.NO);
        interfazForm.setFlagFormatoArchivo(Constants.NO);
        interfazForm.setFlagLogErrores(Constants.NO);
        interfazForm.setFlagTipoFormato("");
        interfazForm.setTipoNombreArchivo(Constants.ARCHIVO_FIJO);
        interfazForm.setFlagComprimido(Constants.SI);
        interfazForm.setFlagProceso(Constants.SI);
        
             
        if (!this.accion.equals(this.ACCION_NUEVO) ) 
        {
        	Interfaz interfazbusqueda = (Interfaz)this.beanRegistroSeleccionado;
        	String codigo = interfazbusqueda.getCodigo();
        	String codigoPais = interfazbusqueda.getCodigoPais();
        	String codigoSistema = interfazbusqueda.getCodigoSistema();
        	
        	if(!StringUtils.isBlank(codigo) && !StringUtils.isBlank(codigoPais) && !StringUtils.isBlank(codigoSistema))
        	{
    			if (log.isDebugEnabled()) {
    				log.debug("Id seleccionado de la lista: " + codigo + " " + codigoPais + " " + codigoSistema);
    			}
    			// Creamos el PK de la interfaz
    			InterfazPK pk = new InterfazPK(codigoPais, codigoSistema, codigo);

    			// Obtenemos la informacion de la interfaz
    			Interfaz interfaz = service.getInterfaz(pk);

    			// Copiamos los atributos del bean al form
    			BeanUtils.copyProperties(interfazForm, interfaz);
    			if (interfaz.getTipoGeneracion().equalsIgnoreCase(Constants.TIPO_GENERACION_UNITARIA))
    				interfazForm.setTipoGeneracion(Constants.SI);
    			if (interfaz.getTipoGeneracion().equalsIgnoreCase(Constants.TIPO_GENERACION_PAQUETE))
    				interfazForm.setTipoGeneracion(Constants.NO);

    			interfazForm.setFlagExtensionArchivoBool(StringUtils.equals(interfazForm.getFlagExtensionArchivo(), Constants.SI));
    			interfazForm.setFlagLogErroresBool(StringUtils.equals(interfazForm.getFlagLogErrores(), Constants.SI));
    			interfazForm.setFlagDelimitadorCamposBool(StringUtils.equals(interfazForm.getFlagDelimitadorCampos(), Constants.SI));
    			interfazForm.setFlagFormatoArchivoBool(StringUtils.equals(interfazForm.getFlagFormatoArchivo(), Constants.SI));
    			
    			List listaTarget = service.getInterfacesEmpaquetadas(interfaz, true);
    			List listaSource = service.getInterfacesEmpaquetadas(interfaz, false);

    			this.listaInterfaces = new DualListModel<Base>(listaSource, listaTarget);
    			this.listInterfacesPaquete = new ArrayList();
    			if (interfaz.getTipoGeneracion().equalsIgnoreCase(Constants.TIPO_GENERACION_PAQUETE)) {
    				InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");
    				this.listInterfacesPaquete = interfazService.getComponentesInterfazPaquete(pk);
    			}
    			
    			
    			// Convertimos los parametros
    			List parametros = interfaz.getParametros();
    			List parametrosInterfaz = new ArrayList();
    			this.parametrosInterfazForm = new ArrayList();
    			this.dataTableParametros = new DataTableModel(this.parametrosInterfazForm);
    			
    			if(parametros != null && parametros.size() > 0)
    			{
        			for(int i=0; i<parametros.size(); i++)
        			{
        				ParametroInterfazForm param = new ParametroInterfazForm();
        				BeanUtils.copyProperties(param, parametros.get(i));
        				parametrosInterfaz.add(param);
        			}
        			
        			this.parametrosInterfazForm = parametrosInterfaz; 
        			this.dataTableParametros = new DataTableModel(this.parametrosInterfazForm);
        			
    			}
    			
    			interfazForm.setNewRecord(false);
        	}
        }
		
        return interfazForm;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setAddAttributes()
	 */
	@Override
	protected void setAddAttributes() throws Exception {
		// TODO Auto-generated method stub
		super.setAddAttributes();
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
        String codigoSistema = interfazForm.getCodigoSistema();
    	if (StringUtils.isBlank(codigoSistema)) {
    		if (this.listaSistemasMante.size() > 0) {
    			Sistema beanSistema = (Sistema) this.listaSistemasMante.get(0);
    			interfazForm.setCodigoSistema(beanSistema.getCodigo());
    		}	
    	}
    	if (StringUtils.isNotBlank(interfazForm.getCodigoSistema())) {
    		this.generarCodigoInterfazPorSistema(interfazForm, interfazForm.getCodigoSistema());
    	}
    	this.parametrosInterfazForm = new ArrayList();
    	this.dataTableParametros = new DataTableModel(this.parametrosInterfazForm);
 	}

	/**
	 * @param service
	 * @param interfaz
	 * @param usuario
	 */
	public void insertarComponentes(InterfazService service, Interfaz interfaz, Usuario usuario)
	{
		if (this.listaInterfaces.getTarget() != null && this.listaInterfaces.getTarget().size() > 0)
		{
			for(int i= 0; i < this.listaInterfaces.getTarget().size(); i++ )
			{
				Base interfazSeleccionada = (Base)this.listaInterfaces.getTarget().get(i);
				
				if(!StringUtils.isBlank(interfazSeleccionada.getCodigo()))
				{
					// Creamos la instancia del objeto
					ComponenteInterfazPaquete componente = new ComponenteInterfazPaquete();
					// Asignamos los valores correspondientes
					componente.setCodigoPais(interfaz.getCodigoPais());
					componente.setCodigoSistema(interfaz.getCodigoSistema());
					componente.setCodigoInterfazPaquete(interfaz.getCodigo());
					componente.setCodigoInterfazUnitaria(interfazSeleccionada.getCodigo());
					componente.setOrdenEjecucion(i);
					componente.setOrdenHilo(new Long(0));
					componente.setNivelHilo(new Long(1)); 
					int indice = this.buscarInterfazEnListaPaquete(interfazSeleccionada.getCodigo());
					if (indice >=0) {
						Interfaz interfazPaquete = (Interfaz) this.listInterfacesPaquete.get(indice);
						componente.setOrdenHilo(interfazPaquete.getOrdenHilo());
						componente.setNivelHilo(interfazPaquete.getNivelHilo());
					}
					// Insertamos el componente
					service.insertComponenteInterfazPaquete(componente,usuario);
				}
			}
		}
	}
	
	private int buscarInterfazEnListaPaquete(String codigoInterfaz) {
		int retorno = -1;
		for (int i=0; i < this.listInterfacesPaquete.size(); i++) {
			Interfaz interfaz = (Interfaz) this.listInterfacesPaquete.get(i);
			if (interfaz.getCodigo().equals(codigoInterfaz)) 
				return i;
		}
		return retorno;
	}
	
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		boolean isNew = interfazForm.isNewRecord();
		if(isNew){
			return "interfaz.added";
		}else{
			return "interfaz.updated";
		}	
	}
	
	
	/**
	 * @param sistemaSeleccionado
	 */
	public void generarCodigoInterfazPorSistema(InterfazForm interfazForm, String sistemaSeleccionado)
	{
		if(log.isDebugEnabled())
			log.debug("generarCodigoInterfazPorSistema");

		if(!StringUtils.isBlank(sistemaSeleccionado))
		{
			interfazForm.setCodigoSistema(sistemaSeleccionado);
		
			if(log.isDebugEnabled())
			{
				log.debug("sistemaSeleccionado seleccionado: " + sistemaSeleccionado);
				
				log.debug(interfazForm.getCodigoPais());
				log.debug(interfazForm.getCodigoSistema());
				log.debug(interfazForm.getTipoGeneracion());
			}
			
			interfazForm.setCodigo(this.generarCodigoInterfaz());
		}
		else
		{
			interfazForm.setCodigoSistema("");
			interfazForm.setCodigo("");
		}
		
		cargarPickList();
	}
	
	
	/**
	 * @param e
	 */
	public void generarCodigoInterfazPorSistema(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("generarCodigoInterfazPorSistema");

		String sistemaSeleccionado = (String)e.getNewValue();
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		this.generarCodigoInterfazPorSistema(interfazForm, sistemaSeleccionado);
	}

	/**
	 * @param e
	 */
	public void generarCodigoInterfazPorTipoGeneracion(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("generarCodigoInterfazPorTipoGeneracion");

		String tipo = (String)e.getNewValue();
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		interfazForm.setTipoGeneracion(tipo);

		if(log.isDebugEnabled())
		{
			log.debug("Tipo seleccionado: " + tipo);
			
			log.debug(interfazForm.getCodigoPais());
			log.debug(interfazForm.getCodigoSistema());
			log.debug(interfazForm.getTipoGeneracion());
		}
		
		interfazForm.setCodigo(generarCodigoInterfaz());
		
		//Cargamos los datos del picklist
		cargarPickList();
	}
	
	/**
	 * @param e
	 */
	public void modificarCodigoInterfaz(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("modificarCodigoInterfaz");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;		
		interfazForm.setFlagModificarBool(((Boolean)e.getNewValue()).booleanValue());
	}
	
	/**
	 * @param e
	 */
	public void cambiarTipoFormatoArchivo(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarTipoFormatoArchivo");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		String tipoFormatoArchivo = (String)e.getNewValue();
		interfazForm.setTipoFormatoArchivo(tipoFormatoArchivo);
		
		if(!StringUtils.equals(tipoFormatoArchivo, Constants.ARCHIVO_SEPARADOR)){
			interfazForm.setFlagDelimitadorCampos(Constants.NO);
			interfazForm.setCodigoDelimitador("");
			interfazForm.setFlagFormatoArchivo(Constants.NO);
			interfazForm.setCodigoFormato("");
			interfazForm.setFlagTipoFormato(Constants.NO);
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarFlagExtensionArchivo(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarFlagExtensionArchivo");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		Boolean flagExtensionArchivo = ((Boolean)e.getNewValue()).booleanValue();
		
		interfazForm.setFlagExtensionArchivoBool(flagExtensionArchivo);
		interfazForm.setFlagExtensionArchivo(flagExtensionArchivo ? Constants.SI:Constants.NO);
		
		if(!flagExtensionArchivo)
		{
			interfazForm.setExtensionArchivo(null);
			interfazForm.setNombreEtiquetaPrincipalXML(null);
			interfazForm.setNombreEtiquetaRegistroXML(null);
		}
		
		if(log.isDebugEnabled())
		{
			log.debug("flagExtensionArchivo: " + flagExtensionArchivo);
			log.debug("flagExtensionArchivo: " + interfazForm.getFlagExtensionArchivo());			
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarFlagLogErrores(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarFlagLogErrores");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		Boolean flagLogErrores = ((Boolean)e.getNewValue()).booleanValue();
		
		interfazForm.setFlagLogErroresBool(flagLogErrores);
		interfazForm.setFlagLogErrores(flagLogErrores ? Constants.SI:Constants.NO);
		
		if(!flagLogErrores)
		{
			interfazForm.setExtensionLogErrores("");
		}
		
		if(log.isDebugEnabled())
		{
			log.debug("flagLogErrores: " + flagLogErrores);
			log.debug("flagLogErrores: " + interfazForm.getFlagLogErrores());			
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarFlagDelimitadorCampos(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarFlagDelimitadorCampos");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		Boolean flagDelimitadorCampos = ((Boolean)e.getNewValue()).booleanValue();
		
		interfazForm.setFlagDelimitadorCamposBool(flagDelimitadorCampos);
		interfazForm.setFlagDelimitadorCampos(flagDelimitadorCampos ? Constants.SI:Constants.NO);
		
		if(!flagDelimitadorCampos)
		{
			interfazForm.setCodigoDelimitador("");			
		}		
		
		if(log.isDebugEnabled())
		{
			log.debug("flagDelimitadorCampos: " + flagDelimitadorCampos);
			log.debug("flagDelimitadorCampos: " + interfazForm.getFlagDelimitadorCampos());			
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarFlagFormatoArchivo(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarFlagFormatoArchivo");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		Boolean flagFormatoArchivo = ((Boolean)e.getNewValue()).booleanValue();
		
		interfazForm.setFlagFormatoArchivoBool(flagFormatoArchivo);
		interfazForm.setFlagFormatoArchivo(flagFormatoArchivo ? Constants.SI:Constants.NO);
		
		if(!flagFormatoArchivo)
		{
			interfazForm.setCodigoFormato("");
			interfazForm.setFlagTipoFormato("");
		}
		
		if(log.isDebugEnabled())
		{
			log.debug("flagFormatoArchivo: " + flagFormatoArchivo);
			log.debug("flagFormatoArchivo: " + interfazForm.getFlagFormatoArchivo());			
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarExtensionArchivo(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarExtensionArchivo");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		String extension = (String)e.getNewValue();
		
		if(!StringUtils.equals(extension, Constants.CODIGO_EXTENSION_ARCHIVO_XML))
		{
			interfazForm.setNombreEtiquetaPrincipalXML("");
			interfazForm.setNombreEtiquetaRegistroXML("");
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarFlagEnvioArchivo(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarFlagEnvioArchivo");
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		
		String flagEnvioArchivo = (String)e.getNewValue();
		
		if(StringUtils.equals(flagEnvioArchivo, Constants.ENVIO_RED))
		{
			interfazForm.setServidorFtp("");
			interfazForm.setPuertoFtp("");
			interfazForm.setUsuarioFtp("");
			interfazForm.setPasswordFtp("");
		}
	}
	
	/**
	 * @param e
	 */
	public void cambiarTipo(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarTipo");
	}
	
	/**
	 * @return
	 */
	private String generarCodigoInterfaz()
	{
		if(log.isDebugEnabled())
			log.debug("generarCodigoInterfaz");
		
		
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		return this.generarCodigoInterfaz(interfazForm);
	}
	
	/**
	 * @param interfazForm
	 * @return
	 */
	private String generarCodigoInterfaz(InterfazForm interfazForm)
	{
		if(log.isDebugEnabled())
			log.debug("generarCodigoInterfaz");
		
		String codigo = "";
		
		if(!StringUtils.isBlank(interfazForm.getCodigoPais()) && !StringUtils.isBlank(interfazForm.getCodigoSistema()) && !StringUtils.isBlank(interfazForm.getTipoGeneracion()))
		{
			InterfazService service = (InterfazService) getBean("sisicc.interfazService");
			
			String tipo = Constants.TIPO_GENERACION_PAQUETE;
			
			if(StringUtils.equals(interfazForm.getTipoGeneracion(), Constants.SI))
				tipo = Constants.TIPO_GENERACION_UNITARIA;
			
			codigo = service.getNuevoCodigo(interfazForm.getCodigoPais(), interfazForm.getCodigoSistema(), tipo);
			
			if(StringUtils.isBlank(codigo))
			{
				if(StringUtils.equals(tipo, Constants.TIPO_GENERACION_UNITARIA)){
					codigo = String.format("%s-1", interfazForm.getCodigoSistema());
				}
				else if(StringUtils.equals(tipo, Constants.TIPO_GENERACION_PAQUETE)){
					codigo = String.format("%s-P1", interfazForm.getCodigoSistema());
				}
			}
		}
		
		if(log.isDebugEnabled())
			log.debug("codigo: " + codigo);
		
		return codigo;
	}
	
	/**
	 * 
	 */
	private void cargarPickList() 	{
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		if(StringUtils.equals(interfazForm.getTipoGeneracion(), Constants.NO)) //tipo = N = PAQUETE
		{
			InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");
			
			List listaInterfacesSistemaSourceLV = interfazService.getInterfacesBySistema(interfazForm.getCodigoPais(), interfazForm.getCodigoSistema());
			List listaInterfacesSistemaSource = (List<Base>)new ArrayList();
			
			//Cambiamos de LabelValue a Base			
			if(listaInterfacesSistemaSourceLV != null && listaInterfacesSistemaSourceLV.size() > 0)
			{
				for(int i=0; i<listaInterfacesSistemaSourceLV.size(); i++)
				{
					Base bc = new Base();
					LabelValue lv = (LabelValue)listaInterfacesSistemaSourceLV.get(i);
					bc.setCodigo(lv.getValue());
					bc.setDescripcion(lv.getLabel());
					listaInterfacesSistemaSource.add(bc);
				}
			}
						
			//
			List listaInterfacesPaqueteTarget = (List<Base>)new ArrayList();
			this.listaInterfaces = new DualListModel<Base>(listaInterfacesSistemaSource, listaInterfacesPaqueteTarget);
			//			
		}		
	}
	
	
	
	public void dummy(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'dummy' method");
        }
	}  

	/**
	 * Agregando Parametros a la Interfaz
	 */
	public void addParametros(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'addParametros' method");
        }
        
        InterfazForm interfazForm = (InterfazForm)this.formMantenimiento;  
        // Extraemos atributos y parámetros a usar  
        ParametroInterfazForm parametro = new ParametroInterfazForm();
        parametro.setNombre(interfazForm.getNombreParametro());
        parametro.setValor(interfazForm.getValorParametro());
        parametro.setEstado(Constants.ESTADO_ACTIVO);
                        
        if (this.parametrosInterfazForm != null) {
        	this.parametrosInterfazForm.add(parametro);            
        }
        else {
        	this.parametrosInterfazForm = new ArrayList();
        	this.parametrosInterfazForm.add(parametro);            
        }
        
        if (log.isDebugEnabled()) {
            log.debug("Nro de Parametros: " + (this.parametrosInterfazForm == null ? 0 : this.parametrosInterfazForm.size()));
        }
        
        interfazForm.setNombreParametro("");
        interfazForm.setValorParametro("");
        
        this.formMantenimiento = interfazForm;
    	this.dataTableParametros = new DataTableModel(this.parametrosInterfazForm);
	}
	
	/**
	 * Removiendo Parametros de la Interfaz
	 */
	public void removeParametros(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'removeParametros' method");
        }        

        if(verificarRegistrosSeleccionado()){
        	Object[] parametros = this.parametrosInterfazSeleccionado;
        	for(int i=0; i < parametros.length; i++){
   				this.parametrosInterfazForm.remove((ParametroInterfazForm)parametrosInterfazSeleccionado[i]);
        	}        	
        }
        this.parametrosInterfazSeleccionado = null;               
        this.dataTableParametros = new DataTableModel(this.parametrosInterfazForm);
	}
	
	
	
	
	/**
	 * Metodo que verifica si se ha seleccionado algun registro del Datatable de Parametros
	 * @return
	 */
	protected final boolean verificarRegistrosSeleccionado() {
		boolean verificar= true;		 
		try {
			if (this.parametrosInterfazSeleccionado.length <= 0)
				verificar = false;
		}	
		catch (Exception e) {		
			verificar = false;
		}
		if (!verificar) 
			this.addWarn("Warning: ", this.getResourceMessage("errors.sin.registros"));
		return verificar;
	}
	
	
	/**
	 * @param actionEvent
	 */
	public void insertarParametroInterfaz(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) { 
			log.warn("Entering 'insertarParametroInterfaz' method");
		}
		InterfazForm interfazForm = (InterfazForm) this.formMantenimiento;
		String nombre = interfazForm.getNombreParametro();
		String valor = interfazForm.getValorParametro();
		if (StringUtils.isBlank(nombre) || StringUtils.isBlank(valor)) {
			String error = this.getResourceMessage("interfazForm.error.parametro");
			this.setMensajeAlertaDefault(error);
			this.mostrarDialogoGeneral();
			return;
		}
		this.addParametros();
		return;
	}
	
	/**
	 * @param actionEvent
	 */
	public void eliminarParametroInterfaz(ActionEvent actionEvent) {
		if (log.isWarnEnabled()) { 
			log.warn("Entering 'eliminarParametroInterfaz' method");
		}
		if (this.parametrosInterfazSeleccionado == null || this.parametrosInterfazSeleccionado.length <= 0) {
			String error = this.getResourceMessage("errors.select.item");
			this.setMensajeAlertaDefault(error);
			this.mostrarDialogoGeneral();
			return;
		}
			
		this.removeParametros();
		return;
	}
	
	/**
	 *Setea valores para redireccionar 
	 *a otra pantalla(mantenimientoBASComponentesPaqueteForm) 
	 * 
	 * @param event
	 */
	public void redireccion(ActionEvent event){				
		try {
			
			if(!this.esPaquete())
				return;
			
			this.interfazComponentesPaqueteAction.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
			this.interfazComponentesPaqueteAction.setAccion(Constants.NUEVO);
			this.interfazComponentesPaqueteAction.add(event);
			this.redireccionarPagina("mantenimientoBASComponentesPaqueteForm");
			this.beanRegistroSeleccionado = new Object();
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * Valida seleccion de registro y
	 * que sea de tipo paquete
	 * 
	 * @return
	 */
	public Boolean esPaquete(){
		
		Boolean flag = true;

		if (this.beanRegistroSeleccionado == null) {
			flag = false;
			this.addWarn("", this.getResourceMessage("errors.select.item"));
		} else {
			Interfaz interfaz = (Interfaz) this.beanRegistroSeleccionado;
			if (!StringUtils.equals(interfaz.getTipoGeneracion(), Constants.PAQUETE)) {
				flag = false;
				this.addWarn("", this.getResourceMessage("interfazComponentesPaqueteForm.validacion.tipo.paquete"));
			}
		}
		return flag;	
	}
	
	
	public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    } 
	
	
	/* GET - SET ATRIBUTOS */

	/**
	 * @return the listaSistemasMante
	 */
	public List getListaSistemasMante() {
		return listaSistemasMante;
	}

	/**
	 * @param listaSistemasMante the listaSistemasMante to set
	 */
	public void setListaSistemasMante(List listaSistemasMante) {
		this.listaSistemasMante = listaSistemasMante;
	}

	/**
	 * @return the listaDelimitadores
	 */
	public List getListaDelimitadores() {
		return listaDelimitadores;
	}

	/**
	 * @param listaDelimitadores the listaDelimitadores to set
	 */
	public void setListaDelimitadores(List listaDelimitadores) {
		this.listaDelimitadores = listaDelimitadores;
	}

	/**
	 * @return the listaFormatos
	 */
	public List getListaFormatos() {
		return listaFormatos;
	}

	/**
	 * @param listaFormatos the listaFormatos to set
	 */
	public void setListaFormatos(List listaFormatos) {
		this.listaFormatos = listaFormatos;
	}

	/**
	 * @return the listaTiposFormatoArchivo
	 */
	public List getListaTiposFormatoArchivo() {
		return listaTiposFormatoArchivo;
	}

	/**
	 * @param listaTiposFormatoArchivo the listaTiposFormatoArchivo to set
	 */
	public void setListaTiposFormatoArchivo(List listaTiposFormatoArchivo) {
		this.listaTiposFormatoArchivo = listaTiposFormatoArchivo;
	}

	/**
	 * @return the listaExtensionesArchivo
	 */
	public List getListaExtensionesArchivo() {
		return listaExtensionesArchivo;
	}

	/**
	 * @param listaExtensionesArchivo the listaExtensionesArchivo to set
	 */
	public void setListaExtensionesArchivo(List listaExtensionesArchivo) {
		this.listaExtensionesArchivo = listaExtensionesArchivo;
	}

	/**
	 * @return the listaExtensionesLogError
	 */
	public List getListaExtensionesLogError() {
		return listaExtensionesLogError;
	}

	/**
	 * @param listaExtensionesLogError the listaExtensionesLogError to set
	 */
	public void setListaExtensionesLogError(List listaExtensionesLogError) {
		this.listaExtensionesLogError = listaExtensionesLogError;
	}

	/**
	 * @return the listaInterfaces
	 */
	public DualListModel<Base> getListaInterfaces() {
		return listaInterfaces;
	}

	/**
	 * @param listaInterfaces the listaInterfaces to set
	 */
	public void setListaInterfaces(DualListModel<Base> listaInterfaces) {
		this.listaInterfaces = listaInterfaces;
	}

	/**
	 * @return the dataTableParametros
	 */
	public DataTableModel getDataTableParametros() {
		return dataTableParametros;
	}

	/**
	 * @param dataTableParametros the dataTableParametros to set
	 */
	public void setDataTableParametros(DataTableModel dataTableParametros) {
		this.dataTableParametros = dataTableParametros;
	}

	/**
	 * @return the parametrosInterfazSeleccionado
	 */
	public Object[] getParametrosInterfazSeleccionado() {
		return parametrosInterfazSeleccionado;
	}

	/**
	 * @param parametrosInterfazSeleccionado the parametrosInterfazSeleccionado to set
	 */
	public void setParametrosInterfazSeleccionado(
			Object[] parametrosInterfazSeleccionado) {
		this.parametrosInterfazSeleccionado = parametrosInterfazSeleccionado;
	}

	/**
	 * @return the parametrosInterfazForm
	 */
	public List getParametrosInterfazForm() {
		return parametrosInterfazForm;
	}

	/**
	 * @param parametrosInterfazForm the parametrosInterfazForm to set
	 */
	public void setParametrosInterfazForm(List parametrosInterfazForm) {
		this.parametrosInterfazForm = parametrosInterfazForm;
	}

	/**
	 * @return the estructuraArchivoAction
	 */
	public EstructuraArchivoAction getEstructuraArchivoAction() {
		return estructuraArchivoAction;
	}

	/**
	 * @param estructuraArchivoAction the estructuraArchivoAction to set
	 */
	public void setEstructuraArchivoAction(
			EstructuraArchivoAction estructuraArchivoAction) {
		this.estructuraArchivoAction = estructuraArchivoAction;
	}

	/**
	 * @return the interfazComponentesPaqueteAction
	 */
	public InterfazComponentesPaqueteAction getInterfazComponentesPaqueteAction() {
		return interfazComponentesPaqueteAction;
	}

	/**
	 * @param interfazComponentesPaqueteAction the interfazComponentesPaqueteAction to set
	 */
	public void setInterfazComponentesPaqueteAction(
			InterfazComponentesPaqueteAction interfazComponentesPaqueteAction) {
		this.interfazComponentesPaqueteAction = interfazComponentesPaqueteAction;
	}
	
}
