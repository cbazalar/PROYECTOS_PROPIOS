package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ConsultoraDAO;
import biz.belcorp.ssicc.dao.scdf.model.Consultora;

@Repository("scdf.consultoraDAO")
public class ConsultoraDAOiBatis extends BaseDAOiBatis implements ConsultoraDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ConsultoraDAO#getConsultoras(biz.belcorp.ssicc.scdf.model.Consultora)
     */
    public List getConsultoras(Consultora consultora) {
        List resultados = getSqlMapClientTemplate().queryForList(
                "scdf.ConsultoraSQL.getConsultoras", consultora);
        return resultados;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ConsultoraDAO#getConsultorasMapByPais(java.lang.String)
     */
    public List getConsultorasMapByPais(String codigoPais) {
        List consultoras = getSqlMapClientTemplate().queryForList(
                "scdf.ConsultoraSQL.getConsultorasMapByPais", codigoPais);
        return consultoras;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ConsultoraDAO#insertConsultora(biz.belcorp.ssicc.scdf.model.Consultora,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertConsultora(Consultora obj, Usuario cliente) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ConsultoraDAO#updateConsultora(biz.belcorp.ssicc.scdf.model.Consultora,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateConsultora(Consultora obj, Usuario cliente) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ConsultoraDAO#updateConsultoraStatusByPais(java.lang.String)
     */
    public void updateConsultoraStatusByPais(String codigoPais) {
        getSqlMapClientTemplate().update(
                "scdf.ConsultoraSQL.updateConsultoraStatusByPais", codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ConsultoraDAO#removeConsultora(biz.belcorp.ssicc.scdf.model.Consultora)
     */
    public void removeConsultora(Consultora consultora) {

    }
}
