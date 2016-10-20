package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECControlBoletaRecojoForm;

@ManagedBean
@SessionScoped
public class ReporteRECControlBoletaRecojoAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -865574095552453540L;

	private String formatoReporte;
	private List siccRegionList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECControlBoletaRecojoForm reporteForm = new ReporteRECControlBoletaRecojoForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;

		ReporteRECControlBoletaRecojoForm f = (ReporteRECControlBoletaRecojoForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		f.setCodigoPais(codpais);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", codpais);
		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRECControlBoletaRecojoXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteRECControlBoletaRecojoPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECControlBoletaRecojoForm f= (ReporteRECControlBoletaRecojoForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "T.REGION", "'");

		String orderBy = "ORDER BY ";
		Map criteria = params;
		String codigoPeriodoInicial = f.getCodigoPeriodoInicial();
		String codigoPeriodoFinal = f.getCodigoPeriodoFinal();
		String codigoPais = f.getCodigoPais();

		if(f.getResultadoList().equals("")){
			String result="TODOS";
			f.setRecojoList(result);
			
		}		
		if(f.getRecojoList().equals("")){
			String recojo="TODOS";
			f.setRecojoList(recojo);
			
		}

		if (orderBy.length() < 10)
			orderBy = "";

		params.put("codigoPais", codigoPais);
		params.put("codigoPeriodoInicial", codigoPeriodoInicial);
		params.put("codigoPeriodoFinal", codigoPeriodoFinal);
		params.put("condicionRegion", condicionRegion);
		params.put("recojoList", f.getRecojoList());
		params.put("resultadoList",f.getResultadoList());
		params.put("NroReporte", "");
		params.put("titulo",getResourceMessage("reporteRECControlBoletaRecojoForm.title"));

		return params;
	}

	@Override
	public String setValidarReporte() {
		ReporteRECControlBoletaRecojoForm form = (ReporteRECControlBoletaRecojoForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (fecha1 > fecha2) {
			String mensaje = "El Periodo Final debe ser mayor o igual al Periodo Inicial";
			return mensaje;
		}
		return null;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

}