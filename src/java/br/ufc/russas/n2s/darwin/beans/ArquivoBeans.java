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
public class ArquivoBeans implements Beans{

    private long codArquivo;
    private String titulo;
    private File arquivo;
    private LocalDateTime data;

    public ArquivoBeans(){}
    
    public ArquivoBeans(long codArquivo, String titulo, File arquivo, LocalDateTime data) {
        this.codArquivo = codArquivo;
        this.titulo = titulo;
        this.arquivo = arquivo;
        this.data = data;
    }

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
        Arquivo arquivo = new Arquivo();
        
        if(this.getCodArquivo() > 0){
            arquivo.setCodArquivo(this.getCodArquivo());
        }
        
        arquivo.setTitulo(this.getTitulo());
        arquivo.setData(this.getData());
        arquivo.setArquivo(this.getArquivo());
        
        return arquivo;
    }

    @Override
    public Beans toBeans(Object object) {
        if(object != null){
            if(object instanceof Arquivo){
               Arquivo arquivo = (Arquivo) object;
                this.setCodArquivo(arquivo.getCodArquivo());
                this.setTitulo(arquivo.getTitulo());
                this.setData(arquivo.getData());
                this.setArquivo(arquivo.getArquivo());
                
                return this;
            }else{
                throw new IllegalArgumentException("O objeto a ser adicionado não é um Período!");
            }
        }else{
            throw new NullPointerException("Período não pode ser nula!");
        }
    }
    
}
