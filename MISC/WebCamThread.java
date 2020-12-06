import org.apache.commons.lang.mutable.MutableBoolean;
import org.bytedeco.javacv.*;
import org.bytedeco.opencv.opencv_core.IplImage;

import java.io.File;

import static org.bytedeco.opencv.global.opencv_core.cvFlip;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvSaveImage;

public class WebCamThread implements  Runnable{

    final int INTERVAL = 50;///you may use interval
    CanvasFrame canvas;
    MutableBoolean takePhoto;

    public WebCamThread(MutableBoolean takePhoto) {
        this.takePhoto = takePhoto;
        this.canvas = new CanvasFrame("Web Cam");
    }

    @Override
    public void run() {

        new File("C:\\Users\\alexa\\Desktop").mkdir();

        FrameGrabber grabber = new OpenCVFrameGrabber(0); // 1 for next camera
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        IplImage img;
        try {
            grabber.start();
            boolean ok = true;
            while (ok) {
                Frame frame = grabber.grab();

                img = converter.convert(frame);

                // the grabbed frame will be flipped, re-flip to make it right
                cvFlip(img, img, 1);// l-r = 90_degrees_steps_anti_clockwise

                // take photo
                if(this.takePhoto.booleanValue()) {
                    cvSaveImage("C:\\Users\\alexa\\Desktop" + File.separator + "web_photo.jpg", img);
                    ok = false;
                }
                canvas.showImage(converter.convert(img));

                Thread.sleep(INTERVAL);
            }
            grabber.stop();
            canvas.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
