package Data;

public class DataMouse {
	private int actual_mouseX;
	private int actual_mouseY;
	private int clic_mouseX;
	private int clic_mouseY;
	private boolean mouse_is_down;
	
	
	public int getActual_mouseX() {
		return actual_mouseX;
	}
	public void setActual_mouseX(int actual_mouseX) {
		this.actual_mouseX = actual_mouseX;
	}
	public int getActual_mouseY() {
		return actual_mouseY;
	}
	public void setActual_mouseY(int actual_mouseY) {
		this.actual_mouseY = actual_mouseY;
	}
	
	public void set_clic(int x,int y)
	{
		this.clic_mouseX=x;
		this.clic_mouseY=y;
		this.mouse_is_down=true;
	}
	public void set_unclic()
	{
		this.clic_mouseX=0;
		this.clic_mouseY=0;
		this.mouse_is_down=false;;
	}
	
	

}
