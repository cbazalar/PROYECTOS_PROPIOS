package biz.belcorp.ssicc.service.scsicc.framework.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.framework.GraphAbstractService;
import biz.belcorp.ssicc.service.scsicc.framework.GraphExecutionService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphParams;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphResult;


/**
 * Implementacion del Service de ejecucin de los Graficos SSiCC
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class GraphAbstractServiceImpl extends BaseService implements GraphAbstractService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected GraphExecutionService graphExecutionServiceImpl;
	
	private Map serviceImplementacion;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.framework.GraphAbstractService#executeGrafico(biz.belcorp.ssicc.scsicc.service.framework.beans.GraphParams)
	 */
	public final GraphResult executeGrafico(GraphParams graphParam) throws Exception {
		log.debug("Entering 'executeGrafico' method");
		
		GraphResult graphResult = new GraphResult();
		this.beforeExecuteGrafico(graphParam);
		
		/* obteniendo el service de generacion de grafico */
		this.graphExecutionServiceImpl = this.getServiceImplementacion(graphParam.getTipoGrafico());
		
		List listaGenerada = this.getDevuelveListaGenerada(graphParam, graphParam.getTipoGrafico());
		graphParam.setListDataGenerada(listaGenerada);
		graphResult = graphExecutionServiceImpl.generarGrafico(graphParam);
		
		/* ejecutando proceso luego de la generacion del grafico */
		this.afterExecuteGrafico(graphParam);
		return graphResult;
	}
	
	
	
	/**
	 * Metodo que debe implementarse, la cual devuelve la lista con la cual debe generarse el grafico
	 * respectivo
	 * @param graphParam
	 * @param tipoGrafico
	 * @return
	 * @throws Exception
	 */
	protected abstract List getDevuelveListaGenerada(GraphParams graphParam, String tipoGrafico) throws Exception;
	
	
	/**
	 * Metodo Hook que se ejecuta antes de la Ejecucion del Grafico.
	 * Dicho metodo puede ser sobreescrito
	 * @param graphParam
	 */
	protected void beforeExecuteGrafico(GraphParams graphParam) throws Exception {
		
	}
	
	/**
	 * Metodo Hook que se ejecuta despues de la Ejecucion del Grafico.
	 * Dicho metodo puede ser sobreescrito
	 * @param graphParam
	 */
	protected void afterExecuteGrafico(GraphParams graphParam) throws Exception {
		
	}
	
	
	/**
	 * @return Returns the serviceImplementacion.
	 */
	public GraphExecutionService getServiceImplementacion(String key) throws Exception {
		log.info(this.serviceImplementacion);
		GraphExecutionService beanService = (GraphExecutionService) serviceImplementacion.get(key);
		return beanService;
		
	}
	

	/**
	 * @param serviceImplementacion The serviceImplementacion to set.
	 */
	public void setServiceImplementacion(Map serviceImplementacion) {
		this.serviceImplementacion = serviceImplementacion;
	}



	/**
	 * @return Returns the graphExecutionServiceImpl.
	 */
	public GraphExecutionService getGraphExecutionServiceImpl() {
		return graphExecutionServiceImpl;
	}

	/**
	 * @param graphExecutionServiceImpl The graphExecutionServiceImpl to set.
	 */
	public void setGraphExecutionServiceImpl(
			GraphExecutionService graphExecutionServiceImpl) {
		this.graphExecutionServiceImpl = graphExecutionServiceImpl;
	}
	
	
	
}
