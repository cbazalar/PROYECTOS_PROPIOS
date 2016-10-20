package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUComercialDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUInicioProcesosDiariosDAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.gen.GenericoEDUFacadeService;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoGENActualizaPedidosCursosFacturadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENActualizaPedidosCursosFacturadosServiceImpl extends BaseInterfazProcesoAbstractService{

	@Resource(name = "edu.genericoEDUFacadeService")
	GenericoEDUFacadeService genericoEDUFacadeService;

	@Resource(name = "edu.procesoEDUComercialDAO")
	ProcesoEDUComercialDAO procesoEDUComercialDAO;
	
	@Resource(name = "edu.mantenimientoEDUGenericoDAO")
	MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;

	@Resource(name = "edu.procesoEDUInicioProcesosDiariosDAO")
	ProcesoEDUInicioProcesosDiariosDAO procesoEDUInicioProcesosDiariosDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) 
	throws InterfazException,Exception {
		
		String codigoPais = (String)params.get("codigoPais");
		
		boolean hayConsFact=this.executeProcesoEDURecepcionarPedidosConsultorasFacturados(codigoPais, params);
		boolean hayCursFact=this.executeProcesoEDURecepcionarCursosFacturados(codigoPais, params);
		if(hayConsFact || hayCursFact ){
			//El envio se hace ahora bien se haga el registro del cronograma, Mantenimiento Cronograa Dictado			
			executaProcesoEDURecepcionarCursosNoFacturadosMixto(codigoPais,params);//los cursos mixtos 
			//que se encuentarn en PO los pasa a PC en su misma campaa
		    //se ejecuta el proceso de pedidos rezagados , movido de la cali a cieere facturacion
			executeProcesoConsultoraRezagadas(params);
			return;
		}
		else{
			 Usuario usuario = (Usuario) params.get("usuario");
			 String mensajeError =  this.messageSource.getMessage("procesoEDUCierreFacturacionDiarioForm.error.noExistenPedidos",null, getLocale(usuario));
			throw new Exception(mensajeError);
			
		}
	}
	
	/**
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private boolean executeProcesoEDURecepcionarPedidosConsultorasFacturados(String codigoPais, Map params) throws Exception {

		List list = genericoEDUFacadeService.getPedidosComercialFacturados(codigoPais, params);
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidos(params);
			procesoEDUComercialDAO.insertTemporalPedidosFacturados(list);
			procesoEDUComercialDAO.updateHistoricoPedidosFacturados(codigoPais, params);
			return true;
		}
		return false;
	}
	
	/**
	 * @param codigoPais
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private boolean executeProcesoEDURecepcionarCursosFacturados(String codigoPais, Map params) throws Exception {
		
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		
		List list = genericoEDUFacadeService.getPedidosCursosFacturados(codigoPais, params);
		
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidosCursos();
			procesoEDUComercialDAO.insertTemporalPedidosCursosFacturados(list, codigoPais, codigoEmpresa);
			procesoEDUComercialDAO.updatePedidosCursosFacturados(codigoPais, params);
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param codigoPais
	 * @param params
	 */
	private void executaProcesoEDURecepcionarCursosNoFacturadosMixto(String codigoPais, Map params) {
		log.debug("executaProcesoEDURecepcionarCursosNoFacturadosMixto");
		
		String codigoEmpresa = (String)params.get("codigoEmpresa");
		
		List list =procesoEDUComercialDAO.getPedidosCursosNoFacturadosMixto(codigoPais, params);
		
		if (list != null && list.size() > 0){
			procesoEDUComercialDAO.deleteTemporalPedidosCursos();
			procesoEDUComercialDAO.insertTemporalPedidosCursosFacturados(list, codigoPais, codigoEmpresa);
			procesoEDUComercialDAO.updatePedidosCursosNoFacturados(codigoPais, params);
		}
	}
	
	/**
	 * @param params
	 */
	private void executeProcesoConsultoraRezagadas(Map params) {
		
		try {
			procesoEDUInicioProcesosDiariosDAO.executeProcesoConsultoraRezagadas(params);
		} catch (Exception e) {
		   log.debug("error en executeProcesoConsultoraRezagadas " + e.getMessage());
		}		
	}
}