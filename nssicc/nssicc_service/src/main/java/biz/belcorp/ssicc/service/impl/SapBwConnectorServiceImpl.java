package biz.belcorp.ssicc.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFieldIterator;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DataProviderException;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class SapBwConnectorServiceImpl extends BaseService implements SapConnectorService{
	
	protected final Log log = LogFactory.getLog(getClass());

	private JCoRepository repository;

	private String repositoryName;

	private String poolName;

	private int maxConnections;

	private String client;

	private String user;

	private String password;

	private String language;

	private String host;

	private String systemNumber;
	
	private JCoDestination pool;
	
	public void initialize() {
		try {
            //JCO.Pool pool = JCO.getClientPoolManager().getPool(poolName)
             // JCoDestination pool = JCoDestinationManager.getDestination(poolName);
            if (log.isDebugEnabled())
                log.debug("Initializing SAP Connector...");
            // Validamos la existencia del pool
            if(pool == null) {
          
            	createPool();
            }     
            pool = JCoDestinationManager.getDestination(poolName);
            repository = pool.getRepository();
//            if(repository == null) {
//                createRepository();
//            }
		} catch (Exception e) {
			log.error("Error initializing SAP Connector: " + e.getMessage());
			e.printStackTrace();
			
		}
	}
    
    private void createPool() {
        log.info("Creating SAP BW Connector pool...");
        if(log.isDebugEnabled()) {
            log.debug("Pool parameters: ");
            log.debug("poolName: " + poolName);
            log.debug("host: " + host);
            log.debug("user: " + user);
            log.debug("password: " + password);
            log.debug("maxConnections: " + maxConnections);
            log.debug("client: " + client);
            log.debug("systemNumber: " + systemNumber);
            log.debug("language: " + language);
        }
        Properties connectProperties = new Properties();
      
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, host);
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  systemNumber);
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, client);
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   user);
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, password);
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   language);
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, String.valueOf(maxConnections));
        
        createDestinationDataFile(poolName,connectProperties);
     
//        JCO.addClientPool(poolName, maxConnections, client, user,
//                        password, language, host, systemNumber);
        log.info("SAP Connector created [OK]");
    }
                
//    private void createRepository() {
//        log.info("Creating SAP Repository...");
//                // Creamos el repositorio en base a los parametros de configuracion
//                repository = JCO.createRepository(repositoryName, poolName);
//                if(repository == null) {
//                    log.warn("Repository couldn't be created  [FAILED].");
//                }
//        log.info("SAP Connector Repository created [OK]");
//	}

	public Map execute(String functionName, Map functionParams,
			String[] outputParams) throws Exception{
		if (log.isDebugEnabled())
			log.debug("Executing SAP Function: " + functionName);
//		JCO.Client client = null;
		Map result = new LinkedHashMap();
		
		try {
			
			initialize();
		    if(this.repository == null) {
                throw new AbapException(
                            "Repository couldn't be created.");
            }
            JCoFunctionTemplate functionTemplate = repository
					.getFunctionTemplate(functionName);

			if (functionTemplate != null) {
//				client = JCO.getClient(poolName);
//                
//                if(client == null) {
//                    throw new JCO.AbapException("No se pudo obtener un cliente del pool");
//                }
                
				//JCO.Function function = functionTemplate.getFunction();
				JCoFunction function = functionTemplate.getFunction();
				// Agregando los ImportParameters
				JCoParameterList importParameters = function
						.getImportParameterList();
				if (importParameters != null && functionParams != null) {
					Iterator it = functionParams.keySet().iterator();
					while (it.hasNext()) {
						String parameterKey = (String) it.next();
						importParameters.setValue(parameterKey,functionParams
								.get(parameterKey));
					}
					log.info("ImportParameters=" + importParameters.toXML());
				}

				function.execute(pool);

				// Agregando los TableParameters
				JCoParameterList tableParameters = function.getTableParameterList();
						
				if (tableParameters != null) {
					log.info("TableParameters=" + tableParameters.toXML());
//					for (int i = 0; i < outputParams.length; i++) {
//						JCoTable table = function.getTableParameterList()
//								.getTable(outputParams[i]);
//						if (table != null) {
//							List tableList = new ArrayList();
//							do {
//								Map rowMap = new LinkedHashMap();
//								for (JCoFieldIterator e = table.getFieldIterator(); e.hasNextField()
//										;) {
//									JCoField field = e.nextField();
//									rowMap.put(field.getName(), field
//											.getValue());
//								}
//								tableList.add(rowMap);
//							} while (table.nextRow());
//							result.put(outputParams[i], tableList);
//						}
//					}
				}

				// Agregando los ExportParameters
				JCoParameterList exportParameters = function.getExportParameterList();
						
				if (exportParameters != null) {
					log.info("ExportParameters=" + exportParameters.toXML());
//					for (int i = 0; i < outputParams.length; i++) {
//						JCoStructure s = exportParameters
//								.getStructure(outputParams[i]);
//						if (s != null) {
//							Map exportParameterMap = new LinkedHashMap();
//							for (JCoFieldIterator e = s.getFieldIterator(); e.hasNextField()
//									;) {
//								JCoField field = e.nextField();
//								exportParameterMap.put(field.getName(), field
//										.getValue());
//							}
//							result.put(outputParams[i], exportParameterMap);
//						}
//					}
				}
				log.info("SAP Function executed: " + functionName);
				
			}
            else {
                throw new AbapException ("No se ha encontrado la función SAP en el repositorio");
            }
        } catch(AbapException jcoae) {
            
            log.error("JCO.AbapException : " + jcoae.getMessage());
            throw jcoae;
        } catch(JCoException jcoe) {
           
            log.error("JCO.Exception : " + jcoe.getMessage());
            throw jcoe;
		}
		return result;
	}

	public void finalize() {
		//Jco.removeClientPool(poolName);
		log.info("SAP Connector pool removed.");
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void createDestinationDataFile(String destinationName,
    		Properties connectProperties)
    {
    		File destCfg = new File(destinationName+".jcoDestination");
    		try
    		{
    			FileOutputStream fos = new FileOutputStream(destCfg,
    					false);
    			connectProperties.store(fos, "SAP BW Connector!");
    			fos.close();
    		}
    		catch (Exception e)
    		{
    			throw new RuntimeException("Unable to create the destination files", e);
    		}
   }
}