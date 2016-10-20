package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/** 
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 **/

public class CambioTipoOrdenPedidosCargados implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoCliente;
	
	private String tipoOrden;

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getTipoOrden() {
		return tipoOrden;
	}

	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}
}