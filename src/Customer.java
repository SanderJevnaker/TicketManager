enum EType{
    PRIVATE,
    RANDOM,
    COMPANY,
}
class Customer {
    private int id;
    private String name;
    private String phoneNumber;
    private int seats[];
    private EType eType;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(Debug.NL).append("Phone: ").append(phoneNumber).append(Debug.NL);

        return String.valueOf(sb);
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

    public EType getEType() {
        return eType;
    }

    public void setEType(EType eType) {
        this.eType = eType;
    }

    public static class Private extends Customer {
        Private(){
            setEType(EType.PRIVATE);
        }
    }

    public static class Random extends Customer {
        Random() {
            setEType(EType.RANDOM);
        }
    }

    public static class Company extends Customer{
        private String contactPerson;
        Company(){
            setEType(EType.COMPANY);
        }

        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }
    }
}