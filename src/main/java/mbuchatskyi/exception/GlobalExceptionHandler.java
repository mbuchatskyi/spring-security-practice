package mbuchatskyi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView entityNotFoundExceptionHandler(HttpServletRequest request, EntityNotFoundException exception) {

		ModelAndView modelAndView = new ModelAndView("error/500");
		modelAndView.addObject("code", HttpStatus.INTERNAL_SERVER_ERROR);
		modelAndView.addObject("info", exception.getMessage());
		return modelAndView;
	}

	@ExceptionHandler(NullEntityReferenceException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView nullEntityReferenceExceptionHandler(HttpServletRequest request,
															NullEntityReferenceException exception) {
		ModelAndView modelAndView = new ModelAndView("error/500");
		modelAndView.addObject("code", HttpStatus.INTERNAL_SERVER_ERROR);
		modelAndView.addObject("info", exception.getMessage());
		return modelAndView;
	}
}