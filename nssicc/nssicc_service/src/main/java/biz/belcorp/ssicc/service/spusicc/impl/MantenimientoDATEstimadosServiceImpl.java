/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoDATEstimadosDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoDATEstimadosService;

/**
 * 
 * <p>
 * <a href="MantenimientoDATEstimadosServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 * 
 */

@Service("spusicc.mantenimientoDATEstimadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoDATEstimadosServiceImpl extends BaseService implements MantenimientoDATEstimadosService {

	@Resource(name="spusicc.mantenimientoDATEstimadosDAO")
	private MantenimientoDATEstimadosDAO mantenimientoDATEstimadosDAO;


	public String getPeriodoInicio(String codigoRangoPeriodo) {
		return this.mantenimientoDATEstimadosDAO.getPeriodoInicio(codigoRangoPeriodo);
		
	}

	public String getPeriodoFin(String codigoRangoPeriodo) {
		return this.mantenimientoDATEstimadosDAO.getPeriodoFin(codigoRangoPeriodo);
	}
		
	public List getEstimadosByCriteria(Map criteria) {
		return this.mantenimientoDATEstimadosDAO.getEstimadosByCriteria(criteria);
	}
	
	public Estimado getEstimadosById(Estimado estimado) {
		return this.mantenimientoDATEstimadosDAO.getEstimadosById(estimado);
	}
	
	public void insertEstimado(Estimado estimado, Usuario usuario) {
		this.mantenimientoDATEstimadosDAO.insertEstimado(estimado,usuario);
		
	}

	public void updateEstimado(Estimado estimado, Usuario usuario) {
		this.mantenimientoDATEstimadosDAO.updateEstimado(estimado,usuario);
		
	}

}
