package isletimsistemleri;

import java.util.LinkedList;

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
		while (used_items_count != size || !fpl.FPL_isEmpty() || !spl.SPL_isEmpty()||!rr.RR_isEmpty()) {
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

			if (!(fcfs.FCFS_isEmpty())) {
				int fcfs_ExecTime = fcfs.FCFS_execute();
				damn_timer += fcfs_ExecTime;
			} else if (!(fpl.FPL_isEmpty())) {
				int fpl_ExecTime = fpl.FPL_execute();
				damn_timer += fpl_ExecTime;
			} else if (!(spl.SPL_isEmpty())) {
				int spl_ExecTime = spl.SPL_execute();
				damn_timer += spl_ExecTime;
			} else if(!(rr.RR_isEmpty()))
			{
				int rr_ExecTime = rr.RR_execute();
				damn_timer += rr_ExecTime;
			}
			else
				damn_timer++;

			System.out.println("timer: " + damn_timer);
		}
		
		System.out.println("\nFCFS : "+fcfs.kuyruk.kuyrukSize());
		System.out.println("FPL : "+fpl.kuyruk.kuyrukSize());
		System.out.println("SPL : "+spl.kuyruk.kuyrukSize());
		System.out.println("RR : "+rr.kuyruk.kuyrukSize());
		
	}
}
