    import org.codehaus.jackson.map.ObjectMapper;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

    import org.eclipse.jetty.server.Handler;
    import org.eclipse.jetty.server.Request;
    import org.eclipse.jetty.server.Server;
    import org.eclipse.jetty.server.handler.AbstractHandler;
    import org.eclipse.jetty.server.handler.HandlerList;
    import org.eclipse.jetty.server.handler.ResourceHandler;

    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;

/* ------------------------------------------------------------ */
    /** Simple Jetty FileServer.
     * This is a simple example of Jetty configured as a FileServer.
     */
    public class TimeTagServerSimple extends AbstractHandler {

        public void handle(String target, Request baseRequest, HttpServletRequest request,
                           HttpServletResponse response) throws IOException, ServletException {

            StringBuffer jb = new StringBuffer();
            String line = null;
            FileWriter f0 = new FileWriter(dbFileName);
            String newLine = System.getProperty("line.separator");
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null)
                    f0.write(line+newLine);
            } catch (Exception e) { /*report an error*/ }

            f0.close();

            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");

            response.setContentLength(0);

            response.setStatus(HttpServletResponse.SC_OK);

            response.flushBuffer();
            baseRequest.setHandled(true);
        }
        static final String dbFileName="src/main/html/state.json";

        public static void main(String[] args) throws Exception
        {

            File dbFile;
            dbFile = new File(dbFileName);
            boolean found = dbFile.exists();



            if(!found)
            {
                MainState state;
                state=new MainState();
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(dbFile,state);
            }


            // Create a basic Jetty server object that will listen on port 8080.  Note that if you set this to port 0
            // then a randomly available port will be assigned that you can either look in the logs for the port,
            // or programmatically obtain it for use in test cases.
            Server server = new Server(8080);

            // Create the ResourceHandler. It is the object that will actually handle the request for a given file. It is
            // a Jetty Handler object so it is suitable for chaining with other handlers as you will see in other examples.
            ResourceHandler resource_handler = new ResourceHandler();
            // Configure the ResourceHandler. Setting the resource base indicates where the files should be served out of.
            // In this example it is the current directory but it can be configured to anything that the jvm has access to.
            resource_handler.setDirectoriesListed(true);
            resource_handler.setWelcomeFiles(new String[]{ "index.html" });
            resource_handler.setResourceBase("src/main/html");

            // Add the ResourceHandler to the server.
            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[] { resource_handler, new TimeTagServerSimple() });
            server.setHandler(handlers);

            // Start things up! By using the server.join() the server thread will join with the current thread.
            // See "http://docs.oracle.com/javase/1.5.0/docs/api/java/lang/Thread.html#join()" for more details.
            server.start();
            server.join();
        }

    }
