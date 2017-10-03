/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wallison Carlos
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController {
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getIndex(){
        return "redirect:index";
    }
}
