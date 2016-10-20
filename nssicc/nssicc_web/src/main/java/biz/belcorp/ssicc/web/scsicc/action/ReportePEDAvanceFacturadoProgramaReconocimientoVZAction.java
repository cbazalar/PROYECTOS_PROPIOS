package biz.belcorp.ssicc.web.scsicc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.XlsxUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCOBEstadisticoCicloNuevasForm;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDAvanceFacturadoProgramaReconocimientoVZForm;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDDetallePedidosFacturadosPorCodigoSAPForm;

@ManagedBean
@SessionScoped
public class ReportePEDAvanceFacturadoProgramaReconocimientoVZAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = -1102428410263810121L;
	
	private String attachment = "";
	private String [] codClienteDocumento={};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDAvanceFacturadoProgramaReconocimientoVZForm reporteForm = new ReportePEDAvanceFacturadoProgramaReconocimientoVZForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals("XLS", this.formatoExportacion))	
			return "reportePEDAvanceFacturadoProgramaReconocimientoVZXLS";
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePEDAvanceFacturadoProgramaReconocimientoVZForm f=(ReportePEDAvanceFacturadoProgramaReconocimientoVZForm)this.formReporte;
		if(this.codClienteDocumento!=null && this.codClienteDocumento.length>0){
			params.put("codigoConsultoras", this.codClienteDocumento);
		}else{
			if(StringUtils.isNotBlank(f.getCodigoConsultora())){
				params.put("codigoConsultoras", new String[]{f.getCodigoConsultora()});
			}
		}
		params.put("codigoPeriodoInicio", f.getCodigoPeriodoInicio());
		params.put("codigoPeriodoFin", f.getCodigoPeriodoFin());
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.ignorarCeldaBorder = false;
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		ReportePEDAvanceFacturadoProgramaReconocimientoVZForm f=(ReportePEDAvanceFacturadoProgramaReconocimientoVZForm)this.formReporte;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
	}
	
	//Cargar el archivo Excel
	public void handleFileUpload(FileUploadEvent event) throws Exception {		
		ReportePEDAvanceFacturadoProgramaReconocimientoVZForm f = (ReportePEDAvanceFacturadoProgramaReconocimientoVZForm) this.formReporte;
		if (event != null) {			
			f.setClienteFile(event.getFile());
			this.attachment=event.getFile().getFileName();			
			this.uploadArchivo();
			XlsxUtil excelUtil = new XlsxUtil(f.getDirectorioTemporal(), this.attachment);	
			
			String codigos="";
			List<String> codigoLista= new ArrayList<String>();
			excelUtil.initSheet(0);
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				codigos = mapRow.get("0").toString().trim();
				if(StringUtils.isNotBlank(codigos))
					codigoLista.add(codigos);
				
			}
			excelUtil.cerrar();
			String []temp= new String [codigoLista.size()];
			temp=codigoLista.toArray(temp);		
			this.codClienteDocumento=temp;			
		}
	}
	
	public void uploadArchivo(){	
		try {			
			ReportePEDAvanceFacturadoProgramaReconocimientoVZForm f = (ReportePEDAvanceFacturadoProgramaReconocimientoVZForm) this.formReporte;			
			UploadedFile archivo = f.getClienteFile();				
			// leyemos el stream de entrada
			InputStream is = archivo.getInputstream();
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), this.attachment));
			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();					
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
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
	 * @return the codClienteDocumento
	 */
	public String[] getCodClienteDocumento() {
		return codClienteDocumento;
	}

	/**
	 * @param codClienteDocumento the codClienteDocumento to set
	 */
	public void setCodClienteDocumento(String[] codClienteDocumento) {
		this.codClienteDocumento = codClienteDocumento;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("Entró ReportePEDAvanceFacturadoProgramaReconocimientoVZAction - setValidarReporte()");
		}
		
		ReportePEDAvanceFacturadoProgramaReconocimientoVZForm f = (ReportePEDAvanceFacturadoProgramaReconocimientoVZForm) this.formReporte;
		
		String mensaje = "";
		
		if(this.codClienteDocumento.length > 0 && StringUtils.isNotBlank(f.getCodigoConsultora())){
			mensaje = "Debe elegir solo un método de elección de Código Consultora";
		}
		
		if(this.codClienteDocumento.length == 0 && StringUtils.isBlank(f.getCodigoConsultora())){
			mensaje = "Debe elegir al menos un método de elección de Código Consultora";
		}
				
		Integer perInicio = Integer.parseInt(f.getCodigoPeriodoInicio());
		Integer perFin = Integer.parseInt(f.getCodigoPeriodoFin());
		
		if (perInicio > perFin){ 
			mensaje =  "Periodo Final debe ser mayor o igual a Periodo Inicial";
			
		}
		return mensaje;
	}

	@Override
	protected String devuelveBeanReporteService() {
		// TODO Auto-generated method stub
		return "reportes.reportePEDAvanceFacturadoProgramaReconocimientoVZServiceImpl";
	}
}
