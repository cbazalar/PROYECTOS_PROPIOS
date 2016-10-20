package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.model.ConsultaHIPDatosCliente;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPReclamosForm;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPRetailForm;

/**
 * The Class ConsultaHIPMetasAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/02/2014
 */
@ManagedBean  
@SessionScoped
public class ConsultaHIPRetailAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	/** The consulta hip datos cliente action. */
	@ManagedProperty(value="#{consultaHIPDatosClienteAction}")
	private ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;
	
	private DataTableModel datatableCabeceraList; 
	
	/** The hip retail periodo list. */
	private List hipRetailDetalleList = new ArrayList();
	private List hipRetailCabeceraList = new ArrayList();
	private List hipRetailPeriodoList = new ArrayList();
	
	/** The Constant ACCION_CABECERA_RETAIL. */
	private static final String ACCION_CABECERA_RETAIL = "CABECERARETAIL";
	
	/** The Constant ACCION_DETALLE_PUNTOS_RETAIL. */
	private static final String ACCION_DETALLE_PUNTOS_RETAIL = "DETALLEPUNTOSRETAIL";
	
	/** The Constant ACCION_DETALLE_RETAIL. */
	private static final String ACCION_DETALLE_RETAIL = "DETALLERETAIL";
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaHIPRetailForm form = new ConsultaHIPRetailForm();
		return form;
	}
	
	/**
	 * Inicializar.
	 */
	public void inicializar(){
		if(log.isDebugEnabled()){
			log.debug("inicializar");
		}
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		ConsultaHIPRetailForm f = (ConsultaHIPRetailForm) this.formBusqueda;
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.getConsultaHIPDatosClienteAction().getHipDtoDatosCliente();
		
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
				+ " / " + dtoDatosCliente.getDescripcionSeccion()+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
		//recuperamos el saldo a pagar para el proximo pedido
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("codigoMarca", dtoDatosCliente.getCodigoMarca());
		criteria.put("codigoCanal", dtoDatosCliente.getCodigoCanal());
		criteria.put("codigoRegion", dtoDatosCliente.getCodigoRegion());
		criteria.put("codigoZona", dtoDatosCliente.getCodigoZona());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("oidCliente", dtoDatosCliente.getOidCliente());

		List listRetail = service.getListPeriodoRetail(criteria);
		
		f.setIndicadorBasparampais(service.getIndicadorBasparampais(criteria));
		this.setHipRetailPeriodoList(listRetail);
		this.find();
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a setViewAttributes");
		}
		
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entro a setFindAttributes");
		}
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.getConsultaHIPDatosClienteAction().getHipDtoDatosCliente();
		ConsultaHIPRetailForm f = (ConsultaHIPRetailForm) this.formBusqueda;
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
				+ " / " + dtoDatosCliente.getDescripcionSeccion()+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		List lista = new ArrayList();
		log.debug("accion:" + accion);
		
		lista = this.getHipRetailPeriodoList();
		
		if(accion!=null){
			String indice = externalContext.getRequestParameterMap().get("parametro");
			if(accion.equals(this.ACCION_CABECERA_RETAIL)){
				
				List listRetail = this.getHipRetailPeriodoList();
				Map mapRetail = (Map)listRetail.get(Integer.parseInt(indice));
				f.setPeriodoProceso(MapUtils.getString(mapRetail,  "periodoProceso", ""));
				f.setTotalUnidades(MapUtils.getString(mapRetail,  "unidadesVenta", ""));
				f.setTotalVenta(MapUtils.getString(mapRetail,  "totalVenta", ""));
				f.setEstado(MapUtils.getString(mapRetail,  "estado", ""));
				f.setPuntos(MapUtils.getString(mapRetail,  "puntos", ""));
				f.setUnidadesDevolucion(MapUtils.getString(mapRetail,  "unidadesDevolucion", ""));
				f.setVentaDevolucion(MapUtils.getString(mapRetail,  "totalDevolucion", ""));
				
				//recuperamos las cabeceras de retail
				Map criteria = new HashMap();
				criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
				criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
				criteria.put("codigoPeriodo", f.getPeriodoProceso());
				
				lista = service.getListCabeceraRetail(criteria);
				
				this.setHipRetailCabeceraList(lista);
				this.datatableCabeceraList = new DataTableModel(lista);
			}
			
			if(accion.equals(this.ACCION_DETALLE_PUNTOS_RETAIL)){
				List listRetail = this.getHipRetailPeriodoList();
				Map mapRetail = (Map) listRetail.get(Integer.parseInt(indice));
				f.setPeriodoProceso(MapUtils.getString(mapRetail,  "periodoProceso", ""));
				f.setTotalUnidades(MapUtils.getString(mapRetail,  "unidadesVenta", ""));
				f.setTotalVenta(MapUtils.getString(mapRetail,  "totalVenta", ""));
				f.setEstado(MapUtils.getString(mapRetail,  "estado", ""));
				f.setPuntos(MapUtils.getString(mapRetail,  "puntos", ""));
				f.setUnidadesDevolucion(MapUtils.getString(mapRetail,  "unidadesDevolucion", ""));
				f.setVentaDevolucion(MapUtils.getString(mapRetail,  "totalDevolucion", ""));
				
				Map criteria = new HashMap();
				criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
				criteria.put("codigoPeriodo", f.getPeriodoProceso());
				
				lista = service.getListDetallePuntosConcursoRetail(criteria);
			}
			
			if(accion.equals(this.ACCION_DETALLE_RETAIL)){
				//pintamos informacion del retail
				List listRetail = this.getHipRetailCabeceraList();
				Map mapRetail = (Map)listRetail.get(Integer.parseInt(indice));
						
				String subAcceso =  MapUtils.getString(mapRetail,  "subAcceso", "");
				String tipoDocumento =  MapUtils.getString(mapRetail,  "tipoDocumento", "");
				String numeroDocumento =  MapUtils.getString(mapRetail,  "numeroDocumento", "");
				String tipoTransaccion =  MapUtils.getString(mapRetail,  "tipoTransaccion", "");		
				f.setNumeroDocumento(numeroDocumento);
				
				//recuperamos las cabeceras de retail
				Map criteria = new HashMap();
				criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
				criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
				criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
				criteria.put("codigoPeriodo", f.getPeriodoProceso());
				criteria.put("subAcceso", subAcceso);
				criteria.put("tipoDocumento", tipoDocumento);
				criteria.put("numeroDocumento", numeroDocumento);
				criteria.put("tipoTransaccion", tipoTransaccion);
				
				lista = service.getListDetalleRetail(criteria);
			}
			
		}
		return lista;
	}

	/**
	 * @param actionEvent
	 */
	public void verDetalle(ActionEvent actionEvent) {
		if(log.isDebugEnabled()){
			log.debug("Entro a verDetalle");
		}
		
		ConsultaHIPDatosCliente dtoDatosCliente = this.getConsultaHIPDatosClienteAction().getHipDtoDatosCliente();
		ConsultaHIPRetailForm f = (ConsultaHIPRetailForm) this.formBusqueda;
		f.setCodConsultora(dtoDatosCliente.getCodigoCliente());
		f.setNomConsultora(dtoDatosCliente.getNombreCompleto());
		f.setDesRegZonTerri(dtoDatosCliente.getDescripcionRegion() + " / " + dtoDatosCliente.getDescripcionZona() 
				+ " / " + dtoDatosCliente.getDescripcionSeccion()+ " / " + dtoDatosCliente.getCodigoTerritorio());
		
		ConsultaHIPDatosClienteService service = (ConsultaHIPDatosClienteService) getBean("scsicc.consultaHIPDatosClienteService");
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		List lista = new ArrayList();
		log.debug("accion:" + accion);
		
		lista = this.getHipRetailPeriodoList();
		
		List listRetail = this.getHipRetailCabeceraList();
		String indice = externalContext.getRequestParameterMap().get("parametro");
		Map mapRetail = (Map)listRetail.get(Integer.parseInt(indice));
				
		String subAcceso =  MapUtils.getString(mapRetail,  "subAcceso", "");
		String tipoDocumento =  MapUtils.getString(mapRetail,  "tipoDocumento", "");
		String numeroDocumento =  MapUtils.getString(mapRetail,  "numeroDocumento", "");
		String tipoTransaccion =  MapUtils.getString(mapRetail,  "tipoTransaccion", "");		
		f.setNumeroDocumento(numeroDocumento);
		
		//recuperamos las cabeceras de retail
		Map criteria = new HashMap();
		criteria.put("codigoPais", dtoDatosCliente.getCodigoPais());
		criteria.put("oidIdioma", dtoDatosCliente.getOidIdioma());
		criteria.put("codigoCliente", dtoDatosCliente.getCodigoCliente());
		criteria.put("codigoPeriodo", f.getPeriodoProceso());
		criteria.put("subAcceso", subAcceso);
		criteria.put("tipoDocumento", tipoDocumento);
		criteria.put("numeroDocumento", numeroDocumento);
		criteria.put("tipoTransaccion", tipoTransaccion);
		
		lista = service.getListDetalleRetail(criteria);
		this.hipRetailDetalleList = lista;
	}
	
	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public List getHipRetailPeriodoList() {
		return hipRetailPeriodoList;
	}

	public void setHipRetailPeriodoList(List hipRetailPeriodoList) {
		this.hipRetailPeriodoList = hipRetailPeriodoList;
	}

	/**
	 * @return the hipRetailCabeceraList
	 */
	public List getHipRetailCabeceraList() {
		return hipRetailCabeceraList;
	}

	/**
	 * @param hipRetailCabeceraList the hipRetailCabeceraList to set
	 */
	public void setHipRetailCabeceraList(List hipRetailCabeceraList) {
		this.hipRetailCabeceraList = hipRetailCabeceraList;
	}

	/**
	 * @return the datatableCabeceraList
	 */
	public DataTableModel getDatatableCabeceraList() {
		return datatableCabeceraList;
	}

	/**
	 * @param datatableCabeceraList the datatableCabeceraList to set
	 */
	public void setDatatableCabeceraList(DataTableModel datatableCabeceraList) {
		this.datatableCabeceraList = datatableCabeceraList;
	}

	/**
	 * @return the hipRetailDetalleList
	 */
	public List getHipRetailDetalleList() {
		return hipRetailDetalleList;
	}

	/**
	 * @param hipRetailDetalleList the hipRetailDetalleList to set
	 */
	public void setHipRetailDetalleList(List hipRetailDetalleList) {
		this.hipRetailDetalleList = hipRetailDetalleList;
	}	
}
