/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

/**
 *
 * @author Lavínia Matoso
 */
public interface Beans{

    /**
     *
     * @return Object
     */
    Object toBusiness();

    /**
     *
     * @param object
     * object - Um objeto de negócio qualquer para ser convertido para uma
     * instância do tipo Beans
     * @return Beans
     */
    Beans toBeans(Object object);

}
