package ie.kambur.jedis;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.JedisPool;

import java.net.URI;
import java.util.Enumeration;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;

public class JNDIFactory implements ObjectFactory {
	final static Logger logger = LogManager.getLogger(JNDIFactory.class);

	@Override
	public Object getObjectInstance(Object obj, Name nameIn, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {

		String uri = null;

		// Customize the bean properties from our attributes
		Reference ref = (Reference) obj;
		Enumeration addrs = ref.getAll();
		while (addrs.hasMoreElements()) {
			RefAddr addr = (RefAddr) addrs.nextElement();
			String name = addr.getType();
			String value = (String) addr.getContent();
			if (name.equals("uri"))
				uri = value;
			else 
				logger.warn ("Ignoring '" + name + "' = '" + value + "'");
		}

		if (uri != null)
			return new JedisPool (new URI (uri));
		
		throw new NamingException ("Missing uri to load jedis");

	}

}
