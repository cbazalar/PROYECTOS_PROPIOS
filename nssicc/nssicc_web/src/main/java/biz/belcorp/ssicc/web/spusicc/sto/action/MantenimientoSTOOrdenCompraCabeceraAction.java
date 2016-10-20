package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ProcesoBatchActu;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.OrdenCompraCabecera;
import biz.belcorp.ssicc.dao.spusicc.sto.model.OrdenCompraDetalle;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.IdiomaService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOHistoricoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaMAEClientePopupSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.ConsultaHIPDatosClienteAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOOrdenCompraCabeceraForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOOrdenCompraCabeceraAction extends	BaseMantenimientoSTOGestionAction {

	private static final long serialVersionUID = 7936558173619975664L;

	private List stoDetallesOccList;
	private List stoVencimientoCronogramaOccList;
	private List stoVencimientoCronogramaSegundoCasoOccList;
	private List stoVencimientoCronogramaTercerCasoOccList;
	private List stoExistenciaCronogramaOccList;
	private List stoMotivosGestion;
	private Integer longitudCampoClientes;
	private Integer longitudUnidadesMaxima;
	private DataTableModel stoDetallesOccListTableModel;
	private DataTableModel stoExistenciaCronogramaTableModel;
	private DataTableModel stoVencimientoCronogramaTableModel;
	private DataTableModel stoVencimientoCronogramaSegundoCasoTableModel;
	private DataTableModel stoVencimientoCronogramaTercerCasoTableModel;
	private boolean editable;
	private boolean newRecord;
	private boolean bMontoMin;
	private boolean bMontoMax;	
	private Object [] stoDetalleSeleccionado;
	private String codigoPopup;
	private List cuadroOfertasList;
	private DataTableModel cuadroOfertasModel;
	private boolean showCuadroOferta;
	private String activarBuscarGrabar = "N";
	private boolean showValidaOCC16;
	
	@ManagedProperty(value = "#{consultaHIPDatosClienteAction}")
	protected ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaMAEClientePopupSearchAction}")	
	private BusquedaMAEClientePopupSearchAction busquedaMAEClientePopup;	
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOOrdenCompraCabeceraForm form = new MantenimientoSTOOrdenCompraCabeceraForm();
		return form;
	}

	@Override
	public String setValidarMantenimiento() {
		MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelDatosConsultoraValue[] consultora;
		consultora = ajax.getCabeceraConsultoraSimple(f.getCodigoPais(),
				f.getCodCliente());
		if (consultora != null) {
			if (StringUtils.equals(f.getCodCliente(), f.getCodClienteBD())) {
				if (this.bMontoMin)
					f.setMontoMinimo("S");
				else 
					f.setMontoMinimo("N");					
				
				if (this.bMontoMax)
					f.setMontoMaximo("S");
				else 
					f.setMontoMaximo("N");
			} else {
				String valor = ajax.getConsolidadoCabeceraByPK(
						f.getCodigoPais(), f.getCodigoPeriodo(),
						f.getCodCliente(), f.getNumLote());
				if (!StringUtils.equals(valor, "0")) 
					return this.getResourceMessage("mensaje.pedido.facturado.rechazado.campanha");
				else {
					if (this.bMontoMin)
						f.setMontoMaximo("S");
					else 
						f.setMontoMinimo("N");						
					
					if (this.bMontoMax)
						f.setMontoMaximo("S");
					else 
						f.setMontoMaximo("N");				
				}
			}
		} else
			return this.getResourceMessage("mensaje.noExisteConsultora");

		return null;
	}

	@Override
	public String setValidarConfirmar(String accion) {
		MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if (accion.equals("INSERTAR_REGISTRO")) {
			if (StringUtils.isEmpty(f.getCodigoVenta()))
				return "Debe de ingresar el codigo de venta para agregar";
			if (f.getUnidades() == null)
				return "Debe de ingresar las unidades para agregar";
			if (f.getUnidades() > Integer.parseInt(f.getUnidadesMaximas()))
				return this.getResourceMessage("mensaje.confirm.mayor99")
						+ " "+ f.getUnidadesMaximas()+ " "
						+ this.getResourceMessage("mensaje.alert.modificarRegistro");

			String validar = ajax.validarDetalle(f.getCodigoPais(),
					f.getCodigoVenta(), f.getCodPeriodo(), f.getCodigoCliente(), f.getNumLote());
			if (StringUtils.equals(validar, "M"))
				return "El código de venta no se encuentra en la matriz";
			if (StringUtils.equals(validar, "F"))
				return "Ya se encuentra el detalle agregado";
		}

		if (accion.equals("MODIFICAR_REGISTRO")) {
			if (this.stoDetalleSeleccionado == null)
				return this.getResourceMessage("errors.select.item");
			
			if (this.stoDetalleSeleccionado.length!=1)
				return this.getResourceMessage("errors.select.unique.item");
		}
		if (accion.equals("GUARDAR_REGISTRO")) {
			if (f.getUnidades() == null
					&& f.getUnidades().toString().equals(""))
				return "Debe de ingresar las unidades para agregar";
			if (f.getUnidades() > Integer.parseInt(f.getUnidadesMaximas()))
				return this.getResourceMessage("mensaje.confirm.mayor99")
						+ " "+ f.getUnidadesMaximas()+ " "
						+ this.getResourceMessage("mensaje.alert.modificarRegistro");
		}

		if (accion.equals("ELIMINAR_REGISTRO")) {
			if (this.stoDetalleSeleccionado == null)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;

		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");

		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoSistema", Constants.STO_CODIGO_SISTEMA);
		params.put("codigoProcesoBatch", Constants.STO_PROC_BATC_OCC);

		List listaProcesoBatchDependientes = procesoBatchService.getProcesoBatchActuDependientesByCriteria(params);
		if (listaProcesoBatchDependientes.size() > 0) {

			String mensaje = this.getResourceMessage("procesoBatch.error.procesoDependienteEnEjecucion");

			for (int i = 0; i < listaProcesoBatchDependientes.size(); i++) {

				ProcesoBatchActu procesoBatchActu = (ProcesoBatchActu) listaProcesoBatchDependientes.get(i);

				mensaje += "\n"+ procesoBatchActu.getCodigoSistema()+ "-"+ procesoBatchActu.getProcesoBatch().getCodigoProcesoBatch();
				mensaje += " "+ procesoBatchActu.getProcesoBatch().getDescripcionProcesoBatch();
				mensaje += "; ";
			}
			mensaje += "\n"+ this.getResourceMessage("procesoBatch.error.espereProcesoDependienteEnEjecucion");
			log.warn(mensaje);

			f.setIndicadorGuardar("N");
			this.editable = true;
			f.setNuevo(true);
			throw new Exception(mensaje);
		}

		if(StringUtils.isBlank(f.getMontoPedido()))
			f.setMontoPedido("");
		if(StringUtils.isBlank(f.getOidMotiGes()))
			f.setOidMotiGes("");
		if(StringUtils.isBlank(f.getValObseGestion()))
			f.setValObseGestion("");
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("numDocumento", f.getNumSecuencia());
		criteria.put("numLote", f.getNumLote());
		criteria.put("codPeriodo", f.getCodPeriodo());
		criteria.put("codCliente", f.getCodCliente());
		criteria.put("numCliente", f.getNumCliente().toString());
		criteria.put("tipoSolicitud", f.getTipoSolicitud());
		criteria.put("codSubAcc", f.getCodSubAcceso());
		criteria.put("codAcc", f.getCodAcceso());
		criteria.put("tipoDespacho", f.getTipoDespacho());
		criteria.put("estadoProceso", f.getEstadoProceso());
		criteria.put("motivoRechazo", f.getCodMotivoRechazo());
		criteria.put("codRegion", f.getCodRegion());
		criteria.put("codZona", f.getCodZona());
		criteria.put("montoPedido", f.getMontoPedido());
		criteria.put("codClienteBD", f.getCodClienteBD());
		criteria.put("montoMaximo", f.getMontoMaximo());
		criteria.put("montoMinimo", f.getMontoMinimo());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("codigoZonaArribo", f.getCodigoZonaArribo());
		criteria.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		criteria.put("oidMotiGes", f.getOidMotiGes());
		criteria.put("valObseGestion", f.getValObseGestion());

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ProcesoSTOHistoricoService serviceHistorico = (ProcesoSTOHistoricoService) getBean("spusicc.procesoSTOHistoricoService");
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");

		// Se valida que no se este ejecutando ninguna validacion
		List historicoList = serviceHistorico
				.getProcesoValidacionEjecucionByDocumento(criteria);
		if (historicoList.size() > 0) {
			HistoricoTipoDocumento historicoTipoDocumento = (HistoricoTipoDocumento) historicoList
					.get(0);
			String mensaje = this
					.getResourceMessage("procesoSTOLevantamientoErroresValidacionForm.validacionMasivaEjecucion")
					+ historicoTipoDocumento.getUsuarioProceso()
					+ historicoTipoDocumento.getMinutosEjecucion();
			f.setIndicadorGuardar("N");
			this.editable = true;
			f.setNuevo(true);
			throw new Exception(mensaje);
		}

		// Se valida que no se este ejecutando ninguna validacion
		List cantEjecuciones = procesoSTOService
				.getCargaEjecucionByDocumento(criteria);
		if (cantEjecuciones.size() > 0) {
			Map map = (Map) cantEjecuciones.get(0);
			String mensaje = this
					.getResourceMessage("procesoSTOLevantamientoErroresValidacionForm.cargaOrdenCompra")
					+ map.get("usuario").toString()
					+ map.get("minutos").toString();
			f.setIndicadorGuardar("N");
			this.editable = true;
			f.setNuevo(true);
			throw new Exception(mensaje);
		}

		// Invocaion al proceso atomico
		procesoSTOEjecucionValidacionesService.updateCodigoConsultora(criteria);
		
		
		//Invoca al proceso para ejecutar el buscar.
		/*ProcesoSTOLevantamientoErroresValidacionAction action = findManageBean("procesoSTOLevantamientoErroresValidacionAction");
		List busqueda=action.setFindAttributes();
		action.setNuevaLista(busqueda);
		DataTableModel busquedaModel=new DataTableModel(action.getNuevaLista());	
		action.setNuevaListaModel(busquedaModel);		
		*/
		return true;
	}
	
	public void buscarCliente() throws Exception{
		MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String codigoClienteCompletado = completarCaracteres(f.getCodCliente(), this.longitudCampoClientes, "0");
		if(StringUtils.isNotEmpty(f.getLongitudNumeroDocIdentidad())){
			int numdoc=Integer.parseInt(f.getLongitudNumeroDocIdentidad());
			String numeroDocumentoIdentidadCompletado =completarCaracteres(f.getCodCliente(), numdoc, "0");
			
			String data=ajax.getNombreConsultoraOrdenCompraSTO(f.getCodigoPais(), codigoClienteCompletado, f.getOidIdioma(), numeroDocumentoIdentidadCompletado, f.getIndicadorValidaDocIdentidad());
			String nomConsu= ""; 
			String codConsu = "";    	
			if(StringUtils.isEmpty(data)){			
				f.setCodCliente(f.getCodClienteAux());
			}else{
				nomConsu = data.split("-")[0];
		   		codConsu = data.split("-")[1];
		   		f.setCodCliente(codConsu);	
			}
			this.nombreConsultora= nomConsu;
		}else{
			if(StringUtils.isBlank(this.codigoPopup))
				f.setCodCliente(f.getCodClienteAux());
			else{
				if(StringUtils.equals(f.getCodCliente(), this.codigoPopup)){
					f.setCodCliente(this.codigoPopup);
				}else{					
					this.codigoPopup="";
					f.setCodCliente(f.getCodClienteAux());
				}					
			}				
		}		
			
	}
	
	public String completarCaracteres(String valor, Integer longitud, String caracter){
		String valorAux ="";
		
		if (valor.length() != 0) {
			int faltante = longitud - valor.length();	
			
			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			}
			else {			
				faltante = valor.length() - longitud;
				valorAux = valor.substring(faltante, longitud);
				
			}
		}
		
		return valorAux;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonSalir = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonBuscar = false;
		this.mostrarBotonSave = true;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonNuevo = false;
		this.bMontoMax = false;
		this.bMontoMin = false;	
		this.showValidaOCC16=false;
	}

	public void inicializarValores() throws Exception {
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
		GenericoService genericoService = (GenericoService) getBean("genericoService");

		f.setCodigoPais(pais.getCodigo());
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteriaOperacion);
		f.setOidPais(String.valueOf(oidPais));

		// +++ INI PER-SiCC-2013-0882 +++
		IdiomaService idiomaService = (IdiomaService) getBean("idiomaService");
		String s = pais.getCodigoIdiomaIso();
		Idioma idioma = idiomaService.getIdiomaByCodigoISO(s);
		Map parameterMap = new HashMap();
		parameterMap.put("codigoIdiomaIso", idioma.getCodigoSiCC());
		parameterMap.put("codigoIdioma", idioma.getCodigoISO());
		String oidIdiomaIso = reporteService.getOidString(
				"getOidIdiomaByCodigoIdiomaIso", parameterMap);
		f.setOidIdioma(oidIdiomaIso);

		String indValidaCodConsultoraDocIdentidad = null;
		String valorPorDefectoCodTipoDocumento = null;

		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(pais.getCodigo());
		parametroPais.setCodigoSistema("STO");
		parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

		List lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
			f.setIndicadorValidaDocIdentidad(indValidaCodConsultoraDocIdentidad);
		}

		parametroPais.setNombreParametro("valorPorDefectoCodTipoDocumento");

		lstParametros = genericoService.getParametrosPais(parametroPais);

		if (lstParametros != null && lstParametros.size() > 0) {
			ParametroPais ps = (ParametroPais) lstParametros.get(0);
			valorPorDefectoCodTipoDocumento = ps.getValorParametro();
		}

		if (valorPorDefectoCodTipoDocumento != null) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("tipoDocumento", valorPorDefectoCodTipoDocumento);
			f.setLongitudNumeroDocIdentidad(service.getLongitudTipoDocumento(criteria));
		}
		// +++ FIN PER-SiCC-2013-0882 +++

		GestionDocumento gestion = (GestionDocumento) this
				.getRegistroSeleccionado();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoTipo", gestion.getDocumento());
		criteria.put("numDocumento", gestion.getNumeroDocumento());
		criteria.put("numLote", gestion.getLote());
		String codParam = "STO_PARAM_VALID";
		criteria.put("codigoParametro", codParam);

		Map criteriaMinimo = new HashMap();
		criteriaMinimo.put("codigoPais", pais.getCodigo());
		criteriaMinimo.put("codigoParametro", "STO_VAL_MON_MINI");
		String montoMinimo = procesoSTOEjecucionValidacionesService
				.getParametroSTO(criteriaMinimo);

		Map criteriaMaximo = new HashMap();
		criteriaMaximo.put("codigoPais", pais.getCodigo());
		criteriaMaximo.put("codigoParametro", "STO_VAL_MON_MAXI");
		String montoMaximo = procesoSTOEjecucionValidacionesService
				.getParametroSTO(criteriaMaximo);

		// INI JR PER-SiCC-2012-0444
		f.setMuestraFlexipago(Constants.NUMERO_CERO);
		// FIN JR PER-SiCC-2012-0444

		// INI SA PER-SiCC-2013-0399
		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPais", pais.getCodigo());		
		criteriaAux.put("codigoSistema", "PED");
		criteriaAux.put("nombreParametro", "indRestActualizacionPROL");

		MantenimientoSTOBloqueoControlService mantenimientoSTOBloqueoControlService = (MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService");
		String indActualizacionPROL = mantenimientoSTOBloqueoControlService
				.getParametroGenericoSistema(criteriaAux);

		if ("S".equals(indActualizacionPROL))
			f.setIndicadorPROL(gestion.getIndicadorPROL().equals("NO") ? true
					: false);
		else
			f.setIndicadorPROL(true);
		// FIN SA PER-SiCC-2013-0399

		List ListaOrdenCompra = procesoSTOEjecucionValidacionesService
				.getOrdenCompraCabecera(criteria);
		OrdenCompraCabecera ordenCompraCabecera = (OrdenCompraCabecera) ListaOrdenCompra
				.get(0);
		setOrdenCompraCabecera(f, ordenCompraCabecera);
		f.setDetalle(gestion.getValidacion() + " - "
				+ gestion.getDesValidacion() + " - "
				+ gestion.getDesValidacionLarga());

		criteriaOperacion.put("codigoUsuario", usuario.getLogin());
		criteriaOperacion.put("codigoAccion", Constants.STO_PANTALLA_EDITABLE);
		criteriaOperacion.put("codigoTipo", gestion.getDocumento());

		this.editable = false;
		String origen = gestion.getDescripcionOrigen();
		criteria.put("codigoOnLine", "OL");
		String online = procesoSTOEjecucionValidacionesService
				.getDescripcionOnline(criteria);
		if (online.equals(origen)) {
			this.editable = false;
		} else {
			if (procesoSTOEjecucionValidacionesService
					.getAccionEditable(criteriaOperacion) == null) {
				this.editable = false;
			} else {
				this.editable = true;
			}
		}

		Map criteriaDetalle = new HashMap();
		criteriaDetalle.put("codigoPais", ordenCompraCabecera.getCodPais());
		criteriaDetalle.put("codPeriodo", ordenCompraCabecera.getCodPeriodo());
		criteriaDetalle.put("codCliente", ordenCompraCabecera.getCodCliente());
		criteriaDetalle.put("numLote", ordenCompraCabecera.getNumLote());
		criteriaDetalle.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCD);

		f.setCodClienteAux(ordenCompraCabecera.getCodCliente());

		criteriaMinimo.put("numLote", ordenCompraCabecera.getNumLote());
		criteriaMinimo.put("numSecuencia",
				ordenCompraCabecera.getNumSecuencia());
		criteriaMinimo.put("valMinimo", montoMinimo);
		criteriaMinimo.put("valMaximo", montoMaximo);

		String parametroSTO = procesoSTOEjecucionValidacionesService
				.getParametroSTO(criteria);
		if (parametroSTO.compareToIgnoreCase("0") == 0) {
			f.setNuevo(true);
		} else {
			if (parametroSTO.compareToIgnoreCase("1") == 0) {
				List excepcionesList = procesoSTOEjecucionValidacionesService
						.getListExcepciones(criteriaMinimo);
				if (excepcionesList.size() > 0) {
					f.setNuevo(true);
				} else
					f.setNuevo(false);
			} else {
				f.setNuevo(false);
			}
		}

		// ---------------------------------------------------------
		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		Map map = new HashMap();
		map.put("periodo", ordenCompraCabecera.getCodPeriodo());
		map.put("pais", pais.getCodigo());
		String stoLongitudUnidadesMaxima = serviceFact
				.getLongitudMaximoUnidades(map);
		this.longitudUnidadesMaxima = Integer
				.parseInt(stoLongitudUnidadesMaxima);

		f.setUnidadesMaximas(serviceFact.getMaximoUnidades(map));
		this.stoDetallesOccList = procesoSTOEjecucionValidacionesService
				.getDetallesOCC(criteriaDetalle);
		this.stoDetallesOccListTableModel = new DataTableModel(this.stoDetallesOccList);

		Map criteriaValidaciones = new HashMap();
		criteriaValidaciones.put("indicadorExistencia",
				ordenCompraCabecera.getIndValiExisCronograma());
		criteriaValidaciones.put("indicadorVencimiento",
				ordenCompraCabecera.getIndValiVencCronograma());
		criteriaValidaciones
				.put("codigoPais", ordenCompraCabecera.getCodPais());
		criteriaValidaciones.put("oidPeriodo",
				ordenCompraCabecera.getOidPeriodo());
		criteriaValidaciones.put("codPeriodo",
				ordenCompraCabecera.getCodPeriodo());
		criteriaValidaciones.put("oidZona", ordenCompraCabecera.getOidZona());
		criteriaValidaciones.put("oidPais", oidPais);
		this.stoExistenciaCronogramaOccList = new ArrayList();
		this.stoVencimientoCronogramaOccList = new ArrayList();
		this.stoVencimientoCronogramaSegundoCasoOccList = new ArrayList();
		this.stoVencimientoCronogramaTercerCasoOccList = new ArrayList();

		List ListaExistencia = procesoSTOEjecucionValidacionesService
				.getListaValidacionExistenciaCronograma(criteriaValidaciones);

		if (ordenCompraCabecera.getIndValiVencCronograma() != null) {
			if (ordenCompraCabecera.getIndValiVencCronograma()
					.compareToIgnoreCase("1") == 0) {
				criteriaValidaciones.put("indVenciPrimerCaso", "1");
				List ListaVencimiento = procesoSTOEjecucionValidacionesService
						.getListaValidacionVencimientoCronograma(criteriaValidaciones);
				this.stoVencimientoCronogramaOccList = ListaVencimiento;
				this.stoVencimientoCronogramaTableModel = new DataTableModel(this.stoVencimientoCronogramaOccList);
			}
			if (ordenCompraCabecera.getIndValiVencCronograma()
					.compareToIgnoreCase("2") == 0) {
				criteriaValidaciones.put("indVenciPrimerCaso", "1");
				List ListaVencimiento = procesoSTOEjecucionValidacionesService
						.getListaValidacionVencimientoCronograma(criteriaValidaciones);
				this.stoVencimientoCronogramaOccList = ListaVencimiento;
				this.stoVencimientoCronogramaTableModel = new DataTableModel(
						this.stoVencimientoCronogramaOccList);
				criteriaValidaciones.put("indVenciSegundoCaso", "2");
				List ListaVencimientoSegundoCaso = procesoSTOEjecucionValidacionesService
						.getListaValidacionVencimientoCronogramaSegundoCaso(criteriaValidaciones);
				this.stoVencimientoCronogramaSegundoCasoOccList = ListaVencimientoSegundoCaso;
				this.stoVencimientoCronogramaSegundoCasoTableModel = new DataTableModel(
						this.stoVencimientoCronogramaSegundoCasoOccList);
			}
			if (ordenCompraCabecera.getIndValiVencCronograma()
					.compareToIgnoreCase("3") == 0) {
				criteriaValidaciones.put("indVenciPrimerCaso", "1");
				List ListaVencimiento = procesoSTOEjecucionValidacionesService
						.getListaValidacionVencimientoCronograma(criteriaValidaciones);
				this.stoVencimientoCronogramaOccList = ListaVencimiento;
				this.stoVencimientoCronogramaTableModel = new DataTableModel(
						this.stoVencimientoCronogramaOccList);
				criteriaValidaciones.put("indVenciSegundoCaso", "2");
				List ListaVencimientoSegundoCaso = procesoSTOEjecucionValidacionesService
						.getListaValidacionVencimientoCronogramaSegundoCaso(criteriaValidaciones);
				this.stoVencimientoCronogramaSegundoCasoOccList = ListaVencimientoSegundoCaso;
				this.stoVencimientoCronogramaSegundoCasoTableModel = new DataTableModel(
						this.stoVencimientoCronogramaSegundoCasoOccList);
				criteriaValidaciones.put("indVenciTercerCaso", "3");
				List ListaVencimientoTercerCaso = procesoSTOEjecucionValidacionesService
						.getListaValidacionVencimientoCronogramaTercerCaso(criteriaValidaciones);
				this.stoVencimientoCronogramaTercerCasoOccList = ListaVencimientoTercerCaso;
				this.stoVencimientoCronogramaTercerCasoTableModel = new DataTableModel(
						this.stoVencimientoCronogramaTercerCasoOccList);
			}
		}

		this.stoExistenciaCronogramaOccList = ListaExistencia;
		this.stoExistenciaCronogramaTableModel = new DataTableModel(this.stoExistenciaCronogramaOccList);

		// Seteo la longitud del codigo de cliente segun el pais
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.longitudCampoClientes = clienteService
				.getTamanhoNumeroCliente(pais.getCodigo());
		// Obtengo el indicador de modificacion de codigo de consultora
		ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		criteria.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		criteria.put("codigoPais", ordenCompraCabecera.getCodPais());
		criteria.put("codigoValidacion", gestion.getValidacion());
		f.setIndicadorModificarConsultora(procesoSTOService
				.getIndicadorModificacionCodigoCliente(criteria));
		// Almaceno el codigo de cliente inicialmente en la BD
		f.setCodClienteBD(f.getCodCliente());
		f.setIndViewMotiGest(gestion.getIndViewMotiGest());

		// inicio @ghuertasa
		Map criteriaMostrarObservacion = new HashMap();
		// criteriaMostrarObservacion.put("validacion", f.getValidacion());

		// mostrar si es "S", no en caso contrario.
		if (f.getIndViewMotiGest() != null) {

			if (f.getIndViewMotiGest().equals(Constants.SI)) {
				f.setMostrarMotivoObservacion(true);
				// obtener la lista para el llenado del combo.
				List lista = procesoSTOEjecucionValidacionesService
						.getMotivosGestionDocumento(criteria);
				this.stoMotivosGestion = lista;
			} else {
				f.setMostrarMotivoObservacion(false);
			}
		}
		
		//Mostrar cuadro de ofertas-OCC-16
		ParametroPais paraOfertas = new ParametroPais();
		paraOfertas.setCodigoPais(pais.getCodigo());
		paraOfertas.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		paraOfertas.setNombreParametro(Constants.STO_ACTIVAR_CUADRO_OFERTA_OCC);

		List listaOfertas = genericoService.getParametrosPais(paraOfertas);
		if(listaOfertas != null && listaOfertas.size() > 0) {	
			ParametroPais ofertas=(ParametroPais)listaOfertas.get(0);
			if(StringUtils.equals(ofertas.getValorParametro(), "1"))
				this.showCuadroOferta=true;
			else
				this.showCuadroOferta=false;
		}
				
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(this.mPantallaPrincipalBean.getCountryCode());
		parametroPais1.setCodigoSistema(Constants.STO_CODIGO_SISTEMA);
		parametroPais1.setNombreParametro(Constants.STO_ACTIVAR_BUSCAR_GRABAR_OCC);
		
		List lstParametros1 = genericoService.getParametrosPais(parametroPais1);
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros1.get(0);
			if (StringUtils.equals(Constants.SI, ps.getValorParametro()))
				activarBuscarGrabar = Constants.SI;
		}
		
		if(StringUtils.equals(f.getValidacion(), "OCC-16"))
			this.showValidaOCC16=true;
		else
			this.showValidaOCC16=false;
		
		//Valida Data Credticia
		MantenimientoRECDigitacionCDRService serviceREC = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		Map criteria1 = new HashMap();
		criteria1.put("codigoPais", pais.getCodigo());
		criteria1.put("codigoParametro", Constants.STO_VER_INFO_COME);
		String valor = serviceREC.getSTOParametroGeneralOCR(criteria1);		
		if(StringUtils.equals(valor, Constants.NUMERO_UNO)){
			f.setMostrarValidaDataCredi(true);
			List resulData=procesoSTOEjecucionValidacionesService.getDataCrediticiaInfoComercial(f.getCodCliente());
			String resultado="";
			String estado="";
			if(!resulData.isEmpty()){
				resultado=((Map)resulData.get(0)).get("resultado").toString();
				estado=((Map)resulData.get(0)).get("estado").toString();
			}
			f.setResultadoDataCredi(resultado);
			f.setEstadoDatacredi(estado);
		}else
			f.setMostrarValidaDataCredi(false);			
		
	}

	private void setOrdenCompraCabecera(MantenimientoSTOOrdenCompraCabeceraForm f,
			OrdenCompraCabecera ordenCompraCabecera) throws Exception {
		f.setOidMotiGes(ordenCompraCabecera.getOidMotiGes());
		f.setValObseGestion(ordenCompraCabecera.getValObseGestion());
		f.setCodigoPais(ordenCompraCabecera.getCodPais());
		f.setCodAcceso(ordenCompraCabecera.getCodAcceso());
		f.setCodCliente(ordenCompraCabecera.getCodCliente());
		f.setCodMotivoRechazo(ordenCompraCabecera.getCodMotivoRechazo());
		f.setCodPeriodo(ordenCompraCabecera.getCodPeriodo());
		f.setCodRegion(ordenCompraCabecera.getCodRegion());
		f.setCodSubAcceso(ordenCompraCabecera.getCodSubAcceso());
		f.setNumSecuencia(ordenCompraCabecera.getNumSecuencia());
		f.setCodZona(ordenCompraCabecera.getCodZona());
		f.setEstadoProceso(ordenCompraCabecera.getEstadoProceso());
		f.setFechaSolicitud(new SimpleDateFormat("dd/MM/yyyy")
				.format(ordenCompraCabecera.getFechaSolicitud()));
		f.setFechaSolicitudDate(DateUtil.convertStringToDate(f
				.getFechaSolicitud()));
		f.setNumCliente(ordenCompraCabecera.getNumCliente());
		f.setNumDocumento(ordenCompraCabecera.getNumDocumento());
		f.setNumLote(ordenCompraCabecera.getNumLote());
		f.setTipoDespacho(ordenCompraCabecera.getTipoDespacho());
		f.setTipoSolicitud(ordenCompraCabecera.getTipoSolicitud());
		f.setMontoPedido(ordenCompraCabecera.getMontoPedido());
		f.setOidZona(ordenCompraCabecera.getOidZona());
		f.setCodigoZonaArribo(ordenCompraCabecera.getCodigoZonaArribo());
		f.setCodigoVenta("");
		f.setUnidades(null);

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", ordenCompraCabecera.getCodPais());
		criteria.put("numLote", ordenCompraCabecera.getNumLote());
		criteria.put("codPeriodo", ordenCompraCabecera.getCodPeriodo());
		criteria.put("codCliente", ordenCompraCabecera.getCodCliente());

		String valMontoMinimo = procesoSTOEjecucionValidacionesService
				.getIndicadorMontoMinimo(criteria);
		String valMontoMaximo = procesoSTOEjecucionValidacionesService
				.getIndicadorMontoMaximo(criteria);

		if (valMontoMinimo == null)
			valMontoMinimo = "0";

		if (valMontoMaximo == null)
			valMontoMaximo = "0";
		
		
		if (StringUtils.equals(valMontoMinimo, "1"))
			f.setMontoMinimo("S");
		else
			f.setMontoMinimo("N");

		if (StringUtils.equals(valMontoMaximo, "1"))
			f.setMontoMaximo("S");
		else
			f.setMontoMaximo("N");
		
		if (StringUtils.equals(f.getMontoMinimo(), "S"))
			this.bMontoMin = true;
		else
			this.bMontoMin = false;
		
		if (StringUtils.equals(f.getMontoMaximo(), "S"))
			this.bMontoMax = true;
		else
			this.bMontoMax = false;

		/* INI JR PER-SiCC-2012-0444 */
		if (ordenCompraCabecera.getIndUtilizaFlex() != null
				&& ordenCompraCabecera.getMontoFlexipago() != null
				&& ordenCompraCabecera.getIndAceptaFlex() != null) {
			f.setMuestraFlexipago(Constants.NUMERO_UNO);
		}

		if (ordenCompraCabecera.getIndUtilizaFlex() != null) {
			f.setMontoFlexipago(ordenCompraCabecera.getMontoFlexipago());

			String indicadorUtilizaFlex = ordenCompraCabecera
					.getIndUtilizaFlex();
			String mensaje = "";

			if (indicadorUtilizaFlex.equals(Constants.NUMERO_UNO))
				mensaje = "Mįs stock";
			else {
				if (indicadorUtilizaFlex.equals(Constants.NUMERO_DOS))
					mensaje = "Llegar a un mayor dsto";
				else {
					if (indicadorUtilizaFlex.equals(Constants.NUMERO_TRES))
						mensaje = "Aprovechar ofertas";
					else {
						if (indicadorUtilizaFlex
								.equals(Constants.NUMERO_CUATRO))
							mensaje = "Uso personal";
						else {
							if (indicadorUtilizaFlex
									.equals(Constants.NUMERO_CINCO))
								mensaje = "Mįs plazo a clientes";
							else
								mensaje = "Llegar al premio";
						}
					}
				}
			}

			f.setUtilizaFlexipago(mensaje);

			if (ordenCompraCabecera.getIndAceptaFlex() != null) {
				if (ordenCompraCabecera.getIndAceptaFlex().equals("S")) {
					f.setAceptaFlexipago(Constants.STO_TIPO_APROBADO_SI);
				} else {
					f.setAceptaFlexipago(Constants.STO_TIPO_APROBADO_NO);
				}
			}
		}
		/* FIN JR PER-SiCC-2012-0444 */
	}

	// parte de la Grilla Registro
	public void insertRegistro(ActionEvent actionEvent) {
		try {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			LabelPedidosValue[] listaDetalles = null;

			// -- Validar Lista detalle
			listaDetalles = ajaxService.getDescPrecio(f.getCodigoPais(),
					f.getCodPeriodo(), f.getCodCliente(), f.getNumLote(),
					f.getCodigoVenta());

			LabelPedidosValue aux = new LabelPedidosValue();
			String numSecuencia = procesoSTOEjecucionValidacionesService
					.getSecuenciaSTONextValue();
			String user = usuario.getLogin();
			String tipo_pos = "OC";

			// -- Insertar
			if (listaDetalles.length > 0) {

				aux = listaDetalles[0];

				// -- Crear pojo
				Map criteria = new HashMap();
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("codigoPeriodo", f.getCodPeriodo());
				criteria.put("codigoCliente", f.getCodCliente());
				criteria.put("numLote", f.getNumLote());
				criteria.put("codigoVenta", f.getCodigoVenta());
				criteria.put("desProducto", aux.getDescripcion());
				criteria.put("numUnidades", f.getUnidades());
				criteria.put("precioCatalogo",
						Float.valueOf(aux.getPrecioCat()));// val_prec_cata_unid_loca
				criteria.put("tipoPosicion", tipo_pos);
				criteria.put("numSecuencia", Integer.valueOf(numSecuencia));
				criteria.put("codRegion", f.getCodRegion());
				criteria.put("codZona", f.getCodZona());
				criteria.put("numSecuenciaCabecera", f.getNumSecuencia());
				criteria.put("usuario", user);

				// -- Inserción (insercionDetalles -
				// insercionDetallesSTODocumDigit)
				procesoSTOEjecucionValidacionesService
						.insercionDetalles(criteria);
				// procesoSTOEjecucionValidacionesService.insercionDetallesSTODocumDigit(criteria);

			}

			// -- Actualizar cabecera desactiva
			Map criteriaBusqueda = new HashMap();
			criteriaBusqueda.put("codigoPais", f.getCodigoPais());
			criteriaBusqueda.put("codPeriodo", f.getCodPeriodo());
			criteriaBusqueda.put("codCliente", f.getCodCliente());
			criteriaBusqueda.put("numLote", f.getNumLote());
			procesoSTOEjecucionValidacionesService
					.updateCabeceraDesactiva(criteriaBusqueda);

			// -- Peticiones
			this.newRecord = false;
			f.setNuevo(true);
			this.stoDetallesOccList = procesoSTOEjecucionValidacionesService.getDetallesOCC(criteriaBusqueda);
			this.stoDetallesOccListTableModel = new DataTableModel(this.stoDetallesOccList);
			f.setCodigoVenta("");
			f.setUnidades(null);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void deleteRegistro(ActionEvent actionEvent) {
		try {

			MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
			String numero = new String();
			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			Map criteriaBusqueda = new HashMap();
			
			for(int i=0;i<this.stoDetalleSeleccionado.length;i++){
				OrdenCompraDetalle detalle = (OrdenCompraDetalle) this.stoDetalleSeleccionado[i];
				criteriaBusqueda.put("codigoPais", f.getCodigoPais());
				criteriaBusqueda.put("codPeriodo", f.getCodPeriodo());
				criteriaBusqueda.put("codCliente", f.getCodCliente());
				criteriaBusqueda.put("numLote", f.getNumLote());
				criteriaBusqueda.put("numSecuencia", detalle.getNumSecuencia());
				criteriaBusqueda.put("codigoVenta", detalle.getCodVenta());
				procesoSTOEjecucionValidacionesService.deleteDetalle(
						"deleteDocumExecepciones", criteriaBusqueda);
				procesoSTOEjecucionValidacionesService.deleteDetalle(
						"deleteDocumDigitados", criteriaBusqueda);
				procesoSTOEjecucionValidacionesService.deleteDetalle(
						"deleteConsolidado", criteriaBusqueda);
			}		

			procesoSTOEjecucionValidacionesService.updateCabeceraDesactiva(criteriaBusqueda);
			this.newRecord = false;
			f.setNuevo(true);
			this.stoDetallesOccList = procesoSTOEjecucionValidacionesService.getDetallesOCC(criteriaBusqueda);
			this.stoDetallesOccListTableModel = new DataTableModel(this.stoDetallesOccList);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void modificarRegistro(ActionEvent actionEvent) {
		try {
			MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
			OrdenCompraDetalle detalle = (OrdenCompraDetalle) this.stoDetalleSeleccionado[0];
			this.newRecord = true;
			f.setNuevo(true);
			f.setCodigoVenta(detalle.getCodVenta());
			f.setUnidades(detalle.getValUniDemandadas());
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void guardarRegistro(ActionEvent actionEvent) {
		try {
			// -- Variables
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			String user = usuario.getLogin();
			MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm) this.formBusqueda;
			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

			// -- Crear pojo
			Map criteriaModifica = new HashMap();
			criteriaModifica.put("codigoPais", f.getCodigoPais());
			criteriaModifica.put("codPeriodo", f.getCodPeriodo());
			criteriaModifica.put("codCliente", f.getCodCliente());
			criteriaModifica.put("numLote", f.getNumLote());
			criteriaModifica.put("codigoVenta", f.getCodigoVenta());
			criteriaModifica.put("unidades", f.getUnidades());
			criteriaModifica.put("usuario", user);

			// -- Actualización (updateDetalle - updateCabeceraDesactiva)
			procesoSTOEjecucionValidacionesService
					.updateDetalle(criteriaModifica);
			// procesoSTOEjecucionValidacionesService.updateCabeceraDesactiva(criteriaModifica);

			// -- Peticiones
			f.setCodigoVenta("");
			f.setUnidades(null);
			this.newRecord = false;
			f.setNuevo(true);
			this.stoDetallesOccList = procesoSTOEjecucionValidacionesService
					.getDetallesOCC(criteriaModifica);
			this.stoDetallesOccListTableModel = new DataTableModel(
					this.stoDetallesOccList);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	// metodo que sale del popup
	public void salir(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	public void salirCuadreOferta(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina("mantenimientoSTOOrdenCompraCabeceraForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	// Abrir el popup de busqueda
	public void abrirPopup(ActionEvent event) {
		try {
			MantenimientoSTOOrdenCompraCabeceraForm f = (MantenimientoSTOOrdenCompraCabeceraForm)this.formBusqueda;
			ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm)  this.consultaHIPDatosClienteAction.getFormBusqueda();	
			searchForm.setCodigoClienteBuscar(f.getCodCliente());
			this.consultaHIPDatosClienteAction.setPaginaPadre("mantenimientoSTOOrdenCompraCabeceraForm");
			this.consultaHIPDatosClienteAction.find();
			this.redireccionarPagina("consultaHIPDatosClienteFormSTO");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			
		}
	}	
	
	//busqueda Cliente
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){			
			try {				
				this.mostrarPopupCliente = true;
				this.busquedaMAEClientePopup.limpiarFind();
				
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}	
		
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {			
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaMAEClientePopup.verificarRegistro(event);			
			if (this.busquedaMAEClientePopup.isSeleccionoRegistro()) {					
					Map busCliente=(Map)this.busquedaMAEClientePopup.getBeanRegistroSeleccionado();
					MantenimientoSTOOrdenCompraCabeceraForm f =(MantenimientoSTOOrdenCompraCabeceraForm)this.formBusqueda;					
					String codigo=busCliente.get("codigo").toString();
					f.setCodCliente(codigo);
					this.codigoPopup=codigo;
					String nombre1="";String nombre2="";
					String apellido1=""; String apellido2="";					
					if(busCliente.get("nombre1")!=null)
						nombre1=busCliente.get("nombre1").toString();
					if(busCliente.get("nombre2")!=null)
						nombre2=busCliente.get("nombre2").toString();
					if(busCliente.get("apellido1")!=null)
						apellido1=busCliente.get("apellido1").toString();
					if(busCliente.get("apellido2")!=null)
						apellido2=busCliente.get("apellido2").toString();		
					
					this.nombreConsultora=nombre1+" "+nombre2+" "+ apellido1+" "+apellido2;
					
					this.busquedaMAEClientePopup.setBeanRegistroSeleccionado(null);					
					this.formBusqueda=f;
			}
		}	
	}
		
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;			
		this.busquedaMAEClientePopup.setBeanRegistroSeleccionado(null);
	}
	
	//metodo para abrir el ventana Emergente
	public void mostrarCuadroOfertas(ActionEvent event){
		try {
			MantenimientoSTOOrdenCompraCabeceraForm f =(MantenimientoSTOOrdenCompraCabeceraForm)this.formBusqueda;	
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			this.cuadroOfertasList= new ArrayList();
			this.cuadroOfertasModel = new DataTableModel();
			Map criteria= new HashMap();
			criteria.put("nroDocumento", f.getNumSecuencia());
			
			this.cuadroOfertasList=service.getCuadroOfertaOCC(criteria);
			this.cuadroOfertasModel = new DataTableModel(this.cuadroOfertasList);
			String ventanaPopup = "mantenimientoSTOOCCCuadroOfertas.xhtml";
			this.getRequestContext().addCallbackParam("ventanaPopup", ventanaPopup);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public List getStoDetallesOccList() {
		return stoDetallesOccList;
	}

	public void setStoDetallesOccList(List stoDetallesOccList) {
		this.stoDetallesOccList = stoDetallesOccList;
	}

	public List getStoVencimientoCronogramaOccList() {
		return stoVencimientoCronogramaOccList;
	}

	public void setStoVencimientoCronogramaOccList(
			List stoVencimientoCronogramaOccList) {
		this.stoVencimientoCronogramaOccList = stoVencimientoCronogramaOccList;
	}

	public List getStoVencimientoCronogramaSegundoCasoOccList() {
		return stoVencimientoCronogramaSegundoCasoOccList;
	}

	public void setStoVencimientoCronogramaSegundoCasoOccList(
			List stoVencimientoCronogramaSegundoCasoOccList) {
		this.stoVencimientoCronogramaSegundoCasoOccList = stoVencimientoCronogramaSegundoCasoOccList;
	}

	public List getStoVencimientoCronogramaTercerCasoOccList() {
		return stoVencimientoCronogramaTercerCasoOccList;
	}

	public void setStoVencimientoCronogramaTercerCasoOccList(
			List stoVencimientoCronogramaTercerCasoOccList) {
		this.stoVencimientoCronogramaTercerCasoOccList = stoVencimientoCronogramaTercerCasoOccList;
	}

	public List getStoExistenciaCronogramaOccList() {
		return stoExistenciaCronogramaOccList;
	}

	public void setStoExistenciaCronogramaOccList(
			List stoExistenciaCronogramaOccList) {
		this.stoExistenciaCronogramaOccList = stoExistenciaCronogramaOccList;
	}

	public List getStoMotivosGestion() {
		return stoMotivosGestion;
	}

	public void setStoMotivosGestion(List stoMotivosGestion) {
		this.stoMotivosGestion = stoMotivosGestion;
	}

	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public Integer getLongitudUnidadesMaxima() {
		return longitudUnidadesMaxima;
	}

	public void setLongitudUnidadesMaxima(Integer longitudUnidadesMaxima) {
		this.longitudUnidadesMaxima = longitudUnidadesMaxima;
	}

	public DataTableModel getStoDetallesOccListTableModel() {
		return stoDetallesOccListTableModel;
	}

	public void setStoDetallesOccListTableModel(
			DataTableModel stoDetallesOccListTableModel) {
		this.stoDetallesOccListTableModel = stoDetallesOccListTableModel;
	}

	public DataTableModel getStoExistenciaCronogramaTableModel() {
		return stoExistenciaCronogramaTableModel;
	}

	public void setStoExistenciaCronogramaTableModel(
			DataTableModel stoExistenciaCronogramaTableModel) {
		this.stoExistenciaCronogramaTableModel = stoExistenciaCronogramaTableModel;
	}

	public DataTableModel getStoVencimientoCronogramaTableModel() {
		return stoVencimientoCronogramaTableModel;
	}

	public void setStoVencimientoCronogramaTableModel(
			DataTableModel stoVencimientoCronogramaTableModel) {
		this.stoVencimientoCronogramaTableModel = stoVencimientoCronogramaTableModel;
	}

	public DataTableModel getStoVencimientoCronogramaSegundoCasoTableModel() {
		return stoVencimientoCronogramaSegundoCasoTableModel;
	}

	public void setStoVencimientoCronogramaSegundoCasoTableModel(
			DataTableModel stoVencimientoCronogramaSegundoCasoTableModel) {
		this.stoVencimientoCronogramaSegundoCasoTableModel = stoVencimientoCronogramaSegundoCasoTableModel;
	}

	public DataTableModel getStoVencimientoCronogramaTercerCasoTableModel() {
		return stoVencimientoCronogramaTercerCasoTableModel;
	}

	public void setStoVencimientoCronogramaTercerCasoTableModel(
			DataTableModel stoVencimientoCronogramaTercerCasoTableModel) {
		this.stoVencimientoCronogramaTercerCasoTableModel = stoVencimientoCronogramaTercerCasoTableModel;
	}

	public boolean isbMontoMin() {
		return bMontoMin;
	}

	public void setbMontoMin(boolean bMontoMin) {
		this.bMontoMin = bMontoMin;
	}

	public boolean isbMontoMax() {
		return bMontoMax;
	}

	public void setbMontoMax(boolean bMontoMax) {
		this.bMontoMax = bMontoMax;
	}
	
	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	/**
	 * @return the consultaHIPDatosClienteAction
	 */
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	/**
	 * @param consultaHIPDatosClienteAction the consultaHIPDatosClienteAction to set
	 */
	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaMAEClientePopupSearchAction getBusquedaMAEClientePopup() {
		return busquedaMAEClientePopup;
	}

	public void setBusquedaMAEClientePopup(
			BusquedaMAEClientePopupSearchAction busquedaMAEClientePopup) {
		this.busquedaMAEClientePopup = busquedaMAEClientePopup;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	public String getCodigoPopup() {
		return codigoPopup;
	}

	public void setCodigoPopup(String codigoPopup) {
		this.codigoPopup = codigoPopup;
	}

	public Object[] getStoDetalleSeleccionado() {
		return stoDetalleSeleccionado;
	}

	public void setStoDetalleSeleccionado(Object[] stoDetalleSeleccionado) {
		this.stoDetalleSeleccionado = stoDetalleSeleccionado;
	}

	/**
	 * @return the cuadroOfertasList
	 */
	public List getCuadroOfertasList() {
		return cuadroOfertasList;
	}

	/**
	 * @param cuadroOfertasList the cuadroOfertasList to set
	 */
	public void setCuadroOfertasList(List cuadroOfertasList) {
		this.cuadroOfertasList = cuadroOfertasList;
	}

	/**
	 * @return the cuadroOfertasModel
	 */
	public DataTableModel getCuadroOfertasModel() {
		return cuadroOfertasModel;
	}

	/**
	 * @param cuadroOfertasModel the cuadroOfertasModel to set
	 */
	public void setCuadroOfertasModel(DataTableModel cuadroOfertasModel) {
		this.cuadroOfertasModel = cuadroOfertasModel;
	}

	/**
	 * @return the activarBuscarGrabar
	 */
	public String getActivarBuscarGrabar() {
		return activarBuscarGrabar;
	}

	/**
	 * @param activarBuscarGrabar the activarBuscarGrabar to set
	 */
	public void setActivarBuscarGrabar(String activarBuscarGrabar) {
		this.activarBuscarGrabar = activarBuscarGrabar;
	}

	/**
	 * @return the showCuadroOferta
	 */
	public boolean isShowCuadroOferta() {
		return showCuadroOferta;
	}

	/**
	 * @param showCuadroOferta the showCuadroOferta to set
	 */
	public void setShowCuadroOferta(boolean showCuadroOferta) {
		this.showCuadroOferta = showCuadroOferta;
	}

	/**
	 * @return the showValidaOCC16
	 */
	public boolean isShowValidaOCC16() {
		return showValidaOCC16;
	}

	/**
	 * @param showValidaOCC16 the showValidaOCC16 to set
	 */
	public void setShowValidaOCC16(boolean showValidaOCC16) {
		this.showValidaOCC16 = showValidaOCC16;
	}
	
	
	

}
