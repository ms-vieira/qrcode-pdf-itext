package br.com.pocQrcodePDF.qrcodepdfitext.api;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateNewPdf {

    private static String INPUTFILE = "/home/usertqi/workspace/Template.pdf";
    private static String OUTPUTFILE = "/home/usertqi/workspace/New.pdf";

    public static void main(String[] args) {

        //Criando o Documento
        Document documentNew = new Document(PageSize.A4, 0, 0, 0, 0);

        try {
            // Para escrever no documento novo
            PdfWriter writer = PdfWriter.getInstance(documentNew, new FileOutputStream(OUTPUTFILE));
            documentNew.open();

            //Para ler o template
            PdfReader reader = new PdfReader(INPUTFILE);
            PdfImportedPage page;

            //Gerando o qrcode e convertendo para imagem
            BarcodeQRCode qrCode = new BarcodeQRCode("i:;q:f99d0bcfe6b47cac08f7a35e8abfecc6ef01b3279f6413f4bab231a7c29a74a6ed3466d14cf599a1;qt:2;vs:2;", 250, 250, null);
            Image qrCodeAsImage = qrCode.getImage();

            //Selecionar a página do template para importar para o novo pdf
            //Convertendo o template para imagem e estilizando
            page = writer.getImportedPage(reader, 1);
            Image instance = Image.getInstance(page);
            instance.setAlignment(Element.BODY);

            //Estilizando o qrCode
            qrCodeAsImage.setAlignment(Element.JPEG);
            qrCodeAsImage.setAbsolutePosition(170, 250);

            //Setando o nome do vendedor e estilizando
            Phrase seller = new Phrase();
            seller.setLeading(600);
            seller.setTabSettings(new TabSettings(230));
            seller.add(Chunk.TABBING);
            seller.add(new Chunk("Ronaldo Souza Silva"));

            //Adicionando ao novo documento
            documentNew.add(instance);
            documentNew.add(qrCodeAsImage);
            documentNew.add(seller);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());

        } finally {
            documentNew.close();
        }
    }
}

