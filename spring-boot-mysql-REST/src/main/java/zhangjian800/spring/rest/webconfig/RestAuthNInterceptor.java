package zhangjian800.spring.rest.webconfig;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RestAuthNInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(RestAuthNInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		if(exception != null) {
			logger.info(exception.getMessage());
		}
		logger.info("======afterCompletion================");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		logger.info("======postHandle================");

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("======preHandle================");
		
		String logID = request.getHeader("logID");
		if(StringUtils.isEmpty(logID) || "null".equalsIgnoreCase(logID)) {
			logID = UUID.randomUUID().toString();
			response.addHeader("logID", logID);
		}
		MDC.put("logID", logID);
		
		return true;
	}

}
