/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraPagosBancariosPorRegularizarMasivos;

/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCCargarPagosBancariosPorRegularizarMasivosDAO")
public class ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAOiBatis extends	BaseDAOiBatis implements ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUpload", datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO#insertEstructuraPagosBancariosPorRegularizarMasivos(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraPagosBancariosPorRegularizarMasivos)
	 */
	public void insertEstructuraPagosBancariosPorRegularizarMasivos(EstructuraPagosBancariosPorRegularizarMasivos estructura){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.insertEstructuraPagosBancariosPorRegularizarMasivos", estructura);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaPagosBancariosPorRegularizarMasivos() {		
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteTablasCargaPagosBancariosPorRegularizarMasivos", null);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO#executeValidarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeValidarPagosBancariosPorRegularizarMasivos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarPagosBancariosPorRegularizarMasivos", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO#getErroresCargaCargosAbonosMasivos()
	 */
	public List getErroresCargaPagosBancariosPorRegularizarMasivos(){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getErroresCargaPagosBancariosPorRegularizarMasivos");
	}
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosPorRegularizarMasivosDAO#executeProcesarPagosBancariosPorRegularizarMasivos(java.util.Map)
	 */
	public void executeProcesarPagosBancariosPorRegularizarMasivos(Map datos){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarPagosBancariosPorRegularizarMasivos", datos);
	}
}
