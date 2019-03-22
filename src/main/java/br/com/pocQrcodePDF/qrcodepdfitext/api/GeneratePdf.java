package br.com.pocQrcodePDF.qrcodepdfitext.api;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeneratePdf {

    public static void main(String[] args) {

        //Criando o documento
        Document document = new Document(PageSize.A4,  72, 72, 72, 72);

        BarcodeQRCode qrCode;

        Image qrCodeAsImage;

        Image header;

        Image footer;


        try {

            PdfWriter.getInstance(document, new FileOutputStream("/home/usertqi/workspace/teste.pdf"));

            //Carregando imagem de cabeçalho e rodapé
            String hd = "/home/usertqi/workspace/hd.png";
            String ft = "/home/usertqi/workspace/ft.png";

            document.open();

            // adicionando um parágrafo com fonte diferente ao arquivo
            //document.add(new Paragraph("Adicionando outro paragrafo",FontFactory.getFont(FontFactory.COURIER, 12)));

            //Criando o QR-code
            qrCode = new BarcodeQRCode("i:;q:f99d0bcfe6b47cac08f7a35e8abfecc6ef01b3279f6413f4bab231a7c29a74a6ed3466d14cf599a1;qt:2;vs:2;",250,250,null);

            //Converte o QRcode para imagem
            qrCodeAsImage = qrCode.getImage();

            //Estilizando as imagens
            header = Image.getInstance(String.format(hd));
            header.setAlignment(Element.ALIGN_CENTER);

            footer = Image.getInstance(String.format(ft));
            footer.setAlignment(Element.ALIGN_CENTER);

            document.add(header);

            qrCodeAsImage.setAlignment(Element.ALIGN_CENTER);

            document.add(qrCodeAsImage);

            Paragraph name = new Paragraph("Ronaldo Souza Silva");

            name.setAlignment(Element.ALIGN_CENTER);

            document.add(name);

            document.add(footer);

        } catch (DocumentException de) {
            System.err.println(de.getMessage());

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());

        } finally {
            document.close();
        }
    }


}
