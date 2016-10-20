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
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBDetalladoRecuperacionCarteraCobradorForm;


/**
 * The Class ReporteCOBDetalladoRecuperacionCarteraCobradorAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 18/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCOBDetalladoRecuperacionCarteraCobradorAction extends BaseReporteAbstractAction {
	private static final long serialVersionUID = 1552421978238981975L;
	private String tipoVista;	
	private String vistaReporte;
	private List siccSociedadList;
	private List siccRegionList;
	private List siccZonaList;
	private List siccEtapaDeudaList;
	private List siccCobradoresList;
	private boolean bregion;
	private boolean bzona;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = new ReporteCOBDetalladoRecuperacionCarteraCobradorForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCOBDetalladoRecuperacionCarteraCobradorAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = true;
		this.mostrarReporteXLSX = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;	
				
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		this.siccRegionList = new ArrayList();
		this.siccZonaList = new ArrayList();
		this.siccEtapaDeudaList = new ArrayList();
		this.siccCobradoresList = new ArrayList();		
		
		this.bregion = true;
		this.bzona = true;
		
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm f = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
		f.setMostrarBotonExcel(this.esVisibleBotonExcel(pais.getCodigo()));
		f.setCodigoPais(pais.getCodigo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));		
		f.setCodigoPeriodoInicio(periodo);
		f.setCodigoPeriodoFin(periodo);
		
		if(this.siccSociedadList != null && this.siccSociedadList.size() == 1)
		{
			Base sociedad = (Base)this.siccSociedadList.get(0);
			f.setCodigoSociedad(sociedad.getCodigo());
			this.cargarEtapas(sociedad.getCodigo());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return  "reporteCOBDetalladoRecuperacionCarteraCobrador" + this.tipoVista + "" + this.vistaReporte + "XLS";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOBDetalladoRecuperacionCarteraCobradorService";
	}	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
     */
    @SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm reporteCOBForm = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
		this.tipoVista = reporteCOBForm.getTipoVista();		
		this.vistaReporte = reporteCOBForm.getVistaReporte();
		        				                      
		String codigoPais =  reporteCOBForm.getCodigoPais();
		String codigoSociedad =  reporteCOBForm.getCodigoSociedad();
		String codigoZona =  reporteCOBForm.getCodigoZona();
		String codigoPeriodoInicio = reporteCOBForm.getCodigoPeriodoInicio();
		String codigoPeriodoFin = reporteCOBForm.getCodigoPeriodoFin();
		
		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);
		params.put("codigoZona", codigoZona);			
		params.put("codigoPeriodoInicio", codigoPeriodoInicio);
		params.put("codigoPeriodoFin", codigoPeriodoFin);
				
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}
	
	/**
     * @param codigoPais
     * @return
     */
    private boolean esVisibleBotonExcel(String codigoPais) {
		GenericoService genericoService1 = (GenericoService) getBean("genericoService");
			
		ParametroPais parametroPais1 = new ParametroPais();
		parametroPais1.setCodigoPais(codigoPais);
		parametroPais1.setCodigoSistema(Constants.SISTEMA_GEN);
		parametroPais1.setNombreParametro("mostrarBotonReporteXLS");
		parametroPais1.setIndicadorActivo("1");
			
		List lstParametros1 = genericoService1.getParametrosPais(parametroPais1);
		boolean activo = false;
		
		if(lstParametros1 != null && lstParametros1.size() > 0){			
			activo = true;
		}
		
		return activo;
	}
    
    /**
	 * loadEtapas.
	 *
	 * @param val the val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		try {
			log.debug(">>loadEtapas ");
			log.debug("val: "+val.getNewValue().toString());
			cargarEtapas(val.getNewValue().toString());
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
	}

	/**
	 * 
	 * @param val
	 */
	private void cargarEtapas(String codigoSociedad) {
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		LabelValue etapas[] = ajax.getEtapasDeudaByPaisSociedad(form.getCodigoPais(), codigoSociedad);  
		if(etapas != null && etapas.length > 0) {
			this.siccEtapaDeudaList = Arrays.asList(etapas);
			form.setCodigoEtapaDeuda(etapas[0].getValue());
		} else {
			this.siccEtapaDeudaList = new ArrayList();
		}
	}
	/**
	 * loadOpciones.
	 *
	 * @param val the val
	 */
	public void limpiarValores(ValueChangeEvent val) {
		log.debug(">>limpia valores ");

		try {
			String tipoVista ="";
			this.siccCobradoresList = new ArrayList();
			ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
			String codigoeduda = val.getNewValue().toString();
			form.setCodigoEtapaDeuda("");

			form.setCodigoEtapaDeuda(codigoeduda);
			if(StringUtils.isBlank(form.getTipoVista())){
				this.siccCobradoresList = new ArrayList();
				form.setTipoVista("");

				return;
			}
		
			tipoVista =form.getTipoVista();
			if (tipoVista.equals("C") || tipoVista.equals("CF")) {
				this.bregion = true;
				this.bzona = true;	
				loadCobradores(); 
			}	

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}	
	
	
	/**
	 * loadOpciones.
	 *
	 * @param val the val
	 */
	public void loadOpciones(ValueChangeEvent val) {
		log.debug(">>loadOpciones ");
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;

		if(StringUtils.isBlank(val.getNewValue().toString())){
			form.setTipoVista("");
			this.siccCobradoresList = new ArrayList();

			return;
		}

		try {
			this.siccCobradoresList = new ArrayList();
			String tipoVista = val.getNewValue().toString();
			if (tipoVista.equals("C") || tipoVista.equals("CF")) {
				form.setTipoVista("");

				form.setTipoVista(tipoVista);
				this.bregion = true;
			
				this.bzona = true;	
				loadCobradores(); 
			}	
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		
	}	
	
	/**
	 * loadCobradores.
	 */
	private void loadCobradores() {
		try {
			ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
		    LabelValue cobradores[] = ajax.getCobradoresByPaisSociedadEtapaDeuda(form.getCodigoPais(), form.getCodigoSociedad(),
					  															 form.getCodigoEtapaDeuda());
		    if(cobradores != null && cobradores.length > 0) {
				this.siccCobradoresList = Arrays.asList(cobradores);
			} else {
				this.siccCobradoresList = new ArrayList();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));

		}
		
	}

	
	/**
	 * loadZonas.
	 *
	 * @param val the val
	 */
	public void loadZonas(ValueChangeEvent val) {
		log.debug(">>loadZonas ");
		log.debug("val: "+val.getNewValue().toString());
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccZonaList = Arrays.asList(ajax.getZonasByPaisSociedadEtapaDeudaPeriodoRegion(form.getCodigoPais(), form.getCodigoSociedad(),
																form.getCodigoEtapaDeuda(), form.getCodigoPeriodo(), val.getNewValue().toString()));
		loadCobradoresUAZonas(val.getNewValue().toString());
	}
	
	
	/**
	 * loadCobradoresUAZonas.
	 * 
	 * @param val the val
	 */
	private void loadCobradoresUAZonas(String val) {
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccCobradoresList = Arrays.asList(ajax.getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(form.getCodigoPais(), form.getCodigoSociedad(),
														form.getCodigoEtapaDeuda(), form.getCodigoPeriodo(), val, form.getCodigoZona()));
	}
	
	/**
	 * loadZonas.
	 *
	 * @param val the val
	 */
	public void loadCobradoresUA(ValueChangeEvent val) {
		log.debug(">>loadCobradoresUA ");
		log.debug("val: "+val.getNewValue().toString());
		ReporteCOBDetalladoRecuperacionCarteraCobradorForm form = (ReporteCOBDetalladoRecuperacionCarteraCobradorForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccCobradoresList = Arrays.asList(ajax.getCobradoresByPaisSociedadEtapaDeudaPeriodoRegionZona(form.getCodigoPais(), form.getCodigoSociedad(),
												form.getCodigoEtapaDeuda(), form.getCodigoPeriodo(), form.getCodigoRegion(), val.getNewValue().toString()));
	}


	
	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista the tipoVista to set
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
	 * @param vistaReporte the vistaReporte to set
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
	 * @param siccSociedadList the siccSociedadList to set
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
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccEtapaDeudaList
	 */
	public List getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	/**
	 * @param siccEtapaDeudaList the siccEtapaDeudaList to set
	 */
	public void setSiccEtapaDeudaList(List siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	/**
	 * @return the siccCobradoresList
	 */
	public List getSiccCobradoresList() {
		return siccCobradoresList;
	}

	/**
	 * @param siccCobradoresList the siccCobradoresList to set
	 */
	public void setSiccCobradoresList(List siccCobradoresList) {
		this.siccCobradoresList = siccCobradoresList;
	}

	/**
	 * @return the bregion
	 */
	public boolean isBregion() {
		return bregion;
	}

	/**
	 * @param bregion the bregion to set
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
	 * @param bzona the bzona to set
	 */
	public void setBzona(boolean bzona) {
		this.bzona = bzona;
	}

}