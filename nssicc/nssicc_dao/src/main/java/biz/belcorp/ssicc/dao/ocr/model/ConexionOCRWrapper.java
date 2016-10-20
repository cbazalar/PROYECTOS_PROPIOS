package biz.belcorp.ssicc.dao.ocr.model;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Clase Wrapper para conexion JDBC para OCR Comercial
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlso Bazalar</a>
 */
public class ConexionOCRWrapper {
	
	private String codigoPais;
	private String tipoConexionExterna;
	private String host;
	private String usuario;
	private String password;
	private boolean isOpen;
	private DriverManagerDataSource ds;
	
	public ConexionOCRWrapper(){
		isOpen=false;
	}
	/**
	 * @return Returns the host.
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * @param host The host to set.
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return Returns the tipoConexionExterna.
	 */
	public String getTipoConexionExterna() {
		return tipoConexionExterna;
	}
	
	/**
	 * @param tipoConexionExterna The tipoConexionExterna to set.
	 */
	public void setTipoConexionExterna(String tipoConexionExterna) {
		this.tipoConexionExterna = tipoConexionExterna;
	}
	
	/**
	 * @return Returns the usuario.
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * @param usuario The usuario to set.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * @param isOpen the isOpen to set
	 */
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	/**
	 * @return the ds
	 */
	public DriverManagerDataSource getDs() {
		return ds;
	}
	/**
	 * @param ds the ds to set
	 */
	public void setDs(DriverManagerDataSource ds) {
		this.ds = ds;
	}
	

	
		
}
