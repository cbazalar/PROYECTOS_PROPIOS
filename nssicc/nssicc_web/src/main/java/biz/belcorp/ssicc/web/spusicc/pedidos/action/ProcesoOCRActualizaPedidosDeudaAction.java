package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizaPedidosDeudaForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizaPedidosDeudaSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoOCRActualizaPedidosDeudaAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8436872734581804482L;
	private List siccPeriodoList = new ArrayList();
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List pedActualizaDeudaMasivaList = new ArrayList();
	private List clientesFileList = new ArrayList();
	private String attachment = "";
	private DataTableModel datatableModelList;
	private Object[] columnasSeleccionadas;
	private Boolean indicadorAdmCartera;

	public void handleFileUpload(FileUploadEvent event)  {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		try {
			ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) this.formBusqueda;
			if (event != null) {
				// f.setArchivo(event.getFile());
				f.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	/**
	 * 
	 */
	public void uploadArchivo() {
		log.debug("ProcesoOCRActualizaPedidosDeudaSearchAction - loadfile");

		try {
			ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) this.formBusqueda;

			List listaClientes = new ArrayList();

			UploadedFile archivo = f.getClienteFile();

			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea;

			PaisService paisService = (PaisService) getBean("paisService");
			Pais pais = paisService.getPais(f.getCodigoPais());

			int contFilas = 0;
			int numMaximoRegistros = StringUtils.isNotEmpty(pais
					.getMaximoNumeroRegistrosFile()) ? Integer.parseInt(pais
					.getMaximoNumeroRegistrosFile()) : 0;
			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				if (StringUtils.isNotEmpty(linea)) {
					contFilas++;
					if (contFilas > numMaximoRegistros) {

						this.addError("Error",
								getResourceMessage("errors.maximo.registro"));

						// f.reset(mapping, request);
						// return mapping.findForward("view");
						listaClientes = new ArrayList();
						break;

					}
					if (StringUtils.isNotBlank(linea.trim())) {
						Base base = new Base();
						base.setCodigo(linea.trim());
						listaClientes.add(base);
					}
				}
			}

			f.setSelectedItems(new String[] {});

			this.clientesFileList = listaClientes;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] zonaLista = (LabelValue[]) aSvc
					.getZonasByPeriodoIntEviPerioRegioZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), f.getCodigoPeriodo(), f
									.getCodigoRegion(), " ");

			this.siccZonaList = zonaLista == null ? null : zonaLista;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public List getPedActualizaDeudaMasivaList() {
		return pedActualizaDeudaMasivaList;
	}

	/**
	 * @param pedActualizaDeudaMasivaList
	 */
	public void setPedActualizaDeudaMasivaList(List pedActualizaDeudaMasivaList) {
		this.pedActualizaDeudaMasivaList = pedActualizaDeudaMasivaList;
	}

	/**
	 * @return
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "procesoOCRActualizaPedidosDeudaList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "procesoOCRActualizaPedidosDeudaForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoOCRActualizaPedidosDeudaSearchForm e = new ProcesoOCRActualizaPedidosDeudaSearchForm();
		return e;
	}

	/**
	 * 
	 */
	public void loadfile() {

		log.debug("ProcesoOCRActualizaPedidosDeudaSearchAction - loadfile");
		try {
			ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) this.formBusqueda;

			List listaClientes = new ArrayList();

			UploadedFile archivo = f.getClienteFile();

			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea;

			PaisService paisService = (PaisService) getBean("paisService");
			Pais pais = paisService.getPais(f.getCodigoPais());

			int contFilas = 0;
			int numMaximoRegistros = StringUtils.isNotEmpty(pais
					.getMaximoNumeroRegistrosFile()) ? Integer.parseInt(pais
					.getMaximoNumeroRegistrosFile()) : 0;
			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				if (StringUtils.isNotEmpty(linea)) {
					contFilas++;
					if (contFilas > numMaximoRegistros) {
						addInfo("Mensaje",
								this.getResourceMessage("errors.maximo.registro"));

						// f.reset(mapping, request);
						// return mapping.findForward("view");
						listaClientes = new ArrayList();
						break;

					}
					if (StringUtils.isNotBlank(linea.trim())) {
						Base base = new Base();
						base.setCodigo(linea.trim());
						listaClientes.add(base);
					}
				}
			}

			this.clientesFileList = listaClientes;

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] zonaLista = (LabelValue[]) aSvc
					.getZonasByPeriodoIntEviPerioRegioZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), f.getCodigoPeriodo(), f
									.getCodigoRegion(), " ");
			this.siccZonaList = zonaLista == null ? null : zonaLista;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		// Removemos atributos session

		this.pedActualizaDeudaMasivaList.clear();

		ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) formBusqueda;
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);
		// La busqueda solo la realizaremos en los sistemas activos
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		criteria.put("clienteFile", new ArrayList());

		List cliList = clientesFileList;

		if (cliList != null && !cliList.isEmpty()) {
			criteria.put("codigoCliente", null);
			List codigosCliList = new ArrayList();

			for (int i = 0; i < cliList.size(); i++) {
				Base base = (Base) cliList.get(i);
				codigosCliList.add(base.getCodigo());
			}

			criteria.put("clienteFile", codigosCliList);
		}

		if (log.isDebugEnabled()) {
			log.debug("criteria search " + criteria.toString());
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		List list = service.getDeudaPedidosByCriteria(criteria);
		this.pedActualizaDeudaMasivaList = list;

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelValue[] zonaLista = (LabelValue[]) aSvc
				.getZonasByPeriodoIntEviPerioRegioZona(mPantallaPrincipalBean
						.getCurrentCountry().getCodigo(), f.getCodigoPeriodo(),
						f.getCodigoRegion(), " ");
		if (log.isDebugEnabled()) {
			log.debug("zonaLista " + zonaLista);
		}

		this.siccZonaList = zonaLista == null ? null : zonaLista;
		this.datatableModelList = new DataTableModel(
				this.pedActualizaDeudaMasivaList);

		return this.pedActualizaDeudaMasivaList;
	}

	/**
	 * Metodo para realizar la busqueda
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public List convertBaseListToLabelValue(LabelValue[] labelValue) {
		List a = new ArrayList();
		for (int i = 0; i < labelValue.length; i++) {
			Base b = new Base();
			b.setCodigo(labelValue[i].getValue());
			b.setDescripcion(labelValue[i].getLabel());
			a.add(b);
		}
		return a;
	}

	/**
	 * @param list
	 * @return
	 */
	public LabelValue[] convertBaseLabelValueToList(List list) {
		LabelValue[] a = {};
		for (int i = 0; i < list.size(); i++) {
			a[i].setLabel(((Base) list).getDescripcion());
			a[i].setValue(((Base) list).getCodigo());
		}
		return a;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}
	
	/**
	 * @param evt
	 */
	public void procesar(ActionEvent evt){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'grabar' method");
		}
		try {
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			ProcesoOCRActualizaPedidosDeudaSearchForm actualizaPedidosDeudaForm = (ProcesoOCRActualizaPedidosDeudaSearchForm) this.formBusqueda;

			int conIndActivos = 0;
			int conIndInactivos = 0;		
			
			if (this.columnasSeleccionadas == null) {
				this.addError("Error : ", "Seleccione un Elemento de la Lista");
				return;
			}
			int tamanio = this.columnasSeleccionadas.length;
			for (int i = 0; i < tamanio; i++) {
				SolicitudConsolidadoCabecera beanSeleccionado = (SolicitudConsolidadoCabecera) this.columnasSeleccionadas[i];
				if (beanSeleccionado.getIndErrorAdminCartera().compareToIgnoreCase("1") == 0)
					conIndActivos = conIndActivos + 1;
				else
					conIndInactivos = conIndInactivos + 1;
			}
			String[] cadenaInactivo = new String[conIndInactivos];
			String[] cadenaActivo = new String[conIndActivos];

			int indiceActivos = 0;
			int indiceInactivos = 0;
			for (int i = 0; i < tamanio; i++) {
				SolicitudConsolidadoCabecera beanSeleccionado = (SolicitudConsolidadoCabecera) this.columnasSeleccionadas[i];

				if (beanSeleccionado.getIndErrorAdminCartera().compareToIgnoreCase("1") == 0) {
					cadenaActivo[indiceActivos] = beanSeleccionado.getCodigoCliente();
					indiceActivos++;
				} else {
					cadenaInactivo[indiceInactivos] = beanSeleccionado.getCodigoCliente();
					indiceInactivos++;
				}
			}
			
			
			Usuario usuario = (this.mPantallaPrincipalBean.getCurrentUser());

			if (cadenaActivo.length > 0) {
				SolicitudConsolidadoCabecera cabeceraActivos = new SolicitudConsolidadoCabecera();
				BeanUtils.copyProperties(cabeceraActivos, actualizaPedidosDeudaForm);
				cabeceraActivos.setCodigos(cadenaActivo == null ? new ArrayList()
						: Arrays.asList(cadenaActivo));
				cabeceraActivos.setObservaciones("Levantamiento Masivo");
				cabeceraActivos
						.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_INACT);
				service.updateDeudaGeneral(cabeceraActivos, usuario);
			}

			if (cadenaInactivo.length > 0) {
				SolicitudConsolidadoCabecera cabeceraInactivos = new SolicitudConsolidadoCabecera();
				BeanUtils.copyProperties(cabeceraInactivos,
						actualizaPedidosDeudaForm);
				cabeceraInactivos
						.setCodigos(cadenaInactivo == null ? new ArrayList()
								: Arrays.asList(cadenaInactivo));
				cabeceraInactivos.setObservaciones("Levantamiento Masivo");
				cabeceraInactivos
						.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_ACT);
				service.updateDeudaGeneral(cabeceraInactivos, usuario);
			}
			
			this.addError("Info : ", this.getResourceMessage("deuda.updated"));
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#save(javax.faces.event.ActionEvent)
	 */
	public void save(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		try {
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			ProcesoOCRActualizaPedidosDeudaForm actualizaPedidosDeudaForm = (ProcesoOCRActualizaPedidosDeudaForm) this.formMantenimiento;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
			BeanUtils.copyProperties(cabecera, actualizaPedidosDeudaForm);
			log.debug("update bean " + cabecera);
			service.updateDeuda(getCabecera(cabecera), usuario);
			String mensaje = this.getResourceMessage("levanta.deuda.updated");
			this.redireccionarPagina("procesoOCRActualizaPedidosDeudaList");
			this.addInfo("Info : ", mensaje);
			this.datatableModelList = new DataTableModel(this.setFindAttributes());
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	/**
	 * @param cabecera
	 * @return
	 */
	private SolicitudConsolidadoCabecera getCabecera(
			SolicitudConsolidadoCabecera cabecera) {
		SolicitudConsolidadoCabecera consolidadoCabecera = new SolicitudConsolidadoCabecera();

		consolidadoCabecera.setCodigoPais(cabecera.getCodigoPais());
		consolidadoCabecera.setCodigoCliente(cabecera.getCodigoCliente());
		consolidadoCabecera.setCodigoPeriodo(cabecera.getCodigoPeriodo());
		consolidadoCabecera.setFechaSolicitud(cabecera.getFechaSolicitud());

		// Si el checkbox de clave temporal esta activo modificamos
		if (this.indicadorAdmCartera) {
			consolidadoCabecera
					.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_ACT);
		} else {
			consolidadoCabecera
					.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_INACT);
		}

		return consolidadoCabecera;
	}
	
	 /**
     * Metodo para retornar la deuda
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void retornar(ActionEvent evt)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Entering 'retornar' method");
        }	
        if (this.columnasSeleccionadas == null) {
			this.addError("Error : ", this.getResourceMessage("procesoOCRActualizaPedidos.error.notRegistros"));
			return;
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		int tamanio = this.columnasSeleccionadas.length;
		String[] idString = new String[tamanio];
		
		for (int i = 0; i < tamanio; i++) {
			SolicitudConsolidadoCabecera beanSeleccionado = (SolicitudConsolidadoCabecera) this.columnasSeleccionadas[i];
			idString[i] = beanSeleccionado.getCodigoPais() + "|" + beanSeleccionado.getCodigoCliente() + "|" + beanSeleccionado.getCodigoPeriodo()
			+ "|" + beanSeleccionado.getFechaSolicitud() +  "|" + beanSeleccionado.getIndErrorAdminCartera() +  "|" + beanSeleccionado.getNumeroLote();
			
		
		}	  
        Map criteria = new HashMap();
        criteria.put("indicador", Constants.NUMERO_CERO);        
        service.updateLevantarRetornarDeuda(criteria,idString);
        String mensje = this.getResourceMessage("update.retornar.deuda");
        this.addError("Error : ", mensje);
    }
	
	/**
     * Metodo para levantar la deuda
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public void levantar(ActionEvent evt){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'levantar' method");
        }
        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
        Map criteria = new HashMap();
        criteria.put("indicador", Constants.NUMERO_UNO);
        criteria.put("usuario",usuario.getLogin());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");			    
        criteria.put("fecha",sdf.format(new Date(System.currentTimeMillis())));
		if (this.columnasSeleccionadas == null) {
			this.addError("Error : ", this.getResourceMessage("procesoOCRActualizaPedidos.error.notRegistros"));
			return;
		}
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		int tamanio = this.columnasSeleccionadas.length;
		String[] idString = new String[tamanio];
		
		for (int i = 0; i < tamanio; i++) {
			SolicitudConsolidadoCabecera beanSeleccionado = (SolicitudConsolidadoCabecera) this.columnasSeleccionadas[i];
			idString[i] = beanSeleccionado.getCodigoPais() + "|" + beanSeleccionado.getCodigoCliente() + "|" + beanSeleccionado.getCodigoPeriodo()
			+ "|" + beanSeleccionado.getFechaSolicitud() +  "|" + beanSeleccionado.getIndErrorAdminCartera() +  "|" + beanSeleccionado.getNumeroLote();
			
		
		}	    
        service.updateLevantarRetornarDeuda(criteria, idString);
        String mensaje = this.getResourceMessage("update.levantar.deuda");
        this.addInfo("Info : ", mensaje);
    }

	/**
	 * @param evt
	 */
	public void obteniendoDatos(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		try {
			ProcesoOCRActualizaPedidosDeudaForm form = new ProcesoOCRActualizaPedidosDeudaForm();
			if (this.columnasSeleccionadas == null) {
				return;
			}
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			int tamanio = this.columnasSeleccionadas.length;
			if (tamanio >= 2) {
				this.addError("Error : ", "Seleccione un Elemento de la Lista");
				return;
			}
			for (int i = 0; i < tamanio; i++) {
				SolicitudConsolidadoCabecera beanSeleccionado = (SolicitudConsolidadoCabecera) this.columnasSeleccionadas[i];
				SolicitudConsolidadoCabecera cabecera = service
						.getDeudaPedidosById(getCriteria(beanSeleccionado));
				BeanUtils.copyProperties(form, cabecera);
				if (form.getIndErrorAdminCartera().equals("1")) {
					this.indicadorAdmCartera = true;
				} else {
					this.indicadorAdmCartera = false;
				}
				form.setIndicadorAdmCartera(form.getIndErrorAdminCartera()
						.equals(Constants.IND_ERRO_ADM_CARTERA_ACT));
			}
			this.formMantenimiento = form;
			this.redireccionarPagina("procesoOCRActualizaPedidosDeudaForm");
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/**
	 * @param beanSeleccionado
	 * @return
	 */
	private Map getCriteria(SolicitudConsolidadoCabecera beanSeleccionado) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", beanSeleccionado.getCodigoPais());
		criteria.put("codigoCliente", beanSeleccionado.getCodigoCliente());
		criteria.put("codigoPeriodo", beanSeleccionado.getCodigoPeriodo());
		criteria.put("fechaSolicitud", beanSeleccionado.getFechaSolicitud());
		criteria.put("numeroLote", beanSeleccionado.getNumeroLote());
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonSave = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		// Removemos atributos session

		this.pedActualizaDeudaMasivaList.clear();

		// Carga de los combos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		List list = reporteService.getListaPeriodosByBasCtrlFact(
				this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				Constants.NUMERO_CERO);

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList = aSvc
				.getRegionesByPais(this.mPantallaPrincipalBean
						.getCurrentCountry().getCodigo());
		this.siccPeriodoList = list;

		ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) this.formBusqueda;
		f.setFechaInicioD(new Date());
		f.setFechaFinD(new Date());
		f.setCodigoCliente("");

	}

	public void buscarZonasRegionForm(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		try {
			if (StringUtils.isNotEmpty(val.getNewValue().toString())
					|| StringUtils.isNotBlank(val.getNewValue().toString())) {
				ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) this.formBusqueda;
				String regionListado = (String) val.getNewValue();
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				this.siccZonaList = ajax.getZonasByPeriodoIntEviPerioRegioZona(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), f.getCodigoPeriodo(), regionListado, "T");

			} else {
				this.siccZonaList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void validarCodigoCliente() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		try {
			// Removemos atributos session
			this.pedActualizaDeudaMasivaList.clear();

			ProcesoOCRActualizaPedidosDeudaSearchForm f = (ProcesoOCRActualizaPedidosDeudaSearchForm) formBusqueda;
			f.setSelectedItems(new String[] {});

			// Obtenemos las propiedades del bean como un 'Map'
			Map criteria = BeanUtils.describe(f);
			// La busqueda solo la realizaremos en los sistemas activos
			criteria.put("estado", Constants.ESTADO_ACTIVO);
			criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
			criteria.put("clienteFile", new ArrayList());

			List cliList = (List) clientesFileList;

			if (cliList != null && !cliList.isEmpty()) {
				criteria.put("codigoCliente", null);
				List codigosCliList = new ArrayList();

				for (int i = 0; i < cliList.size(); i++) {
					Base base = (Base) cliList.get(i);
					codigosCliList.add(base.getCodigo());
				}

				criteria.put("clienteFile", codigosCliList);
			}

			if (log.isDebugEnabled()) {
				log.debug("criteria search " + criteria.toString());
			}
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			List list = service.getDeudaPedidosByCriteria(criteria);
			pedActualizaDeudaMasivaList = list;

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] zonaLista = (LabelValue[]) aSvc
					.getZonasByPeriodoIntEviPerioRegioZona(
							mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(), f.getCodigoPeriodo(), f
									.getCodigoRegion(), " ");
			if (log.isDebugEnabled()) {
				log.debug("zonaLista " + zonaLista);
			}
			this.siccZonaList = zonaLista == null ? null : this
					.convertBaseLabelValueToList(Arrays.asList(zonaLista));
			;
		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the clientesFileList
	 */
	public List getClientesFileList() {
		return clientesFileList;
	}

	/**
	 * @param clientesFileList
	 *            the clientesFileList to set
	 */
	public void setClientesFileList(List clientesFileList) {
		this.clientesFileList = clientesFileList;
	}

	/**
	 * @return the datatableModelList
	 */
	public DataTableModel getDatatableModelList() {
		return datatableModelList;
	}

	/**
	 * @param datatableModelList
	 *            the datatableModelList to set
	 */
	public void setDatatableModelList(DataTableModel datatableModelList) {
		this.datatableModelList = datatableModelList;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas
	 *            the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the indicadorAdmCartera
	 */
	public Boolean getIndicadorAdmCartera() {
		return indicadorAdmCartera;
	}

	/**
	 * @param indicadorAdmCartera the indicadorAdmCartera to set
	 */
	public void setIndicadorAdmCartera(Boolean indicadorAdmCartera) {
		this.indicadorAdmCartera = indicadorAdmCartera;
	}

	
}
