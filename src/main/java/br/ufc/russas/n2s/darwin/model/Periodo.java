/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author N2S-PC03
 */
@Converter(autoApply = true)
@Entity
@Table(name="periodo")
public class Periodo implements AttributeConverter<LocalDateTime, Timestamp>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codPeriodo")
    private long codPeriodo;
    @Column(name="inicio")
    private LocalDateTime inicio;
    @Column(name="termino")
    private LocalDateTime termino;
   
    public Periodo(){
    }

    public Periodo(long codPeriodo, LocalDateTime inicio, LocalDateTime termino){
        setCodPeriodo(codPeriodo);
        setInicio(inicio);
        setTermino(termino);
    }
    public Periodo(LocalDateTime inicio, LocalDateTime termino){
        setInicio(inicio);
        setTermino(termino);
    }
    public long getCodPeriodo() {
        return codPeriodo;
    }

    public void setCodPeriodo(long codPeriodo) {
        if(codPeriodo > 0){
            this.codPeriodo = codPeriodo;
        }else{
            throw new IllegalCodeException("Código do periodo deve ser maior que zero!");
        }
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        if(inicio != null){
            if(this.getTermino()!= null){
                if((inicio.isBefore(this.getTermino()) || inicio.equals(this.getTermino()))){
                    this.inicio = inicio;
                }else{
                    throw new IllegalArgumentException("Inicio não pode ser maior que o termino!");
                }
            }else{
                this.inicio = inicio;
            }
        }else{
            throw new NullPointerException("Inicio de periodo não pode ser nulo!");
        }
    }

    public LocalDateTime getTermino() {
        return termino;
    }

    public void setTermino(LocalDateTime termino) {
        if(termino != null){
            if(this.getInicio()!= null){
                if((termino.isAfter(this.getInicio()) || termino.equals(this.getInicio()))){
                    this.termino = termino;
                }else{
                    throw new IllegalArgumentException("Termino não pode ser menor que o inicio!");
                }
            }else{
                this.termino = termino;
            }
        }else{
            throw new NullPointerException("Termino de periodo não pode ser nulo!");
        }
    }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return (attribute == null ? null : Timestamp.valueOf(attribute));
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return (dbData == null ? null : dbData.toLocalDateTime());
    }

    public boolean isColide(Periodo periodo){
        //começa depois do inicio e termina antes do fim        
        if((periodo.getInicio().isAfter(this.getInicio()) || periodo.getInicio().isEqual(this.getInicio())) && (periodo.getInicio().isBefore(this.getTermino()) || periodo.getInicio().isEqual(this.getTermino()))){
            return true;
        }
        //começa antes do inicio e termina depois do inicio
        if((periodo.getTermino().isAfter(this.getInicio()) || periodo.getTermino().isEqual(this.getInicio())) && (periodo.getTermino().isBefore(this.getTermino()) || periodo.getTermino().isEqual(this.getTermino()))){
            return true;
        }
        //começa antes do fim e termina depois
        if((periodo.getInicio().isBefore(this.getTermino()) || periodo.getInicio().isEqual(this.getTermino())) && (periodo.getTermino().isAfter(this.getTermino()) || periodo.getTermino().isEqual(this.getTermino()))){
            return true;
        }
        //começa antes do inicio e termina depois do fim
        if((periodo.getInicio().isBefore(this.getInicio()) || periodo.getInicio().isEqual(this.getInicio())) && (periodo.getTermino().isAfter(this.getTermino()) || periodo.getTermino().isEqual(this.getTermino()))){
            return true;
        }
        return false;
    }

    public boolean isAntes(Periodo periodo){
        return this.getTermino().isBefore(periodo.getInicio());
    }
    
    public boolean isDepois(Periodo periodo){
        return this.getInicio().isAfter(periodo.getTermino());
    }
    
    public Duration getDuracao(){
        return Duration.between(inicio, termino);
    }
    
    public Duration getTempoOcorrido(){
        return Duration.between(inicio, LocalDateTime.now());
    }
    
    public Duration getTempoRestante(){
        return Duration.between(LocalDateTime.now(), termino);
    }

}
