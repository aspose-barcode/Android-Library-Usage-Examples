package com.aspose.barcode.examples;

import android.util.Log;

import com.aspose.barcode.auxiliary.CommonAssist;
import com.aspose.barcode.auxiliary.LicenseAssist;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;
import com.aspose.barcode.generation.Pdf417ErrorLevel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MacroPDF417 extends CodeExample
{
    @Before
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    @Test
    public void generateAndRead()
    {
        String filePath = CommonAssist.getTestsDataFolderPath("examples") + "MacroPDF417.png";
        //generate
        BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.PDF_417, "Åspóse.Barcóde©");
        {
            bg.getParameters().getBarcode().getXDimension().setPixels(2);
            //set 3 columns
            bg.getParameters().getBarcode().getPdf417().setColumns(3);
            //set error level 2
            bg.getParameters().getBarcode().getPdf417().setPdf417ErrorLevel(Pdf417ErrorLevel.LEVEL_2);
            //set metadata
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroFileID(12345678);
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentID(12);
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroSegmentsCount(20);
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroFileName("file01");
            //checksumm must be calculated in CCITT-16 / CRC-16-CCITT encoding
            //https://en.wikipedia.org/wiki/Cyclic_redundancy_check#Polynomial_representations_of_cyclic_redundancy_checks
            //for the example we use random number
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroChecksum(1234);
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroFileSize(400000);
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroTimeStamp(new GregorianCalendar(2019, Calendar.FEBRUARY, 11).getTime());
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroAddressee("street");
            bg.getParameters().getBarcode().getPdf417().setPdf417MacroSender("aspose");
            try
            {
                bg.save(filePath, BarCodeImageFormat.PNG);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //recognize
        BarCodeReader br = new BarCodeReader(filePath, DecodeType.PDF_417,DecodeType.COMPACT_PDF_417,DecodeType.MACRO_PDF_417);
        BarCodeResult[] barCodeResults = br.readBarCodes();
        for (BarCodeResult result : barCodeResults)
        {
            Log.d(getTag(),"CodeType: " + result.getCodeTypeName());
            Log.d(getTag(),"CodeText: " + result.getCodeText());
            Log.d(getTag(),"Pdf417MacroFileID:" + result.getExtended().getPdf417().getMacroPdf417FileID());
            Log.d(getTag(),"Pdf417MacroSegmentID:" + result.getExtended().getPdf417().getMacroPdf417SegmentID());
            Log.d(getTag(),"Pdf417MacroSegmentsCount:" + result.getExtended().getPdf417().getMacroPdf417SegmentsCount());
            Log.d(getTag(),"Pdf417MacroFileName:" + result.getExtended().getPdf417().getMacroPdf417FileName());
            Log.d(getTag(),"Pdf417MacroChecksum:" + result.getExtended().getPdf417().getMacroPdf417Checksum());
            Log.d(getTag(),"Pdf417MacroFileSize:" + result.getExtended().getPdf417().getMacroPdf417FileSize());
            Log.d(getTag(),"Pdf417MacroTimeStamp:" + result.getExtended().getPdf417().getMacroPdf417TimeStamp().toString());
            Log.d(getTag(),"Pdf417MacroAddressee:" + result.getExtended().getPdf417().getMacroPdf417Addressee());
            Log.d(getTag(),"Pdf417MacroSender:" + result.getExtended().getPdf417().getMacroPdf417Sender());
            

        }
    }
}
