
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fr = FileReader.getInstance();
        ScriptExecutor scriptExecutor = new ScriptExecutor();
        scriptExecutor.executeScript("CreateTables.sql");
        List<Company> c = fr.getCompanies("Companies.xml");
        CompanyRepository companyRepository = new CompanyRepository();
        companyRepository.insertCompany(c);
        c=companyRepository.getAllCourses();
        for (Company company:c) {
            System.out.println(company.toString());
        }
    }
}
