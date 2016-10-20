package biz.belcorp.ssicc.dao.spusicc.descuento.retail.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.descuento.retail.ProcesoRMSEscalaDescuentosRetailDAO;


/**
 * @author Richar Cruzado
 *
 */
@Repository("spusicc.procesoRMSEscalaDescuentosRetailDAO")
public class ProcesoRMSEscalaDescuentosRetailDAOiBatis extends BaseDAOiBatis 
    implements ProcesoRMSEscalaDescuentosRetailDAO {

	

	@Override
	public List getEscalaDescuentoRetail() {
				
		return this.getSqlMapClientTemplate().queryForList("spusicc.comision.retail.procesoRETSQL.getEscalaDescuentoRetail");
	}
	

}
