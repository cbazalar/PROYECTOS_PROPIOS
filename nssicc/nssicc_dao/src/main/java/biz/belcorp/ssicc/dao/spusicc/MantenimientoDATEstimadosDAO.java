package biz.belcorp.ssicc.dao.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;

/**
 * <p>
 * <a href="MantenimientoDATEstimadosDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 * 
 */

public interface MantenimientoDATEstimadosDAO extends DAO {
   
    public String getPeriodoInicio(String codigoRangoPeriodo);
    
    public String getPeriodoFin(String codigoRangoPeriodo);
    
    public List getEstimadosByCriteria(Map criteria);
    
    public Estimado getEstimadosById(Estimado estimado);
  
    public void insertEstimado(Estimado estimado, Usuario usuario);

	public void updateEstimado(Estimado estimado, Usuario usuario);

	

    
}
