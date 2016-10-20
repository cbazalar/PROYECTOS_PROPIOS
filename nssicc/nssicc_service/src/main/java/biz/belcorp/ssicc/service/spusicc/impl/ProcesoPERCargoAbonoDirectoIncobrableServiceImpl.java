/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCargoAbonoDirectoIncobrableDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERCargoAbonoDirectoIncobrableService;

/**
 * @author cbazalar
 *
 */
@Service("spusicc.procesoPERCargoAbonoDirectoIncobrableService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERCargoAbonoDirectoIncobrableServiceImpl extends BaseService
		implements ProcesoPERCargoAbonoDirectoIncobrableService {

	private ProcesoPERCargoAbonoDirectoIncobrableDAO procesoPERCargoAbonoDirectoIncobrableDAO;
	private InterfazDAO interfazDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPERCargoAbonoDirectoIncobrableService#executeCargoAbonoDirecto(java.util.Map)
	 */
	public void executeCargoAbonoDirecto(Map criteria) {
		String numeroLote;
		InterfazPK pk = null;
		String codigoPais     = (String) criteria.get("codigoPais");
		String codigoSistema  = (String) criteria.get("codigoSistema");
		String codigoInterfaz = (String) criteria.get("codigoInterfaz"); 	
		
		pk = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);		
		numeroLote = interfazDAO.getNumeroLote(pk);
		criteria.put("numeroLote", numeroLote);
		criteria.put("numeroLoteSolicitud", numeroLote);
		procesoPERCargoAbonoDirectoIncobrableDAO.executeCargoAbonoDirectoIncobrable(criteria);
	}
}
