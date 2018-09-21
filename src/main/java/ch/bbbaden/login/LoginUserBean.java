package ch.bbbaden.login;



import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.jdom2.JDOMException;

@Named(value="loginUserBean")
@SessionScoped
public class LoginUserBean implements Serializable {

    private String username;
    private String password;
    private User u;
    private String eintrag;
    private boolean loggedIn;


    public String doLogin() throws JDOMException, IOException {
        LoginDAO ldao = new LoginDAO();
        this.u = ldao.check(username, password);
       
        
        if (this.u != null) {
            this.loggedIn = true;
            return "/secured/welcome?faces-redirect=true";
        }
        return "/start";
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

  
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getU() {
        return u;
    }
    

    public String getEintrag() {
        return eintrag;
    }

    public void setEintrag(String eintrag) {
        this.eintrag = eintrag;
    }
    
    

}
