/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.dao.EtapaDAOIfc;
import br.ufc.russas.n2s.darwin.model.Etapa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Wallison Carlos
 */
public class EtapaServiceImpl implements EtapaServiceIfc{

    private EtapaDAOIfc etapaDAOIfc;
    
    public EtapaDAOIfc getEtapaDAOIfc(){
        return etapaDAOIfc;
    }
    
    @Autowired(required = true)
    public void setEtapaDAOIfc(@Qualifier("etapaDAOIfc")EtapaDAOIfc etapaDAOIfc){
        this.etapaDAOIfc = etapaDAOIfc;
    }
    
    
    @Override
    public void adicionaEtapa(EtapaBeans etapa) {
        this.getEtapaDAOIfc().adicionaEtapa((Etapa) etapa.toBusiness());
    }

    @Override
    public void atualizaEtapa(EtapaBeans etapa) {
        this.getEtapaDAOIfc().atualizaEtapa((Etapa) etapa.toBusiness());
    }

    @Override
    public void removeEtapa(EtapaBeans etapa) {
        this.getEtapaDAOIfc().removeEtapa((Etapa) etapa.toBusiness());
    }

    @Override
    public List<EtapaBeans> listaTodasEtapas() {
        List<EtapaBeans> etapas = Collections.synchronizedList(new ArrayList<EtapaBeans>());
        List<Etapa> result = this.getEtapaDAOIfc().listaEtapas();
        for(Etapa etapa : result){
            etapas.add((EtapaBeans) new EtapaBeans().toBeans(etapa));
        }
        return etapas;
    }

    @Override
    public EtapaBeans getEtapa(long codEtapa) {
        return (EtapaBeans) new EtapaBeans().toBeans(this.getEtapaDAOIfc().getEtapa(codEtapa));
    }
    
}
