package DAO.FileHandlers;

import DAO.CompanyRepository;
import DAO.Exceptions.DAOException;
import DAO.Models.Company;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyXMLParser {
    private CompanyRepository companyRepository = new CompanyRepository();

    public List<Company> parseToDb(File file) {
        List<Company> companyList = new ArrayList<>();
        Set<Company> companySet = new HashSet<>();
        Company company = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            // инициализируем reader и скармливаем ему xml файл
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));
            // проходим по всем элементам xml файла
            while (reader.hasNext()) {
                // получаем событие (элемент) и разбираем его по атрибутам
                XMLEvent xmlEvent = reader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equals("company")) {
                        company = new Company();
                    } else if (startElement.getName().getLocalPart().equals("name")) {
                        xmlEvent = reader.nextEvent();
                        company.setName(xmlEvent.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equals("staff_count")) {
                        xmlEvent = reader.nextEvent();
                        company.setStaffCount(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    }
                }
                // если цикл дошел до закрывающего элемента Student,
                // то добавляем считанного из файла студента в список
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("company")) {
                        companySet.add(company);

                    }
                    if (companySet.size() == 3) {
                        pushToDb(companyList, companySet);
                        companySet.clear();
                    }
                }
            }
            pushToDb(companyList, companySet);

        } catch (Exception e) {
            throw new DAOException("Can't get XML", e);
        }
        return companyList;
    }

    private void pushToDb(List<Company> companyList, Set<Company> companySet) {
        companyList.addAll(companySet);
        companyRepository.insertCompany(new ArrayList<>(companySet));
    }
}
