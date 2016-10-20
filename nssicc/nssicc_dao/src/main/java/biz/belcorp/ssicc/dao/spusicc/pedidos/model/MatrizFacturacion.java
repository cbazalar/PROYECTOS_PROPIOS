/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto
 *
 */
public class MatrizFacturacion extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6872804607193307761L;
	
	private String oidMatriz;
	private String codigoPais;
	private String codigoPeriodo;
	private String oidPeriodo;
	private String pedidosEstimados;
	private String unidadesEstimadas;
	private String clientesEstimados;
	private String ventaNetaEstimada;
	private String tipoCambioPromedio;
	
	
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

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
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
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the oidPeriodo
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo the oidPeriodo to set
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return the pedidosEstimados
	 */
	public String getPedidosEstimados() {
		return pedidosEstimados;
	}

	/**
	 * @param pedidosEstimados the pedidosEstimados to set
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MatrizFacturacion [oidMatriz=" + oidMatriz + ", codigoPais="
				+ codigoPais + ", codigoPeriodo=" + codigoPeriodo
				+ ", pedidosEstimados=" + pedidosEstimados
				+ ", unidadesEstimadas=" + unidadesEstimadas
				+ ", clientesEstimados=" + clientesEstimados
				+ ", ventaNetaEstimada=" + ventaNetaEstimada
				+ ", tipoCambioPromedio=" + tipoCambioPromedio + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrizFacturacion other = (MatrizFacturacion) obj;
		if (clientesEstimados == null) {
			if (other.clientesEstimados != null)
				return false;
		} else if (!clientesEstimados.equals(other.clientesEstimados))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPeriodo == null) {
			if (other.codigoPeriodo != null)
				return false;
		} else if (!codigoPeriodo.equals(other.codigoPeriodo))
			return false;
		if (oidMatriz == null) {
			if (other.oidMatriz != null)
				return false;
		} else if (!oidMatriz.equals(other.oidMatriz))
			return false;
		if (pedidosEstimados == null) {
			if (other.pedidosEstimados != null)
				return false;
		} else if (!pedidosEstimados.equals(other.pedidosEstimados))
			return false;
		if (tipoCambioPromedio == null) {
			if (other.tipoCambioPromedio != null)
				return false;
		} else if (!tipoCambioPromedio.equals(other.tipoCambioPromedio))
			return false;
		if (unidadesEstimadas == null) {
			if (other.unidadesEstimadas != null)
				return false;
		} else if (!unidadesEstimadas.equals(other.unidadesEstimadas))
			return false;
		if (ventaNetaEstimada == null) {
			if (other.ventaNetaEstimada != null)
				return false;
		} else if (!ventaNetaEstimada.equals(other.ventaNetaEstimada))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((clientesEstimados == null) ? 0 : clientesEstimados
						.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPeriodo == null) ? 0 : codigoPeriodo.hashCode());
		result = prime * result
				+ ((oidMatriz == null) ? 0 : oidMatriz.hashCode());
		result = prime
				* result
				+ ((pedidosEstimados == null) ? 0 : pedidosEstimados.hashCode());
		result = prime
				* result
				+ ((tipoCambioPromedio == null) ? 0 : tipoCambioPromedio
						.hashCode());
		result = prime
				* result
				+ ((unidadesEstimadas == null) ? 0 : unidadesEstimadas
						.hashCode());
		result = prime
				* result
				+ ((ventaNetaEstimada == null) ? 0 : ventaNetaEstimada
						.hashCode());
		return result;
	}

}
