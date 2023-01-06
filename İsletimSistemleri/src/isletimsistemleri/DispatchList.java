package isletimsistemleri;

import java.util.LinkedList;
import java.util.Random;

public class DispatchList {
	LinkedList<Item> dispatchList;

	FCFSList fcfs = new FCFSList();
	FirstPriList fpl = new FirstPriList();
	SecondPriList spl = fpl.spl;
	RRList rr = spl.rr;
	int damn_timer = 0;

	public DispatchList() {
		dispatchList = new LinkedList<Item>();
	}

	public void ListeyeEkle(Item item) {
		dispatchList.add(item);
	}

	public void Yazdir() {
		for (int i = 0; i < dispatchList.size(); i++) {
			System.out.print(dispatchList.get(i).varis + " ");
			System.out.print(dispatchList.get(i).oncelik + " ");
			System.out.println(dispatchList.get(i).burstTime);
		}
	}

	public void Fuckin_Dispatcher() {
		// dispatchList is full.
		int size = dispatchList.size();
		// System.out.println(size);
		int used_items_count = 0;
		while (used_items_count != size || !fpl.FPL_isEmpty() || !spl.SPL_isEmpty() || !rr.RR_isEmpty()) {
			for (int i = 0; i < size; i++) {
				if (dispatchList.size() == 0)
					break;
				if (dispatchList.get(0).varis > damn_timer) {
					// System.out.println("varis: "+dispatchList.get(0).varis+" timer:
					// "+damn_timer);
					break;
				} else if (dispatchList.get(0).oncelik == 0) {// <=!!!
					// System.out.println("varis: "+dispatchList.get(0).varis);
					fcfs.FCFS_add(dispatchList.remove());
					used_items_count++;
					// System.out.println(damn_timer);
				} else {
					if (dispatchList.get(0).oncelik != 0) {
						if (dispatchList.get(0).oncelik == 1) {
							used_items_count++;
							fpl.FPL_add(dispatchList.remove());
						} else if (dispatchList.get(0).oncelik == 2) {
							used_items_count++;
							spl.SPL_add(dispatchList.remove());
						} else {
							used_items_count++;
							rr.RR_add(dispatchList.remove());
						}
					}

				}

			} // for bitis //time'a gore degerler atanıyor.

			if (!(fcfs.FCFS_isEmpty())) {
				int fcfs_ExecTime = fcfs.FCFS_execute(damn_timer);
				damn_timer += fcfs_ExecTime;
			} else if (!(fpl.FPL_isEmpty())) {
				int fpl_ExecTime = fpl.FPL_execute(damn_timer);
				damn_timer += fpl_ExecTime;
			} else if (!(spl.SPL_isEmpty())) {
				int spl_ExecTime = spl.SPL_execute(damn_timer);
				damn_timer += spl_ExecTime;
			} else if (!(rr.RR_isEmpty())) {
				int rr_ExecTime = rr.RR_execute(damn_timer);
				damn_timer += rr_ExecTime;
			} else
				damn_timer++;
		}
	}

	public void TimeOut_Scanner(int gecenZaman) {
		String text = "";
		for (int i = 0; i < fcfs.kuyruk.kuyrukSize(); i++) {// fcfs checking
			Item item = fcfs.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				Random rng = new Random();

				// Rastgele RGB renkleri oluşturma
				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.id, item.oncelik, item.burstTime);

				System.out.println(text);

				fcfs.kuyruk.kuyruktanCikar(i);
				i--;
			}
		}
		for (int i = 0; i < fpl.kuyruk.kuyrukSize(); i++) {// firstPriorityList checking
			Item item = fpl.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				Random rng = new Random();

				// Rastgele RGB renkleri oluşturma
				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.id, item.oncelik, item.burstTime);

				System.out.println(text);

				fpl.kuyruk.kuyruktanCikar(i);
				i--;
			}
		}
		for (int i = 0; i < spl.kuyruk.kuyrukSize(); i++) {// secondPriorityList checking
			Item item = spl.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				Random rng = new Random();

				// Rastgele RGB renkleri oluşturma
				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);

				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.id, item.oncelik, item.burstTime);

				System.out.println(text);

				spl.kuyruk.kuyruktanCikar(i);
				i--;
			}
		}
		for (int i = 0; i < rr.kuyruk.kuyrukSize(); i++) {// Round-Robin checking
			Item item = rr.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				Random rng = new Random();

				// Rastgele RGB renkleri oluşturma
				int r = rng.nextInt(256);
				int g = rng.nextInt(256);
				int b = rng.nextInt(256);
				
				text = String.format(
						"\033[38;2;%d;%d;%dm%d sn proses zamanasimi      (id: %2d   oncelik:%2d  kalan sure:%2d sn)\033[0m",
						r, g, b, gecenZaman, item.id, item.oncelik, item.burstTime);

				System.out.println(text);

				rr.kuyruk.kuyruktanCikar(i);
				i--;
			}
		}

	}
}
