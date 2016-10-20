package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DetalleOferta;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.BusquedaSTOCantidadDevuelveForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSolicitudPostVentaDetalleForm;

@ManagedBean
@SessionScoped
public class BusquedaSTOCantidadDevuelveAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -4463857597716127779L;
	
	private List stoCantidadDevuelveList;
	private List stoCantidadDevuelveDetalleList;
	private List stoRetornaDetalleOfertaList;
	private List stoRetornaDetalleMoviList;
	
	private String oidSoliDevuelve;
	private String codPeriodo;
	private String codigoCliente;

	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaSTOCantidadDevuelveForm form = new BusquedaSTOCantidadDevuelveForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=true;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonModificar=false;		
	}
	
	public void iniciaValores(){
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();		
		BusquedaSTOCantidadDevuelveForm f = (BusquedaSTOCantidadDevuelveForm)this.formBusqueda;
		String foidoidSoliDevuelve=this.getOidSoliDevuelve();
		String fcodPeriodo=this.getCodPeriodo();
		String fcodigoCliente=this.getCodigoCliente();
		f.setCodigoCliente(fcodigoCliente);
		
		List detOferta = null;		
		Map criteria = new HashMap();
		criteria.put("oidSoliDevuelve",foidoidSoliDevuelve);
		log.debug("oidSoliCabe: --->"+oidSoliDevuelve);		

		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");		
		List lista = service.getCantidadDevuelveList(criteria);
		this.stoCantidadDevuelveList=lista;		
		
		criteria.put("codPeriodo", fcodPeriodo);
		criteria.put("codigoCliente", fcodigoCliente);
		criteria.put("pais",pais.getCodigo());
		
		List detalle = service.getCantidadDevuelveDetalleList(criteria);
		this.stoCantidadDevuelveDetalleList=detalle;		
		
		DetalleOferta beanDetOferta = service.getValoresOidDetalleOferta(criteria);
		
		if (beanDetOferta!=null){
			criteria.put("oidProd", beanDetOferta.getOidProd());
			criteria.put("oidSoliCabe", beanDetOferta.getOidSoliCabe());
			criteria.put("oidOfer", beanDetOferta.getOidOfer());
			log.debug("OID PROD --->"+ beanDetOferta.getOidProd());
			log.debug("OID SOLI CABE --->"+  beanDetOferta.getOidSoliCabe());
			log.debug("OID OFER --->"+  beanDetOferta.getOidOfer());
		}else{
			criteria.put("oidProd", "0");
			criteria.put("oidSoliCabe", "0");
			criteria.put("oidOfer", "0");
		}

		criteria.put("nombreTablaTipoOfer", Constants.CDR_NOMBRE_TABLA_TIPO_OFER);
		criteria.put("oidIdioma", Constants.CDR_OID_IDIOMA);
		
		detOferta = service.getDetalleOfertaList(criteria);
		this.stoRetornaDetalleOfertaList=detOferta;		
	}
	
	public void abrirVentanaDetalle(ActionEvent event){
		try {
			this.consulta();
			this.redireccionarPagina("consultaSTOCantidadDevuelveForm");	
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void consulta(){	
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		BusquedaSTOCantidadDevuelveForm f = (BusquedaSTOCantidadDevuelveForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService service = 
				(ProcesoSTOEjecucionValidacionesService)getBean("spusicc.procesoSTOEjecucionValidacionesService");			
		String codigoPais   = pais.getCodigo();		
		
		ExternalContext externalContext = FacesContext.getCurrentInstance()	.getExternalContext();
		String codigoVenta = externalContext.getRequestParameterMap().get("codVenta").toString();
		String oidSoliPosi = externalContext.getRequestParameterMap().get("oidSoliPosi").toString();
		String descripcionProducto = externalContext.getRequestParameterMap().get("descProd").toString();

		f.setCodigoVenta(codigoVenta);
		f.setDescripcionProducto(descripcionProducto);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoCliente", codigoCliente);
		criteria.put("codigoPeriodo", codPeriodo);
		criteria.put("oidSoliPosi",oidSoliPosi);
		criteria.put("nombreTablaTipoSolic",Constants.CDR_NOMBRE_TABLA_PED_TIPO_SOLIC);
		
		// Lógica de Negocio
		
		List listaDetalle = service.getDetalleMovimientoProductoList(criteria);
		this.stoRetornaDetalleMoviList=listaDetalle;	
	}
	
	//salir de la pantalla Consulta Detalle	
	public void salirVentanaDetalle(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("busquedaSTOCantidadDevuelveForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	public void salirVentana(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("mantenimientoSTOSolicitudPostVentaDetalleForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public List getStoCantidadDevuelveList() {
		return stoCantidadDevuelveList;
	}

	public void setStoCantidadDevuelveList(List stoCantidadDevuelveList) {
		this.stoCantidadDevuelveList = stoCantidadDevuelveList;
	}

	public List getStoCantidadDevuelveDetalleList() {
		return stoCantidadDevuelveDetalleList;
	}

	public void setStoCantidadDevuelveDetalleList(
			List stoCantidadDevuelveDetalleList) {
		this.stoCantidadDevuelveDetalleList = stoCantidadDevuelveDetalleList;
	}

	public List getStoRetornaDetalleOfertaList() {
		return stoRetornaDetalleOfertaList;
	}

	public void setStoRetornaDetalleOfertaList(List stoRetornaDetalleOfertaList) {
		this.stoRetornaDetalleOfertaList = stoRetornaDetalleOfertaList;
	}

	public String getOidSoliDevuelve() {
		return oidSoliDevuelve;
	}

	public void setOidSoliDevuelve(String oidSoliDevuelve) {
		this.oidSoliDevuelve = oidSoliDevuelve;
	}

	public String getCodPeriodo() {
		return codPeriodo;
	}

	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public List getStoRetornaDetalleMoviList() {
		return stoRetornaDetalleMoviList;
	}

	public void setStoRetornaDetalleMoviList(List stoRetornaDetalleMoviList) {
		this.stoRetornaDetalleMoviList = stoRetornaDetalleMoviList;
	}
	


}
