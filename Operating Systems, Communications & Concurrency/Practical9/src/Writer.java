
public class Writer extends Thread{

	DataAccessPolicyManager2 lockManager;

	public Writer(DataAccessPolicyManager2 lockManager) {
		this.lockManager = lockManager;
	}

	public void run() {
		while(true) {
		
		lockManager.acquireWriteLock();
		System.out.println(" " + lockManager + " Writer acquired write lock.");
		try {
			sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {}
		
		System.out.println(" " + lockManager + " Writer done, releasing write lock");
		lockManager.releaseWriteLock();
		try {
			sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {}
		
		}
	}

}
