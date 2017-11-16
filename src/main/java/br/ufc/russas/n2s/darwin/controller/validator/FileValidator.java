/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller.validator;
import br.ufc.russas.n2s.darwin.controller.File;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/**
 *
 * @author Wallison Carlos
 */

public class FileValidator implements Validator {

    public boolean supports(Class<?> paramClass) {
        return File.class.equals(paramClass);
    }
    
    public void validate(Object obj, Errors errors) {
        File file = (File) obj;
          if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "valid.file");
          }
    }

}
