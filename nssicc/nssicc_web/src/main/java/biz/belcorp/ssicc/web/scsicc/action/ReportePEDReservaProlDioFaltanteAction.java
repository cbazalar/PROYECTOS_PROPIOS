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
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDReservaProlDioFaltanteForm;

@ManagedBean
@SessionScoped
public class ReportePEDReservaProlDioFaltanteAction extends BaseReporteAbstractAction{
	
	private static final long serialVersionUID = -1102428410263810121L;
	
	private String attachment = "";
	private String [] codClienteDocumento={};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDReservaProlDioFaltanteForm reporteForm = new ReportePEDReservaProlDioFaltanteForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(StringUtils.equals("XLS", this.formatoExportacion))	
			return "reportePEDReservaProlDioFaltanteXLS";
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
		ReportePEDReservaProlDioFaltanteForm f=(ReportePEDReservaProlDioFaltanteForm)this.formReporte;
		String codigosCuv=this.obtieneCondicion(this.codClienteDocumento, "z.val_codi_vent", "'");
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("condicionCodigos", codigosCuv);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF=false;
		this.mostrarReporteXLS=true;
		ReportePEDReservaProlDioFaltanteForm f=(ReportePEDReservaProlDioFaltanteForm)this.formReporte;
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		
	}
	
	//Cargar el archivo Excel
	public void handleFileUpload(FileUploadEvent event) throws Exception {		
		ReportePEDReservaProlDioFaltanteForm f = (ReportePEDReservaProlDioFaltanteForm) this.formReporte;
		if (event != null) {			
			f.setClienteFile(event.getFile());
			this.attachment=event.getFile().getFileName();			
			this.uploadArchivo();
			XlsxUtil excelUtil = new XlsxUtil(f.getDirectorioTemporal(), this.attachment);	
			
			String codigos="";
			List<String> codigoLista= new ArrayList<String>();
			excelUtil.initSheet(0);
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.nextString();
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
			ReportePEDReservaProlDioFaltanteForm f = (ReportePEDReservaProlDioFaltanteForm) this.formReporte;			
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
	
	

}
