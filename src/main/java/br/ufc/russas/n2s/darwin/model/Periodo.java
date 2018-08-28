/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;


/**
 *
 * @author N2S-PC03
 */
@Converter(autoApply = true)
@Entity
@Table(name = "periodo")
public class Periodo implements AttributeConverter<LocalDate, Date> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codPeriodo")
    private long codPeriodo;
    @Column(name = "inicio")
    private LocalDate inicio;
    @Column(name = "termino")
    private LocalDate termino;

    public Periodo() {
    }

    public Periodo(long codPeriodo, LocalDate inicio, LocalDate termino) {
        setCodPeriodo(codPeriodo);
        setInicio(inicio);
        setTermino(termino);
    }
    public Periodo(LocalDate inicio, LocalDate termino) {
        setInicio(inicio);
        setTermino(termino);
    }
    public long getCodPeriodo() {
        return codPeriodo;
    }

    public void setCodPeriodo(long codPeriodo) {
        if (codPeriodo > 0) {
            this.codPeriodo = codPeriodo;
        } else {
            throw new IllegalCodeException("Código do periodo deve ser maior que zero!");
        }
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        if (inicio != null) {
            if (this.getTermino()!= null) {
                if ((inicio.isBefore(this.getTermino()) || inicio.equals(this.getTermino())) && inicio.isAfter(LocalDate.now())) {
                    this.inicio = inicio;
                } else {
                    throw new IllegalArgumentException("Inicio não pode ser maior que o termino!");
                }
            } else {
                this.inicio = inicio;
            }
        } else {
            throw new NullPointerException("Inicio de periodo não pode ser nulo!");
        }
    }

    public LocalDate getTermino() {
        return termino;
    }

    public void setTermino(LocalDate termino) {
        if (termino != null) {
            if (this.getInicio()!= null) {
                if ((termino.isAfter(this.getInicio()) || termino.equals(this.getInicio()))) {
                    this.termino = termino;
                } else {
                    throw new IllegalArgumentException("Termino não pode ser menor que o inicio!");
                }
            } else {
                this.termino = termino;
            }
        } else {
            throw new NullPointerException("Termino de periodo não pode ser nulo!");
        }
    }

    @Override
    public Date convertToDatabaseColumn(LocalDate attribute) {
        return (attribute == null ? null : Date.valueOf(attribute));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date dbData) {
        return (dbData == null ? null : dbData.toLocalDate());
    }

    public final boolean isColide(final Periodo periodo){
        //começa depois do inicio e termina antes do fim
        if ((periodo.getInicio().isAfter(this.getInicio()) || periodo.getInicio().isEqual(this.getInicio())) && (periodo.getInicio().isBefore(this.getTermino()) || periodo.getInicio().isEqual(this.getTermino()))) {
            return true;
        }
        //começa antes do inicio e termina depois do inicio
        if ((periodo.getTermino().isAfter(this.getInicio()) || periodo.getTermino().isEqual(this.getInicio())) && (periodo.getTermino().isBefore(this.getTermino()) || periodo.getTermino().isEqual(this.getTermino()))) {
            return true;
        }
        //começa antes do fim e termina depois
        if ((periodo.getInicio().isBefore(this.getTermino()) || periodo.getInicio().isEqual(this.getTermino())) && (periodo.getTermino().isAfter(this.getTermino()) || periodo.getTermino().isEqual(this.getTermino()))) {
            return true;
        }
        //começa antes do inicio e termina depois do fim
        if ((periodo.getInicio().isBefore(this.getInicio()) || periodo.getInicio().isEqual(this.getInicio())) && (periodo.getTermino().isAfter(this.getTermino()) || periodo.getTermino().isEqual(this.getTermino()))) {
            return true;
        }
        return false;
    }

    public boolean isAntes(Periodo periodo) {
        return this.getTermino().isBefore(periodo.getInicio());
    }

    public boolean isDepois(Periodo periodo) {
        return this.getInicio().isAfter(periodo.getTermino());
    }

    public Duration getDuracao() {
        return Duration.between(inicio, termino);
    }

    public Duration getTempoOcorrido() {
        return Duration.between(inicio, LocalDateTime.now());
    }

    public Duration getTempoRestante() {
        return Duration.between(LocalDateTime.now(), termino);
    }

}
