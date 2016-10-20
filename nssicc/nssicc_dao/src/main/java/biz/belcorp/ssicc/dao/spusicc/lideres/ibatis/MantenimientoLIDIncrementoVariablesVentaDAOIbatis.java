package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDIncrementoVariablesVentaDAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.IncrementoVariableVenta;

@Repository("spusicc.mantenimientoLIDIncrementoVariablesVentaDAO")
public class MantenimientoLIDIncrementoVariablesVentaDAOIbatis extends BaseDAOiBatis implements 
		MantenimientoLIDIncrementoVariablesVentaDAO {

	public List getTipoIncrementoList() {
		 return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getTipoIncrementoList", null);
	
	}

	public List getVariableVentasList(String codIdioma) {
		  return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getVariableVentasList",
				  codIdioma);
	}

	public List getIncrementoVariableVentaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getIncrementoVariableVentaList",
				  criteria);
	}

	public String getPeriodoMaximo(Map criteria) {
		 return (String)getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getPeriodoMaximo",criteria);
	}

	public void saveVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta) {
		getSqlMapClientTemplate().update("spusicc.lideres.MantenimientoLIDSQL.saveIncrementoVariableVenta",incrementoVariableVenta);
		
	}

	public void updateVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta) {
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.updateIncrementoVariableVenta",incrementoVariableVenta);
		
	}

	public void executeGenerarActividadFinalZonasPeriodo(Map params) {
		getSqlMapClientTemplate().update("spusicc.lideres.ProcesosLIDSQL.executeGenerarActividadFinalZonasPeriodo",params);
		
	}
	
	

	
	

}
