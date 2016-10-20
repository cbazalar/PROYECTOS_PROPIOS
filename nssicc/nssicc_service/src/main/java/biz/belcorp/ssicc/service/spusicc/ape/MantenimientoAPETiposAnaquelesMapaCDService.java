package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoAnaquelMapaCD;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPETiposAnaquelesMapaCDService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPETiposAnaquelesMapaCDService extends Service{

	/**
	 * @param criteria
	 * @return La lista de Mapa de Zonas
	 */
	public List getMapaZonaList(Map criteria);
	
	
	/**
	 * @param criteria
	 * @return El oidMapaZona
	 */
	public String getOidMapaZona(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Mapa de Centro de Distribucin
	 */
	public List getMapaCentroDistribucionList(Map criteria);
	
	/**
	 * @param criteria
	 * @return El oidMapaCentroDistribucion
	 */
	public String getOidMapaCentroDistribucion(Map criteria);

	/**
	 * @param criteria
	 * @return La lista de Sub Lineas de Armado
	 */
	public List getSubLineasArmadoList(Map criteria);
	
	/**
	 * @param criteria
	 * @return El oidSubLineaArmado
	 */
	public String getOidSubLineaArmado(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Sub Lineas de Armado
	 */
	public List getSubLineasList(Map criteria);
	
	
	/**
	 * @param criteria
	 * @return La lista de Anaqueles recuperados por Mapa CD, Linea y Sublinea.
	 */
	public List getAnaquelesMapaCDList(Map criteria);
	
	/**
	 * @param criteria
	 * @return Los Tipos de Anaqueles dependiendo del Indicador Tipo Aframe
	 */
	public List getTipoAnaquelFiltroList(Map criteria);

	/**
	 * @param criteria
	 * Actualiza los tipos de Anaqueles que no correponden a Sistema de Picado AFrame
	 */
	public void updateMapaCentroDistribucionDetalleNoAframe(Map criteria);
	
	/**
	 * @param criteria
	 * Actualiza los tipos de Anaqueles que correponden a Sistema de Picado AFrame
	 */
	public void updateMapaCentroDistribucionDetalleAframe(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Anaqueles por Sub Lnea
	 */
	public List getNumeroAnaquelxSubLineaList(Map criteria);
	
	/**
	 * @param criteria
	 * @return La lista de Tipos de Anaqueles
	 */
	public List getTipoAnaquelList(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getTipoChanelComboList(Map criteria);
	
	/**
	 * @param listaoidMapaCDDet
	 * @param listaTipoChanel
	 * @param listaSide
	 * @param listaFrameNumber
	 * @param listaChanelAddress
	 * @param listaMachineAddress
	 * @param listaLevelNumber
	 * @param listaAlto
	 * @param listaAncho
	 * @param criteria
	 * Registra los tipos de Anaqueles en la tabla APE_MAPA_CENTR_DISTR_DETAL
	 */
	public List executeRegistrarTipoAnaquelesMapaCDDetalle(String[] listaoidMapaCDDet,  String[] listaTipoAnaquel,
  											               String[] listaSide,          String[] listaFrameNumber,
												           String[] listaChanelAddress, String[] listaMachineAddress,
												           String[] listaLevelNumber,   String[] listaAlto,
												           String[] listaAncho,	        Map criteria);
	
	/**
	 * @param criteria
	 * @return Los datos de Cabecera de Tipo Anaquel
	 */
	public TipoAnaquelMapaCD getObtenerDatosCabeceraTipoAnaquel(Map criteria);
	
	
	/**
	 * @param criteria
	 * @return Realiza la carga del archivo Excel
	 * @throws Exception
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception ;
	
	
}
