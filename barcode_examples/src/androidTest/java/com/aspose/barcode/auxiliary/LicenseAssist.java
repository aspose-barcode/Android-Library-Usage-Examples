package com.aspose.barcode.auxiliary;

import com.aspose.barcode.License;

import java.io.File;

public class LicenseAssist
{
    private static final String LICENSE_PATH =
            CommonAssist.getLicenseFullPath("Aspose.BarCode.Android.Java.lic");

    public static License setupLicense()
    {
        License  license = new License();
        try
        {
            if(!CommonAssist.isFileExists(LICENSE_PATH))
            {
                throw new Exception("Unable to find license file " + LICENSE_PATH);
            }
            license.setLicense(LICENSE_PATH);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return license;
    }
}