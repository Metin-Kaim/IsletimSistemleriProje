package isletimsistemleri;

import java.util.LinkedList;

public class DispatchList {
	LinkedList<Item> dispatchList;
	
	
	FCFSList fcfs = new FCFSList();
	FirstPriList fpl = new FirstPriList();
	SecondPriList spl = new SecondPriList();
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
				// System.out.println("item count: " +used_items_count);
				// System.out.print("timer: " + damn_timer + " --> ");

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

//		System.out.println("\nFCFS : "+fcfs.kuyruk.kuyrukSize());
//		System.out.println("FPL : "+fpl.kuyruk.kuyrukSize());
//		System.out.println("SPL : "+spl.kuyruk.kuyrukSize());
//		System.out.println("RR : "+rr.kuyruk.kuyrukSize());
	}

	public void TimeOut_Scanner(int gecenZaman) {
		for (int i = 0; i < fcfs.kuyruk.kuyrukSize(); i++) {// fcfs checking
			Item item = fcfs.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				System.out.println(gecenZaman + " sn proses zamanasimi" + "(id:" + item.id + "  oncelik: "
						+ item.oncelik + "  kalan sure: " + item.burstTime + " sn)");
				fcfs.kuyruk.kuyruktanCikar(i);
			}
		}
		for (int i = 0; i < fpl.kuyruk.kuyrukSize(); i++) {// firstPriorityList checking
			Item item = fpl.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				System.out.println(gecenZaman + " sn proses zamanasimi" + "(id:" + item.id + "  oncelik: "
						+ item.oncelik + "  kalan sure: " + item.burstTime + " sn)");
				fpl.kuyruk.kuyruktanCikar(i);
			}
		}
		for (int i = 0; i < spl.kuyruk.kuyrukSize(); i++) {// secondPriorityList checking
			Item item = spl.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				System.out.println(gecenZaman + " sn proses zamanasimi" + "(id:" + item.id + "  oncelik: "
						+ item.oncelik + "  kalan sure: " + item.burstTime + " sn)");
				spl.kuyruk.kuyruktanCikar(i);
			}
		}
		for (int i = 0; i < rr.kuyruk.kuyrukSize(); i++) {// Round-Robin checking
			Item item = rr.kuyruk.Getir(i);
			if (gecenZaman - item.askiyaAlinma >= 20)// zaman aşımı oldu
			{
				System.out.println(gecenZaman + " sn proses zamanasimi" + "(id:" + item.id + "  oncelik: "
						+ item.oncelik + "  kalan sure: " + item.burstTime + " sn)");
				rr.kuyruk.kuyruktanCikar(i);
			}
		}

	}
}
