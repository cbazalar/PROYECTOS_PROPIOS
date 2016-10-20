package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
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

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBConsolidadoRecuperacionCarteraForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCOBConsolidadoRecuperacionCarteraAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6467122943316716354L;
	private String tipoVista;
	private List siccSociedadList;
	private List siccEtapaDeudaList;
	private LabelValue[] siccCobradoresList = {};
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private boolean bregion = true;
	private boolean bzona = true;
	private boolean bcobrador = true;
	private boolean bseccion = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes...");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteCOBConsolidadoRecuperacionCarteraForm form = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		Base base = (Base) this.siccSociedadList.get(0);
		form.setCodigoSociedad(base.getCodigo());

		LabelValue etapas[] = ajax.getEtapasDeudaByPaisSociedad(form.getCodigoPais(), base.getCodigo());
		if (etapas == null){
			this.siccEtapaDeudaList = new ArrayList();
		}
		else{
			this.siccEtapaDeudaList = Arrays.asList(etapas);
			LabelValue primerValor = etapas[0];
			form.setCodigoEtapaDeuda(primerValor.getValue());
			}

		form.setCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		form.setCodigoPeriodo(periodo);
		
		LabelValue todos = new LabelValue();		
		todos.setLabel("Todos");
		todos.setValue("");		
		this.siccRegionList = new LabelValue[]{todos}; 
		this.siccZonaList = new LabelValue[]{todos}; 
		this.siccSeccionList = new LabelValue[]{todos};
		this.siccCobradoresList = new LabelValue[]{todos};

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOBConsolidadoRecuperacionCarteraForm form = new ReporteCOBConsolidadoRecuperacionCarteraForm();
		return form;
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
		return "reporteCOBConsolidadoRecuperacionCartera" + tipoVista + "XLS";
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
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCOBConsolidadoRecuperacionCarteraForm reporteCOBForm = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;

		this.tipoVista = reporteCOBForm.getTipoVista();

		String codigoPais = reporteCOBForm.getCodigoPais();
		String codigoSociedad = reporteCOBForm.getCodigoSociedad();

		String condicionRegion = obtieneCondicion(
				reporteCOBForm.getRegionList(), "CARTE.COD_REGI", "'");

		String condicionZonas = obtieneCondicion(reporteCOBForm.getZonaList(),
				"CARTE.COD_ZONA_CLIE", "'");

		String condicionSeccion = obtieneCondicion(
				reporteCOBForm.getZonaList(), "CARTE.COD_SECC", "'");

		String condicionCobrador = obtieneCondicion(
				reporteCOBForm.getCobradorList(), "CARTE.COD_USUA_COBR", "'");

		String condicion = condicionZonas + condicionRegion + condicionCobrador
				+ condicionSeccion;

		params.put("codigoPais", codigoPais);
		params.put("codigoSociedad", codigoSociedad);
		params.put("condicion", condicion);

		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");
		return params;
	}
	

	/**
	 * Carga las etapas
	 * 
	 * @param val
	 */
	public void loadEtapas(ValueChangeEvent val) {
		log.info(">>Entro aacaa!!!!! ");
		log.debug(">>loadEtapas ");
		try {
			ReporteCOBConsolidadoRecuperacionCarteraForm form = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			String valor = (String) val.getNewValue(); 
			if(StringUtils.isNotBlank(valor)){
				
				LabelValue etapas[] = ajax.getEtapasDeudaByPaisSociedad(form.getCodigoPais(), valor);
				if (etapas != null && etapas.length > 0) {
					this.siccEtapaDeudaList = new ArrayList();					
					this.siccEtapaDeudaList = Arrays.asList(etapas);
					LabelValue primerValor = (LabelValue) this.siccEtapaDeudaList.get(0);
					form.setCodigoEtapaDeuda(primerValor.getValue());
				} else {
					this.siccEtapaDeudaList = new ArrayList();
				}
				log.info("LA LISTA ESSS");
				log.info(getSiccEtapaDeudaList().toString());
				
			}else{
				this.siccEtapaDeudaList = new ArrayList();
			}
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Carga combos dependiendo de la etapa seleccionada
	 * 
	 * @param val
	 */
	public void loadListasTipoVistaEtapa(ValueChangeEvent val) {
		log.debug(">>loadListasTipoVistaEtapa ");
		
		try {
			ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
			String valor = (String) val.getNewValue();
						
			String vista = f.getTipoVista();
			
			this.siccRegionList = new LabelValue[]{}; 
			this.siccZonaList = new LabelValue[]{};
			this.siccSeccionList = new LabelValue[]{};
			this.siccCobradoresList = new LabelValue[]{};
			
			if (StringUtils.equals(vista,"P")) {
				bregion = true;
				bzona = true;
				bcobrador = true;
				bseccion = true;	
			} else if (StringUtils.equals(vista,"R") || StringUtils.equals(vista,"CR")) {
				bregion = false;
				bzona = true;
				bseccion = true;
				bcobrador = false;
				loadRegionesList();
				loadZonasList();
				loadSeccionList();
				loadCobradoresList();
			} else if (StringUtils.equals(vista,"Z") || StringUtils.equals(vista,"CZ")) {
//				bregion = false;
//				bseccion = true;
				bzona = false;				
				bcobrador = false;
				loadRegionesList();
				loadCobradoresList();
			} else if (StringUtils.equals(vista,"S") || StringUtils.equals(vista,"CS")) {
//				bregion = false;
				bzona = false;
				bseccion = false;
				bcobrador = false;
				siccZonaList=null;
				loadRegionesList();
				loadCobradoresList();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}	
	
	/**
	 * Carga los combos dependiendo del periodo seleccionado
	 * 
	 * @param valor
	 */
	public void loadListasTipoVistaPeriodo(String valor) {
		log.debug(">>loadListasTipoVistaEtapa ");
		
		try {
			ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		
			String vista = f.getTipoVista();
			
			this.siccRegionList = new LabelValue[]{}; 
			this.siccZonaList = new LabelValue[]{};
			this.siccSeccionList = new LabelValue[]{};
			this.siccCobradoresList = new LabelValue[]{};
						
			if (StringUtils.equals(vista,"P")) {
				bregion = true;
				bzona = true;
				bcobrador = true;
				bseccion = true;	
			} else if (StringUtils.equals(vista,"R") || StringUtils.equals(vista,"CR")) {
				bregion = false;
				bzona = true;
				bseccion = true;
				bcobrador = false;
				loadRegionesList();
				loadZonasList();
				loadSeccionList();
				loadCobradoresList();
			} else if (StringUtils.equals(vista,"Z") || StringUtils.equals(vista,"CZ")) {
//				bregion = false;
//				bseccion = true;
				bzona = false;				
				bcobrador = false;
				loadRegionesList();
				loadCobradoresList();
			} else if (StringUtils.equals(vista,"S") || StringUtils.equals(vista,"CS")) {
//				bregion = false;
				bzona = false;
				bseccion = false;
				bcobrador = false;
				siccZonaList=null;
				loadRegionesList();
				loadCobradoresList();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
		
	}
	
	/**
	 * Carga combos dependiendo del tipo viste seleccionado
	 * 
	 * @param val
	 */
	public void loadListasTipoVista(ValueChangeEvent val) {
		log.debug(">>loadListasTipoVistaEtapa ");
		
		try {
			ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
			String vista = (String) val.getNewValue();
			f.setTipoVista(vista);
			
			this.siccRegionList = new LabelValue[]{}; 
			this.siccZonaList = new LabelValue[]{};
			this.siccSeccionList = new LabelValue[]{};
			this.siccCobradoresList = new LabelValue[]{};
			
			if (StringUtils.equals(vista,"P")) {
				bregion = true;
				bzona = true;
				bcobrador = true;
				bseccion = true;	
			} else if (StringUtils.equals(vista,"R") || StringUtils.equals(vista,"CR")) {
				bregion = false;
				bzona = true;
				bseccion = true;
				bcobrador = false;
				loadRegionesList();
				loadZonasList();
				loadSeccionList();
				loadCobradoresList();
			} else if (StringUtils.equals(vista,"Z") || StringUtils.equals(vista,"CZ")) {
//				bregion = false;
//				bseccion = true;
				bzona = false;				
				bcobrador = false;
				loadRegionesList();
				loadCobradoresList();
			} else if (StringUtils.equals(vista,"S") || StringUtils.equals(vista,"CS")) {
//				bregion = false;
				bzona = false;
				bseccion = false;
				bcobrador = false;
				siccZonaList=null;
				loadRegionesList();
				loadCobradoresList();
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Carga combos dependiendo de la region seleccionada
	 * 
	 * @param val
	 */
	public void loadListasRegionList(ValueChangeEvent val) {
		ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		String[] valor = (String[]) val.getNewValue();
		String vista = f.getTipoVista();
		if (StringUtils.equals(vista, "S") || StringUtils.equals(vista, "CS")) {
			bseccion = false;
			loadZonasList1(valor);
			loadSeccionList();
		}

		if (StringUtils.equals(vista, "Z") || StringUtils.equals(vista, "CZ")) {
			bzona = false;
			loadZonasList1(valor);
		}
		
		if (StringUtils.equals(vista, "R") || StringUtils.equals(vista, "CR")) {
			bzona = true;
		}

		loadCobradoresRegionList(valor);
	}
	
	/**
	 * Carga combos dependiendo de la zona seleccionada
	 * 
	 * @param val
	 */
	public void loadListasZonaList(ValueChangeEvent val) {

		ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		String[] valor = (String[]) val.getNewValue();
		f.setZonaList(valor);
		String vista = f.getTipoVista();

		if (StringUtils.equals(vista, "S") || StringUtils.equals(vista, "CS")){
			bseccion = false;
			loadSeccionList();
		}
		
		if (StringUtils.equals(vista, "Z") || StringUtils.equals(vista, "CZ")){
			bseccion = true;
		}
	}
	

	/**
	 * Logica del cargar la lista regiones
	 * 
	 */
	public void loadRegionesList() {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
			this.siccRegionList = new LabelValue[]{};
			f.setRegionList(new String[]{});
			this.siccRegionList = ajax.getRegionesByPaisSociedadEtapaDeudaPeriodo(pais.getCodigo(), f.getCodigoSociedad(), f.getCodigoEtapaDeuda(), f.getCodigoPeriodo());
			int tam = 0;
			if(this.siccRegionList != null && this.siccRegionList.length > 0){
				tam = this.siccRegionList.length + 1;
			}else{
				tam = 1;
			}
			
			LabelValue[] listTemp = new LabelValue[tam];			
			LabelValue label = new LabelValue();
			label.setLabel("Todos");
			label.setValue("");
			listTemp[0] = label;
			if(this.siccRegionList != null && this.siccRegionList.length > 0){
				for (int i = 0; i < siccRegionList.length; i++) {
					LabelValue label2 = new LabelValue();
					label2.setLabel(this.siccRegionList[i].getLabel());
					label2.setValue(this.siccRegionList[i].getValue());
					listTemp[i + 1] = label2;
				}
			}

			this.siccRegionList = listTemp;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	
	
	/**
	 * Logica del cargar la lista zonas
	 */
	public void loadZonasList() {
		try {
			String[] values = new String[]{};
			int salir = 1;
			int j = 0;
			ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
			if(f.getRegionList() != null && f.getRegionList().length > 0){
						salir = 0;
						values = f.getRegionList();				
			}
			
			if (salir == 1) {
				this.siccZonaList = new LabelValue[]{};
				f.setZonaList(new String[]{});
			} else {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
				f.setZonaList(new String[]{});
				this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", values, "T");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * Logica del cargar la lista regiones, con parametro 
	 * 
	 * @param valores
	 */
	public void loadZonasList1(String[] valores) {
		try {
			String[] values = valores;
			ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
			int salir = 1;
			int j = 0;
			if(values != null && values.length > 0){
				f.setRegionList(values);
				salir = 0;
			}
			
			if (salir == 1) {
				this.siccZonaList = new LabelValue[]{};
				f.setZonaList(new String[]{});
			} else {
				AjaxService ajax = (AjaxService) getBean("ajaxService");
				Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
				this.siccZonaList = new LabelValue[]{};
				f.setZonaList(new String[]{});
				this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", values, "T");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	
	/**
	 * Logica del cargar la lista secciones
	 * 
	 */
	public void loadSeccionList() {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String[] valuesRegion = new String[]{};
		int salir = 1;
		ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		
		if(f.getRegionList() != null && f.getRegionList().length > 0){
					salir = 0;
					valuesRegion = f.getRegionList();								
		}
		
		if (salir == 1){
		 	this.siccSeccionList = new LabelValue[]{};     	
		} else {
			salir = 1;
		}
		
		String[] valuesZonas = new String[]{};
		
		if(f.getZonaList() != null && f.getZonaList().length > 0){ 
					salir = 0;
					valuesZonas = f.getZonaList();								
		}
		
		if (salir == 1) {
			this.siccSeccionList = new LabelValue[]{};
			f.setSeccionList(new String[]{});
		} else {
			this.siccSeccionList = new LabelValue[]{};
			f.setSeccionList(new String[]{});
			this.siccSeccionList = ajax.getSeccionMultipleByPaisMarcaCanalRegionZona(f.getCodigoPais(), "T", "VD", valuesRegion,valuesZonas, "T");
		}
	}
	
	
	/**
	 * Logica del cargar la lista cobradores
	 */
	public void loadCobradoresList() {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		this.siccCobradoresList = new LabelValue[]{};
		f.setCobradorList(new String[]{});
		this.siccCobradoresList = ajax.getCobradoresByPaisSociedadEtapaDeudaPeriodo(f.getCodigoPais(), f.getCodigoSociedad(), f.getCodigoEtapaDeuda(), f.getCodigoPeriodo());
		int tam = 0;
		if(this.siccCobradoresList != null && this.siccCobradoresList.length > 0){
			tam = this.siccCobradoresList.length + 1;
		}else{
			tam = 1;
		}
		
		LabelValue[] listTemp = new LabelValue[tam];
		LabelValue label = new LabelValue();
		label.setLabel("Todos");
		label.setValue("");
		listTemp[0] = label;
		if(this.siccCobradoresList != null && this.siccCobradoresList.length > 0){
			for (int i = 0; i < siccCobradoresList.length; i++) {
				LabelValue label2 = new LabelValue();
				label2.setLabel(this.siccCobradoresList[i].getLabel());
				label2.setValue(this.siccCobradoresList[i].getValue());
				listTemp[i + 1] = label2;
			}
		}

		this.siccCobradoresList = listTemp;

	}

	/**
	 * Logica del cargar la lista cobradores, con parametro
	 * @param valores
	 */
	public void loadCobradoresRegionList(String[] valores) {
		ReporteCOBConsolidadoRecuperacionCarteraForm f = (ReporteCOBConsolidadoRecuperacionCarteraForm) this.formReporte;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String[] regiones = valores;
		int salir = 1;  
		ArrayList values = new ArrayList();
		for (int i = 0; i < regiones.length; i++) {
			salir = 0;
			values.add(regiones[i]);
		}
		
		if (salir== 1){			 
			this.siccCobradoresList= new LabelValue[]{}; 
			f.setCobradorList(new String[]{});
		}else{
			this.siccCobradoresList= new LabelValue[]{}; 
			f.setCobradorList(new String[]{});
			siccCobradoresList = ajax.getCobradoresMultiplesByPaisSociedadEtapaDeudaPeriodoRegion(f.getCodigoPais(), f.getCodigoSociedad(), f.getCodigoEtapaDeuda(), f.getCodigoPeriodo(), values, Constants.TODAS);
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

	public LabelValue[] getSiccCobradoresList() {
		return siccCobradoresList;
	}

	public void setSiccCobradoresList(LabelValue[] siccCobradoresList) {
		this.siccCobradoresList = siccCobradoresList;
	}

	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
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
	 * @return the bseccion
	 */
	public boolean isBseccion() {
		return bseccion;
	}

	/**
	 * @param bseccion
	 *            the bseccion to set
	 */
	public void setBseccion(boolean bseccion) {
		this.bseccion = bseccion;
	}
}