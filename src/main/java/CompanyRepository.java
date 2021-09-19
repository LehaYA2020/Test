import Exceptions.DAOException;
import Exceptions.MessagesConstants;

import javax.xml.stream.XMLEventReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final String INSERT_COMPANIES = "INSERT INTO companies(name, staff_count) VALUES (?,?);";
    private final String GET_ALL_COMPANIES = "SELECT * FROM companies;";
    private final String GET_COMPANY_BY_ID = "SELECT * FROM companies WHERE id = ?;";
    private final String DELETE_COMPANY = "DELETE FROM companies WHERE id = ?;";
    private final DBConnection dbConnection = DBConnection.getInstance();
    private  FileReader fileReader = FileReader.getInstance();


    public List<Company> insertCompany(List<Company> companies) throws DAOException {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_COMPANIES, Statement.RETURN_GENERATED_KEYS)) {
            for (Company company : companies) {
                preparedStatement.setString(1, company.getName());
                preparedStatement.setInt(2, company.getStaffCount());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new DAOException(MessagesConstants.CANNOT_INSERT_GROUPS, e);
        }
        return companies;
    }

    public List<Company> getAllCompanies() throws DAOException {
        List<Company> companies;
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COMPANIES);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            companies = processCompanySet(resultSet);
        } catch (SQLException e) {
            throw new DAOException(MessagesConstants.CANNOT_GET_COURSES, e);
        }
        return companies;
    }

    public Company getCompanyById(int id){
            List<Company> companies;
            try (Connection connection = dbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(GET_COMPANY_BY_ID)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    companies = processCompanySet(resultSet);
                }
            } catch (SQLException e) {
                throw new DAOException(MessagesConstants.CANNOT_GET_STUDENT_BY_ID, e);
            }
            return companies.get(0);

    }

    public void deleteStudent(Company student) throws DAOException {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMPANY)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(MessagesConstants.CANNOT_DELETE_STUDENT, e);
        }
    }

    private List<Company> processCompanySet(ResultSet resultSet) throws DAOException {
        List<Company> companies = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Company course = new Company(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("staff_count"));
                companies.add(course);
            }
        } catch (SQLException e) {
            throw new DAOException(MessagesConstants.CANNOT_PROCESS_COURSES_SET, e);
        }
        return companies;
    }
}
