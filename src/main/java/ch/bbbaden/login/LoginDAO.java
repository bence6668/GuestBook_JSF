package ch.bbbaden.login;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class LoginDAO {

    private SAXBuilder builder;
    private File xmlFile;

    public LoginDAO() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        path = path + "WEB-INF\\Benutzerliste.xml";
        xmlFile = new File(path);
        builder = new SAXBuilder();
    }

    public User check(String username, String password) {
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            List list = rootNode.getChildren("user");
            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                if (node.getChildText("name").equals(username) && node.getChildText("password").equals(password)) {
                    
                    return new User(username, Integer.parseInt(node.getChildText("id")));
                }
            }
        } catch (JDOMException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("flop");
        return null;
    }

}
