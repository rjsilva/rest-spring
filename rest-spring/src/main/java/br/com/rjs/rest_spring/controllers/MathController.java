package br.com.rjs.rest_spring.controllers;

import br.com.rjs.rest_spring.model.MathModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ){
        if(!MathModel.isNumeric(numberOne) || !MathModel.isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return MathModel.sum(numberOne, numberTwo);
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!MathModel.isNumeric(numberOne) || !MathModel.isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return MathModel.subtraction(numberOne, numberTwo);
    }

    @GetMapping("/mul/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!MathModel.isNumeric(numberOne) || !MathModel.isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return MathModel.multiplication(numberOne, numberTwo);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double division(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!MathModel.isNumeric(numberOne) || !MathModel.isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return MathModel.division(numberOne, numberTwo);
    }

    @GetMapping("/med/{numberOne}/{numberTwo}")
    public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!MathModel.isNumeric(numberOne) || !MathModel.isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return MathModel.average(numberOne, numberTwo);
    }

    @GetMapping("/squ/{numberOne}")
    public int squarRoot(@PathVariable("numberOne") String number){
        if(!MathModel.isNumeric(number)) throw new UnsupportedOperationException("Digite apenas números");
        return MathModel.calculateSquareRoot(number);
    }

}
