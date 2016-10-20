/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazMAERecibirAsistenciaConferenciaServiceImpl"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas</a>
 */
@Service("sisicc.interfazMAERecibirAsistenciaConferenciaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMAERecibirAsistenciaConferenciaServiceImpl extends BaseInterfazEntradaAbstractService {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	@Override
	protected void addLine(List data, Map row) {
		// TODO Auto-generated method stub
		data.add(row);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.util.List)
	 */
	@Override
	protected void processData(InterfazParams interfazParams, List data)
		throws InterfazException {

		Usuario usuario = interfazParams.getUsuario();
		
		for(int i=0; i< data.size(); i++)
		{
			HashMap criteria = (HashMap) data.get(i);
			
			criteria.put("usuario", usuario.getLogin());
				
			//buscamos si existe en la bd
			int valor = interfazSiCCDAO.getExisteAsistenciaConferencia(criteria);
			
			if(valor > 0)
			{
				interfazSiCCDAO.updateAsistenciaConferencia(criteria);
			}
			else
			{
				interfazSiCCDAO.insertAsistenciaConferencia(criteria);
			}			
		}
		
	}

	
}
