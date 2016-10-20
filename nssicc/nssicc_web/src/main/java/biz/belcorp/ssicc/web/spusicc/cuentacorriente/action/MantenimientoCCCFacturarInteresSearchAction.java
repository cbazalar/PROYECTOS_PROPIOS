package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCFacturarInteresService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCFacturarInteresForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCFacturarInteresSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCCCFacturarInteresSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List cccCodigoConsultoraArchivoList;
    private Object[] columnasSeleccionadas;
	private List cccFacturarInteresList;
	//private List<MantenimientoCCCFacturarInteresSearchForm> seleccionadosList;
	private String attachment = "";
	private String attachmentForm = "";
	private DataTableModel datatableCCCD;
	private MantenimientoCCCFacturarInteresForm facturarInteresForm;
	private List mantenimientoCCCFacturarInteresErrorList;
	private String mantenimientoCCCFacturarInteresViewValida;
	private List listaPopup;
	private Boolean mostrarGrilla;
	private Boolean mostrarBotonGuardar;
	private Boolean mostrarBotonGuardarBoton;
	private String nombreArchivo;
	private String totalRegistros;
	private String totalRegistrosConError;
	private String totalRegistrosValidos;
	private List mantenimientoCCCFacturarInteresErrorListAux;

	@ManagedProperty(value = "#{mantenimientoCCCFacturarInteresAction}")
	protected MantenimientoCCCFacturarInteresAction mantenimientoCCCFacturarInteresAction;

	/**
	 * Cargar archivo
	 * 
	 * @param event
	 * @throws Exception
	 */

	public void buscar(ActionEvent actionEvent) {
		try {

			this.setFindAttributes();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUpload(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		MantenimientoCCCFacturarInteresSearchForm f = (MantenimientoCCCFacturarInteresSearchForm) this.formBusqueda;
		if (event != null) {
			// f.setArchivo(event.getFile());
			f.setArchivoCodigoConsultora(event.getFile());
			this.setAttachment(event.getFile().getFileName());
			this.validar();
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String parametroVentana= externalContext.getRequestParameterMap().get("parametroVentana");
		if(StringUtils.equals(parametroVentana, "confirmDialogGuardar")){
			return null;
		}
		
		if(this.columnasSeleccionadas == null || this.columnasSeleccionadas.length==0){
			return "Debe de seleccionar un registro ";
		}
		return null;
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void ejecutar(ActionEvent evet) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ejecutar' method and cleaned ");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		List listaConsolidado = this.cccFacturarInteresList;

		List listaConsolidadoAux = new ArrayList();
		String mensaje = "";
		try {

			MantenimientoCCCFacturarInteresSearchForm f = (MantenimientoCCCFacturarInteresSearchForm) this.formBusqueda;
//			this.actualizaListaGrilla(listaConsolidado);

			if (listaConsolidado.size() > 0) {
				MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");
				Map criteria = new HashMap();
				
//                List items=new ArrayList();
//                for(Object item:obj)
//                	items.add(item);
                 
//				if(items != null && items.size() > 0){
//					for (int i = 0; i < listaConsolidado.size(); i++) {
//						Map params = (Map) listaConsolidado.get(i);
//						for (int j = 0; j < items.size(); j++) {
//							if (StringUtils.equals(items.get(j).toString(), params.get("numeroFila").toString())) {
//								listaConsolidadoAux.add(listaConsolidado.get(i));
//							}
//						}
//					}
					
					for (int i = 0; i < columnasSeleccionadas.length; i++) {
		                HashMap obj=(HashMap) this.columnasSeleccionadas[i];
		                
		                listaConsolidadoAux.add(obj);
					}
					
					criteria.put("listaConsolidado", listaConsolidadoAux);

					if (service.updateConsolidadoInterMoraCCC(criteria, usuario)) {
						mensaje = this.getResourceMessage("mantenimientoCCCFacturarInteresSearchForm.updated");
						this.addInfo("Info :", mensaje);											
					} else {
						mensaje = this.getResourceMessage("mantenimientoCCCFacturarInteresSearchForm.error");
						this.addError("Error : ", mensaje);						
					}
					this.cccFacturarInteresList = new ArrayList();
					this.cccFacturarInteresList = this.setFindAttributes();
					this.datatableCCCD = new DataTableModel(this.cccFacturarInteresList);
					this.columnasSeleccionadas = null;
					return;
				
			} else {
				mensaje = this.getResourceMessage("mantenimientoCCCFacturarInteresSearchForm.error");
				this.addError("Error : ", mensaje);
				return;
			}
			
			

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	public void redireccionar(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'showgratis' method");
		}
		this.mostrarBotonGuardar = false;
		this.mostrarBotonGuardarBoton = false;
		this.mostrarGrilla = false;
		this.mantenimientoCCCFacturarInteresErrorListAux = new ArrayList();
		this.totalRegistros = "";
		this.totalRegistrosConError = "";
		this.totalRegistrosValidos = "";
		this.nombreArchivo ="";
		this.attachmentForm ="";
		this.facturarInteresForm = null;
		// HashMap objetoSeleccionado = (HashMap) this.beanRangosList;
		try {
			mantenimientoCCCFacturarInteresAction.inicializarPantalla();
			String ventana = "PF('viewMantenimientoCCCFacturarInteresPopup').show()";
			this.getRequestContext().execute(ventana);
			// this.mostrarBotonSalir = false;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}

	}

	/**
	 * Carga al servidor el archivo del Popup
	 * @param event
	 * @throws Exception
	 */
	public void handleFileUploadForm(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		
		
		this.facturarInteresForm = new MantenimientoCCCFacturarInteresForm();
		try {
			if (event != null) {
				this.mantenimientoCCCFacturarInteresAction
						.setArchivoPadre(event.getFile());
				this.mantenimientoCCCFacturarInteresAction.cargar();
				this.facturarInteresForm = (MantenimientoCCCFacturarInteresForm) this.mantenimientoCCCFacturarInteresAction
						.getFormProceso();
				this.nombreArchivo = facturarInteresForm.getNombreArchivo();
				this.totalRegistros = facturarInteresForm.getNumRegistros();
				this.setAttachmentForm(event.getFile().getFileName());
				this.mantenimientoCCCFacturarInteresErrorListAux = this.mantenimientoCCCFacturarInteresAction
						.getMantenimientoCCCFacturarInteresErrorList();
				this.mostrarGrilla = true;
			}

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Valida archivo del Popup
	 * @param evt
	 */
	public void validarUploadForm(ActionEvent evt) {
		try {
			if(facturarInteresForm == null){
				this.addError("Error : ", "Se necesitar cargar un archivo.");
				return;
			}
			
			this.mantenimientoCCCFacturarInteresAction.validar();
			facturarInteresForm = (MantenimientoCCCFacturarInteresForm) this.mantenimientoCCCFacturarInteresAction.getFormProceso();
			
			this.totalRegistrosConError = facturarInteresForm.getNumRegistrosError();
			this.totalRegistrosValidos = facturarInteresForm.getNumRegistrosValidos();
			this.mostrarBotonGuardar = true;
			
			if(Integer.parseInt(this.totalRegistrosConError) > 0){
				this.mostrarBotonGuardarBoton = false;
			}else{
				this.mostrarBotonGuardarBoton = true;
			}
			
			this.mantenimientoCCCFacturarInteresErrorListAux = this.mantenimientoCCCFacturarInteresAction.getMantenimientoCCCFacturarInteresErrorList();

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Guarda valores del Popup
	 * @param evt
	 */
	public void guardarDatosForm(ActionEvent evt) {
		try {
			this.mantenimientoCCCFacturarInteresAction.procesarArchivo();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Carga el archivo de C?digos de Consultora
	 * 
	 */
	public void validar() {
		try {
			log.debug("MantenimientoCCCFacturarInteresSearchAction - validar");

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			MantenimientoCCCFacturarInteresSearchForm f = (MantenimientoCCCFacturarInteresSearchForm) this.formBusqueda;
			MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");

			List listaCodigosConsultora = new ArrayList();

			UploadedFile archivoConsultora = f.getArchivoCodigoConsultora();

			log.debug((new StringBuilder()).append("Nombre Archivo Upload: ")
					.append(archivoConsultora.getFileName()).toString());
			InputStream is = archivoConsultora.getInputstream();
			FileOutputStream os = new FileOutputStream(new File(
					service.obtenerPathUpload(pais.getCodigo()),
					archivoConsultora.getFileName()));
			int bytesRead = 0;
			byte buffer[] = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();

			ExcelUtil excelUtil = new ExcelUtil(service.obtenerPathUpload(pais
					.getCodigo()), archivoConsultora.getFileName());
			excelUtil.initSheet(0);

			while (excelUtil.hasNext()) {

				Map mapRow = excelUtil.next();

				String linea = StringUtils.remove((String) mapRow.get("0"),
						".0");

				if (linea != null) {
					if (StringUtils.isNotBlank(linea.trim())
							&& StringUtils.isNumeric(linea.trim())) {
						listaCodigosConsultora.add(linea.trim());
					}
				}
			}
			excelUtil.cerrar();

			this.cccCodigoConsultoraArchivoList = listaCodigosConsultora;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param lista
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	protected String obtieneCondicion(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato)
						|| StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	}

	/**
	 * @param list
	 */
	private void actualizaListaGrilla(List list) {
		MantenimientoCCCFacturarInteresSearchForm f = (MantenimientoCCCFacturarInteresSearchForm) this.formBusqueda;
		try {
			if (list == null)
				return;

			for (int k = 0; k < list.size(); k++) {
				Map params = (Map) list.get(k);
				
//				String montoFacturado = "";
//				if(f.getListaMontoFacturado().length > 0){
//					montoFacturado = f.getListaMontoFacturado()[k];
//				}

				Integer numeroFila = Integer.valueOf(k);

//				params.put("montoInteres", montoFacturado);
				params.put("numeroFila", numeroFila + 1);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCFacturarInteresList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCCCFacturarInteresForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCFacturarInteresSearchForm form = new MantenimientoCCCFacturarInteresSearchForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoCCCFacturarInteresSearchAction");
		}
		MantenimientoCCCFacturarInteresSearchForm f = (MantenimientoCCCFacturarInteresSearchForm) this.formBusqueda;
		f.setFechaProcesoDesde(DateUtil.convertDateToString(f
				.getFechaProcesoDesdeD()));
		f.setFechaProcesoHasta(DateUtil.convertDateToString(f
				.getFechaProcesoHastaD()));

		Map params = BeanUtils.describe(f);
		List consultoraList = this.cccCodigoConsultoraArchivoList;

		if (consultoraList == null)
			consultoraList = new ArrayList();
		if (StringUtils.isNotEmpty(f.getCodigoConsultora()))
			consultoraList.add(f.getCodigoConsultora().trim());

		if (consultoraList.size() > 0) {
			String[] listaCodigoCliente = new String[consultoraList.size()];

			for (int i = 0; i < consultoraList.size(); i++) {
				listaCodigoCliente[i] = consultoraList.get(i).toString();
			}
			params.put("codigoConsultora",
					obtieneCondicion(listaCodigoCliente, "MC.COD_CLIE", "'"));
		}

		MantenimientoCCCFacturarInteresService service = (MantenimientoCCCFacturarInteresService) getBean("spusicc.mantenimientoCCCFacturarInteresService");
		
		List lista = service.getConsoCalcuInteMoralList(params);
		for (int i = 0; i < lista.size(); i++) {
			Map params1 = (Map) lista.get(i);
			Integer numeroFila = Integer.valueOf(i);

			params1.put("numeroFila", numeroFila + 1);
		}
		
		
		
		this.cccFacturarInteresList = new ArrayList();
		this.cccFacturarInteresList = lista;
		this.setDatatableCCCD(new DataTableModel(this.cccFacturarInteresList));
		return this.cccFacturarInteresList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarListaBusqueda = false;
		
		this.mostrarBotonBuscar = true;
		MantenimientoCCCFacturarInteresSearchForm form = (MantenimientoCCCFacturarInteresSearchForm) this.formBusqueda;
		form.setCodigoConsultora("");
		form.setArchivoCodigoConsultora(null);
		this.mostrarBotonGuardar = false;
		this.mostrarBotonGuardarBoton = false;
		this.mostrarGrilla = false;
		this.cccCodigoConsultoraArchivoList = new ArrayList();
		// form.setFechaProcesoDesdeD(new Date());
		// form.setFechaProcesoHastaD(new Date());
	}

	/**
	 * @return the cccCodigoConsultoraArchivoList
	 */
	public List getCccCodigoConsultoraArchivoList() {
		return cccCodigoConsultoraArchivoList;
	}

	/**
	 * @param cccCodigoConsultoraArchivoList
	 *            the cccCodigoConsultoraArchivoList to set
	 */
	public void setCccCodigoConsultoraArchivoList(
			List cccCodigoConsultoraArchivoList) {
		this.cccCodigoConsultoraArchivoList = cccCodigoConsultoraArchivoList;
	}

	/**
	 * @return the cccFacturarInteresList
	 */
	public List getCccFacturarInteresList() {
		return cccFacturarInteresList;
	}

	/**
	 * @param cccFacturarInteresList
	 *            the cccFacturarInteresList to set
	 */
	public void setCccFacturarInteresList(List cccFacturarInteresList) {
		this.cccFacturarInteresList = cccFacturarInteresList;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the datatableCCCD
	 */
	public DataTableModel getDatatableCCCD() {
		return datatableCCCD;
	}

	/**
	 * @param datatableCCCD
	 *            the datatableCCCD to set
	 */
	public void setDatatableCCCD(DataTableModel datatableCCCD) {
		this.datatableCCCD = datatableCCCD;
	}



	/**
	 * @return the facturarInteresForm
	 */
	public MantenimientoCCCFacturarInteresForm getFacturarInteresForm() {
		return facturarInteresForm;
	}

	/**
	 * @param facturarInteresForm
	 *            the facturarInteresForm to set
	 */
	public void setFacturarInteresForm(
			MantenimientoCCCFacturarInteresForm facturarInteresForm) {
		this.facturarInteresForm = facturarInteresForm;
	}

	/**
	 * @return the mantenimientoCCCFacturarInteresErrorList
	 */
	public List getMantenimientoCCCFacturarInteresErrorList() {
		return mantenimientoCCCFacturarInteresErrorList;
	}

	/**
	 * @param mantenimientoCCCFacturarInteresErrorList
	 *            the mantenimientoCCCFacturarInteresErrorList to set
	 */
	public void setMantenimientoCCCFacturarInteresErrorList(
			List mantenimientoCCCFacturarInteresErrorList) {
		this.mantenimientoCCCFacturarInteresErrorList = mantenimientoCCCFacturarInteresErrorList;
	}

	/**
	 * @return the mantenimientoCCCFacturarInteresViewValida
	 */
	public String getMantenimientoCCCFacturarInteresViewValida() {
		return mantenimientoCCCFacturarInteresViewValida;
	}

	/**
	 * @param mantenimientoCCCFacturarInteresViewValida
	 *            the mantenimientoCCCFacturarInteresViewValida to set
	 */
	public void setMantenimientoCCCFacturarInteresViewValida(
			String mantenimientoCCCFacturarInteresViewValida) {
		this.mantenimientoCCCFacturarInteresViewValida = mantenimientoCCCFacturarInteresViewValida;
	}

	/**
	 * @return the mantenimientoCCCFacturarInteresAction
	 */
	public MantenimientoCCCFacturarInteresAction getMantenimientoCCCFacturarInteresAction() {
		return mantenimientoCCCFacturarInteresAction;
	}

	/**
	 * @param mantenimientoCCCFacturarInteresAction
	 *            the mantenimientoCCCFacturarInteresAction to set
	 */
	public void setMantenimientoCCCFacturarInteresAction(
			MantenimientoCCCFacturarInteresAction mantenimientoCCCFacturarInteresAction) {
		this.mantenimientoCCCFacturarInteresAction = mantenimientoCCCFacturarInteresAction;
	}

	/**
	 * @return the attachmentForm
	 */
	public String getAttachmentForm() {
		return attachmentForm;
	}

	/**
	 * @param attachmentForm
	 *            the attachmentForm to set
	 */
	public void setAttachmentForm(String attachmentForm) {
		this.attachmentForm = attachmentForm;
	}

	/**
	 * @return the listaPopup
	 */
	public List getListaPopup() {
		return listaPopup;
	}

	/**
	 * @param listaPopup
	 *            the listaPopup to set
	 */
	public void setListaPopup(List listaPopup) {
		this.listaPopup = listaPopup;
	}

	/**
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the mostrarBotonGuardar
	 */
	public Boolean getMostrarBotonGuardar() {
		return mostrarBotonGuardar;
	}

	/**
	 * @param mostrarBotonGuardar
	 *            the mostrarBotonGuardar to set
	 */
	public void setMostrarBotonGuardar(Boolean mostrarBotonGuardar) {
		this.mostrarBotonGuardar = mostrarBotonGuardar;
	}

	/**
	 * @return the nombreArchivo
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 *            the nombreArchivo to set
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return the totalRegistros
	 */
	public String getTotalRegistros() {
		return totalRegistros;
	}

	/**
	 * @param totalRegistros
	 *            the totalRegistros to set
	 */
	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	/**
	 * @return the totalRegistrosConError
	 */
	public String getTotalRegistrosConError() {
		return totalRegistrosConError;
	}

	/**
	 * @param totalRegistrosConError
	 *            the totalRegistrosConError to set
	 */
	public void setTotalRegistrosConError(String totalRegistrosConError) {
		this.totalRegistrosConError = totalRegistrosConError;
	}

	/**
	 * @return the totalRegistrosValidos
	 */
	public String getTotalRegistrosValidos() {
		return totalRegistrosValidos;
	}

	/**
	 * @param totalRegistrosValidos
	 *            the totalRegistrosValidos to set
	 */
	public void setTotalRegistrosValidos(String totalRegistrosValidos) {
		this.totalRegistrosValidos = totalRegistrosValidos;
	}

	/**
	 * @return the mantenimientoCCCFacturarInteresErrorListAux
	 */
	public List getMantenimientoCCCFacturarInteresErrorListAux() {
		return mantenimientoCCCFacturarInteresErrorListAux;
	}

	/**
	 * @param mantenimientoCCCFacturarInteresErrorListAux
	 *            the mantenimientoCCCFacturarInteresErrorListAux to set
	 */
	public void setMantenimientoCCCFacturarInteresErrorListAux(
			List mantenimientoCCCFacturarInteresErrorListAux) {
		this.mantenimientoCCCFacturarInteresErrorListAux = mantenimientoCCCFacturarInteresErrorListAux;
	}

	/**
	 * @return the mostrarBotonGuardarBoton
	 */
	public Boolean getMostrarBotonGuardarBoton() {
		return mostrarBotonGuardarBoton;
	}

	/**
	 * @param mostrarBotonGuardarBoton the mostrarBotonGuardarBoton to set
	 */
	public void setMostrarBotonGuardarBoton(Boolean mostrarBotonGuardarBoton) {
		this.mostrarBotonGuardarBoton = mostrarBotonGuardarBoton;
	}



	/**
	 * @return the columnasSeleccionadas
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}
}