package isletimsistemleri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Prosesler {

	public void process(DispatchList dl) throws IOException {

		List<String> allLines = Files.readAllLines(Paths.get("./txtfolder/giris.txt"));// dosyadaki satirlari listede
																						// tutacak

		for (int i = 0; i < allLines.size(); i++) {

			String satir = allLines.get(i);// ilk satir alindi

			Item item = new Item();

			int sayac = -1;// satirdaki verileri varis, oncelik ve burst time'a gore ayiriyor.

			String veri = "";// satirdaki verileri almaya yariyor

			for (int j = 0; j < satir.length(); j++) {
				char ch = satir.charAt(j);// satiri tek tek gezmeyi sagliyor

				if (ch == ' ')
					continue;

				else if (j == satir.length() - 1) {// son satirdaki veriyi de almayi sagliyor ve ilgili Item degerine atamayi sagliyor
					veri += ch;
					item.burstTime = Integer.parseInt(veri);

				} else if (ch != ',') {// verileri veri nesnesine atamayi sagliyor ve verileri ayirmayi sagliyor
					veri += ch;

				} else {//alinan verileri ilgili Item degerlerine atiyor
					
					sayac++;
					switch (sayac) {
					case 0:
						item.varis = Integer.parseInt(veri);
						break;
					case 1:
						item.oncelik = Integer.parseInt(veri);
						break;
					}
					veri = "";
				}
			}
			// bir satirdaki veirleri ayırdık ve Item classına atadık
			dl.ListeyeEkle(item);
		}
		System.out.println("dispatcher cagirildi");
		dl.Fuckin_Dispatcher();
	}
}