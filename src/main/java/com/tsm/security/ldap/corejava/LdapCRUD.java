package com.tsm.security.ldap.corejava;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

public class LdapCRUD {

    public static void main(String[] args) {
        LdapCRUD ldapCRUD=new LdapCRUD();

//        ldapCRUD.getAllUsers();
//        ldapCRUD.addUser();
//        ldapCRUD.addUserInGroup();
//        ldapCRUD.deleteUser();
//        ldapCRUD.removeUserFromGroup();
        ldapCRUD.modifyAttributeValue();

    }

    public void getAllUsers(){
        LdapConnection ldapConnection=new LdapConnection();

        String searchFilter="(objectClass=inetOrgPerson)"; //Apache Directory Studio : for this refer properties of any user
        String[]  requiresAttributes={"cn","sn"}; //attributes required in output
        SearchControls searchControls=new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);//because of hierarchical level
        searchControls.setReturningAttributes(requiresAttributes);
        try {
//            NamingEnumeration users=ldapConnection.getLdapConnection().search("ou=users,ou=system",searchFilter,searchControls);
            NamingEnumeration<SearchResult> users=ldapConnection.getLdapConnection()
//                    .search("ou=users,o=TestCompany",searchFilter,searchControls); //working
                    .search("ou=users,ou=system",searchFilter,searchControls);

            SearchResult searchResult=null;
            while(users.hasMore()){
//                searchResult= (SearchResult) users.next();
                searchResult= users.next();
                Attributes attributes=searchResult.getAttributes();
                System.out.println(attributes.get("cn"));
                System.out.println(attributes.get("sn"));
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void addUser(){
        LdapConnection ldapConnection=new LdapConnection();

        Attributes attributes=new BasicAttributes();
        Attribute attribute=new BasicAttribute("objectClass");
        attribute.add("inetOrgPerson");


        attributes.put(attribute);
        attributes.put("sn","JavaUser");
//        attributes.put("cn","JavaUser");

        try {
            ldapConnection.getLdapConnection().createSubcontext("cn=Java User CN2,ou=users,ou=system",attributes);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(){
        LdapConnection ldapConnection=new LdapConnection();
        try {
            ldapConnection.getLdapConnection().destroySubcontext("cn=Java User CN2,ou=users,ou=system");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void addUserInGroup(){
        String userName="tsm";
        String groupName="Administrators";
        Attribute attribute=new BasicAttribute("uniqueMember","cn="+userName+",ou=users,ou=system");//"cn=tsm,ou=users,ou=system" : attribute,newValue

        ModificationItem[] modificationItems=new ModificationItem[1];
        modificationItems[0]=new ModificationItem(DirContext.ADD_ATTRIBUTE,attribute);

        LdapConnection ldapConnection=new LdapConnection();
        try {
            ldapConnection.getLdapConnection().modifyAttributes("cn="+groupName+",ou=groups,ou=system",modificationItems);//dn,item
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void removeUserFromGroup(){//DirContext.REMOVE_ATTRIBUTE
        String userName="tsm";
        String groupName="Administrators";
        Attribute attribute=new BasicAttribute("uniqueMember","cn="+userName+",ou=users,ou=system");//"cn=tsm,ou=users,ou=system"

        ModificationItem[] modificationItems=new ModificationItem[1];
        modificationItems[0]=new ModificationItem(DirContext.REMOVE_ATTRIBUTE,attribute);

        LdapConnection ldapConnection=new LdapConnection();
        try {
            ldapConnection.getLdapConnection().modifyAttributes("cn="+groupName+",ou=groups,ou=system",modificationItems);//dn,item
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void modifyAttributeValue(){
        Attribute attribute=new BasicAttribute("sn","Singh");//attribute,newValue

        ModificationItem[] modificationItems=new ModificationItem[1];
        modificationItems[0]=new ModificationItem(DirContext.REPLACE_ATTRIBUTE,attribute);

        LdapConnection ldapConnection=new LdapConnection();
        try {
            ldapConnection.getLdapConnection().modifyAttributes("cn=Mohan,ou=users,ou=system",modificationItems);//dn,item
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}
