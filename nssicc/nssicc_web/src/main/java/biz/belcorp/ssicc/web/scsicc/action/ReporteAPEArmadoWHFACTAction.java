package biz.belcorp.ssicc.web.scsicc.action;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEArmadoWHFACTForm;



@ManagedBean
@SessionScoped
public class ReporteAPEArmadoWHFACTAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccRegionList;
    private LabelValue[] siccZonaList = {};
    private List repSacDetalleTipoOfertaList;
	private int flagLoadFile;
    
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEArmadoWHFACTForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
	   return "reporteAPEArmadoWHFACTXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
	   return null;
	}
	
	
	public void handleFileUpload(FileUploadEvent event) throws IOException {
		
		ReporteAPEArmadoWHFACTForm f = (ReporteAPEArmadoWHFACTForm) this.formReporte;
		if(log.isDebugEnabled()){
			log.debug("handleFileUpload");
		}
		if(event != null){
		
			//Cargamos el archivo de la maquina del cliente al servidor
			if (log.isDebugEnabled()) {
				log.debug("JFA Cargando Archivo");
			}
		
			f.setNombreArchivo(event.getFile().getFileName());
			f.setArchivo(event.getFile());
			String extensionArchivo = FilenameUtils.getExtension(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
						
			f.setFlagCargar(true);
							
			if (log.isDebugEnabled()) {
				log.debug("JFA find Forward view");
			}
			
			String msg = getResourceMessage("reporteAPEArmadoWHFACTForm.inf.msg");
			msg = MessageFormat.format(msg, f.getNombreArchivo());
		    this.addInfo("INFO: ", msg);
		}
		
		
	}
	
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteAPEArmadoWHFACTForm form = (ReporteAPEArmadoWHFACTForm) this.formReporte;
		String[] regiones = (String []) val.getNewValue();		
	      
		if(!ArrayUtils.isEmpty(regiones)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccZonaList(aSvc.getZonasMultipleByPeriodoIntEviPerioRegioZona(form.getCodigoPais(),form.getCodigoPeriodo(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
		}else{
			setSiccZonaList(null);
		}
		
		form.setCodigoZona(null);
	    
	}

	@Override
	public String setValidarReporte() {
		
		ReporteAPEArmadoWHFACTForm form = (ReporteAPEArmadoWHFACTForm) this.formReporte;
		
		if(StringUtils.isBlank(form.getCodigoSAP()) && StringUtils.isBlank(form.getNombreArchivo()))
		{
			return this.getResourceMessage("reporteAPEArmadoWHFACTForm.err.tipoSap");
		}
		
		return null;
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
	
		ReporteAPEArmadoWHFACTForm reporteAPEForm = (ReporteAPEArmadoWHFACTForm) this.formReporte;
		
		Map criteria = params;

		String condicionRegion = this.obtieneCondicion(reporteAPEForm.getCodigoRegion(), "ZRE.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteAPEForm.getCodigoZona(),"ZON.COD_ZONA", "'");
		criteria.put("codigoPeriodo", reporteAPEForm.getCodigoPeriodo());
		
		String condicionAdicional = "";
		
		String codigoSap = reporteAPEForm.getCodigoSAP();

		List listaProductos = new ArrayList();
		UploadedFile archivo = reporteAPEForm.getArchivo();
		
		if(archivo != null)
		{
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));		
			String linea = "";
			String codigoProducto = "";
			while (true) {
				linea = br.readLine();
				if (linea == null) {
					break;
				}
				listaProductos.add(linea.trim());
			}
			reporteAPEForm.setPath(StringUtils.EMPTY);
		}		
			
		// Si es cargado por la caja de texto					
		if (codigoSap != null && codigoSap.length()>0) {
			listaProductos.add(codigoSap);
		} 
		
		if(listaProductos != null && listaProductos.size()>0){
			//String[] arrayProductos = (String[])listaProductos.toArray();
			String[] arrayProductos=new String[listaProductos.size()];  
			listaProductos.toArray(arrayProductos);
			
			condicionAdicional = condicionAdicional + this.obtieneCondicion(arrayProductos, "PRD.COD_SAP", "'");
		}
	
		// ** Fin Proceso llenado de clientes en consulta (Map criteria)
		
		params.put("NroReporte", "");
		params.put("condicion", condicionRegion + condicionZona + condicionAdicional);
		params.put("fechaFacturacionInicio", DateFormatUtils.format(reporteAPEForm.getFechaFacturacionInicio(),"dd/MM/yyyy"));
		params.put("fechaFacturacionFin",  (reporteAPEForm.getFechaFacturacionFin() == null) ? StringUtils.EMPTY : DateFormatUtils.format(reporteAPEForm.getFechaFacturacionFin(),"dd/MM/yyyy"));
		params.put("titulo", getResourceMessage("reporteAPEArmadoWHFACTForm.title"));
		
		reporteAPEForm.setFlagCargar(false);
		setFlagLoadFile(0);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
        log.info("ReporteAPEArmadoWHFACTAction - setViewAttributes");
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteAPEArmadoWHFACTForm form = (ReporteAPEArmadoWHFACTForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		//	Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();
		form.setCodigoPais(pais.getCodigo());
		
		if (StringUtils.isBlank(codigoPeriodoActual)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			codigoPeriodoActual = periodo;
		}
		
		form.setCodigoPeriodo(codigoPeriodoActual);
	
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		siccRegionList =  reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);

		repSacDetalleTipoOfertaList =  reporteService.getTipoOfertas();

		form.setFlagCargar(false);
		setFlagLoadFile(0);
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
	 * @return the repSacDetalleTipoOfertaList
	 */
	public List getRepSacDetalleTipoOfertaList() {
		return repSacDetalleTipoOfertaList;
	}

	/**
	 * @param repSacDetalleTipoOfertaList the repSacDetalleTipoOfertaList to set
	 */
	public void setRepSacDetalleTipoOfertaList(List repSacDetalleTipoOfertaList) {
		this.repSacDetalleTipoOfertaList = repSacDetalleTipoOfertaList;
	}

	/**
	 * @return the flagLoadFile
	 */
	public int getFlagLoadFile() {
		return flagLoadFile;
	}

	/**
	 * @param flagLoadFile the flagLoadFile to set
	 */
	public void setFlagLoadFile(int flagLoadFile) {
		this.flagLoadFile = flagLoadFile;
	}

}