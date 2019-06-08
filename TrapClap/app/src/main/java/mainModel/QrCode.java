package mainModel;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Environment;



import java.io.File;



public class QrCode {
    private Bitmap qrCode;
    private String name;


    // ici toutes les fonctions dont on a besoin pour identifier les qr code et SURTOUT recuperer les iamges a comprarer pour les qr code
    // surement un attribut de type image ici


    QrCode(String name, String path ){

        File sd = Environment.getExternalStorageDirectory();
        File image = new File(sd+path, name);
        this.qrCode=new BitmapFactory().decodeFile(image.getAbsolutePath());
        this.name=name;


    }

    public Bitmap getQrCode() {
        return qrCode;
    }

    public String getName() {
        return name;
    }
}
