package com.example.junlianglin.learningone.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.junlianglin.framework.fragment.BaseFragment;
import com.example.junlianglin.learningone.R;
import com.example.junlianglin.learningone.db.SqliteDBHelper;
import com.example.junlianglin.learningone.model.Building;
import com.example.junlianglin.learningone.model.Category;
import com.example.junlianglin.learningone.model.Data;
import com.example.junlianglin.learningone.model.DictionaryData;
import com.example.junlianglin.learningone.model.Field;
import com.example.junlianglin.learningone.model.Form;
import com.example.junlianglin.learningone.model.ReturnResult;
import com.example.junlianglin.learningone.model.Task;
import com.example.junlianglin.learningone.model.Unit;
import com.example.junlianglin.learningone.utils.AsyncTaskResponse;
import com.example.junlianglin.learningone.utils.DataAsyncTask;
import com.example.junlianglin.learningone.utils.Preferences;
import com.example.junlianglin.learningone.utils.ServerUrl;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by JunliangLin on 1/6/2018.
 */
@ContentView(R.layout.fragment_synchronize)
public class SynchronizeFragment extends BaseFragment {

    @ViewInject(R.id.btnSynchronize)
    private Button btnSynchronize;

    private DataAsyncTask mAuthTask = null;


    @Override
    protected void initParams() {





    }

    @Event(value = R.id.btnSynchronize,type = Button.OnClickListener.class)
    private void btnSynchronizeClick(View view){
        Preferences preferences = new Preferences();
        token = preferences.getAccessToken();



        String url = ServerUrl.REMOTEURL + ":" + ServerUrl.REMOTEPORT + ServerUrl.DATA_LIST;

        mAuthTask = new DataAsyncTask(token,url,new AsyncTaskResponse(){
            @Override
            public void processFinish(ReturnResult output) {
                writeDataToDataBase((Data) output);
            }
        });
        mAuthTask.execute((Void) null);
    }

    public void writeDataToDataBase(Data data){
        System.out.println("extract data...");
        try {

            SqliteDBHelper mDbHelper = new SqliteDBHelper(this.context);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            db.beginTransaction();
            //ContentValues values = new ContentValues();
            //values.put("token", "dddddzxcvz");
            //long newRowId = db.insert(SqliteDBHelper.TABLE_T_USER, null, values);

            System.out.println("data.getBuildingList().size()=" + data.getBuildingList().size());
            //data.getBuildingList().size();
            db.delete(SqliteDBHelper.TABLE_T_BUILDING,null,null);
            db.delete(SqliteDBHelper.TABLE_T_DICTIONARY,null,null);
            db.delete(SqliteDBHelper.TABLE_T_FIELDCATEGORY,null,null);
            db.delete(SqliteDBHelper.TABLE_T_FORM,null,null);
            db.delete(SqliteDBHelper.TABLE_T_FORMFIELD,null,null);
            db.delete(SqliteDBHelper.TABLE_T_IMAGE,null,null);
            db.delete(SqliteDBHelper.TABLE_T_UNIT,null,null);


            for (Building building : data.getBuildingList()){
                StringBuffer sbSQL = new StringBuffer();
                sbSQL.append("Insert Into ").append(SqliteDBHelper.TABLE_T_BUILDING)
                        .append(" (id,name,address) values (").append(building.getId())
                        .append(",'").append(building.getBuildingName()).append("','")
                        .append(building.getBuildingAddress()).append("'); ");
                db.execSQL(sbSQL.toString());


                for(Unit unit : building.getUnits()){
                    StringBuffer unitSql = new StringBuffer();
                    unitSql.append("Insert Into ").append(SqliteDBHelper.TABLE_T_UNIT)
                            .append(" (id,buildingid,name,unittype,unitstatus,moveindate,moveoutdate) values (")
                            .append(unit.getId()).append(",").append(building.getId()).append(",'")
                            .append(unit.getUnitName()).append("','")
                            .append(unit.getUnitType()).append("','")
                            .append(unit.getUnitStatus()).append("','")
                            .append(unit.getMoveInDate()).append("','")
                            .append(unit.getMoveOutDate()).append("');");
                    db.execSQL(unitSql.toString());
                }
            }

            for(DictionaryData dictionaryData: data.getRentalType()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('rentalType',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }
            for(DictionaryData dictionaryData: data.getFormSubmissionStatus()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('formSubmissionStatus',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }
            for(DictionaryData dictionaryData: data.getFormFieldPassFail()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('formFieldPassFail',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }
            for(DictionaryData dictionaryData: data.getHazardRating()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('hazardRating',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }
            for(DictionaryData dictionaryData: data.getFormType()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('formType',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }
            for(DictionaryData dictionaryData: data.getFormFieldCorrectiveAction()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('formFieldCorrectiveAction',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }
            for(DictionaryData dictionaryData: data.getFormFieldChargeTo()){
                StringBuffer rentalSql = new StringBuffer();
                rentalSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_DICTIONARY)
                        .append("(typename,id,value) values ('formFieldChargeTo',")
                        .append(dictionaryData.getValue()).append(",'")
                        .append(dictionaryData.getName()).append("'")
                        .append(");");
                db.execSQL(rentalSql.toString());
            }

            for(Form form :data.getFormList()){

                StringBuffer formSql = new StringBuffer();
                formSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_FORM)
                        .append("(id,cityid,name,startnumber) values (")
                        .append(form.getId()).append(",")
                        .append(form.getCityId()).append(",'")
                        .append(form.getName()).append("',")
                        .append(form.getStartNumber()).append(");");

                db.execSQL(formSql.toString());

                for(Category category:form.getFieldCategories()){
                    StringBuffer categorySql = new StringBuffer();
                    categorySql.append("Insert into ").append(SqliteDBHelper.TABLE_T_FIELDCATEGORY)
                            .append("(id,formid,code,name) values (")
                            .append(category.getId()).append(",")
                            .append(form.getId()).append(",'")
                            .append(category.getCategoryCode()).append("','")
                            .append(category.getCategoryName()).append("');");

                    db.execSQL(categorySql.toString());

                    for(Field field : category.getFormFields()){
                        StringBuffer fieldSql = new StringBuffer();
                        fieldSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_FORMFIELD)
                                .append("(id,categoryid,name,datatype,orderby) values (")
                                .append(field.getId()).append(",")
                                .append(category.getId()).append(",'")
                                .append(field.getName()).append("','")
                                .append(field.getFormFieldDataType()).append("',")
                                .append(field.getOrder()).append(");");
                        db.execSQL(fieldSql.toString());
                    }
                }
            }

            for(Task task : data.getTaskList()){
                StringBuffer taskSql = new StringBuffer();
                /*taskSql.append("Insert into ").append(SqliteDBHelper.TABLE_T_SUBMISSION)
                        .append("(id,categoryid,name,datatype,orderby) values (")
                        .append(field.getId()).append(",")
                        .append(category.getId()).append(",'")
                        .append(field.getName()).append("','")
                        .append(field.getFormFieldDataType()).append("',")
                        .append(field.getOrder()).append(");");*/
                db.execSQL(taskSql.toString());
            }




            db.setTransactionSuccessful();
            db.endTransaction();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
