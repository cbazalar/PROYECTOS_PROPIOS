package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETBajasPorSeccionForm;

@ManagedBean
@SessionScoped
public class ReporteLETBajasPorSeccionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -7026421572291202428L;
	private List lecProgramaCorporativoList;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteLETBajasPorSeccionForm reporteForm = new ReporteLETBajasPorSeccionForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteLETResumenPagosPendientesAction - setViewAtributes");
		}
		
		MantenimientoLECProgramaCorporativoService service = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");		
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry() ;
		MantenimientoOCRPedidoControlFacturacionService serviceCampania = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

		ReporteLETBajasPorSeccionForm f = (ReporteLETBajasPorSeccionForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		Map criteriaCampania = new HashMap();
		criteriaCampania.put("codigoPais", pais.getCodigo());
		criteriaCampania.put("estadoCampanha", "0");
		criteriaCampania.put("indicadorActiva", "1");
		List lista = serviceCampania.getCampanhasActivasByCriteria(criteriaCampania);
		if (lista.size() == 1) {
			f.setCodigoPeriodo((String) lista.get(0));
		}
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());		
		
	 	List programaCorporativoList = service.getProgramaCorporativoList(criteria);
	 	
	 	this.lecProgramaCorporativoList = programaCorporativoList;
	 	this.mostrarReportePDF = false;
	 	this.mostrarReporteXLS = true;
		log.debug("Todo OK: Redireccionando");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formReporte.getFormatoExportacion())){
			return "reporteLETBajasPorSeccionXLS";
		}
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteLETBajasPorSeccionForm f = (ReporteLETBajasPorSeccionForm) this.formReporte;
		
		params.put("codigoPais",f.getCodigoPais());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("codigoPrograma", f.getCodigoPrograma());
		return params;
	}

	/**
	 * @return the lecProgramaCorporativoList
	 */
	public List getLecProgramaCorporativoList() {
		return lecProgramaCorporativoList;
	}

	/**
	 * @param lecProgramaCorporativoList the lecProgramaCorporativoList to set
	 */
	public void setLecProgramaCorporativoList(List lecProgramaCorporativoList) {
		this.lecProgramaCorporativoList = lecProgramaCorporativoList;
	}
}