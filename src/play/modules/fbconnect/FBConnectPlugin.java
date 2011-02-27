package play.modules.fbconnect;

import play.Logger;
import play.PlayPlugin;
import play.mvc.Router;

public class FBConnectPlugin extends PlayPlugin
{

  private static ThreadLocal<FBConnectSession> _session = new ThreadLocal<FBConnectSession>();

  public FBConnectSession session()
  {
    FBConnectSession s = _session.get();
    if (s != null)
    {
      Logger.trace("found session in threadLocal");
      return s;
    }
    else
    {
      Logger.trace("NOT found session in threadLocal");
      FBConnectSession session = new FBConnectSession();
      session.init();
      _session.set(session);
      return _session.get();
    }
  }

  @Override
  public void onApplicationStart()
  {
    Logger.trace("FBConnectPlugin.onApplicationStart()");
    FBConnectSession session = new FBConnectSession();
    session.init();
    _session.set(session);
  }

  @Override
  public void onRoutesLoaded()
  {
    Router.addRoute("GET", "/fbconnect/oauth/callback", "FBConnect.callback");
  }

}
