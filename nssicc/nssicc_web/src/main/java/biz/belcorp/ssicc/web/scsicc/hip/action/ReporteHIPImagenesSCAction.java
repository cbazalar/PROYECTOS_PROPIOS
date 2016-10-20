package biz.belcorp.ssicc.web.scsicc.hip.action;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletResponse;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.util.ImagenPDFUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ReporteHIPImagenesSCForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ReporteHIPImagenesSCAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private String codigoCliente;
	
	

	public void executeReporteImage(){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'dc' method");
		}
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoSTOBloqueoControlService stoService = (MantenimientoSTOBloqueoControlService)
												getBean("spusicc.mantenimientoSTOBloqueoControlService");
			HttpServletResponse response = this.getResponse();

			String codigoCliente = this.codigoCliente;
			
			//-- Seteamos el Codigo de Pais y Codigo de Sistema
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoSistema", "OCR");
			
			//-- obtenemos los valores para comunicarse con el servidor FTP 
			criteria.put("nombreParametro", "servidorFtpSC");
			String servidorFtpSC = stoService.getParametroGenericoSistema(criteria);
			criteria.put("nombreParametro", "puertoFtpSC");
			String puertoFtpSC = stoService.getParametroGenericoSistema(criteria);
			criteria.put("nombreParametro", "directorioFtpSC");
			String directorioFtpSC = stoService.getParametroGenericoSistema(criteria);
			criteria.put("nombreParametro", "usuarioFtpSC");
			String usuarioFtpSC = stoService.getParametroGenericoSistema(criteria);
			criteria.put("nombreParametro", "passwordFtpSC");
			String passwordFtpSC = stoService.getParametroGenericoSistema(criteria);
			criteria.put("nombreParametro", "directorioTempSC");
			String directorioTempSC = stoService.getParametroGenericoSistema(criteria);
			criteria.put("nombreParametro", "scaleFitSC");
			String scaleFitSC = stoService.getParametroGenericoSistema(criteria);
			
			//Generamos el pdf en base a las imagenes recuperas del servidor FTP
			ImagenPDFUtil imagenUtil = new ImagenPDFUtil();
			imagenUtil.generarPdfFtpToLocal(servidorFtpSC, puertoFtpSC, directorioFtpSC + codigoCliente, 
											usuarioFtpSC, passwordFtpSC, "jpg",	directorioTempSC, 
											codigoCliente, Long.parseLong(scaleFitSC));
			
			//Una vez generado el archivo PDF, lo mostramos.
			FileInputStream fileInput = new FileInputStream(directorioTempSC + codigoCliente + ".pdf");
	        ByteArrayOutputStream out = new ByteArrayOutputStream(); 
	        byte[] buffer = new byte[8192];
	        int totalBytes = 0;
	        int sizeRead = 0;
	        while ((sizeRead = fileInput.read(buffer)) >= 0) { //leyendo del host
	            out.write(buffer, 0, sizeRead); //escribiendo para el navegador
	            totalBytes = totalBytes + sizeRead;
	        }
	        fileInput.close(); // y cerrando
	        
	        byte[] bytes = out.toByteArray();
	        out.close();
	        
	        this.mPantallaPrincipalBean.setViewPDFMedia(true);
			response.reset();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline");
			response.setContentLength(bytes.length);
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
			this.responseComplete();
			this.viewReporteMedia = true;
			
			
			//borramos el fichero PDF generado 
//			try {
//				File filePDF = new File(directorioTempSC + codigoCliente + ".pdf");
//				filePDF.delete();
//			} catch (Exception e) {
//				log.error("No se pudo eliminar el archivo :" + codigoCliente + ".pdf");
//			}	
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	
		
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteHIPImagenesSCForm  form  = new ReporteHIPImagenesSCForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return null;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	
}
