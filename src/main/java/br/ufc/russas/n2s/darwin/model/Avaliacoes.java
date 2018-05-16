/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author N2S-PC03
 */
@Entity
@Table(name = "avaliacoes")
public class Avaliacoes implements Serializable {
    @Id
    @Column(name = "etapa")
    private long etapa;
    @Column(name = "avaliacao")
    private long avaliacao;
   
    public long getEtapa() {
		return etapa;
	}
    
    public void setEtapa(long etapa) {
		this.etapa = etapa;
	}
    
   public long getAvaliacao() {
	   return avaliacao;
   }
   public void setAvaliacao(long avaliacao) {
	   this.avaliacao = avaliacao;
   }
   
}
