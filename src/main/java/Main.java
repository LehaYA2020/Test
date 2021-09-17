import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fr = FileReader.getInstance();
        ScriptExecutor scriptExecutor = new ScriptExecutor();
        scriptExecutor.executeScript("CreateTables.sql");
        List<Company> c = fr.getCompanies("Companies.xml");
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.insertCompany(c);
//        c=companyRepository.getAllCourses();
//        for (Company company:c) {
//            System.out.println(company.toString());
//        }
        //companyRepository.deleteStudent(companyRepository.getCompanyById(1));
        c=companyRepository.getAllCourses();
        for (Company company:c) {
            System.out.println(company.toString());
        }
    }
}
