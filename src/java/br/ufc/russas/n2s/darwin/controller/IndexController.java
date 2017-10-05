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
<<<<<<< HEAD
 * @author Wallison Carlos
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController {
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getIndex(){
        return "redirect:index";
    }
=======
 * @author Alex Felipe
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void teste(){
        System.out.println("kaslhdakslhda");
    }     
>>>>>>> 515a014c3d4826ce66a3141f2eaf445b983cbb9e
}
