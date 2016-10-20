/*
 * Created on 26/12/2005 11:39:41 AM
 * biz.belcorp.ssicc.scdf.service.impl.UltimasNoticiasServiceImpl
 */
package biz.belcorp.ssicc.service.scdf.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ControlFacturacionDAO;
import biz.belcorp.ssicc.dao.scdf.UltimasNoticiasDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.UltimasNoticiasService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UltimasNoticiasServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("scdf.ultimasNoticiasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class UltimasNoticiasServiceImpl extends BaseService implements
        UltimasNoticiasService {

	@Resource(name="scdf.ultimasNoticiasDAO")
    private UltimasNoticiasDAO ultimasNoticiasDAO;

	@Resource(name="scdf.controlFacturacionDAO")
    private ControlFacturacionDAO controlFacturacionDAO;

	private VelocityEngine velocityEngine;
      

    /**
     * @param velocityEngine
     *            The velocityEngine to set.
     */
	@Autowired
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.UltimasNoticiasService#printUltimasNoticias(java.lang.String)
     */
    public void printUltimasNoticias(String codigoPais) {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'printUltimasNoticias'");
        }

        Map criteria = new HashMap();
        criteria.put("codigoPais", codigoPais);

        // Obtenemos el objeto ControlFacturacion para obtener la ruta destino
        ControlFacturacion controlFacturacion = controlFacturacionDAO
                .getControlFacturacion(codigoPais);

        // Obtenemos la relacion de consultoras que han pasado pedido
        List consultoras = ultimasNoticiasDAO.getConsultoras(criteria);
        Iterator i = consultoras.iterator();

        while (i.hasNext()) {
            Map consultora = (Map) i.next();
            String codigoConsultora = MapUtils.getString(consultora,
                    "CODIGO_CONSULTORA");
            if (log.isDebugEnabled()) {
                log.debug("Procesando la informacion de la consultora '"
                        + codigoConsultora + "'");
            }

            FileOutputStream fos = null;
            File file = new File(controlFacturacion.getRutaArchivoPrivilege(),
                    codigoConsultora + ".pdf");

            try {
                // Creamos el reporte de Ultimas Noticias
                // Creamos el OutputStream cuyo nombre ser el codigo de la
                // consultora en la ruta obtenido del objeto ControlFacturacion
                fos = new FileOutputStream(file);
                JasperReportsUtils.renderAsPdf(getReport(), getParameters(
                        codigoPais, codigoConsultora),
                        getDataSource(consultora), fos);
                fos.close();
                fos = null;
            } catch (Exception e) {
                if (fos != null) {
                    try {
                        fos.close();
                        fos = null;
                    } catch (IOException ignore) {
                    }
                }
                log.error(e.getMessage(), e);
                throw new ServiceException(e.getMessage());
            }
        }
    }

    private Map getParameters(String codigoPais, String codigoConsultora)
            throws Exception {
        // Creamos los parametros a enviar
        Map parameters = new HashMap();

        // 1. FICHAS DE INSCRIPCION
        // Obtenemos la relacion de las fichas de inscripcion
        Map fichasCriteria = new HashMap();
        fichasCriteria.put("codigoPais", codigoPais);
        fichasCriteria.put("codigoConsultora", codigoConsultora);

        List fichas = ultimasNoticiasDAO.getFichasInscripcion(fichasCriteria);

        // Obtenemos la referencia al reporte de fichas de inscripcion
        ClassPathResource fichasResource = new ClassPathResource(
        		Constants.JASPER_DIRECTORIO + "fichasInscripcion.jasper", getClass());
        JasperReport fichasSubreport = (JasperReport) JRLoader
                .loadObject(fichasResource.getInputStream());

        // Guardamos la referencia del reporte y su datasource en el map
        parameters.put("fichasSubreport", fichasSubreport);
        parameters.put("fichasSubreportData", new JRMapCollectionDataSource(
                fichas));

        // 2. TARJETAS DE PUNTOS
        // Obtenemos la relacion de las tarjetas de puntos
        Map tarjetasCriteria = new HashMap();
        tarjetasCriteria.put("codigoPais", codigoPais);
        tarjetasCriteria.put("codigoConsultora", codigoConsultora);

        List tarjetas = ultimasNoticiasDAO.getTarjetasPuntos(tarjetasCriteria);

        // Obtenemos la referencia al reporte de fichas de inscripcion
        ClassPathResource tarjetasResource = new ClassPathResource(
        		Constants.JASPER_DIRECTORIO + "tarjetasPuntos.jasper", getClass());
        JasperReport tarjetasSubreport = (JasperReport) JRLoader
                .loadObject(tarjetasResource.getInputStream());

        // Guardamos la referencia del reporte y su datasource en el map
        parameters.put("tarjetasSubreport", tarjetasSubreport);
        parameters.put("tarjetasSubreportData", new JRMapCollectionDataSource(
                tarjetas));

        // 3. PREMIOS SOLICITADOS
        // Obtenemos la relacion de los premios solicitados
        Map premiosCriteria = new HashMap();
        premiosCriteria.put("codigoPais", codigoPais);
        premiosCriteria.put("codigoConsultora", codigoConsultora);

        List premios = ultimasNoticiasDAO
                .getPremiosSolicitados(premiosCriteria);

        // Obtenemos la referencia al reporte de fichas de inscripcion
        ClassPathResource premiosResource = new ClassPathResource(
        		Constants.JASPER_DIRECTORIO + "premiosSolicitados.jasper", getClass());
        JasperReport premiosSubreport = (JasperReport) JRLoader
                .loadObject(premiosResource.getInputStream());

        // Guardamos la referencia del reporte y su datasource en el map
        parameters.put("premiosSubreport", premiosSubreport);
        parameters.put("premiosSubreportData", new JRMapCollectionDataSource(
                premios));

        return parameters;
    }

    private JasperReport getReport() throws Exception {
        // Cargamos el reporte
        ClassPathResource resource = new ClassPathResource(
        		Constants.JASPER_DIRECTORIO + "ultimasNoticiasPrivilege.jasper", getClass());
        return (JasperReport) JRLoader.loadObject(resource.getInputStream());
    }

    private JRDataSource getDataSource(Map consultora) {
        List list = new ArrayList();
        list.add(consultora);

        return new JRMapCollectionDataSource(list);
    }

    
  
	public void executeGenerarUltimasNoticias(String codigoPais, Usuario usuario) {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'executeGenerarUltimasNoticias'");
        }
        // Eliminamos la informacion anterior
        ultimasNoticiasDAO.executeEliminarUltimasNoticias();
        
        Map criteria = new HashMap();
        criteria.put("codigoPais", codigoPais);
        
        
        // Obtenemos la relacion de consultoras que han pasado pedido
        List consultoras = ultimasNoticiasDAO.getConsultoras(criteria);
        Iterator i = consultoras.iterator();

        while (i.hasNext()) {
            // Creamos el map a ser pasado a la plantilla Velocity
            Map model = new HashMap();
            // Obtenemos la informacion de la consultora
            Map consultora = (Map) i.next();
            String codigoConsultora = MapUtils.getString(consultora,
                    "CODIGO_CONSULTORA");
            if (log.isDebugEnabled()) {
                log.debug("Procesando la informacion de la consultora '"
                        + codigoConsultora + "'");
            }
            // Guardamos la informacion de la consultora
            model.put("consultora", consultora);
            
            //Obtenemos el indicador de L'EBEL que puede ser 'N' sie s Esika o 'S' en caso sea de L'Ebel
            List listIndicador = ultimasNoticiasDAO.getIndicadorLEbel(criteria);        
            Map indicador = (Map)listIndicador.iterator().next();
            String codigoIndicador = MapUtils.getString(indicador,"IND_LBEL");
            model.put("indicadorLEbel", codigoIndicador);
            
           // Guardamos el mensaje que le aparecera a los nuevos cleintes de privilege inscritos en PRI_CONSU_INSCR
            Map mensajesCriteria = new HashMap();
            mensajesCriteria.put("codigoPais", codigoPais);
            mensajesCriteria.put("codigoConsultora", codigoConsultora);
            
            List mensajesInscripcion = ultimasNoticiasDAO.getMensajesPrivilege(mensajesCriteria);
            String codPrivilege="";
            if(mensajesInscripcion != null && mensajesInscripcion.size() > 0) {
                Map privilege= (Map)mensajesInscripcion.get(0);
                codPrivilege = MapUtils.getString(privilege,"CODIGO_PRIVILEGE");
                model.put("longitudPrivilege", String.valueOf(codPrivilege.length()));
                
                String mensaje_Privilege_parrafo1 = this.getKeyMessage("mensaje_Privilege_parrafo1.key", usuario) + codPrivilege + "."; 
                String mensaje_Privilege_parrafo2 = this.getKeyMessage("mensaje_Privilege_parrafo2.key", usuario);
                String mensaje_Privilege_parrafo3 = this.getKeyMessage("mensaje_Privilege_parrafo3.key", usuario);
                
                model.put("mensaje_Privilege_parrafo1", mensaje_Privilege_parrafo1);
                model.put("mensaje_Privilege_parrafo2", mensaje_Privilege_parrafo2);
                model.put("mensaje_Privilege_parrafo3", mensaje_Privilege_parrafo3);
            }
            
            // 1. FICHAS DE INSCRIPCION
            // Obtenemos la relacion de las fichas de inscripcion
            Map fichasCriteria = new HashMap();
            fichasCriteria.put("codigoPais", codigoPais);
            fichasCriteria.put("codigoConsultora", codigoConsultora);

            List fichasInscripcion = ultimasNoticiasDAO.getFichasInscripcion(fichasCriteria);
            
            // Guardamos la informacion de las fichas
            model.put("fichasInscripcion", fichasInscripcion);

            // Obtenemos la relacion de los movimientos de clientes
            Map movimientosCriteria = new HashMap();
            movimientosCriteria.put("codigoPais", codigoPais);
            movimientosCriteria.put("codigoConsultora", codigoConsultora);

            List movimientoClientes = ultimasNoticiasDAO.getMovimientoClientes(movimientosCriteria);
            
            // Guardamos la informacion de los movimientos de clientes
            model.put("movimientoClientes", movimientoClientes);
            
            // Obtenemos la relacion de los clientes rechazados
            Map rechazadosCriteria = new HashMap();
            rechazadosCriteria.put("codigoPais", codigoPais);
            rechazadosCriteria.put("codigoConsultora", codigoConsultora);

            List clientesRechazados = ultimasNoticiasDAO.getClientesRechazados(rechazadosCriteria);
            // Guardamos la informacion de los clientes rechazados
            model.put("clientesRechazados", clientesRechazados);

            // 2. TARJETAS DE PUNTOS
            // Obtenemos la relacion de las tarjetas de puntos
            Map tarjetasCriteria = new HashMap();
            tarjetasCriteria.put("codigoPais", codigoPais);
            tarjetasCriteria.put("codigoConsultora", codigoConsultora);

            List tarjetasPuntos = ultimasNoticiasDAO.getTarjetasPuntos(tarjetasCriteria);
            // Guardamos la informacion de las tarjetas de puntos
            model.put("tarjetasPuntos", tarjetasPuntos);

            // 2. PREMIOS SOLICITADOS
            // Obtenemos la relacion de los premios solicitados
            Map premiosCriteria = new HashMap();
            premiosCriteria.put("codigoPais", codigoPais);
            premiosCriteria.put("codigoConsultora", codigoConsultora);

            List premiosSolicitados = ultimasNoticiasDAO
                    .getPremiosSolicitados(premiosCriteria);
            // Guardamos la informacion de los premios solicitados
            model.put("premiosSolicitados", premiosSolicitados);
            
            // 3. PREMIOS ACUMULADOS
            // Obtenemos la relacion de los premios acumulados
            Map premiosAcumCriteria = new HashMap();
            premiosAcumCriteria.put("codigoPais", codigoPais);
            premiosAcumCriteria.put("codigoConsultora", codigoConsultora);

            List premiosAcumSolicitados = ultimasNoticiasDAO
                    .getPremiosAcumulados(premiosAcumCriteria);
            // Guardamos la informacion de los premios acumulados
            model.put("premiosAcumulados", premiosAcumSolicitados);

            // 3. CARNE_BENEFICIOS
            // Obtenemos la relacion de los premios acumulados
            Map carneBeneficiosCriteria = new HashMap();
            carneBeneficiosCriteria.put("codigoPais", codigoPais);
            carneBeneficiosCriteria.put("codigoConsultora", codigoConsultora);

            List carneBeneficios = ultimasNoticiasDAO
                    .getCarneBeneficios(carneBeneficiosCriteria);
            // Guardamos la informacion de los premios acumulados
            model.put("carneBeneficios", carneBeneficios);


            // 4. CUMPLEAOS DE CLIENTES
            // Obtenemos la relacion de clientes cargados
            Map cumpleañosCriteria = new HashMap();
            cumpleañosCriteria.put("codigoPais", codigoPais);
            cumpleañosCriteria.put("codigoConsultora", codigoConsultora);
            List clientesCumpleaños = ultimasNoticiasDAO.getClientesCumpleaños(cumpleañosCriteria);
            // Guardamos la informacion de los clientes que cumplen aos
            model.put("clientesCumpleanyos", clientesCumpleaños);
            
            // Usamos el motor Velocity para hacer el merge
            try {
                String archivoXML = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine,
                        "ultimasNoticiasPrivilege.vm", model);
                
                // Insertamos el registro en la base de datos
                ultimasNoticiasDAO.insertUltimasNoticiasConsultora(codigoConsultora, archivoXML);
                
            } catch (VelocityException ve) {
                log.error(ve.getMessage(), ve);
                throw new ServiceException(ve.getMessage());
            }
        }
    }


}
