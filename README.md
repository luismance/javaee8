Add the following code snippet to wildfly standalone.xml

datasource
```
<datasource jndi-name="java:jboss/employeeds" pool-name="employeeds">
	<connection-url>jdbc:mysql://localhost:3306/employee_db</connection-url>
	<driver>mysql8</driver>
	<security>
		<user-name>root</user-name>
		<password>admin</password>
	</security>
	<validation>
		<valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
		<exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
	</validation>
</datasource>
```

drivers

```
<driver name="mysql8" module="com.mysql.driver8">
	<driver-class>com.mysql.cj.jdbc.Driver</driver-class>
	<xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
</driver>
```

copy mysql-connector jar file and module.xml file to wildfly-14.0.1.Final\modules\system\layers\base\com\mysql\driver8\main

use the sql file to create the local database
