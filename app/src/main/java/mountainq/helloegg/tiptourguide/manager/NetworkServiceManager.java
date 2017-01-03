package mountainq.helloegg.tiptourguide.manager;

import mountainq.helloegg.tiptourguide.ApplicationController;
import mountainq.helloegg.tiptourguide.interfaces.NetworkService;

/**
 * Created by dnay2 on 2016-11-29.
 */

public class NetworkServiceManager {

    public static NetworkService getNetworkServices(){
        ApplicationController app = ApplicationController.getInstance();
        return app.getNetworkService();
    }


}
