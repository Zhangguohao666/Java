import java.util.ArrayList;

//仓库
public class WareHouse {

    private WareHouse(){}
    private static WareHouse warehouse = new WareHouse();
    public static WareHouse getInstance(){
        return warehouse;
    }

    private ArrayList<String> list = new ArrayList<>();

    //向集合中添加元素
    public synchronized void add(){
        if(list.size() < 20){
            list.add("X");
        }else{
            try {
                notifyAll();//唤醒其余线程
                wait();//访问仓库的线程进入等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //向集合中取元素
    public synchronized void get(){
        if(list.size() > 0){
            list.remove(0);
        }else{
            try {
                notifyAll();//唤醒其余线程
                wait();//访问仓库的线程进入等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

