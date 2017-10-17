/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller.config;

import br.ufc.russas.n2s.darwin.controller.IndexController;
import br.ufc.russas.n2s.darwin.dao.DAOImpl;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOImpl;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *
 * @author Wallison Carlos
 */
@Configuration

@ComponentScan(basePackages = {"br.ufc.russas.n2s.darwin"})
public class WebConfig {
    /*@Bean
    public SelecaoServiceIfc selecaoServiceIfc(){
    return new SelecaoServiceImpl();
    }
    @Bean
    public IndexController indexController(){
    return new IndexController();
    }
    @Bean
    public DAOImpl dao(){
    return new DAOImpl();
    }*/
}
