/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Etapa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wallison Carlos
 */
@Repository("etapaDAOIfc")
@Transactional
public class EtapaDAOImpl implements EtapaDAOIfc{

    private DAOIfc<Etapa> daoImpl;

    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Etapa> dao){
        this.daoImpl = dao;
    }

    @Override
    public void adicionaEtapa(Etapa etapa) {
        this.daoImpl.adiciona(etapa);
    }

    @Override
    public void atualizaEtapa(Etapa etapa) {
        this.daoImpl.atualiza(etapa);
    }

    @Override
    public void removeEtapa(Etapa etapa) {
        this.daoImpl.remove(etapa);
    }

    @Override
    public List<Etapa> listaEtapas() {
        return this.daoImpl.lista(Etapa.class).list();
    }

    @Override
    public Etapa getEtapa(long codEtapa) {
        return this.daoImpl.getObject(Etapa.class, codEtapa);
    }
}
