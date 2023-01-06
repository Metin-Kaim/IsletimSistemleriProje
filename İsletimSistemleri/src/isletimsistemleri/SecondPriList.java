package isletimsistemleri;

import java.util.Random;

public class SecondPriList {

//	public SecondPriList(DispatchList dl) {
//		this.dl = dl;
//	}

	Kuyruk kuyruk = new Kuyruk();
	RRList rr = new RRList();

	void SPL_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean SPL_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

//System.out.println("varis: " + item.varis + "  burst: " + item.burstTime);
	int SPL_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)

		Random rng = new Random();

		// Rastgele RGB renkleri oluşturma
		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		// Rastgele renkleri kullanarak yazıyı biçimlendirme

		DispatchList dl = Prosesler.dl;

//		System.out.println();
//		String text = String.format("\033[38;2;%d;%d;%dmSecond Priority\033[0m", r, g, b);
//		System.out.println(text);
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

			item.oncelik++;

			rr.RR_add(item);

			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses askida          (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

			System.out.println(text);

		} else {
			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

			System.out.println(text);
		}

		dl.TimeOut_Scanner((zaman + timer));

		return timer;
	}
}
