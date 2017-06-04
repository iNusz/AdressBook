# RecyclerView
##### RecyclerView 를 이용한 Address Book 만들기
<br/>

## Data
###### 데이터를 생성하는 코드이다.
```java
public class A_Data {
    private int id;
    private String name;
    private String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ---...skip...---

    }
}
```

## Loader
###### 데이터를 생성해준 뒤 불러오는 코드이다. 이때 데이터를 가져오려면 ContactResolver을 이용 해야 한다.

```java
// 전화번호 데이터를 가져오는 connect 역활
ContentResolver resolver = context.getContentResolver();

// 데이터가 있는 테이블 주소
Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

// 테이블에서 가져올 컬러명을 정의 , 전화번호가 있는 데이터를 가져옴
String proj[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
, ContactsContract.CommonDataKinds.Phone.NUMBER};

```
###### Cursor 사용
```java
//컨텐트 리졸버로 데이터 가져오기
Cursor cursor = resolver.query(phoneUri, proj, null, null ,null);

// cursor에 데이터 존재여부
if(cursor != null){
    while(cursor.moveToNext()){
        int index = cursor.getColumnIndex(proj[0]);
        int id = cursor.getInt(index);

        ---...skip...---

        A_Data data = new A_Data();
                data.setId(id);
                datas.add(data);

        ---...skip...---
     }
 }
 // 사용 후 커서 자원을 반환한다
 cursor.close();

 return datas;
```

## Adapter
###### 이제 Data 와 List를 연결해줄 Adapter를 만든다

```java
// 받아올 데이터를 정의한다
List<A_Data> datas;
public C_Adapter(List<A_Data> datas){
        this.datas = datas;
    }

// ViewHolder을 이용한다

@Override
public D_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
    return new D_Holder(view);
}

@Override
public void onBindViewHolder(D_Holder holder, int position) {
    A_Data data = datas.get(position);
    holder.setName(data.getName());
    holder.setTel(data.getTel());
}

@Override
public int getItemCount() {
    return datas.size();
}

```

## Holder
```java
public class D_Holder extends RecyclerView.ViewHolder{
    private TextView txtTel, txtName;

    public D_Holder(View itemView) {
        super(itemView);
        txtTel = (TextView) itemView.findViewById(R.id.txtTel);
        txtName = (TextView) itemView.findViewById(R.id.txtName);

    }

    public void setTel(String value){
        txtTel.setText(value);
    }
    public String getTel(){
        return txtTel.getText().toString();
    }

    ---...skip...---
}
```

## 연결
###### 데이터를 아답터를 연결하고 리스트뷰와 연결하는 과정이다.
```java
        // 1. 데이터
        List<A_Data> datas = B_Loader.getData(getBaseContext());

        // 2. 아답터
        C_Adapter adapter = new C_Adapter(datas);

        // 3. 리스트뷰와 아답터 연결
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);

        // 4. 레이아웃 매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

```
## Permission
###### Contact는 RuntimePermission이 필요하기 때문에 생성한다
<br/>

Manifest에 추가를 한다
```Xml
<uses-permission android:name="android.permission.READ_CONTACTS"/>
```

버전호환성 체크
```java
if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkPermission();
        }else{
            init();
        }
    }
```

런타임 권한 체크
```java
   @TargetApi(Build.VERSION_CODES.M)
   private void checkPermission(){
       if(checkSelfPermission(android.Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED);
       init();
   }else{
       String perms[] = {android.Manifest.permission.READ_CONTACTS };
       requestPermissions(perms,100);
   }

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       if(requestCode==100){
           if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
               init();
           }else{
               Toast.makeText(getBaseContext(),"권한을 승인하셔야 앱을 사용할수 있습니다", Toast.LENGTH_SHORT).show();
           }
       }
   }
```
