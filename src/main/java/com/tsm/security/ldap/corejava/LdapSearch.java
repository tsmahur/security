package com.tsm.security.ldap.corejava;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LdapSearch {

    public static void main(String[] args) {
        LdapSearch ldapSearch = new LdapSearch();
        ldapSearch.search("(cn=tsm)");
        ldapSearch.search("(uid=1)");
        ldapSearch.search("(&(uid=1)(cn=tsm))");
        ldapSearch.search("(|(cn=Mohan)(cn=tsm))");
        ldapSearch.search("(|(cn=Mohan)(cn=tsm))(uid=1)");
    }

    public void search(String searchFilter){
        LdapConnection ldapConnection=new LdapConnection();

//        String searchFilter="(objectClass=inetOrgPerson)"; //Apache Directory Studio : for this refer properties of any user
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
}
