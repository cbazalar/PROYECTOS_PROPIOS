package biz.belcorp.ssicc.service.sisicc.util.exception;

/**
 * @author pecbazalar
 *
 */
public class StoringFileOverFtpException extends RuntimeException {
	public StoringFileOverFtpException() {
		super();
	}

	public StoringFileOverFtpException(String message) {
		super(message);
	}

	public StoringFileOverFtpException(String message, Throwable cause) {
		super(message, cause);
	}
}
