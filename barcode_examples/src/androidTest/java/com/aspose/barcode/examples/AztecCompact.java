package com.aspose.barcode.examples;

import android.util.Log;

import com.aspose.barcode.auxiliary.CommonAssist;
import com.aspose.barcode.auxiliary.LicenseAssist;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.AztecSymbolMode;
import com.aspose.barcode.generation.BarCodeImageFormat;
import com.aspose.barcode.generation.BarcodeGenerator;
import com.aspose.barcode.generation.EncodeTypes;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AztecCompact extends CodeExample
{
    @Before
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    @Test
    public void generateAndRead()
    {
        String filePath = CommonAssist.getTestsDataFolderPath("examples") + "AztecCompact.png";
        //generate
        BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.AZTEC, "Åspóse.Barcóde©");
        {
            bg.getParameters().getBarcode().getXDimension().setPixels(4);
            //set symbol mode FullRange
            bg.getParameters().getBarcode().getAztec().setAztecSymbolMode(AztecSymbolMode.COMPACT);
            //set error correction capacity to 10% (can be from 5% to 95%)
            bg.getParameters().getBarcode().getAztec().setAztecErrorLevel(10);
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
        BarCodeReader br = new BarCodeReader(filePath, DecodeType.AZTEC);
        BarCodeResult[] barCodeResults = br.readBarCodes();
        for (BarCodeResult result : barCodeResults)
        {
            Log.d(getTag(),"CodeType: " + result.getCodeTypeName());
            Log.d(getTag(),"CodeText: " + result.getCodeText());
        }
    }
}
