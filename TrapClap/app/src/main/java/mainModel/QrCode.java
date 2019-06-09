package mainModel;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.os.Environment;



import java.io.File;

import pack.clap.R;


public class QrCode {
    private Bitmap qrCode;
    private String name;
    private int id;


    // ici toutes les fonctions dont on a besoin pour identifier les qr code et SURTOUT recuperer les iamges a comprarer pour les qr code
    // surement un attribut de type image ici


    public QrCode(String name, Bitmap qrCode, int id){
        this.name=name;
        this.qrCode=qrCode;

        this.id=id;


    }


    public Bitmap getQrCode() {
        return qrCode;

    }

    public String getName() {
        return name;
    }
}
