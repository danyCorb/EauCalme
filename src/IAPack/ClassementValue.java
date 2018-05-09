package IAPack;

public class ClassementValue {
	int x,x2,y,y2,special;
	double value;
	
	public ClassementValue(int x,int y,int x2,int y2,int special,double value){
		this.x=x;
		this.y=y;
		this.x2=x2;
		this.y2=y2;
		this.special=special;
		this.value=value;
	}

	public int getX() {
		return x;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}
