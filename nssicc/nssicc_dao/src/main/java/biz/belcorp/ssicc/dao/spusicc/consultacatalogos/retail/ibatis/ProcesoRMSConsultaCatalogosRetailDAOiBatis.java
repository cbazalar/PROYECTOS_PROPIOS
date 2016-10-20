/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.consultacatalogos.retail.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.consultacatalogos.retail.ProcesoRMSConsultaCatalogosRetailDAO;

/**
 * @author Richar Cruzado Vallejos
 * @date   21/01/2016
 */
@Repository("spusicc.procesoRMSConsultaCatalogosRetailDAO")
public class ProcesoRMSConsultaCatalogosRetailDAOiBatis extends BaseDAOiBatis
	implements ProcesoRMSConsultaCatalogosRetailDAO {

		
	@Override
	public List getConsultaCatalogosRetail() {
		
		return this.getSqlMapClientTemplate().queryForList("spusicc.comision.retail.procesoRETSQL.getConsultaCatalogosRetail");
	}	

}
