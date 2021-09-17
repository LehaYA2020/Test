import java.util.Objects;

public class Company {
    private int id;
    private final String name;
    private final int staffCount;

    public Company(String name, int staffCount) {
        this.name = name;
        this.staffCount = staffCount;
    }

    public Company(int id, String name, int staffCount) {
        this.id = id;
        this.name = name;
        this.staffCount = staffCount;
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
