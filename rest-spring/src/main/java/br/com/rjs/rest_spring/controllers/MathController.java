package br.com.rjs.rest_spring.controllers;

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
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");

        return convertNumberToDouble(numberOne) + convertNumberToDouble(numberTwo);
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return convertNumberToDouble(numberOne) - convertNumberToDouble(numberTwo);
    }

    @GetMapping("/mul/{numberOne}/{numberTwo}")
    public Double mult(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return convertNumberToDouble(numberOne) * convertNumberToDouble(numberTwo);
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return convertNumberToDouble(numberOne) / convertNumberToDouble(numberTwo);
    }

    @GetMapping("/med/{numberOne}/{numberTwo}")
    public Double med(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo){
        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsupportedOperationException("Digite apenas números");
        return (convertNumberToDouble(numberOne) + convertNumberToDouble(numberTwo))/2;
    }

    @GetMapping("/qua/{numberOne}")
    public int qua(@PathVariable("numberOne") String numberOne){
        if(!isNumeric(numberOne)) throw new UnsupportedOperationException("Digite apenas números");
        Double numero = convertNumberToDouble(numberOne);
        int count = 1;
        Boolean flag = false;
        while(!flag) {
            if(count * count == numero) {
                flag = true;
            } else {
                count = count + 1;
            }
        }
        return (count);
    }

    private Double convertNumberToDouble(String number) {
        if (number == null || number.isEmpty()) throw new UnsupportedOperationException("Digite apenas números");
        String formatedNumber = number.replace(",", ".");
        return Double.parseDouble(formatedNumber);
    }

    private boolean isNumeric(String number) {
        if (number == null || number.isEmpty()) return false;
        String formatedNumber = number.replace(",", ".");
        return formatedNumber.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
