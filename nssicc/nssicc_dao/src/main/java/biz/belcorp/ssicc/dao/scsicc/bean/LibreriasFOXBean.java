package biz.belcorp.ssicc.dao.scsicc.bean;

import java.util.Map;

public class LibreriasFOXBean { 
	
	private String libComercial;
	private String libLogistica;	
	private String libProgsComercial;
	private String libProgsLogistica;
	private Map servidores;
	
	public String getLibComercial() {
		return libComercial;
	}
	public void setLibComercial(String libComercial) {
		this.libComercial = libComercial;
	}
	public String getLibLogistica() {
		return libLogistica;
	}
	public void setLibLogistica(String libLogistica) {
		this.libLogistica = libLogistica;
	}
	public String getLibProgsComercial() {
		return libProgsComercial;
	}
	public void setLibProgsComercial(String libProgsComercial) {
		this.libProgsComercial = libProgsComercial;
	}
	public String getLibProgsLogistica() {
		return libProgsLogistica;
	}
	public void setLibProgsLogistica(String libProgsLogistica) {
		this.libProgsLogistica = libProgsLogistica;
	}
	
	public String getServidores(String codigo) {
		return (String) servidores.get(codigo);
	}
	
	public void setServidores(Map servidores) {
		this.servidores = servidores;
	}
	
	
}
