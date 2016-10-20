package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPEDMatrizFacturacionForm extends BaseEditForm  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String oidMatriz;
	private String codigoPais;
	private String codigoPeriodo;
	private String pedidosEstimados;
	private String unidadesEstimadas;
	private String clientesEstimados;
	private String ventaNetaEstimada;
	private String tipoCambioPromedio;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the pedidosEstimados
	 */
	public String getPedidosEstimados() {
		return pedidosEstimados;
	}
	/**
	 * @param pedidosEstimados the pedidosEstimados to set
	 * 
	 */
	public void setPedidosEstimados(String pedidosEstimados) {
		this.pedidosEstimados = pedidosEstimados;
	}
	/**
	 * @return the unidadesEstimadas
	 */
	public String getUnidadesEstimadas() {
		return unidadesEstimadas;
	}
	/**
	 * @param unidadesEstimadas the unidadesEstimadas to set
	 * 
	 */
	public void setUnidadesEstimadas(String unidadesEstimadas) {
		this.unidadesEstimadas = unidadesEstimadas;
	}
	/**
	 * @return the clientesEstimados
	 */
	public String getClientesEstimados() {
		return clientesEstimados;
	}
	/**
	 * @param clientesEstimados the clientesEstimados to set
	 * 
	 */
	public void setClientesEstimados(String clientesEstimados) {
		this.clientesEstimados = clientesEstimados;
	}
	/**
	 * @return the ventaNetaEstimada
	 */
	public String getVentaNetaEstimada() {
		return ventaNetaEstimada;
	}
	/**
	 * @param ventaNetaEstimada the ventaNetaEstimada to set
	 * 
	 */
	public void setVentaNetaEstimada(String ventaNetaEstimada) {
		this.ventaNetaEstimada = ventaNetaEstimada;
	}
	/**
	 * @return the tipoCambioPromedio
	 */
	public String getTipoCambioPromedio() {
		return tipoCambioPromedio;
	}
	/**
	 * @param tipoCambioPromedio the tipoCambioPromedio to set
	 */
	public void setTipoCambioPromedio(String tipoCambioPromedio) {
		this.tipoCambioPromedio = tipoCambioPromedio;
	}
	/**
	 * @return the oidMatriz
	 */
	public String getOidMatriz() {
		return oidMatriz;
	}
	/**
	 * @param oidMatriz the oidMatriz to set
	 */
	public void setOidMatriz(String oidMatriz) {
		this.oidMatriz = oidMatriz;
	}
}