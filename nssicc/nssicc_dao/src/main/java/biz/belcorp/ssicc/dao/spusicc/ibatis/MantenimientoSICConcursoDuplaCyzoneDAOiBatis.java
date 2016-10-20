package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSICConcursoDuplaCyzone;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoSICConcursoDuplaCyzoneDAO;

@Repository("spusicc.mantenimientoSICConcursoDuplaCyzoneDAO")
public class MantenimientoSICConcursoDuplaCyzoneDAOiBatis extends BaseDAOiBatis
		implements MantenimientoSICConcursoDuplaCyzoneDAO {

	public List getConcursosMantenenimientoDuplaCyzoneList(Map criteria) {
		List listado = getSqlMapClientTemplate().queryForList(
				"spusicc.ProcesosPERSQL.getConcursosMantenenimientoDuplaCyzoneList",
				criteria);
		return listado;
	}

	public InterfazSICConcursoDuplaCyzone getConcursoDuplaCyzone(String codigoConcurso) {
		InterfazSICConcursoDuplaCyzone concursoDuplaCyzone = (InterfazSICConcursoDuplaCyzone)getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getConcursoDuplaCyzone", codigoConcurso);
		return concursoDuplaCyzone;
	}

	public void updateConcursoDuplaCyzone(InterfazSICConcursoDuplaCyzone concursoDuplaCyzone) {
		getSqlMapClientTemplate().update(
				"spusicc.ProcesosPERSQL.updateConcursoDuplaCyzone",
				concursoDuplaCyzone);
	}

	public void insertConcursoDuplaCyzone(InterfazSICConcursoDuplaCyzone concursoDuplaCyzone) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosPERSQL.insertConcursoDuplaCyzone",
				concursoDuplaCyzone);
		
	}

	public String getNextConcursoDuplaCyzone() {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.ProcesosPERSQL.getNextConcursoDuplaCyzone",
				null);
		
	}
	
	 
	
}
