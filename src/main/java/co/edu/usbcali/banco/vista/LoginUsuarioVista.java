package co.edu.usbcali.banco.vista;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ViewScoped
@ManagedBean(name = "loginUsuarioVista")
public class LoginUsuarioVista {
    private String username;
    private String password;
    
    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;
    
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(
        AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String login() {
        try {
        	
        	if(username.toString().equals("")) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo de usuario vacío. Digite su usuario.", ""));
            	return "";
            }
        	
        	if(password.toString().equals("")) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo de contraseña vacío. Digite su contraseña.", ""));
            	return "";
            }
        	
            Authentication request = new UsernamePasswordAuthenticationToken(this.getUsername(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);

            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
            Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");          
            
            if(usuario.getActivo() == 'S') {
            	if(usuario.getTipoUsuario().getTiusId() == 1L) {
            		return "/xhtml/principalUsuarioCajero.xhtml";
            	}
            	if(usuario.getTipoUsuario().getTiusId() == 2L) {
            		return "/xhtml/principalUsuarioComercial.xhtml";
            	}
            	if(usuario.getTipoUsuario().getTiusId() == 3L) {
            		return "/xhtml/principalUsuarioAdministrador.xhtml";
            	}
            }else {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario se no encuentra activo.", ""));
            }
            
        } catch (AuthenticationException e) {
        	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La clave no coincide con la del usuario.", ""));
        	return "/loginUsuario.xhtml";
        }

        return "/loginUsuario.xhtml";
    }
}