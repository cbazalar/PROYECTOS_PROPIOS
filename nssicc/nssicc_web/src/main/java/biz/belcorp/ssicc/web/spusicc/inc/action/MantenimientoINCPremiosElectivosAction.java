package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spisicc.ProcesoIMPGeneracionDocumentosLaserService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCPremiosElectivosService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.action.ReporteLETConfiguracionProgramaAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCPremiosElectivosForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoINCPremiosElectivosAction extends
		BaseMantenimientoSearchAbstractAction {
	private static final long serialVersionUID = -3560908115377397331L;

	private List mantenimientoINCConcursoElectivosList;
	private List premiosElectivosList;
	private String maxLongCodVentaFicticio;
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	private boolean mostrarDatosConsultora;
	private String puntajeSaldo;
	private String notSave;
	private List premioList;
	private String arr;
	private DataTableModel datatablePremio;
	private Object[] premioSeleccionado;
	private boolean indConsultora;

	private String hdIndicadorElegido;
	private String hdNumUnidades;
	private String hdCostePuntos;
	private String hdIndicadorPendiente;
	private String hdTipoPremio;
	private String hdIndicadorPremioAcum;
	private String hdNumNivel;
	private String hdNumPremio;
	private String hdCodigoVentaFicticio;
	private String hdoidPremioArticulo;
	private String hdIndicadorAnulado;
	private String hdMenorCodVenta;
	private String hdIndicadorValidos;

	
	

	

	@ManagedProperty(value = "#{busquedaClientesPOPUPSearchAction}")
	private BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction;

	// flag utilizado para mostrar archivo Excel
	private Boolean mostrarDatosExcel;
	
	//Reporte Codigos Validos
	@ManagedProperty(value = "#{reporteINCPremiosElectivosValidosAction}")
	private ReporteINCPremiosElectivosValidosAction reporteValidos;
	
	//Reporte Codigos Invalidos
	@ManagedProperty(value = "#{reporteINCPremiosElectivosInvalidosAction}")
	private ReporteINCPremiosElectivosInvalidosAction reporteInvalidos;

	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.mostrarPopupCliente = true;
			this.mostrarProcesoBatch = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}

		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaClientesPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaClientesPOPUPSearchAction.isSeleccionoRegistro()) {
				MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;

				Map cliente = (Map) this.busquedaClientesPOPUPSearchAction
						.getBeanRegistroSeleccionado();

				f.setCodigoClienteBuscar(MapUtils.getString(cliente, "codigo"));

				this.mostrarPopupCliente = false;
				this.mostrarProcesoBatch = true;
				this.busquedaClientesPOPUPSearchAction
						.setBeanRegistroSeleccionado(null);
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		this.busquedaClientesPOPUPSearchAction
				.setBeanRegistroSeleccionado(null);
	}

	/**
	 * Valida la existencia del codigo de consultora
	 */
	public void loadDataInput() {
		try {
			MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
			String oidPais = f.getOidPais();
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			if (StringUtils.isNotBlank(f.getCodigoClienteBuscar())) {

				if (f.getCodigoClienteBuscar().length() > Integer.parseInt(f
						.getLongitudCodigoCliente())) {
					int tamanio = f.getCodigoClienteBuscar().length()
							- Integer.parseInt(f.getLongitudCodigoCliente());
					f.setCodigoClienteBuscar(f.getCodigoClienteBuscar()
							.substring(tamanio));
				}

				String result = ajax.getExisteCodigoCliente(oidPais,
						f.getCodigoClienteBuscar());

				if (StringUtils.isBlank(result)) {
					this.addWarn(
							"",
							this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.consultora.not.existe"));
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
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
		return "mantenimientoINCPremiosElectivosForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCPremiosElectivosForm form = new MantenimientoINCPremiosElectivosForm();
		return form;
	}

	

	public void limpiandoValores() {
		this.hdIndicadorElegido = "";
		this.hdNumUnidades = "";
		this.hdCostePuntos = "";
		this.hdIndicadorPendiente = "";
		this.hdTipoPremio = "";
		this.hdIndicadorPremioAcum = "";
		this.hdNumNivel = "";
		this.hdNumPremio = "";
		this.hdCodigoVentaFicticio = "";
		this.hdoidPremioArticulo = "";
		this.hdIndicadorAnulado = "";
		this.hdMenorCodVenta = "";
		this.hdIndicadorValidos = "";
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
	
	public void buscar(ActionEvent evt){
		try {
			
		
		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;

		MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");

		Map map = executeValidacionPremiosElectivos(f, service);
		// se setea los datos de la consultora si la validacion fue correcta
		setDatosConsultora(f, map);
		// se recupera la lista de permios ya consolidado
		List listPremiosElectivos = service.getPremiosElectivos();
		// session.removeAttribute(Constants.INC_CONCU_PREMIOS_ELECTIVOS_LIST);
		this.premiosElectivosList = listPremiosElectivos;
		// se manda flag para mostrar la data
		this.setMostrarDatosConsultora(true);
		f.setCodigoCliente(f.getCodigoClienteBuscar());
		f.setIndicadorBusqueda(true);
		this.premioList = new ArrayList();
		if (listPremiosElectivos.size() == 0)
			notSave = Constants.NUMERO_UNO;

		enviarListaExistente(listPremiosElectivos);
		if (premioList.size() == 0) {
			Map data = new HashMap();
			data.put("codigoVentaFicticio", "");
			data.put("numUnidades", "");
			data.put("index", "0");
			premioList.add(data);
		} else {
			Map data = new HashMap();
			data.put("codigoVentaFicticio", "");
			data.put("numUnidades", "");
			premioList.add(data);
			generarIndex();
		}

		this.datatablePremio = new DataTableModel(this.premioList);
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
//		  if (log.isDebugEnabled()) {
//	            log.debug("Entering 'saveDigitacion' method");
//	        }
//	        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
//			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
//	        MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
//	        MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
//	        
//	        MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm)this.formBusqueda;
//	        String [] indicadorValidos = new String[this.premioList.size() - 1];// Se cambio a Validos
//	        String [] codigoVentas = new String[this.premioList.size() - 1];
//	        String [] unidadesItems = new String[this.premioList.size() - 1];
//	        for (int i = 0; i < this.premioList.size() - 1; i++) {
//				Map listaMap = (Map) this.premioList.get(i);
//				String codigoVenta = listaMap.get("codigoVentaFicticio").toString();
//				String hdIndicadorValidos = listaMap.get("hdIndicadorValidos").toString();
//				String unidadesItemsValor = listaMap.get("unidadesItems").toString();
//				codigoVentas[i] = codigoVenta;
//				codigoVentas[i] = hdIndicadorValidos;
//				unidadesItems[i] = unidadesItemsValor;
//				
//			}
//    
//	        List listPremiosElectivos = this.premiosElectivosList; 
//	        List listPremiosElegidos = getListPremiosElegidos(unidadesItems,codigoVentas,listPremiosElectivos,indicadorValidos,f,usuario);
//	        log.debug("listaaaaaaa :"+listPremiosElegidos);
//	        //Se cambio a Validos
//	        if(listPremiosElegidos.size()>0)
//	         service.insertPremiosElectivos(listPremiosElegidos);
//	        else{
//	        	Map map=(Map)listPremiosElectivos.get(0);
//	        	service.deletePremiosElectivos(map);
//	        }
//	        
////			"mantenimientoINCPremiosElectivosForm.cabecera.save", f.getCodigoCliente()));
////	        f.reset(mapping, request);
//	        setParametrosIniciales(usuario, pais, clienteService, f);
////	        session.removeAttribute(Constants.INC_CONCU_PREMIOS_ELECTIVOS_LIST);
////	        session.removeAttribute(DATOS_CONSULTORA);
//	        return true;
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
		MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
		this.activarHotkeyEstandar = false;
		// se cargara la lista de parametros de concurso activos
		List listConcursosElectivos = service.getListParametrosConcursosElectivos();
		this.mantenimientoINCConcursoElectivosList = listConcursosElectivos;

		// Asignamos al codigo del periodo el valor por defecto
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		setParametrosIniciales(usuario, pais, clienteService, f);
		Base base=(Base)this.mantenimientoINCConcursoElectivosList.get(0);		
		f.setOidConcurso(base.getCodigo());
		
		this.mostrarBotonBuscar = false;
		// obtenemos longitud de codigo venta ficticia
		maxLongCodVentaFicticio = getLongitudCodVentaFicticio(pais.getCodigo());
		this.premioList = new ArrayList();
		arr = "";
		// session.removeAttribute("notSave");

		service.deleteCargaPremiosElectivos(null);
		// return mapping.findForward(getViewForward());

		this.setMostrarBotonConsultar(false);
		this.setMostrarBotonEliminar(false);
		this.setMostrarBotonModificar(false);
		this.setMostrarBotonNuevo(false);
		this.setMostrarListaBusqueda(false);
		this.setMostrarDatosConsultora(false);
		this.setMostrarDatosExcel(false);		
		this.indConsultora=false;
		this.mostrarBotonBuscar = false;
	}

	/**
	 * @param listPremiosElectivos
	 */
	public void enviarListaExistente(List listPremiosElectivos) {

		for (int i = 0; i < listPremiosElectivos.size(); i++) {
			Map data = (Map) listPremiosElectivos.get(i);
			arr = arr + data.get("codigoVentaFicticio").toString() + ";";
			addPremiosElegidos(data);
		}
	}

	/**
	 * @param data
	 */
	public void addPremiosElegidos(Map data) {

		String indicadorElegido = data.get("indicadorElegido").toString();
		String indicadorPendiente = data.get("indicadorPendiente").toString();
		// String indicadorValidos = data.get("indicadorValidos").toString();
		try {
			if (indicadorElegido.equals("1") && indicadorPendiente.equals("1")) {
				nuevaFila(data);
				// x=form.codigoVentaFicticioItems;
				// x4=form.unidadesItems;
				// unidadesAuxiliar=form.unidadesItems;
				// x1=form.selectedItems;
				// x[fila].value=hdCodigoVentaFicticio[iAux].value;
				// x4[fila].value= hdNumUnidades[iAux].value;
				// arrCodVenta.push(hdCodigoVentaFicticio[iAux].value);
				// arrCodVentaPopup.push(hdCodigoVentaFicticio[iAux].value);
				// ajax.getCodigoVentasFicticio(hdoidPremioArticulo[iAux].value,fila,
				// loadCodigoVentasFicticioCallback2);
				//
				// if(hdIndicadorPendiente[iAux].value=='0'){
				// x1[fila].disabled=true;
				// x[fila].disabled=true;
				// x4[fila].disabled=true;
				// }else{
				// if(tipoPremio=='2'){
				// x4[fila].readonly=true;
				// }else{
				// x1[fila].disabled=false;
				// x[fila].disabled=false;
				// x4[fila].disabled=false;
				// }
				// }
				// fila++;
			}

		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param data
	 */
	public void nuevaFila(Map data) {
		this.premioList.add(data);
	}

	/**
	 * @param index
	 * @throws Exception
	 */
	public void agregarFila(String index) {
		try {
			Map data = (Map) premioList.get(Integer.parseInt(index));
			String codVenta = data.get("codigoVentaFicticio").toString();
			if (arr.contains(codVenta)) {
				for (int i = 0; i < this.premiosElectivosList.size(); i++) {
					Map data2 = (Map) this.premiosElectivosList.get(i);
					if (data2.get("codigoVentaFicticio").toString()
							.equals(codVenta)) {
//						if(!actualizaPuntaje(data2, index)){
//							return;
//						}
						validaCodigoVentaMemoria(data2, index);
						// addPremiosElegidos(data2);
						break;
					}
				}
			} else {
				data.put("codigoVentaFicticio", "");
				this.addWarn(
						"Error",
						this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.codVtaNoExiste"));
				return;
			}
		} catch (Exception e) {
			
			this.addError("Error", obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param codigoVentaFicticio
	 * @return
	 */
	public Boolean existeCodVentaInGrilla(String codigoVentaFicticio) {
		try {
			int cont = 0;
			for (int i = 0; i < premioList.size(); i++) {
				Map codigoExistente = (Map) premioList.get(i);
				String valorCodigoExistente = codigoExistente.get(
						"codigoVentaFicticio").toString();
				if (StringUtils.equalsIgnoreCase(valorCodigoExistente,
						codigoVentaFicticio)) {
					cont++;
				}
				if (cont == 2) {
					return false;
				}

			}
			return true;

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		}
	}

	public void validaCodigoVentaMemoria(Map data, String index) {
		String tipoPremio = data.get("tipoPremio").toString();
		String indicadorPremioAcum = data.get("indicadorPremioAcum").toString();
		String seleccionPremio = indicadorPremioAcum;
		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
		BigDecimal hdCostePuntos = null;
		if (!(data.get("costoPuntos") == null)) {
			hdCostePuntos =  (BigDecimal) data.get("costoPuntos");
		}
		BigDecimal hdNumUnidades = null;
		if (!(data.get("numUnidades") == null)) {
			hdNumUnidades =  (BigDecimal) data.get("numUnidades");

		}
		
		Map dataList = (Map) premioList.get(Integer.parseInt(index));
		
		String codigoVentaFicticioItems = (String) dataList
				.get("codigoVentaFicticio");
		String valorComprometido = f.getPuntajeComprometido();
		String valorCanjeadoS = f.getPuntajeCanjeado();
		int valorCanjeado = Integer.parseInt(valorCanjeadoS);
		String valorObtenidoS = f.getPuntajeObtenido();
		int valorObtenido = Integer.parseInt(valorObtenidoS);

		String valorDisponibleS = f.getPuntajeDisponible();
		int valorDisponible = Integer.parseInt(valorDisponibleS);
		String indicadorNoValidaPuntaje = f.getIndicadorNoValidaPuntaje();

		// var unidadesItems = document.getElementsByName('unidadesItems');
		String hdoidPremioArticulo = (String) dataList
				.get("oidLotePremioArticulo");
		String hdNumNivel = null;
		if (!(dataList.get("numNivel") == null)) {
			hdNumNivel = (String) dataList.get("numNivel");
		}
		String hdIndicadorValidos = (String) data
				.get("indicadorValido");
		
		Integer valor = 0;
		String codventa;

		// validacion que consta en si se valida o no el puntaje
		// String indicadorNoValidaPuntaje = (String)
		// dataList.get("oidLotePremioArticulo");
		// document.getElementById("indicadorNoValidaPuntaje").value;

	
		
		
		int valor1 = hdCostePuntos.intValueExact() * 1;
		int valor2 = hdNumUnidades.intValueExact() * 1; 
		int resultado = valor2 * valor1;
		valor = valor + resultado;
		

		
		try {
			String mensaje = "";

			for (int i = 0; i < premioList.size(); i++) {
				Map datapremio = (Map) premioList.get(i);
				String codVenta = datapremio.get("codigoVentaFicticio")
						.toString();
				String hdIndicadorAnulado = null;
				if (!(datapremio.get("indicadorAnulado") == null)) {
					hdIndicadorAnulado = datapremio.get("indicadorAnulado")
							.toString();

				}
				String hdMenorCodVenta = null;
				if (!(datapremio.get("menorCodVenta") == null)) {
					hdMenorCodVenta = datapremio.get("menorCodVenta")
							.toString();

				}

//				String indicadorNoValidaPuntaje = f
//						.getIndicadorNoValidaPuntaje();

				if (codVenta.equals(dataList.get("codigoVentaFicticio"))
						&& i != Integer.parseInt(index)) {
					dataList.put("codigoVentaFicticio", "");
					throw new Exception(
							this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.codVtaExiste.grilla"));
				}

				// anhadimos funcionalidad
				// validar que no haya sido anulado(1), la anulacion es un
				// artificio que se esta usando cuando se dispone de mas
				// de un premio para un cod venta para mandar un mensaje que se
				// ingrese el de menor codigo
				if (StringUtils.equalsIgnoreCase(hdIndicadorAnulado,
						Constants.UNO)) {
					dataList.put("codigoVentaFicticio", "");
					mensaje = "El código de venta ingresado pertenece a un premio que tiene varios códigos de venta asociados.Por favor, utilizar el código de venta"
							+ hdMenorCodVenta + " en lugar del código digitado";
					throw new Exception(mensaje);
				}

				// validar que el codigo de venta no haya sido ingresado ya
				// si no existe actualziar puntajes
				// si existe manda mensaje de haver ya sido ingresado
				//
				if (!existeCodVentaInGrilla(codVenta)) {
					dataList.put("codigoVentaFicticio", "");
					throw new Exception(
							this.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.codVtaExiste.grilla"));
				}

			}

			if (StringUtils.equalsIgnoreCase(tipoPremio, Constants.DOS)) {
				if (indicadorPremioAcum.equals(Constants.NUMERO_CERO)) {
					 if (premioList.size() > 1) {   //(arrCodVenta.length==1){
						 this.addError("Error : ", this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.niveles.digitacion"));			 
						 return;
					 }else{
						 	int inicio = Integer.parseInt(index) + 1 ;
 						 	dataList.put("numUnidades", "1");
 						 	dataList.put("hdIndicadorValidos", hdIndicadorValidos);
 						 	dataList.put("puntajeDisponible", "0");
						 	Map filaNueva = new HashMap();
							filaNueva.put("codigoVentaFicticio", "");
							filaNueva.put("numUnidades", "");
							filaNueva.put("index", inicio);
							filaNueva.put("hdIndicadorValidos", hdIndicadorValidos);
							dataList.put("puntajeDisponible", "0");
							this.premioList.add(filaNueva);
							actualizaPuntajesFila(data,index,'1','1','1');
					 } 
					
//					throw new Exception(
//							this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.niveles.digitacion"));
				} else {
					dataList.put("codigoVentaFicticio", "");
					throw new Exception(
							this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.tipo.premio.unica.niveles.digitacion"));
				}
			}

			if (StringUtils.equalsIgnoreCase(f.getIndicadorNoValidaPuntaje(),
					Constants.NUMERO_CERO)) {
				if (StringUtils.equalsIgnoreCase(tipoPremio, Constants.DOS)
						&& StringUtils.equalsIgnoreCase(seleccionPremio,
								Constants.UNO)) {
					actualizaPuntajeMaximoNivel(data, index);
				}
			}
			if(valor*1 <= (valorObtenido*1 - valorCanjeado*1)){			
			    if(valorDisponible*1>=0){
			    	f.setPuntajeComprometido(valor.toString());
			    	Integer result =  valorObtenido*1 - valor*1 - valorCanjeado*1;
			    	f.setPuntajeDisponible(result.toString());
//				  document.getElementById('idpuntajeComprometido').innerHTML=valor*1;
//				  document.getElementById('idpuntajeDisponible').innerHTML= valorObtenido*1 - valor*1 - valorCanjeado*1;	
				}else{
					dataList.put("codigoVentaFicticio", "");
					this.addError("Error : ", this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
					return;			
				}
			}

		} catch (Exception e) {
			this.addError("Error", obtieneMensajeErrorException(e));
		}

	}

	private void actualizaPuntajesFila(Map data, String index,char c, char d, char e) {
		String tipoPremio = data.get("tipoPremio").toString();
		String indicadorPremioAcum = data.get("indicadorPremioAcum").toString();
		String seleccionPremio = indicadorPremioAcum;
		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
		BigDecimal hdCostePuntos = null;
		if (!(data.get("costoPuntos") == null)) {
			hdCostePuntos =  (BigDecimal) data.get("costoPuntos");
		}
		BigDecimal hdNumUnidades = null;
		if (!(data.get("numUnidades") == null)) {
			hdNumUnidades =  (BigDecimal) data.get("numUnidades");

		}
		
		Map dataList = (Map) premioList.get(Integer.parseInt(index));
		
		String codigoVentaFicticioItems = (String) dataList
				.get("codigoVentaFicticio");
		String valorComprometido = f.getPuntajeComprometido();
		String valorCanjeadoS = f.getPuntajeCanjeado();
		int valorCanjeado = Integer.parseInt(valorCanjeadoS);
		String valorObtenidoS = f.getPuntajeObtenido();
		int valorObtenido = Integer.parseInt(valorObtenidoS);

		String valorDisponibleS = f.getPuntajeDisponible();
		int valorDisponible = Integer.parseInt(valorDisponibleS);
		String indicadorNoValidaPuntaje = f.getIndicadorNoValidaPuntaje();

		// var unidadesItems = document.getElementsByName('unidadesItems');
		String hdoidPremioArticulo = (String) dataList
				.get("oidLotePremioArticulo");
		String hdNumNivel = null;
		if (!(dataList.get("numNivel") == null)) {
			hdNumNivel = (String) dataList.get("numNivel");
		}
		
		int valor = 0;
		int valor1 = hdCostePuntos.intValueExact() * 1;
		int valor2 = hdNumUnidades.intValueExact() * 1; 
		int resultado = valor2 * valor1;
		valor = valor + resultado;
		String codventa;
		
		int tempComprometido= valor*1;
		if(tempComprometido*1 <= valorDisponible*1){
			/*alert("tempComprometidoMenor:"+tempComprometido);
			alert("valorDisponibleMayor:"+valorDisponible);
			alert("tipoPremio:"+tipoPremio);
			alert("codventa:"+codventa);
			alert("valor1:"+valor1);
			alert("valor2:"+valor2);
			alert("valor:"+valor);*/
			if(valorDisponible*1>=0){
				//if(indValido!=1){
		 		//indValido='0';
		 		//}
			}else{
				dataList.put("puntajeDisponible", "1");
			 	 this.addWarn("Error : ", this.getResourceMessage("mantenimientoINCPremiosElectivosForm.not.existe.puntaje.disponible"));
			 	 return;
			}		
		}else{
			dataList.put("puntajeDisponible", "1");
			this.addWarn("Error : ", this.getResourceMessage("mantenimientoINCPremiosElectivosForm.not.existe.puntaje.disponible"));
		 	 return;	 
		}	
		
		
	}

	public Boolean actualizaPuntaje(Map data, String index) {
		try {
			
	
		
		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;

		Map dataList = (Map) premioList.get(Integer.parseInt(index));
		BigDecimal hdCostePuntos = null;
		if (!(data.get("costoPuntos") == null)) {
			hdCostePuntos =  (BigDecimal) data.get("costoPuntos");
		}
		BigDecimal hdNumUnidades = null;
		if (!(data.get("numUnidades") == null)) {
			hdNumUnidades =  (BigDecimal) data.get("numUnidades");

		}
		String codigoVentaFicticioItems = (String) dataList
				.get("codigoVentaFicticio");
		String valorComprometido = f.getPuntajeComprometido();
		String valorCanjeadoS = f.getPuntajeCanjeado();
		int valorCanjeado = Integer.parseInt(valorCanjeadoS);
		String valorObtenidoS = f.getPuntajeObtenido();
		int valorObtenido = Integer.parseInt(valorObtenidoS);

		String valorDisponibleS = f.getPuntajeDisponible();
		int valorDisponible = Integer.parseInt(valorDisponibleS);
		String indicadorNoValidaPuntaje = f.getIndicadorNoValidaPuntaje();

		// var unidadesItems = document.getElementsByName('unidadesItems');
		String hdoidPremioArticulo = (String) dataList
				.get("oidLotePremioArticulo");
		String hdNumNivel = null;
		if (!(dataList.get("numNivel") == null)) {
			hdNumNivel = (String) dataList.get("numNivel");
		}
		
		int valor = 0;
		String codventa;

		// validacion que consta en si se valida o no el puntaje
		// String indicadorNoValidaPuntaje = (String)
		// dataList.get("oidLotePremioArticulo");
		// document.getElementById("indicadorNoValidaPuntaje").value;

		if (StringUtils.equals(indicadorNoValidaPuntaje, Constants.UNO)) {
			return false;
		}
		
		
		int valor1 = hdCostePuntos.intValueExact() * 1;
		int valor2 = hdNumUnidades.intValueExact() * 1; 
		int resultado = valor2 * valor1;
		valor = valor + resultado;

		if (valor * 1 <= (valorObtenido * 1 - valorCanjeado * 1)) {

			if (valorDisponible * 1 >= 0) {
				
				Map filaNueva = new HashMap();
				filaNueva.put("codigoVentaFicticio", "");
				filaNueva.put("numUnidades", "");
				filaNueva.put("index", "1");
				this.premioList.add(filaNueva);
				
				
				// document.getElementById('idpuntajeComprometido').innerHTML=valor*1;
				// document.getElementById('idpuntajeDisponible').innerHTML=
				// valorObtenido*1 - valor*1 - valorCanjeado*1;

				// if (inserta == 1)
				this.addWarn("Aviso : ",this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
				return false;

				// }else{
				// //unidadesItems[index].value="";
				// // alert('<fmt:message
				// key="mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"/>');
				// return;
				// }
				// }else{
				// //alert('indicador');
				// if(indicador == 1){
				// this.addWarn("Aviso : ",
				// this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"));
				//
				// return;
				// }
				// //unidadesItems[index].value="";
				//
				// // alert('<fmt:message
				// key="mantenimientoINCPremiosElectivosSearchForm.not.existe.puntaje.disponible"/>');
				// return;
				// }
			}
		}
		} catch (Exception e) {
			this.addError("Error : " , this.obtieneMensajeErrorException(e));
		}
		return true;
	}

	public void actualizaPuntajeMaximoNivel(Map data, String index) {
		try {

			MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;

			Map dataList = (Map) premioList.get(Integer.parseInt(index));

			int hdCostePuntos = (Integer) dataList.get("costoPuntos");
			int unidadesItems = (Integer) dataList.get("numUnidades");
			String codigoVentaFicticioItems = (String) dataList
					.get("codigoVentaFicticio");
			String valorComprometido = f.getPuntajeComprometido();
			String valorCanjeado = f.getPuntajeCanjeado();
			String valorObtenido = f.getPuntajeObtenido();
			String valorDisponible = f.getPuntajeDisponible();
			String hdIndicadorValidos = (String) dataList
					.get("indicadorValido");

			int hdNumNivel = (Integer) dataList.get("numNivel");
			// int maxNivel = hdNumNivel[hdNumNivel.length - 1].value;
			// int maxCosto = hdCostePuntos[hdCostePuntos.length - 1].value;
			//
			// var valor=0;
			// var row;
			// var inserta=0;
			// var indice=0;

		} catch (Exception e) {
			
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 */
	public void generarIndex() {
		for (int i = 0; i < premioList.size(); i++) {
			Map index = (Map) premioList.get(i);
			index.put("index", "" + i + "");
		}
	}

	/**
	 * Setea los datos de la consultora
	 * 
	 * @param f
	 * @param map
	 */
	private void setDatosConsultora(MantenimientoINCPremiosElectivosForm f,
			Map map) {
		String resultado = (String) map.get("mensajeResultado");
		log.debug("mensaje Resultado " + resultado);
		String[] parametros = StringUtils.split(resultado, ",");

		f.setNombreConsultora(parametros[0]);
		f.setMeta(parametros[1]);// si es fase de calificacion (Activo : '1')
		// se muestra meta (>=0)en cualiquier otro caso ese valor viene en
		// blanco y no mostrar
		f.setPuntajeObtenido(parametros[2]);
		f.setPuntajeCanjeado(parametros[3]);
		f.setPuntajeSaldo(parametros[4]);
		puntajeSaldo = parametros[4];
		f.setPuntajeComprometido(parametros[5]);
		f.setPuntajeDisponible(parametros[6]);
		f.setNumeroPeriodos(parametros[7]);
		// ultimo parametro el indicadorde NO validacion de puntajes de premio
		f.setIndicadorNoValidaPuntaje(parametros[8]);// 0:valida 1:no valida
														// puntajes
	}

	/**
	 * Ejecuta Validaciones , si es conrrecta las validacion retorna los datos
	 * de la cabecera quese mostararn en patalla asi mismo consolida la lista de
	 * elegidos y seleccionables
	 * 
	 * @param f
	 * @param service
	 * @param session
	 * @return
	 * @throws Exception
	 */
	private Map executeValidacionPremiosElectivos(
			MantenimientoINCPremiosElectivosForm f,
			MantenimientoINCPremiosElectivosService service) throws Exception {
		Map map = new HashMap();
		map.put("codigoPais", f.getCodigoPais());
		map.put("oidConcurso", f.getOidConcurso());
		map.put("codigoConsultora", f.getCodigoClienteBuscar());
		map.put("tipo", Constants.NUMERO_UNO);// seleccion de premios
		// valores de salida
		map.put("mensajeError", null);
		map.put("mensajeResultado", null);// una cadena decaracteres de
		// nombreConsultora,meta,puntajeObtenido,puntajeCanjeado,
		// puntajeSaldo,puntajeComprometido,puntajeDisponible
		service.executeValidacionesPremiosElectivos(map);

		String mensajeError = (String) map.get("mensajeError");
		if (StringUtils.isNotEmpty(mensajeError)) {
			log.debug("error en validacion " + mensajeError);
			throw new Exception(mensajeError);
		}
		return map;
	}

	public void grabar(ActionEvent evt) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'grabar' method");
		}
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
			int valorPuntajeObtenido = Integer.parseInt(f.getPuntajeObtenido()) * 1;
			if (valorPuntajeObtenido == 0) {
				this.addWarn(
						"Error :  ",
						this.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.puntajeObtenido.cero"));
				return;
			}

			if (premioList.size() > 0) {
				String[] agregarUnidades = new String[premioList.size()-1];
				String[] agregarcodigoVentas = new String[premioList.size()-1];
				String[] agregarIndicadorValidos = new String[premioList.size()-1];
				HashMap objSeleccionado = null;
				for (int i = 0; i < premioList.size()-1; i++) {
					objSeleccionado = (HashMap) this.premioList.get(i);
					if(objSeleccionado.get("numUnidades")!=null){
						agregarUnidades[i] = objSeleccionado.get("numUnidades")
								.toString();
					}
					if(objSeleccionado.get("codigoVentaFicticio")!=null){
						agregarcodigoVentas[i] = objSeleccionado.get(
								"codigoVentaFicticio").toString();
					}
					if(objSeleccionado.get("hdIndicadorValidos")!=null){
						agregarIndicadorValidos[i] = objSeleccionado.get(
								"hdIndicadorValidos").toString();
					}
					
				}
				String[] unidadesItems = agregarUnidades;
				String[] codigoVentas = agregarcodigoVentas;
				String[] indicadorValidos = agregarIndicadorValidos;// Se cambio
																	// a
																	// Validos
				List listPremiosElectivos = this.premiosElectivosList;
				List listPremiosElegidos = getListPremiosElegidos(
						unidadesItems, codigoVentas, listPremiosElectivos,
						indicadorValidos, f, usuario);
				log.debug("listaaaaaaa :" + listPremiosElegidos);
				// Se cambio a Validos
				if (listPremiosElegidos.size() > 0)
					service.insertPremiosElectivos(listPremiosElegidos);
				else {
					Map map = (Map) listPremiosElectivos.get(0);
					service.deletePremiosElectivos(map);
				}
				Object[] objCodigoConsultora= new Object[1];
						objCodigoConsultora[0] = f.getCodigoClienteBuscar();
				this.addInfo(
						"Info : ",
						this.getResourceMessage("mantenimientoINCPremiosElectivosForm.cabecera.save",objCodigoConsultora));
				setParametrosIniciales(usuario, pais, clienteService, f);
			}
		} catch (Exception e) {
			this.addError("Error :", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Pone en session el oid del pais y la longitud de caracteres del cliente
	 * 
	 * @param usuario
	 * @param pais
	 * @param clienteService
	 * @param f
	 */
	private void setParametrosIniciales(Usuario usuario, Pais pais,
			MantenimientoMAEClienteService clienteService,
			MantenimientoINCPremiosElectivosForm f) {
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);
		criteria.put("oidPais", oidPais);
		// recuperamos la longitud del codigo de cliente para el pais logueado
		f.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
	}

	/**
	 * @param codigoPais
	 * @return
	 */
	private String getLongitudCodVentaFicticio(String codigoPais) {
		MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
		String longitud = service.getLongitudCodVentaFicticio(codigoPais);
		log.debug("longitud " + longitud);
		return longitud;
	}

	public void saveDigitacion(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'saveDigitacion' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) getBean("spusicc.mantenimientoINCPremiosElectivosService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;

		// String [] unidadesItems =f.getUnidadesItems();
		// String [] codigoVentas =
		// request.getParameterValues("codigoVentaFicticioItems");
		// String [] indicadorValidos =
		// request.getParameterValues("hdIndicadorValidos");// Se cambio a
		// Validos
		String[] unidadesItems = null;
		String[] codigoVentas = null;
		String[] indicadorValidos = null;
		List listPremiosElectivos = this.premiosElectivosList;
		List listPremiosElegidos = getListPremiosElegidos(unidadesItems,
				codigoVentas, listPremiosElectivos, indicadorValidos, f,
				usuario);

		log.debug("listaaaaaaa :" + listPremiosElegidos);

		// Se cambio a Validos
		if (listPremiosElegidos.size() > 0)
			service.insertPremiosElectivos(listPremiosElegidos);
		else {
			Map map = (Map) listPremiosElectivos.get(0);
			service.deletePremiosElectivos(map);
		}

		// ActionMessages messages = new ActionMessages();
		// messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
		// "mantenimientoINCPremiosElectivosForm.cabecera.save",
		// f.getCodigoCliente()));
		setParametrosIniciales(usuario, pais, clienteService, f);
		this.premiosElectivosList.clear();
	}

	/**
	 * Retorna la lista de premios digitados con num unidades distintas a cero o
	 * vacio
	 * 
	 * @param unidadesItems
	 * @param codigoVentas
	 * @param listPremiosElectivos
	 * @return
	 */
	private List getListPremiosElegidos(String[] unidadesItems,
			String[] codigoVentas, List listPremiosElectivos,
			String[] indicadorValidos, MantenimientoINCPremiosElectivosForm f,
			Usuario usuario) {
		List list = new ArrayList();

		Map map = (Map) listPremiosElectivos.get(0);
		int tipoPremio = Integer
				.parseInt(String.valueOf(map.get("tipoPremio")));
		log.debug("tipoPremio " + tipoPremio);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", f.getCodigoPais());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);

		switch (tipoPremio) {
		case 1:
			int numIndicadores = indicadorValidos.length - unidadesItems.length;
			log.debug("numero Indicador  xx " + numIndicadores);
			for (int i = 0; i < codigoVentas.length; i++) {
				// log.debug("i "+ i);
				log.debug("codigo Venta " + codigoVentas[i] + " unidades "
						+ unidadesItems[i] + "indicador"
						+ indicadorValidos[numIndicadores + i]);
				if (StringUtils.isNotEmpty(unidadesItems[i])) {
					int index = findCodVenta(listPremiosElectivos,
							codigoVentas[i]);
					if (index != -1) {

						map = (Map) listPremiosElectivos.get(index);
						map.put("numUnidades", unidadesItems[i]);
						map.put("indicadorValido",
								indicadorValidos[numIndicadores + i]);
						map.put("codigoCliente", f.getCodigoCliente());
						map.put("puntajeDisponible", puntajeSaldo);
						map.put("codigoUsuario", usuario.getLogin());
						map.put("codigoPeriodo",
								controlFacturacion.getCodigoPeriodo());
						// cambiamos su indicador pendiente
						map.put("indicadorPendiente", Constants.NUMERO_UNO);
						map.put("tipoRecepcion", "M");
						list.add(map);
					}
				}
			}
			break;
		case 2:
			numIndicadores = indicadorValidos.length
					- (codigoVentas.length);// ;
			log.debug("numero Indicador  rr " + numIndicadores);
			for (int i = 0; i < codigoVentas.length; i++) {
				// log.debug("i "+ i);
				log.debug("codigo Venta " + codigoVentas[i]
						+ " unidades default 1" + " indicador"
						+ indicadorValidos[numIndicadores + i]);

				int index = findCodVenta(listPremiosElectivos, codigoVentas[i]);
				if (index != -1) {
					map = (Map) listPremiosElectivos.get(index);
					map.put("numUnidades", Constants.NUMERO_UNO);
					map.put("indicadorValido", indicadorValidos[numIndicadores
							+ i]);
					map.put("codigoCliente", f.getCodigoCliente());
					map.put("puntajeDisponible", puntajeSaldo);
					map.put("codigoUsuario", usuario.getLogin());
					map.put("codigoPeriodo",
							controlFacturacion.getCodigoPeriodo());
					// cambiamos su indicador pendiente
					map.put("indicadorPendiente", Constants.NUMERO_UNO);
					map.put("tipoRecepcion", "M");
					list.add(map);
				}
			}
			break;
		}

		return list;
	}

	/**
	 * Retorna el indice donde se encuntra el codigo de venta en la lista estado
	 * pendiente, si no lo encuntra retorna -1
	 * 
	 * @param listPremiosElectivos
	 * @param string
	 * @return
	 */
	private int findCodVenta(List listPremiosElectivos, String codVenta) {
		Iterator it = listPremiosElectivos.iterator();
		int index = -1;
		int i = 0;
		while (it.hasNext()) {
			Map map = (Map) it.next();
			String auxCodVenta = String.valueOf(map.get("codigoVentaFicticio"));
			// String indicadorPendiente=
			// String.valueOf(map.get("indicadorPendiente"));
			// log.debug("indicador pendiente "+indicadorPendiente);
			if (codVenta.equals(auxCodVenta)) {
				index = i;
				break;
			}
			i++;
		}
		return index;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		String mensaje = "";
		MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (f.getCodigoClienteBuscar().length() > Integer.parseInt(f
				.getLongitudCodigoCliente())) {
			int tamanio = f.getCodigoClienteBuscar().length()
					- Integer.parseInt(f.getLongitudCodigoCliente());
			f.setCodigoClienteBuscar(f.getCodigoClienteBuscar().substring(
					tamanio));
		}

		f.setCodigoClienteBuscar(StringUtils.leftPad(
				f.getCodigoClienteBuscar(),
				Integer.parseInt(f.getLongitudCodigoCliente()), "0"));
		String resultado = ajax.getExisteCodigoCliente(f.getOidPais(),
				f.getCodigoClienteBuscar());

		if (StringUtils.isBlank(resultado)) {
			mensaje = this
					.getResourceMessage("mantenimientoINCPremiosElectivosSearchForm.consultora.not.existe");
			return mensaje;
		}

		return mensaje;
	}
	
	//Metodos para la carga de archivos
	public void handleFileUpload(FileUploadEvent event) {
		try {			        
			MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
			f.setArchivo(event.getFile());		
			f.setNombreArchivo(event.getFile().getFileName());			
			
			UploadedFile archivo =f.getArchivo();				
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));		
			
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), 
									f.getNombreArchivo()));
			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			f.setArchivo(null);
			this.uploadArchivo();		
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}		
		
	}
	
	public void uploadArchivo(){
		try {			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoINCPremiosElectivosForm f = (MantenimientoINCPremiosElectivosForm) this.formBusqueda;
			// obtenemos el servicio 
			ProcesoIMPGeneracionDocumentosLaserService serviceExcel = (ProcesoIMPGeneracionDocumentosLaserService)
													getBean("spisicc.procesoIMPGeneracionDocumentosLaserService");

			MantenimientoINCPremiosElectivosService service = (MantenimientoINCPremiosElectivosService) 
													getBean("spusicc.mantenimientoINCPremiosElectivosService");
			
			//recuperamos el numero de Concurso seleccionado por el usuario
			List listConcursosElectivos = this.mantenimientoINCConcursoElectivosList;			
			String numeroConcurso = "";
			for(int i=0; i<listConcursosElectivos.size(); i++){
				Base base = (Base)listConcursosElectivos.get(i);
				if(base.getCodigo().equalsIgnoreCase(f.getOidConcurso())) {
					int posicion = base.getDescripcion().indexOf("-");
					numeroConcurso = base.getDescripcion().substring(0, posicion - 1);
					break;
				}
			}
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
		
			Map criteria = new HashMap();
			criteria.put("directorioTemporal",f.getDirectorioTemporal());
			criteria.put("nombreArchivo",f.getNombreArchivo());
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoUsuario", usuario.getLogin());
			criteria.put("numeroConcurso", numeroConcurso);
			criteria.put("numeroCampos", new Integer(3));
			
			//validamos el archivo excel
			boolean isValido =serviceExcel.validarFormatoArchivoExcel(criteria);
			if(isValido){
				try{
					service.executeCargaPremiosElectivos(criteria);
				}catch(Exception e){				
					this.addError("Error:",	this.getResourceMessage("mantenimientoINCPremiosElectivosForm.cabecera.error",
							new Object[] {e.getMessage() }));
					borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());					
				}				
				this.addInfo("Info:", this.getResourceMessage("mantenimientoINCPremiosElectivosForm.proceso.ok"));
			}else			
				this.addError("Error:", this.getResourceMessage("mantenimientoINCPremiosElectivosForm.archivo.novalido"));			
			
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
		
	}	
	
	//Eliminar el fichero
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		}	
		catch(Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e));
		}
	}

	public void visualizarCargaFichero(ValueChangeEvent event) {		
		try {
			Boolean valor = (Boolean) event.getNewValue();
			if (valor) {
				this.mostrarDatosExcel = true;
				this.mostrarBotonBuscar = false;
				this.indConsultora=true;
				return;
			} else {
				this.mostrarDatosExcel = false;
				this.mostrarBotonBuscar = true;
				this.indConsultora=false;
				return;
			}			
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	//Obtiene la externsion del archivo cargado.
		private String obtenerExtensionArchivo(String nombreArchivo)
				throws Exception {
			return nombreArchivo.substring(nombreArchivo.length() - 3);
		}
	
	/**
	 * Elimina valores en el datatable
	 * @param event
	 */
	public void deleteX(ActionEvent event) {
		log.info("Entrando al eliminar Premio Seleccionados");
		try {
			
			for (int i = 0; i < premioList.size(); i++) {
				Map dataPremioList = (Map)this.premioList.get(i);
				for (int j = 0; j < this.premioSeleccionado.length; j++) {
					Map dataPremioSeleccionad = (Map) this.premioSeleccionado[j];
					String codigoVentaPremio = dataPremioList.get("codigoVentaFicticio").toString();
					String codigoVentaSeleccionado  = dataPremioSeleccionad.get("codigoVentaFicticio").toString();
					String index = dataPremioSeleccionad.get("index").toString();
					if(StringUtils.equalsIgnoreCase(codigoVentaPremio,codigoVentaSeleccionado)){
						this.premioList.remove(i);
					}
				
				}
			}
			this.generarIndex();
			this.premioSeleccionado = null;
		}catch(Exception e){
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	//Ejecuta el reporte con los codigos  de consultoras INVALIDOS
	public void showInvalidoReport(ActionEvent event){
		try {			
			this.reporteInvalidos.setFormatoExportacion("XLS");
			this.reporteInvalidos.getFormReporte().setFormatoExportacion("XLS");
			this.reporteInvalidos.executeReporte();
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}
	
	//Ejecuta el reporte con los codigos  de consultoras VALIDOS
	public void showValidoReport(ActionEvent event){
		try {
			this.reporteValidos.setFormatoExportacion("XLS");
			this.reporteValidos.getFormReporte().setFormatoExportacion("XLS");
			this.reporteValidos.executeReporte();		
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = "";
		if (this.premioSeleccionado == null
				|| this.premioSeleccionado.length == 0) {
			mensaje = this
					.getResourceMessage("mantenimientoINCPremiosElectivosForm.mensaje.no.seleccion");
			
		}
		return mensaje;
	}
	

	/**
	 * @return the mantenimientoINCConcursoElectivosList
	 */
	public List getMantenimientoINCConcursoElectivosList() {
		return mantenimientoINCConcursoElectivosList;
	}

	/**
	 * @param mantenimientoINCConcursoElectivosList
	 *            the mantenimientoINCConcursoElectivosList to set
	 */
	public void setMantenimientoINCConcursoElectivosList(
			List mantenimientoINCConcursoElectivosList) {
		this.mantenimientoINCConcursoElectivosList = mantenimientoINCConcursoElectivosList;
	}

	/**
	 * @return the mostrarPopupCliente
	 */
	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	/**
	 * @param mostrarPopupCliente
	 *            the mostrarPopupCliente to set
	 */
	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	/**
	 * @return the mostrarDatosConsultora
	 */
	public boolean isMostrarDatosConsultora() {
		return mostrarDatosConsultora;
	}

	/**
	 * @param mostrarDatosConsultora
	 *            the mostrarDatosConsultora to set
	 */
	public void setMostrarDatosConsultora(boolean mostrarDatosConsultora) {
		this.mostrarDatosConsultora = mostrarDatosConsultora;
	}

	/**
	 * @return the popupCliente
	 */
	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	/**
	 * @return the busquedaClientesPOPUPSearchAction
	 */
	public BusquedaClientesPOPUPSearchAction getBusquedaClientesPOPUPSearchAction() {
		return busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @param busquedaClientesPOPUPSearchAction
	 *            the busquedaClientesPOPUPSearchAction to set
	 */
	public void setBusquedaClientesPOPUPSearchAction(
			BusquedaClientesPOPUPSearchAction busquedaClientesPOPUPSearchAction) {
		this.busquedaClientesPOPUPSearchAction = busquedaClientesPOPUPSearchAction;
	}

	/**
	 * @return the puntajeSaldo
	 */
	public String getPuntajeSaldo() {
		return puntajeSaldo;
	}

	/**
	 * @param puntajeSaldo
	 *            the puntajeSaldo to set
	 */
	public void setPuntajeSaldo(String puntajeSaldo) {
		this.puntajeSaldo = puntajeSaldo;
	}

	/**
	 * @return the notSave
	 */
	public String getNotSave() {
		return notSave;
	}

	/**
	 * @param notSave
	 *            the notSave to set
	 */
	public void setNotSave(String notSave) {
		this.notSave = notSave;
	}

	/**
	 * @return the premiosElectivosList
	 */
	public List getPremiosElectivosList() {
		return premiosElectivosList;
	}

	/**
	 * @param premiosElectivosList
	 *            the premiosElectivosList to set
	 */
	public void setPremiosElectivosList(List premiosElectivosList) {
		this.premiosElectivosList = premiosElectivosList;
	}

	/**
	 * @return the datatablePremio
	 */
	public DataTableModel getDatatablePremio() {
		return datatablePremio;
	}

	/**
	 * @param datatablePremio
	 *            the datatablePremio to set
	 */
	public void setDatatablePremio(DataTableModel datatablePremio) {
		this.datatablePremio = datatablePremio;
	}

	/**
	 * @return the arr
	 */
	public String getArr() {
		return arr;
	}

	/**
	 * @param arr
	 *            the arr to set
	 */
	public void setArr(String arr) {
		this.arr = arr;
	}

	/**
	 * @return the premioList
	 */
	public List getPremioList() {
		return premioList;
	}

	/**
	 * @param premioList
	 *            the premioList to set
	 */
	public void setPremioList(List premioList) {
		this.premioList = premioList;
	}

	/**
	 * @return the maxLongCodVentaFicticio
	 */
	public String getMaxLongCodVentaFicticio() {
		return maxLongCodVentaFicticio;
	}

	/**
	 * @param maxLongCodVentaFicticio
	 *            the maxLongCodVentaFicticio to set
	 */
	public void setMaxLongCodVentaFicticio(String maxLongCodVentaFicticio) {
		this.maxLongCodVentaFicticio = maxLongCodVentaFicticio;
	}

	/**
	 * @return the mostrarDatosExcel
	 */
	public Boolean getMostrarDatosExcel() {
		return mostrarDatosExcel;
	}

	/**
	 * @param mostrarDatosExcel
	 *            the mostrarDatosExcel to set
	 */
	public void setMostrarDatosExcel(Boolean mostrarDatosExcel) {
		this.mostrarDatosExcel = mostrarDatosExcel;
	}

	/**
	 * @return the hdIndicadorElegido
	 */
	public String getHdIndicadorElegido() {
		return hdIndicadorElegido;
	}

	/**
	 * @param hdIndicadorElegido
	 *            the hdIndicadorElegido to set
	 */
	public void setHdIndicadorElegido(String hdIndicadorElegido) {
		this.hdIndicadorElegido = hdIndicadorElegido;
	}

	/**
	 * @return the hdNumUnidades
	 */
	public String getHdNumUnidades() {
		return hdNumUnidades;
	}

	/**
	 * @param hdNumUnidades
	 *            the hdNumUnidades to set
	 */
	public void setHdNumUnidades(String hdNumUnidades) {
		this.hdNumUnidades = hdNumUnidades;
	}

	/**
	 * @return the hdCostePuntos
	 */
	public String getHdCostePuntos() {
		return hdCostePuntos;
	}

	/**
	 * @param hdCostePuntos
	 *            the hdCostePuntos to set
	 */
	public void setHdCostePuntos(String hdCostePuntos) {
		this.hdCostePuntos = hdCostePuntos;
	}

	/**
	 * @return the hdIndicadorPendiente
	 */
	public String getHdIndicadorPendiente() {
		return hdIndicadorPendiente;
	}

	/**
	 * @param hdIndicadorPendiente
	 *            the hdIndicadorPendiente to set
	 */
	public void setHdIndicadorPendiente(String hdIndicadorPendiente) {
		this.hdIndicadorPendiente = hdIndicadorPendiente;
	}

	/**
	 * @return the hdTipoPremio
	 */
	public String getHdTipoPremio() {
		return hdTipoPremio;
	}

	/**
	 * @param hdTipoPremio
	 *            the hdTipoPremio to set
	 */
	public void setHdTipoPremio(String hdTipoPremio) {
		this.hdTipoPremio = hdTipoPremio;
	}

	/**
	 * @return the hdIndicadorPremioAcum
	 */
	public String getHdIndicadorPremioAcum() {
		return hdIndicadorPremioAcum;
	}

	/**
	 * @param hdIndicadorPremioAcum
	 *            the hdIndicadorPremioAcum to set
	 */
	public void setHdIndicadorPremioAcum(String hdIndicadorPremioAcum) {
		this.hdIndicadorPremioAcum = hdIndicadorPremioAcum;
	}

	/**
	 * @return the hdNumNivel
	 */
	public String getHdNumNivel() {
		return hdNumNivel;
	}

	/**
	 * @param hdNumNivel
	 *            the hdNumNivel to set
	 */
	public void setHdNumNivel(String hdNumNivel) {
		this.hdNumNivel = hdNumNivel;
	}

	/**
	 * @return the hdNumPremio
	 */
	public String getHdNumPremio() {
		return hdNumPremio;
	}

	/**
	 * @param hdNumPremio
	 *            the hdNumPremio to set
	 */
	public void setHdNumPremio(String hdNumPremio) {
		this.hdNumPremio = hdNumPremio;
	}

	/**
	 * @return the hdCodigoVentaFicticio
	 */
	public String getHdCodigoVentaFicticio() {
		return hdCodigoVentaFicticio;
	}

	/**
	 * @param hdCodigoVentaFicticio
	 *            the hdCodigoVentaFicticio to set
	 */
	public void setHdCodigoVentaFicticio(String hdCodigoVentaFicticio) {
		this.hdCodigoVentaFicticio = hdCodigoVentaFicticio;
	}

	/**
	 * @return the hdoidPremioArticulo
	 */
	public String getHdoidPremioArticulo() {
		return hdoidPremioArticulo;
	}

	/**
	 * @param hdoidPremioArticulo
	 *            the hdoidPremioArticulo to set
	 */
	public void setHdoidPremioArticulo(String hdoidPremioArticulo) {
		this.hdoidPremioArticulo = hdoidPremioArticulo;
	}

	/**
	 * @return the hdIndicadorAnulado
	 */
	public String getHdIndicadorAnulado() {
		return hdIndicadorAnulado;
	}

	/**
	 * @param hdIndicadorAnulado
	 *            the hdIndicadorAnulado to set
	 */
	public void setHdIndicadorAnulado(String hdIndicadorAnulado) {
		this.hdIndicadorAnulado = hdIndicadorAnulado;
	}

	/**
	 * @return the hdMenorCodVenta
	 */
	public String getHdMenorCodVenta() {
		return hdMenorCodVenta;
	}

	/**
	 * @param hdMenorCodVenta
	 *            the hdMenorCodVenta to set
	 */
	public void setHdMenorCodVenta(String hdMenorCodVenta) {
		this.hdMenorCodVenta = hdMenorCodVenta;
	}

	/**
	 * @return the hdIndicadorValidos
	 */
	public String getHdIndicadorValidos() {
		return hdIndicadorValidos;
	}

	/**
	 * @param hdIndicadorValidos
	 *            the hdIndicadorValidos to set
	 */
	public void setHdIndicadorValidos(String hdIndicadorValidos) {
		this.hdIndicadorValidos = hdIndicadorValidos;
	}

	/**
	 * @return the premioSeleccionado
	 */
	public Object[] getPremioSeleccionado() {
		return premioSeleccionado;
	}

	/**
	 * @param premioSeleccionado the premioSeleccionado to set
	 */
	public void setPremioSeleccionado(Object[] premioSeleccionado) {
		this.premioSeleccionado = premioSeleccionado;
	}

	/**
	 * @return the indConsultora
	 */
	public boolean isIndConsultora() {
		return indConsultora;
	}

	/**
	 * @param indConsultora the indConsultora to set
	 */
	public void setIndConsultora(boolean indConsultora) {
		this.indConsultora = indConsultora;
	}

	/**
	 * @return the reporteValidos
	 */
	public ReporteINCPremiosElectivosValidosAction getReporteValidos() {
		return reporteValidos;
	}

	/**
	 * @param reporteValidos the reporteValidos to set
	 */
	public void setReporteValidos(
			ReporteINCPremiosElectivosValidosAction reporteValidos) {
		this.reporteValidos = reporteValidos;
	}

	/**
	 * @return the reporteInvalidos
	 */
	public ReporteINCPremiosElectivosInvalidosAction getReporteInvalidos() {
		return reporteInvalidos;
	}

	/**
	 * @param reporteInvalidos the reporteInvalidos to set
	 */
	public void setReporteInvalidos(
			ReporteINCPremiosElectivosInvalidosAction reporteInvalidos) {
		this.reporteInvalidos = reporteInvalidos;
	}
	
	

}
