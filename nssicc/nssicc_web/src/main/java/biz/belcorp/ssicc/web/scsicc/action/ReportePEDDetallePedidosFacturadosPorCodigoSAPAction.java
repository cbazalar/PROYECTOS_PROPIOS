package biz.belcorp.ssicc.web.scsicc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.util.XlsxUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDDetallePedidosFacturadosPorCodigoSAPForm;

@ManagedBean
@SessionScoped
public class ReportePEDDetallePedidosFacturadosPorCodigoSAPAction extends BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 5452137025798023586L;
	
	private String attachment = "";
	private String [] listCodigoSAP = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDDetallePedidosFacturadosPorCodigoSAPForm reporteForm = new ReportePEDDetallePedidosFacturadosPorCodigoSAPForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		return "reportePEDDetallePedidosFacturadosPorCodigoSAPXLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entró ReportePEDDetallePedidosFacturadosPorCodigoSAPAction - setViewAtributes()");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReportePEDDetallePedidosFacturadosPorCodigoSAPForm f = (ReportePEDDetallePedidosFacturadosPorCodigoSAPForm) this.formReporte;
		
		log.debug("Todo Ok: Redireccionando");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("Entró ReportePEDDetallePedidosFacturadosPorCodigoSAPAction - prepareParameterMap");
		}

		ReportePEDDetallePedidosFacturadosPorCodigoSAPForm f = (ReportePEDDetallePedidosFacturadosPorCodigoSAPForm) this.formReporte;
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		
		String condicionSAP = "";
		
		if(this.listCodigoSAP.length > 0 && this.listCodigoSAP != null){
			condicionSAP = this.obtieneCondicion(this.listCodigoSAP, "E.COD_SAP", "'");
			params.put("condicionSAP1", condicionSAP);
			params.put("listCodigoSAP", this.listCodigoSAP);
		}
		
		if(StringUtils.isNotBlank(f.getCodigoSAP())){
			params.put("condicionSAP2", f.getCodigoSAP());
		}

		return params;
	}
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {		
		ReportePEDDetallePedidosFacturadosPorCodigoSAPForm f = (ReportePEDDetallePedidosFacturadosPorCodigoSAPForm) this.formReporte;
		
		if (event != null) {
			f.setSapsFile(event.getFile());
			this.attachment = event.getFile().getFileName();
			this.uploadArchivo();
			XlsxUtil excelUtil = new XlsxUtil(f.getDirectorioTemporal(), this.attachment);		
			
			String codigos = "";
			
			List<String> codigoLista = new ArrayList<String>();
			excelUtil.initSheet(0);
			
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.nextString();
				codigos = mapRow.get("0").toString().trim();
				
				if(StringUtils.isNotBlank(codigos))
					codigoLista.add(codigos);
			}
			
			excelUtil.cerrar();
			
			String []temp= new String [codigoLista.size()];
			temp = codigoLista.toArray(temp);		
			this.listCodigoSAP = temp;			
		}
	}
	
	public void uploadArchivo(){
		try {
			ReportePEDDetallePedidosFacturadosPorCodigoSAPForm f = (ReportePEDDetallePedidosFacturadosPorCodigoSAPForm) this.formReporte;			
			UploadedFile archivo = f.getSapsFile();
			
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("Entró ReportePEDDetallePedidosFacturadosPorCodigoSAPAction - setValidarReporte()");
		}
		
		ReportePEDDetallePedidosFacturadosPorCodigoSAPForm f = (ReportePEDDetallePedidosFacturadosPorCodigoSAPForm) this.formReporte;
		
		String mensaje = "";
		
		if(this.listCodigoSAP.length > 0 && StringUtils.isNotBlank(f.getCodigoSAP())){
			mensaje = "Debe elegir solo un método de elección de Código SAP";
		}
		
		if(this.listCodigoSAP.length == 0 && StringUtils.isBlank(f.getCodigoSAP())){
			mensaje = "Debe elegir al menos un método de elección de Código SAP";
		}
		
		return mensaje;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reportePEDDetallePedidosFacturadosPorCodigoSAPService";
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
	 * @return the listCodigoSAP
	 */
	public String[] getListCodigoSAP() {
		return listCodigoSAP;
	}

	/**
	 * @param listCodigoSAP the listCodigoSAP to set
	 */
	public void setListCodigoSAP(String[] listCodigoSAP) {
		this.listCodigoSAP = listCodigoSAP;
	}
}