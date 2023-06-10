package com.example.asaf2work;

import android.graphics.Bitmap;import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
public class Helper{
    public static byte[] bitmapToByteArray(Bitmap in)    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();        in.compress(Bitmap.CompressFormat.PNG,100,bytes);
        return bytes.toByteArray();    }
    public static Bitmap byteArrayToBitmap(byte[]bytes)    {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);    }
}
