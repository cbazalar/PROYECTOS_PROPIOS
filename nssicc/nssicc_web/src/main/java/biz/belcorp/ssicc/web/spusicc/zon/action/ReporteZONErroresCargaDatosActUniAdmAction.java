package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ReporteZONErroresCargaDatosActUniAdmForm;

@ManagedBean
@SessionScoped
public class ReporteZONErroresCargaDatosActUniAdmAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -6065715435413409820L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONErroresCargaDatosActUniAdmForm form =  new ReporteZONErroresCargaDatosActUniAdmForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteZONErroresCargaDatosActUniAdm";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		params.put("NroReporte", "REP-ZON03");
		params.put("titulo","Errores Carga de Datos Actualizacion de Unidades Administrativas");
		params.put("formatoExportacion", "PDF");
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		
	}

}
