package com.tsm.security.ldap.corejava;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Properties;

public class LdapAuthentication {

    public static void main(String[] args) {
        System.out.println("is Authenticated : "+authenticateUser("tsm","tsm"));
        System.out.println("is Authenticated : "+authenticateUser("tsm","t"));
        System.out.println("is Authenticated : "+authenticateUser("Mohan","Mohan"));
    }
    public static boolean authenticateUser(String userName, String password){
        Properties ldapProperties=new Properties();
        ldapProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        ldapProperties.put(Context.PROVIDER_URL,"ldap://localhost:10389");
        ldapProperties.put(Context.SECURITY_PRINCIPAL,"cn="+userName+",ou=users,ou=system");//cn=tsm,ou=users,ou=system : dn of the user to be authenticated
        ldapProperties.put(Context.SECURITY_CREDENTIALS,password);
        DirContext connection=null;
        try {
            connection=new InitialDirContext(ldapProperties);
            return true;
        } catch (NamingException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }
}
