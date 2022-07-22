import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StickerGenerator {

    String nameImage;

    public void create(InputStream inputStream, String stickerName) throws IOException {

        BufferedImage imageOriginal = ImageIO.read(inputStream);

        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        int newHeight = height + 200;
        BufferedImage newImage = new BufferedImage(width,newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D grafhics = (Graphics2D) newImage.getGraphics();

        grafhics.drawImage(imageOriginal,0,0,null);

        var font = new Font(Font.SERIF,Font.BOLD,64);
        grafhics.setColor(Color.YELLOW);
        grafhics.setFont(font);

        grafhics.drawString(getNameImage(), 100,newHeight - 100);

        ImageIO.write(newImage,"png",new File("stickers/" + stickerName));


    }

    public void setNameImage(String nameImage){
        this.nameImage = nameImage;
    }

    public String getNameImage(){
        return this.nameImage;
    }

}
