/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOIfc;
import br.ufc.russas.n2s.darwin.model.Selecao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class SelecaoServiceImpl implements SelecaoServiceIfc{
    
    private SelecaoDAOIfc selecaoDAOIfc;
    
    public SelecaoServiceImpl(){}
    
    public SelecaoDAOIfc getSelecaoDAOIfc(){
        return selecaoDAOIfc;
    }
    
    @Autowired(required = true)
    public void setSelecaoDAOIfc(@Qualifier("selecaoDAOIfc")SelecaoDAOIfc selecaoDAOIfc){
        this.selecaoDAOIfc = selecaoDAOIfc;
    }
    
    @Override
    public void adicionaSelecao(SelecaoBeans selecao){
        this.getSelecaoDAOIfc().adicionaSelecao((Selecao) selecao.toBusiness());
    }

    @Override
    public List<SelecaoBeans> listaNovasSelecoes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SelecaoBeans> listaTodasSelecoes() {
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList<SelecaoBeans>());        
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes();
        for(Selecao s : resultado){
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
        }
        return selecoes;
    }
}
