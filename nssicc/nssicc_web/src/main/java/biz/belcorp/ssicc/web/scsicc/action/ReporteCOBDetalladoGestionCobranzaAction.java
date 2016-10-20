package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoGestionCobranzaForm;

/**
 * The Class ReporteCOBDetalladoGestionCobranzaAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 20/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCOBDetalladoGestionCobranzaAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -5540717318962723941L;
	private String tipoVista;
	private LabelValue[] siccSociedadList ={};
	private LabelValue[] siccEtapaDeudaList ={};
	private LabelValue[] siccRegionList ={};
	private LabelValue[] siccZonaList ={};
	private LabelValue[] siccCobradoresList ={};
	private LabelValue[] siccAccionCobranzaList ={};
	private boolean bzona;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBDetalladoGestionCobranzaForm form = new ReporteCOBDetalladoGestionCobranzaForm();
		return form;
	}

	/**
	 * 
	 */
	public void incializar(){
		this.siccSociedadList = null;
		this.siccEtapaDeudaList =null;
		this.siccRegionList =null;
		this.siccZonaList =null;
		this.siccCobradoresList =null;
		this.siccAccionCobranzaList =null;
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBDetalladoGestionCobranzaAction.setViewAtributes' method");
		}
	
		this.incializar();
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBDetalladoGestionCobranzaForm f = (ReporteCOBDetalladoGestionCobranzaForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodo(periodo);

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		List listSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
		int j = 0;
		this.siccSociedadList = new LabelValue[listSociedadList.size()];
		for (Object object : listSociedadList) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccSociedadList()[j] = labelValue;
			j++;
			
		}
		String sociedad = this.siccSociedadList[0].getValue().toString();
		f.setCodigoSociedad(sociedad);
		
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad( pais.getCodigo(), sociedad);
		
		this.bzona = true;
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
		return "reporteCOBDetalladoGestionCobranzaD" + this.tipoVista + "XLS";
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
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCOBDetalladoGestionCobranzaForm form = (ReporteCOBDetalladoGestionCobranzaForm) this.formReporte;
		Date fecha1D = form.getFechaInicioGestionD();
		Date fecha2D = form.getFechaFinGestionD();
		if (fecha1D != null && fecha2D != null) {
			if (fecha2D.compareTo(fecha1D) < 0) {
				String mensaje = this
						.getResourceMessage("reporteCOBDetalladoGestionCobranzaForm.errors.compare.fecha");
				return mensaje;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}

		ReporteCOBDetalladoGestionCobranzaForm reporteCOBForm = (ReporteCOBDetalladoGestionCobranzaForm) this.formReporte;

		this.tipoVista = reporteCOBForm.getTipoVista();

		String codigoPais = reporteCOBForm.getCodigoPais();
		String codigoSociedad = reporteCOBForm.getCodigoSociedad();

		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);

		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(),
				"D.COD_ZONA_CLIE", "'");
		String condicionRegion = obtieneCondicion(
				reporteCOBForm.getRegionList(), "D.COD_REGI_CLIE", "'");

		String condicion = condicionZonas + condicionRegion;

		String fecha1 = DateUtil.getDate(reporteCOBForm
				.getFechaInicioGestionD());
		String fecha2 = DateUtil.getDate(reporteCOBForm.getFechaFinGestionD());
		reporteCOBForm.setFechaInicioGestion(fecha1);
		reporteCOBForm.setFechaFinGestion(fecha2);

		String condicionFecha = "";
		condicionFecha += getCondicionFecha("G.FEC_GEST", ">=",
				reporteCOBForm.getFechaInicioGestion());
		condicionFecha += getCondicionFecha("G.FEC_GEST", "<=",
				reporteCOBForm.getFechaFinGestion());

		params.put("condicionFecha", condicionFecha);
		params.put("condicion", condicion);

		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * getCondicionFecha
	 * 
	 * @param columna
	 * @param operador
	 * @param valor
	 * @return
	 */
	private String getCondicionFecha(String columna, String operador,
			String valor) {
		if (StringUtils.isEmpty(valor.trim())) {
			return "";
		} else {
			return " AND " + columna + operador + "TO_DATE('" + valor + "','"
					+ Constants.DEFAULT_DATE_FORMAT + "')";
		}
	}

	/**
	 * loadEtapas.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		try {
			log.debug(">>loadEtapas ");
			ReporteCOBDetalladoGestionCobranzaForm form = (ReporteCOBDetalladoGestionCobranzaForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			LabelValue etapas[] = ajax.getEtapasDeudaByPaisSociedad(
					form.getCodigoPais(), val.getNewValue().toString());
			if (etapas != null && etapas.length > 0) {
				this.siccEtapaDeudaList = etapas;
			} else {
				this.siccEtapaDeudaList = new LabelValue[]{};
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * loadAccionesEtapaList.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadAccionesEtapaList(ValueChangeEvent val) {
		try {
			LabelValue[] limpiador = {};
			log.debug(">>loadAccionesEtapaList ");
			String valor = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			ReporteCOBDetalladoGestionCobranzaForm f = (ReporteCOBDetalladoGestionCobranzaForm) this.formReporte;
			LabelValue cobradores[] = ajax
					.getCobradoresByPaisSociedadEtapaDeuda(f.getCodigoPais(),
							f.getCodigoSociedad(), valor);
			if (cobradores != null && cobradores.length > 0) {
				this.siccCobradoresList = cobradores;
			} else {
				this.siccCobradoresList = limpiador;
			}

			LabelValue accionesCobranza[] = ajax
					.getAccionesCobranzaByPaisSociedadEtapa(f.getCodigoPais(),
							//f.getCodigoSociedad(), 
							valor);
			if (accionesCobranza != null && accionesCobranza.length > 0) {
				this.siccAccionCobranzaList = accionesCobranza;
			} else {
				this.siccAccionCobranzaList = limpiador;
			}

			LabelValue regiones[] = ajax.getRegionesByPaisSociedadEtapaDeuda(f
					.getCodigoPais(),f.getCodigoSociedad(),valor);
			if (regiones != null && regiones.length > 0) {
				this.siccRegionList =regiones;
			} else {
				this.siccRegionList = limpiador;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * loadListasTipoVista.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadZonasList(ValueChangeEvent val) {
		try {
			log.debug(">>loadZonas ");
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(
					pais.getCodigo(), "T", "VD", (String[]) val.getNewValue(),
					"T");
			if (zonas != null && zonas.length > 0) {
				this.siccZonaList = zonas;
				this.bzona = false;
			} else {
				this.siccZonaList = new LabelValue[]{};
				this.bzona = true;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            the tipoVista to set
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}

	/**
	 * @return the bzona
	 */
	public boolean isBzona() {
		return bzona;
	}

	/**
	 * @param bzona
	 *            the bzona to set
	 */
	public void setBzona(boolean bzona) {
		this.bzona = bzona;
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccCobradoresList
	 */
	public LabelValue[] getSiccCobradoresList() {
		return siccCobradoresList;
	}

	/**
	 * @param siccCobradoresList the siccCobradoresList to set
	 */
	public void setSiccCobradoresList(LabelValue[] siccCobradoresList) {
		this.siccCobradoresList = siccCobradoresList;
	}

	/**
	 * @return the siccAccionCobranzaList
	 */
	public LabelValue[] getSiccAccionCobranzaList() {
		return siccAccionCobranzaList;
	}

	/**
	 * @param siccAccionCobranzaList the siccAccionCobranzaList to set
	 */
	public void setSiccAccionCobranzaList(LabelValue[] siccAccionCobranzaList) {
		this.siccAccionCobranzaList = siccAccionCobranzaList;
	}

	/**
	 * @return the siccSociedadList
	 */
	public LabelValue[] getSiccSociedadList() {
		return siccSociedadList;
	}

	/**
	 * @param siccSociedadList the siccSociedadList to set
	 */
	public void setSiccSociedadList(LabelValue[] siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}
}