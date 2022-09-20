###Apache Director Studio:
https://directory.apache.org/studio/download/download-windows.html

###Requirements :
Java 11 or Later : https://adoptopenjdk.net/

---
###User Guides:
https://nightlies.apache.org/directory/studio/2.0.0.v20210717-M17/userguide/

https://nightlies.apache.org/directory/studio/2.0.0.v20210717-M17/userguide/Apache_Directory_Studio_User_Guide.pdf

https://nightlies.apache.org/directory/studio/2.0.0.v20210717-M17/userguide/Apache_Directory_Studio_Apache_DS_User_Guide.pdf

https://nightlies.apache.org/directory/studio/2.0.0.v20210717-M17/userguide/Apache_Directory_Studio_Schema_Editor_User_Guide.pdf

https://nightlies.apache.org/directory/studio/2.0.0.v20210717-M17/userguide/Apache_Directory_Studio_LDIF_Editor_User_Guide.pdf

https://nightlies.apache.org/directory/studio/2.0.0.v20210717-M17/userguide/Apache_Directory_Studio_LDAP_Browser_User_Guide.pdf

---
#### Abbreviations :
LDAP : Lightweight Directory Access Protocol <br>
o : organisation <br>
ou : organizationUnit <br>
cn : common name <br>
sn : sure name <br>
RDN : relative distinguished name <br>
dn : distinguished name (uniquely identifies an entry)
---

###Open LDAP perspective:
Windows menu > Open Perspective > LDAP

###Setting Up LDAP server:
https://docs.craftercms.org/en/3.1/developers/cook-books/how-tos/setting-up-an-ldap-server-for-dev.html

###Steps :
- ####Create LDAP server:
    LDAP servers > Right Click > New > New Server (Ctrl + E)
    Select Apache Software Foundation > ApacheDs 2.0.0 > Type Server Name

- ####Start server:
    Right click on created server > Create a Connection (This will be now listed under Connections Tab)
    Right click on created server > Run (Ctrl + R)

- ####Open Server Config:
    Right click on created server > Configurtion

- ####Partitions Page:
    Click on Partitions Tab under opened configuration
    Click on Add > Enter below
    ID : TestCompany
    Suffix : o=TestCompany     (o means organisation)
    Press Ctrl + S

- ####Now Restart Server

- ####Add data (New Entry) in LDAP
    Adding organizationUnit under organization:
    Under LDAP Browser Panel > DIT > o=TestCompany > Right click  > New Entry > Next > Add organizationUnit > Next
    RDN: ou=users  (ou means organisation unit) > Next > Finish

- ####Adding inetOrgPerson under organizationUnit:
    Under LDAP Browser Panel > DIT > ou=users > Right click  > New Entry > Next > Add inetOrgPerson > Next
    RDN: employeeNumber=1  (ou means organisation unit) > Next > Add value to cn=SomeName, sn=SurName> Finish

- ####Repeat above process to create employee.

- ####Now create Groups following :
    ou=Groups (organizationUnit)
    RDN: cn=Admin (groupOfNames)
    Add Attributes :-> member:employeeNumer=1,ou=users,o=TestCompany

- ####Add User in LDAP
    under ou=systems > ou=users
    add New Entry > inetOrgPerson >
    RDN:  cn=someName
    or employeeNumber=1 and then add cn,sn
