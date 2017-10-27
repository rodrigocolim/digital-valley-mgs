/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Participante;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author N2S-PC03
 */
public class ParticipanteDAOImpl implements ParticipanteDAOIfc{

    private DAOIfc<Participante> daoImpl;
    
    @Autowired
    public void setDAOIfc(@Qualifier("daoImpl")DAOIfc<Participante> dao){
        this.daoImpl = dao;
    }
    
    
    @Override
    public void adicionaParticipante(Participante participante) {
        this.daoImpl.adiciona(participante);
    }

    @Override
    public void atualizaParticipante(Participante participante) {
        this.daoImpl.atualiza(participante);
    }

    @Override
    public void removeParticipante(Participante participante) {
        this.daoImpl.remove(participante);
    }

    @Override
    public List<Participante> listaParticipantes() {
        return this.daoImpl.lista(Participante.class).list();
    }

    @Override
    public Participante getParticipante(long codigo) {
        return this.daoImpl.getObject(Participante.class, codigo);
    }
    
}
