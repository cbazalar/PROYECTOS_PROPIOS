/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazMICDAO;
import biz.belcorp.ssicc.dao.spusicc.mic.ProcesoMICGeneracionAptasDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.util.FTPClientWrapper;
import biz.belcorp.ssicc.service.util.SFTPClientWrapper;

/**
 * 
 * <p>
 * <a href="InterfazMICServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
@Service("sisicc.interfazMICEnvioInformacionAseguradoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMICEnvioInformacionAseguradoraServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazMICDAO")
	private InterfazMICDAO interfazMICDAO;
	
	@Resource(name="spusicc.procesoMICGeneracionAptasDAO")
	private ProcesoMICGeneracionAptasDAO procesoMICGeneracionAptasDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazMICDAO.executeEnvioInformacionAseguradora(params);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
    throws InterfazException {
	  log.debug("enviando FTP o SFTP " + interfazParams.getArchivoSalidaPath());
	  //leemos el archivo dejado en la red
	  File inputFile = new File(interfazParams.getArchivoSalidaPath());
	  Map parametros = (Map)procesoMICGeneracionAptasDAO.getParametrosMicroSeguro();
	  if(parametros!=null){
	      String servidor= (String)parametros.get("servidorFtp");	      
	      if(StringUtils.isNotEmpty(servidor)){
	    	  BigDecimal puerto= ((BigDecimal)parametros.get("puertoFtp"));
		      String ruta =(String)parametros.get("rutaFtp");
		      String usuario=(String)parametros.get("usuarioFtp");
		      String pwd=(String)parametros.get("pwdFtp");
		      String indicadorFtp = (String)parametros.get("indicadorFtpSftp");//1:FTP 0:SFTP
		      
		      if(Constants.NUMERO_UNO.equals(indicadorFtp)){
		    	  sendFileToFTP(inputFile, servidor, puerto, ruta, usuario, pwd);
		      }else{
		    	  //enviamos via SFTP
		    	  sendFileToSFTP(inputFile, servidor, puerto, ruta, usuario, pwd);
		      }		      		      	    	
	      }
	  }
	  
	}
	
	/**
	 * Envia el archvio de red a SFTP
	 * @param inputFile
	 * @param servidor
	 * @param puerto
	 * @param ruta
	 * @param usuario
	 * @param pwd
	 * @throws InterfazException 
	 */
	private void sendFileToSFTP(File inputFile, String servidor,
			BigDecimal puerto, String ruta, String usuario, String pwd) throws InterfazException {
		  boolean existeDirectorio = false;
		  SFTPClientWrapper sftp=null;
		  try {
		    sftp = new SFTPClientWrapper(servidor,
			          puerto!=null?puerto.intValue():0,usuario, pwd);  
			String directorioDefault = sftp.printWorkingDirectory();
			log.debug("directorio default "+ directorioDefault);
			existeDirectorio= sftp.changeWorkingDirectory(ruta);
			sftp.sendFile(inputFile, 
		    		      existeDirectorio?ruta:directorioDefault, 
		    		      inputFile.getName());
		} catch (Exception e) {
			log.debug("error "+e.getMessage());						
			throw new InterfazException(e.getMessage()); 
		}finally{
			if(sftp!=null)
				sftp.disconnect();
		}
	}	
	
	/**
	 * Envia el archvio de red a FTP
	 * @param inputFile
	 * @param servidor
	 * @param puerto
	 * @param ruta
	 * @param usuario
	 * @param pwd
	 * @throws InterfazException 
	 */
	private void sendFileToFTP(File inputFile, String servidor,
			BigDecimal puerto, String ruta, String usuario, String pwd) throws InterfazException {
		boolean existeDirectorio = false;
		  FTPClientWrapper ftp = new FTPClientWrapper(servidor,
		          puerto!=null?puerto.intValue():0,usuario, pwd);
		  try {
		    FTPClient clientFtp = ftp.getFtpClient();		  						  
			String directorioDefault = clientFtp.printWorkingDirectory();
			log.debug("directorio default "+ directorioDefault);
			existeDirectorio= clientFtp.changeWorkingDirectory(ruta);
		    ftp.sendFile(inputFile, 
		    		      existeDirectorio?ruta:directorioDefault, 
		    		      inputFile.getName());
		} catch (Exception e) {
			log.debug("error "+e.getMessage());						
			throw new InterfazException(e.getMessage()); 
		}finally{
			if(ftp!=null)
				ftp.disconnect();
		}
	}

}