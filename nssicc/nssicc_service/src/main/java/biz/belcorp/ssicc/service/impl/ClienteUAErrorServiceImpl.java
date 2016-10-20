/*
 * Created on 03-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ClienteUAErrorDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAError;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAErrorPK;
import biz.belcorp.ssicc.service.ClienteUAErrorService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAErrorServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.clienteUAErrorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ClienteUAErrorServiceImpl extends BaseService implements
		ClienteUAErrorService {

	@Resource(name="sisicc.clienteUAErrorDAO")
	private ClienteUAErrorDAO clienteUAErrorDAO;
	

	/* 
	 * @see biz.belcorp.ssicc.service.ClienteUAErrorService#insertClienteUAError(biz.belcorp.ssicc.model.ClienteUAError, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClienteUAError(ClienteUAError cliente, Usuario usuario) {
		try{
			// Verificamos que no exista un cliente con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion ObjectRetrievalFailureException			
			this.clienteUAErrorDAO.getClienteUAError(new ClienteUAErrorPK(cliente.getCodigoPais(), cliente.getCodigoCliente()));
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
			throw new InvalidIdentifierException(ClienteUAError.class, cliente.getCodigoCliente());
			
		}
		catch(ObjectRetrievalFailureException orfe){
			// Seteamos los valores por defecto
			cliente.setEstado(Constants.ESTADO_ACTIVO);
			
			// Insertamos el nuevo sistema
			this.clienteUAErrorDAO.insertClienteUAError(cliente, usuario);
		}
	}

      public int selectClienteUAError(ClienteUAError cliente)
        {   int cantidad=0;
            try{
                cantidad= this.clienteUAErrorDAO.selectClienteUAError(cliente);
               }
            catch(ObjectRetrievalFailureException orfe){ }
            return cantidad; 
        }

        public void updateClienteUAError(ClienteUAError cliente, Usuario usuario)
        {
            try{
                cliente.setEstado(Constants.ESTADO_ACTIVO);
                this.clienteUAErrorDAO.updateClienteUAError(cliente, usuario);
               }
            catch(ObjectRetrievalFailureException orfe){ }
         }
    
	/* 
	 * @see biz.belcorp.ssicc.service.ClienteUAErrorService#getClienteUAError(biz.belcorp.ssicc.model.ClienteUAErrorPK)
	 */
	public ClienteUAError getClienteUAError(ClienteUAErrorPK pk) {
		return this.clienteUAErrorDAO.getClienteUAError(pk);
	}

	/* 
	 * @see biz.belcorp.ssicc.service.ClienteUAErrorService#getClientesUAErrorByCriteria(java.util.Map)
	 */
	public List getClientesUAErrorByCriteria(Map criteria) {
		return this.clienteUAErrorDAO.getClientesUAErrorByCriteria(criteria);
	}
    
    public List getDireccionConsultorasByCriteria(Map criteria) {
        return this.clienteUAErrorDAO.getDireccionConsultorasByCriteria(criteria);
    }
    
    public List getReporteClientesErroneos(Map criteria) {
        return this.clienteUAErrorDAO.getReporteClientesErroneos(criteria);
    }
    
    
    
    public List getPagoLideresByCriteria(Map criteria) 
    {
        return this.clienteUAErrorDAO.getPagoLideresByCriteria(criteria);  
    }
    
    public List getLideresNuevasByCriteria(Map criteria) 
    {
        return this.clienteUAErrorDAO.getLideresNuevasByCriteria(criteria);
    }
    
    
    public byte[] getBytesReporteClientesErroneos(Map params, Usuario usuario, Pais pais) {
        byte[] bytes = {}; 
        List clientes = getClientesUAErrorByCriteria(params);
        
        if(clientes.size()>0){
            try{
                bytes = JasperRunManager.runReportToPdf(getReport(), getParameters(pais, usuario), getDataSource(clientes));
            }catch(Exception e){
                log.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            }            
        }                
        return bytes;
    }
    
    public byte[] getBytesReporteDireccionConsultoras(Map params, Usuario usuario, Pais pais) {
        byte[] bytes = {}; 
        List direccionesConsultoras = getDireccionConsultorasByCriteria(params);
        log.debug("tamao de la lista de direccionesConsultoras:"+direccionesConsultoras.size());
        if(direccionesConsultoras.size()>0){
            try{
                bytes = JasperRunManager.runReportToPdf(getReportDireccionConsultoras(), getParameters(pais, usuario), getDataSource(direccionesConsultoras));
            }catch(Exception e){
                log.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            }            
        }                
        return bytes;
    }
  
    public List getListaReporteClientesErroneos(Map params, Usuario usuario, Pais pais) {
        List clientesErroneos = new LinkedList();
        clientesErroneos = getReporteClientesErroneos(params);
        log.debug("tamao de la lista de clientes erroneos:"+clientesErroneos.size());
        return clientesErroneos;
    }
  
    public List getListaReporteDireccionConsultoras(Map params, Usuario usuario, Pais pais) {
        List direccionesConsultoras = new LinkedList();
        direccionesConsultoras = getDireccionConsultorasByCriteria(params);
        log.debug("tamao de la lista de direccionesConsultoras:"+direccionesConsultoras.size());
        return direccionesConsultoras;
    }
  
    
    
    public byte[] getBytesReporteCOMPagoLideres(Map params, Usuario usuario, Pais pais) {
        byte[] bytes = {}; 
        List pagoLideres = getPagoLideresByCriteria(params);
        
        
        log.debug("tamao de la lista de pago lideres:"+pagoLideres.size());
  
        if(pagoLideres.size()>0){
            try{
                String totalTransferir="";
                String fechaCalculo="";
              
                java.util.Map icpl;
                icpl = (java.util.HashMap)pagoLideres.get(0);
                fechaCalculo = (String)icpl.get("FECCAL");
                
                double suma = 0;
                for (int i =0 ; i < pagoLideres.size(); i++ )
                {   
                    icpl = (java.util.HashMap)pagoLideres.get(i);
                    suma = suma +   ((java.math.BigDecimal)icpl.get("IMPORT")).doubleValue();
                }
                totalTransferir = String.valueOf(suma);
                bytes = JasperRunManager.runReportToPdf(getReportPagoLideres(), getParametersReportePagoLideres(totalTransferir,fechaCalculo), getDataSource(pagoLideres));
            }catch(Exception e){
                log.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            }            
        }                
        return bytes;
    }
    
  
    public byte[] getBytesReporteCOMLideresNuevas(Map params, Usuario usuario, Pais pais) {
        byte[] bytes = {}; 
        List lideresNuevas = getLideresNuevasByCriteria(params);
        log.debug("tamao de la lista de pago lideres:"+lideresNuevas.size());
        if(lideresNuevas.size()>0){
            try{
                bytes = JasperRunManager.runReportToPdf(getReportLideresNuevas(), getParametersReportePagoLideres("",""), getDataSource(lideresNuevas));
            }catch(Exception e){
                log.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            }            
        }                
        return bytes;
    }
  
    
    private Map getParameters(Pais pais, Usuario usuario) throws Exception {
        Map parameters = new HashMap();
        
        parameters.put("LOGON_USER", usuario.getLogin());
        parameters.put("PAIS_LOGON_USER", pais.getDescripcion());
        
        return parameters;
    }    
    private JasperReport getReport() throws Exception {
        // Cargamos el reporte
        ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "clientesErroneos.jasper", getClass());
        return (JasperReport) JRLoader.loadObject(resource.getInputStream());
    }

    private JasperReport getReportDireccionConsultoras() throws Exception {
        // Cargamos el reporte
        ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO +  "direccionConsultoras.jasper", getClass());
        return (JasperReport) JRLoader.loadObject(resource.getInputStream());
    }
    
    private JasperReport getReportPagoLideres() throws Exception {
        // Cargamos el reporte
        ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "pagoLideres.jasper", getClass());
        return (JasperReport) JRLoader.loadObject(resource.getInputStream());
    }
    
    private JasperReport getReportLideresNuevas() throws Exception {
        // Cargamos el reporte
        ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "lideresNuevas.jasper", getClass());
        return (JasperReport) JRLoader.loadObject(resource.getInputStream());
    }
    
    
    private JRDataSource getDataSource(List data) {
        return new JRMapCollectionDataSource(data);
    }
    
    private Map getParametersReportePagoLideres(String totalTransferir,String fechaCalculo) throws Exception {
        Map parameters = new HashMap();
        parameters.put("totalTransferir",totalTransferir);
        parameters.put("fechaCalculo",fechaCalculo);
        return parameters;
    }
    
    
}
