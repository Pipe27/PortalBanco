package co.edu.usbcali.banco.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.dto.ResultadoDTO;

@RestController
@RequestMapping("/operaciones")
public class OperacionesMatematicas {
	
	@GetMapping(value="/sumar/{numeroUno}/{numeroDos}")
	public ResultadoDTO sumar(@PathVariable("numeroUno") Integer num1, @PathVariable("numeroDos") Integer num2) {
		ResultadoDTO result = new ResultadoDTO();
		result.setValor(num1+num2);
		return result;
	}
	
	@GetMapping(value="/restar/{numeroUno}/{numeroDos}")
	public ResultadoDTO restar(@PathVariable("numeroUno") Integer num1, @PathVariable("numeroDos") Integer num2) {
		ResultadoDTO result = new ResultadoDTO();
		result.setValor(num1-num2);
		return result;
	}
}