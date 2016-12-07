package com.lvg.tusers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class TUsersExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex) {
		return getCustomModelAndView(viewName, ex, null);
	}

	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		return getCustomModelAndView(viewName, ex, request);
	}

	private ModelAndView getCustomModelAndView(String viewName, Exception ex, HttpServletRequest request) {
		ModelAndView mv = super.getModelAndView(viewName, ex);
		String cause = "No cause!";
		
		if (ex.getCause() != null && ex.getCause() instanceof Exception){
			cause = printStackTrace((Exception)ex.getCause());
			
		}else{
			cause = printStackTrace(ex);
		}
		
		mv.addObject("errorMessage", ex.getMessage());
		mv.addObject("exception", cause);
		return mv;
	}
	
	private String printStackTrace(Exception ex){
		StringBuilder sb = new StringBuilder();
		for(StackTraceElement stackEl: ex.getStackTrace()){
			sb.append(stackEl.toString()).append("\n");
		}
		
		return sb.toString();
	}

}
