package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOEnvioNotificacionUsuariosForm;

/**
 * The Class ReporteOCRMasVeinteUnidadesAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 26/08/2014
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTOEnvioNotificacionUsuariosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccSeccionList= {};
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOEnvioNotificacionUsuariosForm form = new ReporteSTOEnvioNotificacionUsuariosForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTOEnvioNotificacionUsuarioPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");			
		}
		this.mostrarReporteXLS = true;
		ReporteSTOEnvioNotificacionUsuariosForm form = (ReporteSTOEnvioNotificacionUsuariosForm)formReporte ;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais",this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		  
		MantenimientoOCRPedidoControlFacturacionService service = 
				(MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setCodigoPais(pais.getCodigo());
		
		this.siccZonaList = new LabelValue[1];
		LabelValue labelValue = new LabelValue();
		labelValue.setLabel("Todos");
		labelValue.setValue("");
		this.siccZonaList[0] = labelValue;
		
		this.siccSeccionList = new LabelValue[1];
		LabelValue labelValueS = new LabelValue();
		labelValueS.setLabel("Todos");
		labelValueS.setValue("");
		this.siccSeccionList[0] = labelValueS;
		
	}
	
    /**
	 * @param Muestra las zonas por regiones escogidas.
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		
		log.debug(">>showZonasxRegion ");
		ReporteSTOEnvioNotificacionUsuariosForm form = (ReporteSTOEnvioNotificacionUsuariosForm) this.formReporte;
		form.setSeccionList(null);
		this.siccSeccionList = null;
		try {
			String [] regiones = (String [])val.getNewValue();
			if(!val.equals(null) && regiones.length > 0 ){
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
						Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
				form.setZonaList(null);	
			}else {
				this.siccSeccionList = null;
				this.siccZonaList= null;
				form.setZonaList(null);
				form.setSeccionList(null);
				
				this.siccZonaList = new LabelValue[1];
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel("Todos");
				labelValue.setValue("");
				this.siccZonaList[0] = labelValue;
				
				this.siccSeccionList = new LabelValue[1];
				LabelValue labelValueS = new LabelValue();
				labelValueS.setLabel("Todos");
				labelValueS.setValue("");
				this.siccSeccionList[0] = labelValueS;
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
			
	}
	
	/**
	 * @param Muestra las secciones por las zonas seleccionadas.
	 */
	public void showSeccionxZona(ValueChangeEvent val){
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteSTOEnvioNotificacionUsuariosForm form = (ReporteSTOEnvioNotificacionUsuariosForm) this.formReporte;
			
			String[] regiones = (String [])form.getRegionList();
			
			String[] zonas = (String [])val.getNewValue();
			
			if(!val.equals(null) && zonas.length > 0 ){
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccSeccionList(aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones, zonas, Constants.FORMATO_TOTAL));
				form.setSeccionList(null);
			}else {
				this.siccSeccionList = null;
				form.setSeccionList(null);
				
				this.siccSeccionList = new LabelValue[1];
				LabelValue labelValueS = new LabelValue();
				labelValueS.setLabel("Todos");
				labelValueS.setValue("");
				this.siccSeccionList[0] = labelValueS;
			}		
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTOEnvioNotificacionUsuariosForm form = (ReporteSTOEnvioNotificacionUsuariosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if (StringUtils.equals(form.getFormatoExportacion(),"XLS"))
			return "reporteSTOEnvioNotificacionUsuarioXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSTOEnvioNotificacionUsuariosForm f = (ReporteSTOEnvioNotificacionUsuariosForm) this.formReporte;
		
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		
		String condicionZonas ="";	
		if (f.getZonaList() != null) condicionZonas = obtieneCondicion( f.getZonaList(),"dd.cod_zona", "'");
		String condicionRegion ="";
		if (f.getRegionList() != null) condicionRegion=		obtieneCondicion(f.getRegionList(), "dd.cod_regi", "'");
		String condicionSeccion ="";
		if (f.getSeccionList() != null)
		condicionSeccion= obtieneCondicion(f.getSeccionList(), "gen_pkg_gener.GEN_FN_CLIEN_DATOS(dd.cod_clie,'COD_SECC')", "'");
		String condicion = condicionZonas + condicionRegion+condicionSeccion;
		
		String descripcionRegionList = descripcionMultipleLista(f.getRegionList(), this.siccRegionList);
		String descripcionZonaList = descripcionMultipleLista(f.getZonaList(), this.siccZonaList);
		String descripcionSeccionList = descripcionMultipleLista(f.getSeccionList(), this.siccSeccionList);
		
		params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("condicion", condicion);
		
		String codigoStatus = " WHERE datos.ind_paso_pedi IN (0,1)";
		if(f.getCodStatus().equals("SI")) codigoStatus = " WHERE datos.ind_paso_pedi = 1";
		if(f.getCodStatus().equals("NO")) codigoStatus = " WHERE datos.ind_paso_pedi = 0 ";
		params.put("codigoStatus", codigoStatus);
		params.put("descripcionStatus", f.getCodStatus());
		
		params.put("listaZona", "");
		if (f.getZonaList() != null) params.put("listaZona", obtenerListas(f.getZonaList()));
		params.put("listaRegion","");
		if (f.getRegionList() != null)	params.put("listaRegion", obtenerListas(f.getRegionList()));
		params.put("listaSeccion","");
		if (f.getSeccionList() != null)	params.put("listaSeccion", obtenerListas(f.getSeccionList()));
		
		params.put("NroReporte", "");
		params.put("titulo", this.getResourceMessage("reporteSTOEnvioNotificacionUsuariosForm.title"));
		

		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);
		params.put("descripcionSeccionList", descripcionSeccionList);
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes...");
		}
		ReporteSTOEnvioNotificacionUsuariosForm f = (ReporteSTOEnvioNotificacionUsuariosForm) formReporte;

		Map criteria = BeanUtils.describe(f);

		// La busqueda solo la realizaremos en los sistemas activos
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		if (log.isDebugEnabled()) {
			log.debug("criteria search " + criteria.toString());
		}

		List lista = service.getOCRConsultorasInactivasList(criteria);		
		return lista;
	}
	
	/**
	 * @param arregloList
	 * @return Cadena
	 */
	private String obtenerListas(String[] arregloList) {
		String listaCadena = "";
		for(int i=0; i < arregloList.length; i++)
  		  listaCadena += arregloList[i] + ", ";
		return listaCadena;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}