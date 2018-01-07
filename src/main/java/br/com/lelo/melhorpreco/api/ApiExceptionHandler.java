package br.com.lelo.melhorpreco.api;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lelo.melhorpreco.view.ErroView;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErroView handleException(Exception ex) {
		ex.printStackTrace();
		return new ErroView(ex.getMessage());
	}

}
