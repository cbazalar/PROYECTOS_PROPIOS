package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPRETipoOfertaService extends Service {
	    List getManPRETipoOfertaList(Map param);
		void  insertManPreTiPofertaTotal(Map param);
		void  updateManPreTiPofertaTotal(Map param);
		Map  getManPRETipoOferta(Map param);
		void  deleteManPRETipoOferta(Map param);
		int validaManPRETipoOferta(String codigo);
}
