/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.beans;

import br.ufc.russas.n2s.darwin.model.Arquivo;
import java.io.File;
import java.time.LocalDateTime;

/**
 *
 * @author Lavínia Matoso
 */
public class ArquivoBeans implements Beans {

    private long codArquivo;
    private String titulo;
    private File arquivo;
    private LocalDateTime data;

    public long getCodArquivo() {
        return codArquivo;
    }

    public void setCodArquivo(long codArquivo) {
        this.codArquivo = codArquivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public Object toBusiness() {
        Arquivo arq = new Arquivo();
        if (this.getCodArquivo() > 0) {
            arq.setCodArquivo(this.getCodArquivo());
        }
        arq.setTitulo(this.getTitulo());
        arq.setData(this.getData());
        arq.setArquivo(this.getArquivo());
        return arq;
    }

    @Override
    public Beans toBeans(Object object) {
        if (object != null) {
            if (object instanceof Arquivo) {
                Arquivo arq = (Arquivo) object;
                this.setCodArquivo(arq.getCodArquivo());
                this.setTitulo(arq.getTitulo());
                this.setData(arq.getData());
                this.setArquivo(arq.getArquivo());
                return this;
            } else {
                throw new IllegalArgumentException("O objeto a ser adicionado não é um Período!");
            }
        } else {
            throw new NullPointerException("Período não pode ser nula!");
        }
    }

}
