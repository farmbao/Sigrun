package sigrun.reports;

import sigrun.common.BinaryHeader;
import sigrun.common.TextHeader;
import sigrun.common.TraceHeader;
import sigrun.serialization.Serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 4/16/12
 * Time: 10:45 PM
 */
public final class ReportProcessor extends Observable {
    private final Serializer<TextHeader> textHeaderSerializer;
    private final Serializer<BinaryHeader> binaryHeaderSerializer;
    private final Serializer<TraceHeader> traceHeaderSerializer;
    private long position;

    public ReportProcessor(Serializer<TextHeader> textHeaderSerializer,
                           Serializer<BinaryHeader> binaryHeaderSerializer,
                           Serializer<TraceHeader> traceHeaderSerializer) {
        this.textHeaderSerializer = textHeaderSerializer;
        this.binaryHeaderSerializer = binaryHeaderSerializer;
        this.traceHeaderSerializer = traceHeaderSerializer;
    }

    public synchronized void process(InputStream input, OutputStream output, ReportStrategy strategy) throws IOException {
        resetPosition();
        processTextHeader(input, strategy);
        processBinaryHeader(input, strategy);
        processTraceHeaders(input, output, strategy);
    }

    private void processTextHeader(InputStream input, ReportStrategy strategy) throws IOException {
        byte[] textHeaderBytes = new byte[textHeaderSerializer.lengthOfSerializableObject()];
        if (input.read(textHeaderBytes) == -1)
            throw new IOException("Unexpected end of file after text header");
        strategy.setTextHeader(textHeaderSerializer.deserialize(textHeaderBytes));
        increasePosition(textHeaderSerializer.lengthOfSerializableObject());
        assert position == TextHeader.TEXT_HEADER_LENGTH;
    }

    private void processBinaryHeader(InputStream input, ReportStrategy strategy) throws IOException {
        byte[] binHeaderBytes = new byte[binaryHeaderSerializer.lengthOfSerializableObject()];
        if (input.read(binHeaderBytes) == -1)
            throw new IOException("Unexpected end of file after bin header");
        strategy.setBinaryHeader(binaryHeaderSerializer.deserialize(binHeaderBytes));
        increasePosition(binaryHeaderSerializer.lengthOfSerializableObject());
        assert position == TextHeader.TEXT_HEADER_LENGTH + BinaryHeader.BIN_HEADER_LENGTH;
    }

    private void processTraceHeaders(InputStream input, OutputStream output, ReportStrategy strategy) throws IOException {
        if (strategy.isPrintHeaderSupported()) {
            strategy.printHeader(output);
        }

        while (true) {
            byte[] traceHeaderBytes = new byte[traceHeaderSerializer.lengthOfSerializableObject()];
            if (input.read(traceHeaderBytes) == -1)
                break;

            final TraceHeader header = traceHeaderSerializer.deserialize(traceHeaderBytes);
            header.setPositionInFile(position);
            increasePosition(traceHeaderSerializer.lengthOfSerializableObject());

            strategy.processTraceHeader(header, output);

            int skippedSize = header.getNumberOfSamples() * strategy.getBinaryHeader().getDataSampleCode().getSize();
            byte[] skipped = new byte[skippedSize];
            int res = input.read(skipped);
            increasePosition(res);
        }
    }

    private void increasePosition(long increment) {
        if (increment > 0) {
            position += increment;
            positionChanged();
        }
    }

    private void resetPosition() {
        position = 0;
        positionChanged();
    }

    private void positionChanged() {
        setChanged();
//        System.out.println("Position = " + position);
        notifyObservers(position);
    }
}