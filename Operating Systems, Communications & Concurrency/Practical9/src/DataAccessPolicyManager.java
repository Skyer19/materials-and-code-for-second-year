public abstract class DataAccessPolicyManager 
{
	 protected int readerCount;
	 protected Semaphore mutex;
	 protected Semaphore wrt;
	 public String content="0";
	 protected int idx=0;
	 
	 public abstract void acquireReadLock() ;
	 
	 public abstract void releaseReadLock() ;
	 
	 public abstract void acquireWriteLock() ;
	 
	 public abstract void releaseWriteLock() ;
}