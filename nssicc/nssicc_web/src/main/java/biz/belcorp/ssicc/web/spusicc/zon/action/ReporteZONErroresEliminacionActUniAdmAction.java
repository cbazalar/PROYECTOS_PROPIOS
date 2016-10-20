package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.zon.form.ReporteZONErroresEliminacionActUniAdmForm;

@ManagedBean
@SessionScoped
public class ReporteZONErroresEliminacionActUniAdmAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -3233109563137561195L;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONErroresEliminacionActUniAdmForm form =  new ReporteZONErroresEliminacionActUniAdmForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteZONErroresEliminacionActUniAdm";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		params.put("NroReporte", "REP-ZON05");
		params.put("titulo","Errores Eliminacion Actualizacion de Unidades Administrativas");
		params.put("formatoExportacion", "PDF");
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
