/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
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
    public List<Participante> listaParticipantes(Participante participante) {
        return this.daoImpl.lista(participante);
    }

    @Override
    public Participante getParticipante(Participante participante) {
        return this.daoImpl.getObject(participante, participante.getCodParticipante());
    }
    @Override
    public List<Participante> listaParticipantesPorEtapa(int codEtapa) {
    	Session session = this.daoImpl.getSessionFactory().openSession();
    	Transaction t = session.beginTransaction();
    	try {
    		List<Participante> list = (List<Participante>) session.createQuery("from participante where etapa = ?").setInteger(0, codEtapa).list();
			List<Participante> participantes = list;
            t.commit();
            return participantes;
    	} catch (Exception e) {
			t.rollback();
			throw e;
		} finally {
			session.close();
		}
    }

}
