package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoCuponesPendientesConciliacionForm;

@ManagedBean
@SessionScoped
public class ReporteCCCDetalladoCuponesPendientesConciliacionAction extends
		BaseReporteAbstractAction implements Serializable {

	private List siccSociedadList = new ArrayList();

	private static final long serialVersionUID = 2420430768317975582L;

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteCCCDetalladoCuponesPendientesConciliacionForm form =  new ReporteCCCDetalladoCuponesPendientesConciliacionForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteCCCDetalladoCuponesPendientesConciliacion" + "DXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		// ReporteCCCDetalladoCuponesPendientesConciliacionForm reporteCCCForm =
		// (ReporteCCCDetalladoCuponesPendientesConciliacionForm) form;
		ReporteCCCDetalladoCuponesPendientesConciliacionForm f = (ReporteCCCDetalladoCuponesPendientesConciliacionForm) this.formReporte;
		
		String fecha1,fecha2;
		
		fecha1 = DateUtil.getDate(f
				.getFechaCuponDesdeD());
		fecha2 = DateUtil.getDate(f
				.getFechaCuponHastaD());
			
		f.setFechaCuponDesde(fecha1);
		f.setFechaCuponHasta(fecha2);

		// BigDecimal importeCuponDesde = new
		// BigDecimal(reporteCCCForm.getImporteCuponDesde());
		// BigDecimal importeCuponDesde = new
		// BigDecimal(reporteCCCForm.getImporteCuponDesde());
		// params.put("importeCuponDesde",
		// reporteCCCForm.getImporteCuponDesde());

		log.debug(" JFA Imprimiendo parmetros");
		log.debug(params);
		log.debug("JFA Fin parmetros");
		return params;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
}