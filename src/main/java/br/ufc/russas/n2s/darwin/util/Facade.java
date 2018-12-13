package br.ufc.russas.n2s.darwin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.beans.ResultadoParticipanteSelecaoBeans;
import br.ufc.russas.n2s.darwin.beans.SelecaoBeans;
import util.Constantes;



public class Facade {

	public static String gerarPDFDosResultados(EtapaBeans etapa, List<Object[]> resultado, SelecaoBeans selecao) throws DocumentException, MalformedURLException, IOException, Exception {

			Document document = new Document();
			String name = Constantes.getDocumentsDir()+File.separator+"Selecao_"+selecao.getCodSelecao()+File.separator+
					"RESULTADO_"+selecao.getCodSelecao()+"_ETAPA"+"_"+etapa.getCodEtapa()+"_"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")) +".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(name));
			document.open();
			Image image = Image.getInstance(Constantes.getLOGO_UFC());
			image.setAlignment(Image.MIDDLE);
			image.scaleAbsoluteWidth(50);
			image.scaleAbsoluteHeight(70);
			document.add(image);
			Paragraph cabecalho = new Paragraph(
					"UNIVERSIDADE FEDERAL DO CEARÁ\nCAMPUS DA UFC DE RUSSAS" + "\n\n"
							+ "Resultado da etapa de "+ etapa.getTitulo()+" do edital "+selecao.getTitulo()+"\n\n\n");
			cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cabecalho);
			
			com.itextpdf.text.Font f = FontFactory.getFont("SANS_SERIF", 10, Font.BOLD, new BaseColor(0, 0, 255));
            PdfPTable t = new PdfPTable(3);
            PdfPCell cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.addElement(image);
            t.addCell(cell);
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setColspan(2);
            f.setColor(10, 10, 10);
            f.setSize(15);
            t.addCell(cell1);
            f.setSize(10);
            PdfPTable table = new PdfPTable(2);

            PdfPCell cpf = new PdfPCell(new Paragraph("CPF", f));
            cpf.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cpf.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell nome = new PdfPCell(new Paragraph("NOME", f));
            nome.setBackgroundColor(BaseColor.LIGHT_GRAY);
            nome.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell resultad = new PdfPCell(new Paragraph("RESULTADO", f));
            resultad.setBackgroundColor(BaseColor.LIGHT_GRAY);
            resultad.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setWidths(new int[]{180,400, 160});
            table.addCell(cpf);
            table.addCell(nome);
            table.addCell(resultad);
            
        	for (int i=0;i<resultado.size();i++) {
        		Object[] participante = resultado.get(i);
        		String s = ((ParticipanteBeans) participante[0]).getCandidato().getCPF();
				String nova = "";
				for (int j=0;j<s.length();j++) {
					if (j >2 && j <8) {
						nova +="*";
					} else {
						nova += s.charAt(j);
					}
				}
				PdfPCell cpfCelu = new PdfPCell(new Paragraph(nova,f));
				table.addCell(cpfCelu);
        		
			//	Object[] participante = resultado.get(i);
				table.addCell(((ParticipanteBeans) participante[0]).getCandidato().getNome().toUpperCase());
				
				PdfPCell resu  = new PdfPCell(new Paragraph(participante[2].toString().toLowerCase()));
				resu.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(resu);
			}
            document.add(table);
            Paragraph assAluno = new Paragraph(
            		"\n\n(Assinatura(s) do(s) Responsável(is))");
			assAluno.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(assAluno);
			document.close();				
			return name;
	}
	
	public static String gerarPDFResultadoSelecao(SelecaoBeans selecao, List<ResultadoParticipanteSelecaoBeans> resultado) throws DocumentException, MalformedURLException, IOException {

			Document document = new Document();
			String name = Constantes.getDocumentsDir()+File.separator+"Selecao_"+selecao.getCodSelecao()+File.separator+
					"RESULTADO_FINAL_"+selecao.getCodSelecao()+".pdf";

			PdfWriter.getInstance(document, new FileOutputStream(name));
			document.open();
			Image image = Image.getInstance(Constantes.getLOGO_UFC());
			image.setAlignment(Image.MIDDLE);
			image.scaleAbsoluteWidth(50);
			image.scaleAbsoluteHeight(70);
			document.add(image);
			Paragraph cabecalho = new Paragraph(
					"UNIVERSIDADE FEDERAL DO CEARÁ\nCAMPUS DA UFC DE RUSSAS" + "\n\n"
							+ "Resultado da seleção do edital "+selecao.getTitulo()+"\n\n\n");
			cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cabecalho);
			com.itextpdf.text.Font f = FontFactory.getFont("SANS_SERIF", 10, Font.BOLD, new BaseColor(0, 0, 255));
            PdfPTable t = new PdfPTable(3); //3
            PdfPCell cell = new PdfPCell();
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.addElement(image);
            t.addCell(cell);
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorder(PdfPCell.NO_BORDER);
            cell1.setColspan(2);
            f.setColor(10, 10, 10);
            f.setSize(15);
            t.addCell(cell1);
            f.setSize(10);
            PdfPTable table = new PdfPTable(5);
            
            PdfPCell posicao = new PdfPCell(new Paragraph("#", f)); 
            posicao.setBackgroundColor(BaseColor.LIGHT_GRAY);
            posicao.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell cpf = new PdfPCell(new Paragraph("CPF", f));
            cpf.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cpf.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell nome = new PdfPCell(new Paragraph("NOME", f));
            nome.setBackgroundColor(BaseColor.LIGHT_GRAY);
            nome.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell mediaGeral = new PdfPCell(new Paragraph("MÉDIA GERAL", f));
            mediaGeral.setBackgroundColor(BaseColor.LIGHT_GRAY);
            mediaGeral.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell situacao = new PdfPCell(new Paragraph("SITUAÇÃO", f));
            situacao.setBackgroundColor(BaseColor.LIGHT_GRAY);
            situacao.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            table.setWidths(new int[]{50,130,260,100,150});
            table.addCell(posicao);
            table.addCell(cpf);
            table.addCell(nome);
            table.addCell(mediaGeral);
            table.addCell(situacao);
            f.setSize(8);
        	for (ResultadoParticipanteSelecaoBeans rps : resultado) {
        		PdfPCell posi = new PdfPCell(new Paragraph(rps.getColocacao()+"",f));
				posi.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(posi);
				
				String s = rps.getParticipante().getCandidato().getCPF();
				String nova = "";
				for (int j=0;j<s.length();j++) {
					if (j >2 && j <8) {
						nova +="*";
					} else {
						nova += s.charAt(j);
					}
				}
				PdfPCell cpfCelu = new PdfPCell(new Paragraph(nova,f));
				table.addCell(cpfCelu);
				
				PdfPCell nomeCelu = new PdfPCell(new Paragraph(rps.getParticipante().getCandidato().getNome().toUpperCase(),f));
        		table.addCell(nomeCelu);
				
				
				PdfPCell mg  = new PdfPCell(new Paragraph(rps.getMediaGeral()+"",f));
				mg.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(mg);
				
				if (rps.isAprovado()) s = "CLASSIFICADO"; else s = "DESCLASSIFICADO";

				PdfPCell situ  = new PdfPCell(new Paragraph(s,f));
				situ.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(situ);
				
           }
            document.add(table);
            Paragraph assAluno = new Paragraph(
            		"\n\n(Assinatura(s) do(s) Responsável(is))");
			assAluno.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(assAluno);
			document.close();				

			return name;
		
	}
	

}
