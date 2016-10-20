package biz.belcorp.ssicc.service.spusicc.cronograma.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRACalendarioDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACalendarioService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRACalendarioServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Service("spusicc.mantenimientoCRACalendarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCRACalendarioServiceImpl extends BaseService implements MantenimientoCRACalendarioService{

	@Resource(name="spusicc.mantenimientoCRACalendarioDAO")
	private MantenimientoCRACalendarioDAO mantenimientoCRACalendarioDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACalendarioService#insertCalendario(java.util.Map)
	 */
	public int insertFeriados(Map criteria){
						
		if ((Integer)mantenimientoCRACalendarioDAO.existeAnhio(criteria) == 0){
			mantenimientoCRACalendarioDAO.insertAnhio(criteria);
		}
		
		int result = (Integer)mantenimientoCRACalendarioDAO.existeFecha(criteria);
		if  (result == 0){
			mantenimientoCRACalendarioDAO.insertFeriados(criteria);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACalendarioService#deleteFeriado(java.util.Map)
	 */
	public void deleteFeriado(Map criteria){
		mantenimientoCRACalendarioDAO.deleteFeriado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACalendarioService#insertCalendarioDiaNoLaborable(java.util.Map)
	 */
	public void insertCalendarioDiaNoLaborable(Map criteria){
		
		if ((Integer)mantenimientoCRACalendarioDAO.existeAnhio(criteria) == 0){
			mantenimientoCRACalendarioDAO.insertAnhio(criteria);
		}
		mantenimientoCRACalendarioDAO.insertDiaNoLaborable(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACalendarioService#getCalendarioFeriados(java.util.Map)
	 */
	public List getCalendarioFeriados(Map criteria){
		return mantenimientoCRACalendarioDAO.getCalendarioFeriados(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACalendarioService#deleteDiaNoLaborable(java.util.Map)
	 */
	public void deleteDiaNoLaborable(Map criteria){
		mantenimientoCRACalendarioDAO.deleteDiaNoLaborable(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACalendarioService#copiarCalendarioPorActividad(java.util.Map)
	 */
	public void copiarCalendarioPorActividad(Map criteria){

		List actividadRegenerarList = (List)criteria.get("actividadRegenerarList");
		String oidActiReferencia = (String)criteria.get("oidActiReferencia");
		
		if (actividadRegenerarList != null){
			
		  if (actividadRegenerarList.size() > 0)	
			for (int i=0; i<actividadRegenerarList.size();i++){
				
		      if ( !actividadRegenerarList.get(i).equals(oidActiReferencia))	{	
				criteria.put("oidActiRegenerar", actividadRegenerarList.get(i));
				mantenimientoCRACalendarioDAO.copiarCalendarioPorActividad(criteria);
		      }
			}
		}
		
	}
}
