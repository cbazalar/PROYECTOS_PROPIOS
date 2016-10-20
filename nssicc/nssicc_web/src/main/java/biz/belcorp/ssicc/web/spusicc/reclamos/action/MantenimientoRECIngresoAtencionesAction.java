package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECIngresoAtencionesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoRECIngresoAtencionesAction extends
		BaseMantenimientoSearchAbstractAction {
	private int longitudCampoClientes;
	private String numeroLote;
	private List recTipoOperacionList = new ArrayList();
	private List recBusquedaIngresoAtencionesList = new ArrayList();
	private List recProcesarIngresoAtencionesList = new ArrayList();
	private List recClientesIngresoAtencionesList = new ArrayList();
	private List listResultConsultoras = new ArrayList();
	private List mantenimientoRECIngresoAtencionesClientesList = new ArrayList();
	private List mantenimientoRECIngresoAtencionesProcessList = new ArrayList();
	private List mantenimientoRECIngresoAtencionesSearchList = new ArrayList();
	private boolean flagFilaCodigos;
	private List columnasSeleccionadas;
	private Object[] columnasSeleccionadas2;
	private Boolean deshabilitaFinalProceso = false;
	private Boolean mostrarFilaCodigos;

	/**
	 * @return
	 */
	public boolean isFlagFilaCodigos() {
		return flagFilaCodigos;
	}

	/**
	 * @param flagFilaCodigos
	 */
	public void setFlagFilaCodigos(boolean flagFilaCodigos) {
		this.flagFilaCodigos = flagFilaCodigos;
	}

	/**
	 * @return
	 */
	public List getMantenimientoRECIngresoAtencionesProcessList() {
		return mantenimientoRECIngresoAtencionesProcessList;
	}

	/**
	 * @param mantenimientoRECIngresoAtencionesProcessList
	 */
	public void setMantenimientoRECIngresoAtencionesProcessList(
			List mantenimientoRECIngresoAtencionesProcessList) {
		this.mantenimientoRECIngresoAtencionesProcessList = mantenimientoRECIngresoAtencionesProcessList;
	}

	/**
	 * @return
	 */
	public List getMantenimientoRECIngresoAtencionesSearchList() {
		return mantenimientoRECIngresoAtencionesSearchList;
	}

	/**
	 * @param mantenimientoRECIngresoAtencionesSearchList
	 */
	public void setMantenimientoRECIngresoAtencionesSearchList(
			List mantenimientoRECIngresoAtencionesSearchList) {
		this.mantenimientoRECIngresoAtencionesSearchList = mantenimientoRECIngresoAtencionesSearchList;
	}

	/**
	 * @return
	 */
	public List getMantenimientoRECIngresoAtencionesClientesList() {
		return mantenimientoRECIngresoAtencionesClientesList;
	}

	/**
	 * @param mantenimientoRECIngresoAtencionesClientesList
	 */
	public void setMantenimientoRECIngresoAtencionesClientesList(
			List mantenimientoRECIngresoAtencionesClientesList) {
		this.mantenimientoRECIngresoAtencionesClientesList = mantenimientoRECIngresoAtencionesClientesList;
	}

	private static final long serialVersionUID = 7111205488417888764L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECIngresoAtencionesForm e = new MantenimientoRECIngresoAtencionesForm();
		return e;
	}

	/**
	 * Busqueda general
	 * @param evt
	 * @return
	 */
	public List buscar(ActionEvent evt) {

		try {
			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) this.formBusqueda;

			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			Map criteria = new HashMap();

			if (!f.getCodigoVenta().equals(""))
				criteria.put("codigoVenta", f.getCodigoVenta());
			if (!f.getCodigoSAP().equals(""))
				criteria.put("codigoSAP", f.getCodigoSAP());
			if (!f.getDescripcionProducto().equals(""))
				criteria.put("descripcion", f.getDescripcionProducto());
			criteria.put("codigoPeriodo", f.getCodigoPeriodoReferencia());

			List resultList = new ArrayList();
			this.recBusquedaIngresoAtencionesList.clear();
			if (f.getTipoProducto().equals("premio")) {
				resultList = service.getReclamosPremio(criteria);
			} else {
				resultList = service.getReclamosMatriz(criteria);
			}
			for (int i = 0; i < resultList.size(); i++) {
				BoletaRecojoDetalle detalle = (BoletaRecojoDetalle) resultList
						.get(i);
				detalle.setUnidadesReclamadas("1");
				this.recBusquedaIngresoAtencionesList.add(detalle);
			}
			f.setFlagVacio(Constants.NO);
			return this.recBusquedaIngresoAtencionesList;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	/**
	 * Añadiendo a la otra lista los valores.
	 * 
	 * @param evt
	 */
	public void anhadir(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'anhadir' method");
		}
		try {
			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm)this.formBusqueda;									
			
			List newBusquedaList = new ArrayList();		
			List procesarList = this.recProcesarIngresoAtencionesList;
			if(procesarList == null)
				procesarList = new ArrayList();
			
			if(this.columnasSeleccionadas != null && this.columnasSeleccionadas.size() > 0)
			{			
				for (int j = 0; j < this.recBusquedaIngresoAtencionesList.size(); j++) 
				{
					BoletaRecojoDetalle boleta = (BoletaRecojoDetalle) this.recBusquedaIngresoAtencionesList.get(j);
					Integer entero = Integer.valueOf(boleta.getUnidadesReclamadas()) % 10;
					boleta.setUnidadesReclamadas(entero.toString());
					boolean flag = false;
					for (int i = 0; i < this.columnasSeleccionadas.size(); i++) 
					{
						BoletaRecojoDetalle boletaSeleccionada = (BoletaRecojoDetalle) this.columnasSeleccionadas.get(i);
						
						if (boleta.getCodigoVenta().equals(boletaSeleccionada.getCodigoVenta())
								&& boleta.getCodigoSAP().equals(boletaSeleccionada.getCodigoSAP())) {
							flag = true;
							break;
						}
					}
					
					if(flag)
						procesarList.add(boleta);
					else
						newBusquedaList.add(boleta);
				}
			}
			
			this.recBusquedaIngresoAtencionesList = newBusquedaList;
			this.recProcesarIngresoAtencionesList = procesarList;
					
			f.setFlagVacio(Constants.NO);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
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

	/**
	 * @param event
	 */
	public void validar(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}
		this.flagFilaCodigos = false;
		this.mostrarFilaCodigos = false;
		try {
			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) formBusqueda;
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			String cliente = "";
			Map criteria = new HashMap();
			List listaClientes = new ArrayList();
			// FileUpload archivo = f.getArchivo();
			InputStream is = event.getFile().getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String linea = "";
			String valTipoProducto = "";
			String valTipoAtencion = "";
			String codTipoOper = "";

			valTipoProducto = f.getTipoProducto();
			valTipoAtencion = f.getTipoAtencion();
			codTipoOper = f.getTipoOperacion();

			log.debug("Tipo Producto : " + valTipoProducto);
			log.debug("Tipo Atención : " + valTipoAtencion);
			log.debug("Tipo Operacion : " + codTipoOper);

			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;
				log.debug("---> " + linea);
				criteria.put("codigoConsultora", linea);
				cliente = service.getCodigoConsultora(criteria);
				LabelValue bean = new LabelValue(linea, cliente);
				listaClientes.add(bean);
			}
			if (listaClientes.size() != 0) {
				f.setMostrarPanel(Constants.SI);
			} else {
				f.setMostrarPanel("V");
			}
			this.flagFilaCodigos = true;
			this.mostrarFilaCodigos = true;
			this.recClientesIngresoAtencionesList.clear();
			this.recClientesIngresoAtencionesList = listaClientes;
			// request.getSession().removeAttribute(Constants.REC_CLIENTES_INGRESO_ATENCIONES_LIST);
			// request.getSession().setAttribute(Constants.REC_CLIENTES_INGRESO_ATENCIONES_LIST,listaClientes);
			f.setTipoProducto(valTipoProducto);
			f.setTipoAtencion(valTipoAtencion);
			f.setsFlagTipoOper("S");
			f.setCodTipoProducto(valTipoProducto);
			f.setCodTipoAtencion(valTipoAtencion);
			f.setCodTipoOperDefault(codTipoOper);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param event
	 */
	public void validarCliente(FileUploadEvent event) {

		try {
			boolean flag = false;

			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) this.formBusqueda;
			String codigoReferencia = f.getCodigoPeriodoReferencia();
			if (StringUtils.isBlank(codigoReferencia)) {
				this.addError("Error : ",
						"'Campaña de referencia' es un campo requerido");
				return;

			}
			String data = ajaxService
					.getValidarCampanhiaActiva(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), codigoReferencia);

			if (data.equals("1")) {
				this.addError(
						"Mensaje",
						getResourceMessage("mantenimientoRECIngresoAtencionesForm.msg.errorCodPeriodoReferencia"));
				flag = true;
			}
			if (flag == true) {
				return;
			}
			// parte 1

			String data2 = ajaxService
					.getValidarCampanhiaProceso(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), f
							.getCodigoPeriodoProceso());
			if (data2.equals("1")) {
				this.addInfo(
						"Mensaje",
						getResourceMessage("mantenimientoRECIngresoAtencionesForm.msg.errorCodProceso"));
				flag = true;
			}
			if (flag == true) {
				return;
			}
			// parte 2
			if (!event.getFile().getFileName().equals("")) {
				validar(event);
			} else {
				this.addInfo("Mensaje", "Debe Agregar un Archivo");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		this.flagFilaCodigos = true;
		MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) this.formBusqueda;
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO);
		String campahniaActual = service.getObtenerCampahniaActual(criteria);
		this.flagFilaCodigos = false;
		this.mostrarFilaCodigos = false;

		if (campahniaActual == null) {
			campahniaActual = "";
		}
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue[] valores = ajax.getTipoOperacionList("matriz", "normal");
		this.recTipoOperacionList = new ArrayList();
		for (LabelValue object : valores) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(object.getLabel());
			labelValue.setValue(object.getValue());
			Base valor = new Base();
			valor.setCodigo(labelValue.getValue().toString());
			valor.setDescripcion(labelValue.getLabel().toString());
			this.recTipoOperacionList.add(valor);
		}

		initForm(f);
		f.setCodigoPeriodoProceso(campahniaActual);
		f.setTipoOperacion(((Base)this.recTipoOperacionList.get(0)).getCodigo());

		f.setsFlagTipoOper("N");
		this.numeroLote = null;

	}

	/**
	 * Metodo que recibo la el producto y la atencion, cambiando su respectivo
	 * combo
	 * 
	 * @param e
	 */
	public void cambiarCombo(AjaxBehaviorEvent e) {
		try {
			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) this.formBusqueda;
			String primerValor = f.getTipoProducto();
			String segundoValor = f.getTipoAtencion();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			LabelValue[] valores = ajax.getTipoOperacionList(primerValor,
					segundoValor);
			this.recTipoOperacionList = new ArrayList();
			for (LabelValue object : valores) {
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(object.getLabel());
				labelValue.setValue(object.getValue());
				Base valor = new Base();
				valor.setCodigo(labelValue.getValue().toString());
				valor.setDescripcion(labelValue.getLabel().toString());
				this.recTipoOperacionList.add(valor);
			}
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));
		}

	}

	/**
	 * Inicializando parametros
	 * 
	 * @param f
	 */
	private void initForm(MantenimientoRECIngresoAtencionesForm f) {

		this.recBusquedaIngresoAtencionesList.clear();
		this.recProcesarIngresoAtencionesList.clear();
		this.recClientesIngresoAtencionesList.clear();
		this.listResultConsultoras.clear();
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonEliminar = false;
		f.setTipoProducto("matriz");
		f.setTipoAtencion("normal");

		f.setMostrarPanel(Constants.NO);
		f.setCodigoPeriodoProceso("");
		f.setCodigoPeriodoReferencia("");
		f.setCodigoVenta("");
		f.setCodigoSAP("");
		f.setDescripcionProducto("");
		f.setFlagVacio("");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 *             Elimina items de la lista de proceso
	 * 
	 */
	public void eliminar(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'eliminar' method");
		}
		try {
			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) this.formBusqueda;
			int tamanioListaIngresoAtencionFijo = this.recProcesarIngresoAtencionesList
					.size();
			int tamanioListaIngresoAtencion = this.recProcesarIngresoAtencionesList
					.size();
			int tamanioColumnasSeleccionas2 = this.columnasSeleccionadas2.length;
			List listaOriginal = this.recProcesarIngresoAtencionesList;
			// comparando
			int contador = 0;
			for (int i = 0; i < tamanioListaIngresoAtencion; i++) {
				if (this.columnasSeleccionadas2.length == contador) {
					break;
				}
				if (tamanioListaIngresoAtencion != 0) {

					BoletaRecojoDetalle boletaLista = (BoletaRecojoDetalle)recProcesarIngresoAtencionesList.get(i);
					for (int j = 0; j < tamanioColumnasSeleccionas2; j++) {
						BoletaRecojoDetalle boletasSeleccionadas = (BoletaRecojoDetalle) this.columnasSeleccionadas2[j];
						if (boletaLista.getCodigoVenta().equals(
								boletasSeleccionadas.getCodigoVenta())) {
							this.recProcesarIngresoAtencionesList.remove(i);
							contador++;
							tamanioListaIngresoAtencion = this.recProcesarIngresoAtencionesList
									.size();
							i--;
						}

					}
				}

			}

			f.setFlagVacio(Constants.NO);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param evt
	 */
	public void procesar(ActionEvent evt) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			MantenimientoRECIngresoAtencionesForm f = (MantenimientoRECIngresoAtencionesForm) formBusqueda;
			List listResultado = new ArrayList();// se guarad la lista de
													// clientes
													// que al final se mostarra
													// con
													// ok o error
			Map criteria = new HashMap();
			criteria.put("tipoProducto", f.getTipoProducto());
			criteria.put("tipoAtencion", f.getTipoAtencion());
			criteria.put("campanhaProceso", f.getCodigoPeriodoProceso());
			criteria.put("campanhaReferencia", f.getCodigoPeriodoReferencia());
			criteria.put("codigoPais", pais.getCodigo());
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			criteria.put("codigoUsuario", usuario.getLogin().toUpperCase());
			criteria.put("mensajeError", null);
			criteria.put("tipoOperacion", f.getTipoOperacion());

			List procesarList = this.recProcesarIngresoAtencionesList;

			List clientesList = this.recClientesIngresoAtencionesList;
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			String valTipoProducto = "";
			String valTipoAtencion = "";
			String codTipoOper = "";

			valTipoProducto = f.getTipoProducto();
			valTipoAtencion = f.getTipoAtencion();
			codTipoOper = f.getTipoOperacion();

			// Ejecuto el proceso para cada cliente de la lista
			for (int i = 0; i < clientesList.size(); i++) {
				LabelValue bean = (LabelValue) clientesList.get(i);
				// Solo proceso los clientes validos
				// if(!bean.getValue().equals("")){
				if (bean.getValue() != null) {
					criteria.put("codigoCliente", bean.getLabel());
					service.procesarAtenciones(criteria, procesarList);
					this.numeroLote = (String) criteria.get("numeroLote");
					String mensaje = (String) criteria.get("mensajeError");
					Map mapResultado = new HashMap();
					mapResultado.put("codigoCliente", bean.getLabel());
					mapResultado.put("mensajeError", mensaje);
					listResultado.add(mapResultado);
				} else {

					String mensaje = getResourceMessage("ccc.mensaje.consultoraNoExiste");
					Map mapResultado = new HashMap();
					mapResultado.put("codigoCliente", bean.getLabel());
					mapResultado.put("mensajeError", mensaje);
					listResultado.add(mapResultado);
				}
			}
			this.addInfo(
					"Mensaje",
					getResourceMessage("mantenimientoRECIngresoAtencionesForm.procesado"));

			this.recBusquedaIngresoAtencionesList.clear();
			this.recProcesarIngresoAtencionesList.clear();
			this.recClientesIngresoAtencionesList.clear();

			f.setMostrarPanel(Constants.NO);
			f.setFlagVacio("");
			this.listResultConsultoras = listResultado;

			f.setsFlagTipoOper("S");
			f.setCodTipoProducto(valTipoProducto);
			f.setCodTipoAtencion(valTipoAtencion);
			f.setCodTipoOperDefault(codTipoOper);
			this.flagFilaCodigos = true;
			this.mostrarFilaCodigos = false;
			this.deshabilitaFinalProceso = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return
	 */
	public int getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes
	 */
	public void setLongitudCampoClientes(int longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	/**
	 * @return
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return
	 */
	public List getRecTipoOperacionList() {
		return recTipoOperacionList;
	}

	/**
	 * @param recTipoOperacionList
	 */
	public void setRecTipoOperacionList(List recTipoOperacionList) {
		this.recTipoOperacionList = recTipoOperacionList;
	}

	/**
	 * @return
	 */
	public List getRecBusquedaIngresoAtencionesList() {
		return recBusquedaIngresoAtencionesList;
	}

	/**
	 * @param recBusquedaIngresoAtencionesList
	 */
	public void setRecBusquedaIngresoAtencionesList(
			List recBusquedaIngresoAtencionesList) {
		this.recBusquedaIngresoAtencionesList = recBusquedaIngresoAtencionesList;
	}

	/**
	 * @return
	 */
	public List getRecProcesarIngresoAtencionesList() {
		return recProcesarIngresoAtencionesList;
	}

	/**
	 * @param recProcesarIngresoAtencionesList
	 */
	public void setRecProcesarIngresoAtencionesList(
			List recProcesarIngresoAtencionesList) {
		this.recProcesarIngresoAtencionesList = recProcesarIngresoAtencionesList;
	}

	/**
	 * @return
	 */
	public List getRecClientesIngresoAtencionesList() {
		return recClientesIngresoAtencionesList;
	}

	/**
	 * @param recClientesIngresoAtencionesList
	 */
	public void setRecClientesIngresoAtencionesList(
			List recClientesIngresoAtencionesList) {
		this.recClientesIngresoAtencionesList = recClientesIngresoAtencionesList;
	}

	/**
	 * @return
	 */
	public List getListResultConsultoras() {
		return listResultConsultoras;
	}

	/**
	 * @param listResultConsultoras
	 */
	public void setListResultConsultoras(List listResultConsultoras) {
		this.listResultConsultoras = listResultConsultoras;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas
	 *            the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the columnasSeleccionadas2
	 */
	public Object[] getColumnasSeleccionadas2() {
		return columnasSeleccionadas2;
	}

	/**
	 * @param columnasSeleccionadas2
	 *            the columnasSeleccionadas2 to set
	 */
	public void setColumnasSeleccionadas2(Object[] columnasSeleccionadas2) {
		this.columnasSeleccionadas2 = columnasSeleccionadas2;
	}

	/**
	 * @return the deshabilitaFinalProceso
	 */
	public Boolean getDeshabilitaFinalProceso() {
		return deshabilitaFinalProceso;
	}

	/**
	 * @param deshabilitaFinalProceso the deshabilitaFinalProceso to set
	 */
	public void setDeshabilitaFinalProceso(Boolean deshabilitaFinalProceso) {
		this.deshabilitaFinalProceso = deshabilitaFinalProceso;
	}

	/**
	 * @return the mostrarFilaCodigos
	 */
	public Boolean getMostrarFilaCodigos() {
		return mostrarFilaCodigos;
	}

	/**
	 * @param mostrarFilaCodigos the mostrarFilaCodigos to set
	 */
	public void setMostrarFilaCodigos(Boolean mostrarFilaCodigos) {
		this.mostrarFilaCodigos = mostrarFilaCodigos;
	}

}
