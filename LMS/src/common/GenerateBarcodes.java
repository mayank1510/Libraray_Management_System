package common;


 
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
 
public class GenerateBarcodes {
 
 public static void main(String[] args) {
 
  String pdfFilename = "SampleCodes.pdf";
  GenerateBarcodes generateInvoice = new GenerateBarcodes();
  generateInvoice.createPDF(pdfFilename);
 
 }
 
 private void createPDF (String pdfFilename){
 
  Document doc = new Document();
  PdfWriter docWriter = null;
   
  try {
  // String path = "docs/" + pdfFilename;
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
   doc.addAuthor("betterThanZero");
   doc.addCreationDate();
   doc.addProducer();
   doc.addCreator("MySampleCode.com");
   doc.addTitle("Sample BarCode and QRCode");
   doc.setPageSize(PageSize.LETTER);
 
   doc.open();
   PdfContentByte cb = docWriter.getDirectContent();
    
   String myString = "http://www.MySampleCode.com";
    
    
   Barcode128 code128 = new Barcode128();
         code128.setCode(myString.trim());
         code128.setCodeType(Barcode128.CODE128);
         Image code128Image = code128.createImageWithBarcode(cb, null, null);
         code128Image.setAbsolutePosition(10,700);
         code128Image.scalePercent(125);
         doc.add(code128Image);
          
         code128.setCodeType(Barcode128.CODE128_UCC);
         code128Image = code128.createImageWithBarcode(cb, null, null);
         code128Image.setAbsolutePosition(10,650);
         code128Image.scalePercent(125);
         doc.add(code128Image);
          
         BarcodeEAN codeEAN = new BarcodeEAN();
         codeEAN.setCode(myString.trim());
         codeEAN.setCodeType(BarcodeEAN.EAN13);
         Image codeEANImage = code128.createImageWithBarcode(cb, null, null);
         codeEANImage.setAbsolutePosition(10,600);
         codeEANImage.scalePercent(125);
         doc.add(codeEANImage);
 
         BarcodeQRCode qrcode = new BarcodeQRCode(myString.trim(), 1, 1, null);
         Image qrcodeImage = qrcode.getImage();
         qrcodeImage.setAbsolutePosition(10,500);
         qrcodeImage.scalePercent(200);
         doc.add(qrcodeImage);
          
  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
  }
  finally
  {
   if (doc != null)
   {
    doc.close();
   }
   if (docWriter != null)
   {
    docWriter.close();
   }
  }
 }
 
  
 
}