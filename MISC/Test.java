import org.apache.commons.lang.mutable.MutableBoolean;


public class Test{
    public static void main(String[] args) throws InterruptedException {
        MutableBoolean takePhoto = new MutableBoolean(false);
        Thread th = new Thread(new WebCamThread(takePhoto));
        th.start();
        takePhoto.setValue(true);
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}