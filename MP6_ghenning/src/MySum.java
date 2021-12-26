public class MySum implements Runnable {
    public int sum=0;
    /*synchronized*/ public void increaseSum(){
        sum++;
        String id= Thread.currentThread().getName();
        System.out.println("Sum for " + id +" is "+sum);
    }
    public void run(){
        try{
            Thread.sleep(100);
            synchronized (this){
                this.increaseSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        MySum ms= new MySum();
        for(int i=0; i <100; i++){
            Thread t = new Thread(ms);
            t.start();
        }
    }
}
