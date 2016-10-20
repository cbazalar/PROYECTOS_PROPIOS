package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.MantenimientoPRECambioCodigoVentaDAO;
import biz.belcorp.ssicc.dao.spusicc.model.CodigoVentaMod;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;


@Service("spusicc.mantenimientoPRECambioCodigoVentaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPRECambioCodigoVentaServiceImpl extends BaseService implements MantenimientoPRECambioCodigoVentaService {
	
	@Resource(name = "spusicc.mantenimientoPRECambioCodigoVentaDAO")
	MantenimientoPRECambioCodigoVentaDAO mantenimientoPRECambioCodigoVentaDAO;
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#getTipoOfertaList(java.util.Map)
	 */
	public List getTipoOfertaList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getTipoOfertaList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getTipoVarianteList(java.util.Map)
	 */
	public List getTipoVarianteList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getTipoVarianteList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getFormaPagoList(java.util.Map)
	 */
	public List getFormaPagoList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getFormaPagoList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getTipoClienteList(java.util.Map)
	 */
	public List getTipoClienteList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getTipoClienteList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getSubTipoClienteList(java.util.Map)
	 */
	public List getSubTipoClienteList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getSubTipoClienteList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getTipoClasificacionList(java.util.Map)
	 */
	public List getTipoClasificacionList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getTipoClasificacionList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getClasificacionList(java.util.Map)
	 */
	public List getClasificacionList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getClasificacionList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getEstatusClienteList(java.util.Map)
	 */
	public List getEstatusClienteList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getEstatusClienteList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getOidVariante(java.util.Map)
	 */
	public String getOidVariante(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getOidVariante(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCodFormaPago(java.util.Map)
	 */
	public String getCodFormaPago(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getCodFormaPago(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCodigoVentaList(java.util.Map)
	 */
	public List getCodigoVentaList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getCodigoVentaList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#executeEliminarOferta(java.util.Map)
	 */
	public void executeEliminarOferta(Map map){
		mantenimientoPRECambioCodigoVentaDAO.executeEliminarOferta(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#executeEliminarCUV(java.util.Map)
	 */
	public void executeEliminarCUV(Map map){
		mantenimientoPRECambioCodigoVentaDAO.executeEliminarCUV(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCodigoVentaObject(java.util.Map)
	 */
	public CodigoVentaMod getCodigoVentaObject(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getCodigoVentaObject(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#executeModificarDatosCUV(java.util.Map)
	 */
	public void executeModificarDatosCUV(Map map){
		mantenimientoPRECambioCodigoVentaDAO.executeModificarDatosCUV(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#executeModificarDatosOferta(java.util.Map)
	 */
	public void executeModificarDatosOferta(Map map){
		mantenimientoPRECambioCodigoVentaDAO.executeModificarDatosOferta(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#executeInsertVentaExclusiva(java.util.Map)
	 */
	public void executeInsertVentaExclusiva(Map map){
		mantenimientoPRECambioCodigoVentaDAO.executeInsertVentaExclusiva(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#deleteVentaExclusiva(java.util.Map)
	 */
	public void deleteVentaExclusiva(Map map){
		mantenimientoPRECambioCodigoVentaDAO.deleteVentaExclusiva(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#executeCopiarOferta(java.util.Map)
	 */
	public void executeCopiarOferta(Map map){
		mantenimientoPRECambioCodigoVentaDAO.executeCopiarOferta(map);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#deleteCUVTemporal(java.util.Map)
	 */
	public void deleteCUVTemporal(Map map){
		mantenimientoPRECambioCodigoVentaDAO.deleteCUVTemporal(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#insertaCUVTempora(java.util.Map)
	 */
	public void insertaCUVTemporal(Map map){
		mantenimientoPRECambioCodigoVentaDAO.insertaCUVTemporal(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCodigoVentaModificarList(java.util.Map)
	 */
	public List getCodigoVentaModificarList(Map map){
		return mantenimientoPRECambioCodigoVentaDAO.getCodigoVentaModificarList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getExisteCUV(java.util.Map)
	 */
	public Integer getExisteCUV(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getExisteCUV(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getExisteCUVFacturado(java.util.Map)
	 */
	public Integer getExisteCUVFacturado(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getExisteCUVFacturado(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getNumeroSecuenciaUsuario()
	 */
	public String getNumeroSecuenciaUsuario(){
    	return mantenimientoPRECambioCodigoVentaDAO.getNumeroSecuenciaUsuario();
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getEstrategiaList(java.util.Map)
     */
    public List getEstrategiaList(Map map){
    	return mantenimientoPRECambioCodigoVentaDAO.getEstrategiaList(map);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getPeriodoActivo(java.util.Map)
     */
    public String getPeriodoActivo(Map map){
    	return mantenimientoPRECambioCodigoVentaDAO.getPeriodoActivo(map);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getExisteOfertasFacturados(java.util.Map)
     */
    public String getExisteOfertasFacturados(Map map){
    	String error= new String();
    	
    	mantenimientoPRECambioCodigoVentaDAO.getExisteOfertasFacturados(map);
    	
    	error = (String) map.get("valError");
    	
    	return error;
    }
    

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getListGrupo(java.util.Map)
	 */
	public Map getListGrupo(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getListGrupo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getIndicadorCuadreList(java.util.Map)
	 */
	public List getIndicadorCuadreList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getIndicadorCuadreList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#updateFactorCuadre(java.util.Map)
	 */
	public void updateFactorCuadre(Map params) {
		mantenimientoPRECambioCodigoVentaDAO.updateFactorCuadre(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#updateFactorIndicadorCuadre(java.util.Map)
	 */
	public void updateFactorIndicadorCuadre(Map params) {
		mantenimientoPRECambioCodigoVentaDAO.updateFactorIndicadorCuadre(params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCriteriosList(java.util.Map)
	 */
	public List getCriteriosList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getCriteriosList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getSeccionesList(java.util.Map)
	 */
	public Map getSeccionesList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getSeccionesList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getTipoCuadreList(java.util.Map)
	 */
	public List getTipoCuadreList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getTipoCuadreList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCatalogoList(java.util.Map)
	 */
	public List getCatalogoList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getCatalogoList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#deleteCriterio(java.util.Map)
	 */
	public void deleteCriterio(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.deleteCriterio(map);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#deletePromo(java.util.Map)
	 */
	public void deletePromo(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.deletePromo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#insertaCriterio(java.util.Map)
	 */
	public void insertaCriterio(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.insertaCriterio(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#insertaPromo(java.util.Map)
	 */
	public void insertaPromo(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.insertaPromo(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getComponentesList(java.util.Map)
	 */
	public List getComponentesList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getComponentesList(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getProductoBySAP(java.util.Map)
	 */
	public String getProductoBySAP(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getProductoBySAP(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#insertHistoricoCUV(java.util.Map)
	 */
	public void insertHistoricoCUV(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.insertHistoricoCUV(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#updateFactorCuadreCondicion(java.util.Map)
	 */
	public void updateFactorCuadreCondicion(Map params) {
		mantenimientoPRECambioCodigoVentaDAO.updateFactorCuadreCondicion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPRECambioCodigoVentaService#getCriterioById(java.util.Map)
	 */
	public Map getCriterioById(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getCriterioById(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#getPanelGrupos(java.util.Map)
	 */
	@Override
	public List getPanelGrupos(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getPanelGrupos(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#deleteProductoGrupoOfertaDetalle(java.util.Map)
	 */
	@Override
	public void deleteProductoGrupoOfertaDetalle(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.deleteProductoGrupoOfertaDetalle(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#insertProductoGrupoOfertaDetalle(java.util.Map)
	 */
	@Override
	public void insertProductoGrupoOfertaDetalle(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.insertProductoGrupoOfertaDetalle(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#updateProductoGrupoOfertaDetalle(java.util.Map)
	 */
	@Override
	public void updateProductoGrupoOfertaDetalle(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.updateProductoGrupoOfertaDetalle(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#getOidTipoOferta(java.util.Map)
	 */
	@Override
	public List getOidTipoOferta(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getOidTipoOferta(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#updateProductoPrincipalAsociadoOferta(java.util.Map)
	 */
	@Override
	public void updateProductoPrincipalAsociadoOferta(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.updateProductoPrincipalAsociadoOferta(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#insertProductoPrincipalAsociadoOferta(java.util.Map)
	 */
	@Override
	public void insertProductoPrincipalAsociadoOferta(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.insertProductoPrincipalAsociadoOferta(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#getVentaExclusivaOferta(java.util.Map)
	 */
	@Override
	public List getVentaExclusivaOferta(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getVentaExclusivaOferta(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#deleteVentaExclusivaPorOidVentaExclusiva(java.util.Map)
	 */
	@Override
	public void deleteVentaExclusivaPorOidVentaExclusiva(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.deleteVentaExclusivaPorOidVentaExclusiva(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#deleteOfertaIndividual(java.util.Map)
	 */
	@Override
	public void deleteOfertaIndividual(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.deleteOfertaIndividual(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#insertPromoProduCompoTemporal(java.util.Map)
	 */
	@Override
	public void insertPromoProduCompoTemporal(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.insertPromoProduCompoTemporal(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#deletePromoProduCompoTemporal(java.util.Map)
	 */
	@Override
	public void deletePromoProduCompoTemporal(Map map) {
		mantenimientoPRECambioCodigoVentaDAO.deletePromoProduCompoTemporal(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService#getPromoProduCompoTemporalList(java.util.Map)
	 */
	@Override
	public List getPromoProduCompoTemporalList(Map map) {
		return mantenimientoPRECambioCodigoVentaDAO.getPromoProduCompoTemporalList(map);
	}
}