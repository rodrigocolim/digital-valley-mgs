/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface UsuarioDAOIfc {
    /**
     * Método resposável por fazer a persistência de uma etapa.
     * @param etapa
     */
    UsuarioDarwin adicionaUsuario(UsuarioDarwin usuario);

    /**
     * Método resposável por fazer a atualização de uma etapa.
     * @param etapa
     */
    UsuarioDarwin atualizaUsuario(UsuarioDarwin usuario);

    /**
     * Método resposável por fazer a remoção de uma etapa.
     * @param etapa
     */
    void removeUsuario(UsuarioDarwin usuario);

    /**
     * Método resposável por fazer a listagem de todas as etapas.
     * @return List<Etapa>
     */
    List<UsuarioDarwin> listaUsuarios(UsuarioDarwin usuario);

    /**
     * Método resposável por pegar do banco de dados uma etapa a partir do
     * código informado.
     * @param codEtapa
     * @return Etapa
     */
    UsuarioDarwin getUsuario(UsuarioDarwin usuario);
    
    /**
     * Método resposável por pegar do banco de dados uma etapa a partir do
     * código informado.
     * @param codEtapa
     * @return Etapa
     */
    UsuarioDarwin getUsuarioControleDeAcesso(UsuarioDarwin usuario);
}
