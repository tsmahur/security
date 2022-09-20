package com.tsm.security.ldap.corejava;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Properties;

public class LdapConnection {

    //server should be started in Apache Directory Studio
    public static void main(String[] args)  {
        LdapConnection ldapConnection=new LdapConnection();
        System.out.println(ldapConnection.getLdapConnection());
    }

    public DirContext  getLdapConnection() {
        Properties ldapProperties=new Properties();
        ldapProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        ldapProperties.put(Context.PROVIDER_URL,"ldap://localhost:10389");
        ldapProperties.put(Context.SECURITY_PRINCIPAL,"uid=admin,ou=system");//Apache Directory Studio : Right click on connection > properties > Authentication > copy value of Bind DN or use
        ldapProperties.put(Context.SECURITY_CREDENTIALS,"secret");//Apache Directory Studio : goto uid=admin > double-click on value > show password
        DirContext connection=null;
        try {
            connection=new InitialDirContext(ldapProperties);

        } catch (NamingException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
