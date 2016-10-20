package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCProgramaReconocimiento2Form;

@ManagedBean
@SessionScoped
public class ReporteINCProgramaReconocimiento2Action extends BaseReporteAbstractAction {
	private static final long serialVersionUID = 1L;
	private List siccConcursoList;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCProgramaReconocimiento2Form form = new ReporteINCProgramaReconocimiento2Form();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteINCPrograma2XLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		ReporteINCProgramaReconocimiento2Form f = (ReporteINCProgramaReconocimiento2Form) this.formReporte;
	
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", params);
		int puntajeMinimo = 0;
		int puntajeMaximo = 9999999;
		
		formatoReporte = this.formatoExportacion;
		f.setFormatoExportacion(formatoReporte);
		
		//-- Validar valores de Puntaje
		if(f.getPuntajeMinimo().trim().length()>0)
			puntajeMinimo = Integer.valueOf(f.getPuntajeMinimo()).intValue();
		if(f.getPuntajeMaximo().trim().length()>0)
			puntajeMaximo = Integer.valueOf(f.getPuntajeMaximo()).intValue();
		
		log.info("Envio de Puntajes: ".concat("Puntaje Minimo="+puntajeMinimo).concat(" - Puntaje Maximo="+puntajeMaximo));
		
		//-- Activar el generador de tabs XLS
		this.setGenerateTabsXLS(true);

		params.put("formatoExportacion",this.getFormatoExportacion());		
		params.put("codigoPais", codigoPais);
		params.put("numeroConcurso", f.getNumConcurso());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("oidPeriodo",oidPeriodo);
		params.put("puntajeMinimo",puntajeMinimo);
		params.put("puntajeMaximo",puntajeMaximo);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new java.util.Date(System.currentTimeMillis()));

		// String codPeriodo = (String)
		// request.getSession().getAttribute("periodoActual");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		String periodoActual = "";
		ReporteINCProgramaReconocimiento2Form f = (ReporteINCProgramaReconocimiento2Form) this.formReporte;
		
		f.setCodigoPeriodo(!StringUtils.isEmpty(periodoActual) ? periodoActual : periodo);
		// -- Valores x defecto Puntaje minimo y maximo
		f.setPuntajeMinimo("0");
		f.setPuntajeMaximo("9999999");

		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		setSiccConcursoList(service.getListConcursoReconocido());

	}
	

	public List getSiccConcursoList() {
		return siccConcursoList;
	}

	public void setSiccConcursoList(List siccConcursoList) {
		this.siccConcursoList = siccConcursoList;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCProgramaReconocimientoService2";
	}

}
