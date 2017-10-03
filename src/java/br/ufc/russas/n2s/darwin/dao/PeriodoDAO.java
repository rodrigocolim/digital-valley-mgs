/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.dao;

import br.ufc.russas.n2s.darwin.model.Periodo;
import java.util.List;

/**
 *
 * @author Wallison Carlos
 */
public interface PeriodoDAO {
    public void adicionaPeriodo(Periodo periodo);
    public void atualizaPeriodo(Periodo periodo);
    public void removePeriodo(Periodo periodo);
    public List<Periodo> listaPeriodos();
    public Periodo getPeriodo(long codPeriodo);
}
