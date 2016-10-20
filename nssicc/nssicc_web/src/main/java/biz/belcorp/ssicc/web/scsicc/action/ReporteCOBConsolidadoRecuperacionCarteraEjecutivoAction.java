package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm;

/**
 * The Class ReporteCOBConsolidadoRecuperacionCarteraEjecutivoAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 17/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCOBConsolidadoRecuperacionCarteraEjecutivoAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -971262469939752679L;
	private String tipoVista;
	private List siccSociedadList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccEtapaDeudaList;
	private List<Map<String, Object>> siccCobradoresList;
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
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm form = new ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm();
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
			log.debug("Entering 'ReporteCOBConsolidadoRecuperacionCarteraEjecutivoAction.setViewAtributes' method");
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

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		f.setCodigoUsuarioLogin(usuario.getLogin());
		f.setCodigoPais(pais.getCodigo());
		f.setDescripcionUsuarioLogin(usuario.getNombres().concat(" ")
				.concat(usuario.getApellidos()));

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais
				.getCodigo());
		this.siccRegionList = null;
		this.siccZonaList = null;
		this.siccEtapaDeudaList = null;
		this.siccCobradoresList = new ArrayList<Map<String, Object>>();
		Map<String, Object> cobradorIni = new HashMap<String, Object>();
		cobradorIni.put("codigo", f.getCodigoUsuarioLogin());
		cobradorIni.put("descripcion", f.getDescripcionUsuarioLogin());
		this.siccCobradoresList.add(cobradorIni);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		f.setCodigoPeriodo(periodo);
		
		//seteamos los valores para los combos, el primer registro de la lista por defecto
		if(this.siccSociedadList != null && this.siccSociedadList.size()>0)
		{
			f.setCodigoSociedad(((Base)this.siccSociedadList.get(0)).getCodigo());
		}
		
		//cargamos las etapas
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), f.getCodigoSociedad());
		
		if(this.siccEtapaDeudaList != null && this.siccEtapaDeudaList.length>0)
		{
			f.setCodigoEtapaDeuda(((LabelValue)this.siccEtapaDeudaList[0]).getValue());
		}
		
		f.setTipoVista("P");
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
		return "reporteCOBConsolidadoRecuperacionCartera" + this.tipoVista
				+ "XLS";
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
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm reporteCOBForm = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		this.tipoVista = reporteCOBForm.getTipoVista();

		String codigoPais = reporteCOBForm.getCodigoPais();
		String codigoSociedad = reporteCOBForm.getCodigoSociedad();

		String condicionRegion = obtieneCondicion(
				reporteCOBForm.getRegionList(), "CARTE.COD_REGI", "'");
		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(),
				"CARTE.COD_ZONA_CLIE", "'");
		reporteCOBForm.setCobradorList(new String[1]);
		reporteCOBForm.getCobradorList()[0] = reporteCOBForm.getCobrador();
		String condicionCobrador = obtieneCondicion(
				reporteCOBForm.getCobradorList(), "CARTE.COD_USUA_COBR", "'");

		String condicion = condicionZonas + condicionRegion + condicionCobrador;

		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);
		params.put("condicion", condicion);

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
			log.debug("val: " + val.getNewValue().toString());
			ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm form = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			form.setCodigoSociedad(val.getNewValue().toString());

			LabelValue etapas[] = ajax.getEtapasDeudaByPaisSociedad(form.getCodigoPais(), form.getCodigoSociedad());
			if (etapas != null && etapas.length > 0) {
				this.siccEtapaDeudaList = etapas;
			} else {
				this.siccEtapaDeudaList = null;
			}
	}

	/**
	 * loadListasPeriodoTipoVista.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadListasPeriodoTipoVista(String val) {
		log.debug(">>loadListasPeriodoTipoVista ");
		log.debug("val: " + val);
		
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm form = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		form.setCodigoPeriodo(val);
		
		this.siccRegionList= null;
		this.siccZonaList= null;
		
		String vista = form.getTipoVista();
		
		if (vista.equals("C")) {
			this.bregion = true;
			this.bzona = true;
			this.bcobrador = true;
		} else if (vista.equals("R") || vista.equals("CR")) {
			this.bregion = false;
			this.bzona = true;
			this.bcobrador = false;
			loadRegionesPeriodoList(val);
			loadZonasList();
		} else if (vista.equals("Z") || vista.equals("CZ")) {
			this.bzona = false;
			this.bcobrador = false;
			loadRegionesPeriodoList(val);
		}
	}

	/**
	 * loadListasEtapaTipoVista.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadListasEtapaTipoVista(ValueChangeEvent val) {
		log.debug(">>loadListasEtapaTipoVista ");
		log.debug("val: " + val.getNewValue().toString());
		
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm form = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		form.setCodigoEtapaDeuda(val.getNewValue().toString());
		
		this.siccRegionList= null;
		this.siccZonaList= null;
		String vista = form.getTipoVista();
		
		if (vista.equals("C")) {
			this.bregion = true;
			this.bzona = true;
			this.bcobrador = true;
		} else if (vista.equals("R") || vista.equals("CR")) {
			this.bregion = false;
			this.bzona = true;
			this.bcobrador = false;
			loadRegionesList(val.getNewValue().toString());
			loadZonasList();
		} else if (vista.equals("Z") || vista.equals("CZ")) {
			this.bzona = false;
			this.bcobrador = false;
			loadRegionesList(val.getNewValue().toString());
		}
	}

	/**
	 * loadListasTipoVista.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadListasTipoVista(ValueChangeEvent val) {
		log.debug(">>loadListasTipoVista ");
		log.debug("val: " + val.getNewValue().toString());
		
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		f.setTipoVista(val.getNewValue().toString());
		
		this.siccRegionList = null;
		this.siccZonaList= null;
		
		String vista = f.getTipoVista();
		
		if (vista.equals("C")) {
			this.bregion = true;
			this.bzona = true;
			this.bcobrador = true;
		} else if (vista.equals("R") || vista.equals("CR")) {
			this.bregion = false;
			this.bzona = true;
			this.bcobrador = false;
			loadRegionesVistaList();
			loadZonasList();
		} else if (vista.equals("Z") || vista.equals("CZ")) {
			this.bzona = false;
			this.bcobrador = false;
			loadRegionesVistaList();
		}
	}

	/**
	 * loadRegionesVistaList.
	 */
	private void loadRegionesVistaList() {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		LabelValue regiones[] = ajax
				.getRegionesByPaisSociedadEtapaDeudaPeriodo(pais.getCodigo(),
						f.getCodigoSociedad(), f.getCodigoEtapaDeuda(),
						f.getCodigoPeriodo());
		if (regiones != null && regiones.length > 0) {
			this.siccRegionList = regiones;
		} else {
			this.siccRegionList = null;
		}
	}

	/**
	 * loadRegionesPeriodoList.
	 */
	private void loadRegionesPeriodoList(String codigoPeriodo) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		LabelValue regiones[] = ajax
				.getRegionesByPaisSociedadEtapaDeudaPeriodo(pais.getCodigo(),
						f.getCodigoSociedad(), f.getCodigoEtapaDeuda(),
						codigoPeriodo);
		if (regiones != null && regiones.length > 0) {
			this.siccRegionList = regiones;
		} else {
			this.siccRegionList = null;
		}
	}

	/**
	 * loadRegionesList.
	 * 
	 * @param codigoEtapaDeuda
	 *            the codigoEtapaDeuda
	 */
	private void loadRegionesList(String codigoEtapaDeuda) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		LabelValue regiones[] = ajax
				.getRegionesByPaisSociedadEtapaDeudaPeriodo(pais.getCodigo(),
						f.getCodigoSociedad(), codigoEtapaDeuda,
						f.getCodigoPeriodo());
		if (regiones != null && regiones.length > 0) {
			this.siccRegionList = regiones;
		} else {
			this.siccRegionList = null;
		}
	}

	/**
	 * loadZonasList.
	 */
	private void loadZonasList() {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		LabelValue zonas[] = null;
		String [] regiones = null;
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		
		if(f.getRegionList()==null){
			regiones = new String[]{};
		}
		else{
			regiones = f.getRegionList();
		
		zonas = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", regiones, "T");
		}
		if (zonas != null && zonas.length > 0) {
			this.siccZonaList = zonas;
			this.bzona = false;
		} else {
			this.siccZonaList = null;
			this.bzona = true;
		}
	}

	/**
	 * loadListasRegionList.
	 * 
	 * @param val
	 *            the val
	 */
	public void loadListasRegionList(ValueChangeEvent val) {
		log.debug(">>loadListasRegionList ");
		log.debug("val: " + val.getNewValue().toString());
		
		ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm f = (ReporteCOBConsolidadoRecuperacionCarteraEjecutivoForm) this.formReporte;
		
		String vista = f.getTipoVista();
		
		if (vista.equals("Z") || vista.equals("CZ")) {
			loadZonasxRegionList((String[]) val.getNewValue());
			this.bzona = false;
		} else if (vista.equals("R") || vista.equals("CR")) {
			this.bzona = true;
		}
	}

	/**
	 * loadZonasxRegionList.
	 * 
	 * @param val
	 */
	private void loadZonasxRegionList(String[] val) {
		log.debug(">>loadZonasxRegionList ");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(
				pais.getCodigo(), "T", "VD", val, "T");
		if (zonas != null && zonas.length > 0) {
			this.siccZonaList = zonas;
			this.bzona = false;
		} else {
			this.siccZonaList = null;
			this.bzona = true;
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
	 * @return the siccCobradoresList
	 */
	public List<Map<String, Object>> getSiccCobradoresList() {
		return siccCobradoresList;
	}

	/**
	 * @param siccCobradoresList
	 *            the siccCobradoresList to set
	 */
	public void setSiccCobradoresList(
			List<Map<String, Object>> siccCobradoresList) {
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

}