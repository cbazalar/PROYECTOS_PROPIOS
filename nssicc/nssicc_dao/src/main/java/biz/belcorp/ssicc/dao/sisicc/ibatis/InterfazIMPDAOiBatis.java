package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazIMPDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz del programa de Facturacion electronica.
 * 
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
/**
 * @author Sigcomt
 *
 */
/**
 * @author Sigcomt
 *
 */
/**
 * @author Sigcomt
 *
 */
@Repository("sisicc.interfazIMPDAO")
public class InterfazIMPDAOiBatis extends BaseDAOiBatis implements InterfazIMPDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarResultadoProgramas(java.util.Map)
	 */
	public void executeEnviarMaestroEmpresarias(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazEMPSQL.executeEnviarMaestroEmpresarias", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarConsultorasObjetadas(java.util.Map)
	 */
	public void executeEnviarBajasEmpresarias(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazEMPSQL.executeEnviarBajasEmpresarias", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeEnviarVinculosEmpresarias(java.util.Map)
	 */
	public void executeEnviarVinculosEmpresarias(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazEMPSQL.executeEnviarVinculosEmpresarias", params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarFacturasCabeceraPeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarFacturasCabeceraPeru(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarFacturasCabeceraPeru",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarFacturasDetallePeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarFacturasDetallePeru(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarFacturasDetallePeru",
				criteria);
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas065CabeceraPeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas065CabeceraPeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas065CabeceraPeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas065DetallePeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas065DetallePeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas065DetallePeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas067CabeceraPeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas067CabeceraPeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas067CabeceraPeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas067DetallePeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas067DetallePeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas067DetallePeru",
				criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasDebitoCabeceraPeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasDebitoCabeceraPeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasDebitoCabeceraPeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasDebitoDetallePeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasDebitoDetallePeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasDebitoDetallePeru",
				criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoFacturasCabeceraPeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasCabeceraPeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoFacturasCabeceraPeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoFacturasDetallePeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasDetallePeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoFacturasDetallePeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoBoletasCabeceraPeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasCabeceraPeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoBoletasCabeceraPeru",
				criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoBoletasDetallePeru(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasDetallePeru(Map criteria)  {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoBoletasDetallePeru",
				criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarSecuenciaBoletaElectronica(java.util.Map)
	 */
	public void executeInterfazIMPEnviarSecuenciaBoletaElectronica(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarSecuenciaBoletaElectronica",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletaElectronicaHistorico(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletaElectronicaHistorico(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletaElectronicaHistorico",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarOrdenImpresioXerox(java.util.Map)
	 */
	public void executeInterfazIMPEnviarOrdenImpresioXerox(Map params) {
		
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarOrdenImpresioXerox",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarFacturasCabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarFacturasCabeceraDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarFacturasCabeceraDocumento",
				params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarFacturasDetalleDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarFacturasDetalleDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarFacturasDetalleDocumento",
				params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas065CabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas065CabeceraDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas065CabeceraDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas065DetalleDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas065DetalleDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas065DetalleDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas067CabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas067CabeceraDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas067CabeceraDocumento",
				params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarBoletas067DetalleDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarBoletas067DetalleDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarBoletas067DetalleDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasDebitoCabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasDebitoCabeceraDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasDebitoCabeceraDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasDebitoDetalleDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasDebitoDetalleDocumento(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasDebitoDetalleDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoFacturasCabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasCabeceraDocumento(
			Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoFacturasCabeceraDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoFacturasDetalleDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturasDetalleDocumento(
			Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoFacturasDetalleDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoBoletasCabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasCabeceraDocumento(
			Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoBoletasCabeceraDocumento",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoBoletasDetalleDocumento(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletasDetalleDocumento(
			Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoBoletasDetalleDocumento",
				params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarOrdenImpresioXeroxAlterna(java.util.Map)
	 */
	public void executeInterfazIMPEnviarOrdenImpresioXeroxAlterna(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPEnviarOrdenImpresioXeroxAlterna",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeDesactivacionAutomaticaFLX()
	 */
	public void executeDesactivacionAutomaticaFLX(){
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeDesactivacionAutomaticaFLX");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoBoletaCabeceraRetail(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletaCabeceraRetail(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoBoletaCabeceraRetail", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoBoletaDetalleRetail(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoBoletaDetalleRetail(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoBoletaDetalleRetail", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoFacturaCabeceraRetail(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturaCabeceraRetail(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoFacturaCabeceraRetail", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPEnviarNotasCreditoFacturaDetalleRetail(java.util.Map)
	 */
	public void executeInterfazIMPEnviarNotasCreditoFacturaDetalleRetail(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPEnviarNotasCreditoFacturaDetalleRetail", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPProcesoFlexipago()
	 */
	public void executeInterfazIMPProcesoFlexipago(){
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPProcesoFlexipago");
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIMPDAO#executeInterfazIMPDesbloquearConsultorasBloqueadasxBR(java.util.Map)
	 */
	public void executeInterfazIMPDesbloquearConsultorasBloqueadasxBR(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazIMPSQL.executeInterfazIMPDesbloquearConsultorasBloqueadasxBR", params);
	}
}