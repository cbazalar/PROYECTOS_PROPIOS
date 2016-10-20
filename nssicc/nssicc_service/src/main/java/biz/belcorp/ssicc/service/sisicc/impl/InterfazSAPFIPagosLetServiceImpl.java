package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * The Class InterfazSAPFIPagosLetServiceImpl.
 *
 * @author Belcorp
 * @version 1.0
 * 11:27:50 AM
 */
@Service("sisicc.interfazSAPFIPagosLetService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAPFIPagosLetServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazSAFDAO")
	protected InterfazSAFDAO interfazSAFDAO; 
		
	/**
	 * Prepare query params.
	 *
	 * @param interfazParams the interfaz params
	 * @return the map
	 * @throws InterfazException the interfaz exception
	 */
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		String extension = ".csv";
		String codigoPais = (String) queryParams.get("codigoPais");
		String codigoSistema = (String) queryParams.get("codigoSistema");
		String codigoInterfaz = (String) queryParams.get("codigoInterfaz"); 
		
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		String numeroLote =(String)queryParams.get("numeroLote");
		/*		interfazService.getNumeroLote(interfazEjecucionPK);
		queryParams.put("numeroLote", numeroLote);*/
		
		
		  // Actualizamos el nombre del archivo para incluir el codigo de comision
	  
	        String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");
	        if(nombreArchivo == null)
	        	nombreArchivo=" ";
	        // Quitamos el numero de lote
	        nombreArchivo = StringUtils.substring(nombreArchivo, 0, StringUtils.indexOf(nombreArchivo, numeroLote));
	        // Concatenamos el valor de la comision
	     //   nombreArchivo = nombreArchivo + codigoComision + "_";
	        // Asignamos el nuevo valor en el map
	        queryParams.put("nombreArchivo", nombreArchivo + numeroLote);
	        // Actualizamos tambien el objeto interfaz
	        interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
	  //  }
	    if (log.isDebugEnabled()) {
			log.debug(queryParams);
	        log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
		return queryParams;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.debug("inicio InterfazSAPFIPagosLetServiceImpl"	+ params);
		try {
			interfazSAFDAO.executeInterfazSAFPagosLet(params);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}


}
