package biz.belcorp.ssicc.dao.scsicc.bean;

/**
 * @author pecbazalar
 *
 */
public class ConexionBean {


	private String driver;
	private String url;
	private String db;	
	private String user;
	private String password;
	private String jndi;
	
	
	
	public void setJndi(String jndi)
	{
		this.jndi=jndi;
	}
	
	public String getJndi()
	{
		return jndi;
	}
	
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	

	
	
	
	
	
	
	
	

}
