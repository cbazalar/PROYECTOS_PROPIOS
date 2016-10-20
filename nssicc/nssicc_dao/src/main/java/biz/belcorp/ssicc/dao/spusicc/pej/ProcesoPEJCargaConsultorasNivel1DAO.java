package biz.belcorp.ssicc.dao.spusicc.pej;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.ConsultoraNivel1;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJCargaConsultorasNivel1DAO extends DAO{

	/**
	 * Verifica si el cliente existe en la mae_clien
	 * @param codigoCliente
	 * @return
	 */
	public Integer getExisteCliente(String codigoCliente);

	/**
	 * Inserta la consultora de nivel 1
	 * @param consultoraNivel1
	 */
	public void insertConsultoraNivel1(ConsultoraNivel1 consultoraNivel1);

	/**
	 * Elimina una consultora del nivel 1 por Etapa
	 * @param codigoPais
	 * @param codigoEtapa
	 */
	public void deleteCargaConsultorasNivel1ByEtapa(String codigoPais,String codigoEtapa);
}