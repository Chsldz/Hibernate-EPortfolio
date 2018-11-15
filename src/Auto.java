import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Auto {
	private int id;
	private String marke;
	private String model;
	private int leistung;
	private String farbe;

	public Auto() {
	}

	public Auto(String marke, String model, int leistung, String farbe) {
		super();
		this.marke = marke;
		this.model = model;
		this.leistung = leistung;
		this.farbe = farbe;
	}

	@Id
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getLeistung() {
		return leistung;
	}

	public void setLeistung(int leistung) {
		this.leistung = leistung;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
}
