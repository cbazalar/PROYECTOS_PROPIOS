package biz.belcorp.ssicc.service.scsicc.framework.beans;

import java.util.List;
import java.util.Map;


/**
 * Clase que posee los parametros para la ejecucin del Grafico
 * @author peextcbazalar
 *
 */
public class GraphParams {
	
	private String tituloGrafico;
	private String subTituloGrafico;
	private String tipoGrafico;
	private String tituloEjeX;
	private String tituloEjeY;
	
	private String mensajeNoData;
	private Map    parameterMap;
	
	private List listDataGenerada;
	
	
	/**
	 * @return Returns the parameterMap.
	 */
	public Map getParameterMap() {
		return parameterMap;
	}
	/**
	 * @param parameterMap The parameterMap to set.
	 */
	public void setParameterMap(Map parameterMap) {
		this.parameterMap = parameterMap;
	}
	/**
	 * @return Returns the tituloGrafico.
	 */
	public String getTituloGrafico() {
		return tituloGrafico;
	}
	/**
	 * @param tituloGrafico The tituloGrafico to set.
	 */
	public void setTituloGrafico(String tituloGrafico) {
		this.tituloGrafico = tituloGrafico;
	}
	
	/**
	 * @return Returns the tipoGrafico.
	 */
	public String getTipoGrafico() {
		return tipoGrafico;
	}
	
	/**
	 * @param tipoGrafico The tipoGrafico to set.
	 */
	public void setTipoGrafico(String tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}
	
	/**
	 * @return Returns the listDataGenerada.
	 */
	public List getListDataGenerada() {
		return listDataGenerada;
	}
	
	/**
	 * @param listDataGenerada The listDataGenerada to set.
	 */
	public void setListDataGenerada(List listDataGenerada) {
		this.listDataGenerada = listDataGenerada;
	}
	/**
	 * @return Returns the mensajeNoData.
	 */
	public String getMensajeNoData() {
		return mensajeNoData;
	}
	/**
	 * @param mensajeNoData The mensajeNoData to set.
	 */
	public void setMensajeNoData(String mensajeNoData) {
		this.mensajeNoData = mensajeNoData;
	}
	/**
	 * @return Returns the subTituloGrafico.
	 */
	public String getSubTituloGrafico() {
		return subTituloGrafico;
	}
	/**
	 * @param subTituloGrafico The subTituloGrafico to set.
	 */
	public void setSubTituloGrafico(String subTituloGrafico) {
		this.subTituloGrafico = subTituloGrafico;
	}
	/**
	 * @return Returns the tituloEjeX.
	 */
	public String getTituloEjeX() {
		return tituloEjeX;
	}
	/**
	 * @param tituloEjeX The tituloEjeX to set.
	 */
	public void setTituloEjeX(String tituloEjeX) {
		this.tituloEjeX = tituloEjeX;
	}
	/**
	 * @return Returns the tituloEjeY.
	 */
	public String getTituloEjeY() {
		return tituloEjeY;
	}
	/**
	 * @param tituloEjeY The tituloEjeY to set.
	 */
	public void setTituloEjeY(String tituloEjeY) {
		this.tituloEjeY = tituloEjeY;
	}
	
	
	
	
}
