package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ProcesoVENLibroVentasDetalleSIIForm;


@ManagedBean
@SessionScoped
public class ProcesoVENLibroVentasDetalleSIIAction extends BaseReporteAbstractAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1147021642902310272L;
	private String formatoReporte;
	
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteOZIP = true;
		ProcesoVENLibroVentasDetalleSIIForm f = (ProcesoVENLibroVentasDetalleSIIForm) this.formReporte;
		f.reset();
	}
	
	@Override
	protected Map prepareParameterMap(Map parameterMap) throws Exception {
		ProcesoVENLibroVentasDetalleSIIForm form1 = (ProcesoVENLibroVentasDetalleSIIForm) this.formReporte;

		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(form1.getFechaDesdeD());
		fecha2 =  DateUtil.getDate(form1.getFechaHastaD());		
		
		form1.setFechaDesde(fecha1);
		form1.setFechaHasta(fecha2);
		
		this.formatoReporte = form1.getFormatoExportacion();
		
		Usuario usuario = (Usuario) parameterMap.get("usuario");
		parameterMap.put("login", usuario.getLogin());
		
		parameterMap.put("codigoPais", form1.getCodigoPais());
		
		parameterMap.put("fechaDesde",DateUtil.convertDateToString(Constants.PATRON_FECHA_AAAAMMDD,DateUtil.convertStringToDate(form1.getFechaDesde()))); 
		parameterMap.put("fechaHasta",DateUtil.convertDateToString(Constants.PATRON_FECHA_AAAAMMDD,DateUtil.convertStringToDate(form1.getFechaHasta())));
		
		parameterMap.put("accion", form1.getAccion());
		parameterMap.put("tipoReporte", form1.getTipoReporteAMostrar());
		parameterMap.put("usuario", parameterMap.get("login"));
		
		log.debug("Los parametros del Generar en el executeProcess son: "
				+ parameterMap.toString());
		return parameterMap;
	}	

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		String error = "";
		ProcesoVENLibroVentasDetalleSIIForm form1 = (ProcesoVENLibroVentasDetalleSIIForm) this.formReporte;
		Date fechaDesdeD = form1.getFechaDesdeD();
		Date fechaHastaD = form1.getFechaHastaD();
		
		if (fechaDesdeD.after(fechaHastaD)) {
			error = this.getResourceMessage("errors.compare.dates");
			return error;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.procesoVENLibroVentasDetalleSIIService";
	}



	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ProcesoVENLibroVentasDetalleSIIForm form = new ProcesoVENLibroVentasDetalleSIIForm();
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

	

	
	

	
}
