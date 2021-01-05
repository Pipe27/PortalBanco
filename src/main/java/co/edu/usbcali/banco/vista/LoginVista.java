package co.edu.usbcali.banco.vista;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional.TxType;

@ViewScoped
@ManagedBean(name = "loginVista")
public class LoginVista {
    private String cuenId;
    private String password;
    private BigDecimal clieId;
    
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
    
	public String getCuenId() {
		return cuenId;
	}

	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}

	public BigDecimal getClieId() {
		return clieId;
	}

	public void setClieId(BigDecimal clieId) {
		this.clieId = clieId;
	}

	public String login() {
        try {
        	if(cuenId.toString().equals("")) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo de cuenta vacío. Digite su cuenta.", ""));
            	return "";
            }
        	
            if(clieId == null) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo de identificación vacío. IDigite su identificación.", ""));
            	return "";
            }
            
            if(password.toString().equals("")) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo de contraseña vacío. Digite su contraseña.", ""));
            	return "";
            }
            
            Authentication request = new UsernamePasswordAuthenticationToken(this.getCuenId(), this.getPassword());
            Authentication result = authenticationManager.authenticate(request);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(result);

            ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            
            Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
            BigDecimal id= cuenta.getCliente().getClieId();
            
            if(cuenta.getCuenId().toString().equals("")) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_WARN, "Campo de cuenta vacío. Digite su cuenta.", ""));
            	return "";
            }
            if (!id.equals(clieId)) {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "El id del cliente ingresado no coincide con el dueño de la cuenta.", ""));
            	return "";
            }
            if(cuenta.getActiva() == 'N') {
            	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta no está activa.", ""));
            	return "";
            }
            
        } catch (AuthenticationException e) {
        	FacesContext.getCurrentInstance().addMessage("",new FacesMessage(FacesMessage.SEVERITY_ERROR, "La clave no coincide con la del cliente.", ""));
        	return "/loginCliente.xhtml";
        }

        return "/xhtml/principalCliente.xhtml";
    }
}