package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazPREDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

@Service("sisicc.interfazPRERecepcionarProductoOfertaNivelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRERecepcionarProductoOfertaNivelesServiceImpl extends BaseInterfazEntradaAbstractService 
{
	@Resource(name="sisicc.interfazPREDAO")
	private InterfazPREDAO interfazPREDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	@Override
	protected void addLine(List data, Map row) 
	{
		data.add(row);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams, java.util.List)
	 */
	@Override
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException 
	{	
		int j = 1;
		for(int i=0; i< data.size(); i++)
		{
			HashMap criteria = (HashMap) data.get(i);
			criteria.put("numRegistro", j);	

			interfazPREDAO.insertProductoOfertaNiveles(criteria);
			j++;
		}
	}
}
