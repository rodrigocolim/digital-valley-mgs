package util;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.ufc.russas.n2s.darwin.beans.ArquivoBeans;
import br.ufc.russas.n2s.darwin.model.Arquivo;

public class TestBean {
	public static void main (String args[]) {
		ArquivoBeans<Arquivo, ArquivoBeans> beans = new ArquivoBeans<>();
		beans.setCodArquivo(1);
		beans.setTitulo("Teste");
		beans.setArquivo(new File("/"));
		Arquivo business = beans.toBusiness();
		
		System.out.println(business.getTitulo());
	}
}
