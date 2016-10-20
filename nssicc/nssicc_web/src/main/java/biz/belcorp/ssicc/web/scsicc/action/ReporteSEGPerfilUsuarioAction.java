/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSEGPerfilUsuarioForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ReporteSEGPerfilUsuarioAction extends BaseReporteAbstractAction{
	
	private String formato ;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {

		ReporteSEGPerfilUsuarioForm form = new ReporteSEGPerfilUsuarioForm ();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteSEGPerfilUsuarioXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the formato
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * @param formato the formato to set
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

}
