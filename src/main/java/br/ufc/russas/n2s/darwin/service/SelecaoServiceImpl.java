/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOIfc;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wallison Carlos
 */
@Service("selecaoServiceIfc")
@Transactional
public class SelecaoServiceImpl implements SelecaoServiceIfc {

    private SelecaoDAOIfc selecaoDAOIfc;

    public SelecaoServiceImpl(){
    }

    public SelecaoDAOIfc getSelecaoDAOIfc() {
        return selecaoDAOIfc;
    }

    @Autowired(required = true)
    public void setSelecaoDAOIfc(@Qualifier("selecaoDAOIfc")SelecaoDAOIfc selecaoDAOIfc) {
        this.selecaoDAOIfc = selecaoDAOIfc;
    }

    @Override
    public SelecaoBeans adicionaSelecao(SelecaoBeans selecao) {
        return (SelecaoBeans) new SelecaoBeans().toBeans(this.getSelecaoDAOIfc().adicionaSelecao((Selecao) selecao.toBusiness()));
    }

    @Override
    public SelecaoBeans atualizaSelecao(SelecaoBeans selecao){
        return (SelecaoBeans) new SelecaoBeans().toBeans(this.getSelecaoDAOIfc().atualizaSelecao((Selecao) selecao.toBusiness()));
    }

    @Override
    public void removeSelecao(SelecaoBeans selecao) {
        this.getSelecaoDAOIfc().adicionaSelecao((Selecao) selecao.toBusiness());
    }

    @Override
    public List<SelecaoBeans> listaNovasSelecoes() {
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        Selecao selecao = new Selecao();
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        for (Selecao s : resultado) {
            if (s.getInscricao() != null) {
                if (s.getInscricao().getPeriodo().getInicio().isAfter(LocalDateTime.now())) {
                    selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
                }
            }
        }
        return selecoes;
    }

    @Override
    @Transactional
    public List<SelecaoBeans> listaTodasSelecoes() {
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        Selecao selecao = new Selecao();
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        System.out.println(resultado.size());
        for (Selecao s : resultado) {
            System.out.println(s.getTitulo());
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
        }
        return selecoes;
    }

    @Override
    @Transactional(readOnly = true)
    public SelecaoBeans getSelecao(long codSelecao) {
<<<<<<< HEAD
        Selecao selecao = new Selecao();
        selecao.setCodSelecao(codSelecao);
       return (SelecaoBeans) new SelecaoBeans().toBeans(this.getSelecaoDAOIfc().getSelecao(selecao));
=======
       Selecao selecao = this.getSelecaoDAOIfc().getSelecao(codSelecao);
       return (SelecaoBeans) new SelecaoBeans().toBeans(selecao);
>>>>>>> b7b6a5a500dd02507394c94ded36395de7c416f9
    }

}
