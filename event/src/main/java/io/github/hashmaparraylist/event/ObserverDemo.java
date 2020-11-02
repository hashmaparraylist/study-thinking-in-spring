package io.github.hashmaparraylist.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * {@link Observer} 示例
 *
 * @author
 * @date 2020/11/2
 *
 * @see Observer
 */
public class ObserverDemo {

    public static void main(String[] args) {
        EventObservable observable = new EventObservable();
        // 追加观察者(监听者)
        observable.addObserver(new EventObserver());
        // 发布消息(事件)
        observable.notifyObservers("Hello,world");
    }

    static class EventObservable extends Observable {
        public void setChanged() {
            super.setChanged();
        }

        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("收到事件 : " + eventObject.getSource());
        }
    }
}
