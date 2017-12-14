/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufc.russas.n2s.darwin.model;

import java.time.LocalDate;

/**
 *
 * @author Wallison Carlos
 */
public enum EnumEstadoSelecao implements EstadoSelecao{
    ESPERA(1){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            LocalDate inicio = selecao.getInscricao().getPeriodo().getInicio();
            //LocalDate termino = selecao.getInscricao().getPeriodo().getTermino();
            if (inicio.isAfter(LocalDate.now())){
                return this;
            } else {
                return ABERTA.execute(selecao);
            }
        }
    },
    ABERTA(2){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            LocalDate inicio = selecao.getInscricao().getPeriodo().getInicio();
            LocalDate termino = selecao.getInscricao().getPeriodo().getTermino();
            if ((inicio.isBefore(LocalDate.now()) || inicio.equals(LocalDate.now())) && termino.isAfter(LocalDate.now())) {
                return this;
            } else {
                return ANDAMENTO.execute(selecao);
            }
        }
    },
    ANDAMENTO(3){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            LocalDate termino = selecao.getInscricao().getPeriodo().getTermino();
            if (termino.isBefore(LocalDate.now()) && (selecao.getUltimaEtapa().getPeriodo().getTermino().isAfter(LocalDate.now()) || selecao.getUltimaEtapa().getPeriodo().getTermino().equals(LocalDate.now()))) {
                return this;
            } else {
                return FINALIZADA.execute(selecao);
            }
        }
    },
    FINALIZADA(4){
        @Override
        public EnumEstadoSelecao execute(Selecao selecao){
            if(selecao.getUltimaEtapa().getPeriodo().getTermino().isBefore(LocalDate.now())){
                return this;
            }else{
                return ANDAMENTO.execute(selecao);
            }
        }
    };
    
    int estado;
    
    EnumEstadoSelecao() {
        
    }
    
    EnumEstadoSelecao(int estado) {
        setEstado(estado);
    }

    
    public int getEstado() {
        return estado;
    }
    
    private void setEstado(int estado) {
        if (estado>=1 && estado <= 4) {
            this.estado = estado;
        } else {
            throw new IllegalArgumentException("Estado de seleção deve ser maior igual a um e menor igual a quatro!");
        }
    }

}
