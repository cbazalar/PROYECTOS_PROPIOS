/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.consultafactura.retail.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.consultafactura.retail.ProcesoRMSConsultaFacturaRetailDAO;

/**
 * @author Richar Cruzado
 * @date   29/12/2015
 */
@Repository("spusicc.procesoRMSConsultaFacturaRetailDAO")
public class ProcesoRMSConsultaFacturaRetailDAOiBatis extends BaseDAOiBatis
	implements ProcesoRMSConsultaFacturaRetailDAO {

		
	@Override
	public List getConsultaFacturaRetail(Map map) {

		return this.getSqlMapClientTemplate().queryForList("spusicc.comision.retail.procesoRETSQL.getConsultaFacturaRetail", map);
		
	}	

}
