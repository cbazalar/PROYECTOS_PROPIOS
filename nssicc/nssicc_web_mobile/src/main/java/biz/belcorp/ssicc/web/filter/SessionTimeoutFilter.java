package biz.belcorp.ssicc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ivan Tocto Jaimes
 * 
 */
public class SessionTimeoutFilter implements Filter {

	protected transient final Log log = LogFactory.getLog(getClass());
	
	private String timeoutPage = "index.xhtml";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		if ((request instanceof HttpServletRequest)
				&& (response instanceof HttpServletResponse)) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;

			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			// verificamos si es necesario controlar el timeout para este request
			if (isSessionControlRequiredForThisResource(httpServletRequest)) {

				// Verificamos si la sesion es inválida
				if (isSessionInvalid(httpServletRequest)) {

					String timeoutUrl = httpServletRequest.getContextPath() + "/" + getTimeoutPage();
					
					log.debug("La sesion es inválida!, redireccionando a la pagina de login: " + timeoutUrl);
					
					httpServletResponse.sendRedirect(timeoutUrl);

					return;
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * Verifica si es necesario chekear el timeout del request actual
	 * @param httpServletRequest
	 * @return
	 */
	private boolean isSessionControlRequiredForThisResource(
			HttpServletRequest httpServletRequest) {

		String requestPath = httpServletRequest.getRequestURI();

		boolean controlRequired = !StringUtils.contains(requestPath, getTimeoutPage());

		return controlRequired;

	}

	/**
	 * Verifica si la sesion es válida
	 * @param httpServletRequest
	 * @return
	 */
	private boolean isSessionInvalid(HttpServletRequest httpServletRequest) {

		boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null)

				&& !httpServletRequest.isRequestedSessionIdValid();

		return sessionInValid;

	}

	/**
	 * @return the timeoutPage
	 */
	public String getTimeoutPage() {
		return timeoutPage;
	}

	/**
	 * @param timeoutPage the timeoutPage to set
	 */
	public void setTimeoutPage(String timeoutPage) {
		this.timeoutPage = timeoutPage;
	}	
}
