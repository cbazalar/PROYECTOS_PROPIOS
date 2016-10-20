package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;

@Service("sisicc.paqueteProcesoPERCruceSaldoPositivoNegativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteProcesoPERCruceSaldoPositivoNegativoServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl{

	protected boolean continueExecuteInterfaz(Map params) {
		boolean flag = false;
		if(params!=null)
		{	if (((String)params.get("exito")).equalsIgnoreCase("1"))
				flag = true;
			else
				flag = false;
		}
		return flag;
	}
}
