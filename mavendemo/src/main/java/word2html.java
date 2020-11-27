import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

public class word2html {
    /**
     * 把docx转成html
     * @param ages
     * @throws Exception
     */
    public static void main(String[] ages) throws Exception{
        String docxFilePath = "E:/test/test1.docx";
        String htmlPath = "E:/test/test1.html";

        WordprocessingMLPackage wordMLPackage= Docx4J.load(new java.io.File(docxFilePath));

        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        String imageFilePath=htmlPath.substring(0,htmlPath.lastIndexOf("/")+1)+"/images";
        htmlSettings.setImageDirPath(imageFilePath);
        htmlSettings.setImageTargetUri( "images");
        htmlSettings.setWmlPackage(wordMLPackage);

        String userCSS = "html, body, div, span, h1, h2, h3, h4, h5, h6, p, a, img,  ol, ul, li, table, caption, tbody, tfoot, thead, tr, th, td " +
                "{ margin: 0; padding: 0; border: 0;}" +
                "body {line-height: 1;} ";

        htmlSettings.setUserCSS(userCSS);

        OutputStream os;

        os = new FileOutputStream(htmlPath);

        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

    }
}