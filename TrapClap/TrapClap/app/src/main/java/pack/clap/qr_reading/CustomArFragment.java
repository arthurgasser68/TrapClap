package pack.clap.qr_reading;


import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

import mainModel.Building;
import pack.clap.QrMainActivity;

public class CustomArFragment extends ArFragment {

    @Override
    protected Config getSessionConfiguration(Session session) {
        Config config = new Config(session);
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        config.setFocusMode(Config.FocusMode.AUTO);
        session.configure(config);
        this.getArSceneView().setupSession(session);
        if(QrMainActivity.qrCodes==null)
        {
            ((QrMainActivity) getActivity()).setupDatabase(config, session);
            Building.getInstance().setQrCodes(QrMainActivity.qrCodes);
        }
        config.setAugmentedImageDatabase(QrMainActivity.qrCodes.getQrCodes());
        return config;
    }
}