package biz.belcorp.ssicc.service.sisicc.util.exception;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * @deprecated
 */
public class NoDataFoundException extends RuntimeException {
	public NoDataFoundException() {
		super();
	}

	public NoDataFoundException(String message) {
		super(message);
	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
