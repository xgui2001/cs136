public class Student {
	private String name;
	private String address;
	private long campusPhone;
	private int suBox;
	private long personalPhone;

	public Student (String studentName, String location, long cNum, int suNumber, long pNum) {
		name = studentName;
		address = location;
		campusPhone = cNum;
		suBox = suNumber;
		personalPhone = pNum;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public int getSuBox() {
		return suBox;
	}

	public long getPersonalPhone() {
		return personalPhone;
	}

	public long getCampusPhone() {
		return campusPhone;
	}


	// javadoc comment
	public String toString() {
		return this.getName() + "\n" + this.getAddress() + "\n" + String.valueOf(this.getPersonalPhone()) + " " + String.valueOf(this.getSuBox()) + " " + String.valueOf(this.getCampusPhone());
	}

	public static void main(String[] args) {
		int su = 1784;
		long p = 123456789;
		long c = 987654321;
		Student test = new Student("Tom", "10 Hopkins Drive", p, su, c);
		System.out.println(test);
	}
}
