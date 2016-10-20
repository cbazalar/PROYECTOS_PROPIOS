package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.ArrayUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAEClasificacionPorClienteForm;


/**
 * 
 * @author <a href="yrivas@sigcomt.com">Yahir Rivas L.</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteMAEClasificacionPorClienteAction extends BaseReporteAbstractAction implements Serializable {
			
	 private static final long serialVersionUID = 7496906172990857522L;

     private List siccTipoClienteList;
	 private String codigoIdiomaISO;
	 private LabelValue[] siccSubTipoClienteList;
	 private LabelValue[] siccTipoClasificacionList;
	 private LabelValue[] siccClasificacionList;
	 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteMAEClasificacionPorClienteForm();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(this.formatoExportacion.equals("XLS")){
			ReporteMAEClasificacionPorClienteForm form = (ReporteMAEClasificacionPorClienteForm) this.formReporte;
			form.setFormatoExportacion("XLS");
			return "reporteMAETodasCLasificacionesXLS";
		}
		return null;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
	   return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteMAEClasificacionPorClienteService";
	}
	
	public void showSubTipoCliente(ValueChangeEvent val){
		
		log.debug(">>showSubTipoCliente ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteMAEClasificacionPorClienteForm form = (ReporteMAEClasificacionPorClienteForm) this.formReporte;
		String oidTipoCliente = (String) val.getNewValue();		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(oidTipoCliente);
		
		setSiccSubTipoClienteList(aSvc.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), temp));
		
		setSiccClasificacionList(null);
		setSiccTipoClasificacionList(null);
		
		form.setOidSubTipoCliente(null);
	}
	
	public void showTipoClasificacion(ValueChangeEvent val){
		log.debug(">>showTipoClasificacion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteMAEClasificacionPorClienteForm form = (ReporteMAEClasificacionPorClienteForm) this.formReporte;
		String oidSubTipoCliente = (String) val.getNewValue();		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(oidSubTipoCliente);
		
	
		setSiccTipoClasificacionList(aSvc.getTiposClasificacionesByCriteriaMultipleOID2(usuario.getIdioma().getCodigoISO(),
				form.getOidTipoCliente(), temp));
		
		setSiccClasificacionList(null);
		
		form.setOidTipoClasificacion(null);
	}

	public void showClasificacion(ValueChangeEvent val){
		log.debug(">>showClasificacion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteMAEClasificacionPorClienteForm form = (ReporteMAEClasificacionPorClienteForm) this.formReporte;
		String oidTipoClasificacion = (String) val.getNewValue();		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(oidTipoClasificacion);
		
		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(form.getOidSubTipoCliente());

	    setSiccClasificacionList(aSvc.getClasificacionesByCriteriaMultipleOID2(usuario
						.getIdioma().getCodigoISO(), form.getOidTipoCliente(), temp, temp2));
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteMAEClasificacionPorClienteForm reporteSICForm = (ReporteMAEClasificacionPorClienteForm) this.formReporte;
		
		params.put("oidTipoClasi", reporteSICForm.getOidTipoClasificacion());
		params.put("oidClasi", reporteSICForm.getOidClasificacion());
		params.put("oidTipoClien", reporteSICForm.getOidTipoCliente());
		params.put("oidSubtClien", reporteSICForm.getOidSubTipoCliente());
		
		params.put("NroReporte", "");
		params.put("titulo", "");
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = false;
		this.mostrarReportePDF = false;
        this.mostrarReporteOCSV = true;
		// parametros generales

		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		ReporteMAEClasificacionPorClienteForm f = (ReporteMAEClasificacionPorClienteForm)  this.formReporte;
		
		//cargando en session la lista de concursos habilitados
		siccTipoClienteList = interfazSiCCService.getTiposClientesByCodigoISOOID(usuario.getIdioma().getCodigoISO());

		f.setOidSubTipoCliente(Constants.OID_SUBTIPO_CLIENTE_DEFAULT );
		f.setOidTipoCliente(Constants.OID_TIPO_CLIENTE_DEFAULT);
		f.setOidTipoClasificacion(null);
		f.setOidClasificacion(null);

		recargarTipologiaClientes(usuario);
		
		codigoIdiomaISO =  usuario.getIdioma().getCodigoISO();

		f.setNumeroLote("");
	}

	private void recargarTipologiaClientes(Usuario usuario) {
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteMAEClasificacionPorClienteForm f = (ReporteMAEClasificacionPorClienteForm) this.formReporte;		
		ArrayList temp = new ArrayList();
		temp = new ArrayList();
		temp.add(f.getOidTipoCliente());
		siccSubTipoClienteList = aSvc.getSubTiposClientesPorPaisTipoClientesOID(usuario.getIdioma().getCodigoISO(), temp);

		temp = new ArrayList();
		temp.add(f.getOidSubTipoCliente());
		siccTipoClasificacionList = aSvc.getTiposClasificacionesByCriteriaMultipleOID2(usuario.getIdioma().getCodigoISO(),
									Constants.OID_TIPO_CLIENTE_DEFAULT, temp);
		
		
		if(f.getOidTipoClasificacion()==null)
			f.setOidTipoClasificacion(siccTipoClasificacionList[0].getValue());
		
		ArrayList temp2 = new ArrayList();
		temp2 = new ArrayList();
		temp2.add(f.getOidTipoClasificacion());
		
		siccClasificacionList = 
				aSvc.getClasificacionesByCriteriaMultipleOID2(usuario
						.getIdioma().getCodigoISO(), Constants.OID_TIPO_CLIENTE_DEFAULT, temp, temp2);
		
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
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the siccSubTipoClienteList
	 */
	public LabelValue[] getSiccSubTipoClienteList() {
		return siccSubTipoClienteList;
	}

	/**
	 * @param siccSubTipoClienteList the siccSubTipoClienteList to set
	 */
	public void setSiccSubTipoClienteList(LabelValue[] siccSubTipoClienteList) {
		this.siccSubTipoClienteList = siccSubTipoClienteList;
	}

	/**
	 * @return the siccTipoClasificacionList
	 */
	public LabelValue[] getSiccTipoClasificacionList() {
		return siccTipoClasificacionList;
	}

	/**
	 * @param siccTipoClasificacionList the siccTipoClasificacionList to set
	 */
	public void setSiccTipoClasificacionList(LabelValue[] siccTipoClasificacionList) {
		this.siccTipoClasificacionList = siccTipoClasificacionList;
	}

	/**
	 * @return the siccClasificacionList
	 */
	public LabelValue[] getSiccClasificacionList() {
		return siccClasificacionList;
	}

	/**
	 * @param siccClasificacionList the siccClasificacionList to set
	 */
	public void setSiccClasificacionList(LabelValue[] siccClasificacionList) {
		this.siccClasificacionList = siccClasificacionList;
	}
	
	

	
}