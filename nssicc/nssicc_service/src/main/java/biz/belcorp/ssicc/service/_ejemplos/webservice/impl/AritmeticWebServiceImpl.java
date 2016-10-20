package biz.belcorp.ssicc.service._ejemplos.webservice.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import biz.belcorp.ssicc.service._ejemplos.webservice.AritmeticWebService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;

@Service("AritmeticWebService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class AritmeticWebServiceImpl extends SpringBeanAutowiringSupport implements AritmeticWebService { 

	@Resource(name="sisicc.interfazSiCCService")
	private InterfazSiCCService interfazSiCCService;
	
	
	public int sumaTest(int a, int b){
		return a + b;
	}

	public String getCampannaActualProceso(){
		return this.interfazSiCCService.getPeriodoDefaultByPaisCanal("PE", "VD");	    			
	}
	 
}
