package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMBonosDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.Bonos;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;


/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Repository("spusicc.mantenimientoCOMBonosDAO")
public class MantenimientoCOMBonosDAOiBatis extends BaseDAOiBatis 
    implements MantenimientoCOMBonosDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#getBonosEjecutivas(java.util.Map)
	 */
	public List getBonosEjecutivas(Map params) {
		return this.getSqlMapClientTemplate().
				queryForList("spusicc.comision.mantenimientoCOMSQL.getBonosEjecutivas", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#getListDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public List getListDetalleBonos(DetalleBonos detalleBonos) {
		return this.getSqlMapClientTemplate().
			queryForList("spusicc.comision.mantenimientoCOMSQL.getListDetalleBonos", detalleBonos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#getSiguienteCodigoConcepto(biz.belcorp.ssicc.spusicc.comision.dao.model.Bonos)
	 */
	public String getSiguienteCodigoConcepto(Bonos bono) {
		String retorno="";
		Integer codigo = (Integer)getSqlMapClientTemplate().queryForObject(
				"spusicc.comision.mantenimientoCOMSQL.getSiguienteCodigoConcepto",bono);
		codigo = new Integer(codigo.intValue() + 1);
		retorno = StringUtils.leftPad(codigo.toString().trim(),5,"0");
		return retorno;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#insertBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.Bonos)
	 */
	public void insertBonos(Bonos bono) {
		this.getSqlMapClientTemplate().insert("spusicc.comision.mantenimientoCOMSQL.insertBonos",bono);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#insertDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public void insertDetalleBonos(DetalleBonos detalleBonos) {
		this.getSqlMapClientTemplate().insert("spusicc.comision.mantenimientoCOMSQL.insertDetalleBonos",detalleBonos);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#updateBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.Bonos)
	 */
	public void updateBonos(Bonos bono) {
		this.getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateBonos",bono);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMBonosDAO#updateDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public void updateDetalleBonos(DetalleBonos detalleBonos) {
		this.getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateDetalleBonos",detalleBonos);		
	}
	

}
