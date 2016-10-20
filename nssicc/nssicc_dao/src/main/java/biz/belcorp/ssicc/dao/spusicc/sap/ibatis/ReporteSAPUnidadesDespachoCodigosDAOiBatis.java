package biz.belcorp.ssicc.dao.spusicc.sap.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sap.ReporteSAPUnidadesDespachoCodigosDAO;

/**
* @author <a href="mailto:croman@belcorp.biz">Christian Roman</a>
**/
@Repository("spusicc.reporteSAPUnidadesDespachoCodigosDAO")
public class ReporteSAPUnidadesDespachoCodigosDAOiBatis extends BaseDAOiBatis implements ReporteSAPUnidadesDespachoCodigosDAO {

	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.ProcesoSAPUnidadesDespachoCodigosDAO#executeGenerarReporteUnidadesDespacho(java.util.Map)
     */
    public void executeRegistrosUnidadesDespachoCodigosSAP(Map params){
    	getSqlMapClientTemplate().update("spusicc.sap.ProcesoSAPSQL.executeGenerarReporteUnidadesDespacho", params);
    }
}