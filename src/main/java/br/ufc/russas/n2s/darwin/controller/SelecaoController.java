package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.UsuarioBeans;
import br.ufc.russas.n2s.darwin.model.EnumPermissoes;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@Controller("selecaoController")
@RequestMapping(value = "/selecao")
public class SelecaoController {
    
    private SelecaoServiceIfc selecaoServiceIfc;
    
    @Autowired(required = true)
    public void setSelecaoServiceIfc(@Qualifier("selecaoServiceIfc")SelecaoServiceIfc selecaoServiceIfc){
        this.selecaoServiceIfc = selecaoServiceIfc;
    }

    @RequestMapping(value = "/{codSelecao}", method = RequestMethod.GET)
    public String getIndex(@PathVariable long codSelecao, Model model){
        SelecaoBeans selecao = selecaoServiceIfc.getSelecao(codSelecao);
        ArrayList<EtapaBeans> etapas = new ArrayList<>();
        selecao.setEtapas(etapas);
        EtapaBeans inscricao = new EtapaBeans();
        inscricao.setTitulo("Inscrição");
        inscricao.setDescricao("Poderíamos criar um novo JSP com uma mensagem de confirmação da remoção, mas usualmente isso não costuma ser bom, porque precisaríamos navegar até a lista das tarefas novamente caso tenhamos que remover outra tarefa.");
        inscricao.setCodEtapa(11);
        inscricao.setPeriodo(new PeriodoBeans(codSelecao, LocalDate.now(), LocalDate.now()));
        selecao.setInscricao(inscricao);
        EtapaBeans e1 = new EtapaBeans();
        e1.setTitulo("Entrevista");
        e1.setDescricao("Poderíamos criar um novo JSP com uma mensagem de confirmação da remoção, mas usualmente isso não costuma ser bom, porque precisaríamos navegar até a lista das tarefas novamente caso tenhamos que remover outra tarefa.");
        e1.setCodEtapa(11);
        e1.setPeriodo(new PeriodoBeans(codSelecao, LocalDate.now(), LocalDate.now()));
        selecao.getEtapas().add(inscricao);
        selecao.getEtapas().add(e1);
        UsuarioBeans u = new UsuarioBeans();
        u.setCodUsuario(12);
        ArrayList<EnumPermissoes> as = new ArrayList<>();
        as.add(EnumPermissoes.PARTICIPANTE);
        as.add(EnumPermissoes.RESPONSAVEL);
        as.add(EnumPermissoes.AVALIADOR);
        u.setPermissoes(as);
        for(EnumPermissoes permissao: as){
            model.addAttribute(permissao.name(), u);
        }
        model.addAttribute("selecao", selecao);
        return "selecao";
    }
}
