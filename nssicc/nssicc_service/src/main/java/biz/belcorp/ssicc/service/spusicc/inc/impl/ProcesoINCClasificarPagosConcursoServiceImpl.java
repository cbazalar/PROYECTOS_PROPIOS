package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCClasificarPagosConcursoDAO;

/**
 * Service que va a generar Solicitudes de Servicio de bolsa faltantes
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCClasificarPagosConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCClasificarPagosConcursoServiceImpl extends
	BaseInterfazProcesoAbstractService {
	                                                  
	@Resource(name="spusicc.procesoINCClasificarPagosConcursoDAO")
	private ProcesoINCClasificarPagosConcursoDAO procesoINCClasificarPagosConcursoDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCClasificarPagosConcursoDAO.executeClasificarPagosConcurso(params);
	}
	
	/**
	 * @param procesoINCClasificarPagosConcursoServiceDAO the procesoINCClasificarPagosConcursoServiceDAO to set
	 */
	public void setProcesoINCClasificarPagosConcursoDAO(
			ProcesoINCClasificarPagosConcursoDAO procesoINCClasificarPagosConcursoDAO) {
		this.procesoINCClasificarPagosConcursoDAO = procesoINCClasificarPagosConcursoDAO;
		
	}
	
}
