/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Periodo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Lavínia Matoso
 */
public class PeriodoBeans implements Beans{

    private long codPeriodo;
    private LocalDate inicio;
    private LocalDate termino;
    
    public PeriodoBeans(){}
    public PeriodoBeans(long codPeriodo, LocalDate inicio, LocalDate termino) {
        this.codPeriodo = codPeriodo;
        this.inicio = inicio;
        this.termino = termino;
    }
     
    public long getCodPeriodo() {
        return codPeriodo;
    }

    public void setCodPeriodo(long codPeriodo) {
        this.codPeriodo = codPeriodo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        this.termino = termino;
    }
    
    @Override
    public Object toBusiness() {
        Periodo periodo = new Periodo();
        
        if(this.getCodPeriodo() > 0){
            periodo.setCodPeriodo(this.getCodPeriodo());
        }
        periodo.setInicio(this.getInicio());
        periodo.setTermino(this.getTermino());
        
        return periodo;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Periodo){
                Periodo periodo = (Periodo) object;
                this.setCodPeriodo(periodo.getCodPeriodo());
                this.setInicio(periodo.getInicio());
                this.setTermino(periodo.getTermino());
                
                return this;
            }else{
                throw new IllegalArgumentException("O objeto a ser adicionado não é um Periodo!");
            }
        }else{
            throw new NullPointerException("Periodo não pode ser nula!");
        }
        
    }
    
    
}
