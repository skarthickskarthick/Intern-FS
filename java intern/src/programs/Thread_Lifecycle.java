package programs;

class Lifecycles implements Runnable
{
    String threadName;
    Thread thread;
    Lifecycles(String threadName)
    {
        this.threadName=threadName;
    }
    @Override
    public void run() {
        System.out.println("Thread: "+threadName+" State: Running");
        try {
            for (int i = 1; i <= 5; i++) {

                System.out.println("Thread: "+threadName+" Number: "+i);
                System.out.println("Thread: "+threadName+" State: Waiting");
                Thread.sleep(50);
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(e);
        }
        finally {
            System.out.println("Thread: "+threadName+" State: Dead");
        }
    }
    public void start()
    {
       System.out.println("Thread: "+threadName+" State: Start");
       if(thread==null)
       {
           thread=new Thread(this,threadName);
           thread.start();
       }
    }
}
public class Thread_Lifecycle {
    public static void main(String args[])
    {
        Lifecycles cycle1=new Lifecycles("Thread1");
        Lifecycles cycle2=new Lifecycles("Thread2");
        // Thread thread1=new Thread(cycle1);
       // Thread thread2=new Thread(cycle2);// since we override the start method no object instance is necessary
        cycle1.start();
        cycle2.start();
    }
}