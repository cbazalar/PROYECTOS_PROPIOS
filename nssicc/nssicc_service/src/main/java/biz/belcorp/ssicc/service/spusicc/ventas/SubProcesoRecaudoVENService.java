package biz.belcorp.ssicc.service.spusicc.ventas;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Proceso;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.SubProcesoRecaudo;
import biz.belcorp.ssicc.service.framework.Service;
/**
 * Interfaz de Servicio de Sub Proceso de Recaudo
 * @author peextjnunez
 *
 */
public interface SubProcesoRecaudoVENService extends Service{
	
	public List getProceso(Proceso brpoceso);
    public List getSubProcesoRec(SubProcesoRecaudo subproc);
    public void insertSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario);
	public void deleteSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario);

}
