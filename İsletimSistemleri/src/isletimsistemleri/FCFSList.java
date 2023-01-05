package isletimsistemleri;

public class FCFSList {

	Kuyruk kuyruk = new Kuyruk();

	void FCFS_add(Item item) {
		kuyruk.kuyrugaEkle(item);

	}

	boolean FCFS_isEmpty() {
		return kuyruk.kuyrukBosMu();
	}

	int FCFS_execute() {// maine gidecek olan timer(ekleme islemi olacak)
		System.out.println("FCFS");
		int timer = 0;
		Item item = kuyruk.Getir(0);
		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		item.burstTime--;
		timer++;
		System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
		
		if(item.burstTime<=0)
		{
			kuyruk.kuyruktanCikar();
		}
//		while (kuyruk.kuyrukSize() != 0) {
//			Item item = kuyruk.kuyruktanCikar();
//			System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
//			while (item.burstTime != 0) {
//				item.burstTime--;
//				timer++;
//				System.out.println("varis: " + item.varis +"  oncelik: "+item.oncelik +"  burst: " + item.burstTime);
//			}
//		}
		return timer;
	}
}
