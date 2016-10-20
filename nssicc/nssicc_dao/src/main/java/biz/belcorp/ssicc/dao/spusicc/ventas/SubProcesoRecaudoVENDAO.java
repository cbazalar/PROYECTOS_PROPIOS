package biz.belcorp.ssicc.dao.spusicc.ventas;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Proceso;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.SubProcesoRecaudo;


/**
 * Interfaz de Sub Proceso de Recaudo
 * @author peextjnunez
 *
 */
public interface SubProcesoRecaudoVENDAO extends DAO{
	
	
	public List getProceso(Proceso bproceso);
    public List getSubProcesoRec(SubProcesoRecaudo subproc);
    public void insertSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario);
	public void deleteSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario);
    
    
	

}
