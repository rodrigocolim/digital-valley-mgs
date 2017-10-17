/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.util.HibernateUtil;
import br.ufc.russas.n2s.darwin.model.Periodo;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wallison Carlos
 */
public class PeriodoDAOImpl extends DAOImpl<Periodo> implements PeriodoDAO{
    
    public PeriodoDAOImpl(){}


}
