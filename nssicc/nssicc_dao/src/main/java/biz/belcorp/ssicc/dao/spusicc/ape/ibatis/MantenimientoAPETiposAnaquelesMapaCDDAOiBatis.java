package biz.belcorp.ssicc.dao.spusicc.ape.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETiposAnaquelesMapaCDDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoAnaquelMapaCD;

/**
 * @author Nicols Lpez
 *
 */
@Repository("spusicc.mantenimientoAPETiposAnaquelesMapaCDDAO")
public class MantenimientoAPETiposAnaquelesMapaCDDAOiBatis extends BaseDAOiBatis
		implements MantenimientoAPETiposAnaquelesMapaCDDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getMapaCentroDistribucionList(java.util.Map)
	 */
	public List getMapaCentroDistribucionList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaCentrDistList", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getMapaZonaList(java.util.Map)
	 */
	public List getMapaZonaList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getMapaZonaList", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getOidMapaCentroDistribucion(java.util.Map)
	 */
	public String getOidMapaCentroDistribucion(Map criteria) {
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMapaCentrDist", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getOidMapaZona(java.util.Map)
	 */
	public String getOidMapaZona(Map criteria) {
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidMapaZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getOidSubLineaArmado(java.util.Map)
	 */
	public String getOidSubLineaArmado(Map criteria) {
		return (String) this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidSubLiArmado", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getSubLineasArmadoList(java.util.Map)
	 */
	public List getSubLineasArmadoList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLiArmadoList", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getSubLineasList(java.util.Map)
	 */
	public List getSubLineasList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getSubLineaList", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getAnaquelesMapaCDList(java.util.Map)
	 */
	public List getAnaquelesMapaCDList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getListaAnaquelesMapaCD", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#getTipoAnaquelFiltroList(java.util.Map)
	 */
	public List getTipoAnaquelFiltroList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoAnaquelFiltroList", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#updateMapaCentroDistribucionDetalleNoAframe(java.util.Map)
	 */
	public void updateMapaCentroDistribucionDetalleNoAframe(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateMapaCentroDistrDetalNoAframe", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoTiposAnaquelesMapaCDDAO#updateMapaCentroDistribucionDetalleAframe(java.util.Map)
	 */
	public void updateMapaCentroDistribucionDetalleAframe(Map criteria){
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.updateMapaCentroDistrDetalAframe", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#getNumeroAnaquelxSubLineaList(java.util.Map)
	 */
	public List getNumeroAnaquelxSubLineaList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getNumeroAnaquelxSubLineaList", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#getTipoAnaquelList(java.util.Map)
	 */
	public List getTipoAnaquelList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoAnaquelList", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#getTipoChanelComboList(java.util.Map)
	 */
	public List getTipoChanelComboList(Map criteria) {
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getTipoChanelComboList", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#executeRegistrarTipoAnaquelesMapaCDDetalle(java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.lang.String[], java.util.Map)
	 */
	public List executeRegistrarTipoAnaquelesMapaCDDetalle(String[] listaoidMapaCDDet,  String[] listaTipoAnaquel,
			                                               String[] listaSide,          String[] listaFrameNumber,
			                                               String[] listaChanelAddress, String[] listaMachineAddress,
			                                               String[] listaLevelNumber,   String[] listaAlto,
			                                               String[] listaAncho,	        Map criteria) {
		
		String oidSistPicad = criteria.get("oidSistPicad").toString();
		String valError = "";
		
		for (int i=0; i<listaoidMapaCDDet.length; i++){
			
			//System.out.println("Oid Mapa CD Detalle ------>"+listaoidMapaCDDet[i].toString());
			if (oidSistPicad.equals("3")){
				criteria.put("oidMapaCDDet",listaoidMapaCDDet[i].toString());
				criteria.put("oidTipoAnaquel",listaTipoAnaquel[i].toString());
				criteria.put("val_Side",listaSide[i].toString());
				criteria.put("frame_numb",listaFrameNumber[i].toString());
				criteria.put("chann_addr",listaChanelAddress[i].toString());
				criteria.put("mach_addr",listaMachineAddress[i].toString());
				criteria.put("level_numb",listaLevelNumber[i].toString());
				criteria.put("alto",listaAlto[i].toString());
				criteria.put("ancho",listaAncho[i].toString());
			}else{
				criteria.put("oidMapaCDDet",listaoidMapaCDDet[i].toString());
				criteria.put("oidTipoAnaquel",listaTipoAnaquel[i].toString());
				criteria.put("val_Side",null);
				criteria.put("frame_numb",null);
				criteria.put("chann_addr",null);
				criteria.put("mach_addr",null);
				criteria.put("level_numb",null);
				criteria.put("alto",null);
				criteria.put("ancho",null);
			}
			
			if (!listaoidMapaCDDet[i].toString().equals("0")){
				this.getSqlMapClientTemplate().insert("spusicc.ape.MantenimientoAPESQL.insertTipoAnaqueles", criteria);
			}
			
		}
		
		if (oidSistPicad.equals("3")){
			this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeGeneraValidaTipoAnaqueles", criteria);
			valError = criteria.get("NumeroError").toString();
		}else{
			valError = "0";
		}
		
		System.out.println("El Val Error es: --->"+valError);
		
		if (valError.equals("0")){
		   this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeRegistraTipoAnaquelesMapaCDDet", criteria);
		}
		
		return this.getSqlMapClientTemplate().queryForList("spusicc.ape.MantenimientoAPESQL.getNumeroAnaquelesErroresList", criteria);
				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#getObtenerDatosCabeceraTipoAnaquel(java.util.Map)
	 */
	public TipoAnaquelMapaCD getObtenerDatosCabeceraTipoAnaquel(Map criteria){
		return (TipoAnaquelMapaCD)this.getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getObtenerDatosCabTipoAnaquel", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#esSublineaArmadoMatching(java.lang.String)
	 */
	public boolean esSublineaArmadoMatching(String codSublinea) {
		Map criteria = new HashMap();
		criteria.put("codSublinea", codSublinea);
		Integer validaSublineaArmadoMatching = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.validaSublineaArmadoMatching", criteria);
		return validaSublineaArmadoMatching == 1;
	}

	public void executeActualizarTiposAnaquelesMapaCD(Map criteria) {
		this.getSqlMapClientTemplate().update("spusicc.ape.MantenimientoAPESQL.executeActualizarTiposAnaquelesMapaCD", criteria);
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.dao.MantenimientoAPETiposAnaquelesMapaCDDAO#getOidFuncionDistribucionPorOidSublinea(java.lang.String)
	 */
	public String getOidFuncionDistribucionPorOidSublinea(String oidSublinea) {
		 return(String)getSqlMapClientTemplate().queryForObject("spusicc.ape.MantenimientoAPESQL.getOidFuncionDistribucionPorOidSublinea", oidSublinea);
	}
}
