package mainModel;

import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Session;

public class QrCodes {

    AugmentedImageDatabase qrCodes;
    public QrCodes(Session session){
        this.qrCodes=new AugmentedImageDatabase(session);


    }
    public void add(QrCode qrCode){
        qrCodes.addImage(qrCode.getName(),qrCode.getQrCode());
    }


    public AugmentedImageDatabase getQrCodes() {
        return qrCodes;
    }
}
