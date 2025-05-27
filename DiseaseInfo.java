public class DiseaseInfo 
{
    private String name;
    private String symptoms;
    private String prevention;
    private String treatment;
    
    public DiseaseInfo(String name) {this.name = name;}
    
    public String getName() {return name;}
	
	public void setSymptoms(String symptoms) {this.symptoms = symptoms;}
    public String getSymptoms() {return symptoms;}
    
    
    public void setPrevention(String prevention) {this.prevention = prevention;}
    public String getPrevention() {return prevention;}
    
    
    public void setTreatment(String treatment) {this.treatment = treatment;}
    public String getTreatment() {return treatment;}
}