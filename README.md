# JedisPlayground

Demonstrates how to use Jedis with Jedis connection pools in a manner similar to DBCP.

Project includes a simple IntelliJ webapp (and older Eclipse webapp) and a demo doing a ping to Redis server. See Wiki for details but consider this more of a demo and WIP - not completed.

JNDI Jedis resource:
This is a quick how-to and code sample as to how to embed Jedis as JNDI resource into Tomcat. Why would you want to do something like that (although there are perfectly good other ways to do so say https://github.com/xetorthio/jedis/wiki/Getting-started) is to keep your Jedis configuration in line to what Tomcat DBCP does with SQL connections:
- connection details are separated from the web app itself including username, password, number of connections, timeouts etc
- no new configuration files are needed (use Resource tag of context.xml)
- no complex code is required to use such resources i.e. simply request a connection pool by name and ask for a Jedis connection from it.

While Jedis implements various pools (classes inheriting from JedisPoolAbstract), there does not appear to be a simple mechanism to instantiate these pools and access as JNDI resources as described above and it is actually quite straightforward as Tomcat JNDI does all the work.

Only implemented attribute is Resource/@uri that stores Redis URI

