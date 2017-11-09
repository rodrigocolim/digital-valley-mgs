/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Periodo;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wallison Carlos
 */
public interface PeriodoDAOIfc{

    /**
     * Método resposável por fazer a persistência de um período.
     * @param periodo
     */
    void adicionaPeriodo(Periodo  periodo);

    /**
     * Método resposável por fazer a atualização de um período.
     * @param periodo
     */
    void atualizaPeriodo(Periodo  periodo);

    /**
     * Método resposável por fazer a remoção de um período.
     * @param periodo
     */
    void removePeriodo(Periodo  periodo);

    /**
     * Método resposável por fazer a listagem de todos os períodos.
     * @return List<Periodo>
     */
    List<Periodo> listaPeriodos(Periodo  periodo);

    /**
     * Método resposável por pegar do banco de dados um
     * período a partir do código informadao.
     * @param codPeriodo
     * @return Periodo
     */
    Periodo getPeriodo(Periodo  periodo);
}
