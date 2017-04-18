/**
 * Created by ben on 4/9/2017.
 */
public interface QueueInterface <T>
{

    /**
     * Adds new entry to the back of this queue
     * @param newEntry and object to add
     */
    public void enqueue(T newEntry);

    /**
     * Returns and removes the entry at the front of the queue
     * @return the entry at the front of the queue
     */
    public T dequeue();

    /**
     * Returns the entry at the front of the queue but doesn't remove it
     * @return the entry at the front of the queue or null if it's empty
     */
    public T getFront();

    /**
     * Detects whether the queue is empty
     * @return true if the queue is empty, false if not
     */
    public boolean isEmpty();

    /**
     * Removes all entries from the queue
     */
    public void clear();
}
