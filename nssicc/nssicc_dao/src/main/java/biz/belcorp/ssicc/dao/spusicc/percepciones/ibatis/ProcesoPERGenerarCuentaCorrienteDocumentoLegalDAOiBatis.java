/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.percepciones.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.percepciones.ProcesoPERGenerarCuentaCorrienteDocumentoLegalDAO;

/**
 * @author pejflorencio
 *
 */

@Repository("spusicc.procesoPERGenerarCuentaCorrienteDocumentoLegalDAO")
public class ProcesoPERGenerarCuentaCorrienteDocumentoLegalDAOiBatis extends	BaseDAOiBatis implements ProcesoPERGenerarCuentaCorrienteDocumentoLegalDAO {
	
			

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#executeProcesarPagosBancariosMasivos(java.util.Map)
	 */
	public void executeGenerarCuentaCorrienteDocumentoLegal(Map datos){
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.executeGenerarCuentaCorrienteDocumentoLegal", datos);
	}
}
