package sigrun.serialization;

import sigrun.common.*;

public class BinaryHeaderSerializer extends Serializer<BinaryHeader> {
    public BinaryHeaderSerializer() {
    }

    public synchronized byte[] serialize(BinaryHeader binaryHeader) {
        setInternalOffset(0);
        byte[] result = new byte[BinaryHeader.BIN_HEADER_LENGTH];

        writeInt(binaryHeader.getJobId(), result);
        writeInt(binaryHeader.getLineNumber(), result);
        writeInt(binaryHeader.getReelNumber(), result);
        writeShort(binaryHeader.getDataTracesPerEnsemble(), result);
        writeShort(binaryHeader.getAuxilarityTracesPerEnsemble(), result);
        writeShort(binaryHeader.getSampleInterval(), result);
        writeShort(binaryHeader.getSampleIntervalOfOFR(), result);
        writeShort(binaryHeader.getSamplesPerDataTrace(), result);
        writeShort(binaryHeader.getSamplesPerDataTraceOfOFR(), result);
        writeShort(binaryHeader.getDataSampleCode().getCode(), result);
        writeShort(binaryHeader.getEnsembleFold(), result);
        writeShort(binaryHeader.getTraceSorting().getCode(), result);
        writeShort(binaryHeader.getVerticalSumCode(), result);
        writeShort(binaryHeader.getSweepFrequencyAtStart(), result);
        writeShort(binaryHeader.getSweepFrequencyAtEnd(), result);
        writeShort(binaryHeader.getSweepLength(), result);
        writeShort(binaryHeader.getSweepTypeCode().getCode(), result);
        writeShort(binaryHeader.getTraceNumber(), result);
        writeShort(binaryHeader.getTaperLengthAtStart(), result);
        writeShort(binaryHeader.getTaperLengthAtEnd(), result);
        writeShort(binaryHeader.getTaperType().getCode(), result);
        writeShort(binaryHeader.getDataTracesCorrelated(), result);
        writeShort(binaryHeader.getBinaryGainRecovered(), result);
        writeShort(binaryHeader.getAmplitudeRecoveryMethod(), result);
        writeShort(binaryHeader.getMeasurementSystem(), result);
        writeShort(binaryHeader.getImpulseSignalPolarity(), result);
        writeShort(binaryHeader.getVibratoryPolarityCode(), result);
        writeArray(new byte[240], result);
        writeShort(binaryHeader.getSegyFormatRevNumber(), result);
        writeShort(binaryHeader.getFixedLengthTraceFlag(), result);
        writeShort(binaryHeader.getNumberOf3200Byte(), result);
        writeArray(new byte[94], result);

        assert getInternalOffset() == 400;

        return result;
    }

    public synchronized BinaryHeader deserialize(byte[] source) {
        setInternalOffset(0);
        BinaryHeader header = new BinaryHeader();

        header.setJobId(byteAToInt(source));
        header.setLineNumber(byteAToInt(source));
        header.setReelNumber(byteAToInt(source));
        header.setDataTracesPerEnsemble(byteAToShort(source));
        header.setAuxilarityTracesPerEnsemble(byteAToShort(source));
        header.setSampleInterval(byteAToShort(source));
        header.setSampleIntervalOfOFR(byteAToShort(source));
        header.setSamplesPerDataTrace(byteAToShort(source));
        header.setSamplesPerDataTraceOfOFR(byteAToShort(source));
        header.setDataSampleCode(DataSample.create(byteAToShort(source)));
        header.setEnsembleFold(byteAToShort(source));
        header.setTraceSorting(TraceSorting.create(byteAToShort(source)));
        header.setVerticalSumCode(byteAToShort(source));
        header.setSweepFrequencyAtStart(byteAToShort(source));
        header.setSweepFrequencyAtEnd(byteAToShort(source));
        header.setSweepLength(byteAToShort(source));
        header.setSweepTypeCode(SweepTypeCode.create(byteAToShort(source)));
        header.setTraceNumber(byteAToShort(source));
        header.setTaperLengthAtStart(byteAToShort(source));
        header.setTaperLengthAtEnd(byteAToShort(source));
        header.setTaperType(TaperType.create(byteAToShort(source)));
        header.setDataTracesCorrelated(byteAToShort(source));
        header.setBinaryGainRecovered(byteAToShort(source));
        header.setAmplitudeRecoveryMethod(byteAToShort(source));
        header.setMeasurementSystem(byteAToShort(source));
        header.setImpulseSignalPolarity(byteAToShort(source));
        header.setVibratoryPolarityCode(byteAToShort(source));
        setInternalOffset(getInternalOffset() + 240);
        header.setSegyFormatRevNumber(byteAToShort(source));
        header.setFixedLengthTraceFlag(byteAToShort(source));
        header.setNumberOf3200Byte(byteAToShort(source));

        return header;
    }

    @Override
    public int lengthOfSerializableObject() {
        return BinaryHeader.BIN_HEADER_LENGTH;
    }
}