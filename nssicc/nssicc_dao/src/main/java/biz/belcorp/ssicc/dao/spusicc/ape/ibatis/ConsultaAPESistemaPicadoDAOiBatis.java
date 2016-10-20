package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.ConsultaAPESistemaPicadoDAO;

/**
 * Implementacion del DAO que ejecutara el metodo de listado de Sistemas de Picado
 * <p>
 * <a href="ConsultaAPESistemaPicadoDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:nlopez@csigcomt.com">Nicolas Lopez</a>
 */
@Repository("spusicc.consultaAPESistemaPicadoDAO")
public class ConsultaAPESistemaPicadoDAOiBatis extends BaseDAOiBatis implements ConsultaAPESistemaPicadoDAO {


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.ConsultaAPESistemaPicadoDAO#getSistemaPicadoLista(java.util.Map)
	 */
	public List getSistemaPicadoLista(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSistemaPicadoLista", criteria);
	}

	
}
