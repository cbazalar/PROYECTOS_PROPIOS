package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.util.ZipUtil;

@Service("sisicc.paqueteInterfazSAPFIReportesSAPFIService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazSAPFIReportesSAPFIServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl{

	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService#afterExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		//código para el zipeado de los archivos
		File[] archivosSalida=new File[3];
		archivosSalida[0]=new File((String)params.get("directorioCabecera"),(String)params.get("nombreArchivoCabecera"));
		archivosSalida[1]=new File((String)params.get("directorioDetalle"),(String)params.get("nombreArchivoDetalle"));
		archivosSalida[2]=new File((String)params.get("directorioResumen"),(String)params.get("nombreArchivoResumen"));
				
		String nombreArchivoZip="reporteSAPFI.zip";
		File zipFile = new File((String)params.get("directorioCabecera"), nombreArchivoZip);
		ZipUtil.zipFiles(archivosSalida, zipFile);
		
		String formaEnvio=(String)params.get("formaEnvio");
		if(StringUtils.isNotBlank(formaEnvio)){
			if(StringUtils.equals(formaEnvio, "CORREO")){
				//código para el envío por correo electrónico
				Map paramReporte = null;
				try {
					
					params.put("nombreReporte","reporteSAPFI");
					paramReporte =  reporteService.getParametrosReporte(params);
					
					 // Creamos el mensaje y configuramos los principales atributos
		            MimeMessage mime = javaMailSender.createMimeMessage();
		            MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
		            String correoOrigen=(String)paramReporte.get("correoOrigen");
		            helper.setFrom(correoOrigen);
		            String listaCorreoTo = (String) paramReporte.get("correoDefault");
		            helper.setTo(listaCorreoTo);
		            String subject = (String) paramReporte.get("subject");
		            helper.setSubject(subject);
		            Pais pais=(Pais)params.get("pais");
		            Usuario usuario=(Usuario)params.get("usuario");            
		            
		            String linea="<html> <head> <meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'> <title> Reportes SAPFI</title> </head> <body> <table style='text-align: left; width: 490px; height: 461px;' border='0' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='height: 172px; width: 256px;'> <font color='#4188b2' face='Arial' size='3'>Se ha realizado el envío del <strong> Reportes SAPFI </strong>&nbsp;desde el Sistema de SSiCC correspondiente al pais&nbsp;<strong>"+pais.getDescripcion()+"</strong> <strong> </font> </td> </tr> </tbody> </table> <font face='Arial' size='1'><br> <strong> NOTA: Por favor no responda a este mensaje, es generado automáticamente desde una cuenta no monitoreada. </strong> <br> Proceso ejecutado por el Usuario <strong>"+usuario.getLogin()+"</strong>. <br> <br> Se adjunta el archivo correspondiente: </font> <br> <br> <br> </body> </html>";
		            
		            helper.setText(linea, true);
		            File archivoAtachado = new File((String)params.get("directorioCabecera"),nombreArchivoZip);
		    		FileSystemResource fsr = new FileSystemResource(archivoAtachado);
		            helper.addAttachment(nombreArchivoZip, fsr);
		            javaMailSender.send(mime);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}else{
				formaEnvio="DESCARGA";
			}
		}
		if(StringUtils.isNotBlank(formaEnvio)&&StringUtils.equals(formaEnvio, "CORREO")){
			return ;
		}
		else{
			String separador = "/";
			String nombreArchivo="reporteSAPFI";
			String extension=".zip";
			
			params.put("nombreArchivo", nombreArchivo);
			params.put("extensionArchivo", extension);
			params.put("directorioArchivo", (String)params.get("directorioCabecera")+separador);
			params.put("nombreArchivoDescarga", nombreArchivo+extension);
		}
	}
    
    
    
}
