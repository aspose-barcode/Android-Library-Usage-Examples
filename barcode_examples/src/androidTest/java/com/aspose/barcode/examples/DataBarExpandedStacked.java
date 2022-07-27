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
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DataBarExpandedStacked extends CodeExample
{
    @Before
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    @Test
    public void generateAndRead()
    {
        String filePath = CommonAssist.getTestsDataFolderPath("examples") + "DataBarExpandedStacked.png";
        //generate
        BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.DATABAR_EXPANDED_STACKED, "(01)12345678901231");
        {
            bg.getParameters().getBarcode().getXDimension().setPixels(2);
            bg.getParameters().getBarcode().getDataBar().setAllowOnlyGS1Encoding(true);
            //set 3 rows
            bg.getParameters().getBarcode().getDataBar().setRows(3);
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
        BarCodeReader br = new BarCodeReader(filePath, DecodeType.DATABAR_EXPANDED,DecodeType.DATABAR_EXPANDED_STACKED);
        BarCodeResult[] barCodeResults = br.readBarCodes();
        for (BarCodeResult result : barCodeResults)
        {
            Log.d(getTag(),"CodeType: " + result.getCodeTypeName());
            Log.d(getTag(),"CodeText: " + result.getCodeText());
        }
    }
}
