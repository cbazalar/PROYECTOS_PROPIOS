package biz.belcorp.ssicc.web.spusicc.fdv.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.model.ProcesoFDVDistribucionMeta;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVClusterizacionService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileNoreService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileSeccService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileVariService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVFileZonaService;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidConsolidatedProcessException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidTransactionProcessFileException;
import biz.belcorp.ssicc.service.spusicc.fdv.exception.InvalidUploadException;
import biz.belcorp.ssicc.service.util.ConstantsList;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.fdv.form.MantenimientoFDVClusterizacionForm;
import biz.belcorp.ssicc.web.spusicc.fdv.form.MantenimientoFDVClusterizacionSearchForm;
import biz.belcorp.ssicc.web.spusicc.fdv.form.MantenimientoParametroProcesoFDVForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoFDVClusterizacionSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5731095534798983683L;
	
	//pantalla principal
	private List procesosClusterList;	
	private UploadedFile zonaFile;
    private UploadedFile seccionFile;
    private UploadedFile variablesExogFile;
    private UploadedFile noReconstruidaFile;
    private String directorio;
    
    //pantalla parametros
    private MantenimientoParametroProcesoFDVForm parametrosForm;
    private String editParams;
    
	//pantalla proceso de clusterizacion
    private List fdvProcessPeriodList;
    private List fdvGroupPoblationList;
    private DataTableModel listaZonaModel;
    private DataTableModel listazoneNoReliableDataModel;
    private List columnasSeleccionadas;
    private List columnasNoReliableDataSeleccionadas;
    private boolean stFlaPobl;
    private boolean stFlaNse;
   	private boolean stFlaRlur;
   	private boolean stFlaVar1;
   	private boolean stFlaVar2;
   	private boolean stClose;
   	private boolean stFlaClus;
   	
   	//pantalla Asignar/Modificar Zonas
   	private List fdvCluAsigSistList;
   	private DataTableModel listazoneAsigSistModel;
   	private DataTableModel listazoneAsigPaisModel;
   	private List zonaSistema;
   	private List zonaAsignar;
   	
   	//pantalla de distribucion de metas
   	private DataTableModel listDistModel;
   	private DataTableModel listSeccModel;
   	private DataTableModel zoneNoReliableDataVarVentaModel;
   	private List zoneNoRealiableDataVarVenta;
   	private List columnasNoReliableDataVarVentaSeleccionadas;
   	private List listDist;
   	private List listSecc;
   	
   	//pantalla ajustar meta
   	private DataTableModel listZonaAjustModel;
   	private List listZonaAjust;
   	
   	// pantalla Listado de Distribución de Metas
	@ManagedProperty(value="#{consultaFDVDistribucionMetaAction}")
	private ConsultaFDVDistribucionMetaAction consultaFDVDistribucionMetaAction;
	private List listCamp;
	

	@Override
	protected String getSalirForward() 
	{
		return "mantenimientoFDVClusterizacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "mantenimientoFDVClusterizacionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFDVClusterizacionSearchForm searchForm = new MantenimientoFDVClusterizacionSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoFDVClusterizacionSearchForm searchForm = (MantenimientoFDVClusterizacionSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(searchForm);

        if(StringUtils.isNotBlank(searchForm.getNombreProceso())) {
            criteria.put("nomProc", "%" + searchForm.getNombreProceso() + "%");
        }
        
        if(StringUtils.isNotBlank(searchForm.getCodigoPais())) {
            criteria.put("codigoPais", searchForm.getCodigoPais());
        }else{
        	criteria.put("codigoPais", pais.getCodigo());
        }
        
        if(StringUtils.isNotBlank(searchForm.getFechaRegistro())) {
            criteria.put("fecDigi", searchForm.getFechaRegistro());
        }

        if (log.isDebugEnabled()) {
            log.debug(criteria.toString());
        }

        ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService)
        getBean("spusicc.procesoFDVClusterizacionService");
        
        List resultado = service.getProcesosClusterByCriteria(criteria);
        
        return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'deleteProceso' method");
		}

		// Creamos las instancias de los objetos a usar
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;

		String codProceso = registroSeleccionado.getCodProc();
		String estProceso = registroSeleccionado.getStaProc();

		if (this.log.isDebugEnabled()) {
			this.log.debug("Id seleccionado de la lista: " + codProceso+"estado: "+ estProceso);
		}
		// Todas las excepciones son capturadas por ActionExceptionHandler
		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this.getBean("spusicc.procesoFDVClusterizacionService");

		service.removeProcesoCluster(codProceso, usuario);

		this.getResourceMessage("procesosClusterForm.deleted");

		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'setSaveAttributes' method");
		}
		
		// Extraemos atributos y parámetros a usar
        MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
        boolean isNew = mantenimientoFDVClusterizacionForm.isNewRecord();
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

        // Extraemos el usuario de la sesión
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService)
        this.getBean("spusicc.procesoFDVClusterizacionService");
        
        ProcesoFDVFileZonaService serviceZona = (ProcesoFDVFileZonaService)
        this.getBean("spusicc.procesoFDVFileZonaService");
        
        ProcesoFDVFileSeccService serviceSecc = (ProcesoFDVFileSeccService)
        this.getBean("spusicc.procesoFDVFileSeccService");
        
        ProcesoFDVFileVariService serviceVari = (ProcesoFDVFileVariService)
        this.getBean("spusicc.procesoFDVFileVariService");
        
        ProcesoFDVFileNoreService serviceNore = (ProcesoFDVFileNoreService)
        this.getBean("spusicc.procesoFDVFileNoreService");

        ProcesoFDVClusterizacion procesoFDVClusterizacion = new ProcesoFDVClusterizacion();
        BeanUtils.copyProperties(procesoFDVClusterizacion, mantenimientoFDVClusterizacionForm);
                       
        try {
            if (isNew) {
            	
            	// Limpiando los datos adjuntados seran seteados mas adelante solo los archivos que esten correctos
            	mantenimientoFDVClusterizacionForm.setArcSecc(null);
            	mantenimientoFDVClusterizacionForm.setArcZona(null);
            	mantenimientoFDVClusterizacionForm.setArcVari(null);
            	mantenimientoFDVClusterizacionForm.setArcNore(null);
            	
            	serviceZona.saveFileZonasAndProcesoCluster(procesoFDVClusterizacion, usuario);            	
            	serviceSecc.saveFileSecciones(procesoFDVClusterizacion, usuario);            	
            	serviceVari.saveFileVariablesExog(procesoFDVClusterizacion, usuario);
            	serviceNore.saveFileNoReconstruida(procesoFDVClusterizacion, usuario);
            	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
            }
            else 
            {            	
            	service.updateProcesoClusterizacion(procesoFDVClusterizacion, usuario);
            	serviceZona.saveFileZonas(procesoFDVClusterizacion, usuario);              	
            	serviceSecc.saveFileSecciones(procesoFDVClusterizacion, usuario);            	
            	serviceVari.saveFileVariablesExog(procesoFDVClusterizacion, usuario);            	
            	serviceNore.saveFileNoReconstruida(procesoFDVClusterizacion, usuario);
            	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
            }
        }
        catch (InvalidIdentifierException iie) 
        {
        	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
        	
            String codigo = iie.getIdentifier().toString();
            this.addError("", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            
            if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
            
            // Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
            
        	this.log.error(iie.getMessage());
        	this.log.error(iie.toString());
            this.log.error("Detalles: ", iie);
            
            return false;
            
        }
        catch (InvalidDescriptionException ide) {
        	
        	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
        	
            String descripcion = ide.getDescription();            
            if(StringUtils.isBlank(descripcion)){            	                
                mantenimientoFDVClusterizacionForm.setCodigoPais(pais.getCodigo());
                mantenimientoFDVClusterizacionForm.setDirectorioTemporal(
                service.obtenerPathUpload(pais.getCodigo()));
            	
                this.addError("", this.getResourceMessage("errors.invalid.nulo.dataform"));
            }else{
            	this.addError("", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));	
            }            
            
            if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
            
        	this.log.error(ide.getMessage());
        	this.log.error(ide.toString());
        	this.log.error("Detalles: ", ide);
            
            // Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
            return false;
        }
        catch (InvalidTransactionProcessException ite) {
        	
        	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
        	
            String descripcion = ite.getDescription();
            String tipoProceso = ite.getTypeProcess();
            this.addError("", this.getResourceMessage("errors.invalid.operation.transaction.description", new Object[]{tipoProceso, descripcion}));
            
            
            if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
            
            // Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
            
        	this.log.error(ite.getMessage());
        	this.log.error(ite.toString());
        	this.log.error("Detalles: ", ite);
            
            return false;
            
        }
        catch(InvalidUploadException iue){
        	        		
        	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
        	
        	String descripcion = iue.getDescription();
        	String indicadorProblema = iue.getIndicatorProblem();
        	
        	if("extension".equalsIgnoreCase(indicadorProblema)){
        		this.addError("", this.getResourceMessage("errors.invalid.extension.upload", new Object[]{descripcion}));
        	}else{
        		this.addError("", this.getResourceMessage("errors.invalid.operation.upload", new Object[]{descripcion}));
        	}
            
        	if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
        	
        	// Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
                    
			this.log.error(iue.getMessage());
			this.log.error(iue.getIndicatorProblem());
			this.log.error("Detalles: ", iue);
            
            return false;
        }
        catch(InvalidTransactionProcessFileException ite){
        	
        	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
        	
        	String fileName = ite.getFileName();
        	String descripcion = ite.getDescription();        	        	
        	this.addError("", this.getResourceMessage("errors.invalid.transaction.fileupload", new Object[]{fileName, descripcion}));
            
        	if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
        	
        	// Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
                      
        	this.log.error(ite.getMessage());
        	this.log.error(ite.toString());
        	this.log.error("Detalles: ", ite);
            
            return false;
        }
        catch(InvalidConsolidatedProcessException ice){
        	
        	String descripcion = ice.getDescription();        	        	
        	this.addError("", this.getResourceMessage("errors.invalid.consolidated.process", new Object[]{descripcion}));
            
        	if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
        	
        	// Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
                       
        	this.log.error(ice.getMessage());
        	this.log.error(ice.toString());
        	this.log.error("Detalles: ", ice);
            
            return false;
        }
        catch (Exception e){
        	
        	service.updateFinalUpload(procesoFDVClusterizacion, usuario);
        	
        	this.addError("", this.getResourceMessage("errors.none"));
                        
            if(StringUtils.isNotBlank(procesoFDVClusterizacion.getCodProc())) {
				mantenimientoFDVClusterizacionForm.setNewRecord(false);
			}
            
            // Enviando los cambios que lograron aplicarse, antes de la excepcion
            BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
            
        	this.log.error(e.getMessage());
        	this.log.error(e.toString());
        	this.log.error("Detalles: ", e);
            
            return false;
        }

        // Actualizamos las listas que se encuentran en el contexto
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		this.mostrarBotonSave = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");

		MantenimientoFDVClusterizacionForm f = new MantenimientoFDVClusterizacionForm();

		f.setCodigoPais(pais.getCodigo());
		f.setDirectorioTemporal(service.obtenerPathUpload(pais.getCodigo()));
		f.setNewRecord(true);
		
		//Obtener Directivo
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
	   	Map criteria = new HashMap();
		criteria.put("codigoPais",f.getCodigoPais());		
		//criteria.put("nombreReporte",this.zonaFile.getFileName());
	   	Map paramReporteGeneral = reporteService.getParametrosReporteGeneral(criteria);
	   	this.directorio = paramReporteGeneral.get("directorioRepositorio").toString(); 

		/* METODO EDIT */

		ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;
		
		if (!this.accion.equals(this.ACCION_NUEVO)) 
		{
			String codProceso = registroSeleccionado.getCodProc();
			String estProceso = registroSeleccionado.getStaProc();

			if (codProceso != null && estProceso != null) 
			{

				if (this.log.isDebugEnabled()) {
					this.log.debug("Id seleccionado de la lista: " + codProceso);
				}

				ProcesoFDVClusterizacion procesoFDVClusterizacion = service.getProcesoCluster(codProceso);				
				setearValoresFile(procesoFDVClusterizacion);		        
				BeanUtils.copyProperties(f, procesoFDVClusterizacion);
				f.setNewRecord(false);
			}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) getBean("spusicc.procesoFDVClusterizacionService");

		this.procesosClusterList = service.getProcesosClusterByCriteria(criteria);
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.procesosClusterList);
		
	}

	/**
	* Carga la pantalla de edicion de parametros
	*/
	public void editarParametros(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'editarParametros' method");
		}

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");

		ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;

		try {
			String codProceso = registroSeleccionado.getCodProc();

			// Si el codProceso ha sido enviado, buscamos la informacion
			if (codProceso != null) 
			{
				if (this.log.isDebugEnabled()) {
					this.log.debug("Id seleccionado de la lista: " + codProceso);
				}

				MantenimientoParametroProcesoFDVForm parametrosForm = new MantenimientoParametroProcesoFDVForm();
				ProcesoFDVClusterizacion proceso = service.getProcesoCluster(codProceso);

				parametrosForm.setCodigoProceso(proceso.getCodProc());
				parametrosForm.setCodigoPais(proceso.getCodigoPais());
				parametrosForm.setCampanyaProceso(proceso.getCamProc());
				parametrosForm.setAnyoProceso(proceso.getAnyProc());

				if (StringUtils.isBlank(proceso.getAnyoPerProc()))
					parametrosForm.setNombreProceso(proceso.getNomProc());
				else
					parametrosForm.setNombreProceso(String.format(
							Constants.FORMATO_NOMBRE_PROCESO_LARGO_FDV,
							proceso.getAnyoPerProc(), proceso.getPerProc(),
							proceso.getVersionProceso(), proceso.getNomProc()));

				// Cargamos los parametros
				List parametros = service.getParametrosProceso(codProceso);

				parametrosForm.setParametros(parametros);
		
//				 establecemos el valor editable
				if (StringUtils.equals(this.editParams, Constants.ESTADO_ACTIVO))
					parametrosForm.setEditable(true);
				else
					parametrosForm.setEditable(false);

				this.mostrarBotonSave = false;
				this.parametrosForm = parametrosForm;
				this.redireccionarPagina("mantenimientoParametroProcesoFDVForm");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	* guarda los cambios en la pantalla de edicion de parametros 
	*/
	public void guardarParametros(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'guardarParametros' method");
		}

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this.getBean("spusicc.procesoFDVClusterizacionService");

		MantenimientoParametroProcesoFDVForm parametrosForm = new MantenimientoParametroProcesoFDVForm();

		try {
			String[] codigo = new String[this.parametrosForm.getParametros().size()];
			String[] valor = new String[this.parametrosForm.getParametros().size()];
			int i = 0;

			for (Object parametro : this.parametrosForm.getParametros()) {
				ProcesoFDVDistribucionMeta aux = (ProcesoFDVDistribucionMeta)parametro;
				codigo[i] = aux.getCodPara();
				valor[i] = aux.getValPara().toString();
				i++;
			}
			this.parametrosForm.setCodigoParametroList(codigo);
			this.parametrosForm.setValorParametroList(valor);
			
			parametrosForm = this.parametrosForm;
			String codigoParametroList[] = parametrosForm.getCodigoParametroList();
			String valorParametroList[] = parametrosForm.getValorParametroList();

			service.updateListaParamDist(codigoParametroList,valorParametroList, parametrosForm.getCodigoProceso());

			this.addInfo("", this.getResourceMessage("procesosClusterForm.parametros.updated"));
		
		} catch (InvalidTransactionProcessException ite) {

			this.log.error(ite.getMessage());
			this.log.error(ite.toString());
			this.log.error("Detalles: ", ite);

			String descripcion = ite.getDescription();
			if ("".equalsIgnoreCase(descripcion)) {
				this.addError("Error ", this.getResourceMessage("errors.invalid.transaction.parametro"));
			} else {
				this.addError("Error ", this.getResourceMessage("errors.invalid.transaction.parametro.description", 
						new Object[]{descripcion}));
			}
		} catch (Exception e) 
		{
			this.addError("Error ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	*  Carga la pantalla del proceso de clusterizacion
	*/
	public void generateProceso(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'generateProceso' method");
		}

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");

		MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = new MantenimientoFDVClusterizacionForm();
		mantenimientoFDVClusterizacionForm.setCheckAllListZoneNoReliableData(true);
		mantenimientoFDVClusterizacionForm.setCheckAllListZoneOffice(true);
		
		ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;

		try{
		String codProceso = registroSeleccionado.getCodProc();
		
		if (codProceso != null) 
		{
			if (this.log.isDebugEnabled()) {
				this.log.debug("Id seleccionado de la lista: " + codProceso);
			}
			
			ProcesoFDVClusterizacion procesoFDVClusterizacion = service.getProcesoCluster(codProceso);
			setearValoresFile(procesoFDVClusterizacion);
			BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);

			String anyProc = mantenimientoFDVClusterizacionForm.getAnyProc();
			String camProc = mantenimientoFDVClusterizacionForm.getCamProc();

			if (StringUtils.isNotBlank(anyProc) && StringUtils.isNotBlank(camProc)) 
			{
				mantenimientoFDVClusterizacionForm.setCamAnyProc(anyProc + camProc);
			}

			this.fdvProcessPeriodList = ConstantsList.getProcessPeriodListFDV();
			this.fdvGroupPoblationList = ConstantsList.getGroupPoblationListFDV();

			List listZoneOffice = service.getZoneOfficeList(codProceso);
			List listZoneNoReliableData = service.getZoneNoReliableDataList(codProceso);

			GenericBean genericBean = null;
			GenericBean[] zoneOffice = null;

			if (listZoneOffice != null) 
			{
				zoneOffice = new GenericBean[listZoneOffice.size()];
				for (int i = 0; i < listZoneOffice.size(); i++) 
				{
					genericBean = (GenericBean) listZoneOffice.get(i);
					if (Constants.YES.equalsIgnoreCase(genericBean.getFlaZofi())) 
					{
						genericBean.setSelected("true");
					} else {
						genericBean.setSelected("false");
						mantenimientoFDVClusterizacionForm.setCheckAllListZoneOffice(false);
					}

					genericBean.setContador(listZoneOffice.size());
					zoneOffice[i] = genericBean;
				}
			} else {
				zoneOffice = new GenericBean[0];
			}

			GenericBean[] zoneNoRealiableData = null;
			if (listZoneNoReliableData != null) 
			{
				zoneNoRealiableData = new GenericBean[listZoneNoReliableData.size()];
				for (int i = 0; i < listZoneNoReliableData.size(); i++) 
				{
					genericBean = (GenericBean) listZoneNoReliableData.get(i);
					if (Constants.YES.equalsIgnoreCase(genericBean.getFlaZonc())) {
						genericBean.setSelected("true");
					} else {
						genericBean.setSelected("false");
						mantenimientoFDVClusterizacionForm.setCheckAllListZoneNoReliableData(false);
					}

					genericBean.setContador(listZoneNoReliableData.size());
					zoneNoRealiableData[i] = genericBean;
				}
			} else {
				zoneNoRealiableData = new GenericBean[0];
			}

			mantenimientoFDVClusterizacionForm.setZoneOffice(zoneOffice);
			mantenimientoFDVClusterizacionForm.setZoneNoReliableData(zoneNoRealiableData);
			this.mostrarBotonSave = false;
			
			if(mantenimientoFDVClusterizacionForm.getZoneOffice().length > 0)
			{
				List lista = new ArrayList();
				List listaSelecionada = new ArrayList();
				String[] listaCodigoZona = new String[mantenimientoFDVClusterizacionForm.getZoneOffice().length];
				int i = 0;
				for (GenericBean objeto : mantenimientoFDVClusterizacionForm.getZoneOffice()) {
					lista.add(objeto);		
					if(objeto.getSelected().equalsIgnoreCase("true"))
						listaSelecionada.add(objeto);
					listaCodigoZona[i] = objeto.getCodZona();
					i++;
				}
				
				this.listaZonaModel = new DataTableModel(lista);	
				this.columnasSeleccionadas = listaSelecionada;
				mantenimientoFDVClusterizacionForm.setListCodZonaOffice(listaCodigoZona);
			}
			
			if(mantenimientoFDVClusterizacionForm.getZoneNoReliableData().length > 0)
			{
				List lista = new ArrayList();
				List listaSelecionada = new ArrayList();
				String[] listaCodigoZona = new String[mantenimientoFDVClusterizacionForm.getZoneNoReliableData().length];
				for (GenericBean objeto : mantenimientoFDVClusterizacionForm.getZoneNoReliableData()) {
					lista.add(objeto);
					if(objeto.getSelected().equalsIgnoreCase("true"))
						listaSelecionada.add(objeto);
				}
				
				this.listazoneNoReliableDataModel = new DataTableModel(lista);
				this.columnasNoReliableDataSeleccionadas = listaSelecionada;
				mantenimientoFDVClusterizacionForm.setListCodZonaNoReliableData(listaCodigoZona);
			}
			
			this.stFlaPobl = (mantenimientoFDVClusterizacionForm.getStFlaPobl().equalsIgnoreCase("true"))?true:false;
			this.stFlaClus = (mantenimientoFDVClusterizacionForm.getStFlaClus().equalsIgnoreCase("true"))?true:false;			
			this.stClose = (mantenimientoFDVClusterizacionForm.getStFlaClus().equalsIgnoreCase("true"))?true:false;
			this.stFlaNse = (mantenimientoFDVClusterizacionForm.getStFlaNse().equalsIgnoreCase("true"))?true:false;
			this.stFlaRlur = (mantenimientoFDVClusterizacionForm.getStFlaRlur().equalsIgnoreCase("true"))?true:false;
			this.stFlaVar1 = (mantenimientoFDVClusterizacionForm.getStFlaVar1().equalsIgnoreCase("true"))?true:false;
			this.stFlaVar2 = (mantenimientoFDVClusterizacionForm.getStFlaVar2().equalsIgnoreCase("true"))?true:false;
					
			this.formMantenimiento = mantenimientoFDVClusterizacionForm;		
			
			this.redireccionarPagina("procesoFDVGenerarClusterizacionForm");
		}
		}catch(Exception e)
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	* Ejecuta el proceso de clusterizacion
	*/
	public void processGenerarFDVClusterizacion(ActionEvent event) throws Exception
	{
        if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'processGenerarFDVClusterizacion' method");
        }
                
        // Extraemos el usuario de la sesión
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

        // Creamos la instancia del servicio y le asignamos
        // el usuario que va a realizar las operaciones
        ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService)
        this.getBean("spusicc.procesoFDVClusterizacionService");
        
        MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
        this.setVariablesExogenas(mantenimientoFDVClusterizacionForm);
        
        String camAnyProc = mantenimientoFDVClusterizacionForm.getCamAnyProc();
        if(StringUtils.isNotBlank(camAnyProc)){
        	try{
        		mantenimientoFDVClusterizacionForm.setAnyProc(camAnyProc.substring(0, 4)); //anio
        		mantenimientoFDVClusterizacionForm.setCamProc(camAnyProc.substring(4, camAnyProc.length())); //campaign
        	}catch(Exception e){
                
        		this.addError("", this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeCampaign"));                
        	}
        }
        
        ProcesoFDVClusterizacion procesoFDVClusterizacion = new ProcesoFDVClusterizacion();
        BeanUtils.copyProperties(procesoFDVClusterizacion, mantenimientoFDVClusterizacionForm);
        
        try {
        	if (this.columnasSeleccionadas!= null && this.columnasSeleccionadas.size() > 0)
        	{
        		String[] listaCodZonaOffice = new String[columnasSeleccionadas.size()];
        		for (int i = 0; i<this.columnasSeleccionadas.size();i++){
        			GenericBean objeto = (GenericBean)this.columnasSeleccionadas.get(i);
        			listaCodZonaOffice[i] = objeto.getCodZona(); 					
				}        		
        		mantenimientoFDVClusterizacionForm.setListSelectedOffice(listaCodZonaOffice);
        	}
        	
        	if (this.columnasNoReliableDataSeleccionadas!= null && this.columnasNoReliableDataSeleccionadas.size() > 0)
        	{
        		String[] listaCodZonaNoReliableDatae = new String[columnasNoReliableDataSeleccionadas.size()];
        		for (int i = 0; i<this.columnasNoReliableDataSeleccionadas.size();i++){
        			GenericBean objeto = (GenericBean)this.columnasNoReliableDataSeleccionadas.get(i);
        			listaCodZonaNoReliableDatae[i] = objeto.getCodZona(); 					
				}
        		mantenimientoFDVClusterizacionForm.setListSelectedNoReliableData(listaCodZonaNoReliableDatae);
        	}
        	
        	String[] listCodZonaOffice  = mantenimientoFDVClusterizacionForm.getListCodZonaOffice();
        	String[] listSelectedOffice = mantenimientoFDVClusterizacionForm.getListSelectedOffice();
        	String[] listCodZonaNoReliableData  = mantenimientoFDVClusterizacionForm.getListCodZonaNoReliableData();
        	String[] listSelectedNoReliableData = mantenimientoFDVClusterizacionForm.getListSelectedNoReliableData();       	
        	
        	service.updatePropuestaClusterizacion(listSelectedOffice, listSelectedNoReliableData, 
        	listCodZonaOffice, listCodZonaNoReliableData, procesoFDVClusterizacion, usuario);
        	
        	String stFlaClus = mantenimientoFDVClusterizacionForm.getStFlaClus();
        	
        	if(stFlaClus.equalsIgnoreCase("true")){
        		service.executeProcedureClusterizacionDesdeArchivo(procesoFDVClusterizacion);     		
        		
        		this.setMensajeAlertaDefaultAction(this.getResourceMessage("procesosClusterForm.clusterVarExogena"));
				RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
				String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
				this.getRequestContext().execute(ventana);        		
        		
        	}
        	else{
        		service.executeProcedureClusterizacion(procesoFDVClusterizacion);
        		// salvamos los mensajes en la sesión para que persistan luego del redirect        		
        		this.setMensajeAlertaDefaultAction(this.getResourceMessage("procesosClusterForm.cluster"));
				RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
				String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
				this.getRequestContext().execute(ventana);            	
        	}
        }
        catch (InvalidTransactionProcessException ite) {
        	
			this.log.error(ite.getMessage());
			this.log.error(ite.toString());
			this.log.error("Detalles: ", ite);
			
            String descripcion = ite.getDescription();
            if("".equalsIgnoreCase(descripcion)){
            	this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.clusterizacion"));
            }else{
            	this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.clusterizacion.description", new Object[]{descripcion}));
            }
        }
        catch (InvalidIdentifierException iie){
			this.log.error(iie.getMessage());
			this.log.error(iie.toString());
			this.log.error("Detalles: ", iie);
        	
            // Se esta tratando de crear un proceso ya existente, año y periodo ya existe
            this.addError("Error: ", this.getResourceMessage("errors.invalid.clusterizacion.process.identifier",new Object[]{iie.getIdentifier()}));        	
        }
        catch (Exception e){

			this.log.error(e.getMessage());
			this.log.error(e.toString());
			this.log.error("Detalles: ", e);
			
        	 this.addError("Error: ", this.getResourceMessage("errors.none"));
        }
	}
	
	private void setVariablesExogenas(MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm) 
	{		
		if(this.stFlaNse){
			mantenimientoFDVClusterizacionForm.setStFlaNse("true");
		}else{
			mantenimientoFDVClusterizacionForm.setStFlaNse("false");
		}
		
		if(this.stFlaPobl){ 
			mantenimientoFDVClusterizacionForm.setStFlaPobl("true");
		}else{
			mantenimientoFDVClusterizacionForm.setStFlaPobl("false");
			mantenimientoFDVClusterizacionForm.setNroGrpo("");
		}
		
		if(this.stFlaRlur){
			mantenimientoFDVClusterizacionForm.setStFlaRlur("true");
		}else{
			mantenimientoFDVClusterizacionForm.setStFlaRlur("false");
		}
		
		if(this.stFlaVar1){ 
			mantenimientoFDVClusterizacionForm.setStFlaVar1("true");
		}else{
			mantenimientoFDVClusterizacionForm.setStFlaVar1("false");
		}
		
		if(this.stFlaVar2){
			mantenimientoFDVClusterizacionForm.setStFlaVar2("true");
		}else{
			mantenimientoFDVClusterizacionForm.setStFlaVar2("false");
		}
		
		if(this.stFlaClus){ 
			mantenimientoFDVClusterizacionForm.setStFlaClus("true");
		}else{
			mantenimientoFDVClusterizacionForm.setStFlaClus("false");
		}
	}
	
	/**
	*  carga la pantalla de Asignar/Modificar Zonas
	*/
	public void asigModifProceso(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'asigModifProceso' method");
		}

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");

		MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = new MantenimientoFDVClusterizacionForm();
		
		ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;

		// Si el id ha sido enviado, buscamos la informacion
		// en caso contrario, redirigimos al listado
		try {
			String codProceso = registroSeleccionado.getCodProc();

			if (codProceso != null) {

				if (this.log.isDebugEnabled()) {
					this.log.debug("Id seleccionado de la lista: " + codProceso);
				}

				ProcesoFDVClusterizacion procesoFDVClusterizacion = service.getProcesoCluster(codProceso);
				setearValoresFile(procesoFDVClusterizacion);
				BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);

				List listZonesAsigSist = service.getZonesAsigSistList(procesoFDVClusterizacion);
				List listZonesAsigPais = service.getZonesAsigPaisList(procesoFDVClusterizacion);
				this.zonaSistema = new ArrayList();
				this.zonaAsignar = new ArrayList();
				
				GenericBean[] zonesAsigSist = null;
				ProcesoFDVClusterizacion[] zonesAsigPais = null;

				if (listZonesAsigSist != null) 
				{
					zonesAsigSist = new GenericBean[listZonesAsigSist.size()];
					for (int i = 0; i < listZonesAsigSist.size(); i++) 
					{
						GenericBean bean = (GenericBean) listZonesAsigSist.get(i);
						bean.setContador(listZonesAsigSist.size());
						zonesAsigSist[i] = bean;
						this.zonaSistema.add(bean);
					}
				} else {
					zonesAsigSist = new GenericBean[0];
				}

				if (listZonesAsigPais != null) {
					zonesAsigPais = new ProcesoFDVClusterizacion[listZonesAsigPais.size()];
					for (int i = 0; i < listZonesAsigPais.size(); i++) 
					{
						ProcesoFDVClusterizacion bean = (ProcesoFDVClusterizacion) listZonesAsigPais.get(i);
						bean.setContador(listZonesAsigPais.size());
						zonesAsigPais[i] = bean;
						this.zonaAsignar.add(bean);
					}
				} else {
					zonesAsigPais = new ProcesoFDVClusterizacion[0];
				}

				String camAnyProc = mantenimientoFDVClusterizacionForm.getAnyProc()
						+ mantenimientoFDVClusterizacionForm.getCamProc();

				mantenimientoFDVClusterizacionForm.setCamAnyProc(camAnyProc);
				mantenimientoFDVClusterizacionForm.setZoneAsigSist(zonesAsigSist);
				mantenimientoFDVClusterizacionForm.setZoneAsigPais(zonesAsigPais);

				this.fdvCluAsigSistList = service.getCluAsigSistList(procesoFDVClusterizacion);
				
				this.formMantenimiento = mantenimientoFDVClusterizacionForm;
				this.listazoneAsigSistModel = new DataTableModel(this.zonaSistema);
				this.listazoneAsigPaisModel = new DataTableModel(this.zonaAsignar);
				this.mostrarBotonSave = false;
				
				this.redireccionarPagina("mantenimientoFDVAsigModifZonaForm");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
		
	/**
	*  ejecuta el guardar de la pantalla de Asignar/Modificar Zonas
	*/
	public void processAsigModif(ActionEvent event)
	{
		 if (this.log.isDebugEnabled()) {
	            this.log.debug("Entering 'processAsigModif' method");
	        }
	        	        
	        // Extraemos el usuario de la sesión
	        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

	        // Creamos la instancia del servicio y le asignamos
	        // el usuario que va a realizar las operaciones
	        ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService)
	        this.getBean("spusicc.procesoFDVClusterizacionService");
	        
	        try{
	        
		        MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
		        GenericBean[] zonesAsigSist = null;
				ProcesoFDVClusterizacion[] zonesAsigPais = null;
				mantenimientoFDVClusterizacionForm.setStClose("closeClusterizacion");
				
		        if(this.zonaSistema != null)
		        {
		        	zonesAsigSist = new GenericBean[this.zonaSistema.size()];
		        	String[] listCodZona = new String[this.zonaSistema.size()];
		        	String[] listAsigPaisSist = new String[this.zonaSistema.size()];
		        	for (int i = 0; i < this.zonaSistema.size(); i++) 
			        {
			        	GenericBean bean = (GenericBean) this.zonaSistema.get(i);
			        	bean.setContador(this.zonaSistema.size());
						zonesAsigSist[i] = bean;						
						listCodZona[i] = bean.getCodZona();
						listAsigPaisSist[i] = bean.getCluAsigPais();
					}	
		        	
		        	mantenimientoFDVClusterizacionForm.setZoneAsigSist(zonesAsigSist);
		        	mantenimientoFDVClusterizacionForm.setListCodZonaPorSist(listCodZona);
		        	mantenimientoFDVClusterizacionForm.setListAsigPaisPorSist(listAsigPaisSist);
		        }
		        
		        if(this.zonaAsignar != null)
		        {
		        	zonesAsigPais = new ProcesoFDVClusterizacion[this.zonaAsignar.size()];
		        	String[] listCodPais = new String[this.zonaAsignar.size()];
		        	String[] listAsigPais = new String[this.zonaAsignar.size()];
		        	for (int i = 0; i < this.zonaAsignar.size(); i++)
		        	{
		        		GenericBean bean = (GenericBean) this.zonaAsignar.get(i);
			        	bean.setContador(this.zonaAsignar.size());
						zonesAsigSist[i] = bean;						
						listCodPais[i] = bean.getCodZona();
						listAsigPais[i] = bean.getCluAsigPais();
		        	}		        	
		        	
		        	mantenimientoFDVClusterizacionForm.setZoneAsigPais(zonesAsigPais);
		        	mantenimientoFDVClusterizacionForm.setListCodZonaPorAsig(listCodPais);
		        	mantenimientoFDVClusterizacionForm.setListAsigPaisPorAsig(listAsigPais);
		        }
		        		        
		        String[] arrayCountryAsigSist = mantenimientoFDVClusterizacionForm.getListAsigPaisPorSist();
		        String[] arrayCountryAsigPais = mantenimientoFDVClusterizacionForm.getListAsigPaisPorAsig();
		        String[] arrayZoneAsigPais = mantenimientoFDVClusterizacionForm.getListCodZonaPorAsig();
		        String[] arrayZoneAsigSist = mantenimientoFDVClusterizacionForm.getListCodZonaPorSist();        
		        
		        String codProc = mantenimientoFDVClusterizacionForm.getCodProc();
		        String stClose = mantenimientoFDVClusterizacionForm.getStClose();
		        
		        service.updateClusterAsigPais(arrayCountryAsigSist, arrayCountryAsigPais, 
		        arrayZoneAsigPais, arrayZoneAsigSist, codProc);
		        
		        if("closeClusterizacion".equals(stClose)){		        	
		        	service.updateStatusClusterizacion(codProc, usuario, 
		        	Constants.PROCESO_CLUSTER_COD_CLUSTER_CERRADO);
		        	
		        	// salvamos los mensajes en la sesión para que persistan luego del redirect		        	
		        	this.setMensajeAlertaDefaultAction(this.getResourceMessage("procesosClusterForm.closed"));
					RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
					String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
					this.getRequestContext().execute(ventana);			    	    
		        }else{
		        	// salvamos los mensajes en la sesión para que persistan luego del redirect
		        	this.setMensajeAlertaDefaultAction(this.getResourceMessage("procesosClusterForm.updated"));
					RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
					String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
					this.getRequestContext().execute(ventana);   	
		        }	        
	        }catch (Exception e)
	        {	
				this.log.error(e);
				this.log.error(e);
				this.log.error("Detalles: ", e);
	        	
	        	this.addError("", this.getResourceMessage("errors.none"));
	        }
	}
	
	/**
	 * Muestra la pantalla de distribucion de metas 
	*/
	public void distribuirMeta(ActionEvent event)
	{
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'distribuirMeta' method");
        }
        
        ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this.getBean("spusicc.procesoFDVClusterizacionService");
        MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = new MantenimientoFDVClusterizacionForm();        
        mantenimientoFDVClusterizacionForm.setCheckAllListZoneNoReliableDataVarVenta(true);
        
        ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;
        
        String codProc = registroSeleccionado.getCodProc();
        // Si el id ha sido enviado, buscamos la informacion
        // en caso contrario, redirigimos al listado
        if (codProc != null) {
                	
            if (this.log.isDebugEnabled()) {
                this.log.debug("Id seleccionado de la lista: " + codProc);
            }
        	
            try{
            	
            	ProcesoFDVClusterizacion procesoFDVClusterizacion = service.getProcesoCluster(codProc);
            	setearValoresFile(procesoFDVClusterizacion);
            	BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
            	
            	// Invocando al procedure que inicializara los datos 
            	// para la distribucion de metas
            	service.executeProcedureIniDistMetasSecciones(codProc);
            	
            	// Obteniendo el listado de los parametros (Distribucion % por Campaña)
            	this.listDist = service.getParamDistribucionList(codProc);
            	
            	// Obteniendo el listado de las zonas con sus secciones
            	this.listSecc = service.getZonaSeccionList(codProc);
            	
            	// Obteniendo el listado de campañas de acuerdo a la campaña corte del proceso
            	this.listCamp = service.getCampaniaCambioList(procesoFDVClusterizacion);
            	
            	//Obteniendo las zonas con data no confiable
            	List listZoneNoReliableDataVarVenta = service.getZoneNoReliableDataVarVentaList(codProc);

    			GenericBean[] zoneNoRealiableDataVarVenta = null;
    			GenericBean genericBean = null;
    			List listaSeleccionadas = new ArrayList();
    			
    			if(listZoneNoReliableDataVarVenta != null)
    			{
    				zoneNoRealiableDataVarVenta = new GenericBean[listZoneNoReliableDataVarVenta.size()];
    				this.zoneNoRealiableDataVarVenta = new ArrayList();
    				for (int i = 0; i < listZoneNoReliableDataVarVenta.size(); i++) 
    				{	
    					genericBean = (GenericBean)listZoneNoReliableDataVarVenta.get(i);
    					if(Constants.YES.equalsIgnoreCase(genericBean.getFlaZonc()))
    					{
    						genericBean.setSelected("true");
    						listaSeleccionadas.add(genericBean);
    					}else{
    						genericBean.setSelected("false");
    						mantenimientoFDVClusterizacionForm.setCheckAllListZoneNoReliableDataVarVenta(false);
    					}
    					
    					genericBean.setContador(listZoneNoReliableDataVarVenta.size());
    					zoneNoRealiableDataVarVenta[i] = genericBean;
    					this.zoneNoRealiableDataVarVenta.add(genericBean);
    				}
    			}else{
    				zoneNoRealiableDataVarVenta = new GenericBean[0];
    			}		

    			mantenimientoFDVClusterizacionForm.setZoneNoReliableDataVarVenta(zoneNoRealiableDataVarVenta);            	
            	// setea los valores del campo camCase cambia null por "" 
    			for (Object object : this.listSecc) {
            		ProcesoFDVDistribucionMeta obj = (ProcesoFDVDistribucionMeta)object;
            		if(StringUtils.isBlank(obj.getCamCase()))
            		{
            			obj.setCamCase(new String(""));            			
            		}
				}    			
            	
            	String camAnyProc = mantenimientoFDVClusterizacionForm.getAnyProc() + 
            	mantenimientoFDVClusterizacionForm.getCamProc();
            	
            	mantenimientoFDVClusterizacionForm.setCamAnyProc(camAnyProc);
            	mantenimientoFDVClusterizacionForm.setListDist(this.listDist);
            	mantenimientoFDVClusterizacionForm.setListSecc(this.listSecc);
            	mantenimientoFDVClusterizacionForm.setListCamp(this.listCamp);
            	
            	this.formMantenimiento = mantenimientoFDVClusterizacionForm;
            	this.listDistModel = new DataTableModel(this.listDist);
            	this.listSeccModel = new DataTableModel(this.listSecc);
            	this.zoneNoReliableDataVarVentaModel = new DataTableModel(this.zoneNoRealiableDataVarVenta);
            	this.columnasNoReliableDataVarVentaSeleccionadas = listaSeleccionadas;
            	this.mostrarBotonSave = false;
            	this.redireccionarPagina("procesoFDVDistribuirMetaForm");
            }catch(InvalidTransactionProcessException ite)
            { 	
				this.log.error(ite.getMessage());
				this.log.error(ite.toString());
				this.log.error("Detalles: ", ite);
				
            	String descripcion = ite.getDescription();
                if("".equalsIgnoreCase(descripcion)){
                	this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.distmetas"));
                }else{
                	this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.distmetas.description", new Object[]{descripcion}));
                }                
            }catch (Exception e)
            {
				this.log.error(e.getMessage());
				this.log.error(e.toString());
				this.log.error("Detalles: ", e);
				
            	this.addError("Error: ", this.getResourceMessage("errors.none"));
            }        	
        }
	}
	
	/**
	 * Ejecuta el proceso de distribucion de metas
	 */
	public void processDistribuirMeta(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'processDistribuirMeta' method");
		}

		// Extraemos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this.getBean("spusicc.procesoFDVClusterizacionService");

		try {
			MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
			
			String codProc = mantenimientoFDVClusterizacionForm.getCodProc();
			String staProc = mantenimientoFDVClusterizacionForm.getStaProc();
			BigDecimal valMevd = mantenimientoFDVClusterizacionForm.getValMevd();

			service.updateValMevd(valMevd, codProc, usuario);
			
			if(this.listDist != null && this.listDist.size() > 0)
			{
				String[] listCodParaDist = new String[this.listDist.size()];
				String[] listValParaDist = new String[this.listDist.size()];
				int i = 0;
				for (Object objeto : this.listDist) {
					ProcesoFDVDistribucionMeta aux = (ProcesoFDVDistribucionMeta)objeto;
					listCodParaDist[i] = aux.getCodPara();
					listValParaDist[i] = aux.getValPara().toString();
					i++;
				}				
				mantenimientoFDVClusterizacionForm.setListCodParaDist(listCodParaDist);
				mantenimientoFDVClusterizacionForm.setListValParaDist(listValParaDist);
				
				service.updateListaParamDist(listCodParaDist, listValParaDist, codProc);
			}
			
			if(this.listSecc != null && this.listSecc.size() > 0)
			{
				String[] listCodZonaDist = new String[this.listSecc.size()];	
				String[] listCamCaseDist = new String[this.listSecc.size()];
				String[] listNroSecoDist = new String[this.listSecc.size()];
				int i = 0;
				for (Object objeto : this.listSecc) {	
					ProcesoFDVDistribucionMeta aux = (ProcesoFDVDistribucionMeta)objeto;
					listCodZonaDist[i] = aux.getCodZona();
					listCamCaseDist[i] = aux.getCamCase();
					listNroSecoDist[i] = aux.getNroSeco().toString();
					i++;							
				}
				mantenimientoFDVClusterizacionForm.setListCodZonaDist(listCodZonaDist);
				mantenimientoFDVClusterizacionForm.setListCamCaseDist(listCamCaseDist);
				mantenimientoFDVClusterizacionForm.setListNroSecoDist(listNroSecoDist);	
				
				service.updateZonaSeccion(listCodZonaDist, listCamCaseDist, listNroSecoDist, codProc);
			}
		
			if(this.columnasNoReliableDataVarVentaSeleccionadas != null && this.columnasNoReliableDataVarVentaSeleccionadas.size() > 0) 
			{
				String[] listSelectedNoReliableDataVarVenta = new String[this.columnasNoReliableDataVarVentaSeleccionadas.size()];
				int i = 0;
				for (Object objeto : this.columnasNoReliableDataVarVentaSeleccionadas) {
					GenericBean aux = (GenericBean)objeto;
					listSelectedNoReliableDataVarVenta[i] = aux.getCodZona();
					i++;
				}
				
				mantenimientoFDVClusterizacionForm.setListSelectedNoReliableDataVarVenta(listSelectedNoReliableDataVarVenta);
				
				service.updateZonasNoReliableDataVarVenta(listSelectedNoReliableDataVarVenta, codProc);
			}	
			
			service.executeProcedureDistMetas(codProc, pais.getCodigo());

			if (!Constants.PROCESO_CLUSTER_COD_DIST_REALIZADA.equals(staProc)) {
				service.updateStatusClusterizacion(codProc, usuario,
						Constants.PROCESO_CLUSTER_COD_DIST_REALIZADA);
			}

			// salvamos los mensajes en la sesión para que persistan luego del redirect
			this.setMensajeAlertaDefaultAction(this.getResourceMessage("procesosClusterForm.distmetas"));
			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
			String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
			this.getRequestContext().execute(ventana);
			
		} catch (InvalidTransactionProcessException ite) 
		{
			this.log.error(ite.getMessage());
			this.log.error(ite.toString());
			this.log.error("Detalles: ", ite);

			String descripcion = ite.getDescription();
			if ("".equalsIgnoreCase(descripcion)) {
				this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.distmetas"));
			} else {
				this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.distmetas.description", new Object[]{descripcion}));
			}
		} catch (Exception e) 
		{
			this.log.error(e.getMessage());
			this.log.error(e.toString());
			this.log.error("Detalles: ", e);

		this.addError("Error: ", this.getResourceMessage("errors.none"));
		}
	}
	
	/** 
	* Carga la pantalla de ajuste de metas por el DV
	*/
	public void ajustarMeta(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ajustarMeta' method");
		}

		MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = new MantenimientoFDVClusterizacionForm();

		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");

		// String id = request.getParameter("id");
		ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;
		try {
			String codProc = registroSeleccionado.getCodProc();
			// Si el id ha sido enviado, buscamos la informacion
			// en caso contrario, redirigimos al listado
			if (codProc != null) {
				if (this.log.isDebugEnabled()) {
					this.log.debug("Id seleccionado de la lista: " + codProc);
				}

				ProcesoFDVClusterizacion procesoFDVClusterizacion = service.getProcesoCluster(codProc);
				setearValoresFile(procesoFDVClusterizacion);
				BeanUtils.copyProperties(mantenimientoFDVClusterizacionForm, procesoFDVClusterizacion);
				mantenimientoFDVClusterizacionForm.setCamAnyProc(mantenimientoFDVClusterizacionForm.getAnyProc()
								+ mantenimientoFDVClusterizacionForm.getCamProc());

				cargarDatosAjuste(codProc, mantenimientoFDVClusterizacionForm, service);
				
				this.listZonaAjust = mantenimientoFDVClusterizacionForm.getListZonaAjus();
				this.listZonaAjustModel = new DataTableModel(this.listZonaAjust);
				this.formMantenimiento = mantenimientoFDVClusterizacionForm;
				this.mostrarBotonSave = false;
				this.redireccionarPagina("procesoFDVAjustarMetaForm");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void processAjustarMeta(ActionEvent event) 
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'processAjustarMeta' method");
		}

		// Extraemos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");

		try {

			MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;

			if (this.listZonaAjust != null && this.listZonaAjust.size() > 0) 
			{
				int i = 0;
				String[] listCodZonaAjus = new String[this.listZonaAjust.size()];
				String[] listValZonaAjus = new String[this.listZonaAjust.size()];
				for (Object objeto : this.listZonaAjust) {
					Map aux = (Map) objeto;
					listCodZonaAjus[i] = aux.get("codigoZona").toString();
					listValZonaAjus[i] = aux.get("metaAjustadaDv").toString();
					i++;
				}

				mantenimientoFDVClusterizacionForm.setListCodZonaAjus(listCodZonaAjus);
				mantenimientoFDVClusterizacionForm.setListValZonaAjus(listValZonaAjus);

				String codProc = mantenimientoFDVClusterizacionForm.getCodProc();
				String staProc = mantenimientoFDVClusterizacionForm.getStaProc();

				service.updateValoresAjustados(listCodZonaAjus, listValZonaAjus, codProc);
				service.executeProcesoAjusteMetas(codProc);

				if (!Constants.PROCESO_CLUSTER_COD_AJUS_REALIZADO.equals(staProc)) {
					service.updateStatusClusterizacion(codProc, usuario,
							Constants.PROCESO_CLUSTER_COD_AJUS_REALIZADO);
				}

				// salvamos los mensajes en la sesión para que persistan luego del redirect
				this.setMensajeAlertaDefaultAction(this.getResourceMessage("procesosClusterForm.ajustemetas"));
				RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
				String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
				this.getRequestContext().execute(ventana);
				
			}
		} catch (InvalidTransactionProcessException ite) {

			this.log.error(ite.getMessage());
			this.log.error(ite.toString());
			this.log.error("Detalles: ", ite);

			String descripcion = ite.getDescription();
			if ("".equalsIgnoreCase(descripcion)) {
				this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.ajustemetas"));
			} else {
				this.addError("Error: ", this.getResourceMessage("errors.invalid.transaction.ajustemetas.description",
						new Object[] { descripcion }));
			}
		} catch (Exception e) {

			this.log.error(e.getMessage());
			this.log.error(e.toString());
			this.log.error("Detalles: ", e);

			this.addError("Error: ", this.getResourceMessage("errors.none"));
		}
	}
	
	/**
	* Carga la pantalla Listado de Distribución de Metas
	*/
	public void listadoMetas(ActionEvent event) throws Exception 
	{
		try {
			ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;
			consultaFDVDistribucionMetaAction.setRegistroSeleccionado(registroSeleccionado);
			consultaFDVDistribucionMetaAction.cargar();
			
			this.redireccionarPagina("consultaFDVDistribucionMetaList");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void uploadZonaFile(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadZonaFile");
		}
		this.zonaFile = event.getFile();
        if(this.zonaFile == null) {
            this.addError("Error", "Debe seleccionar primero el archivo a Cargar");
            return;
        }
       MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
       try {
    	    //Cargamos el archivo de la maquina del cliente al servidor
    	   
			
			this.uploadArchivo(this.directorio, this.zonaFile.getFileName(), this.zonaFile.getInputstream());
			
			File file = new File(this.directorio, this.zonaFile.getFileName());
			mantenimientoFDVClusterizacionForm.setZonaFile(file);
			mantenimientoFDVClusterizacionForm.setArcZona(this.zonaFile.getFileName());
			if(mantenimientoFDVClusterizacionForm.isNewRecord())
				this.mostrarBotonSave = true;
			else
				this.mostrarBotonSave = false;
			this.addInfo("Información: ", "Archivo cargado correctamente en el Servidor");
        }
        catch (Exception e) {
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
        }
		if (log.isDebugEnabled()) {
			log.debug("Fin metodo uploadZonaFile");
		}   
    }
	
	public void uploadSeccionFile(FileUploadEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadSeccionFile");
		}
		this.seccionFile = event.getFile();
        if(this.seccionFile == null) {
            this.addError("Error", "Debe seleccionar primero el archivo a Cargar");
            return;
        }
       MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
       try {
    	    //Cargamos el archivo de la maquina del cliente al servidor    	   
			this.uploadArchivo(this.directorio, this.seccionFile.getFileName(), this.seccionFile.getInputstream());
			
			File file = new File(this.directorio, this.seccionFile.getFileName());
			mantenimientoFDVClusterizacionForm.setSeccionFile(file);
			mantenimientoFDVClusterizacionForm.setArcSecc(this.seccionFile.getFileName());
			if(mantenimientoFDVClusterizacionForm.isNewRecord())
				this.mostrarBotonSave = true;
			else
				this.mostrarBotonSave = false;

			this.addInfo("Información: ", "Archivo cargado correctamente en el Servidor");
        }
        catch (Exception e) {
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
        }
		if (log.isDebugEnabled()) {
			log.debug("Fin metodo uploadSeccionFile");
		}   
		
	}
	
	public void uploadVariablesExogFile(FileUploadEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadVariablesExogFile");
		}
		this.variablesExogFile = event.getFile();
        if(this.variablesExogFile == null) {
            this.addError("Error", "Debe seleccionar primero el archivo a Cargar");
            return;
        }
       MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
       try {
    	    //Cargamos el archivo de la maquina del cliente al servidor
    	  
			this.uploadArchivo(this.directorio, this.variablesExogFile.getFileName(), this.variablesExogFile.getInputstream());
			
			File file = new File(this.directorio, this.variablesExogFile.getFileName());
			mantenimientoFDVClusterizacionForm.setVariablesExogFile(file);
			mantenimientoFDVClusterizacionForm.setArcVari(this.variablesExogFile.getFileName());
			if(mantenimientoFDVClusterizacionForm.isNewRecord())
				this.mostrarBotonSave = true;
			else
				this.mostrarBotonSave = false;

			this.addInfo("Información: ", "Archivo cargado correctamente en el Servidor");
        }
        catch (Exception e) {
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
        }
		if (log.isDebugEnabled()) {
			log.debug("Fin metodo uploadVariablesExogFile");
		}   		
	}
	
	public void uploadNoReconstruidaFile(FileUploadEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug(" metodo uploadNoReconstruidaFile");
		}
		this.noReconstruidaFile = event.getFile();
        if(this.noReconstruidaFile == null) {
            this.addError("Error", "Debe seleccionar primero el archivo a Cargar");
            return;
        }
       MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
       try {
    	    //Cargamos el archivo de la maquina del cliente al servidor    	 
			this.uploadArchivo(this.directorio, this.noReconstruidaFile.getFileName(), this.noReconstruidaFile.getInputstream());
			
			File file = new File(this.directorio, this.noReconstruidaFile.getFileName());
			mantenimientoFDVClusterizacionForm.setNoReconstruidaFile(file);
			mantenimientoFDVClusterizacionForm.setArcNore(this.noReconstruidaFile.getFileName());
			if(mantenimientoFDVClusterizacionForm.isNewRecord())
				this.mostrarBotonSave = true;
			else
				this.mostrarBotonSave = false;

			this.addInfo("Información: ", "Archivo cargado correctamente en el Servidor");
        }
        catch (Exception e) {
        	this.addError("Error: ", this.obtieneMensajeErrorException(e));
        }
		if (log.isDebugEnabled()) {
			log.debug("Fin metodo uploadNoReconstruidaFile");
		}   				
	}
	
	/**
	 * Metodo que guarda el archivo en el servidor
	 * @param fileName
	 * @param in
	 * @throws Exception
	 */
	protected final void uploadArchivo(String directorio, String fileName, InputStream in) throws Exception {
		
	    // Escribe el contenido de un archivo de entrada a un FileOutputStream de salida
	    OutputStream out = new FileOutputStream(new File(directorio + fileName));
	  
	    int read = 0;
	    byte[] bytes = new byte[1024];
	  
	    while ((read = in.read(bytes)) != -1) {
	    	out.write(bytes, 0, read);
	    }
	  
	    in.close();
	    out.flush();
	    out.close();   
	}
		
	public void loadfile(FileUploadEvent event)
	{
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'loadfile' method");
		}
		
        MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
        
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        
        ExcelUtil excelUtil = null;
		List listaAjusteMeta = new ArrayList();
		
        try {
        	ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService)getBean("spusicc.procesoFDVClusterizacionService");
        				
        	UploadedFile eve = event.getFile();
        	mantenimientoFDVClusterizacionForm.setAjusteMetaFile(eve);
        	
			excelUtil = new ExcelUtil(eve.getInputstream());
			excelUtil.initSheet(0);
			
			while(excelUtil.hasNext()){
				Map mapRow = excelUtil.next();
				String codigoZona = (String.valueOf(mapRow.get("0"))).trim();
				String ajusteMeta = (String.valueOf(mapRow.get("1"))).trim();
				
				GenericBean  bean = new GenericBean();

				if(codigoZona.endsWith(".0")){
					codigoZona = codigoZona.substring(0, codigoZona.length()-2);
				}
				bean.setCodZona(codigoZona);
				
				if(ajusteMeta != null){
					try{							
						bean.setValor(new BigDecimal(ajusteMeta));
					}catch(Exception pe){
						bean.setValor(BigDecimal.ZERO);
					}
				}
								
				listaAjusteMeta.add(bean);
			}
			excelUtil.cerrar();

			service.updateValorAjustadoArchivo(listaAjusteMeta, mantenimientoFDVClusterizacionForm.getCodProc());
			
			cargarDatosAjuste(mantenimientoFDVClusterizacionForm.getCodProc(), mantenimientoFDVClusterizacionForm, service);
			
            this.addInfo("", this.getResourceMessage("mantenimientoFDVClusterizacionForm.file.load.ok"));
			
		} catch (Exception e) {
			if(excelUtil != null)
				excelUtil.cerrar();
			
            this.addError("Error: ", this.getResourceMessage("mantenimientoFDVClusterizacionForm.file.load.error"));
		}
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		 MantenimientoFDVClusterizacionForm f = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
		 boolean registro = f.isNewRecord();
		 if(registro)
			 return "procesosClusterForm.added";
		 else
			 return"procesosClusterForm.updated";
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		
		if (!this.verificarRegistroSeleccionado()) 
			return this.getResourceMessage("errors.select.item");
		else
		{
			ProcesoFDVClusterizacion registroSeleccionado = (ProcesoFDVClusterizacion) this.beanRegistroSeleccionado;
			
			if(accion.equals("1"))
				this.editParams = "1";
			
			if(accion.equals("0"))
				this.editParams = "0";
			
			if(accion.equalsIgnoreCase("EDITARPROCESO"))
			{
				if(!registroSeleccionado.getStaProc().equals("1") && !registroSeleccionado.getStaProc().equals("2"))
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeEditar");
			}
			
			if(accion.equalsIgnoreCase("GENERATEPROCESO"))
			{
				if(!registroSeleccionado.getStaProc().equals("2") && !registroSeleccionado.getStaProc().equals("3"))
					return this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeGenerarCluster");
			}
			
			if(accion.equalsIgnoreCase("ASIGMODIPROCESO"))
			{
				if(registroSeleccionado.getStaProc().equals("1") || registroSeleccionado.getStaProc().equals("2"))
					return this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeAsigModifCluster");
			}
			
			if(accion.equalsIgnoreCase("DISTRIBUIRMETA"))
			{
				if(!registroSeleccionado.getStaProc().equals("4") && !registroSeleccionado.getStaProc().equals("5") 
						&& !registroSeleccionado.getStaProc().equals("6"))
					return this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeDistribuirMetas");
			}
			
			if(accion.equalsIgnoreCase("AJUSTARMETA"))
			{
				if(!registroSeleccionado.getStaProc().equals("5") && !registroSeleccionado.getStaProc().equals("6"))
					return this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeAjustarMetas");
			}		
			
			if(accion.equalsIgnoreCase("VIEWDISTRIBUCIONMETAS"))
			{
				if(!registroSeleccionado.getStaProc().equals("5") && !registroSeleccionado.getStaProc().equals("6"))
					return this.getResourceMessage("mantenimientoFDVClusterizacionForm.errors.mensajeConsultaDistribucionMeta");
			}
		}		
		
		if(accion.equalsIgnoreCase("GENERARCLU"))
		{
			MantenimientoFDVClusterizacionForm f = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
			
			if(StringUtils.isBlank(f.getCamAnyProc()))
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.campania.required");
			
			if(StringUtils.isBlank(f.getPerProc()))
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.periodo.required");
			
			if(StringUtils.isBlank(f.getAnyoPerProc()))
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.anyoPerProc.required");
			
			if(this.stFlaPobl && StringUtils.isBlank(f.getNroGrpo()))
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.numgrupo.required");
			
			if(!this.stFlaPobl && !this.stFlaNse && !this.stFlaRlur && !this.stFlaVar1 && !this.stFlaVar2)
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.minVariableExog");
		}
		
		if(accion.equalsIgnoreCase("PROCESSAJUSTARMETA"))
		{
			int i = 0;
			for (Object objeto : this.listZonaAjust) {
				Map aux = (Map) objeto;
				if(StringUtils.isBlank(aux.get("metaAjustadaDv").toString()))
				{
					i = 1;
					break;					
				}									
			}
			
			if(i == 1)
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.valZonaAjus.required");			
		}
		
		if(accion.equalsIgnoreCase("PROCESSDISTRIBUIRMETA"))
		{
			int i = 0;
			MantenimientoFDVClusterizacionForm f = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
			
			for (Object objeto : this.listDist) {
				ProcesoFDVDistribucionMeta aux = (ProcesoFDVDistribucionMeta)objeto;
				if(StringUtils.isBlank(aux.getValPara().toString()))
				{
					i = 1;
					break;
				}
			}
			
			if(i == 1)
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.distField.required");
			
			if(StringUtils.isBlank(f.getValMevd().toString()))
			{
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.metaVentaDist.required");
			}else
			{
				if(f.getValMevd().compareTo(new BigDecimal("0")) == 0)
					return this.getResourceMessage("mantenimientoFDVClusterizacionForm.metaVentaDist.zero");
			}
			
			for (Object objeto : this.listSecc) {	
				ProcesoFDVDistribucionMeta aux = (ProcesoFDVDistribucionMeta)objeto;
				if(StringUtils.isNotBlank(aux.getCamCase()))
				{
					if(StringUtils.isBlank(aux.getNroSeco().toString()))
					{
						i = 1;
						break;
					}else
					{
						if(aux.getNroSeco().compareTo(new Long("0")) == 0)
						{
							i = 2;
							break;
						}
					}
				}						
			}
			
			if(i == 1)
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.nroSecc.required");
			
			if(i == 2)
				return this.getResourceMessage("mantenimientoFDVClusterizacionForm.nroSecc.zero");	
		}
		
		return null;
	}
		
    @Override
    public String setValidarMantenimiento() 
    {
    	String mensaje = null;
    	
    	MantenimientoFDVClusterizacionForm f = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
    	if(f.getZonaFile() == null)
    		mensaje = this.getResourceMessage("mantenimientoFDVClusterizacionForm.bdzona.required");
    	
    	if(f.getSeccionFile() == null)
    		mensaje = this.getResourceMessage("mantenimientoFDVClusterizacionForm.bdsecc.required");
    	
    	if(f.getVariablesExogFile() == null)
    		mensaje = this.getResourceMessage("mantenimientoFDVClusterizacionForm.bdvari.required");
    	
    	if(f.getNoReconstruidaFile() == null)
    		mensaje = this.getResourceMessage("mantenimientoFDVClusterizacionForm.bdnore.required");    	
    	
    	return mensaje;
    }
    
    
	
	// metodo calcula el valor del campo diferencia en la pantalla 	FDV - Ajuste de Metas por el DV  
	public void recalcularTotal(AjaxBehaviorEvent e)
	{
		MantenimientoFDVClusterizacionForm f = (MantenimientoFDVClusterizacionForm) this.formMantenimiento;
		BigDecimal totalAjuDv = BigDecimal.ZERO;
		
		for(int i = 0; i < listZonaAjust.size(); i++)
		{
			Map map = (Map)this.listZonaAjust.get(i);
			BigDecimal nroActual = new BigDecimal(map.get("metaAjustadaDv").toString());	
			totalAjuDv =totalAjuDv.add(nroActual.setScale(10, RoundingMode.HALF_UP));
		}
		
		BigDecimal totalMetVenAjuPer = new BigDecimal(f.getTotalMetVenAjuPer());
		BigDecimal diferencia = totalMetVenAjuPer.subtract(totalAjuDv.setScale(10, RoundingMode.HALF_UP));
		f.setDiferenciaMetas(diferencia.toString());		
	}
	
	private void setearValoresFile(ProcesoFDVClusterizacion p)
	{
		ProcesoFDVClusterizacionService service = (ProcesoFDVClusterizacionService) this
				.getBean("spusicc.procesoFDVClusterizacionService");
		
		p.setDirectorioTemporal(service.obtenerPathUpload(p.getCodigoPais()));
		
		File fileZona = new File(p.getDirectorioTemporal(), p.getArcZona());
		p.setZonaFile(fileZona);
		
		File fileSecciones = new File(p.getDirectorioTemporal(), p.getArcSecc());
		p.setSeccionFile(fileSecciones);
		
		File fileExogenas = new File(p.getDirectorioTemporal(), p.getArcVari());
		p.setVariablesExogFile(fileExogenas);
		
		File fileArcNore = new File(p.getDirectorioTemporal(), p.getArcNore());
		p.setNoReconstruidaFile(fileArcNore);		
	}
	
	private void cargarDatosAjuste(String id, MantenimientoFDVClusterizacionForm mantenimientoFDVClusterizacionForm, ProcesoFDVClusterizacionService service)
	{
		//Obtenemos los valores de cada una de las zonas
    	List listZonaAjus = service.getZonasAjusteDV(id);
    	mantenimientoFDVClusterizacionForm.setListZonaAjus(listZonaAjus);
    	
    	//Obtenemos los totales 
    	Map totales = service.getTotalesZonasAjusteDV(listZonaAjus);
    	
    	BigDecimal totalAjustePerido = (BigDecimal)MapUtils.getObject(totales, "totalAjustePerido");
    	BigDecimal totalAjusteDv = (BigDecimal)MapUtils.getObject(totales, "totalAjusteDv");
    	
    	BigDecimal diferenciaMetas = totalAjustePerido.subtract(totalAjusteDv);
    	
    	mantenimientoFDVClusterizacionForm.setTotalMetVenAjuPer(totalAjustePerido.toPlainString());
    	mantenimientoFDVClusterizacionForm.setTotalMetVenAjuDv(totalAjusteDv.toPlainString());
    	
    	mantenimientoFDVClusterizacionForm.setDiferenciaMetas(diferenciaMetas.toPlainString());	
    }
		
	public List getProcesosClusterList() {
		return procesosClusterList;
	}

	public void setProcesosClusterList(List procesosClusterList) {
		this.procesosClusterList = procesosClusterList;
	}

	/**
	 * @return the zonaFile
	 */
	public UploadedFile getZonaFile() {
		return zonaFile;
	}

	/**
	 * @param zonaFile the zonaFile to set
	 */
	public void setZonaFile(UploadedFile zonaFile) {
		this.zonaFile = zonaFile;
	}

	/**
	 * @return the seccionFile
	 */
	public UploadedFile getSeccionFile() {
		return seccionFile;
	}

	/**
	 * @param seccionFile the seccionFile to set
	 */
	public void setSeccionFile(UploadedFile seccionFile) {
		this.seccionFile = seccionFile;
	}

	/**
	 * @return the variablesExogFile
	 */
	public UploadedFile getVariablesExogFile() {
		return variablesExogFile;
	}

	/**
	 * @param variablesExogFile the variablesExogFile to set
	 */
	public void setVariablesExogFile(UploadedFile variablesExogFile) {
		this.variablesExogFile = variablesExogFile;
	}

	/**
	 * @return the noReconstruidaFile
	 */
	public UploadedFile getNoReconstruidaFile() {
		return noReconstruidaFile;
	}

	/**
	 * @param noReconstruidaFile the noReconstruidaFile to set
	 */
	public void setNoReconstruidaFile(UploadedFile noReconstruidaFile) {
		this.noReconstruidaFile = noReconstruidaFile;
	}

	public MantenimientoParametroProcesoFDVForm getParametrosForm() {
		return parametrosForm;
	}

	public void setParametrosForm(
			MantenimientoParametroProcesoFDVForm parametrosForm) {
		this.parametrosForm = parametrosForm;
	}

	public List getFdvProcessPeriodList() {
		return fdvProcessPeriodList;
	}

	public void setFdvProcessPeriodList(List fdvProcessPeriodList) {
		this.fdvProcessPeriodList = fdvProcessPeriodList;
	}

	public List getFdvGroupPoblationList() {
		return fdvGroupPoblationList;
	}

	public void setFdvGroupPoblationList(List fdvGroupPoblationList) {
		this.fdvGroupPoblationList = fdvGroupPoblationList;
	}

	public DataTableModel getListaZonaModel() {
		return listaZonaModel;
	}

	public void setListaZonaModel(DataTableModel listaZonaModel) {
		this.listaZonaModel = listaZonaModel;
	}

	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	public DataTableModel getListazoneNoReliableDataModel() {
		return listazoneNoReliableDataModel;
	}

	public void setListazoneNoReliableDataModel(
			DataTableModel listazoneNoReliableDataModel) {
		this.listazoneNoReliableDataModel = listazoneNoReliableDataModel;
	}

	public List getColumnasNoReliableDataSeleccionadas() {
		return columnasNoReliableDataSeleccionadas;
	}

	public void setColumnasNoReliableDataSeleccionadas(
			List columnasNoReliableDataSeleccionadas) {
		this.columnasNoReliableDataSeleccionadas = columnasNoReliableDataSeleccionadas;
	}

	public String getEditParams() {
		return editParams;
	}

	public void setEditParams(String editParams) {
		this.editParams = editParams;
	}

	public boolean isStFlaPobl() {
		return stFlaPobl;
	}

	public void setStFlaPobl(boolean stFlaPobl) {
		this.stFlaPobl = stFlaPobl;
	}

	public boolean isStFlaNse() {
		return stFlaNse;
	}

	public void setStFlaNse(boolean stFlaNse) {
		this.stFlaNse = stFlaNse;
	}

	public boolean isStFlaRlur() {
		return stFlaRlur;
	}

	public void setStFlaRlur(boolean stFlaRlur) {
		this.stFlaRlur = stFlaRlur;
	}

	public boolean isStFlaVar1() {
		return stFlaVar1;
	}

	public void setStFlaVar1(boolean stFlaVar1) {
		this.stFlaVar1 = stFlaVar1;
	}

	public boolean isStFlaVar2() {
		return stFlaVar2;
	}

	public void setStFlaVar2(boolean stFlaVar2) {
		this.stFlaVar2 = stFlaVar2;
	}

	public boolean isStClose() {
		return stClose;
	}

	public void setStClose(boolean stClose) {
		this.stClose = stClose;
	}

	public boolean isStFlaClus() {
		return stFlaClus;
	}

	public void setStFlaClus(boolean stFlaClus) {
		this.stFlaClus = stFlaClus;
	}

	public List getFdvCluAsigSistList() {
		return fdvCluAsigSistList;
	}

	public void setFdvCluAsigSistList(List fdvCluAsigSistList) {
		this.fdvCluAsigSistList = fdvCluAsigSistList;
	}

	public DataTableModel getListazoneAsigSistModel() {
		return listazoneAsigSistModel;
	}

	public void setListazoneAsigSistModel(DataTableModel listazoneAsigSistModel) {
		this.listazoneAsigSistModel = listazoneAsigSistModel;
	}

	public DataTableModel getListazoneAsigPaisModel() {
		return listazoneAsigPaisModel;
	}

	public void setListazoneAsigPaisModel(DataTableModel listazoneAsigPaisModel) {
		this.listazoneAsigPaisModel = listazoneAsigPaisModel;
	}

	public List getZonaSistema() {
		return zonaSistema;
	}

	public void setZonaSistema(List zonaSistema) {
		this.zonaSistema = zonaSistema;
	}

	public List getZonaAsignar() {
		return zonaAsignar;
	}

	public void setZonaAsignar(List zonaAsignar) {
		this.zonaAsignar = zonaAsignar;
	}

	public ConsultaFDVDistribucionMetaAction getConsultaFDVDistribucionMetaAction() {
		return consultaFDVDistribucionMetaAction;
	}

	public void setConsultaFDVDistribucionMetaAction(
			ConsultaFDVDistribucionMetaAction consultaFDVDistribucionMetaAction) {
		this.consultaFDVDistribucionMetaAction = consultaFDVDistribucionMetaAction;
	}

	public DataTableModel getListDistModel() {
		return listDistModel;
	}

	public void setListDistModel(DataTableModel listDistModel) {
		this.listDistModel = listDistModel;
	}

	public DataTableModel getListSeccModel() {
		return listSeccModel;
	}

	public void setListSeccModel(DataTableModel listSeccModel) {
		this.listSeccModel = listSeccModel;
	}

	public DataTableModel getZoneNoReliableDataVarVentaModel() {
		return zoneNoReliableDataVarVentaModel;
	}

	public void setZoneNoReliableDataVarVentaModel(
			DataTableModel zoneNoReliableDataVarVentaModel) {
		this.zoneNoReliableDataVarVentaModel = zoneNoReliableDataVarVentaModel;
	}

	public List getZoneNoRealiableDataVarVenta() {
		return zoneNoRealiableDataVarVenta;
	}

	public void setZoneNoRealiableDataVarVenta(List zoneNoRealiableDataVarVenta) {
		this.zoneNoRealiableDataVarVenta = zoneNoRealiableDataVarVenta;
	}

	public List getColumnasNoReliableDataVarVentaSeleccionadas() {
		return columnasNoReliableDataVarVentaSeleccionadas;
	}

	public void setColumnasNoReliableDataVarVentaSeleccionadas(
			List columnasNoReliableDataVarVentaSeleccionadas) {
		this.columnasNoReliableDataVarVentaSeleccionadas = columnasNoReliableDataVarVentaSeleccionadas;
	}

	public List getListCamp() {
		return listCamp;
	}

	public void setListCamp(List listCamp) {
		this.listCamp = listCamp;
	}

	public DataTableModel getListZonaAjustModel() {
		return listZonaAjustModel;
	}

	public void setListZonaAjustModel(DataTableModel listZonaAjustModel) {
		this.listZonaAjustModel = listZonaAjustModel;
	}

	public List getListZonaAjust() {
		return listZonaAjust;
	}

	public void setListZonaAjust(List listZonaAjust) {
		this.listZonaAjust = listZonaAjust;
	}

	public List getListDist() {
		return listDist;
	}

	public void setListDist(List listDist) {
		this.listDist = listDist;
	}

	public List getListSecc() {
		return listSecc;
	}

	public void setListSecc(List listSecc) {
		this.listSecc = listSecc;
	}
}