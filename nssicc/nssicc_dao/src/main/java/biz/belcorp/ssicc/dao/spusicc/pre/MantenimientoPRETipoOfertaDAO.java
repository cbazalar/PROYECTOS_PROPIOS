package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface MantenimientoPRETipoOfertaDAO extends DAO {

	
	List  getManPRETipoOfertaList(Map param);
	
	void  insertManPRETipoOferta(Map param);
	
	void  insertManPRETipoOfertaDetalle(Map param);
	
	void  updateManPRETipoOferta(Map param);
	
	void  updateManPRETipoDetalle(Map param);
	
	Map   getManPRETipoOferta(Map param);
	
	void  deleteManPRETipoOferta(Map param);
	
	int validaManPRETipoOferta(String codigo);

	
}
