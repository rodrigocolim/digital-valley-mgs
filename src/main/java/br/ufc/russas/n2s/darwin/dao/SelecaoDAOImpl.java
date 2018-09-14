/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.util.List;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
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
    
    
    
    /*    @Override
    public List<Selecao> getMinhasSelecoes(UsuarioDarwin responsavel){
    Session session;
    session = this.daoImpl.getSessionFactory().openSession();
    Transaction t = session.beginTransaction();
    Criteria cr = session.createCriteria(Selecao.class);
    cr.add(Restrictions.eq("responsaveis.usuario", responsavel.getCodUsuario()));
    List<Selecao> selecoes = cr.list();
    t.commit();
    return selecoes;
    }*/
    

}
