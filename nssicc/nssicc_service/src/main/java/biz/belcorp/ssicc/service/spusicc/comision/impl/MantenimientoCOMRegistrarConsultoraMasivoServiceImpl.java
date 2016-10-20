package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMRegistrarConsultoraMasivoDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ConsultoraMasivo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMRegistrarConsultoraMasivoService;

/**
 * @author Aurelio Oviedo
 *
 */
@Service("spusicc.mantenimientoCOMRegistrarConsultoraMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMRegistrarConsultoraMasivoServiceImpl extends BaseService implements MantenimientoCOMRegistrarConsultoraMasivoService {
	
	@Resource(name="spusicc.mantenimientoCOMRegistrarConsultoraMasivoDAO")
	private MantenimientoCOMRegistrarConsultoraMasivoDAO mantenimientoCOMRegistrarConsultoraMasivoDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMRegistrarConsultoraMasivoService#executeCargarListaZonas(java.util.Map)
	 */
	public void executeObtenerListaZonasAsociadas(Map criteria) {
		mantenimientoCOMRegistrarConsultoraMasivoDAO.executeObtenerListaZonasAsociadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMRegistrarConsultoraMasivoService#executeValidarRegistroxCodigoConsultora(java.util.Map)
	 */
	public void executeValidarRegistroxCodigoConsultora(Map criteria) {
		mantenimientoCOMRegistrarConsultoraMasivoDAO.executeValidarRegistroxCodigoConsultora(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMRegistrarConsultoraMasivoService#executeProcesarRegistros(java.util.Map)
	 */
	public void executeProcesarRegistros(Map criteria) {
		
		//Si el indicador es 0 - Registra
		//Si el indicador es 1 - Actualiza
		
		List listaRegistrosValidos = (List)criteria.get("listaRegistrosValidos");
		
		int indicadorConstancia = 0;
		int numeroVigenciaPrograma = 0;
		
		//Capturar el Indicador de Constancia y Numero de Vigencia Programa
		String resultado = "";
		int contador = 0;
		
		String codigoPeriodoControlFacturacion = MapUtils.getString(criteria, "codigoPeriodo");
		
		for (int i = 0; i < listaRegistrosValidos.size(); i++) {
			ConsultoraMasivo bean = new ConsultoraMasivo();
			bean = (ConsultoraMasivo)listaRegistrosValidos.get(i);
			contador = -1;
			criteria.put("codigoPeriodo", codigoPeriodoControlFacturacion);
			criteria.put("codigoPrograma", bean.getCodigoPrograma());
			criteria.put("codigoConsultora", bean.getCodigoConsultora());
			
			resultado = mantenimientoCOMRegistrarConsultoraMasivoDAO.getIndicadorConstanciaProgramaAsociado(criteria);
			
			String[] indicadores = resultado.split("_");
			
			indicadorConstancia = Integer.parseInt(indicadores[0].toString());
			numeroVigenciaPrograma = Integer.parseInt(indicadores[1].toString());
			
			List listaMatriz = new ArrayList();
						
			if (StringUtils.equals(bean.getIndicador(), "0")) {
				//Registra
				mantenimientoCOMRegistrarConsultoraMasivoDAO.insertarConsultoraNuevas(criteria);
				
				mantenimientoCOMRegistrarConsultoraMasivoDAO.insertFacturacionPrograma(criteria);
				
				criteria.put("numeroVigenciaPrograma", numeroVigenciaPrograma);
				
				if (indicadorConstancia == 1) {
					/*Indicador de Constancia = 1 El sistema 
					inserta un registro en la entidad Niveles de Consultora(CUP_CONSU_NIVEL)*/ 
					log.debug("insertanto Niveles de Consultas, cuando el indicador es 1");
					criteria.put("codigoNivel", Constants.CUP_CODIGO_NIVEL);
					
					//Campaa de Proceso -1
					criteria.put("numeroPeriodo", -1);// Lo que se va a restar o sumar en el periodo
					String campanyaInicioVigencia = 
							mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
					
					//CAM_INIC_VIG + (Numero de Vigencia-1)
					criteria.put("numeroPeriodo", numeroVigenciaPrograma -1); // Lo que se va a restar o sumar en el periodo
					criteria.put("codigoPeriodo",campanyaInicioVigencia);
					String campanyaFinVigencia =
							mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
					
					criteria.put("campanyaInicioVigencia",campanyaInicioVigencia);
					criteria.put("campanyaFinVigencia",campanyaFinVigencia);
					
					mantenimientoCOMRegistrarConsultoraMasivoDAO.insertNivelesConsultoras(criteria);
					
				}else {
					//Indicador de Constancia = 0 El sistema 
					log.debug("insertanto Niveles de Consultas, cuando el indicador es 0");
					
					listaMatriz = mantenimientoCOMRegistrarConsultoraMasivoDAO.getListaMatrizEquivalente(criteria);
					
					String periodo = (String)criteria.get("codigoPeriodo");
					
					if (listaMatriz!= null && listaMatriz.size() > 0) {
						for (int j = 0; j < listaMatriz.size(); j++) {
							Map map = new HashMap(); 
							map = (Map)listaMatriz.get(j);
							//#codigoPais#,#codigoPeriodo#,#numeroPeriodo#
							
							if (j == 1)	contador = 0;
							
							criteria.put("codigoPeriodo",periodo);
							//Campaa de Proceso -1
							criteria.put("numeroPeriodo", (contador));// Lo que se va a restar o sumar en el periodo
							String campanyaInicioVigencia = mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
							//#codigoPais#,#codigoPeriodo#,#numeroPeriodo#
							
							//CAM_INIC_VIG + (Numero de Vigencia-1)
							criteria.put("numeroPeriodo", (numeroVigenciaPrograma-1) +((contador))); // Lo que se va a restar o sumar en el periodo
							//criteria.put("codigoPeriodo",periodo);//campanyaInicioVigencia
							String campanyaFinVigencia = mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
							//#codigoPais#,#codigoPeriodo#,#numeroPeriodo#
														
							if(log.isDebugEnabled())
							{
								log.debug("campanyaInicioVigencia: " + campanyaInicioVigencia);
								log.debug("campanyaFinVigencia: " + campanyaFinVigencia);
							}
															
							criteria.put("codigoNivel", map.get("codigoNivel"));
							criteria.put("campanyaInicioVigencia",campanyaInicioVigencia);
							criteria.put("campanyaFinVigencia",campanyaFinVigencia);
							mantenimientoCOMRegistrarConsultoraMasivoDAO.insertNivelesConsultoras(criteria);

							//Sumamos el periodo para que haga el calculo denuevo
							criteria.put("codigoPeriodo",campanyaInicioVigencia);
							//Campaa de Proceso -1
							criteria.put("numeroPeriodo", 1);// Lo que se va a restar o sumar en el periodo
							periodo = mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
						}
					}
				}
			}else {
				//Actualiza
				
				//actualiza los datos de la consultora CUP_CONSU_NUEVA
				mantenimientoCOMRegistrarConsultoraMasivoDAO.updateConsultoraNuevas(criteria);
				
				//Actualiza los cupones utilizados (CUP_CONSU_CUPON) por la consultora 
				mantenimientoCOMRegistrarConsultoraMasivoDAO.updateConsultoraCupon(criteria);
				
				//Actualiza la entidad  de Facturacin (CUP_CONSU_FACTU) para la Consultora y Programa Asociado
				mantenimientoCOMRegistrarConsultoraMasivoDAO.updateFacturacionPrograma(criteria);
				
				criteria.put("numeroVigenciaPrograma", numeroVigenciaPrograma);
				
				//actualiza los registros de la entidad Niveles de Consultora(CUP_CONSU_NIVEL) 
				//Nota: No se consider Niveles, ya que en el documento no indica de donde sacar el nivel para la condicional
				//Si es COD_CONST = 0 o 1
				if (indicadorConstancia == 0) {
					//COD_CONST = 0
					
					//Campaa de Proceso -1
					criteria.put("numeroPeriodo", -1);// Lo que se va a restar o sumar en el periodo
					String campanyaInicioVigencia = 
							mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
					
					//CAM_INIC_VIG + (Numero de Vigencia-1)
					criteria.put("numeroPeriodo", numeroVigenciaPrograma -1); // Lo que se va a restar o sumar en el periodo
					criteria.put("codigoPeriodo",campanyaInicioVigencia);
					String campanyaFinVigencia =
							mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
					
					criteria.put("campanyaInicioVigencia",campanyaInicioVigencia);
					criteria.put("campanyaFinVigencia",campanyaFinVigencia);
					
					mantenimientoCOMRegistrarConsultoraMasivoDAO.updateNivelesConsultoras(criteria);
					
				}else {
					//COD_CONST = 1
					//Indicador de Constancia = 2 El sistema 
					log.debug("insertanto Niveles de Consultas, cuando el indicador es 0");
					
					listaMatriz = mantenimientoCOMRegistrarConsultoraMasivoDAO.getListaMatrizEquivalente(criteria);
					
					String periodo = (String)criteria.get("codigoPeriodo");
					
					if (listaMatriz!= null && listaMatriz.size() > 0) {
						for (int j = 0; j < listaMatriz.size(); j++) {
							Map map = new HashMap(); 
							map = (Map)listaMatriz.get(j);
							//#codigoPais#,#codigoPeriodo#,#numeroPeriodo#
							
							if (j == 1)	contador = 0;
							
							criteria.put("codigoPeriodo",periodo);
							//Campaa de Proceso -1
							criteria.put("numeroPeriodo", (contador));// Lo que se va a restar o sumar en el periodo
							String campanyaInicioVigencia = 
									mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
							//#codigoPais#,#codigoPeriodo#,#numeroPeriodo#
							
							//CAM_INIC_VIG + (Numero de Vigencia-1)
							criteria.put("numeroPeriodo", (numeroVigenciaPrograma-1) + (contador)); // Lo que se va a restar o sumar en el periodo
							//criteria.put("codigoPeriodo",campanyaInicioVigencia);
							String campanyaFinVigencia =
									mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
							//#codigoPais#,#codigoPeriodo#,#numeroPeriodo#
							
							criteria.put("codigoNivel", map.get("codigoNivel"));
							criteria.put("campanyaInicioVigencia",campanyaInicioVigencia);
							criteria.put("campanyaFinVigencia",campanyaFinVigencia);
							
							//Existen niveles
							if (!mantenimientoCOMRegistrarConsultoraMasivoDAO.existeNivelConsultora(criteria)) {
								//Resultado = false => Inserto
								mantenimientoCOMRegistrarConsultoraMasivoDAO.insertNivelesConsultoras(criteria);
							}else {
								//Resultado = true => Actualizo
								mantenimientoCOMRegistrarConsultoraMasivoDAO.updateNivelesConsultoras(criteria);
							}
							
							//Sumamos el periodo para que haga el calculo denuevo
							criteria.put("codigoPeriodo",campanyaInicioVigencia);
							//Campaa de Proceso -1
							criteria.put("numeroPeriodo", 1);// Lo que se va a restar o sumar en el periodo
							periodo = mantenimientoCOMRegistrarConsultoraMasivoDAO.getPeriodoNSiguiente(criteria);
						}
					}
				}
			}
		}
	}
	
}