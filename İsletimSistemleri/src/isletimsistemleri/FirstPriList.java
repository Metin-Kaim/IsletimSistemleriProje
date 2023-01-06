package isletimsistemleri;

import java.util.Random;

public class FirstPriList {

//	private DispatchList dl;
//	
//	public FirstPriList(DispatchList dl)
//	{
//		this.dl=dl;
//	}

	SecondPriList spl = new SecondPriList();
	Kuyruk kuyruk = new Kuyruk();

	void FPL_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean FPL_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

//System.out.println("varis: " + item.varis + "  burst: " + item.burstTime);
	int FPL_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)

		Random rng = new Random();

		// Rastgele RGB renkleri oluşturma
		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		// Rastgele renkleri kullanarak yazıyı biçimlendirme

		DispatchList dl = Prosesler.dl;

//		System.out.println();
//		String text = String.format("\033[38;2;%d;%d;%dmFirst Priority\033[0m", r, g, b);
		// System.out.println(text);
		String text = "";

		int timer = 0;

		Item item = kuyruk.kuyruktanCikar();

		text = String.format(
				"\033[38;2;%d;%d;%dm%2d sn proses basladi         (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m", r,
				g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

		System.out.println(text);

		timer++;
		item.burstTime--;

		item.askiyaAlinma = zaman + timer;

		if (item.burstTime > 0) {
			// System.out.print("spl gonderildi => ");
			item.oncelik++;

			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses askida          (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

			System.out.println(text);

			spl.SPL_add(item);

		} else {
			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

			System.out.println(text);
		}

		dl.TimeOut_Scanner(zaman + timer);
		// System.out.println("--------------"+kuyruk.kuyrukSize()+"-----------");
		return timer;
	}
}
