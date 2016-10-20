package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelPedidosValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.ParametrosOperacionesReclamos;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoCabecera;
import biz.belcorp.ssicc.dao.model.ReclamoDigitadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.OperacionesResultado;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CodigoVentaMatriz;
import biz.belcorp.ssicc.dao.spusicc.sto.model.CodigoVentaPedido;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoReferencia;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DatosCabeceraCDRTO;
import biz.belcorp.ssicc.web.framework.util.DigitacionCDRDataModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECCodigoVentaMatrizForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECCodigoVentaPedidoForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECDigitacionCDRForm;


/**
 * The Class MantenimientoRECDigitacionCDRAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/12/2013
 */
@ManagedBean
@SessionScoped
public class MantenimientoRECDigitacionCDRAction extends BaseMantenimientoSearchAbstractAction {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	OperacionesResultado devolucionEspecial = new OperacionesResultado();
	private String validacionCambia = "";
	private String cargo = "";
	private String varOidPeriCDR = "";
	private String varCodOperSICC = "";
	private String opcion = "";
	private String valorFoco = "";
	private String visualizaRechazo = "";
	private String codigoVentaGlobal = "";
	private String codigoOperacionGlobal = "";
	private String codigoMotivoGlobal = "";
	private String textCodVenta = "";
	private String posicionMotivo = "";
	
	List listaOperaciones = new ArrayList();
	List listaMotivo = new ArrayList();	
	Map<String, OperacionesResultado> operaciones = new HashMap<String, OperacionesResultado>();
	private boolean mostrarBotonBuscarDOCREF;
	private boolean mostrarBotonBuscarCUVCambia;
	private boolean mostrarBotonBuscarCUVDesea;
	private boolean mostrarPopupRECCodigoVentaPedido;
	private boolean mostrarPopupRECDocRef;
	private boolean mostrarPopupCUV;
	private boolean mostrarPopupCVM;
	private static final String POPUP_RECDOCREF = "RECDOCREF";
	private static final String POPUP_BUSCUV = "BUSCUV";
	private static final String POPUP_BUSCVM = "BUSCVM";
	private static String mensajeValidaPedido;
	private List<ReclamoDigitadoDetalle> mantenimientoRECDigitacionCDRDetallesDigitadosList = new ArrayList<ReclamoDigitadoDetalle>();
	private List recOperacionParametrosList = new ArrayList();
	private List lstCodMotRechazo = new ArrayList();
	private double montoDevolucion = 0.0;
	private double montoPedido = 0.0;
	private double porcentajeDevolucion = 0.0;
	private double montoFaltantes = 0.0;
	private double porcentajeFaltantes = 0.0;
	private double montoCambios = 0.0;
	private double porcentajeCambios = 0.0;	
	
	private List arrCuvs = new ArrayList();
	private List arrDesc = new ArrayList();
	private List arrPrec = new ArrayList();
	private List arrPrecCata = new ArrayList();
	private List arrPosic = new ArrayList();
	private List stoDevolucionesList = new ArrayList();
	private List stoCambiosList = new ArrayList();
	private List stoFfneList = new ArrayList();
	private List stoOtrosList = new ArrayList();
	
	//Listas temporales  (verificar si es necesario trabajar con estas listas)
	private List arrUnidades = new ArrayList();
	private List arrCodigoVenta = new ArrayList();
	private List arrOperacion = new ArrayList();
	
	private String unidades = new String();
	private List arrCodigoOperacion = new ArrayList();
	private List arrIndicadorCUVCambiaObligatorio = new ArrayList();
	private List arrIndicadorCUVDeseaObligatorio = new ArrayList();
	private List arrIndicadorValidacionCUVCambia = new ArrayList();
	private List arrIndicadorValidacionCUVDesea = new ArrayList();
	private List arrPopupCambia = new ArrayList();
	private List arrPopupDesea = new ArrayList();
	private List arrIndicadorValidarCentroServicio = new ArrayList();
	
	private List listaIdentFila = new ArrayList();
	private List listaIdentFilaPadre = new ArrayList();
	
	private DatosCabeceraCDRTO ofertas = new DatosCabeceraCDRTO();
	private List<DatosCabeceraCDRTO> listaOfertas = new ArrayList<DatosCabeceraCDRTO>();
	private DigitacionCDRDataModel listaOfertasDataModel = new DigitacionCDRDataModel();
	
	private DatosCabeceraCDRTO datosCabeceraCDRTO = new DatosCabeceraCDRTO();
	private List<DatosCabeceraCDRTO> listaDatosCabeceraCDRTO = new ArrayList<DatosCabeceraCDRTO>();
	private DigitacionCDRDataModel digitacionCDRDataModel = new DigitacionCDRDataModel();
	private DatosCabeceraCDRTO[] seleccionados = {};
	
	private List<DatosCabeceraCDRTO> prodList = new ArrayList<DatosCabeceraCDRTO>();
	private List valoresOfertaPOSBUSC = new ArrayList();
	private List valoresOfertaPOSOFER = new ArrayList();				
	private List valoresOfertaCUV = new ArrayList();  
	private List valoresOfertaUNI = new ArrayList();
	private List valoresOfertaDESPRO = new ArrayList();
	private List valoresOfertaPREPRO = new ArrayList();
	
	private boolean mantenerFocoIgualEnvio;
	private boolean flagOperacionTrueque;
	private boolean onFocusMotivo;
	private boolean onFocusOperacion;
	private boolean onFocusCUV;
	private boolean onFocusCantidad;
	private boolean verex;	
	private boolean esEnterEnCantidadProductoCambiaOnChange;
	private boolean esEnterEnCantidadProductoCambia;
	private boolean mostrarBotonLimpiar;
	
	private boolean mostrarPanelDigitacionCdr;
	private boolean agregarFilasOferta;
	private boolean flagHiperConsulta;
	
	private String mensajeLocal;
	private String validacionDesea;
	
	private boolean visualizarPanelGrilla;
	private boolean errorValidaGrabarFinal;
	private int indiceValidaGrabarFinal  = 0;	
	
	/**
	 * Variables que deshabilitan componentes.
	 */
	//private boolean deshabilitaOperacion;
	private boolean deshabilitarEnvio;
	private boolean desabilitaMotivo;
	private boolean deshabilitaDescripcionProductoCambia;
	private boolean deshabilitaDescripcionProductoDesea;
	private boolean desabilitaCodigoMotivoRechazoDef;
	private boolean deshabilitaObservacionCDR;
	private boolean validaCodigoResultado;
	private boolean deshabilitaCUVDesea;
	private boolean deshabilitaCantidadDesea;
	
	private boolean readOnlyCUVCambia;
	private boolean readOnlyCUVDesea;
	private boolean readOnlyCantidadCambia;
	private boolean readOnlyCantidadDesea;
	
	private boolean onFocusCUVDesea;
	private boolean onFocusCantidadDesea;
	
	private int contValidaciones = 0;
	private boolean registroModificable;
	private double montoT = 0.0d;
	
	Map mantenimientoRecDigitacionCdrOfertaParametro = new HashMap();
	List mantenimientoRecDigitacionCdrListaOfertas = new ArrayList();
	
	//<input type="hidden" name="indicadorExcluirValidacionDevolucion" value='N' />
	private String indicadorExcluirValidacionDevolucion = "N";	
	//<input type="hidden" name="indicadorExcluirValidaciones" value='N' />
	private String indicadorExcluirValidaciones = "N";
	//<input type="hidden" name="indicadorPrimerIngreso" value='S' />
	private String indicadorPrimerIngreso = "S";

	/** The busqueda rec documento referencia search action. */
	@ManagedProperty(value="#{busquedaRECDocumentoReferenciaSearchAction}")
	private BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction;
	
	@ManagedProperty(value="#{busquedaRECCodigoVentaPedidoAction}")
	private BusquedaRECCodigoVentaPedidoAction busquedaRECCodigoVentaPedidoAction;
	
	@ManagedProperty(value="#{busquedaRECCodigoVentaMatrizAction}")
	private BusquedaRECCodigoVentaMatrizAction busquedaRECCodigoVentaMatrizAction;
	
	public void buscaCodigoVentaPedido(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'MantenimientoRECDigitacionCDRAction - buscaCodigoVentaPedido' method");
		}
	}
	
	public void limpiar(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("limpiar");
		}
		this.limpiarSinAction();
	}
	
	public void limpiarSinAction(){
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		f.setNumeroCDR("");
		f.setNumeroBoletaDespacho("");
		f.setCodigoConsejera("");
		f.setFechaPedido("");
		f.setCampana("");
		f.setCodigoConsejera("");
		f.setNombreConsejera("");
		f.setDireccionDomicilio("");
		f.setUbicacionGeografica("");
		f.setPeriodo("");
		f.setSaldoUnico("");
		f.setZona("");
		f.setPeriodoCDR("");
		f.setIndicadorExpress(false);
		f.setIndicadorCDRRechazo(false);
		f.setCodigoMotivoRechazoDef("");
		f.setObservacionCDR("");
		f.setCodigoMotivoRechazoDef("");
		f.setCodigoOperacionCorrecto("");
		this.setVisualizarPanelGrilla(false);
		this.setMostrarBotonBuscarDOCREF(true);
		this.setDatosCabeceraCDRTO(new DatosCabeceraCDRTO());
		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
		this.getDatosCabeceraCDRTO().setDesOperacion("");
		this.getDatosCabeceraCDRTO().setPuFactura("");
		this.setListaDatosCabeceraCDRTO(new ArrayList<DatosCabeceraCDRTO>());
		this.setDigitacionCDRDataModel(new DigitacionCDRDataModel());
		this.setArrCodigoVenta(new ArrayList());
		this.setArrUnidades(new ArrayList());
		this.setArrOperacion(new ArrayList());
		this.valorFoco = "2";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSalirPopup' method");
		}	
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			this.busquedaRECDocumentoReferenciaSearchAction.setBeanRegistroSeleccionado(null);
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCUV)){
			this.busquedaRECCodigoVentaPedidoAction.setBeanRegistroSeleccionado(null);
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCVM)){
 		  this.busquedaRECCodigoVentaMatrizAction.setBeanRegistroSeleccionado(null);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopup' method");
		}
		
		if(StringUtils.equals(accion,this.POPUP_RECDOCREF)){
			this.busquedaRECDocumentoReferenciaSearchAction.verificarRegistro(event);
			if(this.busquedaRECDocumentoReferenciaSearchAction.isSeleccionoRegistro()){
				DocumentoReferencia documentoReferencia = (DocumentoReferencia)this.busquedaRECDocumentoReferenciaSearchAction.getBeanRegistroSeleccionado();
				((MantenimientoRECDigitacionCDRForm)this.formBusqueda).setNumeroBoletaDespacho(documentoReferencia.getNumeroSolicitud());
				this.seteaFocoNumeroBoletaDespacho();
				this.busquedaRECDocumentoReferenciaSearchAction.setBeanRegistroSeleccionado(null);
				this.getRequestContext().execute("alert('SALIO ESTE MENSAJE?')");
			}
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCUV)){
			this.busquedaRECCodigoVentaPedidoAction.verificarRegistro(event);
			if(this.busquedaRECCodigoVentaPedidoAction.isSeleccionoRegistro()){
				CodigoVentaPedido codigoVentaPedido = (CodigoVentaPedido)this.busquedaRECCodigoVentaPedidoAction.getBeanRegistroSeleccionado(); 
				this.getDatosCabeceraCDRTO().setCodigoVentaCambia(codigoVentaPedido.getCodigoVenta());
				this.getDatosCabeceraCDRTO().setPuFactura(codigoVentaPedido.getPrecioFactura());
				this.getDatosCabeceraCDRTO().setProductoCambia(codigoVentaPedido.getDescripcion());
				this.busquedaRECCodigoVentaPedidoAction.setBeanRegistroSeleccionado(null);
				this.valorFoco = "4";
			}
		}
		
		if(StringUtils.equals(accion, this.POPUP_BUSCVM)){
			this.busquedaRECCodigoVentaMatrizAction.verificarRegistro(event);
			if(this.busquedaRECCodigoVentaMatrizAction.isSeleccionoRegistro()){
				CodigoVentaMatriz codigoVentaMatriz = (CodigoVentaMatriz)this.busquedaRECCodigoVentaMatrizAction.getBeanRegistroSeleccionado();
				this.getDatosCabeceraCDRTO().setCodigoVentaDesea(codigoVentaMatriz.getCodigoVenta());
				this.getDatosCabeceraCDRTO().setProductoDesea(codigoVentaMatriz.getDescripcion());
				this.getDatosCabeceraCDRTO().setPrecioDesea(codigoVentaMatriz.getPrecio());
				this.busquedaRECCodigoVentaMatrizAction.setBeanRegistroSeleccionado(null);
				this.valorFoco = "8";
			}
		}
	}
	
	public void seteaFocoNumeroBoletaDespacho(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'seteaFocoNumeroBoletaDespacho' method");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
	    String strLen = params.get("strLen");
	    String strMaxLen = params.get("strMaxLen");
	    String valorNumeroBoleto = params.get("valorNumeroBoleto");
	    String valorNumeroCDR = params.get("valorNumeroCDR");
	    
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		if(StringUtils.isNotBlank(valorNumeroCDR)
			&& StringUtils.isNotEmpty(valorNumeroCDR)){
			form.setNumeroCDR(valorNumeroCDR);
		}
		
		if(StringUtils.isNotEmpty(valorNumeroBoleto)
			&& StringUtils.isNotBlank(valorNumeroBoleto)){
			int intStrLen = Integer.parseInt(strLen);
			int intStrMaxLen = Integer.parseInt(strMaxLen);
			if(intStrLen < intStrMaxLen){
				//autocompletar con ceros a la izquierda
				form.setNumeroBoletaDespacho(this.cerosIzquierda(valorNumeroBoleto, intStrMaxLen));
				strLen = strMaxLen;
			}else{
				form.setNumeroBoletaDespacho(valorNumeroBoleto);
			}
		}
		
		if(StringUtils.isNotBlank(form.getNumeroBoletaDespacho())
			&& StringUtils.isNotEmpty(form.getNumeroBoletaDespacho())){
			
			if(StringUtils.equals(strLen,strMaxLen)){
				String data = ajax.getMensajeValidaPedido(form.getNumeroBoletaDespacho());
				if(StringUtils.isNotEmpty(data) && StringUtils.isNotBlank(data)){
					this.setMensajeLocal(data);
					this.mostrarDialogo();
					this.valorFoco = "2";
				}else{
					this.setDatos();
				}
			}
		}else{
			//TODO
			//openSearchDocumentosReferenciaPopup
		}
	}	
	
	public void setDatos(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDatos' method");
		}
		this.setearConsultora();
		this.valorFoco = "3";		
		this.ejecutarFunciones("1");
	}
	
	public void setearConsultora(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setearConsultora' method");
		}
		
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValueCDR data = ajax.getConsultoraReclamoByCodigo(form.getNumeroBoletaDespacho(), form.getCodigoPais(), form.getPeriodoActivo());
		this.loadConsultoraCallBack(data);
		if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getOperacion())
			|| StringUtils.isEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
			this.seteaCodigoOperacionPordefecto();
		}
		if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getMotivo())
			|| StringUtils.isEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
			this.seteaCodigoMotivoPordefecto();
		}
		this.setVisualizarPanelGrilla(true);
	}
	
	
	public void loadConsultoraCallBack(LabelValueCDR data){
		if(log.isDebugEnabled()){
			log.debug("loadConsultoraCallBack");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(data != null) {
			if(StringUtils.isNotBlank(data.getLabel()) 
				&& StringUtils.isNotEmpty(data.getLabel())){
				f.setCodigoConsejera(data.getLabel());
			}
			
			if(StringUtils.isNotEmpty(data.getValue())
				&& StringUtils.isNotBlank(data.getValue())){
				f.setNombreConsejera(data.getValue());
			}
			
			if(StringUtils.isNotEmpty(data.getZona())
				&& StringUtils.isNotBlank(data.getZona())){
				f.setZona(data.getZona());
			}
			
			//Ini PER-SiCC-2012-0642 
			if(StringUtils.isNotBlank(data.getNuevoNumeroBoleta())
				&& StringUtils.isNotEmpty(data.getNuevoNumeroBoleta())){
				f.setNumeroBoletaDespacho(data.getNuevoNumeroBoleta());
			}
			//Fin PER-SiCC-2012-0642
						
			if(StringUtils.isNotBlank(data.getDireccionDomicilio())
				&& StringUtils.isNotEmpty(data.getDireccionDomicilio())){
				f.setDireccionDomicilio(data.getDireccionDomicilio());
			}
			
			if(StringUtils.isNotBlank(data.getUbicacionGeografica())
				&& StringUtils.isNotEmpty(data.getUbicacionGeografica())){
				f.setUbicacionGeografica(data.getUbicacionGeografica());
			}
			
			//ini PER-SiCC-2012-0558
			if(StringUtils.isNotBlank(data.getCampana())
				&& StringUtils.isNotEmpty(data.getCampana())){
				f.setCampana(data.getCampana());
			}
						
			if(StringUtils.isNotBlank(data.getFechaFactura())
				&& StringUtils.isNotEmpty(data.getFechaFactura())){
				f.setFechaPedido(data.getFechaFactura());
			}
			
			if(StringUtils.isNotBlank(data.getSaldoUnico())
				&& StringUtils.isNotEmpty(data.getSaldoUnico())){
				f.setSaldoUnico(data.getSaldoUnico());
			}
			//validar
			
			if(StringUtils.equals(f.getIndicadorOnline(),"S")
					&&  StringUtils.equals(f.getIndicadorValCDRLinea(),"1")){
				this.setearPeriodoCDR();
			}
			//fin PER-SiCC-2012-0558
			
			this.setearPeriodo();
			
			//doliva			
			this.setMontoDevolucion(Math.abs(Double.parseDouble(data.getMontoDevolucion().replace(',','.'))));
			this.setMontoPedido(Double.parseDouble(data.getMontoPedido().replace(',','.')));
			this.setPorcentajeDevolucion(Double.parseDouble(data.getPorcentajeDevolucion().replace(',','.')));

			//obtenemos el monto de faltantes y cambios, ademas de los porcentajes
			this.setMontoFaltantes(Math.abs(Double.parseDouble(data.getMontoFaltantes().replace(',','.'))));
			this.setPorcentajeFaltantes(Double.parseDouble(data.getPorcentajeFaltantes().replace(',','.')));
			this.setMontoCambios(Math.abs(Double.parseDouble(data.getMontoCambios().replace(',','.'))));
			this.setPorcentajeCambios(Double.parseDouble(data.getPorcentajeCambios().replace(',','.')));
			
			List lista = data.getLista();
			this.arrCuvs = new ArrayList();
			this.arrDesc = new ArrayList();
			this.arrPrec = new ArrayList();
			this.arrPrecCata = new ArrayList();
			this.arrPosic = new ArrayList();
			for (int i = 0; i < lista.size(); i++) {
				this.arrCuvs.add(((LabelPedidosValue) lista.get(i)).getCodigoVta());
				this.arrDesc.add(((LabelPedidosValue) lista.get(i)).getDescripcion());
				this.arrPrec.add(((LabelPedidosValue) lista.get(i)).getPrecioCat());				
				this.arrPosic.add(((LabelPedidosValue)lista.get(i)).getPosicion());
				this.arrPrecCata.add(((LabelPedidosValue) lista.get(i)).getPrecioCatalogo());
			}	
			
			
			//doliva
			//alert('aca seria el bloqueo');	
			this.bloquear();

			//se obtiene el indicador de pedido chequeado
			if (StringUtils.equals(f.getIndicadorOnline(),"S") 
				&& StringUtils.equals(f.getIndicadorValCDRLinea(),"1")){
				if(StringUtils.equals(f.getIndicadorPedidoChequeado(),"1")){
					obtenerCodigoResultadoChequeo();
				}
			}

			//poner el foco en lista operaciones
			this.valorFoco = "3";			
		}	
		else{
			this.addError("Error", this.getResourceMessage("mensaje.noExisteBoleta"));
			f.setNumeroCDR("");
			f.setNumeroBoletaDespacho("");
			this.valorFoco = "1";
		}
	}
	
	protected void setearPeriodo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setearPeriodo' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String data = ajax.getPeriodoReclamo(form.getCodigoConsejera(),form.getPeriodoActivo());
		this.loadPeriodoCallBack(data);
	}
	
	protected void loadPeriodoCallBack(String data){
		if(log.isDebugEnabled()){
			log.debug("loadPeriodoCallBack");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotBlank(data)
				&& StringUtils.isNotEmpty(data)){
			form.setPeriodo(data);
			this.existeReclamo(form.getPeriodo());
		}
	}
	
	protected void existeReclamo(String periodo){
		if(log.isDebugEnabled()){
			log.debug("existeReclamo");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(!StringUtils.equals(this.getCargo(),"1")){
			String data = ajax.getExisteReclamo(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
												f.getNumeroCDR(),
												periodo,
												f.getCodigoConsejera());
			this.loadExisteReclamoCallback(data);
		}
	}
	
	protected void loadExisteReclamoCallback(String data){
		if(log.isDebugEnabled()){
			log.debug("loadExisteReclamoCallback");
		}
		if(!StringUtils.equals(data,"N")) {

			String ventana = "confirmDialogCDR";
			String ventanaConfirmar = "PF('" + ventana + "_confirmationDialogConfirmar').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			
		}
	}
	
	public void existeCDR(ActionEvent actionEvent){
		if(log.isDebugEnabled()){
			log.debug("existeCDR");
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
		if (StringUtils.equals(accion,"1"))  {
			//alert(data);
			/*$('numeroBoletaDespacho').value = data;
			var form =document.forms[0];
			form.action='findMantenimientoRECDigitacionCDR.do';
		   	form.submit(); */
			this.findMantenimientoRECDigitacionCDR();			
		}else{
			//$('codigoPais').value = '';
			/*$('numeroCDR').value = '';
			$('numeroBoletaDespacho').value = '';
			$('periodo').value = '';
			$('codigoConsejera').value = '';
			$('nombreConsejera').value = '';
			$('numeroCDR').focus();	
			//return false;*/
		}
	}
	
	protected void findMantenimientoRECDigitacionCDR(){
		if(log.isDebugEnabled()){
			log.debug("findMantenimientoRECDigitacionCDR");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		Map criteria =  new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("numeroCDR", f.getNumeroCDR());
		criteria.put("numeroBoletaDespacho", f.getCodigoConsejera());
		criteria.put("codigoPeriodo", f.getPeriodo());
		List detallesList = new ArrayList();
		detallesList = service.getListaDetallesDigitados(criteria);
		
		this.setMantenimientoRECDigitacionCDRDetallesDigitadosList(detallesList);					
	    
	    f.setIndicadorModifica("2");
	    if(detallesList.size()!=0)
	    	f.setIndicadorHayRegistros("1");
	    
	    f.setIndicadorExpressHidden(service.getIndicadorExpressBoletaRecojo(criteria));
	    log.debug("-------indicador = "+f.getIndicadorExpressHidden());
	}
	
	protected void setearPeriodoCDR() {
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotBlank(form.getCodigoConsejera()) &&
				StringUtils.isNotEmpty(form.getCodigoConsejera())){
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			LabelValue labelValue = ajax.getPeriodoCDR(form.getCodigoConsejera(), form.isIndicadorExpress()==true?"1":"0");
			if(labelValue != null){
				if(labelValue.getValue() == "0"){					
					this.addError("Error", this.getResourceMessage("Error en periodo del Pedido"));
				}else{
					form.setPeriodoCDR(labelValue.getValue());
					this.setVarOidPeriCDR(labelValue.getLabel());
				}
			}
		}
		
	}
	
	protected void existeReclamo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'existeReclamo' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(!StringUtils.equals(this.getCargo(), "1")){
			String valorExisteReclamo = ajax.getExisteReclamo(form.getCodigoPais(),form.getNumeroCDR(),form.getPeriodo(),form.getCodigoConsejera());
			if(!StringUtils.equals(valorExisteReclamo, "N")){
				form.setNumeroCDR("");
				form.setNumeroBoletaDespacho("");
				form.setPeriodo("");
				form.setCodigoConsejera("");
				form.setNombreConsejera("");
				this.valorFoco = "2";
			}else{
				form.setNumeroBoletaDespacho(valorExisteReclamo);
			}
		}
	}
	
	protected void obtenerCodigoResultadoChequeo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'obtenerCodigoResultadoChequeo' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String valor = ajax.getCodigoResultadoChequeo(form.getNumeroBoletaDespacho());
		if(StringUtils.isNotBlank(valor) 
				&& StringUtils.isNotEmpty(valor)){
			form.setCodigoResultadoChequeo(valor);
		}else{
			form.setCodigoResultadoChequeo("");
		}
	}
	
	public void bloquear(){
		this.mostrarBotonBuscarDOCREF = false;
	}
	
	
	
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {		
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_RECDOCREF)){
			this.mostrarPopupRECDocRef = true;
			this.mostrarPopupCUV = false;
			this.mostrarPopupCVM = false;
		}
		if(StringUtils.equals(accion, this.POPUP_BUSCUV)){
			try {				
				BusquedaRECCodigoVentaPedidoForm busquedaRECCodigoVentaPedidoForm = (BusquedaRECCodigoVentaPedidoForm)this.getBusquedaRECCodigoVentaPedidoAction().getFormBusqueda();
				busquedaRECCodigoVentaPedidoForm.setNumeroCruce(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getNumeroBoletaDespacho());
				busquedaRECCodigoVentaPedidoForm.setNumPedido(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getNumeroBoletaDespacho());
				busquedaRECCodigoVentaPedidoForm.setCodConsejera(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getCodigoConsejera());
				busquedaRECCodigoVentaPedidoForm.setNombreConsejera(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getNombreConsejera());
				this.mostrarPopupCUV = true;
				this.mostrarPopupRECDocRef = false;
				this.mostrarPopupCVM = false;
				busquedaRECCodigoVentaPedidoAction.setViewAtributes();
				busquedaRECCodigoVentaPedidoAction.setFindAttributes();				
			} catch (Exception e) {			
				e.printStackTrace();
			}
				
		}
		if(StringUtils.equals(accion, this.POPUP_BUSCVM)){
			try {
					BusquedaRECCodigoVentaMatrizForm busquedaRECCodigoVentaMatrizForm = (BusquedaRECCodigoVentaMatrizForm)this.getBusquedaRECCodigoVentaMatrizAction().getFormBusqueda();
					busquedaRECCodigoVentaMatrizForm.setNumeroCruce(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getNumeroBoletaDespacho());
					busquedaRECCodigoVentaMatrizForm.setCodigoPeriodo(((MantenimientoRECDigitacionCDRForm)this.formBusqueda).getCampana());
					this.mostrarPopupRECDocRef = false;
					this.mostrarPopupCUV = false;
					this.mostrarPopupCVM = true;
					busquedaRECCodigoVentaMatrizAction.setViewAtributes();
					busquedaRECCodigoVentaMatrizAction.setFindAttributes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECDigitacionCDRForm form = new MantenimientoRECDigitacionCDRForm(); 
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//======== VALIDA CUV CAMBIA - INICIO ========
	public boolean setFocusCUVCambiaGrabarFinal(DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("setFocusCUVCambiaGrabarFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		int index = 0;
		for(String objeto : this.getArrCodigoOperacion()){
			if(StringUtils.equals(elemento.getOperacion(), objeto)){
				index = this.getArrCodigoOperacion().indexOf(objeto);
				break;
			}
		}
		if(StringUtils.equals((String)arrIndicadorCUVCambiaObligatorio.get(index),"NO")
			&& !StringUtils.equals(elemento.getOperacion(), "T")){
			int index2 = 0;
			for (Object objeto : this.getArrCuvs()) {
				if(StringUtils.equals((String)objeto, elemento.getCodigoVentaCambia())){
					index2 = this.getArrCuvs().indexOf(objeto);
					break;
				}
			}
			if(index2 != -1){				
				if(this.getArrDesc().get(index)!=null
					&& StringUtils.isNotBlank(elemento.getCodigoVentaCambia())){
					elemento.setListaIdentSolicPos((String)this.getArrPosic().get(index));
					this.setCodigoOperacionGlobal(elemento.getOperacion());
					this.verificaExcepcionGrabarFinal(elemento, "4", indice);
					verex = true;
				}
			}
		}else{
			if(StringUtils.isEmpty(elemento.getCodigoVentaCambia()) 
				|| StringUtils.isBlank(elemento.getCodigoVentaCambia())){
				return false;
			}else{
				int index2 = 0;
				for(Object objeto : this.getArrCuvs()){
					if(StringUtils.equals(elemento.getCodigoVentaDesea(), (String)objeto)){
						index2 = this.getArrCuvs().indexOf(objeto);
						break;
					}
				}
				if(index2 != -1){
					if(this.getArrDesc().get(index2) != null
							&& StringUtils.isNotBlank(elemento.getCodigoVentaCambia())
							&& StringUtils.isNotEmpty(elemento.getCodigoVentaCambia())){
						//validarException
						elemento.setListaIdentSolicPos((String)this.getArrPosic().get(index2));
						this.verificaExcepcionGrabarFinal(elemento, "4", indice);
						this.verex = true;
					}
				}
			}
		}
		//Evalua el tipo de validacion parametrizada para el tipo de operacion seleccionado		
		this.validacionCambia = (String)this.getArrIndicadorValidacionCUVCambia().get(index);
		if(StringUtils.isBlank((String)this.getArrIndicadorValidacionCUVCambia().get(index))
			|| StringUtils.isEmpty((String)this.getArrIndicadorValidacionCUVCambia().get(index))){
			
		}else{
			if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVCambia().get(index),"PE")){
				int index2 = 0;
				for(Object objeto : this.getArrCuvs()){
					if(StringUtils.equals(elemento.getCodigoVentaCambia(), (String)objeto)){
						index2 = this.getArrCuvs().indexOf(objeto);
						break;
					}
				}
				if(index2 == -1){
					//this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
					//this.mostrarDialogoGeneral();
					this.addError("Error", this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
					elemento.setCodigoVentaCambia("");
					elemento.setDesOperacion("");
					elemento.setPuFactura("");
					elemento.setListaIdentSolicPos("");
				}else{
					elemento.setListaIdentSolicPos((String)this.getArrPosic().get(index2));
					this.setCodigoVentaGlobal(elemento.getCodigoVentaCambia());
					this.setCodigoOperacionGlobal(elemento.getOperacion());
					if(!this.verex){
						verificaExcepcionGrabarFinal(elemento, "4", indice);
					}
				}
			}else{
				if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVCambia().get(index),"MF")){
					LabelValue data = ajax.validarCUVMatrizPreciosPremios(elemento.getCodigoVentaCambia(), "1", f.getNumeroBoletaDespacho());
					return this.loadProductoGrabarFinalCallback(data);					
				}else{
					if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVCambia().get(index),"MP")){
						LabelValue data = ajax.validarCUVMatrizPreciosPremios(elemento.getCodigoVentaCambia(), "2", f.getNumeroBoletaDespacho());
						return this.loadProductoGrabarFinalCallback(data);
						
					}else{
						if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVCambia().get(index),"-")){
							/*x1=form.listaCantidadCambia;
							x1[form.listaCantidadCambia.length-1].focus();*/
							int index2 = 0;
							for (Object objeto : this.getArrCuvs()) {
								if(StringUtils.equals((String)objeto, elemento.getCodigoVentaCambia())){
									index2 = this.getArrCuvs().indexOf(objeto);
									break;
								}
							}
							
							if(index2 != -1){
								if(this.getArrDesc().get(index2) != null 
									&& StringUtils.isNotBlank(elemento.getCodigoVentaCambia())
										&& StringUtils.isNotEmpty(elemento.getCodigoVentaCambia())){
									elemento.setListaIdentSolicPos((String)this.getArrPosic().get(index2));
									codigoVentaGlobal = elemento.getCodigoVentaCambia();
									codigoOperacionGlobal = elemento.getOperacion() ;

									//validarException
									if(!verex)
										return verificaExcepcionGrabarFinal(elemento,"4", indice);
								}
							}
						}		
					}
				}
			}
		}
		//VALIDACION GAR
		if(StringUtils.equals((String)this.getArrIndicadorValidarCentroServicio().get(index),"SI")){
			this.setTextCodVenta(elemento.getCodigoVentaCambia());
		}		
		//hasta aqui la val de operacion
		return false;
	}
	
	protected boolean loadProductoGrabarFinalCallback(LabelValue data){
		if(log.isDebugEnabled()){
			log.debug("loadProductoGrabarFinalCallback");
		}
		if(data != null){
			/*x1=form.listaCantidadCambia;
	 		x1[form.listaCantidadCambia.length-1].focus();*/
	 		//form.listaDescripcionCambia[form.listaCantidadCambia.length-1].value = data.label;

	 		//WARN!! No se ha determinado si se hace la validacion por tipoOeracion
	 		//Devolucion, toma CERO si precioCatalogo es CERO y precioFactura en otro caso 
		 	//form.listaPrecioCambia[form.listaCantidadCambia.length-1].value = data.value;
		}else{
			/*form.listaDescripcionCambia[form.listaCUVCambia.length-1].value = '';
	    	form.listaPrecioCambia[form.listaCUVCambia.length-1].value = '';
	    	form.listaCUVCambia[form.listaCUVCambia.length-1].value = '';
	    	form.listaCUVCambia[form.listaCUVCambia.length-1].focus();
	    	onFocusOperacion = false;
			onFocusCUV = true;
			onFocusCantidad = false;*/
			StringBuilder mensaje = new StringBuilder();
			if(StringUtils.equals(this.getValidacionCambia(),"PE")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
			}
			if(StringUtils.equals(this.getValidacionCambia(), "MF")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.precisos"));
			}
			if(StringUtils.equals(this.getValidacionCambia(), "MP")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
			}
			if(StringUtils.isEmpty(mensaje.toString())
				|| StringUtils.isBlank(mensaje.toString())){
				//this.setMensajeAlertaDefault(mensaje.toString());
				//this.mostrarDialogoGeneral();
				this.addError("Error", mensaje.toString());
				return true;
			}
		}
		return false;
	}
	
	public boolean verificaExcepcionGrabarFinal(DatosCabeceraCDRTO elemento, String variable, int indice){
		if(log.isDebugEnabled()){
			log.debug("verificaExcepcionGrabarFinal");
		}
		opcion = variable;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(StringUtils.equals(f.getIndicadorOnline(),"S") 
			&& StringUtils.equals(f.getIndicadorValCDRLinea(),"1") 
				&& (StringUtils.equals(this.getVisualizaRechazo(),"0") || StringUtils.equals(this.getVisualizaRechazo(),"2"))){
			if(StringUtils.isNotBlank(this.getVarCodOperSICC()) 
				&& StringUtils.isNotEmpty(this.getVarCodOperSICC())
					&& StringUtils.equals(operacionesResultado.getCambVali(), "PE") ) {//IND_CAMB_VALI
				String data = ajax.getValExcepProduGanador(varOidPeriCDR,
															elemento.getCodigoVentaCambia(), 
															varCodOperSICC, 
															operacionesResultado.getTipoOperSicc(), 
															elemento.getListaIdentSolicPos(),															
															elemento.getMotivo(),
															this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
															f.getIndicadorValorAceptaCDR(),
															this.getIndicadorExcluirValidaciones());
				return this.loadMensajeGrabarFinalProduCallBack(elemento, data, indice);
			}
		}
		return false;
	}
	
	protected boolean loadMensajeGrabarFinalProduCallBack(DatosCabeceraCDRTO elemento, String data, int indice){
		if(log.isDebugEnabled()){
			log.debug("loadMensajeGrabarFinalProduCallBack");
		}
		String[] arrRespuesta = new String[]{};
		if(StringUtils.isBlank(data)
			|| StringUtils.isEmpty(data)){
			this.addError("Error", data);
			return true;
		}
		arrRespuesta = StringUtils.split(data,"|");
		data = arrRespuesta[0];
		String reemplazar = arrRespuesta[1];
		this.setIndicadorExcluirValidacionDevolucion("N");
		if(StringUtils.equals(reemplazar, "0")){
			if(StringUtils.equals(elemento.getOperacion(),"D")){
				elemento.setOperacion(devolucionEspecial.getCodigo());
				((DatosCabeceraCDRTO)this.getArrOperacion().get(indice)).setOperacion("N");
			}
			if(StringUtils.equals(reemplazar,"1")){
				elemento.setMotivo(arrRespuesta[2]);
				this.setIndicadorExcluirValidacionDevolucion("S");
			}
		}
		return false;
	}
	
	//====================== VALIDACIONES GRABAR FINAL - INICIO ==========================
	
	//======== VALIDA COMBO OPERACION - INICIO ========
	public boolean ejecutarFuncionesGrabarFinal(DatosCabeceraCDRTO elemento){
		if(log.isDebugEnabled()){
			log.debug("ejecutarFuncionesGrabarFinal");
		}
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		StringBuilder datoCompuesto = new StringBuilder();
		datoCompuesto.append(operacionesResultado.getCodigo())
					.append("|")
					.append(operacionesResultado.getTipoOperSicc())
					.append("|")
					.append(operacionesResultado.getCodOperSicc());
		if(StringUtils.equals(f.getIndicadorOnline(),"S")  
				&& StringUtils.equals(f.getIndicadorValCDRLinea(),"1") 
					&& StringUtils.isNotBlank(varOidPeriCDR) 
						&& (StringUtils.equals(this.getVisualizaRechazo(),"0") || StringUtils.equals(this.getVisualizaRechazo(),"2"))){
			LabelValue data = ajax.getCodigoOperacionCorrecto(datoCompuesto.toString(),
									elemento.getMotivo(), 
									this.indicadorExpressEstado(),
									this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
									f.getNumeroBoletaDespacho(), 
									varOidPeriCDR, 
									this.getmPantallaPrincipalBean().getCurrentUser().getCodigo(),
									this.getIndicadorExcluirValidaciones());
			return loadgetCodigoOperacionCorrectoGrabarFinalCallBack(data,elemento);
		}
		return false;
	}
	
	private boolean loadgetCodigoOperacionCorrectoGrabarFinalCallBack(LabelValue data, DatosCabeceraCDRTO elemento){
		if(log.isDebugEnabled()){
			log.debug("loadgetCodigoOperacionCorrectoGrabarFinalCallBack");
		}		
		if(data != null){
			if(data.getLabel() != null){
				elemento.setOperacion(data.getLabel());
				varCodOperSICC = data.getLabel();
			}
			if(data.getValue() != null){
				this.setMensajeLocal(data.getValue());
				this.mostrarDialogo();
				return true;
			}
		}
		return false;
	}
	//======== VALIDA COMBO OPERACION - FINAL ========
	
	//======== VALIDA CANTIDAD CAMBIA - INICIO ========
	public boolean seteaFocoUnidadesCambiaGrabarFinal(DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("seteaFocoUnidadesCambiaGrabarFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		boolean resultado = false;
		this.esEnterEnCantidadProductoCambiaOnChange = true;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		resultado = this.getValUnidadReclamoGrabarFinal("5", indice, elemento);
		/*var dato = form.listaOperacion[indice].value.split("|");
		var operacion = dato[0];
  		var camObi = dato[4];
  		var cantidad = form.listaCantidadCambia[indice].value;
  		var oidSoliPosi = form.listaIdentSolicPos[indice].value;
		var codigo = form.listaCUVCambia[indice].value;*/

		if(StringUtils.isNotBlank(elemento.getCodigoVentaCambia())){
  			if(StringUtils.equals(operacionesResultado.getCambObli(),"SI")
  				&& (StringUtils.isBlank(elemento.getCantidadCambia()) 
  						|| Integer.parseInt(elemento.getCantidadCambia()) == 0)){
  				this.addError("Error:", "Cantidad debe ser mayor a cero.");
  				errorValidaGrabarFinal = true; 
  				return false;
  			}

  			if(StringUtils.equals(operacionesResultado.getCambObli(),"NO")
  	  				&& (StringUtils.isBlank(elemento.getCantidadCambia()) || Integer.parseInt(elemento.getCantidadCambia()) == 0) 
  	  				&& StringUtils.equals(elemento.getOperacion(),"T") 
  	  				&& !StringUtils.isBlank(elemento.getCantidadCambia())){
  				this.addError("Error:", "Cantidad debe ser mayor a cero");
  				errorValidaGrabarFinal = true;      				
  				return false;
  			}

  			esEnterEnCantidadProductoCambia = true;
  			
			if(!StringUtils.equals(operacionesResultado.getCodigo(),"G") 
				&& !StringUtils.equals(operacionesResultado.getCodigo(),"F") 
					&& !StringUtils.equals(operacionesResultado.getCodigo(),"H") 
						&& !StringUtils.equals(operacionesResultado.getCodigo(),"F3") 
							&& !StringUtils.equals(operacionesResultado.getCodigo(),"F4") 
								&& !StringUtils.equals(operacionesResultado.getCodigo(),"G3") 
									&& !StringUtils.equals(operacionesResultado.getCodigo(),"G4") 
										&& !StringUtils.equals(operacionesResultado.getCodigo(),"XA") 
											&& !StringUtils.equals(operacionesResultado.getCodigo(),"XI") 
												&& !StringUtils.equals(operacionesResultado.getCodigo(),"XM") 
													&& !StringUtils.equals(operacionesResultado.getCodigo(),"XP")){///CAMBIO
      			if(StringUtils.equals(operacionesResultado.getCodigo(),"D") 
      				&& StringUtils.equals(f.getIndicadorDevolucionOferta(),"1")){
	      			// En este caso solo valida el primer registro, los demas que fueron agregados en forma automatica ya no se validan      				
      					
	      			if(StringUtils.isNotBlank((String)this.getListaIdentFila().get(indice))
	      				&& StringUtils.isNotBlank((String)this.getListaIdentFilaPadre().get(indice))
	      				&& StringUtils.equals((String)this.getListaIdentFila().get(indice), (String)this.getListaIdentFilaPadre().get(indice))){
	      				return this.validarOfertaGrabarFinal(operacionesResultado, elemento.getListaIdentSolicPos(), elemento, indice);
	      			}
      			}
			}
		}
		return resultado;
	}
	
	protected boolean validarOfertaGrabarFinal(OperacionesResultado operacionesResultado, String oidSoliPosi, DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("validarOfertaGrabarFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(StringUtils.equals(operacionesResultado.getCodigo(), "D")
			&& StringUtils.equals(f.getIndicadorOnline(),"S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")){
			Map data = null; //ajax.ofertaDevolucion(oidSoliPosi, elemento.getCantidadCambia());
			return this.validarOfertaGrabarFinalCallback(elemento,data,indice);
		}
		return false;
	}
	
	protected boolean validarOfertaGrabarFinalCallback(DatosCabeceraCDRTO elemento, Map map,int indice){
		if(log.isDebugEnabled()){
			log.debug("validarOfertaGrabarFinalCallback");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String valor = "";
		valor = (String)map.get("valor");
		this.mantenimientoRecDigitacionCdrOfertaParametro = (Map)map.get("mantenimientoRecDigitacionCdrOfertaParametro");
		this.mantenimientoRecDigitacionCdrListaOfertas = (List)map.get("mantenimientoRecDigitacionCdrListaOfertas");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		String[] arrRespuesta = valor.split("|");
		String muestraOferta = arrRespuesta[0];
		String mensajeError = arrRespuesta[1];
		if(StringUtils.isNotBlank(mensajeError)){
			this.addError("Error:", mensajeError);
			errorValidaGrabarFinal = true;
			return true;
			/*if(muestraOferta > 0){
				//Error en cantidad
				onFocusCantidad = true;
				openSearchOfertaPopup();
			}*/
		}else{
			if(StringUtils.equals(muestraOferta,"0")){
				//poner foco en listamotivo
				/*var y1=form.listaMotivo;
				y1[y1.length-1].focus();*/
			}else{
				//Validar cada registro de la tabla GTT
				double montoDevolucionActual = calcularMontoDevolucionActualGrabarFinal(indice);
								
				//Indica donde valida el codigo de venta [PE]:en el pedido [MF]:en la matriz de facturacion [MP]:en la matriz de premios [ ]:no se valida
				//Para este caso debe de ser PE
				//var indCamVali = dato[3];
				String cantidades = calcularCantidadesPorCUVGrabarFinal();				
				String data = ""; /*ajax.validarProductosOferta(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),	//UR
															f.getCodigoConsejera(),	//UR
															cantidades,	//UR
															Double.toString(porcentajeDevolucion), //PD
															Double.toString(montoDevolucionActual), //PD
															Double.toString(montoDevolucion),	//PD
															Double.toString(montoPedido),	//PD
															varOidPeriCDR, //PROD_GANA
															varCodOperSICC, //PROD_GANA 
															operacionesResultado.getTipoOperSicc(),  //PROD_GANA
															operacionesResultado.getCambVali(),  //PROD_GANA
															elemento.getMotivo(), //PROD_GANA
															this.mantenimientoRecDigitacionCdrListaOfertas,
															this.mantenimientoRecDigitacionCdrOfertaParametro);*/
				return this.validarProductosOfertaGrabarFinalCallBack(data);
			}
		}
		return false;
	}
	
	protected boolean validarProductosOfertaGrabarFinalCallBack(String data){
		if(log.isDebugEnabled()){
			log.debug("validarProductosOfertaGrabarFinalCallBack");
		}
		
		if(StringUtils.equals(data, "1")){
			errorValidaGrabarFinal = true;
			return true;
		}else{
			//Salvamos la data en variables globales
			String[] valoresOferta = data.split("|");

			if(valoresOferta.length > 0){
				valoresOfertaPOSBUSC.add(valoresOferta[0].split(";"));
				valoresOfertaPOSOFER.add(valoresOferta[1].split(";"));
				valoresOfertaCUV.add(valoresOferta[2].split(";"));
				valoresOfertaUNI.add(valoresOferta[3].split(";"));
				valoresOfertaDESPRO.add(valoresOferta[4].split(";"));
				valoresOfertaPREPRO.add(valoresOferta[5].split(";"));
			}
				
			/*var form = document.forms[0];
			var x1 = form.listaMotivo;
			x1[form.listaMotivo.length-1].focus();*/
			
			agregarFilasOferta = true;
		}
		return false;
	}
	
	protected String calcularCantidadesPorCUVGrabarFinal(){
		if(log.isDebugEnabled()){
			log.debug("calcularCantidadesPorCUVGrabarFinal");
		}
		//Calcula todas las cantidades de todos los CUV' ingresados
		MultiValueMap map = new MultiValueMap();
		for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
			 map.put(elemento.getCodigoVentaCambia(), elemento.getCantidadCambia());
		}
				
		Set<String> keys = map.keySet();
        StringBuilder lista = new StringBuilder("");
        for (String key : keys) {
        	Collection<String> col =  (Collection<String>) map.get(key);
        	int total = 0;
            for (String elemento : col) {
				total += Integer.parseInt(elemento);
			}
            lista.append(key)
        	.append("|")
        		.append(total)
        		.append(";");
        }		
					
		/*for(int i=0; i < this.getArrCodigoVenta().size(); i++){
			String cv = (String)this.getArrCodigoVenta().get(i);
			var pc = productosCantidades[cv];
			
			if(pc == null){
				productosCantidades[cv] = parseInt(arrUnidades[i]);
			}else{
				pc += parseInt(arrUnidades[i]);
				productosCantidades[cv] = pc;
			}
		}
		
		for(producto in productosCantidades){
			lista = lista + producto + '|' + productosCantidades[producto] + ";";
		}*/
		
		return lista.toString();
	}
	
	//======== VALIDA CUV DESEA - INICIO ========
	public boolean setFocusCUVDeseaGrabarFinal(DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("setFocusCUVDeseaGrabarFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		int index = 0;		  
		for(String objeto : this.getArrCodigoOperacion()){
			if(StringUtils.equals(operacionesResultado.getCodigo(), objeto)){
				index = this.getArrCodigoOperacion().indexOf(objeto);
				break;
			}
		}
		
		//Evalua el tipo de validacion parametrizada para el tipo de operacion seleccionado
		int index2 = 0;
		for(Object objeto : this.getArrCuvs()){
			if(StringUtils.equals(elemento.getCodigoVentaDesea(), (String)objeto)){
				index2 = this.getArrCuvs().indexOf(objeto);
				break;
			}
		}
		
		if(StringUtils.equals((String) this.getArrIndicadorValidacionCUVDesea().get(index),"PE")){
			if(index2 == -1){
//				this.mensajeLocal = this.getResourceMessage("mensaje.codVtaNoExisteCDR");
//				this.mostrarDialogo();
				this.addError("Error:", this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
				f.setListaCUVDesea(new String[]{""});
			}else{				
				return verificaExcepcionDeseaFinal(elemento, 
													this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), 
													"8", 
													indice);
			}
		}else{
			if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"MF")){
				LabelValue data = ajax.validarCUVMatrizPreciosPremios(elemento.getCodigoVentaDesea(),
																		"1",
																		f.getNumeroBoletaDespacho());
				return this.loadProductoDeseaGrabarFinalCallback(data);
			}else{
				if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"MP")){
					LabelValue data = ajax.validarCUVMatrizPreciosPremios(elemento.getCodigoVentaDesea(),
																			"2",
																			f.getNumeroBoletaDespacho());
					return this.loadProductoDeseaGrabarFinalCallback(data);
				}
			}
			return verificaExcepcionDeseaFinal(elemento, 
												this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), 
												"8", 
												indice);
		}
		return false;
	}
	
	protected boolean loadProductoDeseaGrabarFinalCallback(LabelValue data){
		if(log.isDebugEnabled()){
			log.debug("loadProductoDeseaGrabarFinalCallback");
		}
		if(data != null){
			
		}else{
			StringBuilder mensaje = new StringBuilder();
			if(StringUtils.equals(this.getValidacionDesea(), "PE")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
			}
			if(StringUtils.equals(this.getValidacionDesea(), "MF")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.precios"));
			}
			if(StringUtils.equals(this.getValidacionDesea(), "MP")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
			}
			if(StringUtils.isEmpty(mensaje.toString())
				|| StringUtils.isBlank(mensaje.toString())){
				//this.setMensajeAlertaDefault(mensaje.toString());
				//this.mostrarDialogoGeneral();
				this.addError("Error", mensaje.toString());
				return true;
			}
		}
		return false;
	}
	//======== VALIDA CUV DESEA - FINAL ========
	
	public boolean verificaExcepcionDeseaFinal(DatosCabeceraCDRTO elemento, String idSoliPosi, String variable, int indice){
		if(log.isDebugEnabled()){
			log.debug("verificaExcepcionDeseaFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		this.opcion = variable;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		if(StringUtils.equals(f.getIndicadorOnline(),"S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
				&& StringUtils.equals(this.getVisualizaRechazo(),"0") 
					|| StringUtils.equals(this.getVisualizaRechazo(),"2")){
			if(StringUtils.isNotEmpty(varCodOperSICC) 
				&& StringUtils.isNotBlank(varCodOperSICC) 
					&&  StringUtils.isNotEmpty(operacionesResultado.getDeseVali())  && StringUtils.isNotBlank(operacionesResultado.getDeseVali())) {//IND_CAMB_VALI 
				String data = "";/*ajax.getValExcepProduDesea(f.getCampana(), 
															elemento.getCodigoVentaDesea(), 
															varCodOperSICC, 
															operacionesResultado.getTipoOperSicc(), 
															idSoliPosi, 
															elemento.getMotivo(),"");*/
				if(StringUtils.isNotBlank(data) || StringUtils.isNotEmpty(data)){
					//this.setMensajeAlertaDefault(data);
					//this.mostrarDialogoGeneral();
					this.addError("Error", data);
					return true;
				}
			}
		}
		return false;
	}
	
	//======== VALIDA CANTIDAD DESEA - INICIO ========
	public boolean agregarDetalleGrabarFinal(DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("agregarDetalleGrabarFinal");
		}
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		int index = 0;
		for (Object objeto : this.getArrCodigoOperacion()) {
			if(StringUtils.equals(operacionesResultado.getCodigo(), (String)objeto)){
				index = this.getArrCodigoOperacion().indexOf(objeto);
				break;
			}
		}
		//Evalua parametro que indica si el CUV es obligatorio o no
		if(StringUtils.equals((String)this.getArrIndicadorCUVDeseaObligatorio().get(index),"SI")
			&& StringUtils.isBlank(elemento.getCantidadDesea())
				|| StringUtils.isBlank(elemento.getCantidadDesea())){
			//this.setMensajeAlertaDefault("Cantidad debe ser mayor a cero");
			//this.mostrarDialogoGeneral();
			this.addError("Error", "Cantidad debe ser mayor a cero");
			return true;
		}else{
			this.getValUnidadDeseaGrabarFinal(elemento, indice);
			Integer data = ajax.getSaldoProducto(f.getNumeroBoletaDespacho(), 
							this.getDatosCabeceraCDRTO().getCodigoVentaDesea(), 
							this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			this.validaSaldoProductoGrabarFinalCallBack(elemento,data);
		}		
		return false;
	}
	
	private void validaSaldoProductoGrabarFinalCallBack(DatosCabeceraCDRTO elemento, Integer data){
		if(log.isDebugEnabled()){
			log.debug("validaSaldoProductoGrabarFinalCallBack");
		}
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
			if(validarMontoDevolucionGrabarFinal(this.getDatosCabeceraCDRTO(),indiceValidaGrabarFinal)){
				esEnterEnCantidadProductoCambia = false;
				esEnterEnCantidadProductoCambiaOnChange = false;
			}else{
				this.setReadOnlyCUVCambia(false);
				this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
	
				this.setReadOnlyCantidadCambia(false);
				this.getDatosCabeceraCDRTO().setCantidadCambia("");
	
				this.setDeshabilitarEnvio(false);
				this.getDatosCabeceraCDRTO().setIgualEnvio(false);
	
				this.setReadOnlyCUVDesea(false);
				this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
		 		
				this.setReadOnlyCantidadDesea(false);
				this.getDatosCabeceraCDRTO().setCantidadDesea("");
	
				this.getDatosCabeceraCDRTO().setProductoCambia("");
				this.getDatosCabeceraCDRTO().setPuFactura("");
	
				this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
				
				String[] objeto = { " " + porcentajeDevolucion + "%"};
				this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje", objeto));
			}
		}else{
			int index = this.getArrCodigoOperacion().indexOf(CollectionUtils.find(this.getArrCodigoOperacion(), new EqualPredicate(operacionesResultado.getCodigo())));
			//Evalua parametro que indica si el CUV es obligatorio o no
			if(StringUtils.equals((String)this.getArrIndicadorCUVCambiaObligatorio().get(index),"NO")
				|| StringUtils.equals((String)this.getArrIndicadorCUVCambiaObligatorio().get(index),"SI")
					&& StringUtils.equals(operacionesResultado.getCodigo(),"T")){
				
				if(validarMontoDevolucionGrabarFinal(this.getDatosCabeceraCDRTO(),indiceValidaGrabarFinal)){
					//addRow('prodList',listaCampos);
					//desabilitarFilas();
					esEnterEnCantidadProductoCambia = false;
					esEnterEnCantidadProductoCambiaOnChange = false;
					//ejecutarFunciones(1); PENDIENTE
				}else{
					
					this.setReadOnlyCUVCambia(false);
					this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
					
					this.setReadOnlyCantidadCambia(false);
					this.getDatosCabeceraCDRTO().setCantidadCambia("");
		
					this.setDeshabilitarEnvio(false);
					this.getDatosCabeceraCDRTO().setIgualEnvio(false);
		
					this.setReadOnlyCUVDesea(false);
					this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
		 			
					this.setReadOnlyCantidadCambia(false);
					this.getDatosCabeceraCDRTO().setCantidadCambia("");			 		
			 		
					this.setDeshabilitarEnvio(false);
					this.getDatosCabeceraCDRTO().setIgualEnvio(false);

					this.setReadOnlyCUVDesea(false);
					this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
					
					this.setReadOnlyCantidadDesea(false);
					this.getDatosCabeceraCDRTO().setCantidadDesea("");
					
					this.getDatosCabeceraCDRTO().setProductoCambia("");
					this.getDatosCabeceraCDRTO().setPuFactura("");

					this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
			 		
					String[] objeto = { " " + porcentajeDevolucion + "%"};
					this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje", objeto));
		 		}	 
			}
		}
	}
	
	public boolean validarMontoDevolucionGrabarFinal(DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("validarMontoDevolucionGrabarFinal");
		}
		
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		//Solo aplica la logica para la pantalla de callcenter
		if (!StringUtils.equals(f.getIndicadorOnline(),"S")){
			return true;
		}
		//Solo aplica la logica si esta encendido el indicador
		if(StringUtils.equals(f.getIndicadorValidaDevolucion(),"0")){
			return true;
		}
		//alert('va a validar');
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		//var operacion=form.listaOperacion[form.listaOperacion.length-1].value;

		//Solo se realiza la validacion si la opercacione es de devolucion
		if (StringUtils.equals(operacionesResultado.getCodigo(),"D")){
			return true;
		}
		
		/*var monto = calcularMontoDevolucionActualGrabarFinal(indice);
		var aux = ((monto + montoDevolucion)/montoPedido)*100;
		
		if (aux > porcentajeDevolucion)
			return false;
		else
			return true;*/
		return false;
	}
	//======== VALIDA CANTIDAD DESEA - FINAL ========
	
	public double calcularMontoDevolucionActualGrabarFinal(int indice){
		if(log.isDebugEnabled()){
			log.debug("calcularMontoDevolucionActualGrabarFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		int t = 0;
		double monto = 0.0d;
		
		if(this.getListaDatosCabeceraCDRTO().size() > 0){
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
				if (StringUtils.equals(elemento.getOperacion(),"D")){
					t = this.getListaDatosCabeceraCDRTO().indexOf(elemento);
					if(StringUtils.isNotBlank((String)this.getListaIdentFila().get(t))
							&& StringUtils.isNotBlank((String)this.getListaIdentFilaPadre().get(t))
								&& StringUtils.equals((String)this.getListaIdentFila().get(t), (String)this.getListaIdentFilaPadre().get(t))){
						monto = monto + Integer.parseInt(elemento.getCantidadCambia())
								*
								Double.parseDouble(elemento.getPuFactura());
					}
				}
			}
		}
		
		this.montoT = monto;
		
		return monto;
	}
	
	public void agregarFilasOfertaHTML(){
		if(log.isDebugEnabled()){
			log.debug("agregarFilasOfertaHTML");
		}
		
		this.getListaIdentFilaPadre().add(Arrays.asList(this.getValoresOfertaPOSBUSC()).get(0));
		this.getListaIdentFila().add(Arrays.asList(this.getValoresOfertaPOSBUSC()).get(0));

		/*
		for(var i=0; i < valoresOfertaPOSBUSC.length-1 ; i++){
			DatosCabeceraCDRTO oferta = new DatosCabeceraCDRTO();
			//listaCamposs[0] = "<input type='checkbox' class='selectedItemsOferta' style='border: none;' name='selectedItems' id='selectedItems' onKeyDown='return deshabilitarTab(event, this);' value='D'/>";
			oferta.setOperacion(operacion)
			listaCamposs[1] = $('strComboOperacionDeuda').value;
			listaCamposs[2] = "<input type='text' value='" + valoresOfertaCUV[i] + "' size='4' maxlength='5' onkeyup='seteaFocoCUVCambia(this, event);' onchange='completarCUV(this, 1);' onFocus='enfocar(2);' onkeypress='return jsEsNumero(event); ' name='listaCUVCambia' id='listaCUVCambia' onKeyDown='return deshabilitarTab(event, this);'/> <img id='listaPopupCuvCambia' name='listaPopupCuvCambia' src='<c:out value="${ctxPath}"/><fmt:message key="button.find.img" />' title='Buscar' style='cursor: pointer; cursor: hand'  onclick='javascript:openCodigoVentaPopupCambia(); return false;' >"	
			listaCamposs[3] = "<input type='text' value='" + valoresOfertaDESPRO[i] + "' size='26' maxlength='30' style='border: 0; background-color: transparent; font-family: Tahoma; font-size: 10px; color: #355A8F' readonly='true' name='listaDescripcionCambia' id='listaDescripcionCambia' onFocus='enfocar(3);' onKeyDown='return deshabilitarTab(event, this);'/>"
			listaCamposs[4] = "<input type='text' value='" + valoresOfertaPREPRO[i] +  "' size='3' maxlength='4' style='border: 0; background-color: transparent; font-family: Tahoma; font-size: 10px; color: #355A8F; text-align:right' readonly='true' name='listaPrecioCambia' id='listaPrecioCambia' onFocus='enfocar(4);' onKeyDown='return deshabilitarTab(event, this);' 'value='+cuv+'/>"	
			listaCamposs[5] = "<input type='text' value='" + valoresOfertaUNI[i] + "'size='3' maxlength='4' onkeyup='seteaFocoUnidadesCambia(this, event)' onchange='forzarENTER(this);' onFocus='enfocar(5);'  onkeypress='return jsEsNumero(event);' name='listaCantidadCambia' id='listaCantidadCambia' onKeyDown='return deshabilitarTab(event, this);'/>"
			listaCamposs[6] = $('strComboMotivo').value;
			listaCamposs[7] = "<input type='checkbox' onclick='copiaTexto(this);' onkeyup='seteaFocoIgualEnvio(this, event)' style='border: none;' name='listaIgualEnvio' id='listaIgualEnvio' disabled = 'true'  />";
			listaCamposs[8] = "<input type='text' size='4' maxlength='5' onkeyup='seteaFocoCUVDesea(this, event)' onchange='completarCUV(this, 2);' onkeypress='return jsEsNumero(event); ' name='listaCUVDesea' id='listaCUVDesea' onFocus='enfocar(8);' return onKeyDown='return deshabilitarTab(event, this);'/> <img id='listaPopupCuvDesea' name='listaPopupCuvDesea' src='<c:out value="${ctxPath}"/><fmt:message key="button.find.img" />' title='Buscar' style='cursor: pointer; cursor: hand' onclick='javascript:openCodigoVentaPopupDesea(); return false;'>"
			listaCamposs[9] = "<input type='text' size='26' maxlength='30' style='border: 0; background-color: transparent; font-family: Tahoma; font-size: 10px; color: #355A8F' readonly='true' name='listaDescripcionDesea' id='listaDescripcionDesea' onKeyDown='return deshabilitarTab(event, this);'/>"	
			listaCamposs[10] = "<input type='text' size='3' maxlength='4' style='border: 0; background-color: transparent; font-family: Tahoma; font-size: 10px; color: #355A8F; text-align:right' readonly='true' name='listaPrecioDesea' id='listaPrecioDesea' onKeyDown='return deshabilitarTab(event, this);'/>"
			listaCamposs[11] = "<input type='text' size='3' maxlength='4' name='listaCantidadDesea' id='listaCantidadDesea' onkeypress='return jsEsNumero(event); ' onkeyup='agregarDetalle(this,this.value,event,this.parentNode.parentNode.rowIndex);' onFocus='enfocar(9);' onKeyDown='return deshabilitarTab(event, this);'/>"	
			listaCamposs[12]= "<input type='hidden' name='listaIdentSolicPos' id='listaIdentSolicPos' />";

			//TENER CUIDADO AL MODIFICAR ESTAS LINEAS, SOBRE TODO LOS INDICES
			listaCamposs[13]= "<input type='hidden' name='listaIdentFilaPadre' id='listaIdentFilaPadre' value='"+valoresOfertaPOSBUSC[i]+"'/>";
			listaCamposs[14]= "<input type='hidden' name='listaIdentFila' id='listaIdentFila' value='"+valoresOfertaPOSOFER[i]+"'/>";
			
			addRow('prodList',listaCamposs);
			
			form.listaMotivo[posicionMotivo+i+1].value = codigoMotivoGlobal;
			
			desabilitarFilas();
			esEnterEnCantidadProductoCambia = false;
			esEnterEnCantidadProductoCambiaOnChange = false;
			
			unidades = valoresOfertaUNI[i];
			codigoVentaGlobal = valoresOfertaCUV[i];
			
			agregarValorArr();
			
		}
		//adherir 1 vacio
		addRow('prodList',listaCampos);
		desabilitarFilas();
		
		esEnterEnCantidadProductoCambia = false;
		esEnterEnCantidadProductoCambiaOnChange = false;
		
		//Reiniciamos los valores
		valoresOfertaPOSBUSC = new Array();
		valoresOfertaPOSOFER = new Array();				
		valoresOfertaCUV = new Array();
		valoresOfertaUNI = new Array();
		valoresOfertaDESPRO = new Array();
		valoresOfertaPREPRO = new Array();
		codigoMotivoGlobal = '';
		posicionMotivo = 0;
		//
	}*/
	}
	
	public String getValUnidadDeseaGrabarFinal(DatosCabeceraCDRTO elemento, int indice){
		if(log.isDebugEnabled()){
			log.debug("getValUnidadDeseaGrabarFinal");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion());
		int sumCantidad = 0;
		if(StringUtils.equals(f.getIndicadorOnline(),"S") 
			&& StringUtils.equals(f.getIndicadorValCDRLinea(),"1") 
				&& (StringUtils.equals(this.visualizaRechazo,"0") 
					|| StringUtils.equals(this.visualizaRechazo,"2"))){
    		
    		//setea cantidad
			for (DatosCabeceraCDRTO objeto : this.getListaDatosCabeceraCDRTO()) {
		 		if(StringUtils.equals(elemento.getCodigoVentaDesea(), objeto.getCodigoVentaDesea())){
		 			if(StringUtils.isNotBlank(objeto.getCantidadDesea())){
		 				sumCantidad += Integer.parseInt(objeto.getCantidadDesea());
		 			}
		 		}
			}
	 		
	 		if(StringUtils.isNotBlank(operacionesResultado.getDeseVali())){
				if(StringUtils.isNotEmpty(elemento.getCantidadDesea()) 
					&& StringUtils.isNotBlank(elemento.getCantidadDesea()) ) {
					return ajax.getValUnidadDesea(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
														f.getCodigoConsejera(),
														elemento.getListaIdentSolicPos() ,
														String.valueOf(sumCantidad), 
														varCodOperSICC, 
														operacionesResultado.getTipoOperSicc(),
														this.getIndicadorExcluirValidaciones());
				}
			}
		}
		return null;
	}
	
	/**
	 * @param f
	 * @param reclamoDigitCabec
	 * Setea los datos de la cabecera del reclamo
	 */
	private void setCabecera(ReclamoDigitadoCabecera reclamoDigitCabec) {
		if(log.isDebugEnabled()){
			log.debug("setCabecera");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		reclamoDigitCabec.setCodigoPais(f.getCodigoPais());
		reclamoDigitCabec.setNumeroDocumento(f.getNumeroCDR());
		reclamoDigitCabec.setCodigoPeriodo(f.getPeriodo());
		reclamoDigitCabec.setCodigoCliente(f.getCodigoConsejera());
		reclamoDigitCabec.setNumeroDocumentoCruce(f.getNumeroBoletaDespacho());
		reclamoDigitCabec.setTipoSolicitud(Constants.TIPO_SOLICITUD_SOC);
		reclamoDigitCabec.setCodigoSubAcceso(Constants.CODIGO_SUBACCESO_000);
		reclamoDigitCabec.setAccesoFisico(Constants.ACCESO_FISICO_01);
		reclamoDigitCabec.setEstadoProceso(Constants.ESTADO_PROCESO_01);
		reclamoDigitCabec.setCodigoTipoDocumento(Constants.TIPO_DOCUMENTO_SPVC);
		if(f.isIndicadorExpress())
			reclamoDigitCabec.setIndicadorExpress(Constants.IND_EXPRESS_ACTIVO);
		else
			reclamoDigitCabec.setIndicadorExpress(Constants.IND_EXPRESS_INACTIVO);
		
		if (f.isIndicadorCDRRechazo()){
			reclamoDigitCabec.setCodigoMotivoRechazoDef(f.getCodigoMotivoRechazoDef());//PER-SiCC-2012-0642 
			reclamoDigitCabec.setIndicadorCDRRechazo(f.isIndicadorCDRRechazo()?Constants.NUMERO_UNO:Constants.NUMERO_CERO);
			reclamoDigitCabec.setObservacionCDR(f.getObservacionCDR());
		}
		else{
			reclamoDigitCabec.setCodigoMotivoRechazoDef(null); //PER-SiCC-2012-0642 
			reclamoDigitCabec.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
			reclamoDigitCabec.setObservacionCDR(null);
		}
	}	
	
	/**
	 * @param f
	 * @param detallesList
	 * Setea los detalles del reclamo
	 */
	private void setDetalles(List detalleList) {
		if(log.isDebugEnabled()){
			log.debug("setDetalles");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		//String[]listaCodigos   = f.getListaCUVCambia();
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		String codigoOperacion = "";
		//for (int i = 0; i < listaCodigos.length; i++) {
		for(DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()){
			ReclamoDigitadoDetalle reclamoDigitDetal = new ReclamoDigitadoDetalle();
			reclamoDigitDetal.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			reclamoDigitDetal.setNumeroDocumento(f.getNumeroCDR());
			reclamoDigitDetal.setCodigoCliente(f.getCodigoConsejera());
			Map criteria = new HashMap();
			
			if(StringUtils.isNotEmpty(elemento.getOperacion())){
			OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(elemento.getOperacion()); 
			codigoOperacion = operacionesResultado.getCodigo();
			}else{
				//codigoOperacion = f.getListaOperacion()[i];
			}
			reclamoDigitDetal.setTipoReferencia(codigoOperacion);
			if(codigoOperacion.equals("C") || codigoOperacion.equals("T")){ //Cambio Producto
				criteria.put("codigoItemDuplicado", Constants.CODIGO_PRODUCTO_DUPLICADO);
				//if(f.getListaCUVCambia()[i].equals(f.getListaCUVDesea()[i]) && f.getListaCantidadCambia()[i].equals(f.getListaCantidadDesea()[i]))
				if(StringUtils.equals(elemento.getCodigoVentaCambia(),elemento.getCodigoVentaDesea())
						&& StringUtils.equals(elemento.getCantidadCambia(), elemento.getCantidadDesea())){
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_IGUAL);
				}else{
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_DIFERENTE);
				}
				reclamoDigitDetal.setTipoReferencia(service.getCodigoOperacion(criteria));
			}
			if(codigoOperacion.equals("S") || codigoOperacion.equals("P")){ //Cambio Premio
				criteria.put("codigoItemDuplicado", Constants.CODIGO_PREMIO_DUPLICADO);
				//if(f.getListaCUVCambia()[i].equals(f.getListaCUVDesea()[i]) && f.getListaCantidadCambia()[i].equals(f.getListaCantidadDesea()[i]))
				if(StringUtils.equals(elemento.getCodigoVentaCambia(),elemento.getCodigoVentaDesea())
						&& StringUtils.equals(elemento.getCantidadCambia(), elemento.getCantidadDesea())){
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_IGUAL);				
				}else{
					criteria.put("indicadorIgual", Constants.REC_INDICADOR_DIFERENTE);
				}
				reclamoDigitDetal.setTipoReferencia(service.getCodigoOperacion(criteria));
			}	
			//reclamoDigitDetal.setCodigoOperador(f.getListaOperacion()[i]);
			reclamoDigitDetal.setCodigoPeriodo(f.getPeriodo());
			reclamoDigitDetal.setMotivoSPV(elemento.getMotivo());
			reclamoDigitDetal.setCodigoVentaDevuelve(elemento.getCodigoVentaCambia());
			reclamoDigitDetal.setCodigoVentaDesea(elemento.getCodigoVentaDesea());
			reclamoDigitDetal.setCantidadProductosDevuelve(elemento.getCantidadCambia());
			reclamoDigitDetal.setCantidadProductosDesea(elemento.getCantidadDesea());
			reclamoDigitDetal.setEstadoProceso(Constants.ESTADO_PROCESO_01);
			reclamoDigitDetal.setCodigoTipoDocumento(Constants.TIPO_DOCUMENTO_SPVD);	
			reclamoDigitDetal.setCodigoMotivoRechazoDef(f.getCodigoMotivoRechazoDef()); //PER-SiCC-2012-0642 
			detalleList.add(reclamoDigitDetal);
		}
	}
	
	
	/*@Override
	public String setValidarMantenimiento() {
		if(log.isDebugEnabled()){
			log.debug("setValidarMantenimiento");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		int tamanio = this.getListaDatosCabeceraCDRTO().size();
		indiceValidaGrabarFinal = 0;
		
		if(tamanio > 0){
			//=================== VALIDACIONES GRABAR FINAL - INICIO ===================
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
				
				//VALIDA COMBO OPERACION
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = ejecutarFuncionesGrabarFinal(elemento);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CUV CAMBIA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = setFocusCUVCambiaGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CANTIDAD CAMBIA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = seteaFocoUnidadesCambiaGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CUV DESEA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = setFocusCUVDeseaGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CANTIDAD DESEA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = agregarDetalleGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				indiceValidaGrabarFinal++;
			}			
			//=================== VALIDACIONES GRABAR FINAL - FINAL ===================
			
			if(StringUtils.equals(f.getMuestraIndicador(),"1")){
				if(f.isIndicadorCDRRechazo()){
					if(StringUtils.isBlank(f.getCodigoMotivoRechazoDef())){
						return this.getResourceMessage("mantenimientoRECDigitacionCDRForm.message.motivo.obligatorio");
					}
				}
			}
			
			//Validar trueques			
			if(StringUtils.equals(f.getIndicadorLinea18(),"1")){
				double valorTruequeCambia = calcularMontoTrueque("CA");
				double valorTruequeDesea = calcularMontoTrueque("DE");
				double diferencia = 0.0d;
				if(StringUtils.equals(f.getIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(),"1")){
					//SIN ABS
					diferencia = valorTruequeCambia - valorTruequeDesea;
				}else{
					//CON ABS
					diferencia = Math.abs(valorTruequeCambia - valorTruequeDesea);
				}
				if(diferencia > NumberUtils.toDouble(f.getValorDesviacionPrecioUnitarioTrueque())){
					return "Diferencia en trueques excede lo permitido " + f.getValorDesviacionPrecioUnitarioTrueque() + " (Total Cambia: " + valorTruequeCambia + " / Total Desea: " + valorTruequeDesea + ")";
				}
			}
			
			if(StringUtils.isBlank(f.getNumeroCDR()) && StringUtils.isEmpty(f.getNumeroCDR())){
				this.valorFoco = "1";
				return "Numero de CDR es requerido";
			}

			
		}else{
			return "Debe de ingresar como mÃ­nimo una linea.";
		}
		
		return "";
	}*/
	
	
	@Override
	public String setValidarConfirmar(String accion) {
		if(log.isDebugEnabled()){
			log.debug("setValidarConfirmar");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		int tamanio = this.getListaDatosCabeceraCDRTO().size();
		indiceValidaGrabarFinal = 0;
		
		if(tamanio > 0){
			//=================== VALIDACIONES GRABAR FINAL - INICIO ===================
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
				
				//VALIDA COMBO OPERACION
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = ejecutarFuncionesGrabarFinal(elemento);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CUV CAMBIA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = setFocusCUVCambiaGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CANTIDAD CAMBIA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = seteaFocoUnidadesCambiaGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CUV DESEA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = setFocusCUVDeseaGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				//VALIDA CANTIDAD DESEA
				errorValidaGrabarFinal = false;
				errorValidaGrabarFinal = agregarDetalleGrabarFinal(elemento, indiceValidaGrabarFinal);
				if(errorValidaGrabarFinal) break;
				
				indiceValidaGrabarFinal++;
			}			
			//=================== VALIDACIONES GRABAR FINAL - FINAL ===================
			
			if(StringUtils.equals(f.getMuestraIndicador(),"1")){
				if(f.isIndicadorCDRRechazo()){
					if(StringUtils.isBlank(f.getCodigoMotivoRechazoDef())){
						return this.getResourceMessage("mantenimientoRECDigitacionCDRForm.message.motivo.obligatorio");
					}
				}
			}
			
			//Validar trueques			
			if(StringUtils.equals(f.getIndicadorLinea18(),"1")){
				double valorTruequeCambia = calcularMontoTrueque("CA");
				double valorTruequeDesea = calcularMontoTrueque("DE");
				double diferencia = 0.0d;
				if(StringUtils.equals(f.getIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(),"1")){
					//SIN ABS
					diferencia = valorTruequeCambia - valorTruequeDesea;
				}else{
					//CON ABS
					diferencia = Math.abs(valorTruequeCambia - valorTruequeDesea);
				}
				if(diferencia > NumberUtils.toDouble(f.getValorDesviacionPrecioUnitarioTrueque())){
					return "Diferencia en trueques excede lo permitido " + f.getValorDesviacionPrecioUnitarioTrueque() + " (Total Cambia: " + valorTruequeCambia + " / Total Desea: " + valorTruequeDesea + ")";
				}
			}
			
			if(StringUtils.isBlank(f.getNumeroCDR()) && StringUtils.isEmpty(f.getNumeroCDR())){
				this.valorFoco = "1";
				return "Numero de CDR es requerido";
			}

			
		}else{
			return "Debe de ingresar como mÃ­nimo una linea.";
		}
		
		return "";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setSaveAttributes");
		}
		
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		ReclamoDigitadoCabecera reclamoDigitCabec = new ReclamoDigitadoCabecera();
		List detallesList = new ArrayList();
		boolean respuesta = false;
		
		//Carga todos los datos de la cabecera
		setCabecera(reclamoDigitCabec);
		//Carga los detalles
		if(this.mantenimientoRECDigitacionCDRDetallesDigitadosList.size() > 0)
			detallesList = this.mantenimientoRECDigitacionCDRDetallesDigitadosList;
		// Si es 0, es un nuevo registro, si es diferente de 0, es una modificacion
		int indicadorModificar = detallesList.size();			
		setDetalles(detallesList);
		// Extraemos el usuario de la sesiÃ³n
        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
        String indicadorOnline = f.getIndicadorOnline();
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		String indicadorCDR = service.getIndicadorCDRRechazo(criteria);

		//Estraemos el Indicador Online
		if (indicadorOnline!=null && indicadorOnline.equals("S")){
			
			String codigoDocumento=Constants.STO_TIPO_DOCUMENTO_SPVC;
			
			Map params = new HashMap();
			
			params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			params.put("codigoDocumento",codigoDocumento);
			
			
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			
			TipoDocumentoDigitadoPK tipoDocumentoDigitadoPK = new TipoDocumentoDigitadoPK(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.STO_TIPO_DOCUMENTO_SPVC);
			ProcesoSTOService stoService = (ProcesoSTOService)getBean("spusicc.procesoSTOService");
			String numLoteSTO=stoService.updateLoteSTO(tipoDocumentoDigitadoPK);
			
			params.put("usuario",usuario);
			params.put("numLoteSTO",numLoteSTO);
			
			
			reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_CC);
			
			// Inserta la informacion en las tablas temporales y consolida la informacion en tablas de consolidado
			service.insertReclamoDigitadoOnline(reclamoDigitCabec, detallesList, params);
					
			
			
			//Ejecutando Validaciones STO
			params.put("codPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
			params.put("codTipoDocu", codigoDocumento);			
			params.put("numLote", numLoteSTO);
			
			params.put("codigoPeriodo", "");
			
			
			ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
			
			List lista = procesoSTOService.getDocumentoDigitadoPKByLote(params);
			
			List listaSTO = new ArrayList();
			for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
	    		 DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
	    		 GestionDocumento gestionDocumento = new GestionDocumento();
	    		 gestionDocumento.setCodigoPais(documentoDigitadoPK.getCodPais());
	    		 gestionDocumento.setNumeroDocumento(documentoDigitadoPK.getSecNumeDocu());
	    		 gestionDocumento.setLote(documentoDigitadoPK.getNumLote());
	    		 gestionDocumento.setDocumento(documentoDigitadoPK.getCodTipoDocu());
	    		 listaSTO.add(gestionDocumento);
	    		 
	  		}		
			
			ProcesoSTOExecutionService execService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
			
			AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),codigoDocumento,Constants.STO_ACCI_VALI_ON_LINE);
			execService.execute(accionTipoDocumento,params,listaSTO);
			
			ProcesoSTOLevantamientoErroresValidacionService serviceGestion = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");
			
			//Verificamos si  Existen Errores STO En caso existan los mostramos
			
			params.put("tipoDocumento",codigoDocumento);
			StringBuilder mensajeErrors = new StringBuilder();
			List listaErroresValidacion =  serviceGestion.getGestionDocumentoList(params);
			if (listaErroresValidacion.size()>0){
				mensajeErrors.append(this.getResourceMessage("mantenimientoRECDigitacionCDRForm.errors.gestion"));
				for (Iterator iterator = listaErroresValidacion.iterator(); iterator.hasNext();) {
		    		 GestionDocumento gestionDocumento = (GestionDocumento) iterator.next();
		    		 mensajeErrors.append(this.getResourceMessage("errors.detail") + " " + gestionDocumento.getDesValidacionLarga());
		  		}				
			}else{
				if(Constants.NUMERO_UNO.equals(indicadorCDR)){
					this.addInfo("Info:", this.getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrarCCS.CDR"));
					respuesta = true;
				}else{
					this.addInfo("Info:", this.getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrarCCS"));
					respuesta = true;
				}
			}
		}
		else{
			reclamoDigitCabec.setIndicadorOrigen(Constants.REC_DIGI_CDRS_ORIG_DIG);
			service.insertReclamoDigitado(reclamoDigitCabec, detallesList, usuario, indicadorModificar);
			this.addInfo("Info:", this.getResourceMessage("mantenimientoRECDigitacionCDRForm.msj.registrar"));
			respuesta = true;
		}
		f.reset();
	    //f.setIndicadorModifica("0");
	    

	    /* INI JR PER-SiCC-2012-0304 */
	    f.setObservacionCDR(null);
    	//f.setIndicadorCDRRechazo(Constants.NUMERO_CERO);
	    f.setIndicadorCDRRechazo(false);
    	/* FIN JR PER-SiCC-2012-0304 */
    	
    	// PER-SiCC-2012-0642 
    	f.setCodigoMotivoRechazoDef(null);
    	
    	//hibilita el boton grabar
    	//f.setEditable(true);
    	limpiarSinAction();
    	
    	this.setViewAtributes();
    	
		return false;
	}
	
	
	
	public double calcularMontoTrueque(String columna){
		if(log.isDebugEnabled()){
			log.debug("calcularMontoTrueque");
		}
		double monto = 0.0d;
		if(StringUtils.equals(columna,"CA")){
			for(DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()){
				if(StringUtils.equals(elemento.getOperacion(),"T")){
					monto = monto + NumberUtils.toDouble(elemento.getCantidadCambia()) * NumberUtils.toDouble(elemento.getPuFactura());
				}
			}
		}else{
			if(StringUtils.equals(columna,"DE")){
				for(DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()){
					if(StringUtils.equals(elemento.getOperacion(),"T")){
						monto = monto + NumberUtils.toDouble(elemento.getCantidadDesea()) * NumberUtils.toDouble(elemento.getPrecioDesea());
					}
				}
			}
		}
		return monto;
	}
	
	public boolean getValUnidadReclamoGrabarFinal(String variable, int indice, DatosCabeceraCDRTO elemento){
		if(log.isDebugEnabled()){
			log.debug("getValUnidadReclamoGrabarFinal");
		}
		this.opcion = variable;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		int sumCantidad = 0;
		//setea cantidad
		
		if(this.getArrUnidades().size() > 0){
			for (int i = 0; i < this.getArrUnidades().size(); i++) {				
				if(StringUtils.equals((String)this.getArrCodigoVenta().get(i), elemento.getCodigoVentaCambia())){
					if(StringUtils.isNotBlank((String)this.getArrUnidades().get(i))){
						sumCantidad += Integer.parseInt((String)this.getArrUnidades().get(i));
					}
				}
			}
		}
		
		if(StringUtils.equals(f.getIndicadorOnline(),"S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(),"1")
				&& (StringUtils.equals(this.getVisualizaRechazo(),"0") 
					|| StringUtils.equals(this.getVisualizaRechazo(),"2"))){
			if(StringUtils.equals(operacionesResultado.getCambVali(),"PE")){
				if(StringUtils.isNotBlank(elemento.getCantidadCambia())
					&& StringUtils.isNotEmpty(elemento.getCantidadCambia())
						&& StringUtils.isNotBlank(elemento.getListaIdentSolicPos())
							&& StringUtils.isNotEmpty(elemento.getListaIdentSolicPos())){
					String data = ajax.getValUnidadReclamo(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
															f.getCodigoConsejera(),
															this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),
															String.valueOf(sumCantidad), 
															varCodOperSICC, 
															operacionesResultado.getTipoOperSicc(),
															this.getIndicadorExcluirValidaciones());
					return this.loadMensajeGrabarFinalCallBack(data);
				}
			}
		}
		return false;
	}
	
	protected boolean loadMensajeGrabarFinalCallBack(String data){
		if(log.isDebugEnabled()){
			log.debug("loadMensajeGrabarFinalCallBack");
		}
		if(StringUtils.isNotBlank(data)
			|| StringUtils.isNotEmpty(data)){
//			this.setMensajeLocal(data);
//			mostrarDialogo();
			this.addError("Error:", data);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Inits the onload.
	 */
	public void initOnload(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'init' method");
		}
		//this.initESC();		
		this.inicializa();
		this.existeFlagRechazo();
		this.inicializaIndicadorRechazo();
	}
	
	/**
	 * Inicializa.
	 */
	public void inicializa(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'inicializa' method");
		}
		this.activarHotkeyEstandar = false;
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotBlank(f.getNumeroBoletaDespacho())
				&& StringUtils.isNotEmpty(f.getNumeroBoletaDespacho())){
			this.setCargo("1");
			//this.setearConsultora();
			//this.seteaFocoNumeroBoletaDespacho();
		}else{
			this.setCargo("0");
		}		
	}
	
	public void existeFlagRechazo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'existeFlagRechazo' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.equals(f.getMuestraIndicador(), "1")){
			if(f.isIndicadorCDRRechazo()){
				f.setIndicadorCDRRechazo(true);
				this.visualizaRechazo = "1";
			}else{
				f.setIndicadorCDRRechazo(false);
				this.visualizaRechazo = "2";
			}
		}else{
			this.visualizaRechazo = "0";
		}
	}
	
	public void inicializaIndicadorRechazo(ValueChangeEvent e){
		if(log.isDebugEnabled()){
			this.inicializaIndicadorRechazo();
		}
	}
	
	public void inicializaIndicadorRechazo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'inicializaIndicadorRechazo' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.equals(f.getMuestraIndicador(), "1")){
			if(f.isIndicadorCDRRechazo()){
				this.desabilitaCodigoMotivoRechazoDef = false;
				this.deshabilitaObservacionCDR = false;
				f.setCodigoMotivoRechazoDef("1");
				borrarTodo();
				this.existeFlagRechazo();
			}else{
				this.desabilitaCodigoMotivoRechazoDef = true;
				this.deshabilitaObservacionCDR = true;
				f.setCodigoMotivoRechazoDef("");
				f.setObservacionCDR("");
				borrarTodo();
				this.existeFlagRechazo();
			}
		}
	}
	
	public void borrarTodo(){
		if(log.isDebugEnabled()){
			log.debug("borrarTodo");
		}
		//TODO
		int checkelimina = 3;
		String codVenta = "";
		String codOper = "";
		String dato = "";
		List arrVent = new ArrayList();
		List arrOpe = new ArrayList();
		int cont = 0;
		int t = 0;
		if(checkelimina > 0){
			for (t = 0; t < checkelimina; t++) {
				codVenta = 	this.getDatosCabeceraCDRTO().getCodigoVentaCambia();
        		/*dato= form.listaOperacion[t+2-cont-1].value.split("|");   
          		codOper = dato[0];
				//alert("t+2-cont-1 "+(t+2-cont-1)+" codVenta "+codVenta+ " codOper "+codOper);
				arrVent.push(codVenta);
				arrOpe.push(codOper);
				deleteRow('prodList',t+2-cont);*/
        	    cont = cont + 1;
			}
		}
		
		/*var form =document.forms[0];
		var checkelimina = form.selectedItems;
		var codVenta = '';
		var codOper = '';
		var dato='';
		var arrVent = new Array();
		var arrOpe = new Array();
		if(checkelimina.length != null) {
        	if (checkelimina.length>0) {             	                  
            	cont = 0;
            	var long = checkelimina.length;
                for (t=0; t<long-1; t++){                                         	
                	                   	      
                		codVenta = 	form.listaCUVCambia[t+2-cont-1].value;
                		dato=form.listaOperacion[t+2-cont-1].value.split("|");   
                  		codOper = dato[0];
						//alert("t+2-cont-1 "+(t+2-cont-1)+" codVenta "+codVenta+ " codOper "+codOper);
						arrVent.push(codVenta);
						arrOpe.push(codOper);
						deleteRow('prodList',t+2-cont);
                	    cont = cont + 1;                   		
                   	                   	
                }
                eliminarArrays(arrVent,arrOpe);
                for (t=0; t<form.selectedItems.length; t++){
                	form.selectedItems[t].checked = false;
                }                                            
            }
        }*/
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		this.activarHotkeyEstandar = false;
		
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		if(this.getParametrosPantalla().containsKey("indicadorOnline")){
			f.setIndicadorOnline((String)this.getParametrosPantalla().get("indicadorOnline"));
		}else{
			f.setIndicadorOnline("");
		}

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  

        MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = serviceFact.getControlFacturacionById(criteria);
        f.setPeriodoActivo(controlFacturacion.getCodigoPeriodo());

        this.mantenimientoRECDigitacionCDRDetallesDigitadosList = new ArrayList();

        f.setIndicadorModifica("0");


		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		this.recOperacionParametrosList = new ArrayList();
		this.recOperacionParametrosList = service.getParametrosOperacionesReclamos();
		
		if(!this.getRecOperacionParametrosList().isEmpty()){
			if(this.getRecOperacionParametrosList().size()>0){
				for (Object item : this.getRecOperacionParametrosList()) {
					this.getArrCodigoOperacion().add(((ParametrosOperacionesReclamos)item).getCodigoOperacion());
					this.getArrIndicadorCUVCambiaObligatorio().add(((ParametrosOperacionesReclamos)item).getIndicadorCUVCambiaObligatorio());
					this.getArrIndicadorCUVDeseaObligatorio().add(((ParametrosOperacionesReclamos)item).getIndicadorCUVDeseaObligatorio());
					this.getArrIndicadorValidacionCUVCambia().add(((ParametrosOperacionesReclamos)item).getIndicadorValidacionCUVCambia());
					this.getArrIndicadorValidacionCUVDesea().add(((ParametrosOperacionesReclamos)item).getIndicadorValidacionCUVDesea());
					this.getArrPopupCambia().add(((ParametrosOperacionesReclamos)item).getPopupCambia());
					this.getArrPopupDesea().add(((ParametrosOperacionesReclamos)item).getPopupDesea());
					this.getArrIndicadorValidarCentroServicio().add(((ParametrosOperacionesReclamos)item).getIndicadorValidarCentroServicio());
				}
			}
		}
		

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		Map criteriaMinimo = new HashMap();
    	criteriaMinimo.put("codigoPais",f.getCodigoPais());
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_DEVOLUCION);
    	f.setIndicadorValidaDevolucion(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));
    	
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_FALTANTES);
    	f.setIndicadorValidaFaltantes(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));
    	
    	criteriaMinimo.put("codigoParametro",Constants.REC_INDICADOR_VALIDA_CAMBIOS);
    	f.setIndicadorValidaCambios(procesoSTOEjecucionValidacionesService.getParametroSTO(criteriaMinimo));

    	//Se obtiene el parametro de indicador de pedido chequeado, solo para la opcion en linea
    	if (StringUtils.isNotBlank(f.getIndicadorOnline())
    			&& StringUtils.isNotEmpty(f.getIndicadorOnline())
    			&& StringUtils.equals(f.getIndicadorOnline(), "S")){
    		String sIndPedido = service.getIndicadorPedidoChequeado();
        	f.setIndicadorPedidoChequeado(sIndPedido);	
    	}
    	f.setNumeroCDR("");
    	f.setNumeroBoletaDespacho("");
    	f.setCodigoConsejera("");
    	f.setNombreConsejera("");
    	f.setPeriodo("");
    	f.setZona("");

    	/* INI JR PER-SiCC-2012-0304 */
    	//Obteniendo el parametro para mostra el Rechazo CDR
    	f.setMuestraIndicador(service.getIndicadorCDRRechazo(criteria));
    	f.setObservacionCDR(null);
    	f.setIndicadorCDRRechazo(Constants.NUMERO_CERO=="0"?false:true);
    	//this.visualizaRechazo = Constants.NUMERO_CERO;
    	/* FIN JR PER-SiCC-2012-0304 */
    	
    	f.setPeriodoCDR("");
    	criteria.put("codigoParametro", Constants.STO_IND_CDR_LINEA);
    	
    	String valorParametro = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorValCDRLinea(valorParametro);
    	
    	//Obteniendo el dato de Envio de BR
		Map criteria1 = new HashMap();
		criteria1.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria1.put("codigoParametro", Constants.STO_IND_VAL_ACEP_CDR);
		String valor = service.getSTOParametroGeneralOCR(criteria1);
		
		f.setIndicadorValorAceptaCDR(valor);
    	
    	// PER-SiCC-2012-0642 
    	this.lstCodMotRechazo = new ArrayList();
    	this.lstCodMotRechazo = service.getCodigoMotivoRechazo();
    	f.setCodigoMotivoRechazoDef(null);
    	
    	f.setUsuario(this.getmPantallaPrincipalBean().getCodigoUsuario());
    	
    	//Obtenemos el parametro de validacion de oferta
    	criteria.put("codigoParametro", Constants.STO_IND_DEV_OFERTA);
    	String indicadorDevolucionOferta = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	
    	f.setIndicadorDevolucionOferta(StringUtils.isBlank(indicadorDevolucionOferta)? Constants.NUMERO_CERO:indicadorDevolucionOferta);
    	//
    	
    	//Obtenemos los parametros Adicionales para validar TRUEQUES
    	criteria.put("codigoParametro", Constants.STO_IND_CDR_LINEA_18);
    	String indicadorLinea18 = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);    	
    	f.setIndicadorLinea18(StringUtils.isBlank(indicadorLinea18)?"":indicadorLinea18);
    	
    	criteria.put("codigoParametro", Constants.STO_DESV_TRQ);
    	String valorDesviacionPrecioUnitarioTrueque = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setValorDesviacionPrecioUnitarioTrueque(StringUtils.isBlank(valorDesviacionPrecioUnitarioTrueque)?"":valorDesviacionPrecioUnitarioTrueque);
    	//
    	
    	//Obtenemos el parametro de validacion PRODUCTO QUE CAMBIA IGUAL DE PORDUCTO QUE DESEA
    	criteria.put("codigoParametro", Constants.STO_IND_CAMB_IGUA);
    	String indicadorProductoCambiaIgualDesea = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorProductoCambiaIgualDesea(StringUtils.isBlank(indicadorProductoCambiaIgualDesea)?"":indicadorProductoCambiaIgualDesea);
    	//
    	
    	//Obtenemos el parametro codigo de cliente que luego sera enviado en la busqueda de documentos de referencia (Nro perido)
    	String codigoCliente = f.getCodigoClienteDocumentoReferencia();
    	f.setCodigoClienteDocumentoReferencia(codigoCliente);
    	//
    	
    	//Obtenemos el parametro de operacion de TRUEQUE con valor absoluto
    	criteria.put("codigoParametro", Constants.STO_DESV_TRQ_OPER);
    	String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto = procesoSTOEjecucionValidacionesService.getParametroSTO(criteria);
    	f.setIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(StringUtils.isBlank(indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto)?"":indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto);
    	
		this.loadCombos();
		this.mostrarBotonBuscarDOCREF = true;
		this.mostrarBotonBuscarCUVCambia = true;
		this.mostrarBotonBuscarCUVDesea = true;
		this.valorFoco = "1";
		this.inicializaComponentes(true);
		
		this.mostrarPanelDigitacionCdr = true;
		
		this.mostrarBotonSave = false;
		this.mostrarBotonLimpiar = false;
		this.mostrarBotonSalir = false;
		
	}
	
	/**
	 * Load combos.
	 */
	private void loadCombos() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'loadCombos' method");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		Map criteria = new HashMap();
		Map resultado = new HashMap();
		MantenimientoRECDigitacionCDRService service = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		this.listaOperaciones = service.getOperacionesReclamos(criteria);
		this.listaMotivo = service.getMotivosReclamos(criteria);
		
		Collections.sort(listaOperaciones, new Comparator<OperacionesResultado>() {
			@Override
			public int compare(OperacionesResultado o1, OperacionesResultado o2) {
				int val = o1.getDescripcion().compareTo(o2.getDescripcion());
				return val;
			}
		});
		
		for (int i = 0; i < listaOperaciones.size(); i++) {					
			//Base base = new Base(); Se cambio por OperacionesREsultado donde se encuentra mas data
			OperacionesResultado base=(OperacionesResultado)listaOperaciones.get(i);

			if(StringUtils.equals(base.getCodigo(), "D")){
				OperacionesResultado baseOfertas = new OperacionesResultado();
				try {
					BeanUtils.copyProperties(baseOfertas, base);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}else{
				if(StringUtils.equals(base.getCodigo(), "N")){
					try {
						BeanUtils.copyProperties(this.devolucionEspecial, base);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			resultado.put(String.valueOf(i),(OperacionesResultado)listaOperaciones.get(i));		
		}
		
		List<Entry<String, OperacionesResultado>> entries = new ArrayList<Entry<String, OperacionesResultado>>(resultado.entrySet());
		Collections.sort(entries, new Comparator<Entry<String, OperacionesResultado>>() {
		    public int compare(Entry<String, OperacionesResultado> e1, Entry<String, OperacionesResultado> e2) {
		        return ((OperacionesResultado)e1.getValue()).getDescripcion().compareTo(((OperacionesResultado)e2.getValue()).getDescripcion());
		    }
		});
		
		Map<String, OperacionesResultado> orderedMap = new LinkedHashMap<String, OperacionesResultado>();
		for (Entry<String, OperacionesResultado> entry : entries) {
		    orderedMap.put(entry.getKey(), entry.getValue());
		}
		this.operaciones = orderedMap;
	}
		
	/**
	 * Ceros izquierda.
	 *
	 * @param query the query
	 * @return the list
	 */
	public String cerosIzquierda(String query, int limite){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		return StringUtils.leftPad(query, limite,"0");
	}
	
	/**
	 * Ceros izquierda.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<String> cerosIzquierdaCVC(String query){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cerosIzquierda' method");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		List<String> listado = new ArrayList<String>();
		listado.add(StringUtils.leftPad(query, 5,"0"));
		this.getDatosCabeceraCDRTO().setCodigoVentaCambia(StringUtils.leftPad(query, 5,"0"));
		return listado;
	}
	
	/**
	 * Busca documento referencia.
	 *
	 * @param e the e
	 */
	public void buscaDocumentoReferencia(ActionEvent e){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'buscaDocumentoReferencia' method");
		}
	}
	
	/**
	 * Changed operacion.
	 */
	public void changedOperacion(){
		if (log.isDebugEnabled()) {
			log.debug("changedOperacion");
		}
		
		/*if(!this.()){
			this.inicializaComponentes(true);
		}else{
			this.mostrarDialogo();
		}*/
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		this.getDatosCabeceraCDRTO().setDesOperacion(operacionesResultado.getDescripcion());
		
		bloquearComponentes();
		ejecutarFunciones("1");
		this.valorFoco = "4";
		log.debug("valor del foco: "+ this.getValorFoco());
	}
	
	protected void bloquearComponentes(){
		if(log.isDebugEnabled()){
			log.debug("bloquearComponentes");
		}
		
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		String codigo = operacionesResultado.getCodigo();
		
		desbloquearTodo();

		//Limpiamos todos los valores anteriores
		
		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
		this.getDatosCabeceraCDRTO().setProductoCambia("");
		this.getDatosCabeceraCDRTO().setPuFactura("");
		this.getDatosCabeceraCDRTO().setCantidadCambia("");
		this.getDatosCabeceraCDRTO().setMotivo(((Base)this.getListaMotivo().get(0)).getCodigo());
	 	this.getDatosCabeceraCDRTO().setIgualEnvio(false);
		
	 	this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
	 	this.getDatosCabeceraCDRTO().setProductoDesea("");
	 	this.getDatosCabeceraCDRTO().setPrecioDesea("");
	 	this.getDatosCabeceraCDRTO().setCantidadDesea("");
	 	
	 	//Desabilitamos los demas controles segun el tipo de operacion	 	
	 	this.mostrarBotonBuscarCUVCambia = true;
	 	this.mostrarBotonBuscarCUVDesea = true;

		if(StringUtils.equals(codigo, "D")){
			this.deshabilitarEnvio = true;
			this.setReadOnlyCUVDesea(true);
			this.setReadOnlyCantidadDesea(true);
			this.mostrarBotonBuscarCUVDesea = false;
			
			this.setValidaCodigoResultado(false);
		}
		
		if(StringUtils.equals(codigo, "G") 
				|| StringUtils.equals(codigo, "F") 
					|| StringUtils.equals(codigo, "H") 
						|| StringUtils.equals(codigo, "F3") 
							|| StringUtils.equals(codigo, "F4") 
								|| StringUtils.equals(codigo, "G3") 
									|| StringUtils.equals(codigo, "G4") 
										|| StringUtils.equals(codigo, "XA") 
											|| StringUtils.equals(codigo, "XI")
												|| StringUtils.equals(codigo, "XM") 
													|| StringUtils.equals(codigo, "XP")){
			this.setDeshabilitarEnvio(true);
			this.setReadOnlyCUVDesea(true);
			this.setReadOnlyCantidadDesea(true);
			this.setMostrarBotonBuscarCUVDesea(false);
			this.setValidaCodigoResultado(true);
		}
		
		if(StringUtils.equals(codigo, "E") || StringUtils.equals(codigo, "A")){
			this.deshabilitarEnvio = true;
			this.setValidaCodigoResultado(false);
		}
		
		//Verificamos si son obligatorios o no los campos, segun eso habilitamos o desabilitamos
		ParametrosOperacionesReclamos objeto = this.obtieneIndexRecOperacionParametrosList(codigo);

		if(StringUtils.equals(objeto.getIndicadorCUVCambiaObligatorio(),"NO") 
				&& !StringUtils.equals(codigo, "T")){
			this.setReadOnlyCUVCambia(true);
			this.setReadOnlyCantidadCambia(readOnlyCantidadDesea);
			this.mostrarBotonBuscarCUVCambia = false;
		}
		
		if(StringUtils.equals(objeto.getIndicadorCUVDeseaObligatorio(), "NO")
				&& !StringUtils.equals(codigo, "T")){
			this.setReadOnlyCUVDesea(true);
			this.setReadOnlyCantidadDesea(true);
			this.mostrarBotonBuscarCUVDesea = false;
			this.setDeshabilitarEnvio(true);
		}
	}
	
	private void desbloquearTodo(){
		if(log.isDebugEnabled()){
			log.debug("desbloquearTodo");
		}
		this.inicializaComponentes(true);
		this.setReadOnlyCUVCambia(false);
		this.setReadOnlyCantidadCambia(false);
		this.setDeshabilitarEnvio(false);
		this.setReadOnlyCUVDesea(false);
		this.setReadOnlyCantidadDesea(false);
		this.setValidaCodigoResultado(false);
	}
	
	/**
	 * Obtiene index rec operacion parametros list.
	 *
	 * @return the parametros operaciones reclamos
	 */
	public ParametrosOperacionesReclamos obtieneIndexRecOperacionParametrosList(String codigo){
		if (log.isDebugEnabled()) {
			log.debug("obtieneIndexRecOperacionParametrosList");
		}
		ParametrosOperacionesReclamos resultado = new ParametrosOperacionesReclamos();
		List local = new ArrayList();
		if(!this.getRecOperacionParametrosList().isEmpty()
				&& this.getRecOperacionParametrosList().size()>0){
			local = this.getRecOperacionParametrosList();
			resultado = (ParametrosOperacionesReclamos)CollectionUtils.find(local, new BeanPredicate("codigoOperacion", new EqualPredicate(codigo)));			
		}		
		return resultado;
	}
		
	private boolean existeCodigoOperacionInMapOperaciones(String codigoOperacion){
		if(log.isDebugEnabled()){
			log.debug("existeCodigoOperacionInMapOperciones");
		}
		int i = 0;
		boolean resultado = false;
		
		OperacionesResultado aux = (OperacionesResultado)this.getListaOperaciones().get(Integer.parseInt(codigoOperacion));
		
		for(OperacionesResultado elemento : this.getOperaciones().values()){
			if(StringUtils.equals(elemento.getCodigo(),aux.getCodigo())){
				i++;
			}
		}
		
		if(i > 0){
			resultado = true;
		}
		return resultado;
	}
	
	public Base obtenerMotivoFromCollectionMotivo(String codigo){
		if(log.isDebugEnabled()){
			log.debug("obtenerMotivoFromCollectionMotivo");
		}
		return (Base)CollectionUtils.find(this.getListaMotivo(), new BeanPredicate("codigo", new EqualPredicate(codigo)));
	}
	
	/**
	 * Obtener codigo mapa operaciones.
	 *
	 * @param codigo the codigo
	 * @return the operaciones resultado
	 */
	public OperacionesResultado obtenerOperacionFromMapaOperaciones(String codigo){
		if (log.isDebugEnabled()) {
			log.debug("obtenerOperacionFromMapaOperaciones");
		}
		OperacionesResultado operacionesResultado = new OperacionesResultado();
		if(StringUtils.isNotBlank(codigo) 
			&& StringUtils.isNotEmpty(codigo)){			
			operacionesResultado = (OperacionesResultado) this.getOperaciones().get(codigo);
		}
		return operacionesResultado;
	}
	
	/**
	 * Inicializa componentes.
	 */
	private void inicializaComponentes(boolean valor){
		if (log.isDebugEnabled()) {
			log.debug("inicializaComponentes");
		}
		this.deshabilitarEnvio = valor?false:true;
		this.mostrarBotonBuscarCUVCambia = valor?true:false;
		this.mostrarBotonBuscarCUVDesea = valor?true:false;
	}
	
	public void seteaFocoCUVDesea(){
		if(log.isDebugEnabled()){
			log.debug("seteaFocoCUVDesea");
		}
		this.setFocusCUVDesea();
	}
	
	public boolean setFocusCUVDesea(){
		if(log.isDebugEnabled()){
			log.debug("setFocusCUVDesea");
		}
		MantenimientoRECDigitacionCDRForm form = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		
		int cont = 0;
		int intStrLen = 0;
		int intStrMaxLen = 0;
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String,String> params = context.getExternalContext().getRequestParameterMap();
	    String valor = params.get("pflagOperacionTrueque");
	    String strLen = params.get("cadLen");
	    String strMaxLen = params.get("cadMaxLen");
	    String valorCUVdesea = params.get("valor");
	    
	    if(StringUtils.isNotEmpty(strLen)
			&& StringUtils.isNotBlank(strLen)){
	    	intStrLen = Integer.parseInt(strLen);
	    }
	    
	    if(StringUtils.isNotEmpty(strMaxLen)
				&& StringUtils.isNotBlank(strMaxLen)){
	    	intStrMaxLen = Integer.parseInt(strMaxLen);
		}	
			
		if(intStrLen < intStrMaxLen){
			//autocompletar con ceros a la izquierda
			this.getDatosCabeceraCDRTO().setCodigoVentaDesea(this.cerosIzquierda(valorCUVdesea, intStrMaxLen));
			strLen = strMaxLen;
		}else{
			this.getDatosCabeceraCDRTO().setCodigoVentaDesea(valorCUVdesea);
		}
		
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		int index = this.getListaOperaciones().indexOf(operacionesResultado);
		
		//Evalua parametro que indica si el CUV es obligatorio o no
		if(StringUtils.equals((String)this.getArrCodigoOperacion().get(index),"NO")){
			//this.valorFoco = "5";
		}else{
			if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
				return false;
			}
		}
		
		//Evalua el tipo de validacion parametrizada para el tipo de operacion seleccionado
		int index2 = this.getArrCuvs().indexOf(
				CollectionUtils.find(
						this.getArrCuvs(), 
						new PredicateUtils().equalPredicate(
								this.getDatosCabeceraCDRTO().getCodigoVentaDesea())));
		this.setValidacionDesea((String)this.getArrIndicadorValidacionCUVDesea().get(index));
		if(StringUtils.isBlank((String)this.getArrIndicadorValidacionCUVDesea().get(index))){
			//this.valorFoco = "8";
		}else{
			if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"PE")){
				if(index2 == -1){
					//this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
					//this.mostrarDialogoGeneral();
					this.addError("Error",this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
					//this.valorFoco = "7";
				}else{
					this.getDatosCabeceraCDRTO().setProductoDesea((String)this.getArrDesc().get(index2));
					//this.valorFoco = "8";
				}
			}else{
				verificaExcepcionDesea("8");
				if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"MF")){
					LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaDesea(), 
														"1", 
														form.getNumeroBoletaDespacho());
					this.loadProductoDeseaCallback(labelValue);
				}else{
					if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"MP")){
						LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaDesea(), 
								"2", 
								form.getNumeroBoletaDespacho());
						this.loadProductoDeseaCallback(labelValue);
					}else{
						if(StringUtils.equals((String)this.getArrIndicadorValidacionCUVDesea().get(index),"-")){
							if(index2 != -1){
								this.getDatosCabeceraCDRTO().setProductoDesea((String)this.getArrDesc().get(index2));
								this.getDatosCabeceraCDRTO().setPrecioDesea((String)this.getArrPrec().get(index2));
							}
							this.valorFoco = "8";
						}
					}
				}
			}
		}
		return true;
	}
	
	
	public void loadProductoDeseaCallback(LabelValue labelValue){
		if(log.isDebugEnabled()){
			log.debug("loadProductoDeseaCallback");
		}
		if(labelValue != null){
			//Ponemos la descripcion
			this.getDatosCabeceraCDRTO().setProductoDesea(labelValue.getLabel());
			//Ponemos el precio
			this.getDatosCabeceraCDRTO().setPrecioDesea(labelValue.getValue());
			this.valorFoco = "8";
		}else{
			this.getDatosCabeceraCDRTO().setProductoDesea("");
			this.getDatosCabeceraCDRTO().setPrecioDesea("");
			this.valorFoco = "6";
			if(StringUtils.equals(this.getValidacionDesea(), "PE")){
				//this.mostrarDialogoGeneral();
				//this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
				this.addError("Error",this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
			}
			if(StringUtils.equals(this.getValidacionDesea(), "MF")){
				//this.mostrarDialogoGeneral();
				//this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.precios"));
				this.addError("Error",this.getResourceMessage("mensaje.codVtaNoExiste.precios"));
			}
			if(StringUtils.equals(this.getValidacionDesea(), "MP")){
				//this.mostrarDialogoGeneral();
				//this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
				this.addError("Error",this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
			}
		}
	}
	
	public void agregarDetalle(){
		if(log.isDebugEnabled()){
			log.debug("agregarDetalle");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		int index = this.getArrCodigoOperacion().indexOf(CollectionUtils.find(this.getArrCodigoOperacion(), new EqualPredicate(operacionesResultado.getCodigo())));
		
		if(this.isReadOnlyCantidadDesea()){
			this.valorFoco = "6";
			return;
		}
		
		if(StringUtils.equals(operacionesResultado.getCodigo(),"T")){//TRUEQUE
			//Verifica que almenos uno de los cuvs y cantidades se haya ingresado
			if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
				&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
				this.valorFoco = "4";
				return;
			}
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
				&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())
				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) == 0){
				this.valorFoco = "5";
				return;
			}
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())
				&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadDesea())
				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadDesea()) == 0){
				this.valorFoco = "7";
				return;
			}
		}
		//Evalua parametro que indica si el CUV es obligatorio o no		
		if(StringUtils.equals((String)this.getArrIndicadorCUVDeseaObligatorio().get(index), "SI")
			&& StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadDesea())
			|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadDesea()) == 0){
			//this.setMensajeAlertaDefault("Cantidad debe ser mayor a cero");
			//this.mostrarDialogoGeneral();
			this.addError("Error", "Cantidad debe ser mayor a cero");
			return;
		}else{
			if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())){
				this.getDatosCabeceraCDRTO().setCantidadDesea("0");
			}
			this.agregarValorArr();
			this.agregarObjectoToColeccion();
			getValUnidadDesea("9");
			Integer dato = ajax.getSaldoProducto(f.getNumeroBoletaDespacho(),
									this.getDatosCabeceraCDRTO().getCodigoVentaCambia(), 
									this.getmPantallaPrincipalBean().getCountryCode());
			if(this.validaSaldoProductoCallBack(dato)){
				log.debug("validaSaldoProductoCallBack true");
			}else{
				log.debug("validaSaldoProductoCallBack true");
			}
		}
	}
	
	public void seteaFocoIgualEnvio(){
		if(log.isDebugEnabled()){
			log.debug("seteaFocoIgualEnvio");
		}
//		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
//		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
//		if(StringUtils.equals(operacionesResultado.getCodigo(), "C")
//			&& this.isMantenerFocoIgualEnvio()){
//			
//		}
		
		copiaTexto();
		this.valorFoco = "8";
	}
	
	public void copiaTexto(){
		if(log.isDebugEnabled()){
			log.debug("copiaTexto");
		}
		if(this.getDatosCabeceraCDRTO().isIgualEnvio()){
			this.getDatosCabeceraCDRTO().setCodigoVentaDesea(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
			this.getDatosCabeceraCDRTO().setCantidadDesea(this.getDatosCabeceraCDRTO().getCantidadCambia());
			this.getDatosCabeceraCDRTO().setProductoDesea(this.getDatosCabeceraCDRTO().getProductoCambia());
			this.getDatosCabeceraCDRTO().setPrecioDesea(this.getDatosCabeceraCDRTO().getPuFactura());
		}else{
			this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
			this.getDatosCabeceraCDRTO().setCantidadDesea("");
			this.getDatosCabeceraCDRTO().setProductoDesea("");
			this.getDatosCabeceraCDRTO().setPrecioDesea("");
		}
	}
	
	/**
	 * Setea foco cuv cambia.
	 */
	public void seteaFocoCUVCambia(){
		if (log.isDebugEnabled()) {
			log.debug("seteaFocoCUVCambia");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		int cont = 0;
		int intStrLen = 0;
		int intStrMaxLen = 0;
		FacesContext context = FacesContext.getCurrentInstance();
	    Map<String,String> params = context.getExternalContext().getRequestParameterMap();
	    String valor = params.get("pflagOperacionTrueque");
	    String strLen = params.get("strLen");
	    String strMaxLen = params.get("strMaxLen");
	    String valorCUVcambia = params.get("valor");
	    
	    if(StringUtils.isNotEmpty(strLen)
			&& StringUtils.isNotBlank(strLen)){
	    	intStrLen = Integer.parseInt(strLen);
	    }
	    
	    if(StringUtils.isNotEmpty(strMaxLen)
				&& StringUtils.isNotBlank(strMaxLen)){
	    	intStrMaxLen = Integer.parseInt(strMaxLen);
		}	
			
		if(intStrLen < intStrMaxLen){
			//autocompletar con ceros a la izquierda
			this.getDatosCabeceraCDRTO().setCodigoVentaCambia(this.cerosIzquierda(valorCUVcambia, intStrMaxLen));
			strLen = strMaxLen;
		}else{
			this.getDatosCabeceraCDRTO().setCodigoVentaCambia(valorCUVcambia);
		}
	    
	    log.debug(valor);
	    if(StringUtils.isNotBlank(valor)
				&& StringUtils.isNotEmpty(valor)){
			if(StringUtils.equals(valor, "true")){
				this.flagOperacionTrueque = true;
			}
		}
		
		if(StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
			&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
			log.debug(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getOperacion())
				&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
				log.debug(this.getDatosCabeceraCDRTO().getOperacion());
				OperacionesResultado operacionesResultado= obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion()); 
				log.debug(operacionesResultado.getCodigo());
				if(!StringUtils.equals(operacionesResultado.getCodigo(), "C")
						&& !StringUtils.equals(operacionesResultado.getCodigo(), "P")){
										
					if(this.getListaDatosCabeceraCDRTO().size()>0){
						if(CollectionUtils.exists(this.getListaDatosCabeceraCDRTO(), 
								new BeanPredicate("codigoVentaCambia", 
										new EqualPredicate(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())))
								&& 
							CollectionUtils.exists(this.getListaDatosCabeceraCDRTO(), 
									new BeanPredicate("operacion", 
										new EqualPredicate(this.getDatosCabeceraCDRTO().getOperacion())))){
								cont++;
						}
						
					}	
				}
				
				if(cont != 0){
					//this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.cuvingresadoCDR"));
					//this.mostrarDialogoGeneral();
		    		
		    		this.addError("Error", this.getResourceMessage("mantenimientoRECDigitacionCDRAction.cuvingresadoCDR"));
				}else{
					this.setFocusCUVCambia();
				}
			}
		}else{
			if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getOperacion())
					&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getOperacion())){
				OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
				int index = this.getListaOperaciones().indexOf(operacionesResultado);
				if(StringUtils.equals(((ParametrosOperacionesReclamos)this.getRecOperacionParametrosList().get(index)).getIndicadorCUVCambiaObligatorio(),"NO")){
					this.valorFoco = "6";
				}else{
					if(StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"T")){
						if(this.isFlagOperacionTrueque()){
							this.setFlagOperacionTrueque(false);
						}else{
							this.valorFoco = "6";
						}
							
					}
				}
			}
		}		
	}
	
	/**
	 * Sets the focus cuv cambia.
	 */
	public void setFocusCUVCambia(){
		if (log.isDebugEnabled()) {
			log.debug("setFocusCUVCambia");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(this.isOnFocusOperacion()){
			this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
			this.getDatosCabeceraCDRTO().setProductoCambia("");
			this.getDatosCabeceraCDRTO().setPuFactura("");
			this.getDatosCabeceraCDRTO().setCantidadCambia("");
			this.ejecutarFunciones("1");
		}else{
			
			int index = this.getListaOperaciones().indexOf(operacionesResultado);
			if(((String)this.getArrIndicadorCUVCambiaObligatorio().get(index)).equals("NO") && operacionesResultado.getCodigo()!="T"){
				this.valorFoco = "5";
				int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
				if(index2!=-1){
					if(StringUtils.isNotBlank((String)this.getArrDesc().get(index)) 
						&& StringUtils.isNotEmpty((String)this.getArrDesc().get(index))
						&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
						&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
						this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getArrDesc().get(index));
						
						String valor = (String)this.getArrPrecCata().get(index);
						if(Integer.parseInt(valor) == 0){
							this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index));
						}else{
							this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index));
						}
						
						this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index));
						this.setCodigoVentaGlobal(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
						this.setCodigoOperacionGlobal(this.getDatosCabeceraCDRTO().getOperacion());
						this.verificaExcepcion(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), "4");
						this.setVerex(true);
					}
				}
			}else{
				int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
				if(index2 != -1){
					if(StringUtils.isNotBlank((String)this.getArrDesc().get(index2))
						&& StringUtils.isNotEmpty((String)this.getArrDesc().get(index2))
						&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
						&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
						this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getArrDesc().get(index2));
						if(StringUtils.equals(operacionesResultado.getCodigo(), "T")){
							this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
						}else{
							
							if(StringUtils.equals((String)this.getArrPrecCata().get(index2),"0")){
								this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
							}else{
								this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
							}
						}
						this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index2));
						//validarException
						this.verificaExcepcion(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),"4");
						this.setVerex(true);
					}
				}
			}
			
			//Evalua el tipo de validacion parametrizada para el tipo de operacion seleccionado
			this.validacionCambia = (String)this.getArrIndicadorValidacionCUVCambia().get(index);
			if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("")){
				this.valorFoco = "5";
			}else{
				if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("PE")){
					int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
					if(index2 == -1 ){
						//this.setMensajeLocal(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
			    		//this.mostrarDialogo();
			    		this.addError("Error", this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
			    		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
			    		this.getDatosCabeceraCDRTO().setProductoCambia("");
			    		this.getDatosCabeceraCDRTO().setPuFactura("");
			    		this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
					}else{
						this.valorFoco = "5";
						this.getDatosCabeceraCDRTO().setProductoCambia((String) this.getArrDesc().get(index2));
						if(StringUtils.equals(operacionesResultado.getCodigo(), "T")){
							this.getDatosCabeceraCDRTO().setPuFactura((String) this.getArrPrecCata().get(index2));
						}else{
							String precioCatalogo = (String)this.getArrPrecCata().get(index2);
							if(precioCatalogo == "0"){
								this.getDatosCabeceraCDRTO().setPuFactura((String) this.getArrPrecCata().get(index2));
							}else{
								this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
							}	
						}
						this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index2));
						this.setCodigoVentaGlobal(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
						this.setCodigoOperacionGlobal(this.getDatosCabeceraCDRTO().getOperacion());
						if(!this.isVerex()){
							this.verificaExcepcion(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),"4");
						}
					}
				}else{
					if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("MF")){
						LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaCambia(), 
																					"1", 
																					f.getNumeroBoletaDespacho());
						this.loadProductoCallback(labelValue);
					}else{
						if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("MP")){
							LabelValue labelValue = ajax.validarCUVMatrizPreciosPremios(this.getDatosCabeceraCDRTO().getCodigoVentaCambia(), 
																						"2", 
																						f.getNumeroBoletaDespacho());
							this.loadProductoCallback(labelValue);
						}else{
							if(((String)this.getArrIndicadorValidacionCUVCambia().get(index)).equals("-")){
								this.valorFoco = "5";
								int index2 = this.getArrCuvs().indexOf(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
								if(index2 != -1){
									if(StringUtils.isNotBlank((String)this.getArrDesc().get(index2))
										&& StringUtils.isNotEmpty((String)this.getArrDesc().get(index2))
										&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
										&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
										this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getArrDesc().get(index2));
										if(StringUtils.equals(operacionesResultado.getCodigo(), "T")){
											this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
										}else{
											if(Integer.parseInt((String)this.getArrPrecCata().get(index2)) == 0){
												this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrecCata().get(index2));
											}else{
												this.getDatosCabeceraCDRTO().setPuFactura((String)this.getArrPrec().get(index2));
											}
											this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getArrPosic().get(index2));
											this.setCodigoVentaGlobal(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
											this.setCodigoOperacionGlobal(this.getDatosCabeceraCDRTO().getOperacion());
											
											//validarException
											if(!this.verex){
												verificaExcepcion(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),"4");
											}
										}
									}
								}
							}
						}
					}
				}
			}
			//VALIDACION GAR
			if(((String)this.getArrIndicadorValidarCentroServicio().get(index)).equals("SI")){
				this.validaCentroServicio(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
			}			
		}//hasta aqui la val de operacion
	}
	
	
	public void loadProductoCallback(LabelValue labelValue){
		if (log.isDebugEnabled()) {
			log.debug("loadProductoCallback");
		}
		if(labelValue != null){
			//WARN!! No se ha determinado si se hace la validacion por tipoOeracion
	 		//Devolucion, toma CERO si precioCatalogo es CERO y precioFactura en otro caso 
			this.valorFoco = "5";
			this.getDatosCabeceraCDRTO().setProductoCambia(labelValue.getLabel());
			this.getDatosCabeceraCDRTO().setPuFactura(labelValue.getValue());
		}else{
			this.getDatosCabeceraCDRTO().setProductoCambia("");
			this.getDatosCabeceraCDRTO().setPuFactura("");
			this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
			this.valorFoco = "4";
			this.setOnFocusOperacion(false);
			this.setOnFocusCUV(true);
			this.setOnFocusCantidad(false);
			StringBuilder mensaje = new StringBuilder();
			if(this.getValidacionCambia().equals("PE")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.pedido"));
			}
			if(this.getValidacionCambia().equals("MF")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.precios"));	    		
			}
			if(this.getValidacionCambia().equals("MP")){
				mensaje.append(this.getResourceMessage("mensaje.codVtaNoExiste.premios"));
			}
			if(StringUtils.isNotBlank(mensaje.toString())
				|| StringUtils.isNotEmpty(mensaje.toString())){
				//this.setMensajeAlertaDefault(mensaje.toString());
				//this.mostrarDialogoGeneral();
				this.addError("Error", mensaje.toString());
			}
		}
	}
	
	public void verificaExcepcion(String oidSoliPosi, String variable){
		if (log.isDebugEnabled()) {
			log.debug("verificaExcepcion");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String[] arrRespuesta = {};
		if(StringUtils.equals(f.getIndicadorOnline(), "S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
			&& (this.getVisualizaRechazo().equals("0"))
			|| (this.getVisualizaRechazo().equals("2"))){
			if(StringUtils.isNotEmpty(this.getVarCodOperSICC()) 
				&& StringUtils.isNotBlank(this.getVarCodOperSICC()) 
					&& StringUtils.equals(operacionesResultado.getCambVali(), "PE")) {//IND_CAMB_VALI 
				String data = ajax.getValExcepProduGanador(varOidPeriCDR,
															this.getDatosCabeceraCDRTO().getCodigoVentaCambia(),
															varCodOperSICC, 
															operacionesResultado.getTipoOperSicc(), 
															this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), 
															this.getDatosCabeceraCDRTO().getMotivo(),
															this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
															f.getIndicadorValorAceptaCDR(),
															this.getIndicadorExcluirValidaciones());
				if(StringUtils.isNotEmpty(data) || StringUtils.isNotBlank(data)){
					arrRespuesta = data.split("|");
					String mensaje = arrRespuesta[0];
					if(StringUtils.isNotEmpty(mensaje)
						&& StringUtils.equals(mensaje, "null")){
						this.mensajeLocal = mensaje;
						this.mostrarDialogo();
						if(StringUtils.equals(this.valorFoco, "4")){
							this.onFocusCUV = true;
							this.valorFoco = "4";
						}else{
							if(StringUtils.equals(this.valorFoco, "5")){
								this.onFocusCantidad = true;
								this.valorFoco = "5";
							}
						}
					}else{
						if(StringUtils.equals(this.valorFoco, "4")){
							this.onFocusCUV = false;
						}else{
							this.onFocusCantidad = false;
						}
					}
				}
				
				if(arrRespuesta.length> 0){
					String reemplazar = arrRespuesta[1];
					this.setIndicadorExcluirValidacionDevolucion("N");
					
					if(!StringUtils.equals(reemplazar,"0")){
						if(StringUtils.equals(reemplazar,"1")){
							this.setIndicadorExcluirValidacionDevolucion("S");
						}
					}
				}
			}			
		}
	}
	
	public void verificaExcepcionDesea(String variable){
		if(log.isDebugEnabled()){
			log.debug("verificaExcepcionDesea");
		}
		opcion = variable;
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		/*var dato=form.listaOperacion[form.listaCUVDesea.length-1].value.split("|");
		var codigoVenta = form.listaCUVDesea[form.listaCUVDesea.length-1].value;
		var codigoMotivo = form.listaMotivo[form.listaMotivo.length-1].value;*/
		
		if(StringUtils.equals(f.getIndicadorOnline(), "S")
				&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
				&& (this.getVisualizaRechazo().equals("0"))
				|| (this.getVisualizaRechazo().equals("2"))){
			if(StringUtils.isNotEmpty(this.getVarCodOperSICC()) && StringUtils.isNotBlank(this.getVarCodOperSICC()) && StringUtils.isNotBlank(operacionesResultado.getDeseVali()) && StringUtils.isNotEmpty(operacionesResultado.getDeseVali())) {//IND_CAMB_VALI
				String data = "";/*ajax.getValExcepProduDesea(f.getCampana(), this.getDatosCabeceraCDRTO().getCodigoVentaCambia(),varCodOperSICC , operacionesResultado.getTipoOperSicc(),this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), this.getDatosCabeceraCDRTO().getMotivo(),"");*/
				if(StringUtils.isNotBlank(data) && StringUtils.isNotEmpty(data)){
					if(StringUtils.equals(this.valorFoco, "8")){
						this.valorFoco = "8";
					}else{
						if(StringUtils.equals(this.valorFoco, "9")){
							this.valorFoco = "9";
						}
					}
				}else{
					if(StringUtils.equals(this.valorFoco, "8")){
						this.valorFoco = "8";
					}
				}
			}
		}
		
	}
	
	private void seteaCodigoOperacionPordefecto(){
		if(log.isDebugEnabled()){
			log.debug("setearOperacionPordefecto");
		}
		this.getDatosCabeceraCDRTO().setOperacion("0");
	}
	
	private void seteaCodigoMotivoPordefecto(){
		if(log.isDebugEnabled()){
			log.debug("setearOperacionPordefecto");
		}
		this.getDatosCabeceraCDRTO().setMotivo(((Base)this.getListaMotivo().get(0)).getCodigo());
	}
	
	
	public void ejecutarFunciones(String variable){
		if (log.isDebugEnabled()) {
			log.debug("ejecutarFunciones");
		}
		this.setOpcion(variable);
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = new OperacionesResultado();
		if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getOperacion())
			&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getOperacion())
				&& existeCodigoOperacionInMapOperaciones(this.getDatosCabeceraCDRTO().getOperacion())){
			
			operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
			if(StringUtils.equals(f.getIndicadorOnline(),"S")
					&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
					&& StringUtils.isNotBlank(this.getVarOidPeriCDR())
					&& StringUtils.isNotEmpty(this.getVarOidPeriCDR())
					&& (StringUtils.equals(this.getVisualizaRechazo(),"0")
							|| StringUtils.equals(this.getVisualizaRechazo(), "2"))){
				
				if(this.isOnFocusOperacion() && !StringUtils.equals(this.getOpcion(), "1")){
					this.valorFoco = "3";
				}else{
					if(this.isOnFocusMotivo() && !StringUtils.equals(this.getValorFoco(), "3")){
						this.valorFoco = "6";
					}else{
						if(operacionesResultado != null){
							StringBuilder datoCompuesto = new StringBuilder();
							datoCompuesto.append(operacionesResultado.getCodigo())
										.append("|")
										.append(operacionesResultado.getTipoOperSicc())
										.append("|")
										.append(operacionesResultado.getCodOperSicc());
							log.debug(datoCompuesto.toString());
							LabelValue data = ajax.getCodigoOperacionCorrecto(datoCompuesto.toString() ,
															this.getDatosCabeceraCDRTO().getMotivo(),
															f.isIndicadorExpress()?"1":"0", 
															this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
															f.getNumeroBoletaDespacho(),
															this.getVarOidPeriCDR(),
															this.mPantallaPrincipalBean.getCodigoUsuario(),
															this.getIndicadorExcluirValidaciones());
							this.loadgetCodigoOperacionCorrectoCallBack(data);
						}
					}
				}
			}
			bloquearComponentes();
		}
	}
	
	private void loadgetCodigoOperacionCorrectoCallBack(LabelValue data){
		if(log.isDebugEnabled()){
			log.debug("loadgetCodigoOperacionCorrectoCallBack");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(data != null){
			//this.mostrarDialogo();
			//this.mensajeLocal = labelValue.getValue();
			if(data.getLabel() != null){
				f.setCodigoOperacionCorrecto(data.getLabel());
				//llena variable global
				this.setVarCodOperSICC(data.getLabel());
			}
			if(data.getValue() != null){
				this.mostrarDialogo();
				this.mensajeLocal = data.getValue();
				if(StringUtils.equals(this.getOpcion(),"1")){
					this.valorFoco = "3";
					this.setOnFocusOperacion(true);
				}
			}else{
				if(StringUtils.equals(this.getOpcion(),"1")){
					this.setOnFocusOperacion(false);
				}
			}
		}
	}
	
	/**
	 * Valida centro servicio.
	 *
	 * @param codigoVenta the codigo venta
	 */
	public void validaCentroServicio(String codigoVenta){
		if (log.isDebugEnabled()) {
			log.debug("validaCentroServicio");
		}
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		LabelValue labelValue = ajax.getIndicadorCentroServicio(codigoVenta);
		
		
		if(labelValue != null){
	    	if(StringUtils.equals(labelValue.getValue(),"1")){
	    		//this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.garantiaCentroServicio"));
	    		//this.mostrarDialogoGeneral();
	    		this.addError("Error", this.getResourceMessage("mensaje.garantiaCentroServicio"));
		    	this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
		    	this.getDatosCabeceraCDRTO().setProductoCambia("");
		    	this.getDatosCabeceraCDRTO().setPuFactura("");
		    	this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
		 		this.valorFoco = "4";
	    	}else{
//	    		int index = this.getListaCodigoVenta().indexOf((String)this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
//	    		if(index == -1){
//					this.setMensajeAlertaDefault(this.getResourceMessage("mensaje.codVtaNoExisteCDR"));
//		    		this.mostrarDialogoGeneral();
//		    		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
//		    		this.valorFoco = "4";
//	    		}else{
//	    			this.getDatosCabeceraCDRTO().setProductoCambia((String)this.getListaDescripcion().get(index));
//	    			this.valorFoco = "5";
//	    			if(StringUtils.equals(((OperacionesResultado)this.operaciones.get(this.getDatosCabeceraCDRTO().getOperacion())).getCodigo(),"D")){
//	    				if(((Integer)this.getListaPrecioCat().get(index)).intValue() == 0){
//	    					this.getDatosCabeceraCDRTO().setPuFactura((String)this.getListaPrecioCatalogo().get(index));
//	    				}else{
//	    					this.getDatosCabeceraCDRTO().setPuFactura((String)this.getListaPrecioCat().get(index));
//	    				}
//	    			}else{
//	    				this.getDatosCabeceraCDRTO().setPuFactura((String)this.getListaPrecioCat().get(index));
//	    			}
//	    		}
//	    		this.getDatosCabeceraCDRTO().setListaIdentSolicPos((String)this.getListaPosicion().get(index));
	    	}			
		}
	}
	
	
	
	/**
	 * Setea foco unidades cambia.
	 */
	public void seteaFocoUnidadesCambia(){
		if (log.isDebugEnabled()) {
			log.debug("seteaFocoUnidadesCambia");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		this.setEsEnterEnCantidadProductoCambiaOnChange(true);
		this.getValUnidadReclamo("5");
		//Si tiene errores
	 	if(this.isOnFocusCantidad()){
      		return;
      	}else{
      		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
      		if(operacionesResultado.getCambObli().equals("SI")
      			&& (!StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())		
      				|| Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) == 0)
      			){
      			//this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.cantidadMayoracero"));
	    		//this.mostrarDialogoGeneral();
	    		this.addError("Error", this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.cantidadMayoracero"));
      		}
      		if(operacionesResultado.getCambObli().equals("NO")
      			&& (StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadCambia()) || Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) == 0)
      				&& StringUtils.equals(operacionesResultado.getCodigo(),"T")
      					&& !StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){      			
      			//this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.cantidadMayoracero"));
	    		//this.mostrarDialogoGeneral();
	    		this.addError("Error", this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.cantidadMayoracero"));
      		}
      			
  			this.setEsEnterEnCantidadProductoCambiaOnChange(true);
  			
  			if(!StringUtils.equals(operacionesResultado.getCodigo(), "G") 
  				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F")
  					&& !StringUtils.equals(operacionesResultado.getCodigo(), "H") 
  						&& !StringUtils.equals(operacionesResultado.getCodigo(), "F3")
  							&& !StringUtils.equals(operacionesResultado.getCodigo(), "F4")
  								&& !StringUtils.equals(operacionesResultado.getCodigo(), "G3")
  									&& !StringUtils.equals(operacionesResultado.getCodigo(), "G4")
  										&& !StringUtils.equals(operacionesResultado.getCodigo(), "XA")
  											&& !StringUtils.equals(operacionesResultado.getCodigo(), "XI")
  												&& !StringUtils.equals(operacionesResultado.getCodigo(), "XM")
  													&& !StringUtils.equals(operacionesResultado.getCodigo(), "XP")){///CAMBIO
  				if(StringUtils.equals(operacionesResultado.getCodigo(), "D")
  					&& StringUtils.equals(f.getIndicadorDevolucionOferta(), "1")){
  					this.validarOferta();
  				}else{
  					this.valorFoco = "6";
  				}
  			}else{
  				this.valorFoco = "6";
  			}
  		}
    }
	
	
	public void validarOferta(){
		if (log.isDebugEnabled()) {
			log.debug("validarOferta");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		if(StringUtils.equals(operacionesResultado.getCodigo(), "D")
			&& StringUtils.equals(f.getIndicadorOnline(), "S")
				&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")){
			String valor = "";
			
			Map data = null; /*ajax.ofertaDevolucion(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),
												this.getDatosCabeceraCDRTO().getCantidadCambia());*/
			this.validarOfertaCallback(data);
			if(StringUtils.isNotBlank(valor) && StringUtils.isNotEmpty(valor)){
				String arrRespuesta[] = valor.split("|");
				StringBuilder muestraOferta = new StringBuilder(arrRespuesta[0]);
				StringBuilder mensajeError = new StringBuilder(arrRespuesta[1]);
				if(StringUtils.isNotBlank(mensajeError.toString())){
					if(Integer.parseInt(muestraOferta.toString()) > 0){
						//error de cantidad
						this.setOnFocusCantidad(true);
						this.openSearchOfertaPopup();
					}
				}else{
					if(Integer.parseInt(muestraOferta.toString()) == 0){
						//poner foco en listamotivo
						this.valorFoco = "6";
					}else{
						//Validar cada registro de la tabla GTT
						double montoDevolucionActual = this.calcularMontoDevolucionActual();
						int cantidades = this.calcularCantidadesPorCUV();
						
						//Indica donde valida el codigo de venta [PE]:en el pedido [MF]:en la matriz de facturacion [MP]:en la matriz de premios [ ]:no se valida
						//Para este caso debe de ser PE
						String valProdOfe = "";

						
						this.validarProductosOfertaCallBack(null /*ajax.validarProductosOferta(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),//UR
													f.getCodigoConsejera(),			//UR
													String.valueOf(cantidades),		//UR
													String.valueOf(this.getPorcentajeDevolucion()),	//PD
													String.valueOf(montoDevolucionActual),	//PD
													String.valueOf(this.getMontoDevolucion()),	//PD
													String.valueOf(this.getMontoPedido()),		//PD
													this.getVarOidPeriCDR(),	//PROD_GANA
													this.getVarCodOperSICC(),	//PROD_GANA
													operacionesResultado.getTipoOperSicc(), //PROD_GANA
													operacionesResultado.getCambVali(),	//PROD_GANA
													this.getDatosCabeceraCDRTO().getMotivo(), //PROD_GANA
													mantenimientoRecDigitacionCdrListaOfertas,
													mantenimientoRecDigitacionCdrOfertaParametro)*/);
						
						
							
							
						
					}
				}
			}
			
		}else{
			this.valorFoco = "6";
		}
	}
	
	private void validarOfertaCallback(Map data){
		if(log.isDebugEnabled()){
			log.debug("validarOfertaCallback");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String valor = "";
		valor = (String)data.get("valor");
		this.mantenimientoRecDigitacionCdrOfertaParametro = (Map)data.get("mantenimientoRecDigitacionCdrOfertaParametro");
		this.mantenimientoRecDigitacionCdrListaOfertas = (List)data.get("mantenimientoRecDigitacionCdrListaOfertas");
		
		if(StringUtils.isNotBlank(valor) && StringUtils.isNotEmpty(valor)){			
			String arrRespuesta[] = StringUtils.splitByWholeSeparator(valor, "|");
			String muestraOferta = new String(arrRespuesta[0]);
			String mensajeError = new String(arrRespuesta[1]);
			if(StringUtils.isNotBlank(mensajeError.toString())){
				this.setMensajeLocal(mensajeError.toString());
				this.mostrarDialogo();
				if(Integer.parseInt(muestraOferta.toString()) > 0){
					//error de cantidad
					this.setOnFocusCantidad(true);
					this.openSearchOfertaPopup();
				}
			}else{
				if(Integer.parseInt(muestraOferta.toString()) == 0){
					//poner foco en listamotivo
					this.valorFoco = "6";
				}else{
					//Validar cada registro de la tabla GTT
					double montoDevolucionActual = this.calcularMontoDevolucionActual();
					int cantidades = this.calcularCantidadesPorCUV();
					
					//Indica donde valida el codigo de venta [PE]:en el pedido [MF]:en la matriz de facturacion [MP]:en la matriz de premios [ ]:no se valida
					//Para este caso debe de ser PE
					this.validarProductosOfertaCallBack(null /*ajax.validarProductosOferta(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),//UR
												f.getCodigoConsejera(),			//UR
												String.valueOf(cantidades),		//UR
												String.valueOf(this.getPorcentajeDevolucion()),	//PD
												String.valueOf(montoDevolucionActual),	//PD
												String.valueOf(this.getMontoDevolucion()),	//PD
												String.valueOf(this.getMontoPedido()),		//PD
												this.getVarOidPeriCDR(),	//PROD_GANA
												this.getVarCodOperSICC(),	//PROD_GANA
												operacionesResultado.getTipoOperSicc(), //PROD_GANA
												operacionesResultado.getCambVali(),	//PROD_GANA
												this.getDatosCabeceraCDRTO().getMotivo(), //PROD_GANA
												mantenimientoRecDigitacionCdrListaOfertas,
												mantenimientoRecDigitacionCdrOfertaParametro)*/);
					
				}
			}
		}
	}
	
	private void validarProductosOfertaCallBack(String data){
		if(log.isDebugEnabled()){
			log.debug("validarProductosOfertaCallBack");
		}
		
		if(StringUtils.equals(data,"1")){	//ERROR
			//this.setMensajeAlertaDefault(this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.ofertanoingresada"));
			//this.mostrarDialogoGeneral();
			
			this.addError("Error", this.getResourceMessage("mantenimientoRECDigitacionCDRAction.mensaje.ofertanoingresada"));
			this.setOnFocusCantidad(true);
			this.openSearchOfertaPopup();
		}else{
			//Salvamos la data en variables globales
			String[] valoresOferta = data.split("|");
			if(valoresOferta.length > 0){
				this.setValoresOfertaPOSBUSC(Arrays.asList(valoresOferta[0].split(";")));
				this.setValoresOfertaPOSOFER(Arrays.asList(valoresOferta[1].split(";")));
				this.setValoresOfertaCUV(Arrays.asList(valoresOferta[2].split(";")));
				this.setValoresOfertaUNI(Arrays.asList(valoresOferta[3].split(";")));
				this.setValoresOfertaDESPRO(Arrays.asList(valoresOferta[4].split(";")));
				this.setValoresOfertaPREPRO(Arrays.asList(valoresOferta[5].split(";")));
			}
			
			this.valorFoco = "6";
			this.setAgregarFilasOferta(true);
		}
	}
	
	private double calcularMontoDevolucionActual(){
		if (log.isDebugEnabled()) {
			log.debug("calcularMontoDevolucionActual");
		}
		
		double monto = 0.0;
		
		if(this.listaDatosCabeceraCDRTO.size() > 0){				
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()){
				if(StringUtils.equals(elemento.getOperacion(),"D")){
					monto = monto + Integer.parseInt(elemento.getCantidadCambia()) * Double.parseDouble(this.getDatosCabeceraCDRTO().getPuFactura());					
				}
			}
		}
		
		this.montoT = monto;
		
		return monto;
	}
	
	public int calcularCantidadesPorCUV(){
		if (log.isDebugEnabled()) {
			log.debug("calcularCantidadesPorCUV");
		}
		int cantidad = 0;
		if(this.getListaDatosCabeceraCDRTO().size()>0){
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()) {
				if(StringUtils.isNotBlank(elemento.getCodigoVentaCambia())
					&& StringUtils.isNotEmpty(elemento.getCodigoVentaCambia())){
					cantidad = cantidad + Integer.parseInt(elemento.getCantidadCambia());
				}
			} 
		}
		return cantidad;
	}
	
	public void esEnterMotivo(){
		if(log.isDebugEnabled()){
			log.debug("esEnterMotivo");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		boolean valor = false;
		this.setMantenerFocoIgualEnvio(false);
		
		if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getListaIdentSolicPos())){
			this.verificaExcepcionValida(this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),"4");
		}
		
		
		EqualPredicate nameEqlPredicate = new EqualPredicate(this.getDatosCabeceraCDRTO().getMotivo());
		BeanPredicate beanPredicate = new BeanPredicate("codigo", nameEqlPredicate);
		Base base = (Base) CollectionUtils.find(this.listaMotivo, beanPredicate);		
		this.getDatosCabeceraCDRTO().setDesMotivo(base.getDescripcion());
		if(!StringUtils.equals(operacionesResultado.getCodigo(),"E")
			&& !StringUtils.equals(operacionesResultado.getCodigo(), "A")){
			if(!StringUtils.equals(operacionesResultado.getCodigo(), "D")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "H")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F3")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "F4")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G3")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G4")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XA")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XI")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XM")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "XP")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "J")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "K")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "H3")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "N")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G5")){
				if(StringUtils.equals(operacionesResultado.getCodigo(), "C")
					|| StringUtils.equals(operacionesResultado.getCodigo(), "P")){
					if(StringUtils.isNotBlank(f.getIndicadorProductoCambiaIgualDesea()) 
						&& StringUtils.equals(f.getIndicadorProductoCambiaIgualDesea(),"1")){
						//Cargar los mismos valores de producto cambia en producto desea, PRODUCTO Y CANTIDAD, Y LUEGO NUEVA LINEA
						boolean checkControl = this.getDatosCabeceraCDRTO().isIgualEnvio();
						String yC = this.getDatosCabeceraCDRTO().getCodigoVentaCambia();
						String xC = this.getDatosCabeceraCDRTO().getCantidadCambia();
						String zC = this.getDatosCabeceraCDRTO().getProductoCambia();
						String wC = this.getDatosCabeceraCDRTO().getPuFactura();

						String yD = this.getDatosCabeceraCDRTO().getCodigoVentaDesea();
						String xD = this.getDatosCabeceraCDRTO().getCantidadDesea();
						String zD = this.getDatosCabeceraCDRTO().getProductoDesea();
						String wD = this.getDatosCabeceraCDRTO().getPrecioDesea();
						
						checkControl = true;
						
						yD = yC;
						xD = yC;
						zD = zC;
						wD = wC;
						
						//Agregamos la fila						
						this.agregarValorArr();
						if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
							if(this.validaSaldoProductoCallBack(ajax.getSaldoProducto(f.getNumeroBoletaDespacho(),
									this.getDatosCabeceraCDRTO().getCodigoVentaDesea(),
									f.getCodigoPais()))){
								
							}
						
							//this.setDesabilitaMotivo(true);
						}
					}else{
						this.valorFoco = "7";
						this.setMantenerFocoIgualEnvio(true);
					}
				}else{
					this.valorFoco = "6";
				}
			}else{
				if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())
					|| StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
//					this.setReadOnlyCUVCambia(true);
//					this.setReadOnlyCantidadCambia(true);
//					this.setDeshabilitarEnvio(true);
//					this.setReadOnlyCUVDesea(true);
//					this.setReadOnlyCantidadDesea(true);
					
					this.setCodigoVentaGlobal(this.getDatosCabeceraCDRTO().getCodigoVentaCambia());
					this.setCodigoOperacionGlobal(this.getDatosCabeceraCDRTO().getOperacion());
					this.setCodigoMotivoGlobal(this.getDatosCabeceraCDRTO().getMotivo());
					this.setPosicionMotivo(String.valueOf(this.getListaMotivo().size() - 1));
					String cambObi = ((OperacionesResultado)this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion())).getCambObli();
					String cantidad = this.getDatosCabeceraCDRTO().getCantidadCambia();
					String oidSoliPosi = this.getDatosCabeceraCDRTO().getListaIdentSolicPos();
					
					boolean flagValidarMontoDevolucion = validarMontoDevolucion();
			 		boolean flagValidarMontoFaltantes = validarMontoFaltantes(); 
			 		boolean flagValidarMontoCambios = validarMontoCambios();
			 			
			 		if(flagValidarMontoDevolucion && flagValidarMontoFaltantes && flagValidarMontoCambios)	{	
						this.agregarValorArr();
						this.agregarObjectoToColeccion();
			 			if(agregarFilasOferta){
			 				agregarFilasOfertaHTML();
			 			}else{					 		
					 		esEnterEnCantidadProductoCambia = false;
					 		esEnterEnCantidadProductoCambiaOnChange = false;
			 			}
				 		//deshabilitarFilas(true);
						ejecutarFunciones("1");
						
				 	}else{
			 			
				 		this.setReadOnlyCUVCambia(false);
				 		this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
				 		
				 		this.setReadOnlyCantidadCambia(false);
				 		this.getDatosCabeceraCDRTO().setCantidadCambia("");
			 			
				 		this.setDeshabilitarEnvio(false);
				 		this.getDatosCabeceraCDRTO().setIgualEnvio(false);

				 		this.setReadOnlyCUVDesea(false);
				 		this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
				 		
				 		this.setReadOnlyCUVDesea(false);
				 		this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
				 		
				 		this.setReadOnlyCantidadDesea(false);
				 		this.getDatosCabeceraCDRTO().setCantidadDesea("");

				 		this.getDatosCabeceraCDRTO().setProductoCambia("");
				 		this.getDatosCabeceraCDRTO().setPuFactura("");
				 		
				 		this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");			 		

				 		if(!flagValidarMontoDevolucion){
				 			String[] objeto = { " " + porcentajeDevolucion + "%"};
				 			this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje", objeto));
				 		}else{
				 			if(!flagValidarMontoFaltantes){
					 			String[] objeto = { " " + porcentajeFaltantes + "%"};
					 			this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentajeFaltantes", objeto));
					 		}else{
					 			if(!flagValidarMontoCambios){
					 				String[] objeto = { " " + porcentajeCambios + "%"};
					 				this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentajeCambios", objeto));
						 		}
						 	}	
					 	}
			 		}	 								
		 		}
		 		this.setValorFoco("3");
			}
		}else{
    		this.setValorFoco("6");
		}
	}
	
	public void verificaExcepcionValida(String idSoliPosi, String variable){
		if(log.isDebugEnabled()){
			log.debug("verificaExcepcionValida");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		this.opcion = variable;		
		if(StringUtils.equals(f.getIndicadorOnline(),"S") 
				&& StringUtils.equals(f.getIndicadorValCDRLinea(),"1") 
					&& (StringUtils.equals(this.getVisualizaRechazo(),"0") || StringUtils.equals(this.getVisualizaRechazo(),"2"))){
			if(StringUtils.isNotBlank(this.getVarCodOperSICC()) 
					&& StringUtils.isNotEmpty(this.getVarCodOperSICC())
						&& StringUtils.equals(operacionesResultado.getCambVali(), "PE") ) {//IND_CAMB_VALI
				String data = ajax.getValExcepProduGanador(varOidPeriCDR,
											this.getDatosCabeceraCDRTO().getCodigoVentaCambia(),
											varCodOperSICC,
											operacionesResultado.getTipoOperSicc(),
											idSoliPosi,
											this.getDatosCabeceraCDRTO().getMotivo(),
											this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
											f.getIndicadorValorAceptaCDR(),
											this.getIndicadorExcluirValidaciones());
				this.loadMensajeProduValidaCallBack(data);
			}
		}
	}
	
	public void loadMensajeProduValidaCallBack(String data){
		if(log.isDebugEnabled()){
			log.debug("loadMensajeProduValidaCallBack");
		}
		String arrRespuesta[] = StringUtils.split(data, '|');
		data = arrRespuesta[0];
		String reemplazar = arrRespuesta[1];
		this.setIndicadorExcluirValidacionDevolucion("N");
		if(!StringUtils.equals(reemplazar,"0")){
			if(StringUtils.equals(reemplazar,"1")){
				this.setIndicadorExcluirValidacionDevolucion("S");
			}
		}
	}
	
	public void eliminarItems(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("eliminarItems");
		}
		List listados = Arrays.asList(seleccionados);
		List listadoAux = (List)this.getDigitacionCDRDataModel().getWrappedData();
		for (Object object : listados){
			listadoAux.remove((DatosCabeceraCDRTO)object);
		}
	}
	
	public void openSearchHistoricoSolicitudPopup(ActionEvent e){
		if(log.isDebugEnabled()){
			log.debug("openSearchHistoricoSolicitudPopup");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.isNotBlank(f.getCodigoConsejera()) && StringUtils.isNotEmpty(f.getCodigoConsejera())){
			this.getRequestContext().execute("PF('varIdHistorial').show()");
		}
	}
	
	public void findPopup(){
		if(log.isDebugEnabled()){
			log.debug("findPopup");
		}
	}
	
	public void agregarObjectoToColeccion(){
		if(log.isDebugEnabled()){
			log.debug("agregarObjectoToColeccion");
		}
		
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		Base base = this.obtenerMotivoFromCollectionMotivo(this.getDatosCabeceraCDRTO().getMotivo());
		this.getDatosCabeceraCDRTO().setDesOperacion(operacionesResultado.getDescripcion());
		this.getDatosCabeceraCDRTO().setDesMotivo(base.getDescripcion());
		this.getListaDatosCabeceraCDRTO().add(this.getDatosCabeceraCDRTO());
		this.getDigitacionCDRDataModel().setWrappedData(this.getListaDatosCabeceraCDRTO());
		this.valorFoco = "3";
		this.setDatosCabeceraCDRTO(new DatosCabeceraCDRTO());
	}
	
	public void agregarValorArr(){
		if(log.isDebugEnabled()){
			log.debug("agregarValorArr");
		}
		this.getArrUnidades().add(this.getUnidades());
 		this.getArrCodigoVenta().add(this.getCodigoVentaGlobal());
		this.getArrOperacion().add(this.getCodigoOperacionGlobal());
		
		this.setUnidades("0");
	}
	
	public void openSearchOfertaPopup(){
		if (log.isDebugEnabled()) {
			log.debug("openSearchOfertaPopup");
		}
	}
	
	public void getValUnidadReclamo(String variable){
		if (log.isDebugEnabled()) {
			log.debug("getValUnidadReclamo");
		}
		this.opcion = variable;
		
		
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		String codOperacion = operacionesResultado.getCodigo();
		String camObi = operacionesResultado.getCambObli();
		int sumCantidad = 0;
		int tamanioArrUnidades = 0;
		
		if(this.getArrUnidades().size()>0 && !this.getArrUnidades().isEmpty()){
			tamanioArrUnidades = this.getArrUnidades().size();
		}
		
		if(tamanioArrUnidades > 0){
			for (int j = 0; j < tamanioArrUnidades; j++) {
				if(StringUtils.equals((String)this.getArrUnidades().get(j),this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
					if(StringUtils.isNotBlank((String)this.getArrUnidades().get(j))){
						sumCantidad += Integer.parseInt((String)this.getArrUnidades().get(j));
					}
				}
			}
		}
		
		if(sumCantidad == 0){
			sumCantidad = Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia());
		}else{
			sumCantidad += Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia());
		}
				
		if(StringUtils.equals(f.getIndicadorOnline(), "S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
			&& (StringUtils.equals(this.getVisualizaRechazo(),"0")
				|| StringUtils.equals(this.getVisualizaRechazo(),"2"))){
			if(StringUtils.equals(operacionesResultado.getCambVali(), "PE")){
				if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())
					&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCantidadCambia())
						&& StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getListaIdentSolicPos())
							&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getListaIdentSolicPos())){
					String valor = ajax.getValUnidadReclamo(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
															f.getCodigoConsejera(), 
															this.getDatosCabeceraCDRTO().getListaIdentSolicPos(), 
															String.valueOf(sumCantidad),
															operacionesResultado.getCodOperSicc(),
															operacionesResultado.getTipoOperSicc(),
															this.getIndicadorExcluirValidaciones());
					this.loadMensajeCallBack(valor);
				}
			}else{
				this.setOnFocusCantidad(false);
			}
		}
	}
	
	public void getValUnidadDesea(String variable){
		if(log.isDebugEnabled()){
			log.debug("getValUnidadDesea");
		}
		int i=0;
		this.setOpcion(variable);
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
	
		int sumCantidad =0;
		if(StringUtils.equals(f.getIndicadorOnline(), "S")
				&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")
				&& (StringUtils.equals(this.getVisualizaRechazo(),"0")
					|| StringUtils.equals(this.getVisualizaRechazo(),"2"))){	
	 		//setea cantidad			
			if(this.getDatosCabeceraCDRTO().getCodigoVentaCambia().equals(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())){
				if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadDesea()) 
						&& StringUtils.isNotEmpty(this.getDatosCabeceraCDRTO().getCantidadDesea())){
					sumCantidad += Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadDesea());
				}
			}
			
			if(this.getListaDatosCabeceraCDRTO().size()>0){
				DatosCabeceraCDRTO elemento = (DatosCabeceraCDRTO)CollectionUtils.find(this.getListaDatosCabeceraCDRTO(), 
						new BeanPredicate("codigoVentaDesea", 
								new EqualPredicate(this.getDatosCabeceraCDRTO().getCodigoVentaDesea())));
				if(elemento != null){
					if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCantidadDesea())){
						if(elemento.getCantidadDesea().equals(this.getDatosCabeceraCDRTO().getCantidadDesea())){
							sumCantidad += Integer.parseInt(elemento.getCantidadCambia());
						}
					}
				}
			}
			
			if(StringUtils.isNotBlank(operacionesResultado.getDeseVali())
				&& StringUtils.isNotEmpty(operacionesResultado.getDeseVali())){
				String data = ajax.getValUnidadDesea(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
						f.getCodigoConsejera(),
						this.getDatosCabeceraCDRTO().getListaIdentSolicPos(),
						String.valueOf(sumCantidad), 
						varCodOperSICC, 
						operacionesResultado.getTipoOperSicc(),
						this.getIndicadorExcluirValidaciones());
				this.loadMensajeDeseaCallBack(data);
			}//onFocusCantidad = false;			
		}
	}
	
	public void loadMensajeDeseaCallBack(String data){
		if(log.isDebugEnabled()){
			log.debug("loadMensajeDeseaCallBack");
		}
		
		this.setDeshabilitaCUVDesea(false);
		this.setDeshabilitaCantidadDesea(false);		
		if(StringUtils.isNotBlank(data) || StringUtils.isNotEmpty(data)){
			//this.setMensajeAlertaDefault(data);
			//this.mostrarDialogoGeneral();
			this.addError("Error", data);
			if(StringUtils.equals(this.getOpcion(), "8")){
				//operaciones
		 		//form.listaCUVDesea[form.listaCUVDesea.length-1].focus();
		 		onFocusCUVDesea = true;
				onFocusCantidadDesea = false;
				this.setDeshabilitaCantidadDesea(true);
			}else{
				if(StringUtils.equals(this.getOpcion(), "9")){
			
					//unidades
					//form.listaCantidadDesea[form.listaCantidadDesea.length-1].focus();
					onFocusCUVDesea = false;
					onFocusCantidadDesea = true;
					this.setDeshabilitaCUVDesea(true);
				}
			}
		}else{
			if(StringUtils.equals(this.getOpcion(), "8")){
				this.valorFoco = "8";
			}
		}
		onFocusCUV = false;
		onFocusCantidad = false;
	}
	
	public void loadMensajeCallBack(String data){
		if(StringUtils.isNotBlank(data)
			&& StringUtils.isNotEmpty(data)){
			this.mostrarDialogo();
			this.setMensajeLocal(data);
			if(this.getOpcion().equals("4")){
				this.valorFoco = "4";
				this.setOnFocusCUV(true);
			}else{
				if(this.getOpcion().equals("5")){
					this.valorFoco = "5";
					this.setOnFocusCantidad(true);
				}
			}
		}else{
			if(this.getOpcion().equals("4")){
				this.setOnFocusCUV(false);
			}else{
				if(this.getOpcion().equals("5")){
					this.setOnFocusCantidad(false);
				}
			}
		}
	}
	
	public void cambiarPeriodo(){
		if (log.isDebugEnabled()) {
			log.debug("cambiarPeriodo");
		}
	}
	
	public void openPopup(){
		if (log.isDebugEnabled()) {
			log.debug("openPopup");
		}
		this.getRequestContext().execute("PF('dlgOpenCodigoVentaMatrizPopup').show()");
	}
	
	public boolean validaSaldoProductoCallBack(Integer data){
		if (log.isDebugEnabled()) {
			log.debug("validaSaldoProductoCallBack");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		if(StringUtils.equals(f.getIndicadorOnline(),"S")){
			if(data <= 0){
				this.setMensajeLocal(this.getResourceMessage("mantenimientoRECDigitacionCDRForm.message.listaSTO"));
				this.mostrarDialogo();
				return false;
			}
		}
		if(StringUtils.isNotBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
			
			/*this.setReadOnlyCUVCambia(true);
			this.setReadOnlyCantidadCambia(true);
			this.setDeshabilitarEnvio(true);
			this.setReadOnlyCUVDesea(true);
			this.setReadOnlyCantidadDesea(true);*/
			
			boolean flagValidarMontoDevolucion = this.validarMontoDevolucion();
			boolean flagValidarMontoFaltantes = this.validarMontoFaltantes();
			boolean flagValidarMontoCambios = this.validarMontoCambios();
			
			if(flagValidarMontoDevolucion
				&& flagValidarMontoFaltantes
					&& flagValidarMontoCambios){
				log.debug("paso la validacion de montos, aqui se deben de desahbilitar los registros de la coleccion, no se podra modificar ningun registro existente.");
				//TODO addRow('prodList',listaCampos);   //Verificar si aqui se inserta a 
				//this.deshabilitarFilas(true);
				this.setEsEnterEnCantidadProductoCambia(false);
				this.setEsEnterEnCantidadProductoCambiaOnChange(false);
				this.ejecutarFunciones("1");
			}else{
				
				this.setReadOnlyCUVCambia(false);
				this.getDatosCabeceraCDRTO().setCodigoVentaCambia("");
				
	 			this.setReadOnlyCantidadCambia(false);
	 			this.getDatosCabeceraCDRTO().setCantidadCambia("");
	 			
	 			this.setDeshabilitarEnvio(false);
	 			this.getDatosCabeceraCDRTO().setIgualEnvio(false);
	 			
		 		this.setReadOnlyCUVDesea(false);
		 		this.getDatosCabeceraCDRTO().setCodigoVentaDesea("");
		 		
		 		this.setReadOnlyCantidadDesea(false);
		 		this.getDatosCabeceraCDRTO().setCantidadDesea("");
		 		
		 		this.getDatosCabeceraCDRTO().setProductoCambia("");
		 		this.getDatosCabeceraCDRTO().setPuFactura("");
		 		
		 		this.getDatosCabeceraCDRTO().setListaIdentSolicPos("");
		 		
		 		String dataMsj="";
		 		if(!flagValidarMontoDevolucion){
		 			String[] objeto = { " " + porcentajeDevolucion + "%"};
		 			this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje", objeto));
		 			dataMsj = this.getResourceMessage("message.supera.porcentaje", objeto);
		 		}else{		 			
		 			if(!flagValidarMontoFaltantes){
		 				String[] objeto = { " " + porcentajeFaltantes + "%"};
			 			this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentajeFaltantes", objeto));			 			
			 			dataMsj = this.getResourceMessage("message.supera.porcentajeFaltantes", objeto);
			 		}else{
			 			if(!flagValidarMontoCambios){
			 				String[] objeto = { " " + porcentajeCambios + "%"};
			 				this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentajeCambios", objeto));				 			
			 				dataMsj = this.getResourceMessage("message.supera.porcentajeCambios", objeto);
				 		}
				 	}	
			 	}
		 		this.addError("Error", dataMsj);
				//this.mostrarDialogoGeneral();
				this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje") + " " + this.getPorcentajeDevolucion());
			}
		}else{
			if(CollectionUtils.exists(
					this.getArrCodigoOperacion(), 
					new PredicateUtils().equalPredicate(
							this.getDatosCabeceraCDRTO().getCodigoVentaCambia()))){
				int index = this.getArrCodigoOperacion().indexOf(
					CollectionUtils.find(this.getArrCodigoOperacion(), 
						new PredicateUtils().equalPredicate(
								this.getDatosCabeceraCDRTO().getCodigoVentaCambia())));
				if(StringUtils.equals((String)this.getArrIndicadorCUVCambiaObligatorio().get(index), "NO")
					|| StringUtils.equals((String)this.getArrIndicadorCUVCambiaObligatorio().get(index), "SI")
					&& StringUtils.equals(operacionesResultado.getCodigo(), "T")){
					
					if(validarMontoDevolucion()){
//					addRow('prodList',listaCampos);
						//this.deshabilitarFilas(true);
						this.setEsEnterEnCantidadProductoCambia(false);
						this.setEsEnterEnCantidadProductoCambiaOnChange(false);
						this.ejecutarFunciones("1");
					}else{
						//this.mostrarDialogoGeneral();
						//this.setMensajeAlertaDefault(this.getResourceMessage("message.supera.porcentaje") + " " + this.getPorcentajeDevolucion());
						this.addError("Error", this.getResourceMessage("message.supera.porcentaje") + " " + this.getPorcentajeDevolucion());
					}
				}
			}
		}
		this.valorFoco = "3";
		return true;
	}
	
	/*private void deshabilitarFilas(boolean valor){
		if(log.isDebugEnabled()){
			log.debug("deshabilitarFilas");
		}

		this.setMostrarBotonBuscarCUVCambia(valor?false:true);
		this.setMostrarBotonBuscarCUVDesea(valor?false:true);
		
		//this.setDeshabilitaOperacion(valor?true:false);
		this.setReadOnlyCUVCambia(valor?true:false);
		this.setReadOnlyCantidadCambia(valor?true:false);
		this.setDesabilitaMotivo(valor?true:false);
		this.setDeshabilitarEnvio(valor?true:false);
		this.setReadOnlyCUVDesea(valor?true:false);
		this.setReadOnlyCantidadDesea(valor?true:false);
	}*/
	
	public boolean validarMontoDevolucion(){
		if (log.isDebugEnabled()) {
			log.debug("validarMontoDevolucion");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
		//Solo aplica la logica para la pantalla de callcenter
		if(StringUtils.equals(f.getIndicadorOnline(), "S")){																											
			return true;
		}
		//Solo aplica la logica si esta encendido el indicador
		if(StringUtils.equals(f.getIndicadorValidaDevolucion(),"0")){
			return true;
		}
		//Solo se realiza la validacion si la opercacione es de devolucion
		if(!StringUtils.equals(operacionesResultado.getCodigo(), "D")){
			return true;
		}
		
		double monto = this.calcularMontoDevolucionActual();
		double valor = ((monto + this.getMontoDevolucion())/this.getMontoPedido())*100;
		
		if(valor > this.getPorcentajeDevolucion()){
			return false;
		}else{
			return true;
		}
	}
	
	private boolean validarMontoFaltantes(){
		if(log.isDebugEnabled()){
			log.debug("validarMontoFaltantes");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		double monto = 0.0;
		double aux = 0.0;
		if(StringUtils.equals(this.getIndicadorExcluirValidaciones(),"N")){
			//Solo aplica la logica para la pantalla de callcenter
			if(StringUtils.equals(f.getIndicadorOnline(),"S")){
				return true;
			}
			//Solo aplica la logica si esta encendido el indicador
			if(StringUtils.equals(f.getIndicadorValidaCambios(),"0")){
				return true;
			}
			
			OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());
			
			//Solo se realiza la validacion si la opercacione es de devolucion
			if(!StringUtils.equals(operacionesResultado.getCodigo(),"G")
				&& !StringUtils.equals(operacionesResultado.getCodigo(), "G3")
					&& !StringUtils.equals(operacionesResultado.getCodigo(), "G4")
						&& !StringUtils.equals(operacionesResultado.getCodigo(), "G5")
							&& !StringUtils.equals(operacionesResultado.getCodigo(), "F")
								&& !StringUtils.equals(operacionesResultado.getCodigo(), "F3")
									&& !StringUtils.equals(operacionesResultado.getCodigo(), "F4")){
				return true;
			}
			
			monto = this.calcularMontoFaltantesActual();
			aux = (monto + this.getMontoFaltantes())/this.getMontoPedido() * 100;
			if(aux > this.getPorcentajeFaltantes()){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	private boolean validarMontoCambios(){
		if(log.isDebugEnabled()){
			log.debug("validarMontoCambios");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		double monto = 0.0;
		if(StringUtils.equals(this.getIndicadorExcluirValidaciones(),"N")){
				
			//Solo aplica la logica para la pantalla de callcenter
			if (StringUtils.equals(f.getIndicadorOnline(),"S")){
				return true;
			}
			
			//Solo aplica la logica si esta encendido el indicador
			if (StringUtils.equals(f.getIndicadorValidaCambios(),"0")){
				return true;
			}
			
			//alert('va a validar');
			OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion()); 
			//var operacion=form.listaOperacion[form.listaOperacion.length-1].value;
	
			//Solo se realiza la validacion si la opercacione es de devolucion
			if (StringUtils.equals(operacionesResultado.getCodigo(),"C") 
				&& StringUtils.equals(operacionesResultado.getCodigo(),"T")){
				return true;
			}
	
			monto = this.calcularMontoCambiosActual();
			double aux = ((monto + this.getMontoCambios())/this.getMontoPedido())*100;
			
			if (aux > this.getPorcentajeCambios()){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
	private double calcularMontoCambiosActual(){
		if(log.isDebugEnabled()){
			log.debug("calcularMontoCambiosActual");
		}
		double monto = 0.0;
		double montoT = 0.0;
		
		if(this.listaDatosCabeceraCDRTO.size() > 0){				
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()){
				if(StringUtils.equals(elemento.getOperacion(),"C")
					|| StringUtils.equals(elemento.getOperacion(),"T")){
					montoT = montoT + Integer.parseInt(elemento.getCantidadCambia()) * Double.parseDouble(this.getDatosCabeceraCDRTO().getPuFactura());
				}
			}
		}
		
		if(StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"C")
			|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"T")){
			monto = monto + Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) 
				* Double.parseDouble(this.getDatosCabeceraCDRTO().getPuFactura());
		}
		montoT = montoT + monto;
		
		return montoT;
	}
	
	private double calcularMontoFaltantesActual(){
		if(log.isDebugEnabled()){
			log.debug("calcularMontoFaltantesActual");
		}
		double monto = 0.0;
		double montoT = 0.0;
		
		if(this.listaDatosCabeceraCDRTO.size() > 0){				
			for (DatosCabeceraCDRTO elemento : this.getListaDatosCabeceraCDRTO()){
				if(StringUtils.equals(elemento.getOperacion(),"G")
					|| StringUtils.equals(elemento.getOperacion(),"G3")
						|| StringUtils.equals(elemento.getOperacion(),"G4")
							|| StringUtils.equals(elemento.getOperacion(),"G5")
								|| StringUtils.equals(elemento.getOperacion(),"F")
									|| StringUtils.equals(elemento.getOperacion(),"F3")
										|| StringUtils.equals(elemento.getOperacion(),"F4")){
					montoT = montoT + Integer.parseInt(elemento.getCantidadCambia()) * Double.parseDouble(this.getDatosCabeceraCDRTO().getPuFactura());
				}
			}
		}
		
		if(StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"G")
			|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"G3")
				|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"G4")
					|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"G5")
						|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"F")
							|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"F3")
								|| StringUtils.equals(this.getDatosCabeceraCDRTO().getOperacion(),"F4")){
			monto = monto + Integer.parseInt(this.getDatosCabeceraCDRTO().getCantidadCambia()) 
				* Double.parseDouble(this.getDatosCabeceraCDRTO().getPuFactura());
		}
		montoT = montoT + monto;
		
		return montoT;
	}
	
	public void mostrarDialogo(){
		if (log.isDebugEnabled()) {
			log.debug("mostrarDialogo");
		}
		this.getRequestContext().execute("PF('dlgLocal').show()");
	}
	
	public void mostrarDialogoConfirmacion(){
		if (log.isDebugEnabled()) {
			log.debug("mostrarDialogoConfirmacion");
		}
		this.getRequestContext().execute("PF('dlgConfirmacion').show()");
	}
	
	public String indicadorExpressEstado(){
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(f.isIndicadorExpress()){
			return "1";
		}else{
			return "0";
		}
	}
	
	public void enfocar(String caso){
		if(log.isDebugEnabled()){
			log.debug("enfocar");
		}
		MantenimientoRECDigitacionCDRForm f = (MantenimientoRECDigitacionCDRForm)this.formBusqueda;
		if(StringUtils.equals(f.getIndicadorOnline(),"S")
			&& StringUtils.equals(f.getIndicadorValCDRLinea(), "1")){
			if(this.isOnFocusOperacion()){//1
				//	alert("onFocusOperacion "+onFocusOperacion);
				//operaciones
				this.valorFoco = "3";

			}else if(this.isOnFocusCUV()){//4
				if(StringUtils.equals(caso,"4")){
					onFocusCUV = false;
					this.valorFoco = "3";
				}else{
					//codigo venta cuv
					this.valorFoco = "4";
				}
			}else if(this.isOnFocusCantidad()){//5
				//Enfocar hacia atras
				if(StringUtils.equals(caso,"5")){
					onFocusCUV = false;
					this.valorFoco = "3";
				}else if(caso == "4"){
					onFocusCUV = false;
					this.valorFoco = "4";
				}else{
					this.valorFoco = "5";
				}
		 	}else if(onFocusCUVDesea){
				onFocusCUVDesea = false;
				this.valorFoco = "8";
				return;
		
			}else if(onFocusCantidadDesea){
				onFocusCUVDesea = false;
				onFocusCantidadDesea = false;
				this.valorFoco = "9";
				return;
		 	}
		}

		//Verificamos la operacion
      	OperacionesResultado operacionesResultado = this.obtenerOperacionFromMapaOperaciones(this.getDatosCabeceraCDRTO().getOperacion());

		if(StringUtils.equals(operacionesResultado.getCodigo(),"C")){
			if(StringUtils.isNotBlank(f.getIndicadorProductoCambiaIgualDesea()) 
				&& StringUtils.equals(f.getIndicadorProductoCambiaIgualDesea(),"1")){
				if(StringUtils.equals(caso,"6") 
					|| StringUtils.equals(caso,"7")
					|| StringUtils.equals(caso,"8")
					|| StringUtils.equals(caso,"9")){
					//Mover el Foco segun las condiciones
					if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCodigoVentaCambia())){
						this.valorFoco = "4";
					}else{
						if(StringUtils.isBlank(this.getDatosCabeceraCDRTO().getCantidadCambia())){
							this.valorFoco = "5";
						}else{
							if(esEnterEnCantidadProductoCambia){
								this.valorFoco = "6";
							}else{
								this.valorFoco = "5";
							}
						}
					}
				}
			}
			//
		}
	}
	
	/**
	 * Metodos Get y Set
	 * @return
	 */

	public List getListaOperaciones() {
		return listaOperaciones;
	}

	public void setListaOperaciones(List listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	public List getListaMotivo() {
		return listaMotivo;
	}

	public void setListaMotivo(List listaMotivo) {
		this.listaMotivo = listaMotivo;
	}

	public boolean isMostrarPopupRECCodigoVentaPedido() {
		return mostrarPopupRECCodigoVentaPedido;
	}

	public void setMostrarPopupRECCodigoVentaPedido(
			boolean mostrarPopupRECCodigoVentaPedido) {
		this.mostrarPopupRECCodigoVentaPedido = mostrarPopupRECCodigoVentaPedido;
	}

	public BusquedaRECDocumentoReferenciaSearchAction getBusquedaRECDocumentoReferenciaSearchAction() {
		return busquedaRECDocumentoReferenciaSearchAction;
	}

	public void setBusquedaRECDocumentoReferenciaSearchAction(
			BusquedaRECDocumentoReferenciaSearchAction busquedaRECDocumentoReferenciaSearchAction) {
		this.busquedaRECDocumentoReferenciaSearchAction = busquedaRECDocumentoReferenciaSearchAction;
	}

	public boolean isMostrarPopupRECDocRef() {
		return mostrarPopupRECDocRef;
	}

	public void setMostrarPopupRECDocRef(boolean mostrarPopupRECDocRef) {
		this.mostrarPopupRECDocRef = mostrarPopupRECDocRef;
	}

	public static String getMensajeValidaPedido() {
		return mensajeValidaPedido;
	}

	public static void setMensajeValidaPedido(String mensajeValidaPedido) {
		MantenimientoRECDigitacionCDRAction.mensajeValidaPedido = mensajeValidaPedido;
	}

	public List getRecOperacionParametrosList() {
		return recOperacionParametrosList;
	}

	public void setRecOperacionParametrosList(List recOperacionParametrosList) {
		this.recOperacionParametrosList = recOperacionParametrosList;
	}

	public List getLstCodMotRechazo() {
		return lstCodMotRechazo;
	}

	public void setLstCodMotRechazo(List lstCodMotRechazo) {
		this.lstCodMotRechazo = lstCodMotRechazo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getValorFoco() {
		return valorFoco;
	}

	public void setValorFoco(String valorFoco) {
		this.valorFoco = valorFoco;
	}

	public double getMontoDevolucion() {
		return montoDevolucion;
	}

	public void setMontoDevolucion(double montoDevolucion) {
		this.montoDevolucion = montoDevolucion;
	}

	public double getMontoPedido() {
		return montoPedido;
	}

	public void setMontoPedido(double montoPedido) {
		this.montoPedido = montoPedido;
	}

	public double getPorcentajeDevolucion() {
		return porcentajeDevolucion;
	}

	public void setPorcentajeDevolucion(double porcentajeDevolucion) {
		this.porcentajeDevolucion = porcentajeDevolucion;
	}	

	public boolean isMostrarBotonBuscarDOCREF() {
		return mostrarBotonBuscarDOCREF;
	}

	public void setMostrarBotonBuscarDOCREF(boolean mostrarBotonBuscarDOCREF) {
		this.mostrarBotonBuscarDOCREF = mostrarBotonBuscarDOCREF;
	}

	public boolean isMostrarPopupCUV() {
		return mostrarPopupCUV;
	}

	public void setMostrarPopupCUV(boolean mostrarPopupCUV) {
		this.mostrarPopupCUV = mostrarPopupCUV;
	}

	public BusquedaRECCodigoVentaPedidoAction getBusquedaRECCodigoVentaPedidoAction() {
		return busquedaRECCodigoVentaPedidoAction;
	}

	public void setBusquedaRECCodigoVentaPedidoAction(
			BusquedaRECCodigoVentaPedidoAction busquedaRECCodigoVentaPedidoAction) {
		this.busquedaRECCodigoVentaPedidoAction = busquedaRECCodigoVentaPedidoAction;
	}

	public DatosCabeceraCDRTO getDatosCabeceraCDRTO() {
		return datosCabeceraCDRTO;
	}

	public void setDatosCabeceraCDRTO(DatosCabeceraCDRTO datosCabeceraCDRTO) {
		this.datosCabeceraCDRTO = datosCabeceraCDRTO;
	}

	public BusquedaRECCodigoVentaMatrizAction getBusquedaRECCodigoVentaMatrizAction() {
		return busquedaRECCodigoVentaMatrizAction;
	}

	public void setBusquedaRECCodigoVentaMatrizAction(
			BusquedaRECCodigoVentaMatrizAction busquedaRECCodigoVentaMatrizAction) {
		this.busquedaRECCodigoVentaMatrizAction = busquedaRECCodigoVentaMatrizAction;
	}
	
	public boolean isMostrarPopupCVM() {
		return mostrarPopupCVM;
	}

	public void setMostrarPopupCVM(boolean mostrarPopupCVM) {
		this.mostrarPopupCVM = mostrarPopupCVM;
	}

	public Map<String, OperacionesResultado> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(Map<String, OperacionesResultado> operaciones) {
		this.operaciones = operaciones;
	}

	public boolean isDeshabilitarEnvio() {
		return deshabilitarEnvio;
	}

	public void setDeshabilitarEnvio(boolean deshabilitarEnvio) {
		this.deshabilitarEnvio = deshabilitarEnvio;
	}

	public boolean isMostrarBotonBuscarCUVCambia() {
		return mostrarBotonBuscarCUVCambia;
	}

	public void setMostrarBotonBuscarCUVCambia(boolean mostrarBotonBuscarCUVCambia) {
		this.mostrarBotonBuscarCUVCambia = mostrarBotonBuscarCUVCambia;
	}

	public boolean isMostrarBotonBuscarCUVDesea() {
		return mostrarBotonBuscarCUVDesea;
	}

	public void setMostrarBotonBuscarCUVDesea(boolean mostrarBotonBuscarCUVDesea) {
		this.mostrarBotonBuscarCUVDesea = mostrarBotonBuscarCUVDesea;
	}

	public boolean isFlagOperacionTrueque() {
		return flagOperacionTrueque;
	}

	public void setFlagOperacionTrueque(boolean flagOperacionTrueque) {
		this.flagOperacionTrueque = flagOperacionTrueque;
	}

	public boolean isOnFocusOperacion() {
		return onFocusOperacion;
	}

	public void setOnFocusOperacion(boolean onFocusOperacion) {
		this.onFocusOperacion = onFocusOperacion;
	}

	public String getVarOidPeriCDR() {
		return varOidPeriCDR;
	}

	public void setVarOidPeriCDR(String varOidPeriCDR) {
		this.varOidPeriCDR = varOidPeriCDR;
	}

	public String getVisualizaRechazo() {
		return visualizaRechazo;
	}

	public void setVisualizaRechazo(String visualizaRechazo) {
		this.visualizaRechazo = visualizaRechazo;
	}

	public String getVarCodOperSICC() {
		return varCodOperSICC;
	}

	public void setVarCodOperSICC(String varCodOperSICC) {
		this.varCodOperSICC = varCodOperSICC;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public boolean isMostrarPanelDigitacionCdr() {
		return mostrarPanelDigitacionCdr;
	}

	public void setMostrarPanelDigitacionCdr(boolean mostrarPanelDigitacionCdr) {
		this.mostrarPanelDigitacionCdr = mostrarPanelDigitacionCdr;
	}

	public boolean isEsEnterEnCantidadProductoCambiaOnChange() {
		return esEnterEnCantidadProductoCambiaOnChange;
	}

	public void setEsEnterEnCantidadProductoCambiaOnChange(
			boolean esEnterEnCantidadProductoCambiaOnChange) {
		this.esEnterEnCantidadProductoCambiaOnChange = esEnterEnCantidadProductoCambiaOnChange;
	}

	public List getArrCuvs() {
		return arrCuvs;
	}

	public void setArrCuvs(List arrCuvs) {
		this.arrCuvs = arrCuvs;
	}

	public List getArrDesc() {
		return arrDesc;
	}

	public void setArrDesc(List arrDesc) {
		this.arrDesc = arrDesc;
	}

	public List getArrPrec() {
		return arrPrec;
	}

	public void setArrPrec(List arrPrec) {
		this.arrPrec = arrPrec;
	}

	public List getArrPrecCata() {
		return arrPrecCata;
	}

	public void setArrPrecCata(List arrPrecCata) {
		this.arrPrecCata = arrPrecCata;
	}

	public List getArrPosic() {
		return arrPosic;
	}

	public void setArrPosic(List arrPosic) {
		this.arrPosic = arrPosic;
	}

	public DigitacionCDRDataModel getDigitacionCDRDataModel() {
		return digitacionCDRDataModel;
	}

	public void setDigitacionCDRDataModel(
			DigitacionCDRDataModel digitacionCDRDataModel) {
		this.digitacionCDRDataModel = digitacionCDRDataModel;
	}

	public List<DatosCabeceraCDRTO> getListaDatosCabeceraCDRTO() {
		return listaDatosCabeceraCDRTO;
	}

	public void setListaDatosCabeceraCDRTO(
			List<DatosCabeceraCDRTO> listaDatosCabeceraCDRTO) {
		this.listaDatosCabeceraCDRTO = listaDatosCabeceraCDRTO;
	}

	public DatosCabeceraCDRTO[] getSeleccionados() {
		return seleccionados;
	}

	public void setSeleccionados(DatosCabeceraCDRTO[] seleccionados) {
		this.seleccionados = seleccionados;
	}	

	public List getArrIndicadorCUVCambiaObligatorio() {
		return arrIndicadorCUVCambiaObligatorio;
	}

	public void setArrIndicadorCUVCambiaObligatorio(
			List arrIndicadorCUVCambiaObligatorio) {
		this.arrIndicadorCUVCambiaObligatorio = arrIndicadorCUVCambiaObligatorio;
	}

	public List getArrIndicadorCUVDeseaObligatorio() {
		return arrIndicadorCUVDeseaObligatorio;
	}

	public void setArrIndicadorCUVDeseaObligatorio(
			List arrIndicadorCUVDeseaObligatorio) {
		this.arrIndicadorCUVDeseaObligatorio = arrIndicadorCUVDeseaObligatorio;
	}

	public List getArrIndicadorValidacionCUVCambia() {
		return arrIndicadorValidacionCUVCambia;
	}

	public void setArrIndicadorValidacionCUVCambia(
			List arrIndicadorValidacionCUVCambia) {
		this.arrIndicadorValidacionCUVCambia = arrIndicadorValidacionCUVCambia;
	}

	public List getArrIndicadorValidacionCUVDesea() {
		return arrIndicadorValidacionCUVDesea;
	}

	public void setArrIndicadorValidacionCUVDesea(
			List arrIndicadorValidacionCUVDesea) {
		this.arrIndicadorValidacionCUVDesea = arrIndicadorValidacionCUVDesea;
	}

	public List getArrPopupCambia() {
		return arrPopupCambia;
	}

	public void setArrPopupCambia(List arrPopupCambia) {
		this.arrPopupCambia = arrPopupCambia;
	}

	public List getArrPopupDesea() {
		return arrPopupDesea;
	}

	public void setArrPopupDesea(List arrPopupDesea) {
		this.arrPopupDesea = arrPopupDesea;
	}

	public List getArrIndicadorValidarCentroServicio() {
		return arrIndicadorValidarCentroServicio;
	}

	public void setArrIndicadorValidarCentroServicio(
			List arrIndicadorValidarCentroServicio) {
		this.arrIndicadorValidarCentroServicio = arrIndicadorValidarCentroServicio;
	}

	public List<String> getArrCodigoOperacion() {
		return arrCodigoOperacion;
	}

	public void setArrCodigoOperacion(List arrCodigoOperacion) {
		this.arrCodigoOperacion = arrCodigoOperacion;
	}

	public boolean isOnFocusCUV() {
		return onFocusCUV;
	}

	public void setOnFocusCUV(boolean onFocusCUV) {
		this.onFocusCUV = onFocusCUV;
	}

	public boolean isVerex() {
		return verex;
	}

	public void setVerex(boolean verex) {
		this.verex = verex;
	}

	public boolean isOnFocusCantidad() {
		return onFocusCantidad;
	}

	public void setOnFocusCantidad(boolean onFocusCantidad) {
		this.onFocusCantidad = onFocusCantidad;
	}

	public String getValidacionCambia() {
		return validacionCambia;
	}

	public void setValidacionCambia(String validacionCambia) {
		this.validacionCambia = validacionCambia;
	}


	public boolean isAgregarFilasOferta() {
		return agregarFilasOferta;
	}

	public void setAgregarFilasOferta(boolean agregarFilasOferta) {
		this.agregarFilasOferta = agregarFilasOferta;
	}

	public String getMensajeLocal() {
		return mensajeLocal;
	}

	public void setMensajeLocal(String mensajeLocal) {
		this.mensajeLocal = mensajeLocal;
	}

	public boolean isVisualizarPanelGrilla() {
		return visualizarPanelGrilla;
	}

	public void setVisualizarPanelGrilla(boolean visualizarPanelGrilla) {
		this.visualizarPanelGrilla = visualizarPanelGrilla;
	}

	public boolean isDesabilitaMotivo() {
		return desabilitaMotivo;
	}

	public void setDesabilitaMotivo(boolean desabilitaMotivo) {
		this.desabilitaMotivo = desabilitaMotivo;
	}

	public boolean isEsEnterEnCantidadProductoCambia() {
		return esEnterEnCantidadProductoCambia;
	}

	public void setEsEnterEnCantidadProductoCambia(
			boolean esEnterEnCantidadProductoCambia) {
		this.esEnterEnCantidadProductoCambia = esEnterEnCantidadProductoCambia;
	}

	public boolean isMantenerFocoIgualEnvio() {
		return mantenerFocoIgualEnvio;
	}

	public void setMantenerFocoIgualEnvio(boolean mantenerFocoIgualEnvio) {
		this.mantenerFocoIgualEnvio = mantenerFocoIgualEnvio;
	}

	public String getValidacionDesea() {
		return validacionDesea;
	}

	public void setValidacionDesea(String validacionDesea) {
		this.validacionDesea = validacionDesea;
	}

	public List getStoDevolucionesList() {
		return stoDevolucionesList;
	}

	public void setStoDevolucionesList(List stoDevolucionesList) {
		this.stoDevolucionesList = stoDevolucionesList;
	}

	public List getStoCambiosList() {
		return stoCambiosList;
	}

	public void setStoCambiosList(List stoCambiosList) {
		this.stoCambiosList = stoCambiosList;
	}

	public List getStoFfneList() {
		return stoFfneList;
	}

	public void setStoFfneList(List stoFfneList) {
		this.stoFfneList = stoFfneList;
	}

	public List getStoOtrosList() {
		return stoOtrosList;
	}

	public void setStoOtrosList(List stoOtrosList) {
		this.stoOtrosList = stoOtrosList;
	}

	public Map getMantenimientoRecDigitacionCdrOfertaParametro() {
		return mantenimientoRecDigitacionCdrOfertaParametro;
	}

	public void setMantenimientoRecDigitacionCdrOfertaParametro(
			Map mantenimientoRecDigitacionCdrOfertaParametro) {
		this.mantenimientoRecDigitacionCdrOfertaParametro = mantenimientoRecDigitacionCdrOfertaParametro;
	}

	public List getMantenimientoRecDigitacionCdrListaOfertas() {
		return mantenimientoRecDigitacionCdrListaOfertas;
	}

	public void setMantenimientoRecDigitacionCdrListaOfertas(
			List mantenimientoRecDigitacionCdrListaOfertas) {
		this.mantenimientoRecDigitacionCdrListaOfertas = mantenimientoRecDigitacionCdrListaOfertas;
	}

	public boolean isErrorValidaGrabarFinal() {
		return errorValidaGrabarFinal;
	}

	public void setErrorValidaGrabarFinal(boolean errorValidaGrabarFinal) {
		this.errorValidaGrabarFinal = errorValidaGrabarFinal;
	}

	public int getIndiceValidaGrabarFinal() {
		return indiceValidaGrabarFinal;
	}

	public void setIndiceValidaGrabarFinal(int indiceValidaGrabarFinal) {
		this.indiceValidaGrabarFinal = indiceValidaGrabarFinal;
	}

	public boolean isDesabilitaCodigoMotivoRechazoDef() {
		return desabilitaCodigoMotivoRechazoDef;
	}

	public void setDesabilitaCodigoMotivoRechazoDef(
			boolean desabilitaCodigoMotivoRechazoDef) {
		this.desabilitaCodigoMotivoRechazoDef = desabilitaCodigoMotivoRechazoDef;
	}

	public boolean isDeshabilitaObservacionCDR() {
		return deshabilitaObservacionCDR;
	}

	public void setDeshabilitaObservacionCDR(boolean deshabilitaObservacionCDR) {
		this.deshabilitaObservacionCDR = deshabilitaObservacionCDR;
	}

	public String getCodigoVentaGlobal() {
		return codigoVentaGlobal;
	}

	public void setCodigoVentaGlobal(String codigoVentaGlobal) {
		this.codigoVentaGlobal = codigoVentaGlobal;
	}

	public String getCodigoOperacionGlobal() {
		return codigoOperacionGlobal;
	}

	public void setCodigoOperacionGlobal(String codigoOperacionGlobal) {
		this.codigoOperacionGlobal = codigoOperacionGlobal;
	}

	public String getTextCodVenta() {
		return textCodVenta;
	}

	public void setTextCodVenta(String textCodVenta) {
		this.textCodVenta = textCodVenta;
	}

	public List getArrUnidades() {
		return arrUnidades;
	}

	public void setArrUnidades(List arrUnidades) {
		this.arrUnidades = arrUnidades;
	}

	public List getArrCodigoVenta() {
		return arrCodigoVenta;
	}

	public void setArrCodigoVenta(List arrCodigoVenta) {
		this.arrCodigoVenta = arrCodigoVenta;
	}

	public List getArrOperacion() {
		return arrOperacion;
	}

	public void setArrOperacion(List arrOperacion) {
		this.arrOperacion = arrOperacion;
	}

	public String getUnidades() {
		return unidades;
	}

	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public List getListaIdentFila() {
		return listaIdentFila;
	}

	public void setListaIdentFila(List listaIdentFila) {
		this.listaIdentFila = listaIdentFila;
	}

	public List getListaIdentFilaPadre() {
		return listaIdentFilaPadre;
	}

	public void setListaIdentFilaPadre(List listaIdentFilaPadre) {
		this.listaIdentFilaPadre = listaIdentFilaPadre;
	}

	public DatosCabeceraCDRTO getOfertas() {
		return ofertas;
	}

	public void setOfertas(DatosCabeceraCDRTO ofertas) {
		this.ofertas = ofertas;
	}

	public List<DatosCabeceraCDRTO> getListaOfertas() {
		return listaOfertas;
	}

	public void setListaOfertas(List<DatosCabeceraCDRTO> listaOfertas) {
		this.listaOfertas = listaOfertas;
	}

	public DigitacionCDRDataModel getListaOfertasDataModel() {
		return listaOfertasDataModel;
	}

	public void setListaOfertasDataModel(
			DigitacionCDRDataModel listaOfertasDataModel) {
		this.listaOfertasDataModel = listaOfertasDataModel;
	}

	public String getIndicadorExcluirValidacionDevolucion() {
		return indicadorExcluirValidacionDevolucion;
	}

	public void setIndicadorExcluirValidacionDevolucion(
			String indicadorExcluirValidacionDevolucion) {
		this.indicadorExcluirValidacionDevolucion = indicadorExcluirValidacionDevolucion;
	}

	public String getIndicadorExcluirValidaciones() {
		return indicadorExcluirValidaciones;
	}

	public void setIndicadorExcluirValidaciones(String indicadorExcluirValidaciones) {
		this.indicadorExcluirValidaciones = indicadorExcluirValidaciones;
	}

	public String getIndicadorPrimerIngreso() {
		return indicadorPrimerIngreso;
	}

	public void setIndicadorPrimerIngreso(String indicadorPrimerIngreso) {
		this.indicadorPrimerIngreso = indicadorPrimerIngreso;
	}

	public boolean isValidaCodigoResultado() {
		return validaCodigoResultado;
	}

	public void setValidaCodigoResultado(boolean validaCodigoResultado) {
		this.validaCodigoResultado = validaCodigoResultado;
	}

	public boolean isReadOnlyCUVDesea() {
		return readOnlyCUVDesea;
	}

	public void setReadOnlyCUVDesea(boolean readOnlyCUVDesea) {
		this.readOnlyCUVDesea = readOnlyCUVDesea;
	}

	public boolean isReadOnlyCantidadDesea() {
		return readOnlyCantidadDesea;
	}

	public void setReadOnlyCantidadDesea(boolean readOnlyCantidadDesea) {
		this.readOnlyCantidadDesea = readOnlyCantidadDesea;
	}

	public boolean isReadOnlyCUVCambia() {
		return readOnlyCUVCambia;
	}

	public void setReadOnlyCUVCambia(boolean readOnlyCUVCambia) {
		this.readOnlyCUVCambia = readOnlyCUVCambia;
	}

	public boolean isReadOnlyCantidadCambia() {
		return readOnlyCantidadCambia;
	}

	public void setReadOnlyCantidadCambia(boolean readOnlyCantidadCambia) {
		this.readOnlyCantidadCambia = readOnlyCantidadCambia;
	}

	public boolean isOnFocusMotivo() {
		return onFocusMotivo;
	}

	public void setOnFocusMotivo(boolean onFocusMotivo) {
		this.onFocusMotivo = onFocusMotivo;
	}

	public List getValoresOfertaPOSBUSC() {
		return valoresOfertaPOSBUSC;
	}

	public void setValoresOfertaPOSBUSC(List valoresOfertaPOSBUSC) {
		this.valoresOfertaPOSBUSC = valoresOfertaPOSBUSC;
	}

	public List getValoresOfertaPOSOFER() {
		return valoresOfertaPOSOFER;
	}

	public void setValoresOfertaPOSOFER(List valoresOfertaPOSOFER) {
		this.valoresOfertaPOSOFER = valoresOfertaPOSOFER;
	}

	public List getValoresOfertaCUV() {
		return valoresOfertaCUV;
	}

	public void setValoresOfertaCUV(List valoresOfertaCUV) {
		this.valoresOfertaCUV = valoresOfertaCUV;
	}

	public List getValoresOfertaUNI() {
		return valoresOfertaUNI;
	}

	public void setValoresOfertaUNI(List valoresOfertaUNI) {
		this.valoresOfertaUNI = valoresOfertaUNI;
	}

	public List getValoresOfertaDESPRO() {
		return valoresOfertaDESPRO;
	}

	public void setValoresOfertaDESPRO(List valoresOfertaDESPRO) {
		this.valoresOfertaDESPRO = valoresOfertaDESPRO;
	}

	public List getValoresOfertaPREPRO() {
		return valoresOfertaPREPRO;
	}

	public void setValoresOfertaPREPRO(List valoresOfertaPREPRO) {
		this.valoresOfertaPREPRO = valoresOfertaPREPRO;
	}

	public double getPorcentajeFaltantes() {
		return porcentajeFaltantes;
	}

	public void setPorcentajeFaltantes(double porcentajeFaltantes) {
		this.porcentajeFaltantes = porcentajeFaltantes;
	}

	public double getMontoCambios() {
		return montoCambios;
	}

	public void setMontoCambios(double montoCambios) {
		this.montoCambios = montoCambios;
	}

	public double getPorcentajeCambios() {
		return porcentajeCambios;
	}

	public void setPorcentajeCambios(double porcentajeCambios) {
		this.porcentajeCambios = porcentajeCambios;
	}

	public double getMontoFaltantes() {
		return montoFaltantes;
	}

	public void setMontoFaltantes(double montoFaltantes) {
		this.montoFaltantes = montoFaltantes;
	}

	public List<DatosCabeceraCDRTO> getProdList() {
		return prodList;
	}

	public void setProdList(List<DatosCabeceraCDRTO> prodList) {
		this.prodList = prodList;
	}

	public List<ReclamoDigitadoDetalle> getMantenimientoRECDigitacionCDRDetallesDigitadosList() {
		return mantenimientoRECDigitacionCDRDetallesDigitadosList;
	}

	public void setMantenimientoRECDigitacionCDRDetallesDigitadosList(
			List<ReclamoDigitadoDetalle> mantenimientoRECDigitacionCDRDetallesDigitadosList) {
		this.mantenimientoRECDigitacionCDRDetallesDigitadosList = mantenimientoRECDigitacionCDRDetallesDigitadosList;
	}

//	public boolean isDeshabilitaOperacion() {
//		return deshabilitaOperacion;
//	}
//
//	public void setDeshabilitaOperacion(boolean deshabilitaOperacion) {
//		this.deshabilitaOperacion = deshabilitaOperacion;
//	}

	public boolean isDeshabilitaDescripcionProductoCambia() {
		return deshabilitaDescripcionProductoCambia;
	}

	public void setDeshabilitaDescripcionProductoCambia(
			boolean deshabilitaDescripcionProductoCambia) {
		this.deshabilitaDescripcionProductoCambia = deshabilitaDescripcionProductoCambia;
	}

	public boolean isDeshabilitaDescripcionProductoDesea() {
		return deshabilitaDescripcionProductoDesea;
	}

	public void setDeshabilitaDescripcionProductoDesea(
			boolean deshabilitaDescripcionProductoDesea) {
		this.deshabilitaDescripcionProductoDesea = deshabilitaDescripcionProductoDesea;
	}

	public String getCodigoMotivoGlobal() {
		return codigoMotivoGlobal;
	}

	public void setCodigoMotivoGlobal(String codigoMotivoGlobal) {
		this.codigoMotivoGlobal = codigoMotivoGlobal;
	}

	public String getPosicionMotivo() {
		return posicionMotivo;
	}

	public void setPosicionMotivo(String posicionMotivo) {
		this.posicionMotivo = posicionMotivo;
	}

	public boolean isDeshabilitaCUVDesea() {
		return deshabilitaCUVDesea;
	}

	public void setDeshabilitaCUVDesea(boolean deshabilitaCUVDesea) {
		this.deshabilitaCUVDesea = deshabilitaCUVDesea;
	}

	public boolean isDeshabilitaCantidadDesea() {
		return deshabilitaCantidadDesea;
	}

	public void setDeshabilitaCantidadDesea(boolean deshabilitaCantidadDesea) {
		this.deshabilitaCantidadDesea = deshabilitaCantidadDesea;
	}

	public boolean isOnFocusCUVDesea() {
		return onFocusCUVDesea;
	}

	public void setOnFocusCUVDesea(boolean onFocusCUVDesea) {
		this.onFocusCUVDesea = onFocusCUVDesea;
	}

	public boolean isOnFocusCantidadDesea() {
		return onFocusCantidadDesea;
	}

	public void setOnFocusCantidadDesea(boolean onFocusCantidadDesea) {
		this.onFocusCantidadDesea = onFocusCantidadDesea;
	}

	public int getContValidaciones() {
		return contValidaciones;
	}

	public void setContValidaciones(int contValidaciones) {
		this.contValidaciones = contValidaciones;
	}

	public boolean isRegistroModificable() {
		return registroModificable;
	}

	public void setRegistroModificable(boolean registroModificable) {
		this.registroModificable = registroModificable;
	}

	public boolean isMostrarBotonLimpiar() {
		return mostrarBotonLimpiar;
	}

	public void setMostrarBotonLimpiar(boolean mostrarBotonLimpiar) {
		this.mostrarBotonLimpiar = mostrarBotonLimpiar;
	}

	public boolean isFlagHiperConsulta() {
		return flagHiperConsulta;
	}

	public void setFlagHiperConsulta(boolean flagHiperConsulta) {
		this.flagHiperConsulta = flagHiperConsulta;
	}

	public OperacionesResultado getDevolucionEspecial() {
		return devolucionEspecial;
	}

	public void setDevolucionEspecial(OperacionesResultado devolucionEspecial) {
		this.devolucionEspecial = devolucionEspecial;
	}

	public double getMontoT() {
		return montoT;
	}

	public void setMontoT(double montoT) {
		this.montoT = montoT;
	}
}