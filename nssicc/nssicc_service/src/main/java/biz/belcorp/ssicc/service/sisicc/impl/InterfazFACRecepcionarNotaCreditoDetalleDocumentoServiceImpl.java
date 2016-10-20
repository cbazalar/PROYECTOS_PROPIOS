/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazFACDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazFACRecepcionarNotaCreditoDetalleDocumentoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("sisicc.interfazFACRecepcionarNotaCreditoDetalleDocumentoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFACRecepcionarNotaCreditoDetalleDocumentoServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.InterfazFACDAO")
	InterfazFACDAO interfazFACDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		
		Map map = interfazParams.getQueryParams();
		map.put("usuario", interfazParams.getUsuario().getLogin());
		map.put("nombreArchivo", interfazParams.getTempName());
		
		try {
			interfazFACDAO.executeInterfazFACRecepcionarNotasCreditoDetalleDocumento(map);
		} catch (Exception e) {
			throw new InterfazException("Error al recepcionar datos del detalle de Notas de Credito: "+ e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		
		try
		{
			Interfaz interfaz = interfazParams.getInterfaz();
			List parametros = interfaz.getParametros();
			Interfaz tmpInterfaz = new Interfaz();
			if(parametros != null && parametros.size() > 0)
			{
				for(int i=0; i<parametros.size(); i++)
				{
					log.debug(parametros.get(i));
					ParametroInterfaz pi = (ParametroInterfaz)parametros.get(i);
					
					if(StringUtils.equals(pi.getNombre(), Constants.FAC_CODIGO_PARAMETRO_SERVIDOR_FTP)){
						tmpInterfaz.setServidorFtp(pi.getValor());
					}
					
					if(StringUtils.equals(pi.getNombre(), Constants.FAC_CODIGO_PARAMETRO_PUERTO_FTP)){
						tmpInterfaz.setPuertoFtp(pi.getValor());
					}
					
					if(StringUtils.equals(pi.getNombre(), Constants.FAC_CODIGO_PARAMETRO_USUARIO_FTP)){
						tmpInterfaz.setUsuarioFtp(pi.getValor());
					}
					
					if(StringUtils.equals(pi.getNombre(), Constants.FAC_CODIGO_PARAMETRO_CLAVE_FTP)){
						tmpInterfaz.setPasswordFtp(pi.getValor());
					}
					
					if(StringUtils.equals(pi.getNombre(), Constants.FAC_CODIGO_PARAMETRO_DIRECTORIO_FTP)){
						tmpInterfaz.setDirectorioHistorico(pi.getValor());
					}
				}
			}
			
			File file = new File(FileUtil.formatDirectory(interfaz.getDirectorioHistorico()) + interfazParams.getTempName() + "." + interfaz.getExtensionArchivoDescripcion());
			
			if(file.exists())
			{
				FTPUtil ftpUtil = new FTPUtil();
				ftpUtil.loguearFTP(tmpInterfaz);
				ftpUtil.copiarArchivoRedaFTP(file, tmpInterfaz.getDirectorioHistorico(), true);
				ftpUtil.cerrarFTP();
			}
			else
			{
				throw new Exception("El archivo no fue generado");
			}
			file.delete();
		}
		catch(Exception ex)
		{
			throw new InterfazException(ex.getMessage());
		}
		
	}

}
