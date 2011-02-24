package play.modules.fbconnect;

import play.PlayPlugin;
import play.mvc.Router;

public class FBConnectPlugin extends PlayPlugin {
    
    private static ThreadLocal<FBConnectSession> _session = new ThreadLocal<FBConnectSession>();
    
    public FBConnectSession session(){
        return _session.get();
    }
    
    @Override
    public void onApplicationStart() {
        FBConnectSession session = new FBConnectSession();
        session.init();
        _session.set(session);
    }
    
    @Override
    public void onRoutesLoaded() {
        Router.addRoute("GET", "/fbconnect/oauth/callback", "FBConnect.callback");
    }
    
}
