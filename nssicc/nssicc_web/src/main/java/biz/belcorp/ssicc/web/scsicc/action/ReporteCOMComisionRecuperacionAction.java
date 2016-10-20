// TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionRecuperacionForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOMComisionRecuperacionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -1091798983329193025L;

	private List listaTipoComision;
	private List listaPresentacion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMComisionRecuperacionForm reporteForm = new ReporteCOMComisionRecuperacionForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		ReporteCOMComisionRecuperacionForm reporteForm = (ReporteCOMComisionRecuperacionForm) this.formReporte;

		if ("5".equals(reporteForm.getPresentacion())) {
			return "reporteMaestroHorizontal";
		}
		if ("4".equals(reporteForm.getPresentacion())
				|| "6".equals(reporteForm.getPresentacion())) {
			return "reporteMaestroVerticalComision";
		}
		return "reporteMaestroHorizontalComision";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {

		ReporteCOMComisionRecuperacionForm reporteForm = (ReporteCOMComisionRecuperacionForm) this.formReporte;

		if ("1".equals(reporteForm.getPresentacion())) {
			return "reporteCOMComisionRecuperacionDetalle";
		} else if ("2".equals(reporteForm.getPresentacion())) {
			return "reporteCOMComisionRecuperacionSeccion";
		} else if ("3".equals(reporteForm.getPresentacion())) {
			return "reporteCOMComisionRecuperacionZona";
		} else if ("4".equals(reporteForm.getPresentacion())) {
			return "reporteCOMComisionRecuperacionRegion";
		} else if ("5".equals(reporteForm.getPresentacion())) {
			return "reporteCOMComisionRecuperacionGZona";
		} else if ("6".equals(reporteForm.getPresentacion())) {
			return "reporteCOMComisionRecuperacionGRegion";
		}

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOMComisionRecuperacionService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMComisionRecuperacionAction.setViewAtributes' method");
		}

		// Seteo de valores por default de nuevos registros
		ReporteCOMComisionRecuperacionForm reporteForm = (ReporteCOMComisionRecuperacionForm) this.formReporte;

		InterfazSiCCService service = (InterfazSiCCService) this
				.getBeanService("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);

		reporteForm.setCodigoPeriodo(codigoPeriodo);

		ArrayList resultadoTipo = new ArrayList();
		Base[] mesTipo = new Base[2];
		String[] presentacionesTipo = { "Lider", "Gerente de Zona" };
		for (int i = 0; i < 2; i++) {
			mesTipo[i] = new Base();
			mesTipo[i].setCodigo("" + (i + 1));
			mesTipo[i].setDescripcion(presentacionesTipo[i]);
			resultadoTipo.add(mesTipo[i]);
		}
		this.listaTipoComision = resultadoTipo;

		ArrayList resultado = new ArrayList();
		Base[] mes = new Base[6];
		String[] presentaciones = { "Detalle de Comisión",
				"Resumen por Sección", "Resumen por Zona",
				"Resumen por Región", "Comisión de Gerente de Zona",
				"Comisión de Gerente Regional" };
		for (int i = 0; i < 6; i++) {
			mes[i] = new Base();
			mes[i].setCodigo("" + (i + 1));
			mes[i].setDescripcion(presentaciones[i]);
			resultado.add(mes[i]);
		}
		this.listaPresentacion = resultado;

		reporteForm.setAnyoPeriodo(this.mPantallaPrincipalBean
				.getAnyoActualperiodo());

		this.log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOMComisionRecuperacionAction.prepareParameterMap' method");
		}

		ReporteCOMComisionRecuperacionForm form = (ReporteCOMComisionRecuperacionForm) this.formReporte;

		String tipoComision;
		String presentacion = form.getPresentacion();
		if (presentacion.equals("5"))
			tipoComision = "G";
		else
			tipoComision = "L";

		if (presentacion.equals("3") || presentacion.equals("4")) {
			if (presentacion.equals("3")) {
				form.setSubReporteDir1("reporteCOMComisionRecuperacionZonaIngresos");
				form.setSubReporteDir2("reporteCOMComisionRecuperacionZonaRecup");
			} else {
				form.setSubReporteDir1("reporteCOMComisionRecuperacionRegionRecup");
				form.setSubReporteDir2("reporteCOMComisionRecuperacionRegionIngresos");
			}
			form.setTipoComision(tipoComision);
			params.put("tipoComision", tipoComision);

			ClassPathResource resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + form.getSubReporteDir1()
							+ JASPER_EXTENSION);
			ClassPathResource resource1 = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + form.getSubReporteDir2()
							+ JASPER_EXTENSION);

			params.put(
					"SUBREPORT_DIR1",
					JRLoader.loadObject(this.getClass().getClassLoader()
							.getResource(resource.getPath())));
			params.put(
					"SUBREPORT_DIR2",
					JRLoader.loadObject(this.getClass().getClassLoader()
							.getResource(resource1.getPath())));

		}

		form.setNroReporte(" ");
		params.put("NroReporte", " ");

		if (presentacion.equals("1")) {
			form.setTitulo(this
					.getReportResourceMessage("reporteCOMComisionRecuperacionForm.tituloComision"));
		} else if (presentacion.equals("2")) {
			form.setTitulo(this
					.getReportResourceMessage("reporteCOMComisionRecuperacionForm.tituloSeccion"));
		} else if (presentacion.equals("3")) {
			form.setTitulo(this
					.getReportResourceMessage("reporteCOMComisionRecuperacionForm.tituloZona"));
		} else if (presentacion.equals("4")) {
			form.setTitulo(this
					.getReportResourceMessage("reporteCOMComisionRecuperacionForm.tituloRegion"));
		} else if (presentacion.equals("5")) {
			form.setTitulo(this
					.getReportResourceMessage("reporteCOMComisionRecuperacionForm.tituloGerenteZona"));
		} else if (presentacion.equals("")) {
			form.setTitulo(this
					.getReportResourceMessage("reporteCOMComisionRecuperacionForm.tituloGerenteRegion"));
		}
		params.put("titulo", form.getTitulo());

		form.setBeforeExecuteReporte(true);
		return params;

	}

	/**
	 * @return the listaTipoComision
	 */
	public List getListaTipoComision() {
		return listaTipoComision;
	}

	/**
	 * @param listaTipoComision
	 *            the listaTipoComision to set
	 */
	public void setListaTipoComision(List listaTipoComision) {
		this.listaTipoComision = listaTipoComision;
	}

	/**
	 * @return the listaPresentacion
	 */
	public List getListaPresentacion() {
		return listaPresentacion;
	}

	/**
	 * @param listaPresentacion
	 *            the listaPresentacion to set
	 */
	public void setListaPresentacion(List listaPresentacion) {
		this.listaPresentacion = listaPresentacion;
	}

}
