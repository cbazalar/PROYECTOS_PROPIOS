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
import javax.naming.spi.DirStateFactory.Result;

import org.apache.poi.ss.formula.functions.Even;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoRecuperacionCarteraForm;

/**
 * The Class ReporteCOBConsolidadoRecuperacionCarteraEjecutivoAction.
 * 
 * @autor: Jose Pulido
 * @version: 1.0 17/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCOBDetalladoRecuperacionCarteraAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -4545428358557554422L;
	private String tipoVista;
	private String vistaReporte;
	private List siccSociedadList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccEtapaDeudaList;
	private LabelValue[] siccCobradoresList;
	private boolean bregion;
	private boolean bzona;
	private boolean bcobrador;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBDetalladoRecuperacionCarteraForm form = new ReporteCOBDetalladoRecuperacionCarteraForm();
		return form;
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
			log.debug("Entering 'ReporteCOBDetalladoRecuperacionCarteraAction.setViewAtributes' method");
		}
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBDetalladoRecuperacionCarteraForm f = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccRegionList = new ArrayList();
		
		LabelValue base = new LabelValue();
		base.setLabel("Todos");
		base.setValue("");
		this.siccZonaList = new LabelValue[]{base};
		this.siccEtapaDeudaList = new ArrayList();
		this.siccCobradoresList = new LabelValue[]{};
		this.bzona = true;
		this.bcobrador = true;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodo(periodo);
		
		if(this.siccSociedadList != null && this.siccSociedadList.size() == 1)
		{
			Base sociedad = (Base)this.siccSociedadList.get(0);
			f.setCodigoSociedad(sociedad.getCodigo());
			this.cargarEtapas(sociedad.getCodigo());
		}
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
		return "reporteCOBDetalladoRecuperacionCarteraD" + this.tipoVista + ""
				+ this.vistaReporte + "XLS";
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
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteCOBDetalladoRecuperacionCarteraForm reporteCOBForm = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;

		this.tipoVista = reporteCOBForm.getTipoVista();
		this.vistaReporte = reporteCOBForm.getVistaReporte();

		String codigoPais = reporteCOBForm.getCodigoPais();
		String codigoSociedad = reporteCOBForm.getCodigoSociedad();
		String codigoZona = reporteCOBForm.getCodigoZona();

		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);
		params.put("codigoZona", codigoZona);

		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * loadEtapas.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		log.debug(">>loadEtapas ");
		cargarEtapas(val.getNewValue().toString());
	}

	/**
	 * 
	 * @param codigoSociedad
	 */
	private void cargarEtapas(String codigoSociedad) {
		try {
			ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			LabelValue etapas[] = ajax.getEtapasDeudaByPaisSociedad(form.getCodigoPais(), codigoSociedad);
			if (etapas != null && etapas.length > 0) {
				this.siccEtapaDeudaList = Arrays.asList(etapas);
				LabelValue lv = etapas[0];
				form.setCodigoEtapaDeuda(lv.getValue());
			} else {
				this.siccEtapaDeudaList = new ArrayList();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * loadOpciones.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadOpciones(ValueChangeEvent val) 
	{   ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
		log.debug(">>loadOpciones ");
		try {
			String tipoVista = val.getNewValue().toString();
			if (tipoVista.equals("C")) {
				this.bregion = true;
				this.bzona = true;
				this.bcobrador = true;
				loadCobradores();
			} else if (tipoVista.equals("U")) {
				this.bregion = false;
				this.bzona = false;
				this.bcobrador = false;
				loadRegiones();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * loadCobradores.
	 */
	private void loadCobradores() {
		ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue cobradores[] = ajax
				.getCobradoresByPaisSociedadEtapaDeudaPeriodo(
						form.getCodigoPais(), form.getCodigoSociedad(),
						form.getCodigoEtapaDeuda(), form.getCodigoPeriodo());
		if (cobradores != null && cobradores.length > 0) {
			this.siccCobradoresList = cobradores;
		} else {
			this.siccCobradoresList = new LabelValue[]{};
		}
	}

	/**
	 * loadRegiones.
	 */
	private void loadRegiones() {
		ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		LabelValue regiones[] = ajax
				.getRegionesByPaisSociedadEtapaDeudaPeriodo(
						form.getCodigoPais(), form.getCodigoSociedad(),
						form.getCodigoEtapaDeuda(), form.getCodigoPeriodo());
		if (regiones != null && regiones.length > 0) {
			this.siccRegionList = Arrays.asList(regiones);
		} else {
			this.siccRegionList = new ArrayList();
		}
	}

	/**
	 * loadZonas.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas ");
		try {
			
			String valor = (String)val.getNewValue();
			ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			 LabelValue[] resultado = ajax
					.getZonasByPaisSociedadEtapaDeudaPeriodoRegion(
							form.getCodigoPais(), form.getCodigoSociedad(),
							form.getCodigoEtapaDeuda(),
							form.getCodigoPeriodo(), valor);
			
			LabelValue base = new LabelValue();
			base.setLabel("");
			base.setValue("");
			int i=1;
			LabelValue[] aux = new LabelValue[resultado.length+1]; 
			aux[0] = base;
			for (LabelValue obj : resultado) {
				aux[i] = obj;														
				i++;
			}
			
			this.siccZonaList = aux;	
			
			loadCobradoresUAZonas(valor);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * loadCobradoresUAZonas.
	 */
	private void loadCobradoresUAZonas(String region) {
		ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccCobradoresList = ajax
				.getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(
						form.getCodigoPais(), form.getCodigoSociedad(),
						form.getCodigoEtapaDeuda(), form.getCodigoPeriodo(),
						region, form.getCodigoZona());
	}

	/**
	 * loadCobradoresUA.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadCobradoresUA(ValueChangeEvent val) {
		log.debug(">>loadCobradoresUA ");
		try {
			
			String valor = val.getNewValue().toString();
			ReporteCOBDetalladoRecuperacionCarteraForm form = (ReporteCOBDetalladoRecuperacionCarteraForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccCobradoresList = ajax
					.getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(
							form.getCodigoPais(), form.getCodigoSociedad(),
							form.getCodigoEtapaDeuda(),
							form.getCodigoPeriodo(), form.getCodigoRegion(),
							valor);
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
	 * @return the vistaReporte
	 */
	public String getVistaReporte() {
		return vistaReporte;
	}

	/**
	 * @param vistaReporte
	 *            the vistaReporte to set
	 */
	public void setVistaReporte(String vistaReporte) {
		this.vistaReporte = vistaReporte;
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
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList
	 *            the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the siccCobradoresList
	 */
	public LabelValue[] getSiccCobradoresList() {
		return siccCobradoresList;
	}

	/**
	 * @param siccCobradoresList
	 *            the siccCobradoresList to set
	 */
	public void setSiccCobradoresList(LabelValue[] siccCobradoresList) {
		this.siccCobradoresList = siccCobradoresList;
	}

	/**
	 * @return the bregion
	 */
	public boolean isBregion() {
		return bregion;
	}

	/**
	 * @param bregion
	 *            the bregion to set
	 */
	public void setBregion(boolean bregion) {
		this.bregion = bregion;
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
	 * @return the bcobrador
	 */
	public boolean isBcobrador() {
		return bcobrador;
	}

	/**
	 * @param bcobrador
	 *            the bcobrador to set
	 */
	public void setBcobrador(boolean bcobrador) {
		this.bcobrador = bcobrador;
	}

}