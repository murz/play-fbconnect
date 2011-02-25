package tags.fbconnect;

import groovy.lang.Closure;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import play.Play;
import play.modules.fbconnect.FBConnectPlugin;
import play.mvc.Http.Request;
import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;

@FastTags.Namespace("fbconnect")
public class FBConnectTags extends FastTags
{

  public static void _button(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine)
  {
    Object labelArg = args.get("label");
    Object classArg = args.get("cssClass");
    Object scopeArg = args.get("scope");
    String label = labelArg != null ? labelArg.toString() : "Sign in with Facebook";
    String className = classArg != null ? classArg.toString() : "play-fbconnect-button";
    String scope = scopeArg != null ? scopeArg.toString() : null;
    
    //String tagUrl = Request.current().url;
    Map<String , Object> extraMap = new HashMap<String , Object>();
    args.remove("label");
    args.remove("cssClass");
    args.remove("scope");
    for(Object key : args.keySet())
      extraMap.put(key.toString() , args.get(key));
    
    String url = Play.plugin(FBConnectPlugin.class).session().getLoginUrl(scope , extraMap);
    
    out.println("<a href='" + url + "' class='" + className + "'>" + label + "</a>");
    Request.current().headers.get("refer");

  }

}
