// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCMediosMagneticosForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCCCMediosMagneticosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCMediosMagneticosForm reporteForm = new ReporteCCCMediosMagneticosForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCMediosMagneticosAction.prepareParameterMap' method");
		}
		ReporteCCCMediosMagneticosForm f = (ReporteCCCMediosMagneticosForm) this.formReporte;
		f.setFechaDesde(DateUtil.convertDateToString(f.getFechaDesdeD()));
		f.setFechaHasta(DateUtil.convertDateToString(f.getFechaHastaD()));
		f.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());

		Usuario usuario = (Usuario) params.get("usuario");
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoUsuario", usuario.getLogin());
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		log.debug("Los parametros del Generar en el executeProcess son: "
				+ params.toString());

		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCCCMediosMagneticosAction.setViewAtributes' method");
		}
		this.mostrarReporteXLS = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteOZIP = true;
		// servicios
		ReporteCCCMediosMagneticosForm form = (ReporteCCCMediosMagneticosForm) this.formReporte;
		// beans principales

		// reset
		form.setCodigoPais("");
		form.setFechaDesde("");
		form.setFechaHasta("");
		form.setTipoReporteAMostrar("");
		log.debug("Todo Ok: Redireccionando");
	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCMediosMagneticosService";
	}
	
	public String setValidarReporte() {
		ReporteCCCMediosMagneticosForm reporteRETForm = (ReporteCCCMediosMagneticosForm) this.formReporte;
	    if (reporteRETForm.getFechaDesdeD().compareTo(reporteRETForm.getFechaHastaD()) > 0) 
				return getResourceMessage("reporteRETDetalleComisionVentaRetailForm.validar.fechas");
			
	    return null;
	}


}
