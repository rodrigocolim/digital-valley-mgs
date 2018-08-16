/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.service;

import br.ufc.russas.n2s.darwin.beans.DocumentacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.InscricaoBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.dao.EtapaDAOIfc;
import br.ufc.russas.n2s.darwin.dao.SelecaoDAOIfc;
import br.ufc.russas.n2s.darwin.model.Documentacao;
import br.ufc.russas.n2s.darwin.model.EnumEstadoSelecao;
import br.ufc.russas.n2s.darwin.model.Etapa;
import br.ufc.russas.n2s.darwin.model.Inscricao;
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
    private EtapaDAOIfc etapaDAOIfc;
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
    
    @Autowired(required = true)
    public void setEtapaDAOIfc(@Qualifier("etapaDAOIfc")EtapaDAOIfc etapaDAOIfc) {
        this.etapaDAOIfc = etapaDAOIfc;
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
        for (Selecao s : resultado) {
            if (s.getInscricao() != null) {
                s.getInscricao().setEstado(s.getInscricao().getEstado().execute(s.getInscricao()));
                etapaDAOIfc.atualizaEtapa(s.getInscricao());
                if (s.getEstado().execute(s).compareTo(s.getEstado()) != 0) {
                    this.atualizaEstado(s, s.getEstado().execute(s));
                }
                for (Etapa etapa : s.getEtapas()) {
                    etapa.setEstado(etapa.getEstado().execute(etapa));
                    etapaDAOIfc.atualizaEtapa(etapa);
                }
            }
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
        }
        return this.ordenaSelecoesPorData(selecoes);
    }
    
    
    
    @Override
    @Transactional
    public List<SelecaoBeans> listaSelecoes(Selecao selecao) {
        selecao.setDivulgada(true);
        List<SelecaoBeans> selecoes = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<Selecao> resultado = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        for (Selecao s : resultado) {
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
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
        List<Selecao> resultadoNaoDivulgadas = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        List<SelecaoBeans> resultadoDivulgadas = this.listaTodasSelecoes();
    	
        for (Selecao s : resultadoNaoDivulgadas) {
        	Inscricao i = s.getInscricao();
            if (s.getResponsaveis().contains(user) || (i != null && i.isCanditado((UsuarioDarwin)usuario.toBusiness()))) {
                selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
            } 
        }
        for (SelecaoBeans s : resultadoDivulgadas) {        	
        	Inscricao i = (Inscricao) s.getInscricao().toBusiness();
            if (s.getResponsaveis().contains(usuario) || (i != null && i.isCanditado((UsuarioDarwin)usuario.toBusiness()))) {
                selecoes.add(s);
            }
        }
               
        return this.ordenaSelecoesPorData(selecoes);
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

    @Override
    public List<SelecaoBeans> ordenaSelecoesPorData(List<SelecaoBeans> selecoes) {
        List<SelecaoBeans> selecoesSemDatas = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<SelecaoBeans> selecoesFinalizadas = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<SelecaoBeans> selecoesEmAndamento = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<SelecaoBeans> selecoesEmEspera = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        int p=0;
        while( p<selecoes.size()) {
            if (selecoes.get(p).getInscricao() == null) {
                selecoesSemDatas.add(selecoes.get(p));
            } else if (selecoes.get(p).getEstado().compareTo(EnumEstadoSelecao.ANDAMENTO) == 0) {
                selecoesEmAndamento.add(selecoes.get(p));
            } else if (selecoes.get(p).getEstado().compareTo(EnumEstadoSelecao.ESPERA) == 0) {
                selecoesEmEspera.add(selecoes.get(p));
            } else if (selecoes.get(p).getEstado().compareTo(EnumEstadoSelecao.FINALIZADA) == 0) {
                selecoesFinalizadas.add(selecoes.get(p));
            }
            p++;
        }
        selecoes.removeAll(selecoesFinalizadas);
        selecoes.removeAll(selecoesSemDatas);
        selecoes.removeAll(selecoesEmEspera);
        selecoes.removeAll(selecoesEmAndamento);
        selecoes = this.ordena(selecoes);
        selecoes.addAll(this.ordena(selecoesEmEspera));
        selecoes.addAll(this.ordena(selecoesEmAndamento));
        selecoes.addAll(this.ordena(selecoesFinalizadas));
        selecoes.addAll(selecoesSemDatas);
        return selecoes;
    }
    
    private List<SelecaoBeans> ordena(List<SelecaoBeans> selecoes) {
        SelecaoBeans aux;
         for (int i=0;i<selecoes.size()-1;i++) {
            for (int j=i;j<selecoes.size()-1;j++) {
                if (selecoes.get(j).getInscricao().getPeriodo().getInicio().isAfter(selecoes.get(j+1).getInscricao().getPeriodo().getInicio())) {
                    aux = selecoes.get(j);
                    selecoes.set(j, selecoes.get(j+1));
                    selecoes.set(j+1, aux);
                }
            }
        }
        return selecoes;
    }
    
    @Override
    public EtapaBeans getUltimaEtapa(SelecaoBeans selecao) {
        Selecao s = (Selecao) selecao.toBusiness();
        Etapa etapa = s.getUltimaEtapa();
        if (etapa != null) {
            return (EtapaBeans) new EtapaBeans().toBeans(etapa);
        } else {
            return null;
        }
    }
}
=======
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
import br.ufc.russas.n2s.darwin.dao.EtapaDAOIfc;
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
    private EtapaDAOIfc etapaDAOIfc;
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
    
    @Autowired(required = true)
    public void setEtapaDAOIfc(@Qualifier("etapaDAOIfc")EtapaDAOIfc etapaDAOIfc) {
        this.etapaDAOIfc = etapaDAOIfc;
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
        for (Selecao s : resultado) {
            if (s.getInscricao() != null) {
                s.getInscricao().setEstado(s.getInscricao().getEstado().execute(s.getInscricao()));
                etapaDAOIfc.atualizaEtapa(s.getInscricao());
                if (s.getEstado().execute(s).compareTo(s.getEstado()) != 0) {
                    this.atualizaEstado(s, s.getEstado().execute(s));
                }
                for (Etapa etapa : s.getEtapas()) {
                    etapa.setEstado(etapa.getEstado().execute(etapa));
                    etapaDAOIfc.atualizaEtapa(etapa);
                }
            }
            selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
        }
        return this.ordenaSelecoesPorData(selecoes);
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
        List<Selecao> resultadoNaoDivulgadas = this.getSelecaoDAOIfc().listaSelecoes(selecao);
        List<SelecaoBeans> resultadoDivulgadas = this.listaTodasSelecoes();
        for (Selecao s : resultadoNaoDivulgadas) {
            if (s.getResponsaveis().contains(user)) {
                selecoes.add((SelecaoBeans) new SelecaoBeans().toBeans(s));
            } 
        }
        for (SelecaoBeans s : resultadoDivulgadas) {
            if (s.getResponsaveis().contains(usuario)) {
                selecoes.add(s);
            }
        }
       
        return this.ordenaSelecoesPorData(selecoes);
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

    @Override
    public List<SelecaoBeans> ordenaSelecoesPorData(List<SelecaoBeans> selecoes) {
        List<SelecaoBeans> selecoesSemDatas = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<SelecaoBeans> selecoesFinalizadas = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<SelecaoBeans> selecoesEmAndamento = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        List<SelecaoBeans> selecoesEmEspera = Collections.synchronizedList(new ArrayList<SelecaoBeans>());
        int p=0;
        while( p<selecoes.size()) {
            if (selecoes.get(p).getInscricao() == null) {
                selecoesSemDatas.add(selecoes.get(p));
            } else if (selecoes.get(p).getEstado().compareTo(EnumEstadoSelecao.ANDAMENTO) == 0) {
                selecoesEmAndamento.add(selecoes.get(p));
            } else if (selecoes.get(p).getEstado().compareTo(EnumEstadoSelecao.ESPERA) == 0) {
                selecoesEmEspera.add(selecoes.get(p));
            } else if (selecoes.get(p).getEstado().compareTo(EnumEstadoSelecao.FINALIZADA) == 0) {
                selecoesFinalizadas.add(selecoes.get(p));
            }
            p++;
        }
        selecoes.removeAll(selecoesFinalizadas);
        selecoes.removeAll(selecoesSemDatas);
        selecoes.removeAll(selecoesEmEspera);
        selecoes.removeAll(selecoesEmAndamento);
        selecoes = this.ordena(selecoes);
        selecoes.addAll(this.ordena(selecoesEmEspera));
        selecoes.addAll(this.ordena(selecoesEmAndamento));
        selecoes.addAll(this.ordena(selecoesFinalizadas));
        selecoes.addAll(selecoesSemDatas);
        return selecoes;
    }
    
    private List<SelecaoBeans> ordena(List<SelecaoBeans> selecoes) {
        SelecaoBeans aux;
         for (int i=0;i<selecoes.size()-1;i++) {
            for (int j=i;j<selecoes.size()-1;j++) {
                if (selecoes.get(j).getInscricao().getPeriodo().getInicio().isAfter(selecoes.get(j+1).getInscricao().getPeriodo().getInicio())) {
                    aux = selecoes.get(j);
                    selecoes.set(j, selecoes.get(j+1));
                    selecoes.set(j+1, aux);
                }
            }
        }
        return selecoes;
    }
    
    @Override
    public EtapaBeans getUltimaEtapa(SelecaoBeans selecao) {
        Selecao s = (Selecao) selecao.toBusiness();
        Etapa etapa = s.getUltimaEtapa();
        if (etapa != null) {
            return (EtapaBeans) new EtapaBeans().toBeans(etapa);
        } else {
            return null;
        }
    }
}
