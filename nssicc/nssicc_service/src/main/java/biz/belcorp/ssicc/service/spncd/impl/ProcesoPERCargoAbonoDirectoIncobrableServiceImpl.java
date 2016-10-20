/**
 * 
 */
package biz.belcorp.ssicc.service.spncd.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import biz.belcorp.ssicc.dao.sisicc.InterfazDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERCargoAbonoDirectoIncobrableDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spncd.ProcesoPERCargoAbonoDirectoIncobrableService;

/**
 * @author cbazalar
 *
 */
@Service("spncd.procesoPERCargoAbonoDirectoIncobrableService")
public class ProcesoPERCargoAbonoDirectoIncobrableServiceImpl extends BaseService implements ProcesoPERCargoAbonoDirectoIncobrableService {

	@Resource(name="spusicc.procesoPERCargoAbonoDirectoIncobrableDAO")
	private ProcesoPERCargoAbonoDirectoIncobrableDAO procesoPERCargoAbonoDirectoIncobrableDAO;
	
	@Resource(name="sisicc.interfazDAO")
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
		procesoPERCargoAbonoDirectoIncobrableDAO.executeCargoAbonoDirectoIncobrable(criteria);
	}

	/**
	 * @return Returns the interfazDAO.
	 */
	public InterfazDAO getInterfazDAO() {
		return interfazDAO;
	}

	/**
	 * @param interfazDAO The interfazDAO to set.
	 */
	public void setInterfazDAO(InterfazDAO interfazDAO) {
		this.interfazDAO = interfazDAO;
	}

	/**
	 * @return Returns the procesoPERCargoAbonoDirectoIncobrableDAO.
	 */
	public ProcesoPERCargoAbonoDirectoIncobrableDAO getProcesoPERCargoAbonoDirectoIncobrableDAO() {
		return procesoPERCargoAbonoDirectoIncobrableDAO;
	}

	/**
	 * @param procesoPERCargoAbonoDirectoIncobrableDAO The procesoPERCargoAbonoDirectoIncobrableDAO to set.
	 */
	public void setProcesoPERCargoAbonoDirectoIncobrableDAO(
			ProcesoPERCargoAbonoDirectoIncobrableDAO procesoPERCargoAbonoDirectoIncobrableDAO) {
		this.procesoPERCargoAbonoDirectoIncobrableDAO = procesoPERCargoAbonoDirectoIncobrableDAO;
	}
	
	
	
}
