package biz.belcorp.ssicc.dao.scdf.ibatis;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.MovimientoClienteDAO;
import biz.belcorp.ssicc.dao.scdf.model.MovimientoCliente;

@Repository("scdf.movimientoClienteDAO")
public class MovimientoClienteDAOiBatis extends BaseDAOiBatis implements MovimientoClienteDAO {

    public void insertMovimientoCliente(MovimientoCliente mov, Usuario usuario) {
        getSqlMapClientTemplate().insert("scdf.MovimientoClienteSQL.insertMovimientoCliente",
        		mov);
    }

}