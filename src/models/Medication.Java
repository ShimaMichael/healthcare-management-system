public class Medication {
    private String medicationId;
    private String name;
    private String form;       // e.g. tablet, syrup
    private String strength;   // e.g. 500 mg

    public Medication() { }

    public Medication(String medicationId, String name, String form, String strength) {
        this.medicationId = medicationId;
        this.name = name;
        this.form = form;
        this.strength = strength;
    }

    public String getMedicationId() { return medicationId; }
    public void setMedicationId(String medicationId) { this.medicationId = medicationId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getForm() { return form; }
    public void setForm(String form) { this.form = form; }

    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }
}
