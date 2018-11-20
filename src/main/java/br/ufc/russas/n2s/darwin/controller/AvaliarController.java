/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.AvaliacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.Email;
import br.ufc.russas.n2s.darwin.model.EnumCriterioDeAvaliacao;
import br.ufc.russas.n2s.darwin.model.EnumEstadoAvaliacao;
import br.ufc.russas.n2s.darwin.model.Log;
import br.ufc.russas.n2s.darwin.model.Selecao;
import br.ufc.russas.n2s.darwin.model.UsuarioDarwin;
import br.ufc.russas.n2s.darwin.service.EtapaServiceIfc;
import br.ufc.russas.n2s.darwin.service.LogServiceIfc;
import br.ufc.russas.n2s.darwin.service.ParticipanteServiceIfc;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Wallison Carlos
 */
@Controller("avaliarController")
@RequestMapping("/avaliar")
public class AvaliarController {
    
    private EtapaServiceIfc etapaServiceIfc;
    private ParticipanteServiceIfc participanteServiceIfc;
    private LogServiceIfc logServiceIfc;
    
    @Autowired(required = true)
    public void setEtapaServiceIfc(@Qualifier("etapaServiceIfc") EtapaServiceIfc etapaServiceIfc) {
        this.etapaServiceIfc = etapaServiceIfc;
    }
    
    @Autowired(required = true)
    public void setParticipanteServiceIfc(@Qualifier("participanteServiceIfc") ParticipanteServiceIfc participanteServiceIfc) {
        this.participanteServiceIfc = participanteServiceIfc;
    }
    public LogServiceIfc getLogServiceIfc() {
    	return logServiceIfc;
    }
    @Autowired(required = true)
    public void setLogServiceIfc(@Qualifier("logServiceIfc") LogServiceIfc logServiceIfc) {
    	this.logServiceIfc = logServiceIfc;
    }  
    
    @RequestMapping(value = "/{codEtapa}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codEtapa, Model model, HttpServletRequest request) {
        EtapaBeans etapa = etapaServiceIfc.getEtapa(codEtapa);
        HttpSession session = request.getSession();
        UsuarioBeans avaliador = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        if (etapa.getAvaliadores().contains(avaliador)) {
	        model.addAttribute("etapa", etapa);
	        model.addAttribute("participantesEtapa", etapa.getParticipantes());
	        model.addAttribute("avaliador", avaliador);
	        return "avaliar";
        } else {return "error/404";}
    }
    
    @RequestMapping(value = "/inscricao/{codEtapa}", method = RequestMethod.GET)
    public String getIndexInscricao(@PathVariable long codEtapa, Model model, HttpServletRequest request) {
        EtapaBeans etapa = etapaServiceIfc.getEtapa(codEtapa);
        HttpSession session = request.getSession();
        UsuarioBeans avaliador = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        if (etapa.getAvaliadores().contains(avaliador)) {
	        model.addAttribute("avaliador", avaliador);
	        model.addAttribute("etapa", etapa);
	        model.addAttribute("participantesEtapa", etapa.getParticipantes());
	        return "avaliar";
        } else {return "error/404";}
    }
    
    @RequestMapping(value = "/inscricao/{codEtapa}", method = RequestMethod.POST)
    public String avaliarInscricao(@PathVariable long codEtapa, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UsuarioBeans avaliador = (UsuarioBeans) session.getAttribute("usuarioDarwin");
        EtapaBeans etapa = null;
        try {
            etapa = etapaServiceIfc.getEtapa(codEtapa);
            AvaliacaoBeans avaliacao = new AvaliacaoBeans();
            if (etapa.getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.APROVACAO) {
                avaliacao.setAprovado((Integer.parseInt(request.getParameter("aprovacao")) == 1));
            } else if (etapa.getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.DEFERIMENTO) {
                avaliacao.setAprovado((Integer.parseInt(request.getParameter("deferimento")) == 1));
            } else if (etapa.getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.NOTA) {
                float nota = Float.parseFloat(request.getParameter("nota"));
                avaliacao.setNota(nota);
                if (nota >= etapa.getNotaMinima()) {
                    avaliacao.setAprovado(true);
                } else {
                    avaliacao.setAprovado(false);
                }
            }
            
            
            avaliacao.setObservacao(request.getParameter("observacoes"));
            ParticipanteBeans participante = participanteServiceIfc.getParticipante(Long.parseLong(request.getParameter("participante")));
            avaliacao.setParticipante(participante);
            avaliacao.setAvaliador(avaliador);
            avaliacao.setEstado(EnumEstadoAvaliacao.AVALIADO);
            etapaServiceIfc.setUsuario(avaliador);
            etapaServiceIfc.avalia(etapa, avaliacao);
            etapa = this.etapaServiceIfc.getEtapa(etapa.getCodEtapa());
            this.getLogServiceIfc().adicionaLog(new Log(LocalDate.now(),(UsuarioDarwin) avaliador.toBusiness(), (Selecao) ((SelecaoBeans) session.getAttribute("selecao")).toBusiness(), "O(A) usuario(a) "+ avaliador.getNome()+" realizou a avaliação do candidato detentor do CPF: "+participante.getCandidato().getCPF()+" na etapa "+etapa.getTitulo()+" da seleção "+((SelecaoBeans) session.getAttribute("selecao")).getTitulo()+" em "+LocalDate.now()+"."));
            model.addAttribute("etapa", etapa);
            model.addAttribute("avaliador", avaliador);
            model.addAttribute("participantesEtapa", etapa.getParticipantes());
            model.addAttribute("mensagem", "Participante avaliado com sucesso!");
            model.addAttribute("status", "success");
            return "avaliar";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("avaliador", avaliador);
            model.addAttribute("participantesEtapa", etapa.getParticipantes());
            model.addAttribute("status", "danger");
            return "avaliar";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", "Isso não é um número!");
            model.addAttribute("avaliador", avaliador);
            model.addAttribute("participantesEtapa", etapa.getParticipantes());
            model.addAttribute("status", "danger");
            return "avaliar";
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("avaliador", avaliador);
            model.addAttribute("participantesEtapa", etapa.getParticipantes());
            model.addAttribute("status", "danger");
            return "avaliar";
        }  catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("avaliador", avaliador);
            model.addAttribute("participantesEtapa", etapa.getParticipantes());
            model.addAttribute("status", "danger");
            return "avaliar";
        }
    }
    
    @RequestMapping(value = "/{codEtapa}", method = RequestMethod.POST)
    public String avaliarEtapa(@PathVariable long codEtapa, HttpServletRequest request, Model model) {
        try {
            EtapaBeans etapa = etapaServiceIfc.getEtapa(codEtapa);
            AvaliacaoBeans avaliacao = new AvaliacaoBeans();
            if (etapa.getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.APROVACAO) {
                avaliacao.setAprovado((Integer.parseInt(request.getParameter("aprovacao")) == 1));
            } else if (etapa.getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.DEFERIMENTO) {
                avaliacao.setAprovado((Integer.parseInt(request.getParameter("deferimento")) == 1));
            } else if (etapa.getCriterioDeAvaliacao() == EnumCriterioDeAvaliacao.NOTA) {
                float nota = Float.parseFloat(request.getParameter("nota"));
                avaliacao.setNota(nota);
                if (nota >= etapa.getNotaMinima()) {
                    avaliacao.setAprovado(true);
                } else {
                    avaliacao.setAprovado(false);
                }
            }
            
            HttpSession session = request.getSession();
            UsuarioBeans avaliador = (UsuarioBeans) session.getAttribute("usuarioDarwin");
            avaliacao.setObservacao(request.getParameter("observacoes"));
            ParticipanteBeans participante = participanteServiceIfc.getParticipante(Long.parseLong(request.getParameter("participante")));
            avaliacao.setParticipante(participante);
            avaliacao.setAvaliador(avaliador);
            avaliacao.setEstado(EnumEstadoAvaliacao.AVALIADO);
            etapaServiceIfc.setUsuario(avaliador);
            etapaServiceIfc.avalia(etapa, avaliacao);
            //etapa = this.etapaServiceIfc.getEtapa(etapa.getCodEtapa());            
            //model.addAttribute("etapa", etapa);
            //model.addAttribute("participantesEtapa", etapa.getParticipantes());
            session.setAttribute("mensagem", "Participante avaliado com sucesso!");
            session.setAttribute("status", "success");
            return "redirect: /Darwin/avaliar/"+etapa.getCodEtapa();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "avaliar";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", "Isso não é um número!");
            model.addAttribute("status", "danger");
            return "avaliar";
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "avaliar";
        }  catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensagem", e.getMessage());
            model.addAttribute("status", "danger");
            return "avaliar";
        }
    }
    
}
