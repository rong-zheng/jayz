/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaydenzheng;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author rong
 */
public class MyViewManager extends ViewManager {

    private LinearLayout layout;

    public MyViewManager(Activity act) {
        super(act);

        android.content.res.Configuration config = act.getResources().getConfiguration();
        Log.d(this.getClass().getName(), "orientation:" + config.orientation);

    }

    @Override
    public void init() {
        layout = new LinearLayout(this.act);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout btnLayout = new LinearLayout(this.act);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button btn1 = new Button(this.act);
        btn1.setText("Btn1");
        btn1.setPadding(0, 0, 0, 0);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp1.setMargins(0, 0, 0, 0);
        //btn1.setLayoutParams(lp1);
        // btn1.setGravity(Gravity.RIGHT);

        Button btn2 = new Button(this.act);
        btn2.setText("Btn2");
        btn2.setPadding(0, 0, 0, 0);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp2.setMargins(-21, 0, 0, 0);
        //btn2.setLayoutParams(lp2);

        btnLayout.addView(btn1, lp1);
        btnLayout.addView(btn2, lp2);

        TextView text = new TextView(this.act);

        text.setText("Login Name:");
        text.setTextSize(24f);

        EditText textBox = new EditText(this.act);
        textBox.setTextSize(18f);
        textBox.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout loginRow = new LinearLayout(this.act);
        loginRow.setOrientation(LinearLayout.VERTICAL);
        loginRow.addView(text);
        loginRow.addView(textBox);
        loginRow.setPadding(10, 20, 10, 10);

        layout.addView(btnLayout);
        layout.addView(loginRow);

        RelativeLayout relativeLayout = new RelativeLayout(this.act);
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        Button btn3 = new Button(this.act);
        btn3.setText("Btn3");
        btn3.setId(3);
        //btn3.setLayoutParams(lp1);

        Button btn4 = new Button(this.act);
        btn4.setText("Btn4");
        btn4.setId(4);
        //btn4.setLayoutParams(lp1);

        Button btn5 = new Button(this.act);
        btn5.setText("Btn5");
        btn5.setId(5);
        //btn5.setLayoutParams(lp1);

        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_START);
        relativeLayout.addView(btn3, lp3);

        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        Log.d("layout", "btn3 id:" + btn3.getId());
        Log.d("layout", "btn4 id:" + btn4.getId());

        lp4.addRule(RelativeLayout.BELOW, btn3.getId());
        lp4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        relativeLayout.addView(btn4, lp4);

        RelativeLayout.LayoutParams lp5 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp5.addRule(RelativeLayout.LEFT_OF, btn4.getId());
        lp5.addRule(RelativeLayout.BELOW, btn3.getId());
        relativeLayout.addView(btn5, lp5);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onButtonClick();
            }
        };
        btn5.setOnClickListener(buttonClickListener);

        layout.addView(relativeLayout);
    }

    @Override
    public ViewGroup getViewGroup() {
        return this.layout;
    }

    @Override
    public void refreshLayout() {

    }

    public void onButtonClick() {
        // Test adding contact...
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactID = ops.size();

        String name = "Rongz";
        String phone = "1-718-111-2222";

        // Adding insert operation to operations list
        // to insert a new raw contact in the table ContactsContract.RawContacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(RawContacts.ACCOUNT_NAME, null)
                .build());

        // Adding insert operation to operations list
        // to insert display name in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                .withValue(StructuredName.DISPLAY_NAME, name)
                .build());

        // Adding insert operation to operations list
        // to insert Mobile Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, phone)
                .withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
                .build());

        // to insert Home Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                .withValue(Phone.NUMBER, "1-212-532-0918")
                .withValue(Phone.TYPE, Phone.TYPE_HOME)
                .build());

        // to insert Email in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(CommonDataKinds.Email.ADDRESS, "rong@sgsdgs.com")
                .withValue(Phone.TYPE, Phone.TYPE_HOME)
                .build());

        // to insert Address in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue("data1", "13 Neptune Ave Brooklyn NY 11235")
                .withValue(Phone.TYPE, Phone.TYPE_HOME)
                .build());

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap mBitmap = this.loadImage("/storage/sdcard0/DCIM/Camera/test.jpg");
        if (mBitmap != null) {    // If an image is selected successfully
            Log.d("MyViewManager", "saving bitmap...");
            mBitmap.compress(Bitmap.CompressFormat.PNG, 75, stream);

            // Adding insert operation to operations list
            // to insert Photo in the table ContactsContract.Data
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                    .withValue(ContactsContract.Data.IS_SUPER_PRIMARY, 1)
                    .withValue(ContactsContract.Data.MIMETYPE, Photo.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, stream.toByteArray())
                    .build());

            try {
                stream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Log.d("MyViewManager", "Unable to load image....");
        }

        try {
            // Executing all the insert operations as a single database transaction
            this.act.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            Toast.makeText(this.act.getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    public Bitmap loadImage(String filepath) {
        File imagefile = new File(filepath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
            Bitmap bm = BitmapFactory.decodeStream(fis);
            return bm;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("MyViewManager", e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;        
    }
}
