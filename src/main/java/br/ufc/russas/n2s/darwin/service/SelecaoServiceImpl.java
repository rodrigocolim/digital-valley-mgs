/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOIfc;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Participante;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.SelecaoProxy;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import java.time.LocalDate;
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
public class SelecaoServiceImpl implements SelecaoServiceIfc {

    private SelecaoDAOIfc selecaoDAOIfc;
    private UsuarioBeans usuario;
    
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
    public SelecaoBeans adicionaSelecao(SelecaoBeans selecao) throws IllegalAccessException {
        UsuarioDarwin usuario = (UsuarioDarwin) this.usuario.toBusiness();
        SelecaoProxy sp = new SelecaoProxy(usuario);
        Selecao s = getSelecaoDAOIfc().adicionaSelecao(sp.adicionaSelecao((Selecao) selecao.toBusiness()));
        return (SelecaoBeans) selecao.toBeans(s);
    }

    @Override
    public SelecaoBeans atualizaSelecao(SelecaoBeans selecao) throws IllegalAccessException{
        UsuarioDarwin usuario = (UsuarioDarwin) this.usuario.toBusiness();
        SelecaoProxy sp = new SelecaoProxy(usuario);
        Selecao s = getSelecaoDAOIfc().atualizaSelecao(sp.atualizaSelecao((Selecao) selecao.toBusiness()));
        return (SelecaoBeans) new SelecaoBeans().toBeans(s);
    }

    @Override
    public void removeSelecao(SelecaoBeans selecao) {
        this.getSelecaoDAOIfc().adicionaSelecao((Selecao) selecao.toBusiness());
    }

    @Override
    @Transactional
    public List<SelecaoBeans> listaTodasSelecoes() {
        Selecao selecao = new Selecao();
        selecao.setDivulgada(true);
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        System.out.println(resultado.size());
        for (Selecao s : resultado) {
            if (s.getInscricao() != null) {
                System.out.println("\n\n\n\n");
                System.out.println(s.getEstado());
                System.out.println(s.getEstado().execute(s));
                System.out.println("\n\n\n\n");
                if (s.getEstado().execute(s).compareTo(s.getEstado()) != 0) {
                    this.atualizaEstado(s, s.getEstado().execute(s));
                }
            }
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
        }
        return selecoes;
    }
    
    
    @Override
    @Transactional
    public List<SelecaoBeans> listaSelecoes(Selecao selecao) {
        selecao.setDivulgada(true);
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        System.out.println(resultado.size());
        for (Selecao s : resultado) {
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
        }
        SelecaoBeans aux;
        List<SelecaoBeans> selecoesSemPeriodo = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        for(int i=0;i<selecoes.size()-1;i++){
            for(int j=i;j<selecoes.size()-1;j++){
                if (selecoes.get(j).getInscricao() != null && selecoes.get(j+1).getInscricao() != null) {
                    if (selecoes.get(j).getInscricao().getPeriodo().getInicio().isAfter(selecoes.get(j+1).getInscricao().getPeriodo().getInicio())) {
                        aux = selecoes.get(j);
                        selecoes.set(j, selecoes.get(j+1));
                        selecoes.set(j+1, aux);
                    } else {
                        break;
                    }
                }
            }
        }
        return selecoes;
    }

    @Override
    @Transactional(readOnly = true)
    public SelecaoBeans getSelecao(long codSelecao) {
       Selecao selecao = new Selecao();
       selecao.setCodSelecao(codSelecao);
       return (SelecaoBeans) new SelecaoBeans().toBeans(this.getSelecaoDAOIfc().getSelecao(selecao));
    }

    @Override
    public void setUsuario(UsuarioBeans usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public List<SelecaoBeans> listaSelecoesAssociada(UsuarioBeans usuario) {
        Selecao selecao = new Selecao();
        UsuarioDarwin user = (UsuarioDarwin) usuario.toBusiness();
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList());
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        for (Selecao s : resultado) {
            if (s.getResponsaveis().contains(user)) {
                selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
            }
        }
        return selecoes;
    }


    @Override
    public EtapaBeans getEtapaAtual(SelecaoBeans selecao) {
        Selecao s = (Selecao) selecao.toBusiness();
        Etapa etapa = s.getEtapaAtual();
        if (etapa != null) {
            return (EtapaBeans) new EtapaBeans().toBeans(etapa);
        } else {
            return null;
        }
    }
    
    private Selecao atualizaEstado(Selecao selecao, EnumEstadoSelecao estado) {
        selecao.setEstado(estado);
        return this.selecaoDAOIfc.atualizaSelecao(selecao);
    }

}
