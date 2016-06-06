package biz.fesenmeyer;

public class Theft {
	private String person;

	public Theft(String person) {
		super();
		this.person = person;
	}

	public String getPerson() {
		return person;
	}

	@Override
	public String toString() {
		return "Theft [person=" + person + "]";
	}

}
