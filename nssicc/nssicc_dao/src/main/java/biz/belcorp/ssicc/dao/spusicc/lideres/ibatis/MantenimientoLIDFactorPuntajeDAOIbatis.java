package biz.belcorp.ssicc.dao.spusicc.lideres.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDFactorPuntajeDAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.FactorPuntaje;

@Repository("spusicc.mantenimientoLIDFactorPuntajeDAO")
public class MantenimientoLIDFactorPuntajeDAOIbatis extends BaseDAOiBatis implements
		MantenimientoLIDFactorPuntajeDAO {

	
	public List getNumeroConcursoList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getNumeroConcursoList",criteria);
	}

	public List getFactorPuntajeList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getFactorPuntajeList",criteria);
	}
	
	public String getPeridoDesde(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getPeriodoDesde",criteria);
	}

	public String getPeridoHasta(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.lideres.MantenimientoLIDSQL.getPeriodoHasta",criteria);
																									 
	}

	public List getTipoAsignacionList() {
		return getSqlMapClientTemplate().queryForList("spusicc.lideres.MantenimientoLIDSQL.getTipoAsignacionList", null);
	}

	public void saveFactorPuntaje(FactorPuntaje factorPuntaje) {
		getSqlMapClientTemplate().insert("spusicc.lideres.MantenimientoLIDSQL.saveFactorPuntaje",factorPuntaje);
		
	}

	public void updateFactorPuntaje(FactorPuntaje factorPuntaje) {
		getSqlMapClientTemplate().update("spusicc.lideres.MantenimientoLIDSQL.updateFactorPuntaje",factorPuntaje);
		
	}

	
	
}
