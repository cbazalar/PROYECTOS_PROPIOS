/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteOCRPedidosGP1SinErrorForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteOCRMontoMaximoAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 14/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteOCRPedidosGP1SinErrorAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteOCRPedidosGP1SinErrorForm form = new ReporteOCRPedidosGP1SinErrorForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}		
		
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReportePDF = false;
		
		ReporteOCRPedidosGP1SinErrorForm f = (ReporteOCRPedidosGP1SinErrorForm)this.formReporte;
		
		Date date = new Date();
		f.setFechaInicialDate(date);
		f.setFechaFinalDate(date);
		f.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaInicialDate()));
		f.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaFinalDate()));
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes...");
		}
		
		ReporteOCRPedidosGP1SinErrorForm f = (ReporteOCRPedidosGP1SinErrorForm)this.formReporte;
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		
		if(f.getFechaInicialDate() != null){
			f.setFechaInicial(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaInicialDate()));
		}
		
		if(f.getFechaFinalDate() != null){
			f.setFechaFinal(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, f.getFechaFinalDate()));
		}
		
		//-- Crear pojo
		Map criteria =  new HashMap();
		criteria.put("fechaInicial", f.getFechaInicial());
		criteria.put("fechaFinal", f.getFechaFinal());
		criteria.put("tipoSolicitud", Constants.TIPO_SOLICITUD_SOC);
		
		//-- Logica negocio
		List lista = service.getReporteOCRPedidosGP1SinError(criteria);
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		if(log.isDebugEnabled()){
			log.debug("setValidarFind");
		}
		
		ReporteOCRPedidosGP1SinErrorForm form = (ReporteOCRPedidosGP1SinErrorForm) this.formReporte;
		
		if(form.getFechaInicialDate()!=null && form.getFechaFinalDate()!=null){
			if(form.getFechaInicialDate().compareTo(form.getFechaFinalDate())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
		
				return this.getMensajeAlertaDefault();
			}
		}
		
		return null;
	}
	
}