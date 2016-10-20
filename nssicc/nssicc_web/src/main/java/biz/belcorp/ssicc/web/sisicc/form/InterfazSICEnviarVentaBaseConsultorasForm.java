package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazSICEnviarVentaBaseConsultorasForm extends BaseInterfazForm
		implements Serializable {

	private static final long serialVersionUID = 5730387859163829339L;

	private String codigoMarca;

	private String codigoCanal;

	private String codigoPeriodo;

	private String codigoPais;
	
	private String codigoTipoCliente;
	
	private String codigoSubTipoCliente;

	private String[] codigoTipoClientes;
	
	private String[] codigoSubTipoClientes;
	
	private String codigoTipoClasificacion;
	
	private String codigoClasificacion;
	
	private String codigoConcurso;
	
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
//		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
////		this.codigoTipoCliente = Constants.CODIGO_TIPO_CLIENTE_DEFAULT;
//	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoClasificacion.
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}


	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
	}

	/**
	 * @return Returns the codigoConcurso.
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso The codigoConcurso to set.
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return Returns the codigoSubTipoCliente.
	 * 
	 */
	public String getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}

	/**
	 * @param codigoSubTipoCliente The codigoSubTipoCliente to set.
	 */
	public void setCodigoSubTipoCliente(String codigoSubTipoCliente) {
		this.codigoSubTipoCliente = codigoSubTipoCliente;
	}

	/**
	 * @return Returns the codigoTipoClasificacion.
	 */
	public String getCodigoTipoClasificacion() {
		return codigoTipoClasificacion;
	}

	/**
	 * @param codigoTipoClasificacion The codigoTipoClasificacion to set.
	 */
	public void setCodigoTipoClasificacion(String codigoTipoClasificacion) {
		this.codigoTipoClasificacion = codigoTipoClasificacion;
	}

	/**
	 * @return Returns the codigoTipoCliente.
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}

	/**
	 * @param codigoTipoCliente The codigoTipoCliente to set.
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}

	public String[] getCodigoSubTipoClientes() {
		return codigoSubTipoClientes;
	}

	public void setCodigoSubTipoClientes(String[] codigoSubTipoClientes) {
		this.codigoSubTipoClientes = codigoSubTipoClientes;
	}

	public String[] getCodigoTipoClientes() {
		return codigoTipoClientes;
	}

	public void setCodigoTipoClientes(String[] codigoTipoClientes) {
		this.codigoTipoClientes = codigoTipoClientes;
	}
}