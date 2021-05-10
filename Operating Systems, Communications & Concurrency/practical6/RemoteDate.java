import java.rmi.*;
import java.util.Date;
public interface RemoteDate extends Remote
{
 public abstract Date getDate() throws RemoteException;
}
