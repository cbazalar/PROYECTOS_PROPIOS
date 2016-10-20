package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import net.sf.jasperreports.engine.JRParameter;


import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBRecuperacionCobranzaFFVVCaribeForm;


/**
 * 
 * @author <a href="mailto:ghuertas@belcorp.biz">Gonzalo Huertas</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOBRecuperacionCobranzaFFVVCaribeAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;
		
    private String codigoIdiomaISO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBRecuperacionCobranzaFFVVCaribeForm form = new ReporteCOBRecuperacionCobranzaFFVVCaribeForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCOBRecuperacionCobranzaFFVVCaribeForm form = (ReporteCOBRecuperacionCobranzaFFVVCaribeForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())){
			if(StringUtils.equalsIgnoreCase(form.getTipoReporte(),"C31D"))
				return "reporteCOBRecuperacionCobranzaFFVVCaribeC31DXLS";
			else
				if(StringUtils.equalsIgnoreCase(form.getTipoReporte(),"C33D"))
					return "reporteCOBRecuperacionCobranzaFFVVCaribeC33DXLS";
				else
					return null;
		}			
		else
		   return "reporteMaestroHorizontal";
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
		ReporteCOBRecuperacionCobranzaFFVVCaribeForm reporteCOBForm = (ReporteCOBRecuperacionCobranzaFFVVCaribeForm) this.formReporte;

		params.put("codigoPais",reporteCOBForm.getCodigoPais());
		params.put("codigoPeriodo",reporteCOBForm.getCodigoPeriodo());


		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		if(log.isDebugEnabled()){
			log.debug("Entro a ReporteCOBRecuperacionCobranzaFFVVCaribeAction - setViewAttributes");	
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteCOBRecuperacionCobranzaFFVVCaribeForm reporteCOBForm = (ReporteCOBRecuperacionCobranzaFFVVCaribeForm) this.formReporte;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		this.codigoIdiomaISO = usuario.getIdioma().getCodigoISO();
		reporteCOBForm.setCodigoPais(pais.getCodigo());


		log.info("Salio a ReporteCOBRecuperacionCobranzaFFVVCaribeAction - setViewAttributes");
	
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

		
}