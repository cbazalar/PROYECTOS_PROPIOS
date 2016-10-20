package biz.belcorp.ssicc.service.scsicc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.GraficoDAO;
import biz.belcorp.ssicc.service.scsicc.framework.GraphAbstractService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.GraphParams;
import biz.belcorp.ssicc.service.scsicc.framework.impl.GraphAbstractServiceImpl;

/**
 * Service de Grafico de Resumen Consultoras x Estado
 * 
 * @author <a href="">Carlos Bazalar La Rosa</a>
 * 
 */
@Service("scsicc.graficoEDUResumenConsultorasxEstadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GraficoEDUResumenConsultorasxEstadoServiceImpl extends GraphAbstractServiceImpl implements GraphAbstractService {

	@Resource(name="scsicc.graficoDAO")
	GraficoDAO  graficoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.framework.impl.GraphAbstractServiceImpl#getDevuelveListaGenerada(biz.belcorp.ssicc.scsicc.service.framework.beans.GraphParams, java.lang.String)
	 */
	protected List getDevuelveListaGenerada(GraphParams graphParam, String tipoGrafico) throws Exception {
		Map params = graphParam.getParameterMap();
		List lista = new ArrayList();
		if (Constants.TIPO_GRAFICO_PIE.equals(tipoGrafico) || Constants.TIPO_GRAFICO_PIE3D.equals(tipoGrafico))
			lista = graficoDAO.getResumenConsultorasxEstadoPie(params);
		else 
			if (Constants.TIPO_GRAFICO_BAR3D_H.equals(tipoGrafico) || Constants.TIPO_GRAFICO_BAR3D_V.equals(tipoGrafico))
				lista = graficoDAO.getResumenConsultorasxEstadoBar(params);
		return lista;
	}

	
		
}