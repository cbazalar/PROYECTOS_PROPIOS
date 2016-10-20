/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.percepciones.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.percepciones.ProcesoPERCargarPagosBancariosMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.percepciones.model.EstructuraPercepcionesPagosBancariosMasivos;

/**
 * @author pejflorencio
 *
 */

@Repository("spusicc.procesoPERCargarPagosBancariosMasivosDAO")
public class ProcesoPERCargarPagosBancariosMasivosDAOiBatis extends	BaseDAOiBatis implements ProcesoPERCargarPagosBancariosMasivosDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUpload", datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#insertEstructuraPagosBancariosMasivos(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraPagosBancariosMasivos)
	 */
	public void insertEstructuraPagosBancariosMasivos(EstructuraPercepcionesPagosBancariosMasivos estructura){
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.insertEstructuraPagosBancariosMasivos", estructura);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaPagosBancariosMasivos() {		
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.deleteTablasCargaPagosBancariosMasivos", null);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#executeValidarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeValidarPagosBancariosMasivos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.executeValidarPagosBancariosMasivos", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#getErroresCargaCargosAbonosMasivos()
	 */
	public List getErroresCargaPagosBancariosMasivos(){
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosPERSQL.getErroresCargaPagosBancariosMasivos");
	}
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.procesoPERCargarPagosBancariosMasivosDAO#executeProcesarPagosBancariosMasivos(java.util.Map)
	 */
	public void executeProcesarPagosBancariosMasivos(Map datos){
		getSqlMapClientTemplate().update("spusicc.ProcesosPERSQL.executeProcesarPagosBancariosMasivos", datos);
	}
}
