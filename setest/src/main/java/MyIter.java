import java.util.Iterator;

/**
 * @author ：songdalin
 * @date ：2022/7/18 下午 3:49
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class MyIter implements Iterable, Iterator{

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
