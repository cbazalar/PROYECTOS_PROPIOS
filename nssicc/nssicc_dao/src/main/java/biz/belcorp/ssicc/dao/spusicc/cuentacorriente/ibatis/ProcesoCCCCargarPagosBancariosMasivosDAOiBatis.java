/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarPagosBancariosMasivosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.EstructuraPagosBancariosMasivos;

/**
 * @author pejflorencio
 *
 */
/**
 * @author pejflorencio
 *
 */
@Repository("spusicc.procesoCCCCargarPagosBancariosMasivosDAO")
public class ProcesoCCCCargarPagosBancariosMasivosDAOiBatis extends	BaseDAOiBatis implements ProcesoCCCCargarPagosBancariosMasivosDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#obtenerPathUpload(java.util.Map)
	 */
	public String obtenerPathUpload(Map datos) {
		return (String) getSqlMapClientTemplate().
		queryForObject("spusicc.cuentacorriente.procesosCCCSQL.getPathUpload", datos);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#insertEstructuraPagosBancariosMasivos(biz.belcorp.ssicc.spusicc.cuentacorriente.model.EstructuraPagosBancariosMasivos)
	 */
	public void insertEstructuraPagosBancariosMasivos(EstructuraPagosBancariosMasivos estructura){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.insertEstructuraPagosBancariosMasivos", estructura);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#deleteTablasCargaCargosAbonosMasivos()
	 */
	public void deleteTablasCargaPagosBancariosMasivos() {		
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteTablasCargaPagosBancariosMasivos", null);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#executeValidarCargosAbonosMasivos(java.util.Map)
	 */
	public void executeValidarPagosBancariosMasivos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarPagosBancariosMasivos", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#getErroresCargaCargosAbonosMasivos()
	 */
	public List getErroresCargaPagosBancariosMasivos(){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getErroresCargaPagosBancariosMasivos");
	}
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#executeProcesarPagosBancariosMasivos(java.util.Map)
	 */
	public void executeProcesarPagosBancariosMasivos(Map datos){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesarPagosBancariosMasivos", datos);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#getParamCargaArchivosBanco()
	 */
	public List getParamCargaArchivosBanco(Map datos){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getParamCargaArchivosBanco",datos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#insertCargaArchivosBanco(java.util.Map)
	 */
	public void insertCargaArchivosBanco(Map datos){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.insertCargaArchivosBanco", datos);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#executeValidarCargaArchivosBanco()
	 */
	public void executeValidarCargaArchivosBanco(){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeValidarCargaArchivosBanco");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.ProcesoCCCCargarPagosBancariosMasivosDAO#deleteTablaCargaArchivosBanco()
	 */
	public void deleteTablaCargaArchivosBanco(){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.deleteTablaCargaArchivosBanco");

	}
}
