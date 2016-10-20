package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENVentaVariableForm;


/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar </a>
 */
@ManagedBean
@SessionScoped
public class ReporteVENVentaVariableAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	 private List siccSociedadList;
	 private List siccAlmacenList;
	 private List siccMarcaList;
	 private List siccCanalList;
	 private List siccRangoPeriodoList;
	 private List siccPresentacionList;
	 private List siccFormaVentaList;
	 
	 private String codigoPeriodoFinalDisable;
	 private String codigoAnioDisable;
	 private String codigoPeriodoInicioDisable;
	 private String rangoPeriodoDisable;
	 
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteVENVentaVariableForm();
	}

		@Override
		protected String devuelveBeanReporteService() {
			// TODO Auto-generated method stub
			
				return "reportes.ReporteVENVentaVariableService";
			
		}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		
	   return "reporteVENVentaVariableXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
	   return null;
	}
	

	public void activarDesactivar(ValueChangeEvent val){
		log.debug(">>activarDesactivar ");
		log.debug("val: "+ val.getNewValue());
	    String tipoReporte = (String) val.getNewValue();
	    ReporteVENVentaVariableForm form = (ReporteVENVentaVariableForm) this.formReporte;
		if (tipoReporte.equals("1")) {
			 setCodigoPeriodoFinalDisable("true");
			 setCodigoAnioDisable("true");
			 setCodigoPeriodoInicioDisable("false");
			 setRangoPeriodoDisable("true");
			 
			 form.setCodigoAnio("");
			 form.setCodigoPeriodoFinal("");
			 
		}else if(tipoReporte.equals("2")){
			
			 setCodigoPeriodoFinalDisable("true");
			 setCodigoPeriodoInicioDisable("true");
			 setCodigoAnioDisable("false");
			 setRangoPeriodoDisable("false");
			 form.setCodigoPeriodo("");
			 form.setCodigoPeriodoFinal("");
			 
		}else if(tipoReporte.equals("3")){
			 setCodigoPeriodoFinalDisable("false");
			 setCodigoAnioDisable("true");
			 setCodigoPeriodoInicioDisable("true");
			 setRangoPeriodoDisable("true");
			 
			 form.setCodigoPeriodo("");
			 form.setCodigoAnio("");
		}
	}
	
	@Override
	public String setValidarReporte() {
	
		 ReporteVENVentaVariableForm form = (ReporteVENVentaVariableForm) this.formReporte;
		
		 String tipoReporte = form.getTipoPresentacion();
		 
		 if (tipoReporte.equals("1")) {

             if (StringUtils.isBlank(form.getCodigoPeriodo())) {
                return getResourceMessage("reporteVENVentaVariableForm.err.periodoIni");
             }
			 
		 }else if(tipoReporte.equals("2")){
			if (StringUtils.isBlank(form.getCodigoAnio())) {
	                return getResourceMessage("reporteVENVentaVariableForm.err.anio");
	       }
			 
		 }else if(tipoReporte.equals("3")){
			 if (StringUtils.isBlank(form.getCodigoPeriodoFinal())) {
	                return getResourceMessage("reporteVENVentaVariableForm.err.periodoFinal");
	       }
		}
		
		
		return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
	
		
		log.debug("Los parametros del Reporte en el Action " + params.toString());

		ReporteVENVentaVariableForm f = (ReporteVENVentaVariableForm) this.formReporte;
		String tipoPresentacion = f.getTipoPresentacion();
		String tituloReporteVenta = "";

		params.put("NroReporte", " ");
		params.put("titulo", getReportResourceMessage(
				"reporteVENVentaVariableForm.titulo"));
		if ("1".equals(tipoPresentacion)) {
			params.put("tipoPresentacion", "P");
			tituloReporteVenta = getResourceMessage(
					"reporteVENVentaVariableForm.periodo")
					+ " " + f.getCodigoPeriodo();
		} else {
			if ("2".equals(tipoPresentacion)) {
				params.put("tipoPresentacion", "R");
				tituloReporteVenta = getResourceMessage(
						"reporteVENVentaVariableForm.rangoPeriodo")
						+ " "
						+ f.getCodigoAnio()
						+ " ("
						+ this.devuelveDescripcionRangoPeriodo(f.getCodigoRangoPeriodo()) + ")";
			}
			if ("3".equals(tipoPresentacion)) {
				params.put("tipoPresentacion", "A");
				tituloReporteVenta = getResourceMessage(
						"reporteVENVentaVariableForm.anual")
						+ " "
						+ StringUtils.left(f.getCodigoPeriodoFinal(), 4)
						+ "01 - " + f.getCodigoPeriodoFinal();
			}
		}
		//poniendo es catalogo o estadisticable
		if(Constants.NUMERO_UNO.equals(f.getTipoReporte())){
			//estadisticable
			tituloReporteVenta+=" - "+getResourceMessage("reporteVENVentaVariableForm.ventaEstadisticable");
		}else{
			//catalogo
			tituloReporteVenta+=" - "+getResourceMessage("reporteVENVentaVariableForm.ventaCatalogo");
		}
		params.put("tituloReporteVenta", tituloReporteVenta);

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteVENVentaVariableForm form = (ReporteVENVentaVariableForm)this.formReporte;

		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoSociedad(Constants.CODIGO_SOCIEDAD_DEFAULT);
		form.setCodigoAlmacen(Constants.CODIGO_ALMACEN_DEFAULT);
	    
		this.codigoPeriodoFinalDisable = "true";
	    this.codigoAnioDisable = "true";
	    this.codigoPeriodoInicioDisable = "false";
	    this.rangoPeriodoDisable = "true";
	    
		Map params = new HashMap();
		params.put("codigoISO", usuario.getIdioma().getCodigoISO());
		params.put("codigoPais", pais.getCodigo());
		
		siccSociedadList = service.getSociedadesByCodigoPais(pais.getCodigo());
		siccAlmacenList = service.getAlmacenesByCodigoISOPais(params);
		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		siccRangoPeriodoList = service.getRangosPeriodo();
			
		List resultado = new ArrayList();
		Base[] tipo = new Base[3];
		String[] presentaciones = { "Periodo", "Rango de Periodos", "Anual" };
		for (int i = 0; i < 3; i++) {
			tipo[i] = new Base();
			tipo[i].setCodigo("" + (i + 1));
			tipo[i].setDescripcion(presentaciones[i]);
			resultado.add(tipo[i]);
		}
		siccPresentacionList = resultado;
	
		List formaVentaList = new ArrayList();
		Base[] base = new Base[3];
		String[] formaVentaArray = { "Estadísticable", "Venta Catálogo" };
		for (int i = 0; i < 2; i++) {
			base[i] = new Base();
			base[i].setCodigo("" + (i + 1));
			base[i].setDescripcion(formaVentaArray[i]);
			formaVentaList.add(base[i]);
		}
		siccFormaVentaList = formaVentaList;
		
		log.debug("Todo Ok: Redireccionando");
		
	}

	
	private String devuelveDescripcionRangoPeriodo(
			String codigo) {
		String retorno = "";
		List lista = siccRangoPeriodoList;
		
		for (int i = 0; i < lista.size(); i++) {
			Base bean = (Base) lista.get(i);
			if (codigo.equals(bean.getCodigo())) {
				return bean.getDescripcion();
			}
		}
		return retorno;
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
	 * @return the siccAlmacenList
	 */
	public List getSiccAlmacenList() {
		return siccAlmacenList;
	}

	/**
	 * @param siccAlmacenList the siccAlmacenList to set
	 */
	public void setSiccAlmacenList(List siccAlmacenList) {
		this.siccAlmacenList = siccAlmacenList;
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
	 * @return the siccRangoPeriodoList
	 */
	public List getSiccRangoPeriodoList() {
		return siccRangoPeriodoList;
	}

	/**
	 * @param siccRangoPeriodoList the siccRangoPeriodoList to set
	 */
	public void setSiccRangoPeriodoList(List siccRangoPeriodoList) {
		this.siccRangoPeriodoList = siccRangoPeriodoList;
	}

	/**
	 * @return the siccPresentacionList
	 */
	public List getSiccPresentacionList() {
		return siccPresentacionList;
	}

	/**
	 * @param siccPresentacionList the siccPresentacionList to set
	 */
	public void setSiccPresentacionList(List siccPresentacionList) {
		this.siccPresentacionList = siccPresentacionList;
	}

	/**
	 * @return the siccFormaVentaList
	 */
	public List getSiccFormaVentaList() {
		return siccFormaVentaList;
	}

	/**
	 * @param siccFormaVentaList the siccFormaVentaList to set
	 */
	public void setSiccFormaVentaList(List siccFormaVentaList) {
		this.siccFormaVentaList = siccFormaVentaList;
	}

	

	/**
	 * @return the codigoPeriodoFinalDisable
	 */
	public String getCodigoPeriodoFinalDisable() {
		return codigoPeriodoFinalDisable;
	}

	/**
	 * @param codigoPeriodoFinalDisable the codigoPeriodoFinalDisable to set
	 */
	public void setCodigoPeriodoFinalDisable(String codigoPeriodoFinalDisable) {
		this.codigoPeriodoFinalDisable = codigoPeriodoFinalDisable;
	}

	/**
	 * @return the codigoAnioDisable
	 */
	public String getCodigoAnioDisable() {
		return codigoAnioDisable;
	}

	/**
	 * @param codigoAnioDisable the codigoAnioDisable to set
	 */
	public void setCodigoAnioDisable(String codigoAnioDisable) {
		this.codigoAnioDisable = codigoAnioDisable;
	}

	/**
	 * @return the codigoPeriodoInicioDisable
	 */
	public String getCodigoPeriodoInicioDisable() {
		return codigoPeriodoInicioDisable;
	}

	/**
	 * @param codigoPeriodoInicioDisable the codigoPeriodoInicioDisable to set
	 */
	public void setCodigoPeriodoInicioDisable(String codigoPeriodoInicioDisable) {
		this.codigoPeriodoInicioDisable = codigoPeriodoInicioDisable;
	}

	/**
	 * @return the rangoPeriodoDisable
	 */
	public String getRangoPeriodoDisable() {
		return rangoPeriodoDisable;
	}

	/**
	 * @param rangoPeriodoDisable the rangoPeriodoDisable to set
	 */
	public void setRangoPeriodoDisable(String rangoPeriodoDisable) {
		this.rangoPeriodoDisable = rangoPeriodoDisable;
	}

	
	
	
	
	

	
}