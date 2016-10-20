package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scdf.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;

@Repository("scdf.interfazSiCCDAO")
public class InterfazSiCCDAOiBatis extends BaseDAOiBatis implements
        InterfazSiCCDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.InterfazSiCCDAO#executeCargaSiCC(java.lang.String, java.lang.String, java.lang.String)
     */
    public int executeCargaSiCC(String codigoPais, String codigoPeriodo, String usuario) {
        Map map = new HashMap();
        map.put("codigoPais", codigoPais);
        map.put("codigoPeriodo", codigoPeriodo);
        map.put("usuario", usuario);
        getSqlMapClientTemplate().update(
                "scdf.InterfazSiCCSQL.executeCargaSiCC", map);
        return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.InterfazSiCCDAO#executeCargaNumeroBoletasDespacho(java.lang.String)
     */
    public void executeCargaNumeroBoletasDespacho(String codigoPais) {
        Map map = new HashMap();
        map.put("codigoPais", codigoPais);
        getSqlMapClientTemplate().update(
                "scdf.InterfazSiCCSQL.executeCargaNumeroBoletasDespacho", map);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.dao.InterfazSiCCDAO#executeCargaProductos(java.lang.String, java.lang.String)
     */
    public void executeCargaProductos(String codigoPais, String usuario) {
        Map map = new HashMap();
        map.put("codigoPais", codigoPais);
        map.put("usuario", usuario);
        getSqlMapClientTemplate().update(
                "scdf.InterfazSiCCSQL.executeCargaProductos", map);
    }


	@Override
	public String getPeriodoDefaultByPaisCanal(Map params) {
		String defecto = "";
		Base base = new Base();
		List aux = getSqlMapClientTemplate().queryForList(
				"sisicc.GenericoSQL.getPeriodoDefaultByPaisCanal", params);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}

}
