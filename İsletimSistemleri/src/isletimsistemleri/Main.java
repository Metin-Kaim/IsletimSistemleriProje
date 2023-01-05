package isletimsistemleri;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
	Prosesler p1 = new Prosesler();
	DispatchList dl = new DispatchList();
	p1.process(dl);
	
	//dl.Yazdir();

}
}
	
		
	