package ctp.smc.session;

import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.seam.annotations.Name;

/**
 *
 * @author edewit
 */
@Name("loginBean")
public class LoginBean1 {

    public boolean isMandantenDropDownNeeded() {
        return true;
    }

    public Map<String, String> getMandanten() {
        Map<String, String> mandantenDropdown = new LinkedHashMap<String, String>();
        //mandantenDropdown.put("", Authenticator1.SUPER_USER_MANDANT);
        mandantenDropdown.put("mandant1", "mandant1");
        mandantenDropdown.put("mandant2", "mandant2");

        return mandantenDropdown;
    }
}
