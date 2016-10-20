 package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazFLXDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author sbuchelli
 * 
 */
@Service("sisicc.interfazFLXRecepcionarConsultorasComunicacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFLXRecepcionarConsultorasComunicacionServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazFLXDAO")
	private InterfazFLXDAO interfazFLXDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams)
	throws InterfazException {
    	if (log.isDebugEnabled())
			log.debug("Entering 'beforeReadData' method");
    	
    	Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
    	
    	Map map = interfazParams.getQueryParams();
    	map.put("codigoUsuario", usuario.getLogin());
    	map.put("nombreArchivo", interfazParams.getTempName());
    	
    	log.debug("Entrando a ejecutar mi procedimiento");
    	
    	try {

    		interfazFLXDAO.executeInterfazAVIRecepcionarConsultorasComunicacion(map);
    			
    	} catch (Exception e) {
			throw new InterfazException("Error al borrar/cargar los registros de la tabla temporal: " + e.getMessage());
		}
    	
	}
	
	/*protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		if (data == null || data.size() == 0) {
			throw new InterfazException(Constants.INTERFAZSICC_ARCHIVO_CEROREGISTROS_ENTRADA);
		}		
		interfazParams.getQueryParams().put("registrosProcesados", new Long(0));
		interfazParams.getQueryParams().put("registrosErroneos", new Long(0));
				
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'processData'");
		}
		
		String codigoPais =	(String)interfazParams.getQueryParams().get("codigoPais");
		Usuario usuario = (Usuario)interfazParams.getQueryParams().get("usuario");
		String flagHabilitada = "";
		
		try {
			Iterator it = data.iterator();			
			 Insertando Recomendantes - Recomendadas 
			log.info("insertando los registros leidos");
			int i=0;
			while(it.hasNext()) {
				HashMap row = (HashMap)it.next();  
								
				row.put("codigoPais", codigoPais);
				row.put("loginUsuario", usuario.getLogin());
				
				interfazFLXDAO.executeInterfazAVIRecepcionarConsultorasComunicacion(row);
								
				//Buscamos si el registro ya existe en la tabla de consultoras habilitadas
				interfazFLXDAO.verificarConsultoraHabilitada(row);
				
				flagHabilitada = MapUtils.getString(row, "flagHabilitada");
				if(StringUtils.equals(flagHabilitada, Constants.SI))
				{
					row.put("indicadorComunicacion", Constants.ESTADO_ACTIVO);
					interfazFLXDAO.updateConsultoraHabilitada(row);
				}
				
				i++;
			}						
		}	
		catch (Exception e) {
			e.printStackTrace();
			InterfazException er = new InterfazException(e.getMessage());
			er.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BASE_DATOS);
			throw er;
		}
		
	}*/

	
}
