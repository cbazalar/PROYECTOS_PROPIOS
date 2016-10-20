/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

  
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarLotesBancariosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarLotesBancariosService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ReporteCCCGenerarReporteCargaLotesBancariosService;

/**
 * @author Jorge Florencio Arias
 *
 */
@Service("spusicc.procesoCCCCargarLotesBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarLotesBancariosServiceImpl extends BaseService implements ProcesoCCCCargarLotesBancariosService {
		
	@Resource(name = "spusicc.reporteCCCGenerarReporteCargaLotesBancariosService")
	ReporteCCCGenerarReporteCargaLotesBancariosService service;
		 
	@Resource(name = "spusicc.procesoCCCCargarLotesBancariosDAO")
	ProcesoCCCCargarLotesBancariosDAO procesoCCCCargarLotesBancariosDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCCargarLotesBancariosService#executeCargarLotesBancarios(java.util.Map)
	 */
	public void executeCargarLotesBancarios(Map params){
		
		procesoCCCCargarLotesBancariosDAO.executeCargarLotesBancarios(params);
						 
		 try {
			
			 params.put("correoDestino", "jflorencio@belcorp.biz");
						 
			 String numeroLote = (String) params.get("numeroLote");
			 log.debug(" *** Numero de Lote ***");
			 log.debug(numeroLote);
			 
			service.setNumeroLote(numeroLote);			
			//service.grabarReporte(params);			
			//return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
		
   
}
