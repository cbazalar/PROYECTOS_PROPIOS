package biz.belcorp.ssicc.web.scsicc.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCAntiguedadNotasCreditoForm;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCInteresCCorrienteForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ProcesoSTOLevantamientoErroresValidacionForm;

@ManagedBean
@SessionScoped
public class ReporteCCCInteresCCorrienteAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -5809419359847575444L;
	
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private String attachment = "";
	private boolean mostrarFechas;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCInteresCCorrienteForm formReporte = new ReporteCCCInteresCCorrienteForm();
		return formReporte;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCCCInteresCCorrienteForm f =(ReporteCCCInteresCCorrienteForm)this.formReporte;
		
		if(StringUtils.equals(f.getTipoReporteInteres(), "CIP"))
			return "reporteCCCInteresCCorrienteFacturadoPedidoXLS";
		if(StringUtils.equals(f.getTipoReporteInteres(), "CIB"))
			return "reporteCCCInteresCCorrienteCalculadoBloqueoXLS";
		if(StringUtils.equals(f.getTipoReporteInteres(), "CIF"))
			return "reporteCCCInteresCCorrienteFacturadoXLS";
		
		return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCInteresCCorrienteForm f =(ReporteCCCInteresCCorrienteForm)this.formReporte;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioDate()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));
		String condRegion="";
		String condZona="";
		String condCliente ="";
		if(!ArrayUtils.isEmpty(f.getRegiones()))
			condRegion= obtieneCondicion(f.getRegiones(), "gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, 'COD_REGI')", "'");
		if(!ArrayUtils.isEmpty(f.getZonas()))
			condZona= obtieneCondicion(f.getZonas(), "gen_pkg_gener.GEN_FN_CLIEN_DATOS(mc.COD_CLIE, 'COD_ZONA')", "'");
		if(!ArrayUtils.isEmpty(f.getCodClientes()))
			condCliente= obtieneCondicion(f.getCodClientes(), "mc.cod_clie", "'");
		
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());
		params.put("condRegiones", condRegion);
		params.put("condZonas", condZona);
		params.put("condClientes", condCliente);
		params.put("tipoReporteInteres", f.getTipoReporteInteres());
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteOCSV=true;
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteCCCInteresCCorrienteForm f =(ReporteCCCInteresCCorrienteForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		f.setFechaInicioDate(DateUtil.convertStringToDate(fecha));
		f.setFechaFinDate(DateUtil.convertStringToDate(fecha));
		
		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", pais.getCodigo());		
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.mostrarFechas=true;
		
	}
	
	//Mostrar Zonas por REgiones seleccionadas
	public void loadZonas(ValueChangeEvent event){
		String[] valor = (String[])event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		if(valor.length >0)
			this.siccZonaList = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", valor,"T");
		else			
			this.siccZonaList = null;		
		
	}
	
	//Mostrar las fechas segun el Tipo de Reporte
	public void showFechasxReporte(ValueChangeEvent event){
		try {
			String valor =event.getNewValue().toString();
			if(StringUtils.equals(valor, "CIB"))
				this.mostrarFechas=false;
			else
				this.mostrarFechas=true;			
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void handleFileUpload(FileUploadEvent event){
		try {
			ReporteCCCInteresCCorrienteForm f =(ReporteCCCInteresCCorrienteForm)this.formReporte;
			if (event != null) {			
				f.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	public void uploadArchivo() throws Exception {	
		try {		
		ReporteCCCInteresCCorrienteForm f =(ReporteCCCInteresCCorrienteForm)this.formReporte;
		List<String> listaClientes = new ArrayList<String>();		
		UploadedFile archivo =f.getClienteFile();				
		InputStream is = archivo.getInputstream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));		
		String linea = "";	

		while (true) {
			linea = br.readLine();
			if (linea == null)
				break;
			if(StringUtils.isNotBlank(linea) && StringUtils.isNumeric(linea)){
				listaClientes.add(linea);
			}			
		}
		String [] codigos = new String [listaClientes.size()];
		for(int i=0;i<listaClientes.size();i++){
			codigos[i]=listaClientes.get(i);
		}
		f.setCodClientes(codigos);

		} catch (Exception e) {							
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarReporte() {
		ReporteCCCInteresCCorrienteForm f =(ReporteCCCInteresCCorrienteForm)this.formReporte;
		String vFechaInicio = DateUtil.getDate(f.getFechaInicioDate());
		String vFechaFin = DateUtil.getDate(f.getFechaFinDate());
		if (StringUtils.isNotBlank(vFechaInicio) && StringUtils.isNotBlank(vFechaFin)) {
			if (vFechaInicio.compareToIgnoreCase(vFechaFin) > 0) 
				return "'Fecha Inicio' debe ser menor o igual a 'Fecha Fin'";			
		}
		return null;
	}
	
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCInteresCCorrienteService";
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
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarFechas
	 */
	public boolean isMostrarFechas() {
		return mostrarFechas;
	}

	/**
	 * @param mostrarFechas the mostrarFechas to set
	 */
	public void setMostrarFechas(boolean mostrarFechas) {
		this.mostrarFechas = mostrarFechas;
	}
	
	

}
