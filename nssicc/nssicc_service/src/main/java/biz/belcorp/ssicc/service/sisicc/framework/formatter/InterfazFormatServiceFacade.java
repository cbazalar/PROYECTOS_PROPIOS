package biz.belcorp.ssicc.service.sisicc.framework.formatter;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;

/**
 * Service que obtiene el InterfazFormat. Sirve de Facade para el
 * InterfazFormatter conteniendo los siguientes Services: DelimitadorService,
 * EstructuraArchivoService, FormatoService, TipoFormatoArchivoService,
 * NormaInterfazService. De esta manera con esta Interface Facade se facilita el
 * acceso al Bean InterfazFormat para que sea usado por el
 * InterfazFormatterService.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public interface InterfazFormatServiceFacade {
	/**
	 * Obtiene el InterfazFormat para la Interfaz SiCC. Este bean contiene los
	 * parametros necesarios para formatear o parsear la data en las Interfaces.
	 * 
	 * @param interfaz
	 * @return InterfazFormatter de la Interfaz
	 */
	public InterfazFormat getInterfazFormat(Interfaz interfaz);

	
}
