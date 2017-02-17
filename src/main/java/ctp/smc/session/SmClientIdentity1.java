package ctp.smc.session;

import static org.jboss.seam.annotations.Install.APPLICATION;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Startup;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.RememberMe;


@Name("org.jboss.seam.security.identity")
@Scope(ScopeType.SESSION)
@Install(precedence = APPLICATION)
@BypassInterceptors
@Startup
public class SmClientIdentity1 extends Identity {

    //private User user;
    private String sedexId;

    public final String MANDANT_COOKIE_NAME = "smc.mandant";

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getSedexId() {
        if (sedexId == null) {
            RememberMe rm = (RememberMe) Contexts.getSessionContext().get(RememberMe.class);
            if (rm.isEnabled()) {
                this.sedexId = this.getStoredMandant();
            }
        }
        return sedexId;
    }

    public void setSedexId(String sedexId) {
        this.sedexId = sedexId;
    }

    public String getStoredMandant() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(MANDANT_COOKIE_NAME)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public String login() {
        String login = super.login();
        RememberMe rm = (RememberMe) Contexts.getSessionContext().get(RememberMe.class);
        if (super.isLoggedIn() && rm.isEnabled()) {
            Cookie cookie = new Cookie(MANDANT_COOKIE_NAME, "mandant1");
            cookie.setMaxAge(rm.getCookieMaxAge());
            cookie.setPath(rm.getCookiePath());
            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
                    .getResponse();
            res.addCookie(cookie);
        }
        return login;
    }

    public static SmClientIdentity1 instance() {
        return (SmClientIdentity1) Component.getInstance(SmClientIdentity1.class, ScopeType.SESSION);
    }
}
