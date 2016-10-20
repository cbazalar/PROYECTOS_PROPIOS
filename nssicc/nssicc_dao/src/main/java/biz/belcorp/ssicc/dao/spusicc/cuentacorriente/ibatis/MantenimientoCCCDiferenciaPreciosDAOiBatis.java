/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ibatis;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCDiferenciaPreciosDAO;

/**
 * @author Cristhian Roman
 *
 */
@Repository("spusicc.mantenimientoCCCDiferenciaPreciosDAO")
public class MantenimientoCCCDiferenciaPreciosDAOiBatis extends	BaseDAOiBatis implements MantenimientoCCCDiferenciaPreciosDAO {
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDiferenciaPreciosDAO#getCodigoVentaPrecio(java.util.Map)
	 */
	public LabelValueCUV getCodigoVentaPrecio(Map criteria){
		List l = new ArrayList(); 
		LabelValueCUV lb = new LabelValueCUV();
		l=getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getCodigoVentaPrecio",criteria);
		if(l.size()!=0)
			lb = (LabelValueCUV)l.get(0);		
		return lb;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDiferenciaPreciosDAO#getCargosAbonosList(java.util.Map)
	 */
	public List getCargosAbonosList(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getCargosAbonosList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDiferenciaPreciosDAO#executeProcesoCargosAbonos(java.util.Map)
	 */
	public void executeProcesoCargosAbonos(Map criteria){
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeProcesoCargosAbonos", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.dao.MantenimientoCCCDiferenciaPreciosDAO#getRegistrosProcesar(java.util.Map)
	 */
	public List getRegistrosProcesar(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.cuentacorriente.procesosCCCSQL.getRegistrosProcesar", criteria);
	}

	public void executeGenerarDataCargosAbonos(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.cuentacorriente.procesosCCCSQL.executeGenerarDataCargosAbonos",criteria);
	}
}