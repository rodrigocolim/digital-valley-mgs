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
public class SelecaoProxy extends Selecao{
    
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
    
    public Etapa adicionaEtapa(Selecao selecao, Etapa etapa) throws IllegalAccessException {
        if (this.getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL) && selecao.getResponsaveis().contains(this.getUsuario())) {
            return selecao.adicionaEtapa(etapa);
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
    
    public Etapa atualizaEtapa(Selecao selecao, Etapa etapa) throws IllegalAccessException {
        if (this.getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL) && selecao.getResponsaveis().contains(this.getUsuario())) {
            return selecao.atualizaEtapa(etapa);
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
     
    public Selecao adicionaSelecao(Selecao selecao) throws IllegalAccessException {
        if (this.getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL)) {
            return selecao.adicionaSelecao();
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
    
    public Selecao atualizaSelecao(Selecao selecao) throws IllegalAccessException {
        if (this.getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL)) {
            return selecao.atualizaSelecao();
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
    
    public void adicionaResponsavel(Selecao selecao, UsuarioDarwin responsavel) throws IllegalAccessException {
        if (getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL) && selecao.isResponsavel(getUsuario())) {
            selecao.adicionaResponsavel(responsavel);
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
    
    public void removeResponsavel(Selecao selecao, UsuarioDarwin responsavel) throws IllegalAccessException {
        if (getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL) && selecao.isResponsavel(getUsuario())) {
            selecao.removeResponsavel(responsavel);
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
    
    public void removeEtapa(Selecao selecao, Etapa etapa) throws IllegalAccessException {
        if (getUsuario().getPermissoes().contains(EnumPermissoes.RESPONSAVEL) && selecao.isResponsavel(getUsuario())) {
            selecao.removeEtapa(etapa);
        } else {
            throw new IllegalAccessException("Você não é um responsável da selação ".concat(selecao.getTitulo()));
        }
    }
    
}
