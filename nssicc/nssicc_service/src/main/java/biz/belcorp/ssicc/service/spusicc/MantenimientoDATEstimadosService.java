/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * 
 * <p>
 * <a href="MantenimientoDATEstimadosService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose A. Cairampoma</a>
 * 
 */

public interface MantenimientoDATEstimadosService extends Service {
    
    
    
    public String  getPeriodoInicio(String codigoRangoPeriodo);
    
    public String  getPeriodoFin(String codigoRangoPeriodo);
    
    public List getEstimadosByCriteria(Map criteria);
    
    public Estimado getEstimadosById(Estimado estimado);
    
    public void insertEstimado(Estimado estimado, Usuario usuario);

    public void updateEstimado(Estimado estimado, Usuario usuario);
    
}
