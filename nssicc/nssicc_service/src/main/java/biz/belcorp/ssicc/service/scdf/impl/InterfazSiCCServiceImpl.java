package biz.belcorp.ssicc.service.scdf.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scdf.InterfazSiCCDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;

@Service("scdf.interfazSiCCService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class InterfazSiCCServiceImpl extends BaseService implements
		InterfazSiCCService {

	@Resource(name = "scdf.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scdf.service.InterfazSiCCService#executeCargaSiCC(java
	 * .lang.String, java.lang.String, java.lang.String)
	 */
	public int executeCargaSiCC(String codigoPais, String codigoPeriodo,
			String usuario) {
		interfazSiCCDAO.executeCargaSiCC(codigoPais, codigoPeriodo, usuario);
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.scdf.service.InterfazSiCCService#
	 * executeCargaNumeroBoletasDespacho(java.lang.String)
	 */
	public void executeCargaNumeroBoletasDespacho(String codigoPais) {
		// Delegamos la ejecucion al DAO correspondiente
		interfazSiCCDAO.executeCargaNumeroBoletasDespacho(codigoPais);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.scdf.service.InterfazSiCCService#executeCargaProductos
	 * (java.lang.String, java.lang.String)
	 */
	public void executeCargaProductos(String codigoPais, String usuario) {
		interfazSiCCDAO.executeCargaProductos(codigoPais, usuario);
	}

	@Override
	public String getPeriodoDefaultByPaisCanal(String codigoPais,
			String codigoCanal) {
		String result = "";
		try {
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoCanal", codigoCanal);

			result = (String) interfazSiCCDAO
					.getPeriodoDefaultByPaisCanal(params);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

}
