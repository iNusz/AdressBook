package com.seunghoshin.android.contact_3;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SeungHoShin on 2017. 6. 3..
 */

public class B_Loader {

    public static List<A_Data> getData(Context context){
        List<A_Data> datas = new ArrayList<>();

        // 데이터를 가져오려면 Contactreslover를 써야한다 근데 context가 필요하다
        // 전화번호 데이터를 가져오는 커넥터 역활
        ContentResolver resolver = context.getContentResolver();

        //데이터가 있는 테이블 주소
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //테이블에서 가져올 컬러명을 정의   / 주소록에있는 Data가 아니라 전화번호가 있는 데이터를 가져온것
        String proj[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
        , ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        , ContactsContract.CommonDataKinds.Phone.NUMBER};


        //컨텐트 리졸버로 데이터 가져오기
        Cursor cursor = resolver.query(phoneUri, proj, null, null ,null);

        // cursor에 데이터 존재여부
        if(cursor != null){
            while(cursor.moveToNext()){
                int index = cursor.getColumnIndex(proj[0]);
                int id = cursor.getInt(index);

                index = cursor.getColumnIndex(proj[1]);
                String name = cursor.getString(index);

                index = cursor.getColumnIndex(proj[2]);
                String tel = cursor.getString(index);

                A_Data data = new A_Data();
                data.setId(id);
                data.setName(name);
                data.setTel(tel);

                datas.add(data);
            }
        }
        // 사용 후 커서 자원을 반환한다
        cursor.close();

        return datas;
    }
}
