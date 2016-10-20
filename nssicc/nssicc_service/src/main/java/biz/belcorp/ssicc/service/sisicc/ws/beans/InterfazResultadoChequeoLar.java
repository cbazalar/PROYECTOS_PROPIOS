package biz.belcorp.ssicc.service.sisicc.ws.beans;


/**
 * Clase que encapsula el resultado de la ejecucion de las Interfaces SiCC
 * unitarias o de paquete. Contiene el numero de lote y el detalle de la
 * ejecuci�n de interfaces unitarias.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class InterfazResultadoChequeoLar {

	
	private String mensaje;    
	private String codigo;
	private String [] list;
	private String nomArchivo;
    private RutasLARWebService listaRutas[];
    
	/**
	 * @return the list
	 */
	public String[] getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(String[] list) {
		this.list = list;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nomArchivo
	 */
	public String getNomArchivo() {
		return nomArchivo;
	}

	/**
	 * @param nomArchivo the nomArchivo to set
	 */
	public void setNomArchivo(String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	/**
	 * @return the listaRutas
	 */
	public RutasLARWebService[] getListaRutas() {
		return listaRutas;
	}

	/**
	 * @param listaRutas the listaRutas to set
	 */
	public void setListaRutas(RutasLARWebService[] listaRutas) {
		this.listaRutas = listaRutas;
	}


	
}
