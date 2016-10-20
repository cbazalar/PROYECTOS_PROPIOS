package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * 
 * @author Sigcomt
 *
 */
public interface MantenimientoPREMatrizAlternativosService extends Service{

	List getAlternativos(Map params);
	
	void updateAlternativo(MatrizAlternativo ma, Usuario usuario);
	
	void insertAlternativo(MatrizAlternativo matrizAlternativo, Usuario usuario);
}
