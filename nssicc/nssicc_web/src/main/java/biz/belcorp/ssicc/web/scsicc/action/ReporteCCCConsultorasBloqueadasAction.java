package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCConsultorasBloqueadasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCConsultorasBloqueadasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 8386915498850342241L;
	private String tipoEstado;
	private String condicionBloqueo;
	private List siccSociedadList = new ArrayList();
	private List cccTipoBloqueosList = new ArrayList();

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCConsultorasBloqueadasForm form = new ReporteCCCConsultorasBloqueadasForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCConsultorasBloqueadasDXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		// Preparando los parametros

		ReporteCCCConsultorasBloqueadasForm reporteCCCConsultorasBloqueadasForm = (ReporteCCCConsultorasBloqueadasForm) this.formReporte;

		String fecha1, fecha2, fecha3, fecha4;

		fecha1 = DateUtil.getDate(reporteCCCConsultorasBloqueadasForm
				.getFechaBloqueoDesdeD());
		fecha2 = DateUtil.getDate(reporteCCCConsultorasBloqueadasForm
				.getFechaBloqueoHastaD());

		fecha3 = DateUtil.getDate(reporteCCCConsultorasBloqueadasForm
				.getFechaDesbloqueoDesdeD());
		fecha4 = DateUtil.getDate(reporteCCCConsultorasBloqueadasForm
				.getFechaDesbloqueoHastaD());
		reporteCCCConsultorasBloqueadasForm.setFechaBloqueoDesde(fecha1);
		reporteCCCConsultorasBloqueadasForm.setFechaBloqueoHasta(fecha2);
		reporteCCCConsultorasBloqueadasForm.setFechaDesbloqueoDesde(fecha3);
		reporteCCCConsultorasBloqueadasForm.setFechaDesbloqueoHasta(fecha4);

		if (log.isDebugEnabled()) {
			log.debug("JFA Obteniendo tipoEstado");
		}

		this.tipoEstado = reporteCCCConsultorasBloqueadasForm.getTipoEstado();

		if (log.isDebugEnabled()) {
			log.debug("JFA Equal B");
		}
		// Bloqueadas
		if (this.tipoEstado.equals("B")) {
			this.condicionBloqueo = "AND mcb.fec_desb IS NULL ";
		}
		;

		if (log.isDebugEnabled()) {
			log.debug("JFA Equal D");
		}

		// Desbloquedas
		if (this.tipoEstado.equals("D")) {
			this.condicionBloqueo = "AND mcb.fec_desb IS NOT NULL";
		}
		;

		if (log.isDebugEnabled()) {
			log.debug("JFA Grabando condicion");
		}

		params.put("condicion", condicionBloqueo);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// Obtenemos el Pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		// Carga de lista para cargar los combos en el JSP
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// La constante SICC_SOCIEDAD_LIST almacena la lista de Sociedades por
		// Paiss
		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());

		ConsultaCCCGenericoService svc = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.cccTipoBloqueosList = svc.getTiposBloqueo(criteria);

	}

	/**
	 * @return the tipoEstado
	 */
	public String getTipoEstado() {
		return tipoEstado;
	}

	/**
	 * @param tipoEstado
	 *            the tipoEstado to set
	 */
	public void setTipoEstado(String tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	/**
	 * @return the condicionBloqueo
	 */
	public String getCondicionBloqueo() {
		return condicionBloqueo;
	}

	/**
	 * @param condicionBloqueo
	 *            the condicionBloqueo to set
	 */
	public void setCondicionBloqueo(String condicionBloqueo) {
		this.condicionBloqueo = condicionBloqueo;
	}

	/**
	 * @return the siccSociedadList
	 */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList
	 *            the siccSociedadList to set
	 */
	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	/**
	 * @return the cccTipoBloqueosList
	 */
	public List getCccTipoBloqueosList() {
		return cccTipoBloqueosList;
	}

	/**
	 * @param cccTipoBloqueosList
	 *            the cccTipoBloqueosList to set
	 */
	public void setCccTipoBloqueosList(List cccTipoBloqueosList) {
		this.cccTipoBloqueosList = cccTipoBloqueosList;
	}
}