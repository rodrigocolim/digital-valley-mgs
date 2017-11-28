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
public class EtapaProxy extends Etapa{
    
    private UsuarioDarwin usuario;
    
    public EtapaProxy(UsuarioDarwin usuario) {
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
    
    public void avalia(Etapa etapa, Avaliacao avaliacao) throws IllegalAccessException{
        if (this.getUsuario().getPermissoes().contains(EnumPermissoes.AVALIADOR) && etapa.isAvaliador(usuario)) {
            etapa.avalia(avaliacao);
        } else {
            throw new IllegalAccessException("Você não é uma valiador da etapa ".concat(etapa.getTitulo()));
        }
    }
}
