/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

/**
 *
 * @author Wallison Carlos
 */
public class SelecaoProxy {
    
    private UsuarioDarwin usuario;
    
    public SelecaoProxy(UsuarioDarwin usuario) {
        setUsuario(usuario);
    }

    public UsuarioDarwin getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDarwin usuario) {
        if (usuario != null) {
            this.usuario = usuario;
        } else {
            throw new NullPointerException("Usuário do Darwin não pode ser vazio!");
        }
    }
    
    public void adicionaEtapa(Selecao selecao, Etapa etapa) throws IllegalAccessException {
        if (usuario.getPermissoes().contains(EnumPermissoes.RESPONSAVEL) && selecao.getResponsaveis().contains(this.getUsuario())) {
            selecao.adicionaEtapa(etapa);
        } else {
            throw new IllegalAccessException("Você não é um responsável de ".concat(selecao.getTitulo()));
        }
    }
    
    public Selecao adicionaSelecao(Selecao selecao) throws IllegalAccessException {
        if (usuario.getPermissoes().contains(EnumPermissoes.RESPONSAVEL)) {
            return selecao.adicionaSelecao(selecao);
        } else {
            throw new IllegalAccessException("Você não é um responsável de ".concat(selecao.getTitulo()));
        }
    }
    
    public Selecao atualizaSelecao(Selecao selecao) throws IllegalAccessException {
        if (usuario.getPermissoes().contains(EnumPermissoes.RESPONSAVEL)) {
            return selecao.atualizaSelecao(selecao);
        } else {
            throw new IllegalAccessException("Você não é um responsável de ".concat(selecao.getTitulo()));
        }
    }
    
}
