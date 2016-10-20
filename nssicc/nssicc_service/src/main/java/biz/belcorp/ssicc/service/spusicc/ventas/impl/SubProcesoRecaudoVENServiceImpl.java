package biz.belcorp.ssicc.service.spusicc.ventas.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.SubProcesoRecaudoVENDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Proceso;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.SubProcesoRecaudo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ventas.SubProcesoRecaudoVENService;

/**
 * Clase que implementa la interfaz de servicio SubProcesoRecaudoVENService
 * @author peextjnunez
 *
 */
@Service("spusicc.subProcesoRecaudoVENService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class SubProcesoRecaudoVENServiceImpl extends BaseService implements SubProcesoRecaudoVENService{
	
	@Resource(name="spusicc.SubProcesoRecaudoVENDAO")
	private SubProcesoRecaudoVENDAO subProcesoRecaudoVENDAO;/**Atributo DAO*/
	
	/**
	 * Metodo que obtiene un listado de procesos
	 * @return List
	 */
	public List getProceso(Proceso bprocso){
		return subProcesoRecaudoVENDAO.getProceso(bprocso);
	}
	/**
	 * Metodo que obtiene un listado de subproceso de recaudo 
	 * de acuerdo a un criterio de busqueda ingresado
	 * @param subproc - Clase que contiene  el criterio de busqeda
	 * @return List
	 */
    public List getSubProcesoRec(SubProcesoRecaudo subproc){
    	return subProcesoRecaudoVENDAO.getSubProcesoRec(subproc);
    }

    /**
	 * Metodo de registro de un Sub Proceso de recaudo
	 */
	 public void insertSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario){
		 subProcesoRecaudoVENDAO.insertSubProcesoRec(subproc,usuario);
	 }
	 /**
	  * Metodo de eliminacion de un Sub Proceso de recaudo
	  */
	 public void deleteSubProcesoRec(SubProcesoRecaudo subproc, Usuario usuario){
		 subProcesoRecaudoVENDAO.deleteSubProcesoRec(subproc,usuario);
		 
	 }

}
