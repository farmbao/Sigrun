package sigrun.reports;

import sigrun.common.TraceHeader;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 4/17/12
 * Time: 2:56 PM
 */
public class TestStrategy extends ReportStrategy {
    @Override
    public void processTraceHeader(TraceHeader traceHeader, OutputStream outputStream) throws IOException {

    }

    @Override
    public void printHeader(OutputStream outputStream) throws IOException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
