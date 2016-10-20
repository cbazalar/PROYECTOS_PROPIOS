package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETiposAnaquelesMapaCDDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoAnaquelMapaCD;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPETiposAnaquelesMapaCDService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 *  
 * <p>
 * <a href="MantenimientoAPETiposAnaquelesMapaCDServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPETiposAnaquelesMapaCDService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPETiposAnaquelesMapaCDServiceImpl extends BaseService implements MantenimientoAPETiposAnaquelesMapaCDService {

	@Resource(name="spusicc.mantenimientoAPETiposAnaquelesMapaCDDAO")
	private MantenimientoAPETiposAnaquelesMapaCDDAO mantenimientoAPETiposAnaquelesMapaCDDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getMapaCentroDistribucionList(java.util.Map)
	 */
	public List getMapaCentroDistribucionList(Map criteria) {
		return mantenimientoAPETiposAnaquelesMapaCDDAO.getMapaCentroDistribucionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getMapaZonaList(java.util.Map)
	 */
	public List getMapaZonaList(Map criteria) {
		return mantenimientoAPETiposAnaquelesMapaCDDAO.getMapaZonaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getOidMapaCentroDistribucion(java.util.Map)
	 */
	public String getOidMapaCentroDistribucion(Map criteria) {
		return mantenimientoAPETiposAnaquelesMapaCDDAO.getOidMapaCentroDistribucion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getOidMapaZona(java.util.Map)
	 */
	public String getOidMapaZona(Map criteria) {
		return mantenimientoAPETiposAnaquelesMapaCDDAO.getOidMapaZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getOidSubLineaArmado(java.util.Map)
	 */
	public String getOidSubLineaArmado(Map criteria) {
		return mantenimientoAPETiposAnaquelesMapaCDDAO.getOidSubLineaArmado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getSubLineasArmadoList(java.util.Map)
	 */
	public List getSubLineasArmadoList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getSubLineasArmadoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getSubLineasList(java.util.Map)
	 */
	public List getSubLineasList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getSubLineasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getAnaquelesMapaCDList(java.util.Map)
	 */
	public List getAnaquelesMapaCDList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getAnaquelesMapaCDList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getTipoAnaquelFiltroList(java.util.Map)
	 */
	public List getTipoAnaquelFiltroList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getTipoAnaquelFiltroList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#updateMapaCentroDistribucionDetalleAframe(java.util.Map)
	 */
	public void updateMapaCentroDistribucionDetalleAframe(Map criteria) {
		this.mantenimientoAPETiposAnaquelesMapaCDDAO.updateMapaCentroDistribucionDetalleAframe(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#updateMapaCentroDistribucionDetalleNoAframe(java.util.Map)
	 */
	public void updateMapaCentroDistribucionDetalleNoAframe(Map criteria) {
		this.mantenimientoAPETiposAnaquelesMapaCDDAO.updateMapaCentroDistribucionDetalleNoAframe(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getNumeroAnaquelxSubLineaList(java.util.Map)
	 */
	public List getNumeroAnaquelxSubLineaList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getNumeroAnaquelxSubLineaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getTipoAnaquelList(java.util.Map)
	 */
	public List getTipoAnaquelList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getTipoAnaquelList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getTipoChanelComboList(java.util.Map)
	 */
	public List getTipoChanelComboList(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getTipoChanelComboList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#executeRegistrarTipoAnaquelesMapaCDDetalle(java.util.Map)
	 */
	public List executeRegistrarTipoAnaquelesMapaCDDetalle(String[] listaoidMapaCDDet,  String[] listaTipoAnaquel,
              String[] listaSide,          String[] listaFrameNumber,
	           String[] listaChanelAddress, String[] listaMachineAddress,
	           String[] listaLevelNumber,   String[] listaAlto,
	           String[] listaAncho,	        Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.executeRegistrarTipoAnaquelesMapaCDDetalle(listaoidMapaCDDet , listaTipoAnaquel , 
																								listaSide         , listaFrameNumber    ,
																								listaChanelAddress, listaMachineAddress ,
																								listaLevelNumber  , listaAlto           ,
																								listaAncho        ,	criteria             );
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#getObtenerDatosCabeceraTipoAnaquel(java.util.Map)
	 */
	public TipoAnaquelMapaCD getObtenerDatosCabeceraTipoAnaquel(Map criteria) {
		return this.mantenimientoAPETiposAnaquelesMapaCDDAO.getObtenerDatosCabeceraTipoAnaquel(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposAnaquelesMapaCDService#executeCargaArchivoExcel(java.util.Map)
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception {
		
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		List list = null;
		List listaError = null;
		
		int numfila=0;
		
		int validacion = 0;
			
		List errores = new ArrayList();
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			Map params = new HashMap();

			if(list==null) list = new ArrayList();
			
			if (numfila > 0){
				if(StringUtils.isNotEmpty((String)mapRow.get("0"))){
					//String anaquel = (String)mapRow.get("0");
					
					params.put("numAnaquel", (String)mapRow.get("0"));
					params.put("codTipoAnaquel", (String)mapRow.get("1"));
					params.put("valSide", (String)mapRow.get("2"));
					params.put("nroAframe", new BigDecimal((String)mapRow.get("3")));
					params.put("addressAframe",  new BigDecimal((String)mapRow.get("4")));
					params.put("heightAframe",  new BigDecimal((String)mapRow.get("5")));
					params.put("widthAframe",  new BigDecimal((String)mapRow.get("6")));
					params.put("machAddressAframe",  new BigDecimal((String)mapRow.get("7")));
					params.put("levelAframe",  new BigDecimal((String)mapRow.get("8")));									
					params.put("oidSublinea", criteria.get("oidSublinea"));
					params.put("oidMapaCD", criteria.get("oidMapaCD"));
					params.put("oidSistPicado", criteria.get("oidSistPicado"));
					
					mantenimientoAPETiposAnaquelesMapaCDDAO.executeActualizarTiposAnaquelesMapaCD(params);
					
					if(!params.get("valError").equals("0")){
						Map error = new HashMap();
						error.put("numAnaquel", (String)mapRow.get("0"));
						error.put("validacion", params.get("valError"));
						errores.add(error);
					}					
				}
			}
			++numfila;
		}

		excelUtil.cerrar();
		return errores.size() == 0 ? null : errores;
	}

}
