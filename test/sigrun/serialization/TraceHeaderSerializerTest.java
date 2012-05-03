package sigrun.serialization;

import org.junit.Assert;
import org.junit.Test;
import sigrun.common.CoordinateUnitsCode;
import sigrun.common.GainTypeForInstruments;
import sigrun.common.TraceHeader;
import sigrun.common.TraceIdentificationCode;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 4/13/12
 * Time: 2:06 PM
 */
public class TraceHeaderSerializerTest {
    public final static byte[] SERIALIZED_HEADER = new byte[]{
            0x7F, 0x00, 0x00, 0x01,             /* Trace sequence number within line - 2130706433 */
            0x7F, 0x00, 0x00, 0x01,             /* Trace sequence number within SEG Y File - 2130706433 */
            0x00, 0x7F, 0x00, 0x01,             /* OFR - 8323073 */
            0x07, 0x00, 0x00, 0x00,             /* Trace number within OFR - 117440512 */
            0x01, 0x00, 0x00, 0x00,             /* Energy source point number - 16777216 */
            0x7F, 0x00, 0x00, 0x00,             /* Ensemble number - 2130706432 */
            0x00, 0x77, 0x00, 0x00,             /* Trace number within the ensemble - 7798784 */
            0x00, 0x01,                         /* Trace identification code */
            0x10, 0x00,                         /* Number of vertically summed traces - 4096 */
            0x01, 0x00,                         /* Number of horizontally stacked traces - 256 */
            0x00, 0x02,                         /* Data use - 2 */
            0x00, 0x00, 0x01, 0x00,             /* Distance from center of source point
                                                        to the center of receiver group - 256 */
            0x10, 0x00, 0x00, 0x00,             /* Receiver group elevation - 268435456 */
            0x01, 0x00, 0x00, 0x01,             /* Surface elevation at source - 16777217 */
            0x00, 0x01, 0x01, 0x02,             /* Source depth below surface - 65794 */
            0x03, 0x01, 0x01, 0x00,             /* Datum elevation at receiver group - 50397440 */
            0x05, 0x00, 0x00, 0x07,             /* Datum elevation at source - 83886087 */
            0x00, 0x01, 0x0F, 0x00,             /* Water depth at source - 69376 */
            0x01, 0x0F, 0x0F, 0x01,             /* Water depth at group - 17764097 */
            0x0A, 0x0A,                         /* Scalar to be applied to all elevation and depths - 2570 */
            0x0B, 0x0B,                         /* Scalar to be applied to all coordinates - 2827 */
            0x00, 0x00, 0x0C, 0x0C,             /* Source coordinate X - 3084 */
            0x00, 0x00, 0x0D, 0x0D,             /* Source coordinate Y - 3341 */
            0x00, 0x00, 0x0E, 0x0E,             /* Group coordinate X - 3598 */
            0x00, 0x00, 0x0F, 0x0F,             /* Group coordinate Y - 3855 */
            0x00, 0x04,                         /* Coordinate units - 4 */
            0x10, 0x01,                         /* Weathering velocity - 4097 */
            0x10, 0x02,                         /* Subweathering velocity - 4098 */
            0x01, 0x01,                         /* Uphole time at source in milliseconds - 257 */
            0x02, 0x02,                         /* Uphole time at group in milliseconds - 514 */
            0x03, 0x03,                         /* Source static correction in milliseconds - 771 */
            0x04, 0x04,                         /* Group static correction in milliseconds - 1028 */
            0x05, 0x05,                         /* Total static applied im milliseconds - 103-104 */
            0x06, 0x06,                         /* Lag time A - 1542 */
            0x07, 0x07,                         /* Lag time B - 1799 */
            0x08, 0x08,                         /* Delay recording time - 2056 */
            0x09, 0x09,                         /* Mute time start - 2313 */
            0x0A, 0x0A,                         /* Mute time end - 2570 */
            0x0B, 0x0B,                         /* Number of samples in this trace - 2827 */
            0x0C, 0x0C,                         /* Sample interval in microseconds - 3084 */
            0x00, 0x01,                         /* Gain type for instruments - 3341 */
            0x0E, 0x0E,                         /* Instrument gain constant - 3598 */
            0x0F, 0x0F,                         /* Instrument early on initial gain - 3855 */
            0x00, 0x01,                         /* Correlated - 1 */
            0x01, 0x00,                         /* Sweep frequency at start - 256 */
            0x02, 0x02,                         /* Sweep frequency at end - 514 */
            0x0F, 0x0F,                         /* Sweep length - 3855 */
            0x00, 0x03,                         /* Sweep type - 3 */
            0x04, 0x04,                         /* Sweep trace taper length at start - 1028 */
            0x05, 0x05,                         /* Sweep trace taper length at end - 1285 */
            0x00, 0x03,                         /* Taper type - 3 */
            0x00, 0x7F,                         /* Alias filter frequency - 127*/
            0x00, 0x0F,                         /* Alias filter slope  - 15 */
            0x01, 0x01,                         /* Notch filter frequency - 257 */
            0x02, 0x02,                         /* Notch filter slope - 514 */
            0x05, 0x05,                         /* Low-cut frequency - 1285 */
            0x07, 0x07,                         /* High-cut frequency - 1799 */
            0x08, 0x08,                         /* Low-cut slope - 2056 */
            0x09, 0x09,                         /* High-cut slope - 2313 */
            0x07, (byte) 0xCF,                  /* Year data recorded - 1999 */
            0x00, 0x7F,                         /* Day - 127 */
            0x00, 0x0F,                         /* Hour - 15 */
            0x00, 0x3B,                         /* Minute - 59 */
            0x00, 0x3A,                         /* Second - 58 */
            0x00, 0x01,                         /* Time basis code - 1 */
            0x01, 0x01,                         /* Trace weighing factor - 257 */
            0x0F, 0x0F,                         /* Geophone group of rpo - 3855 */
            0x0E, 0x0E,                         /* Geophone group of trace number of OFR - 3598 */
            0x0D, 0x0D,                         /* Geophone group number of last trace within ofr - 3341 */
            0x0C, 0x0C,                         /* Gap size - 3084 */
            0x0B, 0x0B,                         /* Over travel - 2827 */
            0x7F, 0x7F, 0x7F, 0x7F,             /* X coordinate - 2139062143 */
            0x6F, 0x6F, 0x6F, 0x6F,             /* Y coordinate - 1869573999 */
            0x00, 0x00, 0x00, 0x0F,             /* Inline number - 15 */
            0x00, 0x00, 0x00, 0x0E,             /* Cross line number - 14 */
            0x10, 0x00, 0x00, 0x00,             /* Shotpoint number - 268435456 */
            0x7F, 0x00,                         /* Constant to be applied to the shotpoint number - 32512 */
            0x00, 0x02,                         /* Trace value measurement unit - 2 */
            0x10, 0x00, 0x00, 0x00, 0x00, 0x00, /* Transduction constant - {0x10, 0x00, 0x00, 0x00, 0x00, 0x00} */
            0x00, 0x01,                         /* Transduction unit - 1 */
            0x00, 0x02,                         /* D/T identifier - 2 */
            0x00, 0x03,                         /* Scalar to be applied to times - 3 */
            0x00, 0x04,                         /* Source Type/Orientation - 4 */
            0x00, 0x05, 0x00, 0x05, 0x00, 0x05, /* Source energy direction - {0x00, 0x05, 0x00, 0x05, 0x00, 0x05} */
            0x00, 0x06, 0x00, 0x06, 0x00, 0x06, /* Source measurement - {0x00, 0x06, 0x00, 0x06, 0x00, 0x06} */
            0x00, 0x02,                         /* Source measurement units - 2 */
            0x00, 0x00, 0x00, 0x00,
            0x00, 0x00, 0x00, 0x00,             /* Optional use - 8 zero bytes */
    };


    @Test
    public void testToByteRepresentation() throws Exception {
        TraceHeader traceHeader = createTestTraceHeader();
        Serializer<TraceHeader> serializer = new TraceHeaderSerializer();

        Assert.assertArrayEquals(SERIALIZED_HEADER, serializer.serialize(traceHeader));
    }

    public static TraceHeader createTestTraceHeader() {
        TraceHeader traceHeader = new TraceHeader();

        traceHeader.setTraceSequenceNumberWL(2130706433);
        traceHeader.setTraceSequenceNumberWS(2130706433);
        traceHeader.setOriginalFieldRecordNumber(8323073);
        traceHeader.setTraceNumberWOFR(117440512);
        traceHeader.setEnergySourcePointNumber(16777216);
        traceHeader.setEnsembleNumber(2130706432);
        traceHeader.setTraceNumberWEnsemble(7798784);
        traceHeader.setTraceIdentificationCode(TraceIdentificationCode.create((short) 1));
        traceHeader.setNumberOfVerticallySummedTraces((short) 4096);
        traceHeader.setNumberOfHorizontallyStackedTraces((short) 256);
        traceHeader.setDataUse((short) 2);
        traceHeader.setDistanceFromTheCenterOfSP(256);
        traceHeader.setReceiverGroupElevation(268435456);
        traceHeader.setSurfaceElevationAtSource(16777217);
        traceHeader.setSourceDepthBelowSurface(65794);
        traceHeader.setDatumElevationAtReceiverGroup(50397440);
        traceHeader.setDatumElevationAtSource(83886087);
        traceHeader.setWaterDepthAtSource(69376);
        traceHeader.setWaterDepthAtGroup(17764097);
        traceHeader.setScalarForElevations((short) 2570);
        traceHeader.setScalarForCoordinates((short) 2827);
        traceHeader.setSourceX(3084);
        traceHeader.setSourceY(3341);
        traceHeader.setGroupX(3598);
        traceHeader.setGroupY(3855);
        traceHeader.setCoordinateUnitsCode(CoordinateUnitsCode.create((short) 4));
        traceHeader.setWeatheringVelocity((short) 4097);
        traceHeader.setSubweatheringVelocity((short) 4098);
        traceHeader.setUpholeTimeAtSourceInMs((short) 257);
        traceHeader.setUpholeTimeAtGroupInMs((short) 514);
        traceHeader.setSourceStaticCorrectionInMs((short) 771);
        traceHeader.setGroupStaticCorrectionInMs((short) 1028);
        traceHeader.setTotalStaticAppliedInMs((short) 1285);
        traceHeader.setLagTimeA((short) 1542);
        traceHeader.setLagTimeB((short) 1799);
        traceHeader.setDelayRecordingTime((short) 2056);
        traceHeader.setMuteTimeStart((short) 2313);
        traceHeader.setMuteTimeEnd((short) 2570);
        traceHeader.setNumberOfSamples((short) 2827);
        traceHeader.setSampleIntervalInMcs((short) 3084);
        traceHeader.setGainTypeForInstruments(GainTypeForInstruments.create((short) 1));
        traceHeader.setInstrumentGainConstant((short) 3598);
        traceHeader.setInstrumentEarlyOrInitialGain((short) 3855);
        traceHeader.setCorrelated((short) 1);
        traceHeader.setSweepFrequencyAtStart((short) 256);
        traceHeader.setSweepFrequencyAtEnd((short) 514);
        traceHeader.setSweepLengthInMilliseconds((short) 3855);
        traceHeader.setSweepType((short) 3);
        traceHeader.setSweepTraceTaperLengthAtStartInMilliseconds((short) 1028);
        traceHeader.setSweepTraceTaperLengthAtEndInMilliseconds((short) 1285);
        traceHeader.setTaperType((short) 3);
        traceHeader.setAliasFilterFrequency((short) 127);
        traceHeader.setAliasFilterSlope((short) 15);
        traceHeader.setNotchFilterFrequency((short) 257);
        traceHeader.setNotchFilterSlope((short) 514);
        traceHeader.setLowCutFrequency((short) 1285);
        traceHeader.setHighCutFrequency((short) 1799);
        traceHeader.setLowCutSlope((short) 2056);
        traceHeader.setHighCutSlope((short) 2313);
        traceHeader.setYearDataRecorded((short) 1999);
        traceHeader.setDayOfYear((short) 127);
        traceHeader.setHourOfDay((short) 15);
        traceHeader.setMinuteOfHour((short) 59);
        traceHeader.setSecondOfMinute((short) 58);
        traceHeader.setTimeBasisCode((short) 1);
        traceHeader.setTraceWeightingFactor((short) 257);
        traceHeader.setGeophoneGroupNumberOfRollSwitchPositionOne((short) 3855);
        traceHeader.setGeophoneGroupNumberOfTraceNumberOneWOFR((short) 3598);
        traceHeader.setGeophoneGroupNumberOfLastTraceWOFR((short) 3341);
        traceHeader.setGapSize((short) 3084);
        traceHeader.setOverTravel((short) 2827);
        traceHeader.setxOfCDPPosition(2139062143);
        traceHeader.setyOfCDPPosition(1869573999);
        traceHeader.setInLineNumber(15);
        traceHeader.setCrossLineNumber(14);
        traceHeader.setShotpointNumber(268435456);
        traceHeader.setScalarForSPNumber((short) 32512);
        traceHeader.setTraceValuesMU((short) 2);
        traceHeader.setTransductionConstant(new byte[]{0x10, 0x00, 0x00, 0x00, 0x00, 0x00});
        traceHeader.setTransductionUnits((short) 1);
        traceHeader.setDeviceTraceIdentifier((short) 2);
        traceHeader.setScalarForTimes((short) 3);
        traceHeader.setSourceTypeOrientation((short) 4);
        traceHeader.setSourceEnergyDirection(new byte[]{0x00, 0x05, 0x00, 0x05, 0x00, 0x05});
        traceHeader.setSourceMeasurement(new byte[]{0x00, 0x06, 0x00, 0x06, 0x00, 0x06});
        traceHeader.setSourceMeasurementUnit((short) 2);
        traceHeader.setOptional(new byte[8]);
        return traceHeader;
    }

    @Test
    public void testFromByteRepresentation() throws Exception {
        Serializer<TraceHeader> serializer = new TraceHeaderSerializer();
        TraceHeader traceHeader = serializer.deserialize(SERIALIZED_HEADER);
        byte[] byteRepresentation = serializer.serialize(traceHeader);
        Assert.assertArrayEquals(SERIALIZED_HEADER, byteRepresentation);
    }
}
