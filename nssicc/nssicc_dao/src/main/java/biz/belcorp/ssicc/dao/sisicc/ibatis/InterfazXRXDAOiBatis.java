/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazXRXDAO;

/**
 * @author Sigcomt
 *
 */
@Repository("sisicc.interfazXRXDAO")
public class InterfazXRXDAOiBatis extends BaseDAOiBatis implements
		InterfazXRXDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#executeInterfazXRXEnviarBoletaVentaElectronica(java.util.Map)
	 */
	public void executeInterfazXRXEnviarBoletaVentaElectronica(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazXRXSQL.executeInterfazXRXEnviarBoletaVentaElectronica", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#executeInterfazXRXEnviarNotaCreditoElectronica(java.util.Map)
	 */
	public void executeInterfazXRXEnviarNotaCreditoElectronica(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazXRXSQL.executeInterfazXRXEnviarNotaCreditoElectronica", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#updateInterfazXRXRecepcionarBoletaVentaElectronica(java.util.Map)
	 */
	public void updateInterfazXRXRecepcionarBoletaVentaElectronica(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazXRXSQL.updateInterfazXRXRecepcionarBoletaVentaElectronica",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#updateInterfazXRXRecepcionarNotaCreditoElectronica(java.util.Map)
	 */
	public void updateInterfazXRXRecepcionarNotaCreditoElectronica(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazXRXSQL.updateInterfazXRXRecepcionarNotaCreditoElectronica",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#moverNotaCreditoAHistorico()
	 */
	public void executeNotaCreditoAHistorico(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazXRXSQL.executeNotaCreditoAHistorico", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#moverBoletaVentaAHistorico()
	 */
	public void executeBoletaVentaAHistorico(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazXRXSQL.executeBoletaVentaAHistorico",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#getCantidadBoletas(java.lang.String)
	 */
	public int getCantidadBoletasVenta(String nombreArchivoSinExtension) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazXRXSQL.getCantidadBoletasVenta", nombreArchivoSinExtension);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazXRXDAO#getCantidadNotasCredito(java.lang.String)
	 */
	public int getCantidadNotasCredito(String nombreArchivoSinExtension) {
		return (Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazXRXSQL.getCantidadNotasCredito", nombreArchivoSinExtension);
	}

	
	
}
