/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.flx;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipago;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoObjetada;
import biz.belcorp.ssicc.dao.spusicc.flx.model.ConsultoraFlexipagoPK;
import biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago;
import biz.belcorp.ssicc.service.framework.Service;



/**
 * @author Juan Altamirano
 *
 */
public interface MantenimientoFLXConsultoraService extends Service {

	/**
	 * Obtie eun listado de consultoras habilitadas para flexipago en base a los criterios de busqueda
	 * @param criteria
	 * @return
	 */
	public List getConsultorasByCriteria(Map criteria);
	
	public ConsultoraFlexipago getConsultora(ConsultoraFlexipagoPK pk);
	
	public void updateConsultora(ConsultoraFlexipago consultora, Usuario usuario);
	
	public List getCalificacionesComportamiento();
	
	public List getCalificacionesExperiencia();
	
	public ConsultoraFlexipagoAuditoria getConsultoraAuditoriaByCodigosAccion(String codigoPais, String codigoCliente, String codigoAccion1, String codigoAccion2);
	
	public String validarCodigoConsultora(String codigoConsultora);
	
	public void updateMasivoConsultoras(List listaConsultoras, String codigoAccion, Usuario usuario);
	
	public List getConsultorasObjetadaByCriteria(Map criteria);
	
	public void insertConsultoraObjetada(ConsultoraFlexipagoObjetada consultora, Usuario usuario);
	
	public ConsultoraFlexipagoObjetada getConsultoraObjetada(ConsultoraFlexipagoPK pk);
	
	public void updateMasivoConsultorasObjetadas(List listaConsultoras, String codigoAccion, Usuario usuario);
	
	public void updateConsultoraObjetada(ConsultoraFlexipagoObjetada consultora, Usuario usuario);
	
	public void deleteConsultoraObjetada(ConsultoraFlexipagoPK pk, Usuario usuario);

	public List getDatosConsultora(Map criteria);

	public List getDatosConsultoraDetalle(Map criteria);
	
	public List getDatosUsosFlexipago(Map params);

	public void executeReporteDetalleSaldo(Map params);
	
	/**
	 * @return
	 */
	public List getMotivos();
	
	public String getPasoPedido(Map criteria);	
	
	public String getCerroRegion(Map criteria);
	
	public String getCodigoConsultoraHabil(String codigoConsultora);
	
	public String getContratoConsultoraHabil(Map criteria);

	public void updateEnvioContrato(List listaConsultoras);
	
	public List getInteresFlexipagoList();
	
	public InteresFlexipago getInteresFlexipago(InteresFlexipago bean);
	
	public void insertInteresFlexipago(InteresFlexipago bean, Usuario usuario) ;	
	
	public void deleteInteresesFlexipago(InteresFlexipago bean, Usuario usuario);
	
	public void deleteTotalInteresesFlexipago();
	
	public String getRangoMaxHasta();
	
	public int getCantDecimalesxPais(Map criteria);
	
}
