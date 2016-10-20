package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.List;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;
import biz.belcorp.ssicc.dao.sisicc.model.Formato;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * Clase que encapsula los parametros necesarios para el
 * InterfaceFormatterService. Este Bean es obtenido por
 * InterfazFormatServiceFacade y es usado por InterfazFormatterService para
 * formatear o parsear los datos de las Interfaces SiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class InterfazFormat {
	private Interfaz interfaz;

	private Delimitador delimitador;

	private List estructurasArchivo;

	private Formato formato;

	private NormaInterfaz normaInterfaz;
	
	private ExcelUtil excelUtil = null;
	
	private boolean archivoBinario = false;
	
	private String tituloCabeceraInterfaz = "";
	
	
	/**
	 * Verifica si el archivo a generar es de tipo binario (Ejemplo: Excel)
	 * @param interfaz
	 * @return
	 */
	public final boolean esArchivoTipoBinario() {
		this.archivoBinario = false;
		String extensionArchivo = this.interfaz.getExtensionArchivoDescripcion().trim().toUpperCase();
		if (Constants.EXTENSION_EXCEL.equals(extensionArchivo)) {
			this.archivoBinario = true;
		}
		return this.archivoBinario;
	}
	
	/**
	 *  Verifica si el archivo a generar es de tipo XML
	 * @return
	 */
	public final boolean esArchivoTipoXML() {
		String extensionArchivo = this.interfaz.getExtensionArchivoDescripcion().trim().toUpperCase();
		if (Constants.EXTENSION_XML.equals(extensionArchivo)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica si el archivo a generar es de tipo CSV
	 * @return
	 */
	public final boolean esArchivoTipoCSV() {
		String extensionArchivo = this.interfaz.getExtensionArchivoDescripcion().trim().toUpperCase();
		if (Constants.EXTENSION_CSV.equals(extensionArchivo)) {
			return true;
		}
		return false;
	}

	public int getNumeroCampos() {
		if (estructurasArchivo != null)
			return estructurasArchivo.size();
		else
			return 0;
	}

	public List getEstructurasArchivo() {
		return estructurasArchivo;
	}

	public void setEstructurasArchivo(List estructurasArchivo) {
		this.estructurasArchivo = estructurasArchivo;
	}

	public Delimitador getDelimitador() {
		return delimitador;
	}

	public void setDelimitador(Delimitador delimitador) {
		this.delimitador = delimitador;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public NormaInterfaz getNormaInterfaz() {
		return normaInterfaz;
	}

	public void setNormaInterfaz(NormaInterfaz normaInterfaz) {
		this.normaInterfaz = normaInterfaz;
	}

	public Interfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	/**
	 * @return the excelUtil
	 */
	public ExcelUtil getExcelUtil() {
		return excelUtil;
	}

	/**
	 * @param excelUtil the excelUtil to set
	 */
	public void setExcelUtil(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}
	
	/**
	 * @return the archivoBinario
	 */
	public boolean isArchivoBinario() {
		return archivoBinario;
	}

	/**
	 * @param archivoBinario the archivoBinario to set
	 */
	public void setArchivoBinario(boolean archivoBinario) {
		this.archivoBinario = archivoBinario;
	}

	/**
	 * @return the tituloCabeceraInterfaz
	 */
	public String getTituloCabeceraInterfaz() {
		return tituloCabeceraInterfaz;
	}

	/**
	 * @param tituloCabeceraInterfaz the tituloCabeceraInterfaz to set
	 */
	public void setTituloCabeceraInterfaz(String tituloCabeceraInterfaz) {
		this.tituloCabeceraInterfaz = tituloCabeceraInterfaz;
	}

	
	
	
}