import java.util.Objects;

public class Company {
    private int id;
    private String name;
    private int staffCount;

    public Company(String name, int staffCount) {
        this.name = name;
        this.staffCount = staffCount;
    }

    public Company(int id, String name, int staffCount) {
        this.id = id;
        this.name = name;
        this.staffCount = staffCount;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStaffCount(int staffCount) {
        this.staffCount = staffCount;
    }

    public int getStaffCount() {
        return staffCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && staffCount == company.staffCount && Objects.equals(name, company.name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", staffCount=" + staffCount +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, staffCount);
    }
}
