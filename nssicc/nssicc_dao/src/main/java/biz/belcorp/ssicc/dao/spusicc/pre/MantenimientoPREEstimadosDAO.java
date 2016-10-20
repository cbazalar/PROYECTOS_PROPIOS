package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface MantenimientoPREEstimadosDAO extends DAO {

	
	List  getManPREEstimadosList(Map param);
	void  deleteManPREEstimados(Map param);
	List getManPREEstimadosCatalogoList();
	
}
