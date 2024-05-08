package cn.weedien.csust.medium.ee.expe02;

class ContactPerson implements Comparable<ContactPerson> {
    private String name;
    private String phoneNumber;
    private String group;

    public ContactPerson(String name, String phoneNumber, String group) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public int compareTo(ContactPerson other) {
        return name.compareTo(other.getName());
    }
}
