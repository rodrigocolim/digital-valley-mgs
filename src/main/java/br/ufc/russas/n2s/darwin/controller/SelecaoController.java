    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.controller;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.PeriodoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import br.ufc.russas.n2s.darwin.service.SelecaoServiceIfc;
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
        selecao.setInscricao(inscricao);
        EtapaBeans e1 = new EtapaBeans();
        e1.setTitulo("Entrevista");
        e1.setDescricao("Poderíamos criar um novo JSP com uma mensagem de confirmação da remoção, mas usualmente isso não costuma ser bom, porque precisaríamos navegar até a lista das tarefas novamente caso tenhamos que remover outra tarefa. Seria muito mais agradável para o usuário que ele fosse redirecionado direto para a lista das tarefas. Uma das formas que poderíamos fazer esse redirecionamento é enviar o usuário diretamente para a página que lista as tarefas (tarefa/lista.jsp). Mas, essa não é uma boa abordagem, porque precisaríamos, outra vez, disponibilizar a lista das tarefas para o JSP, algo que já fazemos na ação de listar as tarefas, o método lista na classe TarefasController. Já que o método lista faz esse trabalho, poderíamos, ao invés de redirecionar a execução para o JSP, enviá-la para essa ação. Para isso, o retorno do método deve ser um pouco modificado. Vamos continuar devolvendo uma String mas essa String deve indicar que queremos chamar uma outra ação. Podemos fazer um redirecionamento na lado do servidor (forward) ou pelo navegador,");
        e1.setCodEtapa(11);
        e1.setPeriodo(new PeriodoBeans(codSelecao, LocalDateTime.now(), LocalDateTime.now()));
        selecao.getEtapas().add(inscricao);
        selecao.getEtapas().add(e1);
        
        model.addAttribute("selecao", selecao);
        return "selecao";
    }
}
