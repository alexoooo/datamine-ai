package ao.ai.demo.supervised.classification.plane;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: AO
 * Date: 2/27/11
 * Time: 9:38 PM
 */
public class AoCondition implements Serializable
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


    //--------------------------------------------------------------------
    private final Lock lock         = new ReentrantLock();
    private final Condition valueChanged = lock.newCondition();

    private       boolean condition;


    //--------------------------------------------------------------------
    /** Create a new condition variable in a known state.
     * @param isTrue ...
     */
    public AoCondition(boolean isTrue)
    {
        condition = isTrue;
    }


    //--------------------------------------------------------------------
    /** See if the condition variable is true (without releasing).
     * @return ...
     */
    public boolean isTrue()
    {
        lock.lock();
        try {
            return condition;
        } finally {
            lock.unlock();
        }
    }


    //--------------------------------------------------------------------
    /** Set the condition to true. Waiting threads are notified.
     */
    public void setTrue()
    {
        lock.lock();
        try {
            if (isTrue()) {
                return;
            }

            condition = true;
            valueChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }


    /** Set the condition to false. Waiting threads are not affected.
     */
    public void setFalse()
    {
        lock.lock();
        try {
            if (! isTrue()) {
                return;
            }

            condition = false;
            valueChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }


    //--------------------------------------------------------------------
    /** Wait for the condition to become true.
     *  @param timeout Timeout in milliseconds
     *  @throws InterruptedException ...
     */
    public void waitForTrue(
            long timeout) throws InterruptedException
    {
        while (! isTrue()) {
            valueChanged.await( timeout, TimeUnit.MILLISECONDS );
        }
    }


    /** Wait (potentially forever) for the condition to become true.
     *  @throws InterruptedException ...
     */
    public void waitForTrue() throws InterruptedException
    {
        lock.lock();
        try {
            while (! isTrue()) {
                valueChanged.await();
            }
        } finally {
            lock.unlock();
        }
    }


    //--------------------------------------------------------------------
    /** Wait for the condition to become false.
     *  @param timeout Timeout in milliseconds
     *  @throws InterruptedException ...
     */
    public synchronized void waitForFalse(
            long timeout) throws InterruptedException
    {
        while (isTrue()) {
            valueChanged.await( timeout, TimeUnit.MILLISECONDS );
        }
    }


    /** Wait (potentially forever) for the condition to become true.
     *  @throws InterruptedException ...
     */
    public synchronized void waitForFalse() throws InterruptedException
    {
        while (isTrue()) {
            valueChanged.await();
        }
    }


    //--------------------------------------------------------------------
//    @Override public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        AoCondition condition1 = (AoCondition) o;
//        return condition == condition1.condition;
//    }
//
//    @Override public int hashCode() {
//        return (isTrue() ? 1 : 0);
//    }
}

