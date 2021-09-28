
public class Reader extends Thread {

	DataAccessPolicyManager2 lockManager;

	public Reader(DataAccessPolicyManager2 lockManager) {
		this.lockManager = lockManager;
	}


	public void run() {
		while (true) {
			try {
				sleep((int) (Math.random() * 5000));
			} catch (InterruptedException e) {}
			
			lockManager.acquireReadLock();
			System.out.println(" " + lockManager + " Reader acquired read lock.");

			System.out.println("Reader"+lockManager+" is reading");
			
			try {
				sleep((int) (Math.random() * 5000));
			} catch (InterruptedException e) {}

			
			System.out.println(" " + lockManager + " Reader done, releasing read lock");
			lockManager.releaseReadLock();
			
			try {
				sleep((int) (Math.random() * 5000));
			} catch (InterruptedException e) {}
		}
	}

}
