package be.quodlibet.boxable;

import be.quodlibet.boxable.csv.CSVTable;
import com.google.common.io.Files;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Dries Horions <dries@quodlibet.be>
 */
public class CSVTest
{

    public CSVTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void csvTestColWidths() throws IOException
    {
        String data = readData("https://s3.amazonaws.com/misc.quodlibet.be/Boxable/teknologic.csv");
        //Initialize Document
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        //Create a landscape page
        page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);
        //Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 70;

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                                            true);
        CSVTable t = new CSVTable(dataTable, page);
        t.addCsvToTable(data, CSVTable.HASHEADER, ";".toCharArray()[0]);
        dataTable.draw();
        File file = new File("target/CSVexampleColWidths.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        Files.createParentDirs(file);
        doc.save(file);
        doc.close();
    }

    @Test
    public void csvTestPortrait() throws IOException
    {
        String data = readData("https://s3.amazonaws.com/misc.quodlibet.be/Boxable/Eurostat_Immigration_Applications.csv");
        //Initialize Document
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        //Create a landscape page
        //page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);
        //Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 70;

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                                            true);
        CSVTable t = new CSVTable(dataTable, page);
        t.addCsvToTable(data, CSVTable.HASHEADER, ";".toCharArray()[0]);
        dataTable.draw();
        File file = new File("target/CSVexamplePortrait.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        Files.createParentDirs(file);
        doc.save(file);
        doc.close();
    }

    @Test
    public void csvTestLandscape() throws IOException
    {
        String data = readData("https://s3.amazonaws.com/misc.quodlibet.be/Boxable/Eurostat_Immigration_Applications.csv");
        //Initialize Document
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        //Create a landscape page
        //page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);
        //Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 70;

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                                            true);
        CSVTable t = new CSVTable(dataTable, page);
        t.addCsvToTable(data, CSVTable.HASHEADER, ";".toCharArray()[0]);
        dataTable.draw();
        File file = new File("target/CSVexampleLandscape.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        Files.createParentDirs(file);
        doc.save(file);
        doc.close();
    }

    @Test
    public void csvTestSimple() throws IOException
    {

        String data = readData("https://s3.amazonaws.com/misc.quodlibet.be/Boxable/Eurostat_Energcy_Prices_Medium_Household.csv");
        //Initialize Document
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        //Create a landscape page
        page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);
        //Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 70;

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                                            true);
        //Add a few things to the table that's not coming from the csv file
        Row h1 = dataTable.createRow(0f);
        Cell c1 = h1.createCell(100, "Electricity Prices by type of user");
        c1.setFillColor(new Color(144, 195, 212));
        dataTable.addHeaderRow(h1);
        Row h2 = dataTable.createRow(0f);
        Cell c2 = h2.createCell(100, "Eur per kWh for Medium Size Households.<br/>Source <i>http://ec.europa.eu/eurostat/tgm/table.do?tab=table&init=1&plugin=1&language=en&pcode=ten00117</i>");
        c2.setFillColor(new Color(175, 212, 224));
        dataTable.addHeaderRow(h2);
        CSVTable t = new CSVTable(dataTable, page);
        t.addCsvToTable(data, CSVTable.HASHEADER, ";".toCharArray()[0]);
        dataTable.draw();
        File file = new File("target/CSVexampleSimple.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        Files.createParentDirs(file);
        doc.save(file);
        doc.close();

    }

     @Test
    public void csvTestAdvanced() throws IOException
    {

        String data = readData("https://s3.amazonaws.com/misc.quodlibet.be/Boxable/Eurostat_Energcy_Prices_Medium_Household.csv");
        //String data = readData("https://s3.amazonaws.com/misc.quodlibet.be/Boxable/simplecsv.csv");
        //Initialize Document
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        //Create a landscape page
        page.setMediaBox(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        doc.addPage(page);
        //Initialize table
        float margin = 10;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 70;

        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                                            true);
        //Add a few things to the table that's not coming from the csv file
        Row h1 = dataTable.createRow(0f);
        Cell c1 = h1.createCell(100, "Electricity Prices by type of user");
        c1.setFillColor(new Color(144, 195, 212));
        dataTable.addHeaderRow(h1);
        Row h2 = dataTable.createRow(0f);
        Cell c2 = h2.createCell(100, "Eur per kWh for Medium Size Households.<br/>Source <i>http://ec.europa.eu/eurostat/tgm/table.do?tab=table&init=1&plugin=1&language=en&pcode=ten00117</i>");
        c2.setFillColor(new Color(175, 212, 224));
        dataTable.addHeaderRow(h2);
        CSVTable t = new CSVTable(dataTable, page);
        //set the style template for header cells
        t.getHeaderCellTemplate().setFillColor(new Color(13, 164, 214));
        //set the style template for first column
        t.getFirstColumnCellTemplate().setFillColor(new Color(13, 164, 214));
        //set the style template for last column
        t.getLastColumnCellTemplate().setFillColor(new Color(144, 195, 212));
        //set the style template for normal, data columns
        t.getDataCellTemplateEven().setFillColor(Color.WHITE);
        t.getDataCellTemplateOdd().setFillColor(new Color(250, 242, 242));

        t.addCsvToTable(data, CSVTable.HASHEADER, ";".toCharArray()[0]);

        dataTable.draw();
        File file = new File("target/CSVexampleAdvanced.pdf");
        System.out.println("Sample file saved at : " + file.getAbsolutePath());
        Files.createParentDirs(file);
        doc.save(file);
        doc.close();
    }

    private static String readData(String url)
    {
        InputStream in = null;
        try {
            in = new URL(url).openStream();
            return IOUtils.toString(in);
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            IOUtils.closeQuietly(in);
        }
        return "";
    }

    private static PDPage addNewPage(PDDocument doc)
    {
        PDPage page = new PDPage();
        doc.addPage(page);
        return page;
    }
}
