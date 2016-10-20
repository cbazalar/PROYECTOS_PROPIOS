package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.ProductoMatriz;

/**
 * 
 * @author Sigcomt
 *
 */
public interface MantenimientoPREMatrizAlternativosDAO extends DAO {

	List getAlternativos(Map params);
	void updateAlternativo(MatrizAlternativo ma, Usuario usuario);
	void insertAlternativoAuditoria(MatrizAlternativoAuditoria audi, Usuario usuario);
	ProductoMatriz getProductoPREMatrizAlternativo(String codigoPeriodo, String cuv);
	List getAlternativosList(MatrizAlternativo matrizAlternativo);
	void insertAlternativo(MatrizAlternativo matrizAlternativo, Usuario usuario);
	
}
