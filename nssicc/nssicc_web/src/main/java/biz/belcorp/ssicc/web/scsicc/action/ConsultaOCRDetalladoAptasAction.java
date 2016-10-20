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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaOCRDetalladoAptasForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ConsultaOCRDetalladoAptasAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccZonaList = {};

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaOCRDetalladoAptasForm form = new ConsultaOCRDetalladoAptasForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
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
		
		this.mostrarReportePDF = false;
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
		
        ConsultaOCRDetalladoAptasForm form = (ConsultaOCRDetalladoAptasForm)this.formReporte ;
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
        form.setFechaFact(controlFacturacion.getFechaProceso());
        form.setPeriodo(controlFacturacion.getCodigoPeriodo());
        form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        form.setCodigoPaisSearch(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        
        // Carga zonas y regiones
        ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
        
        Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
									     
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()];
			int i = 0;
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}

		ConsultaOCRDetalladoAptasForm form = (ConsultaOCRDetalladoAptasForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		List lista = service.getOCRDetalladoAptasList(getCriteria());
		
		return lista;
	}

	/**
	 * Cambia zonas by region.
	 *
	 * @param val the val
	 */
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		String valor = (String) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ConsultaOCRDetalladoAptasForm form = (ConsultaOCRDetalladoAptasForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasByPaisRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), valor));
	}
	
	private Map getCriteria() throws Exception{
		ConsultaOCRDetalladoAptasForm form = (ConsultaOCRDetalladoAptasForm)this.formReporte;    	
        Map criteria = BeanUtils.describe(form);
        
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		ArrayList arrZonas = new ArrayList();
		if(form.getCodZonas()!=null){
			for (int i = 0; i < form.getCodZonas().length; i++) {
				if(StringUtils.isNotBlank(form.getCodZonas()[i])){
					log.debug("---> zona "+form.getCodZonas()[i]);		
					arrZonas.add(form.getCodZonas()[i]);
				}
			}
		}	
		
		criteria.put("periodo", form.getPeriodo());        		
        criteria.put("fechaFacturacion", form.getFechaFact());        
        
        if(StringUtils.isNotBlank(form.getCodigoConsultora())) {
            criteria.put("codConsultora", form.getCodigoConsultora());
        }
                               
        if(form.getCodRegion()!=null){
	        if(!form.getCodRegion().equals(Constants.OPCION_TODOS)){	        	
	        	if(!form.getCodRegion().equals("")){
	        		criteria.put("region", form.getCodRegion());
	        	}
	        }
        }
               
        if(arrZonas.size()!=0){                	        	
        	criteria.put("zona", arrZonas);	        		        
        }
        
                        
        return criteria;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}				
}
