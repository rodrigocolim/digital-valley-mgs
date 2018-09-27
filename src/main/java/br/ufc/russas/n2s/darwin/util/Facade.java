package br.ufc.russas.n2s.darwin.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.ufc.russas.n2s.darwin.beans.AvaliacaoBeans;
import br.ufc.russas.n2s.darwin.beans.EtapaBeans;
import br.ufc.russas.n2s.darwin.beans.ParticipanteBeans;
import br.ufc.russas.n2s.darwin.model.Participante;
import util.Constantes;



public class Facade {
	
	
	
	public static String gerarPDFDosResultados(EtapaBeans etapa, List<Object[]> resultado, String nomeSelecao) {
		try {
			Document document = new Document();
			String name = Constantes.getDocumentsDir()+File.separator+"Seleção_"+nomeSelecao+File.separator+
					"RESULTADO_"+nomeSelecao+"_ETAPA"+"_"+etapa.getTitulo()+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")) +".pdf";
			//PdfWriter.getInstance(document, new FileOutputStream(Constantes.getDocumentsDir()+File.separator+"Seleção_"+nomeSelecao+File.separator+
			//		"RESULTADO_"+nomeSelecao+"_ETAPA"+"_"+etapa.getTitulo()+LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyhhmmss")) +".pdf"));
			PdfWriter.getInstance(document, new FileOutputStream(name));
			document.open();
			Image image = Image.getInstance(Constantes.getDocumentsDir());
			//Image image = Image.getInstance(Constantes.getLOGO_UFC());
			image.setAlignment(Image.MIDDLE);
			image.scaleAbsoluteWidth(50);
			image.scaleAbsoluteHeight(70);
			document.add(image);
			Paragraph cabecalho = new Paragraph(
					"UNIVERSIDADE FEDERAL DO CEARÁ\nCAMPUS DA UFC DE RUSSAS" + "\n\n"
							+ "Resultado da etapa de "+ etapa.getTitulo()+" do edital "+nomeSelecao+"\n\n\n");
			cabecalho.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(cabecalho);
			
			Font bold = new Font(FontFamily.UNDEFINED, 12, Font.BOLD, BaseColor.BLACK);
			Font normal = new Font(FontFamily.UNDEFINED, 12, Font.NORMAL, BaseColor.BLACK);
			
			//Paragraph conteudo = new Paragraph();
			
			//////////////////////////////////////////////////////////////////////////////////////////////////
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
           // document.add(t);
            f.setSize(10);
            PdfPTable table = new PdfPTable(2);

            PdfPCell coluna1 = new PdfPCell(new Paragraph("NOME", f));
            coluna1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            coluna1.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell coluna2 = new PdfPCell(new Paragraph("RESULTADO", f));
            coluna2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            coluna2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setWidths(new int[]{450, 200});
            table.addCell(coluna1);
            table.addCell(coluna2);
        	for (int i=0;i<resultado.size();i++) {
				Object[] participante = resultado.get(i);
				table.addCell(((Participante) participante[0]).getCandidato().getNome().toUpperCase());
                table.addCell(participante[2].toString().toLowerCase());
			}
            document.add(table);
            Paragraph assAluno = new Paragraph(
            		"\n\n(Assinatura(s) do(s) Responsável(is))");
			assAluno.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(assAluno);
			document.close();				
			
			System.out.println("tudo certo aqui");
			return name;
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO ERRO ERRO");
			System.out.println(Constantes.getTemp());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO ERRO ERRO");
			System.out.println(Constantes.getTemp());
		}
		return null;
		
	}
	
	
	
	
	
}
