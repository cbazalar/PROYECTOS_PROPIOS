
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la recepcin de Carga Estimados Leader.
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazLARRecepcionarEstimadosLeaderService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLARRecepcionarEstimadosLeaderServiceImpl extends
		BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazLARDAO")
	private InterfazLARDAO interfazLARDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processLine(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, int, java.util.Map)
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
		
		//Map map=interfazParams.getQueryParams();
		//log.debug("mapppppppp=" + map);
		
		//String codigoPeriodo = (String)map.get("codigoPeriodo");
		String codigoPeriodo = (String)row.get("codigoPeriodo");
		log.debug("codigoPeriodo=" + codigoPeriodo);
		
		log.debug("Linea Count :" + lineCount);
		
		if (Constants.NUMERO_UNO.equals(String.valueOf(lineCount))) {
			interfazLARDAO.deleteInterfazLARRecepcionarEstimadosLeader(codigoPeriodo);
		}
		
		String estimados = ((String)row.get("estimados")).trim();
		Integer numEstimados = new Integer(estimados);   
		row.put("estimados", numEstimados);
		
		log.debug("row=" + row);
		try{
			interfazLARDAO.insertInterfazLARRecepcionarEstimadosLeader(row);
		}catch(Exception e){
			log.debug("error "+e.getMessage()+" "+row);
			String messageError="Linea: "+lineCount+ " "+e.getMessage()+ ", parametros: "+ row ;
			throw new InterfazException(messageError);		
		}
	
	}

}
