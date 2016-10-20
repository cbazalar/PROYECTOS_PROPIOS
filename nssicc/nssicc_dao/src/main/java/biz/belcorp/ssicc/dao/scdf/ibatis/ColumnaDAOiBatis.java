package biz.belcorp.ssicc.dao.scdf.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.scdf.ColumnaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Columna;

@Repository("scdf.columnaDAO")
public class ColumnaDAOiBatis extends BaseDAOiBatis implements ColumnaDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.dao.ColumnaDAO#getColumnas(biz.belcorp.ssicc.scdf.model.Columna)
     */
    public List getColumnas(Columna columna) {
        List columnas = getSqlMapClientTemplate().queryForList(
                "scdf.ColumnaSQL.getColumnas", columna);
        return columnas;
    }

}
