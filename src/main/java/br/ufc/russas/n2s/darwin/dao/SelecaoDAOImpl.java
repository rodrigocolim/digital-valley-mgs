/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Selecao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author N2S-PC03
 */
@Repository("selecaoDAOIfc")
@Transactional
public class SelecaoDAOImpl implements SelecaoDAOIfc {

    private DAOIfc<Selecao> daoImpl;

    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Selecao> dao){
        this.daoImpl = dao;
    }

    @Override
    public Selecao adicionaSelecao(Selecao selecao) {
        Selecao s = this.daoImpl.adiciona(selecao);
        System.out.println("TITULO: "+s.getEdital().getArquivo().getAbsolutePath());
        return s;
    }

    @Override
    public Selecao atualizaSelecao(Selecao selecao) {
        return this.daoImpl.atualiza(selecao);
    }

    @Override
    public void removeSelecao(Selecao selecao) {
        this.daoImpl.remove(selecao);
    }

    @Override
    public List<Selecao> listaSelecoes(Selecao selecao) {
        return this.daoImpl.lista(selecao);
    }

    @Override
    public Selecao getSelecao(Selecao selecao) {
        return this.daoImpl.getObject(selecao, selecao.getCodSelecao());
    }

}
