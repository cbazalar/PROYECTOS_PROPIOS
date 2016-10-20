/**
 * 
 */
package biz.belcorp.ssicc.service.zon.ws;

import biz.belcorp.ssicc.service.zon.ws.beans.ResponsableZONWebServiceResultado;
import biz.belcorp.ssicc.service.zon.ws.beans.TerritZONWebServiceResultado;
import biz.belcorp.ssicc.service.zon.ws.beans.UbigZONWebServiceResultado;

/**
 * @author itocto
 * 
 */
public interface ProcesoZONWebService {

	/**
	 * Este servicio web permite obtener el objeto Territorios.
	 */
	public TerritZONWebServiceResultado consultarTerritorio();

	/**
	 * Este servicio web permite obtener el objeto Ubigeos
	 */
	public UbigZONWebServiceResultado consultarUbigeo();

	/**
	 * Este servicio web permite obtener el objeto Responsables
	 */
	public ResponsableZONWebServiceResultado asignarResponsable(
			String codigoUsuario, String codigoPais, String codigoConsultora,
			String codigoRegion, String codigoZona, String codigoSeccion,
			String campaniaNombramiento, String indUA, String indOperacion);

}
