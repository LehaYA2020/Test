
import java.util.List;

public class Main {
    public static void main(String[] args) {
       MyXMLParser myXMLParser = new MyXMLParser();
       FileReader fileReader = FileReader.getInstance();
        ScriptExecutor scriptExecutor = new ScriptExecutor();
        scriptExecutor.executeScript("CreateTables.sql");
        myXMLParser.parseToDb(fileReader.getFileFromResources("Companies.xml"));
        CompanyRepository companyRepository = new CompanyRepository();
        List<Company> companies=companyRepository.getAllCompanies();
        for (Company company:companies) {
            System.out.println(company.toString());
        }
    }
}
