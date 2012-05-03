package sigrun.common;

/**
 * Trace object
 * Date: 3/27/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Trace {

    private final Long traceHeaderPosition;
    private final TraceHeader header;
    private Trace trace;

    public Trace(TraceHeader header, Long traceHeaderPosition) {
        synchronized (this) {
            this.header = header;
            this.traceHeaderPosition = traceHeaderPosition;
        }
    }

    public Long getTraceHeaderPosition() {
        return traceHeaderPosition;
    }

    public TraceHeader getHeader() {
        return header;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }
}
