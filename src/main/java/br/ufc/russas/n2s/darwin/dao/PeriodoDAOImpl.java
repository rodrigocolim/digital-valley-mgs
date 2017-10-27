/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.util.HibernateUtil;
import br.ufc.russas.n2s.darwin.model.Periodo;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wallison Carlos
 */
public class PeriodoDAOImpl implements PeriodoDAOIfc{
    
    private DAOIfc<Periodo> daoImpl;
    
    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Periodo> dao){
        this.daoImpl = dao;
    }
    
    public PeriodoDAOImpl(){}

    @Override
    public void adicionaPeriodo(Periodo periodo) {
        this.daoImpl.adiciona(periodo);
    }

    @Override
    public void atualizaPeriodo(Periodo periodo) {
        this.daoImpl.atualiza(periodo);
    }

    @Override
    public void removePeriodo(Periodo periodo) {
        this.daoImpl.remove(periodo);
    }

    @Override
    public List<Periodo> listaPeriodos() {
        return this.daoImpl.lista(Periodo.class).list();
    }

    @Override
    public Periodo getPeriodo(long codPeriodo) {
       return this.daoImpl.getObject(Periodo.class, codPeriodo);
    }


}
