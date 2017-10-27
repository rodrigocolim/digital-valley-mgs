/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import br.ufc.russas.n2s.darwin.model.exception.IllegalCodeException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Lavínia Matoso
 */
@Entity
@Table(name = "participante")
@Converter(autoApply = true)
public class Participante implements AttributeConverter<LocalDateTime, Timestamp>, Serializable{
    @Id
    @Column(name = "codParticipante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codParticipante;
    @ManyToOne
    @JoinColumn(name="candidato", referencedColumnName="codUsuario")
    private Usuario candidato;
    @ManyToMany(targetEntity = Documentacao.class)
    @JoinTable(name="documentacoes_participante", joinColumns = {@JoinColumn(name="participante", referencedColumnName = "codParticipante")},
            inverseJoinColumns = {@JoinColumn(name="documentacao", referencedColumnName = "codDocumentacao")})
    private List<Documentacao> documentacao;
    private boolean deferido;
    @Column(name = "dataInscricao")
    private LocalDateTime data;
    private boolean notificado;

    public long getCodParticipante() {
        return codParticipante;
    }

    public void setCodParticipante(long codParticipante) {
        if(codParticipante>0){
            this.codParticipante = codParticipante;
        }else{
            throw new IllegalCodeException("Código de participante deve ser maior que zero!");
        }
    }

    public Usuario getCandidato() {
        return candidato;
    }

    public void setCandidato(Usuario candidato) {
        if(candidato != null){
           this.candidato = candidato;
        }else{
            throw new NullPointerException("Deve ser informado um candidato!");
        }
    }

    public List<Documentacao> getDocumentacao() {
        return documentacao;
    }

    public void setDocumentacao(List<Documentacao> documentacao) {
        this.documentacao = documentacao;
    }

    public boolean isDeferido() {
        return deferido;
    }

    public void setDeferido(boolean deferido) {
        this.deferido = deferido;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        if(data !=null){
            this.data = data;
        }else{
            throw new NullPointerException("Data não pode ser nula!");
        }
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        if (attribute == null) {
            return null;
        } else {
            return Timestamp.valueOf(attribute);
        }
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbDate) {
        if (dbDate == null) {
            return null;
        } else {
            return dbDate.toLocalDateTime();
        }
    }

}
