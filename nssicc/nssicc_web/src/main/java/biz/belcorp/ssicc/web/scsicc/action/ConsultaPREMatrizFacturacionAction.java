package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaPREMatrizFacturacionForm;

@ManagedBean
@SessionScoped
public class ConsultaPREMatrizFacturacionAction extends
		BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4781262596935595507L;
	private String codigoPais;
	private List preCodigoVentaRelacList;
	private List preVentaExclusivaList;
	private List preLimiteVentaList;
	private List preControlStockList;
	private Map mapDatosGenerales;	
	
	private String varCuv;
	private String campanya;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		ConsultaPREMatrizFacturacionForm form = new ConsultaPREMatrizFacturacionForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		ConsultaPREMatrizFacturacionForm f = (ConsultaPREMatrizFacturacionForm) this.formBusqueda;

		findMethod(f);
		List aux = new ArrayList();
		aux.add("faker");
		return aux;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ConsultaPREMatrizFacturacionForm f = (ConsultaPREMatrizFacturacionForm) this.formBusqueda;
		codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		f.setCodigoPais(codigoPais);
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de PeriodoProceso
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());


		// CDR en STO
		log.debug("Seteando Tipo de Variable en el CDR en STO");
		String varTipo = getRequest().getParameter("varTipo");
		String campanya = this.getCampanya();
		String varCuv = this.getVarCuv();
		Formatter fmt = new Formatter();

		if (campanya == null && varCuv == null) {
			f.setCodigoPeriodo("");
			f.setCodigoVentaBuscar("");
		} else {
			f.setCodigoPeriodo(campanya);
			f.setCodigoVentaBuscar(fmt.format("%05d", Integer.parseInt(varCuv))
					.toString());
			findMethod(f);
		}
		mostrarListaBusqueda=false;
	}
	
	private void findMethod(ConsultaPREMatrizFacturacionForm f){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'findMethod' ");
		}
//		ConsultaPREMatrizFacturacionForm f = (ConsultaPREMatrizFacturacionForm) form;
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoVenta", f.getCodigoVentaBuscar());
		
		this.preCodigoVentaRelacList = new ArrayList();
		this.preVentaExclusivaList = new ArrayList();
		this.preLimiteVentaList = new ArrayList();
		this.preControlStockList = new ArrayList();
		
		this.mapDatosGenerales = service.getDatosCodigoVentaPrincipal(criteria);
		if(this.mapDatosGenerales != null) {
			
			f.setCodigoVenta(reemplazarNulo(this.mapDatosGenerales.get("codigoVenta")));
			f.setCodigoSAP(reemplazarNulo(this.mapDatosGenerales.get("codigoSAP")));
			f.setDescripcionProducto(reemplazarNulo(this.mapDatosGenerales.get("descripcionProducto")));
			
			f.setTipoOferta(reemplazarNulo(this.mapDatosGenerales.get("tipoOferta")));
			f.setPrecioCatalogo(reemplazarNulo(this.mapDatosGenerales.get("precioCatalogo")));
			f.setPrecioPosicionamiento(reemplazarNulo(this.mapDatosGenerales.get("precioPosicionamiento")));
			
			f.setCatalogo(reemplazarNulo(this.mapDatosGenerales.get("descripcionCatalogo")));
			f.setNumeroPagina(reemplazarNulo(this.mapDatosGenerales.get("numeroPagina")));
			f.setGrupo(reemplazarNulo(this.mapDatosGenerales.get("numeroGrupo")));
			f.setEstrategia(reemplazarNulo(this.mapDatosGenerales.get("estrategia")));
			
			f.setFactorRepeticion(reemplazarNulo(this.mapDatosGenerales.get("factorRepeticion")));
			f.setIndAplicaComisiones(reemplazarNulo(this.mapDatosGenerales.get("indAplicaComisiones")));
			f.setIndEstadisticable(reemplazarNulo(this.mapDatosGenerales.get("indEstadisticable")));
			f.setIndAplicaPuntajes(reemplazarNulo(this.mapDatosGenerales.get("indAplicaPuntajes")));
			
			f.setProductoReemplazo(reemplazarNulo(this.mapDatosGenerales.get("productoReemplazo")));
			f.setProductoAlternativo(reemplazarNulo(this.mapDatosGenerales.get("productoAlternativo")));
			f.setIndDigitable(reemplazarNulo(this.mapDatosGenerales.get("indDigitable")));
			f.setIndPrincipal(reemplazarNulo(this.mapDatosGenerales.get("indPrincipal")));
			
			//OBTENEMOS LA LISTA DE CODIGO DE VENTA RELACIONADOS
			List listCodigoVentaRelacionados = service.getListCodigoVentaRelacionados(criteria);
			this.preCodigoVentaRelacList= listCodigoVentaRelacionados;
			
			if(listCodigoVentaRelacionados.size() > 4) f.setMostrarScrollHeightPA(true);
			
			//OBTENEMOS LA LISTA DE VENTA EXCLUSIVA
			List listVentaExclusiva = service.getListVentaExclusiva(criteria);
			this.preVentaExclusivaList=listVentaExclusiva;
			
			//OBTENEMOS LA LISTA DE LIMITE DE VENTA
			List listLimiteVenta = service.getListLimiteVenta(criteria);
			this.preLimiteVentaList=listLimiteVenta;
			
			if(listLimiteVenta.size() > 4) f.setMostrarScrollHeightLV(true);
			
			//OBTENEMOS LA LISTA DE CONTROL DE STOCK
			List listControlStock = service.getListControlStock(criteria);
			this.preControlStockList=listControlStock;
			
			if(listControlStock.size() > 4) f.setMostrarScrollHeightCS(true);
			
		} else {
			this.preCodigoVentaRelacList.clear();
			this.preVentaExclusivaList.clear();
			this.preLimiteVentaList.clear();
			this.preControlStockList.clear();
			this.addError("Error", getResourceMessage("consultaPREMatrizFacturacionForm.msg.codigoVentaNoEncontrado"));
		}
		
		
	}
	
	private String reemplazarNulo(Object obj) {
		if(obj == null)
			return "";
		else
			return (String)obj;
	}
	
	// metodo que sale del popup	
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina("mantenimientoSTOSolicitudPostVentaDetalleForm");			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
				
	}
	
	public void iniciaValores()throws Exception{
		this.setViewAtributes();
		this.find();
		
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the preCodigoVentaRelacList
	 */
	public List getPreCodigoVentaRelacList() {
		return preCodigoVentaRelacList;
	}

	/**
	 * @param preCodigoVentaRelacList the preCodigoVentaRelacList to set
	 */
	public void setPreCodigoVentaRelacList(List preCodigoVentaRelacList) {
		this.preCodigoVentaRelacList = preCodigoVentaRelacList;
	}

	/**
	 * @return the preVentaExclusivaList
	 */
	public List getPreVentaExclusivaList() {
		return preVentaExclusivaList;
	}

	/**
	 * @param preVentaExclusivaList the preVentaExclusivaList to set
	 */
	public void setPreVentaExclusivaList(List preVentaExclusivaList) {
		this.preVentaExclusivaList = preVentaExclusivaList;
	}

	/**
	 * @return the preLimiteVentaList
	 */
	public List getPreLimiteVentaList() {
		return preLimiteVentaList;
	}

	/**
	 * @param preLimiteVentaList the preLimiteVentaList to set
	 */
	public void setPreLimiteVentaList(List preLimiteVentaList) {
		this.preLimiteVentaList = preLimiteVentaList;
	}

	/**
	 * @return the preControlStockList
	 */
	public List getPreControlStockList() {
		return preControlStockList;
	}

	/**
	 * @param preControlStockList the preControlStockList to set
	 */
	public void setPreControlStockList(List preControlStockList) {
		this.preControlStockList = preControlStockList;
	}

	/**
	 * @return the mapDatosGenerales
	 */
	public Map getMapDatosGenerales() {
		return mapDatosGenerales;
	}

	/**
	 * @param mapDatosGenerales the mapDatosGenerales to set
	 */
	public void setMapDatosGenerales(Map mapDatosGenerales) {
		this.mapDatosGenerales = mapDatosGenerales;
	}
	
	public String getVarCuv() {
		return varCuv;
	}

	public void setVarCuv(String varCuv) {
		this.varCuv = varCuv;
	}

	public String getCampanya() {
		return campanya;
	}

	public void setCampanya(String campanya) {
		this.campanya = campanya;
	}
	
	
	
	

}
