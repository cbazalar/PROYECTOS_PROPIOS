package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.gis.ReporteGISEnviarDireccionConsultorasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteGISEnviarDireccionConsultorasForm;

@ManagedBean
@SessionScoped
public class ReporteGISEnviarDireccionConsultorasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 4691122755043509582L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private List siccTipoClienteList;
	private List siccEstatusList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccZonaList = {};
	
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering  ReporteGISDireccionConsultorasAction 'setViewAttributes' method");
		}
		this.mostrarReportePDF = false;
		this.mostrarReporteCSV = false;
		this.mostrarReporteOCSV = true;
		this.mostrarReporteXLS = true;
		ReporteGISEnviarDireccionConsultorasForm f = (ReporteGISEnviarDireccionConsultorasForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteGISEnviarDireccionConsultorasService serviceDireccion = (ReporteGISEnviarDireccionConsultorasService) getBean("spusicc.reporteGISEnviarDireccionConsultorasService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		siccMarcaList =  svc.getMarcas();
		siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccTipoClienteList = svc.getTiposClientesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccEstatusList = serviceDireccion.getEstadoDireccion();
		f.setCodigoPais(pais.getCodigo());
		
		siccRegionList = aSvc.getRegionesByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
		//AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valor = siccRegionList[0].getValue();
		String[] strArray = new String[] {valor};
		siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT,strArray, Constants.FORMATO_TOTAL);
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal( Constants.CODIGO_CANAL_DEFAULT);
		
		String [] regionList=new String[]{siccRegionList==null ?"":siccRegionList[0].getValue()};
		String [] zonaList=new String[]{siccZonaList==null?"": siccZonaList[0].getValue()};
		f.setCodigoRegion(siccRegionList==null?"":siccRegionList[0].getValue());	
		f.setCodigoZona(zonaList);	
		siccSeccionList=aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT, regionList, zonaList, Constants.FORMATO_TOTAL);
		String [] seccionList=new String[]{siccSeccionList==null?"": siccSeccionList[0].getValue()};
		f.setCodigoSeccion(seccionList);
	}
	
	 /**
	 * @param Muestra las regiones por marcas escogidas.
	 */
	public void showRegionxMarca(ValueChangeEvent val){
		log.debug(">>showRegionxMarca ");
		log.debug("val: "+val.getNewValue().toString());
		ReporteGISEnviarDireccionConsultorasForm form = (ReporteGISEnviarDireccionConsultorasForm) this.formReporte;
		String marcas = (String)val.getNewValue();
		if(!val.equals(null) ){
			//String strName = "name";
			String[] strArray = new String[] {marcas};
			//System.out.println(strArray[0]); //prints "name"
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			siccRegionList=aSvc.getRegionesByPaisMarcaCanal(form.getCodigoPais(), strArray[0], form.getCodigoCanal());
			form.setCodigoRegion(siccRegionList==null?"":siccRegionList[0].getValue());	
		}else {
			this.siccSeccionList = null;
			this.siccZonaList= null;
			form.setCodigoZona(null);
			form.setCodigoSeccion(null);
		}		
	}
	
	 /**
		 * @param Muestra las regiones por canales escogidas.
		 */
		public void showRegionxCanal(ValueChangeEvent val){
			log.debug(">>showRegionxMarca ");
			log.debug("val: "+val.getNewValue().toString());
			ReporteGISEnviarDireccionConsultorasForm form = (ReporteGISEnviarDireccionConsultorasForm) this.formReporte;
			String canales = (String)val.getNewValue();
			if(!val.equals(null) ){
				//String strName = "name";
				String[] strArray = new String[] {canales};
				//System.out.println(strArray[0]); //prints "name"
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				siccRegionList=aSvc.getRegionesByPaisMarcaCanal(form.getCodigoPais(), form.getCodigoMarca(), strArray[0]);
				form.setCodigoRegion(siccRegionList==null?"":siccRegionList[0].getValue());	
			}else {
				this.siccSeccionList = null;
				this.siccZonaList= null;
				form.setCodigoRegion(null);
				form.setCodigoZona(null);
				form.setCodigoSeccion(null);
			}		
		}
	
	 /**
		 * @param Muestra las zonas por regiones escogidas.
		 */
		public void showZonasxRegion(ValueChangeEvent val){
			log.debug(">>showZonasxRegion ");
			log.debug("val: "+val.getNewValue().toString());
			ReporteGISEnviarDireccionConsultorasForm form = (ReporteGISEnviarDireccionConsultorasForm) this.formReporte;
			String regiones = (String)val.getNewValue();
			if(!val.equals(null) ){
				//String strName = "name";
				String[] strArray = new String[] {regiones};
				//System.out.println(strArray[0]); //prints "name"
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
						Constants.CODIGO_CANAL_DEFAULT, strArray, Constants.FORMATO_TOTAL);
				form.setCodigoZona(null);	
			}else {
				this.siccSeccionList = null;
				this.siccZonaList= null;
				form.setCodigoZona(null);
				form.setCodigoSeccion(null);
			}		
		}
		
		/**
		 * @param Muestra las secciones por las zonas seleccionadas.
		 */
		public void showSeccionxZona(ValueChangeEvent val){
			log.debug(">>showTerritorioxZona ");
			log.debug("val: "+val.getNewValue().toString());
			ReporteGISEnviarDireccionConsultorasForm form = (ReporteGISEnviarDireccionConsultorasForm) this.formReporte;
			
			String regiones = form.getCodigoRegion();
			
			String[] zonas = (String [])val.getNewValue();
			
			if(!val.equals(null) && zonas.length > 0 ){
				String[] strArray = new String[] {regiones};
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccSeccionList = aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, strArray, zonas, Constants.FORMATO_TOTAL);
				String [] seccionList=new String[]{siccSeccionList==null?"": siccSeccionList[0].getValue()};
				form.setCodigoSeccion(seccionList);
			}else {
				this.siccSeccionList = null;
				form.setCodigoSeccion(null);
			}		
		}

	/**
	 * M�todo que forma el query dinamico para generar con regi�n, zona, secci�n
	 * seleccionados
	 * 
	 * @param codigoRegion
	 * @param codigoZona
	 * @param codigoSeccion
	 * @param indicadorRegion
	 * @param indicadorZona
	 * @param indicadorSeccion
	 * @param parametro
	 * @param comilla
	 * @return
	 */
	private String FiltroDinamicoUA(String[] codigoRegion, String[] codigoZona,
			String[] codigoSeccion, boolean indicadorRegion,
			boolean indicadorZona, boolean indicadorSeccion, String parametro,
			String comilla) {

		final String uaInicial = "";
		final String comodin = "%";
		List arrUA = new ArrayList();
		String uaConcateado = null;
		String resultado = new String();

		if (indicadorRegion && indicadorZona && indicadorSeccion) {

			// -- Contruir Arreglo de UAs
			for (int i = 0; i < codigoRegion.length; i++)
				for (int j = 0; j < codigoZona.length; j++)
					for (int k = 0; k < codigoSeccion.length; k++) {
						uaConcateado = uaInicial.concat(codigoRegion[i])
								.concat(codigoZona[j]).concat(codigoSeccion[k]);
						arrUA.add(uaConcateado);
					}

			// -- Construir query dinamico
			uaConcateado = null;
			for (int i = 0; i < arrUA.size(); i++) {
				uaConcateado = (String) arrUA.get(i);
				if (i == 0)
					resultado += "(".concat(comilla).concat(uaConcateado)
							.concat(comilla);
				else
					resultado += ",".concat(comilla).concat(uaConcateado)
							.concat(comilla);
			}
			resultado += ")";
			resultado = " WHERE ".concat(parametro).concat(" IN ")
					.concat(resultado);

		} else if (indicadorRegion && indicadorZona && !indicadorSeccion) {

			// -- Contruir Arreglo de UAs
			for (int i = 0; i < codigoRegion.length; i++)
				for (int j = 0; j < codigoZona.length; j++) {
					uaConcateado = uaInicial.concat(codigoRegion[i])
							.concat(codigoZona[j]).concat(comodin);
					arrUA.add(uaConcateado);
				}

			// -- Construir query dinamico
			uaConcateado = null;
			for (int i = 0; i < arrUA.size(); i++) {
				uaConcateado = (String) arrUA.get(i);
				if (i == 0)
					resultado += "(".concat(parametro).concat(" LIKE ")
							.concat(comilla).concat(uaConcateado)
							.concat(comilla);
				else
					resultado += " OR ".concat(parametro).concat(" LIKE ")
							.concat(comilla).concat(uaConcateado)
							.concat(comilla);
			}
			resultado += ")";
			resultado = " WHERE ".concat(resultado);

		} else if (indicadorRegion && !indicadorZona & !indicadorSeccion) {

			// -- Contruir Arreglo de UAs
			for (int i = 0; i < codigoRegion.length; i++) {
				uaConcateado = uaInicial.concat(codigoRegion[i])
						.concat(comodin);
				arrUA.add(uaConcateado);
			}

			// -- Construir query dinamico
			uaConcateado = null;
			for (int i = 0; i < arrUA.size(); i++) {
				uaConcateado = (String) arrUA.get(i);
				if (i == 0)
					resultado += "(".concat(parametro).concat(" LIKE ")
							.concat(comilla).concat(uaConcateado)
							.concat(comilla);
				else
					resultado += " OR ".concat(parametro).concat(" LIKE ")
							.concat(comilla).concat(uaConcateado)
							.concat(comilla);
			}
			resultado += ")";
			resultado = " WHERE ".concat(resultado);

		} else
			resultado = "";

		return resultado;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		// TODO Auto-generated method stub
		ReporteGISEnviarDireccionConsultorasForm form = new ReporteGISEnviarDireccionConsultorasForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteGISEnviarDireccionConsultorasXLS";
		else if ("OCSV".equals(formatoReporte))
			return null;
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		ReporteGISEnviarDireccionConsultorasService serviceDireccion = (ReporteGISEnviarDireccionConsultorasService) getBean("spusicc.reporteGISEnviarDireccionConsultorasService");
		ReporteGISEnviarDireccionConsultorasForm f = (ReporteGISEnviarDireccionConsultorasForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		if (log.isDebugEnabled()) {
			log.debug("Entering  ReporteGISDireccionConsultorasAction 'prepareParameterMap' method");
		}

		String descripcionRegionList = descripcionSimpleLista(f.getCodigoRegion(), this.siccRegionList);
		String descripcionZonaList = descripcionMultipleLista(f.getCodigoZona(), this.siccZonaList);
		String descripcionSeccionList = descripcionMultipleLista(f.getCodigoSeccion(), this.siccSeccionList);
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);	
		params.put("descripcionSeccionList", descripcionSeccionList);

		List estrucGeopoList = serviceDireccion.getEstructurasGeopoliticas(pais
				.getCodigo());
//		String valor1 = estrucGeopoList.get(0).toString();
//		String valor2 = estrucGeopoList.get(1).toString();
//		String valor3 = estrucGeopoList.get(2).toString();
//		String valor4 = estrucGeopoList.get(3).toString();
		if (estrucGeopoList != null && estrucGeopoList.size() > 0) {
			switch (estrucGeopoList.size()) {
			case 1:
				params.put("EstrucGeopo1",
						( (Base) estrucGeopoList.get(0)).getDescripcion());
				params.put("EstrucGeopo2", " ");
				params.put("EstrucGeopo3", " ");
				params.put("EstrucGeopo4", " ");
				break;
			case 2:
				params.put("EstrucGeopo1",
						((Base) estrucGeopoList.get(0)).getDescripcion());
				params.put("EstrucGeopo2",
						((Base) estrucGeopoList.get(1)).getDescripcion());
				params.put("EstrucGeopo3", " ");
				params.put("EstrucGeopo4", " ");
				break;
			case 3:
				params.put("EstrucGeopo1",
						((Base) estrucGeopoList.get(0)).getDescripcion());
				params.put("EstrucGeopo2",
						((Base) estrucGeopoList.get(1)).getDescripcion());
				params.put("EstrucGeopo3",
						((Base) estrucGeopoList.get(2)).getDescripcion());
				params.put("EstrucGeopo4", " ");
				break;
			case 4:
				params.put("EstrucGeopo1",
						( (Base) estrucGeopoList.get(0)).getDescripcion());
				params.put("EstrucGeopo2",
						((Base) estrucGeopoList.get(1)).getDescripcion());
				params.put("EstrucGeopo3",
						((Base) estrucGeopoList.get(2)).getDescripcion());
				params.put("EstrucGeopo4",
						((Base) estrucGeopoList.get(3)).getDescripcion());
				break;
			}
		}

		// -- Comprobar si la regi�n, zona y secci�n traen datos
		String codigoRegion = null;
		String[] codigoZona = null;
		String[] codigoSeccion = null;
		boolean indicadorRegion = true;
		boolean indicadorZona = true;
		boolean indicadorSeccion = true;

		if (f.getCodigoRegion() == null
				&& f.getCodigoRegion().equals("")) {
			codigoRegion = null;
			indicadorRegion = false;
		} else {
			codigoRegion = f.getCodigoRegion();
			indicadorRegion = true;
		}

		if (f.getCodigoZona().length == 1 && f.getCodigoZona()[0].equals("")) {
			codigoZona = null;
			indicadorZona = false;
		} else {
			codigoZona = f.getCodigoZona();
			indicadorZona = true;
		}

		if (f.getCodigoSeccion().length == 1
				&& f.getCodigoSeccion()[0].equals("")) {
			codigoSeccion = null;
			indicadorSeccion = false;
		} else {
			codigoSeccion = f.getCodigoSeccion();
			indicadorSeccion = true;
		}

		// -- Logica query dinamico
		String[] strArrayCodigoRegion = new String[] {codigoRegion};
		String condicionUA = FiltroDinamicoUA(strArrayCodigoRegion, codigoZona,
				codigoSeccion, indicadorRegion, indicadorZona,
				indicadorSeccion, "cod_regi || cod_zona || cod_secc", "'");

		params.put("condicionUA", condicionUA);

		String estatus = f.getEstatus();
		if (StringUtils.isBlank(estatus))
			params.put("condicionEstatus", "");
		else {
			
			params.put("condicionEstatus", estatus);
		}

		//INI ECU-SiCC-2015-0036
		String key = "";
        ParametroPais paramPais = new ParametroPais();
      	paramPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
      	paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
      	paramPais.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
      	paramPais.setValorParametro(Constants.NUMERO_UNO);

      	GenericoService genericoService = (GenericoService) getBean("genericoService");
      	List listParametros = genericoService.getParametrosPais(paramPais);

      		if (listParametros != null && listParametros.size() > 0) {
      			params.put("indCamposAdicionales", true);
      			key = "reporteGISEnviarDireccionConsultorasForm.tituloReporteCSV2IndCampAdi";
      		} else {
      			params.put("indCamposAdicionales", false);
      			key = "reporteGISEnviarDireccionConsultorasForm.tituloReporteCSV2";
      		}
       //FIN ECU-SiCC-2015-0036

		String titulo = new String();
		if (key != null) {
			String mensaje = this.getResourceMessage(key);
			titulo = mensaje;
			params.put("tituloReporteOracle2", titulo);
		}
	    return params;
	}
	
	@Override
	protected String devuelveBeanReporteService(){
		return "reportes.reporteGISEnviarDireccionConsultorasService";
	}

	
	
	
	/* GET - SET */
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccTipoClienteList
	 */
	public List getSiccTipoClienteList() {
		return siccTipoClienteList;
	}

	/**
	 * @param siccTipoClienteList the siccTipoClienteList to set
	 */
	public void setSiccTipoClienteList(List siccTipoClienteList) {
		this.siccTipoClienteList = siccTipoClienteList;
	}

	/**
	 * @return the siccEstatusList
	 */
	public List getSiccEstatusList() {
		return siccEstatusList;
	}

	/**
	 * @param siccEstatusList the siccEstatusList to set
	 */
	public void setSiccEstatusList(List siccEstatusList) {
		this.siccEstatusList = siccEstatusList;
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
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
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