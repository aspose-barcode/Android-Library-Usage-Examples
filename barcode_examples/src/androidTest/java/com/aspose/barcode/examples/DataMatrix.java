package com.aspose.barcode.examples;

import android.util.Log;

import com.aspose.barcode.auxiliary.CommonAssist;
import com.aspose.barcode.auxiliary.LicenseAssist;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.DecodeType;
import com.aspose.barcode.generation.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DataMatrix extends CodeExample
{
    @Before
    public void setUp()
    {
        LicenseAssist.setupLicense();
    }

    @Test
    public void generateAndReadDataMatrix()
    {
        String filePath = CommonAssist.getTestsDataFolderPath("examples") + "DataMatrix.png";
        //generate
        BarcodeGenerator bg = new BarcodeGenerator(EncodeTypes.DATA_MATRIX, "Åspóse.Barcóde©");
        {
            bg.getParameters().getBarcode().getXDimension().setPixels(4);
            bg.getParameters().getBarcode().getDataMatrix().setDataMatrixEcc(DataMatrixEccType.ECC_200);
            bg.getParameters().getBarcode().getDataMatrix().setColumns(22);
            bg.getParameters().getBarcode().getDataMatrix().setRows(22);
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
        BarCodeReader br = new BarCodeReader(filePath, DecodeType.DATA_MATRIX);
        BarCodeResult[] barCodeResults = br.readBarCodes();
        for (BarCodeResult result : barCodeResults)
        {
            Log.d(getTag(),"CodeType: " + result.getCodeTypeName());
            Log.d(getTag(),"CodeText: " + result.getCodeText());
        }
    }
}
