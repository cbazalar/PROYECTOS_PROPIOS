package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.men.ProcesoMENGenerarMensajesDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaCUVRecuperarSiguienteSemanaService;

/**
 * @author Ivan Tocto
 *
 */
@Service("spusicc.procesoPEDCargaCUVRecuperarSiguienteSemanaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDCargaCUVRecuperarSiguienteSemanaServiceImpl extends BaseService implements ProcesoPEDCargaCUVRecuperarSiguienteSemanaService {

	@Resource(name="spusicc.procesoMENGenerarMensajesDAO")
	private ProcesoMENGenerarMensajesDAO procesoMENGenerarMensajesDAO;
	
	@Resource(name="spusicc.procesoPEDCargaCUVRecuperarSiguienteSemanaDAO")
	private ProcesoPEDCargaCUVRecuperarSiguienteSemanaDAO procesoPEDCargaCUVRecuperarSiguienteSemanaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaCUVRecuperarSiguienteSemanaService#insertCargaCUVRecuperarSiguienteSemana(java.util.Map)
	 */
	public void insertCargaCUVRecuperarSiguienteSemana(Map params) {
		this.procesoPEDCargaCUVRecuperarSiguienteSemanaDAO.insertCargaCUVRecuperarSiguienteSemana(params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaCUVRecuperarSiguienteSemanaService#executeValidarCargaCUVRecuperarSiguienteSemana(java.util.Map)
	 */
	public Map executeValidarCargaCUVRecuperarSiguienteSemana(Map criteria) throws Exception {
		List listMapFila = new ArrayList();
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais = (String)criteria.get("codigoPais");
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		
		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPeriodo", codigoPeriodo);
		criteriaAux.put("codigoPeriodoSiguiente", codigoPeriodo);
		
		
		List listResultado = new ArrayList();
		int fila=0;
		int totalValidos = 0;
		int totalErrores = 0;
		File tempFile = new File(directorioTemporal, nombreArchivo);
		LineIterator lineIterator = null;
		String codigoCUV = null;
		
		lineIterator = FileUtils.lineIterator(tempFile, null);
		while (lineIterator.hasNext()) {
			String mensajeError = "";
			codigoCUV = lineIterator.nextLine();
			fila++;
			Map params = new HashMap();
			params.put("codigoCUV", codigoCUV);
			params.put("codigoUsuario", usuario.getLogin());
			params.put("validarOK","OK");
			params.put("numeroFila", fila);
			
			/* Realizando validaciones */
			if (StringUtils.isBlank(codigoCUV)) {
				mensajeError = messageSource.getMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.msg.codigoCUVEnBlanco", 
						null,getLocale(usuario));
				params.put("validarOK","");
				totalErrores++;
			}
			else  {
				criteriaAux.put("codigoCUV", codigoCUV);
				Map producto = procesoMENGenerarMensajesDAO.getDatosProductoCUVPeriodo(criteriaAux);
				if (producto == null) {
					mensajeError = messageSource.getMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.msg.codigoCUVNoExiste",
							null,getLocale(usuario));
					params.put("validarOK","");
					totalErrores++;
				}
				else {
					Map productoPRE = procesoPEDCargaCUVRecuperarSiguienteSemanaDAO.getDatosProductoCUVRecuperarSemana(criteriaAux);
					if (productoPRE != null) {
						mensajeError = messageSource.getMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.msg.codigoCUVRecuperarSemanaExiste",
								null,getLocale(usuario));
						params.put("validarOK","");
						totalErrores++;
					}
					else 
						if (listResultado.size() > 0 ) {
							for (int x= 0; x < listResultado.size(); x ++) {
								Map productoTempo = (Map)listResultado.get(x);
								String codigoCUVTempo = (String)productoTempo.get("codigoCUV");
								if (StringUtils.isNotBlank(codigoCUVTempo)) {
									if (codigoCUVTempo.equals(codigoCUV)) {
										mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVRepetido",
												null,getLocale(usuario));
										params.put("validarOK","");
										totalErrores++;
										break;
									}
								}
							}
						}
				}
				
			}
			params.put("descripcionError", mensajeError);
			listResultado.add(params);
		}
		
		totalValidos = fila - totalErrores;
		Map resultado = new HashMap();
		resultado.put("totalRegistros", String.valueOf(fila));
		resultado.put("totalValidos", String.valueOf(totalValidos));
		resultado.put("totalErrores", String.valueOf(totalErrores));
		
		resultado.put("listResultado", listResultado);
		return resultado;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaCUVRecuperarSiguienteSemanaService#executeValidarCargaCUVUnitarioRecuperarSiguienteSemana(java.util.Map)
	 */
	public Map executeValidarCargaCUVUnitarioRecuperarSiguienteSemana(Map criteria) throws Exception {
		Usuario usuario = (Usuario)criteria.get("usuario");
		String codigoPais = (String)criteria.get("codigoPais");
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String codigoCUV = (String)criteria.get("codigoCUV");
		
		Map criteriaAux = new HashMap();
		criteriaAux.put("codigoPeriodo", codigoPeriodo);
		criteriaAux.put("codigoPeriodoSiguiente", codigoPeriodo);
		
		
		List listResultado = new ArrayList();
		int fila=0;
		int totalValidos = 0;
		int totalErrores = 0;
	
	    String mensajeError = "";
		fila++;
		Map params = new HashMap();
		params.put("codigoCUV", codigoCUV);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("validarOK","OK");
		params.put("numeroFila", fila);
		
		/* Realizando validaciones */
		if (StringUtils.isBlank(codigoCUV)) {
			mensajeError = messageSource.getMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.msg.codigoCUVEnBlanco", 
					null,getLocale(usuario));
			params.put("validarOK","");
			totalErrores++;
		}
		else  {
			criteriaAux.put("codigoCUV", codigoCUV);
			Map producto = procesoMENGenerarMensajesDAO.getDatosProductoCUVPeriodo(criteriaAux);
			if (producto == null) {
				mensajeError = messageSource.getMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.msg.codigoCUVNoExiste",
						null,getLocale(usuario));
				params.put("validarOK","");
				totalErrores++;
			}
			else {
				Map productoPRE = procesoPEDCargaCUVRecuperarSiguienteSemanaDAO.getDatosProductoCUVRecuperarSemana(criteriaAux);
				if (productoPRE != null) {
					mensajeError = messageSource.getMessage("procesoPEDCargaCUVRecuperarSiguienteSemanaForm.msg.codigoCUVRecuperarSemanaExiste",
							null,getLocale(usuario));
					params.put("validarOK","");
					totalErrores++;
				}
				else 
					if (listResultado.size() > 0 ) {
						for (int x= 0; x < listResultado.size(); x ++) {
							Map productoTempo = (Map)listResultado.get(x);
							String codigoCUVTempo = (String)productoTempo.get("codigoCUV");
							if (StringUtils.isNotBlank(codigoCUVTempo)) {
								if (codigoCUVTempo.equals(codigoCUV)) {
									mensajeError = messageSource.getMessage("procesoMENCargaMasivaInformacionMensajesForm.msg.codigoCUVRepetido",
											null,getLocale(usuario));
									params.put("validarOK","");
									totalErrores++;
									break;
								}
							}
						}
					}
			}
			
		}
		params.put("descripcionError", mensajeError);
		listResultado.add(params);
	
		totalValidos = fila - totalErrores;
		Map resultado = new HashMap();
		resultado.put("totalRegistros", String.valueOf(fila));
		resultado.put("totalValidos", String.valueOf(totalValidos));
		resultado.put("totalErrores", String.valueOf(totalErrores));
		
		resultado.put("listResultado", listResultado);
		return resultado;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaCUVRecuperarSiguienteSemanaService#executeActualizarCargaCUVRecuperarSiguienteSemana(java.util.Map)
	 */
	public void executeActualizarCargaCUVRecuperarSiguienteSemana(Map params) {
		List resultados = (ArrayList) params.get("resultados");
		Usuario usuario = (Usuario)params.get("usuario");
		for (int i=0; i < resultados.size(); i++) {
			Map criteria = (HashMap) resultados.get(i);
			String validarOK = (String) criteria.get("validarOK");
			String codigoCUV = (String) criteria.get("codigoCUV");
			String codigoPeriodo = (String) params.get("codigoPeriodo");
			if (StringUtils.equals(validarOK, "OK")) {
				criteria.put("codigoCUV", codigoCUV);
				criteria.put("codigoPeriodo", codigoPeriodo);
				criteria.put("codigoUsuario", usuario.getLogin());
				this.procesoPEDCargaCUVRecuperarSiguienteSemanaDAO.insertCargaCUVRecuperarSiguienteSemana(criteria);
			}
		}
		
	}
}
