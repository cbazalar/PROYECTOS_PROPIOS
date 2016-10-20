package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoSaldosMenoresAcreedoresForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ReporteCCCDetalladoSaldosMenoresAcreedoresAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 3220446824523753543L;

	private String formatoReporte;
	private List siccsociedadList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.siccsociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCDetalladoSaldosMenoresAcreedoresForm reporteForm = new ReporteCCCDetalladoSaldosMenoresAcreedoresForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteCCCDetalladoSaldosMenoresAcreedoresForm) this.formReporte)
				.getFormatoExportacion();
		return "reporteCCCDetalladoSaldosMenoresAcreedores" + "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCDetalladoSaldosMenoresAcreedoresAction.prepareParameterMap' method");
		}

		return params;
	}

	/**
	 * @return
	 */
	public List getSiccsociedadList() {
		return siccsociedadList;
	}

	/**
	 * @param siccsociedadList
	 */
	public void setSiccsociedadList(List siccsociedadList) {
		this.siccsociedadList = siccsociedadList;
	}
}