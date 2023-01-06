package isletimsistemleri;

import java.util.Random;

public class RRList {
	Kuyruk kuyruk = new Kuyruk();
	int sayac = 0;

	void RR_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean RR_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

	Item cikanEleman = null;

	int RR_execute(int zaman) {// maine gidecek olan timer(ekleme islemi olacak)

		Random rng = new Random();

		// Rastgele RGB renkleri oluşturma
		int r = rng.nextInt(256);
		int g = rng.nextInt(256);
		int b = rng.nextInt(256);

		// Rastgele renkleri kullanarak yazıyı biçimlendirme

		DispatchList dl = Prosesler.dl;

//		System.out.println();
//		String text = String.format("\033[38;2;%d;%d;%dmRound Robin\033[0m", r, g, b);
//		System.out.println(text);
		String text = "";
		int timer = 0;

		Item item = kuyruk.Getir(sayac);

		text = String.format(
				"\033[38;2;%d;%d;%dm%2d sn proses basladi         (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m", r,
				g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

		System.out.println(text);

		timer++;
		item.burstTime--;

		item.askiyaAlinma = zaman + timer;

		if (item.burstTime == 0) {
			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses sonlandi        (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

			System.out.println(text);

			kuyruk.kuyruktanCikar(sayac);

			if (sayac == kuyruk.kuyrukSize())
				sayac = 0;
		} else {
			text = String.format(
					"\033[38;2;%d;%d;%dm%2d sn proses askida          (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
					r, g, b, (zaman + timer), item.id, item.oncelik, item.burstTime);

			System.out.println(text);

			dl.TimeOut_Scanner(zaman + timer);

			sayac = (sayac + 1) % kuyruk.kuyrukSize();

		}
		return timer;
	}
}
