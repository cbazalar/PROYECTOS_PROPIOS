package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaOCRPedidosDuplicadosForm;


/**
 * The Class ConsultaOCRPedidosDuplicadosAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/09/2014
 */
@ManagedBean
@SessionScoped
public class ConsultaOCRPedidosDuplicadosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaOCRPedidosDuplicadosForm form = new ConsultaOCRPedidosDuplicadosForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ConsultaOCRPedidosDuplicadosForm form = (ConsultaOCRPedidosDuplicadosForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		String valor = "";
		if ("XLS".equals(form.getFormatoExportacion())) {			
			valor =  "reporteMaestroHorizontal";
		}
		return valor;
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
		
		this.mostrarBotonBuscar = true;		
		this.mostrarReportePDF = false;
		this.mostrarListaBusqueda = true;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
		
        ConsultaOCRPedidosDuplicadosForm form = (ConsultaOCRPedidosDuplicadosForm)this.formReporte;
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
        form.setFechaFact(controlFacturacion.getFechaProceso());
        if(StringUtils.isNotBlank(form.getFechaFact()) && StringUtils.isNotEmpty(form.getFechaFact())){
        	form.setFechaFactDate(DateUtil.convertStringToDate(Constants.DEFAULT_DATE_FORMAT,form.getFechaFact()));
        }
        form.setPeriodo(controlFacturacion.getCodigoPeriodo());
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes");
		}

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		
		List lista = service.getOCRPedidosDuplicadosList(getCriteria());
		
		if(lista.size() > 0){
			this.mostrarReporteXLS = true;
		}else{
			this.mostrarReporteXLS = false;
		}
		
		return lista;
	}
	
	private Map getCriteria() throws Exception{
		ConsultaOCRPedidosDuplicadosForm form = (ConsultaOCRPedidosDuplicadosForm)this.formReporte;
        Map criteria = BeanUtils.describe(form);
        
		criteria.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteria.put("periodo", form.getPeriodo());
        criteria.put("fechaFacturacion", form.getFechaFact());
        
        return criteria;
	}	
	
}
