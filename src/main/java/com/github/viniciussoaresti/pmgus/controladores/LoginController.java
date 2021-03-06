/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.viniciussoaresti.pmgus.controladores;

import com.github.viniciussoaresti.pmgus.criptografia.LoginCriptografia;
import com.github.viniciussoaresti.pmgus.infraestrutura.repositorios.implementacoes.repositorioUsuario;
import com.github.viniciussoaresti.pmgus.negocio.Usuario;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Matheus
 */
@ManagedBean
@RequestScoped
public class LoginController {

    private String login;
    private String senhaRec;

    public String getSenhaRec() {
        return senhaRec;
    }

    public void setSenhaRec(String senhaRec) {
        this.senhaRec = senhaRec;
    }
    private String senha;
    private String senhaCripto;
    private repositorioUsuario repositoriousuario;
    private Usuario usuarioLogado;
    private boolean usrLogado;

    public LoginController(String login, String senha, Usuario usuarioLogado) {
        this.login = login;
        this.senha = senha;
        this.usuarioLogado = usuarioLogado;
    }

    public LoginController() {
    }

    public void cripSenha(String senha) throws IOException {
        senhaCripto = LoginCriptografia.criptografar(senha);
        logar();
    }

    public boolean isUsrLogado() {
        return usrLogado;
    }

    public void setUsrLogado(boolean usrLogado) {
        this.usrLogado = usrLogado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
  
    public void recupSenha(){
       senhaRec=repositoriousuario.getSenha1();
    }
  
    public void deslogar(){
        setUsrLogado(false);
    }
  
    public void logar() throws IOException {
        if (login.equals("9bpm") && senhaCripto.equals("5cfc42d4c71557cd294522c6b66d91f1".toUpperCase())) {
            usrLogado = true;
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Usuário logado com sucesso!"));
            FacesContext.getCurrentInstance().getExternalContext().redirect("menu.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Atenção!", "Login ou senha, incorretos!"));
        }
    }
  
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}