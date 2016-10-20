package biz.belcorp.ssicc.service.spusicc.cronograma.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAPeriodoDAO;
import biz.belcorp.ssicc.dao.spusicc.cronograma.model.PeriodoCronograma;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAPeriodoService;

/**
 * Service que controla la Consulta de Ejecutivo
 *  
 * <p>
 * <a href="MantenimientoCRAPeriodoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramriez@belcorp.biz">Rosalvina Ramirez</a>
 * 
 */
@Service("spusicc.cronograma.mantenimientoCRAPeriodoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCRAPeriodoServiceImpl extends BaseService implements MantenimientoCRAPeriodoService{

	@Resource(name="spusicc.cronograma.mantenimientoCRAPeriodoDAO")
	private MantenimientoCRAPeriodoDAO mantenimientoCRAPeriodoDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAPeriodoService#getPeriodoCorporativoList(java.lang.String)
	 */
	public List<BaseOID> getPeriodoCorporativoList(String anhio){
		return mantenimientoCRAPeriodoDAO.getPeriodoCorporativoList(anhio);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAPeriodoService#getPeriodoCronogramaList(java.lang.String)
	 */
	public List getPeriodoCronogramaList(String anhio){
		List periodoCronogramaList = mantenimientoCRAPeriodoDAO.getPeriodoCronogramaList(anhio);
		if (periodoCronogramaList.isEmpty())
			return new ArrayList<PeriodoCronograma>();
		else
			return periodoCronogramaList;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAPeriodoService#insertPeriodoCronograma(java.util.List)
	 */
	public void insertPeriodoCronograma(Map criteria) throws Exception  { 

		try { 
			
			String[] listaOidPeriodo = (String[])criteria.get("listaOidPeriodo");			
			String[] listaCodigoPeriodo = (String[])criteria.get("listaCodigoPeriodo");			
			String[] listaFechaInicio = (String[])criteria.get("listaFechaInicio");
			String[] listaFechaFin = (String[])criteria.get("listaFechaFin");
			String[] listaIndicadorPeriodoCorto = (String[])criteria.get("listaIndicadorPeriodoCorto");
			String[] listaIndicadorPeriodoCruce = (String[])criteria.get("listaIndicadorPeriodoCruce");
			
			if (listaCodigoPeriodo.length > 0){				
				for (int i = 1; i < listaCodigoPeriodo.length; i++) {
					
					PeriodoCronograma periodoCronograma = new PeriodoCronograma();					
					
					periodoCronograma.setCodigoPais((String)criteria.get("pais"));
					periodoCronograma.setCodigoMarca((String)criteria.get("marca"));
					periodoCronograma.setCodigoCanal((String)criteria.get("canal"));
					periodoCronograma.setCodigoAcceso((String)criteria.get("acceso"));					
					periodoCronograma.setUsuario((String)criteria.get("usuario"));
					periodoCronograma.setCodigoPeriodo((String)listaCodigoPeriodo[i]);
					periodoCronograma.setFechaInicio((String)listaFechaInicio[i]);
					periodoCronograma.setFechaFin((String)listaFechaFin[i]);
					periodoCronograma.setDuracion((String)criteria.get("usuario"));
					periodoCronograma.setNombrePeriodo(Constants.CRA_CAMPANHA_DEFAULT+(String)listaCodigoPeriodo[i]);
					
					if (listaOidPeriodo.length > 0 )
						periodoCronograma.setOid((String)listaOidPeriodo[i]);
					
					if (((String)listaIndicadorPeriodoCorto[i]).equals("SI"))
						periodoCronograma.setIndicadorPeriodoCorto("1");
					else
						periodoCronograma.setIndicadorPeriodoCorto("0");
					
					if(((String)listaIndicadorPeriodoCruce[i]).equals("SI"))
						periodoCronograma.setIndicadorPeriodoCruce("1");
					else
						periodoCronograma.setIndicadorPeriodoCruce("0");
									
					if ( (Boolean)criteria.get("newRecord") )
						mantenimientoCRAPeriodoDAO.insertPeriodoCronograma(periodoCronograma);
					else
						mantenimientoCRAPeriodoDAO.modificaPeriodoCronograma(periodoCronograma);
				}
			}		
		} catch (Exception e) {
			//throw new Exception(e.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAPeriodoService#insertPeriodoCorporativo(java.lang.String)
	 */
	public List insertPeriodoCorporativo(Map criteria){
		mantenimientoCRAPeriodoDAO.insertPeriodoCorporativo(criteria);
		return mantenimientoCRAPeriodoDAO.getPeriodoNuevoCronogramaList();
	}
		
}
