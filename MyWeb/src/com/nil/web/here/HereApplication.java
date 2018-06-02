package com.nil.web.here;

/**
 * Created by liorr on 5/28/18.
 */

import org.jboss.resteasy.core.Dispatcher;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import java.util.HashSet;
import java.util.Set;

/**
 * The JAX-RS application publishes our REST services to the runtime.
 */
public class HereApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public HereApplication(@Context Dispatcher dispatcher) {
        singletons.add(new HereService(dispatcher.getDefaultContextObjects()));
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
