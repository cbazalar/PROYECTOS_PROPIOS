package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ConsultaPEJProgramaEjecutivasDAO extends DAO{

	/**
	 * Devuelve el programa de ejecutivas
	 * @param criteria
	 * @return
	 */
	public List getProgramaEjecutivasList(Map criteria);

	/**
	 * Obtiene la meta pedido de una ejecutiva en una etapa determinada
	 * @param criteria
	 * @return
	 */
	public Integer getMetaPedido(Map criteria);

	/**
	 * Obtiene la descripcion del nivel mediante el codigo de ejecutiva y codigo de etapa
	 * @param criteria
	 * @return
	 */
	public String getDescripcionNivelByEjecuEtap(Map criteria);

	/**
	 * Obitne la cantidad de campaas por etapa
	 * @param codigoPais
	 * @param codigoEtapa 
	 * @return
	 */
	public Integer getCantidadCampanhaEtapa(String codigoPais, String codigoEtapa);

	/**
	 * Ontiene la campanha inicial segun la etapa seleccionada
	 * @param codigoPais
	 * @param codigoEtapa
	 * @return
	 */
	public String getCampanhaInicialEtapa(String codigoPais, String codigoEtapa);

	/**
	 * Ontiene la campanha final segun la etapa seleccionada
	 * @param codigoPais
	 * @param codigoEtapa
	 * @return
	 */
	public String getCampanhaFinalEtapa(String codigoPais, String codigoEtapa);

	/**
	 * Obtiene el periodo deacuerdo al valor d registro
	 * @param codigoPais
	 * @param periodo
	 * @param valorRegistro
	 * @return
	 */
	public String getObtienePeriodo(String codigoPais, String periodo,Integer valorRegistro);

	/**
	 * Obtiene las metas de pedidos,Pedidos netos,Meta ingresos campaa e ingresos campaa por cada periodo y ejecutiva
	 * @param criteria
	 * @return
	 */
	public HashMap getVariablesEjecutivasCampaByPeriodoEjec(Map criteria);

	/**
	 * Obtiene la comision por la ejecutiva y periodo
	 * @param criteria
	 * @return
	 */
	public HashMap getComisionByEjecutivaPeriodo(Map criteria);

	/**
	 * Obtiene los bonos de excelencia y crecimiento por medio del codigo de ejecutiva, ao inicial  y codigo de etapa
	 * @param criteria
	 * @return
	 */
	public HashMap getMontosBonosEjecAnhioInicEtapa(Map criteria);

	/**
	 * Obtiene los pags por nivel de acuerdo a la ejecutiva y codigo de etapa y ao inicial
	 * @param criteria
	 * @return
	 */
	public List getPagosEjecEtapaList(Map criteria);

}