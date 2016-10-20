package biz.belcorp.ssicc.service.sisicc.framework;


/**
 * Service de ejecucin de las Interfaces SiSiCC, este Service funciona como
 * interface entre la capa web (invocado por el
 * <code>BaseInterfazAbstractAction</code>) y las implementaciones de los
 * services especficos de cada interfaz.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
public interface InterfazBaseCompuestaService {

	/**
	 * @param codigo
	 * @return
	 */
	public SSiCC_Desfasado_BaseInterfazHiloAbstractService getInterfazImplementation(String codigo);
}