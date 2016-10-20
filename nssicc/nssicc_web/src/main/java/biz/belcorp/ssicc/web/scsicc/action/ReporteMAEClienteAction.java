package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAEClienteForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAENuevasForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteMAEClienteAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private List subTiposCliente = new ArrayList();
	private List listZonas = new ArrayList();
	private List listTerritorios = new ArrayList();
	private List indicadorActivosList = new ArrayList();
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAEClienteForm form = new ReporteMAEClienteForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveNombreReporte");
		}
		ReporteMAENuevasForm form = (ReporteMAENuevasForm)this.formReporte;		
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteMAEClienteXLS";
		}
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		
		ReporteMAEClienteForm form = (ReporteMAEClienteForm) this.formReporte;
		this.mostrarCabeceraFija = true;
		this.mostrarBotonBuscar = true;
		this.mostrarListaBusqueda = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
				
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		MantenimientoMAEClienteService mantenimientoMAEClienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
		
		//pone en session la lista de tipo subtipo        
        this.setSubTiposCliente(mantenimientoMAEClienteService.getSubTiposClienteInsertar(criteria));
		this.setListZonas(mantenimientoMAEClienteService.getZonasByPaisMarcaCanal(criteria));
		
		List resultado = new ArrayList();
		Base[] mes = new Base[3];
		
		String activo = this.getResourceMessage("reporteMAEClienteForm.activo");
		String inactivo = this.getResourceMessage("reporteMAEClienteForm.inactivo");
		
		mes[0] = new Base();
		mes[0].setCodigo("");
		mes[0].setDescripcion("");
		resultado.add(mes[0]);
		
		mes[1] = new Base();
		mes[1].setCodigo(Constants.NUMERO_UNO);
		mes[1].setDescripcion(activo);
		resultado.add(mes[1]);
		
		mes[2] = new Base();
		mes[2].setCodigo(Constants.NUMERO_CERO);
		mes[2].setDescripcion(inactivo);
		resultado.add(mes[2]);
		
		this.setIndicadorActivosList(resultado);
		
		form.setLongitudCodigoCliente(mantenimientoMAEClienteService.getLongitudCodigoCliente(criteria));
		
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteMAEClienteForm form = (ReporteMAEClienteForm) this.formReporte;
	    
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		//Eliminar registros del temporal segun Usuario
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		clienteService.deleteReporteClienteTemporal(usuario.getLogin());
		//--------------------------------------------------------------
		
		//Obtenemos el resultado de la busqueda a insertar en la tabla temporal
		List resultado = clienteService.getListClientesByCriteria(getCriteriaSearch(form));
		log.debug("Pintando el tamaño de la lista " + resultado.size());
		
		clienteService.insertReporteClienteTemporal(resultado, usuario);
		//-----------------------------------------------------------------
				
		return resultado;
	}
	
	private Map getCriteriaSearch(ReporteMAEClienteForm form) {
		Map criteria = new HashMap();
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		String subtipo = form.getSubTipoCliente();
		String []split = StringUtils.split(subtipo, "-");
		String oidTipoCliente = (StringUtils.isNotEmpty(subtipo)?split[2]:"");
		String oidSubTipoCliente = (StringUtils.isNotEmpty(subtipo)?split[3]:"");
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("oidPais", clienteService.getOidPais(criteria));		
		criteria.put("codigoCliente", form.getCodigoCliente());
		criteria.put("nombre1", form.getNombre1());
		criteria.put("nombre2", form.getNombre2());
		criteria.put("apellido1", form.getApellido1());
		criteria.put("apellido2", form.getApellido2());
		criteria.put("codigoZona", form.getCodigoZona());
		criteria.put("codigoTerritorio", form.getCodigoTerritorio());
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("activo",form.getIndicadorActivo());
		criteria.put("compromiso",form.getCompromiso());
		
		return criteria;
	}
	
	public void loadTerritorios(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadTerritorios");
		}
		String valor = (String)val.getNewValue();
		if(StringUtils.isNotBlank(valor) && StringUtils.isNotEmpty(valor)){
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			LabelValue[] territorios = ajaxService.getTerritoriosByPaisMarcaCanalZona(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
												Constants.CODIGO_MARCA_DEFAULT, 
												Constants.CODIGO_CANAL_DEFAULT, 
												valor);
			listTerritorios = Arrays.asList(territorios);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		params.put("login", usuario.getLogin());
		return params;
	}
	
	public List getSubTiposCliente() {
		return subTiposCliente;
	}

	public void setSubTiposCliente(List subTiposCliente) {
		this.subTiposCliente = subTiposCliente;
	}

	public List getListZonas() {
		return listZonas;
	}

	public void setListZonas(List listZonas) {
		this.listZonas = listZonas;
	}

	public List getIndicadorActivosList() {
		return indicadorActivosList;
	}

	public void setIndicadorActivosList(List indicadorActivosList) {
		this.indicadorActivosList = indicadorActivosList;
	}

	public List getListTerritorios() {
		return listTerritorios;
	}

	public void setListTerritorios(List listTerritorios) {
		this.listTerritorios = listTerritorios;
	}
	
}
