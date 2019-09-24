package com.example.myapplication05.ResumePackage.Activity.ActivityResume;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.myapplication05.R;
import com.example.myapplication05.ResumePackage.Activity.Adapter.CustomViewPager;
import com.example.myapplication05.ResumePackage.Activity.Tabs.SharePreferance;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFGenerator extends AppCompatActivity {
    int i = 1;
    int[] mResources = {
            R.drawable.resume1,
            R.drawable.resume2,
            R.drawable.resume3,
            R.drawable.resume4,
            R.drawable.resume5,
            R.drawable.resume6,
            R.drawable.resume7,
            R.drawable.resume8
    };
    ImageView imageView;
    int positioon = 0;
    CustomViewPager mCustomPagerAdapter;
    Button set;
    ViewPager mViewPager;
    ProgressBar progressBar;
    DotsIndicator dotsIndicator;
    SharePreferance sharePreferance;
    private String resumeName;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfgenerator);
        imageView = findViewById(R.id.back);
        mCustomPagerAdapter = new CustomViewPager(this, mResources);
        mViewPager = findViewById(R.id.pager);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        mViewPager.setAdapter(mCustomPagerAdapter);
        dotsIndicator.setViewPager(mViewPager);
        sharePreferance = new SharePreferance(this);
        set = findViewById(R.id.select);
        progressBar = findViewById(R.id.progress_circular);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PDFGenerator.this, ResumeHome.class);
                startActivity(intent);
            }
        });
        mViewPager.setOffscreenPageLimit(7);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                positioon = position + 1;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(3000);
                progressBar.isIndeterminate();
                if (positioon == 0 || positioon == 1) {
                    createResumeOne();
                }
                if (positioon == 2) {
                    createResumTwo();
                }
                if (positioon == 3) {
                    createResumeThree();
                }
                if (positioon == 4) {
                    createResumeFour();
                }
                if (positioon == 5) {
                    createResumeFive();
                }
                if (positioon == 6) {
                    createResumeSix();
                }
                if (positioon == 7) {
                    createResumeSeven();
                }
                if (positioon == 8) {
                    createResumeEight();
                }
            }
        });

    }

    private void createResumeOne() {

        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();

            Paragraph Name = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineBold(Name, doc, sharePreferance.getBasicDetails(SharePreferance.NAME));
            Paragraph Phone = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineBold(Phone, doc, sharePreferance.getBasicDetails(SharePreferance.PHONE));
            Paragraph Email = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineBold(Email, doc, sharePreferance.getBasicDetails(SharePreferance.EMAIL));

            S.createLineSeparator(doc);

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(CareerObj, doc, "Career Objective : ");

                Paragraph CareerObjValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(CareerObjValue, doc, sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getMaster(SharePreferance.M_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getMaster(SharePreferance.M_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getMaster(SharePreferance.M_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    Paragraph min = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(min, doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getBachelors(SharePreferance.B_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getBachelors(SharePreferance.B_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getTwelth(SharePreferance.T_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTwelth(SharePreferance.T_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }
                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    Paragraph minor = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(minor, doc, sharePreferance.getTenth(SharePreferance.H_COURSE));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTenth(SharePreferance.H_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }

            }


            if (sharePreferance.getExperience(SharePreferance.LIST).length()!=0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(ExperienceRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EXPERIENCE_HEADING));


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));
                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));

                Paragraph SkillsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValue, doc, sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
            }
            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph Industrial = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(Industrial, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING));

                Paragraph IndustrialValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(IndustrialValue, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
            }

            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                Paragraph achievementsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(achievementsValue, doc, sharePreferance.getAwards(SharePreferance.AWARDS));
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                Paragraph strengthsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(strengthsValue, doc, sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph activity = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(activity, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                Paragraph activityValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(activityValue, doc, sharePreferance.getActivity(SharePreferance.ACTIVITY));
            }

            if (!sharePreferance.getProjectDetails(SharePreferance.DOB).matches("H") || !sharePreferance.getProjectDetails(SharePreferance.MARITAL_STATUS).matches("H") || !sharePreferance.getProjectDetails(SharePreferance.LANGUAGE).matches("H") || !sharePreferance.getProjectDetails(SharePreferance.NATIONALITY).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));

                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                if (!sharePreferance.getBasicDetails(SharePreferance.ADD_2).matches("H") && !sharePreferance.getBasicDetails(SharePreferance.ADD_3).matches("H")) {
                    S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
                } else if (!sharePreferance.getBasicDetails(SharePreferance.ADD_2).matches("H")) {
                    S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1 + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2)));
                } else if (!sharePreferance.getBasicDetails(SharePreferance.ADD_3).matches("H")) {
                    S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1 + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3)));
                }
                if (!sharePreferance.getInterests(SharePreferance.INTERESTS).matches("H")) {
                    S.CreateTableWithColumn(doc, "Hobbies", sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES));
                }
                if (!sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES).matches("H")){
                S.CreateTableWithColumn(doc, "Interests", sharePreferance.getInterests(SharePreferance.INTERESTS));
            }
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));

                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphAlignLeftUnderLine(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                Paragraph declarationValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(declarationValue, doc, sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));


            }


            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                byte[] encodeByte = Base64.decode(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(newBitmap);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, 0, 0, null);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                com.itextpdf.text.Image myImg = com.itextpdf.text.Image.getInstance(stream.toByteArray());
                myImg.setAlignment(Image.ALIGN_LEFT);
                doc.add(myImg);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }
            }
            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }

    }

    void openPdf(int j) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File file = new File(path, resumeName + ".pdf");
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            startActivity(intent);
            i++;
            finish();
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Sorry! Unable to create", Toast.LENGTH_SHORT).show();
        }
    }

    private void createResumTwo() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();

            Paragraph Name = new Paragraph();
            S.CreateParagraphAlignRightUnderLineBold(Name, doc, sharePreferance.getBasicDetails(SharePreferance.NAME));
            Paragraph Phone = new Paragraph();
            S.CreateParagraphAlignRightUnderLineBold(Phone, doc, sharePreferance.getBasicDetails(SharePreferance.PHONE));
            Paragraph Email = new Paragraph();
            S.CreateParagraphAlignRightUnderLineBold(Email, doc, sharePreferance.getBasicDetails(SharePreferance.EMAIL));

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphBoxWithColor(CareerObj, doc, "Career Objective ");

                Paragraph CareerObjValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(CareerObjValue, doc, sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphBoxWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getMaster(SharePreferance.M_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getMaster(SharePreferance.M_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getMaster(SharePreferance.M_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    Paragraph min = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(min, doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getBachelors(SharePreferance.B_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getBachelors(SharePreferance.B_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getTwelth(SharePreferance.T_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTwelth(SharePreferance.T_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }
                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    Paragraph minor = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(minor, doc, sharePreferance.getTenth(SharePreferance.H_COURSE));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTenth(SharePreferance.H_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }

            }
            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphBoxWithColor(ExperienceRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EXPERIENCE_HEADING));


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));


                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Log.e("test : ", "exp dat :  " + object.getString("Organization"));

                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphBoxWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));
                Paragraph TechnicalB = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(TechnicalB, doc, sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
            }

            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph Technical = new Paragraph();
                S.CreateParagraphBoxWithColor(Technical, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRY_HEADING));
                Paragraph TechnicalBody = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(TechnicalBody, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphBoxWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphBoxWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                Paragraph extraActivitiesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(extraActivitiesValue, doc, sharePreferance.getActivity(SharePreferance.ACTIVITY));
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphBoxWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                Paragraph achievementsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(achievementsValue, doc, sharePreferance.getAwards(SharePreferance.AWARDS));
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphBoxWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                Paragraph strengthsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(strengthsValue, doc, sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphBoxWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));

                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
                if (!sharePreferance.getInterests(SharePreferance.INTERESTS).matches("H")) {
                    S.CreateTableWithColumn(doc, sharePreferance.getHeadingInterests(SharePreferance.INTERESTS_HEADING), sharePreferance.getInterests(SharePreferance.INTERESTS));
                }
                if (!sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES).matches("H")) {
                    S.CreateTableWithColumn(doc, "Hobbies", sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES));
                }
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphBoxWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));

                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphBoxWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                Paragraph declarationValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(declarationValue, doc, sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));
            }
            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                byte[] encodeByte = Base64.decode(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                //remove Bitmap black background
                Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(newBitmap);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, 0, 0, null);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                myImg.setAlignment(Image.ALIGN_LEFT);
                doc.add(myImg);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }

            }

            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

    private void createResumeThree() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();

            Paragraph Resume = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineCenter(Resume, doc, "Resume");

            if (!sharePreferance.getPhotoandSign(SharePreferance.PHOTO).matches("H")) {
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{0.5f, 2});
                table.addCell(S.createImageCell(Image.getInstance(sharePreferance.getPhotoandSign(SharePreferance.PHOTO))));
                table.addCell(S.createTextCell(sharePreferance.getBasicDetails(SharePreferance.NAME) + "\n" + sharePreferance.getBasicDetails(SharePreferance.EMAIL) + "\n" + sharePreferance.getBasicDetails(SharePreferance.PHONE)));
                doc.add(table);

            } else {
                System.out.println("signature +  == els = " + sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE));
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{0.5f, 2});
                table.addCell(S.createImageCell(null));
                table.addCell(S.createTextCell(sharePreferance.getBasicDetails(SharePreferance.NAME) + "\n" + sharePreferance.getBasicDetails(SharePreferance.EMAIL) + "\n" + sharePreferance.getBasicDetails(SharePreferance.PHONE)));
                doc.add(table);
            }

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphBoxWithColor(CareerObj, doc, "Career Objective : ");

                Paragraph CareerObjValue = new Paragraph(sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
                S.CreateParagraphAlignLeft(CareerObjValue, doc);
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphBoxWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getMaster(SharePreferance.M_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getMaster(SharePreferance.M_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getMaster(SharePreferance.M_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    Paragraph min = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(min, doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getBachelors(SharePreferance.B_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getBachelors(SharePreferance.B_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getTwelth(SharePreferance.T_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTwelth(SharePreferance.T_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }
                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    Paragraph minor = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(minor, doc, sharePreferance.getTenth(SharePreferance.H_COURSE));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTenth(SharePreferance.H_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }
            }
            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphBoxWithColor(ExperienceRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EXPERIENCE_HEADING));


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));

                Log.e("test : ", "exp dat :  " + array);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Log.e("test : ", "exp dat :  " + object.getString("Organization"));

                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
            }
            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphBoxWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));

                Paragraph SkillsValue = new Paragraph(sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
                S.CreateParagraphAlignLeft(SkillsValue, doc);
            }

            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph IT = new Paragraph();
                S.CreateParagraphBoxWithColor(IT, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING));
                Paragraph indu = new Paragraph(sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
                S.CreateParagraphAlignLeft(indu, doc);

            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphBoxWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphBoxWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                Paragraph extraActivitiesValue = new Paragraph(sharePreferance.getActivity(SharePreferance.ACTIVITY));
                S.CreateParagraphAlignLeft(extraActivitiesValue, doc);
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphBoxWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                Paragraph achievementsValue = new Paragraph(sharePreferance.getAwards(SharePreferance.AWARDS));
                S.CreateParagraphAlignLeft(achievementsValue, doc);
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphBoxWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                Paragraph strengthsValue = new Paragraph(sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
                S.CreateParagraphAlignLeft(strengthsValue, doc);
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphBoxWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));

                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));

            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphBoxWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));
                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphBoxWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                Paragraph declarationValue = new Paragraph(sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));
                S.CreateParagraphAlignLeft(declarationValue, doc);
            }

            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                byte[] encodeByte = Base64.decode(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                //remove Bitmap black background
                Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(newBitmap);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, 0, 0, null);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                myImg.setAlignment(Image.ALIGN_RIGHT);
                doc.add(myImg);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignRight(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }

            }
            S.T(this, "Created");
            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

    private void createResumeFour() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();


            if (!sharePreferance.getPhotoandSign(SharePreferance.PHOTO).matches("H")) {
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{7, 3, 2});
                table.addCell(S.createTextCellHeadingLeft(sharePreferance.getBasicDetails(SharePreferance.NAME) + "\n" + sharePreferance.getBasicDetails(SharePreferance.EMAIL) + "\n" + sharePreferance.getBasicDetails(SharePreferance.PHONE)));
                table.addCell(S.createTextCellFourth(null));
                table.addCell(S.createImageCellRight(Image.getInstance(sharePreferance.getPhotoandSign(SharePreferance.PHOTO))));
                doc.add(table);

            } else {
                System.out.println("signature +  == els = " + sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE));
                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{0.5f, 8, 2});
                table.addCell(S.createImageCell(null));
                table.addCell(S.createTextCell(null));
                table.addCell(S.createTextCellHeading(sharePreferance.getBasicDetails(SharePreferance.NAME) + "\n" + sharePreferance.getBasicDetails(SharePreferance.EMAIL) + "\n" + sharePreferance.getBasicDetails(SharePreferance.PHONE)));
                doc.add(table);
            }

            // For Line
            S.createLineSeparator(doc);

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(CareerObj, doc, "Career Objective : ");

                Paragraph CareerObjValue = new Paragraph();
                S.CreateParagraphAlignLeftWithDoubleTab(CareerObjValue, doc, sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getMaster(SharePreferance.M_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getMaster(SharePreferance.M_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getMaster(SharePreferance.M_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    Paragraph min = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(min, doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getBachelors(SharePreferance.B_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getBachelors(SharePreferance.B_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getTwelth(SharePreferance.T_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTwelth(SharePreferance.T_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }
                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    Paragraph minor = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(minor, doc, sharePreferance.getTenth(SharePreferance.H_COURSE));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTenth(SharePreferance.H_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }
            }
            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(ExperienceRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EXPERIENCE_HEADING));


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));

                Log.e("test : ", "exp dat :  " + array);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Log.e("test : ", "exp dat :  " + object.getString("Organization"));

                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));

                Paragraph SkillsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValue, doc, sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
            }


            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph IT = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(IT, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING));
                Paragraph SkillsValu = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValu, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                Paragraph extraActivitiesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(extraActivitiesValue, doc, sharePreferance.getActivity(SharePreferance.ACTIVITY));
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                Paragraph achievementsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(achievementsValue, doc, sharePreferance.getAwards(SharePreferance.AWARDS));
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                Paragraph strengthsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(strengthsValue, doc, sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));

                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
                if (!sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES).matches("H")) {
                    S.CreateTableWithColumn(doc, "Hobbies", sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES));
                }
                if (!sharePreferance.getInterests(SharePreferance.INTERESTS).matches("H")) {
                    S.CreateTableWithColumn(doc, "Interests", sharePreferance.getInterests(SharePreferance.INTERESTS));
                }
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));

                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));
            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                Paragraph declarationValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(declarationValue, doc, sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));
            }

            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                byte[] encodeByte = Base64.decode(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                //remove Bitmap black background
                Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(newBitmap);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, 0, 0, null);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                myImg.setAlignment(Image.ALIGN_LEFT);
                doc.add(myImg);

                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }
            }
            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

    private void createResumeFive() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();

            Paragraph name = new Paragraph();
            S.CreateParagraphAlignRightUnderLineBold(name, doc, sharePreferance.getBasicDetails(SharePreferance.NAME));


            Paragraph name2 = new Paragraph();
            S.CreateParagraphAlignRightUnderLineBold(name2, doc, sharePreferance.getBasicDetails(SharePreferance.EMAIL));

            Paragraph name1 = new Paragraph();
            S.CreateParagraphAlignRightUnderLineBold(name1, doc, sharePreferance.getBasicDetails(SharePreferance.PHONE));
            //for line
            S.createLineSeparator(doc);

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(CareerObj, doc, "Career Objective : ");

                Paragraph CareerObjValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(CareerObjValue, doc, sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getMaster(SharePreferance.M_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getMaster(SharePreferance.M_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getMaster(SharePreferance.M_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    Paragraph min = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(min, doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getBachelors(SharePreferance.B_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getBachelors(SharePreferance.B_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    Paragraph mino = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(mino, doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));
                    S.CreateTableWithColumn(doc, "Stream", sharePreferance.getTwelth(SharePreferance.T_STREAM));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTwelth(SharePreferance.T_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }
                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    Paragraph minor = new Paragraph();
                    S.CreateParagraphAlignLeftUnderLineSmall(minor, doc, sharePreferance.getTenth(SharePreferance.H_COURSE));
                    S.CreateTableWithColumn(doc, "Score", sharePreferance.getTenth(SharePreferance.H_CGPA));
                    S.CreateTableWithColumn(doc, "Institute", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));
                    S.CreateTableWithColumn(doc, "Passing Year", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }

            }

            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(ExperienceRecords, doc, sharePreferance.getHeadingExperience(SharePreferance.EXPERIENCE_HEADING));


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));

                Log.e("test : ", "exp dat :  " + array);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Log.e("test : ", "exp dat :  " + object.getString("Organization"));

                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));

                Paragraph SkillsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValue, doc, sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
            }


            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph IT = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(IT, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING));

                Paragraph SkillsValu = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValu, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                Paragraph extraActivitiesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(extraActivitiesValue, doc, sharePreferance.getActivity(SharePreferance.ACTIVITY));
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                Paragraph achievementsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(achievementsValue, doc, sharePreferance.getAwards(SharePreferance.AWARDS));
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                Paragraph strengthsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(strengthsValue, doc, sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));

                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));
                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                Paragraph declarationValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(declarationValue, doc, sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));
            }

            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                Image myImg = S.convertBitmapToImage(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE));
                myImg.setAlignment(Image.ALIGN_LEFT);
                doc.add(myImg);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }

            }

            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

    private void createResumeSix() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();

            Paragraph Name = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
            S.CreateParagraphAlignRight(Name, doc);

            Paragraph Email = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.EMAIL));
            S.CreateParagraphAlignRight(Email, doc);

            Paragraph ContactNo = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.PHONE));
            S.CreateParagraphAlignRight(ContactNo, doc);

            //for line
            S.createLineSeparator(doc);

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(CareerObj, doc, "Career Objective : ");

                Paragraph CareerObjValue = new Paragraph(sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
                S.CreateParagraphAlignLeft(CareerObjValue, doc);
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    Paragraph ProfessionalQua = new Paragraph();
                    ProfessionalQua.setSpacingAfter(5);
                    ProfessionalQua.setSpacingBefore(5);
                    S.CreateParagraphAlignLeftUnderLine(ProfessionalQua, doc, sharePreferance.getMaster(SharePreferance.M_COURSE));

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Stream : ", sharePreferance.getMaster(SharePreferance.M_STREAM));

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Score : ", sharePreferance.getMaster(SharePreferance.M_CGPA));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Institute : ", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Passing Year : ", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }
                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    Paragraph ProfessionalQua = new Paragraph();
                    ProfessionalQua.setSpacingAfter(5);
                    ProfessionalQua.setSpacingBefore(5);
                    S.CreateParagraphAlignLeftUnderLine(ProfessionalQua, doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Stream : ", sharePreferance.getBachelors(SharePreferance.B_STREAM));

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Score : ", sharePreferance.getBachelors(SharePreferance.B_CGPA));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Institute : ", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Passing Year : ", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }
                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    Paragraph ProfessionalQua = new Paragraph();
                    ProfessionalQua.setSpacingAfter(5);
                    ProfessionalQua.setSpacingBefore(5);
                    S.CreateParagraphAlignLeftUnderLine(ProfessionalQua, doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Stream : ", sharePreferance.getTwelth(SharePreferance.T_STREAM));

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Score : ", sharePreferance.getTwelth(SharePreferance.T_CGPA));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Institute : ", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Passing Year : ", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }
                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    Paragraph ProfessionalQua = new Paragraph();
                    ProfessionalQua.setSpacingAfter(5);
                    ProfessionalQua.setSpacingBefore(5);
                    S.CreateParagraphAlignLeftUnderLine(ProfessionalQua, doc, sharePreferance.getTenth(SharePreferance.H_COURSE));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Score : ", sharePreferance.getTenth(SharePreferance.H_CGPA));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Institute : ", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Passing Year : ", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }
            }

            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(ExperienceRecords, doc, sharePreferance.getHeadingExperience(SharePreferance.EXPERIENCE_HEADING));


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));

                Log.e("test : ", "exp dat :  " + array);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Paragraph ProfessionalQua = new Paragraph();
                    ProfessionalQua.setSpacingAfter(5);
                    ProfessionalQua.setSpacingBefore(5);
                    S.CreateParagraphAlignLeftUnderLine(ProfessionalQua, doc, (i + 1) + ".");

                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Organization : ", object.getString("Organization"));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Designation : ", object.getString("Designation"));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Duration : ", object.getString("Duration"));
                    S.createTable(doc, 2, new float[]{0.5f, 2f}, "Technology : ", object.getString("Technology"));

                }
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));

                Paragraph SkillsValue = new Paragraph(sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
                S.CreateParagraphAlignLeft(SkillsValue, doc);
            }

            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph IT = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(IT, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING));
                Paragraph SkillsValue1 = new Paragraph(sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
                S.CreateParagraphAlignLeft(SkillsValue1, doc);
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Title : ", sharePreferance.getProjectDetails(SharePreferance.TITLE));

                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Description : ", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));

                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Duration ", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                Paragraph extraActivitiesValue = new Paragraph(sharePreferance.getActivity(SharePreferance.ACTIVITY));
                S.CreateParagraphAlignLeft(extraActivitiesValue, doc);
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                Paragraph achievementsValue = new Paragraph(sharePreferance.getAwards(SharePreferance.AWARDS));
                S.CreateParagraphAlignLeft(achievementsValue, doc);
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                Paragraph strengthsValue = new Paragraph(sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
                S.CreateParagraphAlignLeft(strengthsValue, doc);
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));
                S.createTable(doc, 2, new float[]{0.5f, 2f}, "DOB : ", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Nationality : ", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Marital Status : ", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Language Known : ", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Father's Name : ", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.createTable(doc, 2, new float[]{0.5f, 2f}, "Address : ", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));

                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphAlignLeftBoxWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                Paragraph declarationValue = new Paragraph(sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));
                S.CreateParagraphAlignLeft(declarationValue, doc);
            }

            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                Image myImg = S.convertBitmapToImage(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE));
                myImg.setAlignment(Image.ALIGN_LEFT);
                doc.add(myImg);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }

            }

            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }

    private void createResumeSeven() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();


            Paragraph Name = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineCenter(Name, doc, sharePreferance.getBasicDetails(SharePreferance.NAME));

            Paragraph Email = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineCenter(Email, doc, sharePreferance.getBasicDetails(SharePreferance.EMAIL) + " , " + sharePreferance.getBasicDetails(SharePreferance.PHONE));

            //for draw line
            S.createLineSeparatorWithColor(doc);

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(CareerObj, doc, "Career Objective : ");


                Paragraph CareerObjValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(CareerObjValue, doc, sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING));

                //for draw line


                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getMaster(SharePreferance.M_COURSE));

                    S.CreateTableWithColumn(doc, "Stream ", sharePreferance.getMaster(SharePreferance.M_STREAM));

                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getMaster(SharePreferance.M_CGPA));

                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));

                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream ", sharePreferance.getBachelors(SharePreferance.B_STREAM));

                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getBachelors(SharePreferance.B_CGPA));

                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));

                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));

                    S.CreateTableWithColumn(doc, "Stream ", sharePreferance.getTwelth(SharePreferance.T_STREAM));


                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getTwelth(SharePreferance.T_CGPA));


                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    Paragraph me = new Paragraph();
                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }

                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getTenth(SharePreferance.H_COURSE));

                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getTenth(SharePreferance.H_CGPA));

                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));

                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }
                S.createLineSeparatorWithColor(doc);
            }
            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(ExperienceRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EXPERIENCE_HEADING));

                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));


                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Log.e("test : ", "exp dat :  " + object.getString("Organization"));

                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
                S.createLineSeparatorWithColor(doc);
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING));

                //for draw line


                Paragraph SkillsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValue, doc, sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
            }


            //for draw line
            S.createLineSeparatorWithColor(doc);

            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph IT = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(IT, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING));
                Paragraph tt = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(tt, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING));

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING));

                //for draw line


                Paragraph extraActivitiesValue = new Paragraph();

                //for draw line
                S.createLineSeparatorWithColor(doc);
                S.CreateParagraphAlignLeftWithTab(extraActivitiesValue, doc, sharePreferance.getActivity(SharePreferance.ACTIVITY));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING));

                //for draw line

                Paragraph achievementsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(achievementsValue, doc, sharePreferance.getAwards(SharePreferance.AWARDS));

                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING));

                //for draw line


                Paragraph strengthsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(strengthsValue, doc, sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING));

                //for draw line


                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
                if (!sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES).matches("H")) {
                    S.CreateTableWithColumn(doc, "Hobbies", sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES));
                }
                if (!sharePreferance.getInterests(SharePreferance.INTERESTS).matches("H")) {
                    S.CreateTableWithColumn(doc, "Interests", sharePreferance.getInterests(SharePreferance.INTERESTS));
                }
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING));

                //for draw line


                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING));

                //for draw line
                Paragraph declarationValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(declarationValue, doc, sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));

                S.createLineSeparatorWithColor(doc);

            }

            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                byte[] encodeByte = Base64.decode(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                //remove Bitmap black background
                Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(newBitmap);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, 0, 0, null);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                myImg.setAlignment(Image.ALIGN_LEFT);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }

                doc.add(myImg);
            }
            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }

    }


    private void createResumeEight() {
        Document doc = new Document();
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getString(R.string.app_name) + "/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy_hh:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            resumeName = "Resume_" + currentDateandTime;
            File file = new File(dir, resumeName + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);
            doc.open();


            Paragraph Resume = new Paragraph();
            S.CreateParagraphAlignLeftUnderLineCenter(Resume, doc, "Resume");

            if (!sharePreferance.getPhotoandSign(SharePreferance.PHOTO).matches("H")) {
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{0.5f, 2});
                table.addCell(S.createImageCell(Image.getInstance(sharePreferance.getPhotoandSign(SharePreferance.PHOTO))));
                table.addCell(S.createTextCell(sharePreferance.getBasicDetails(SharePreferance.NAME) + "\n" + sharePreferance.getBasicDetails(SharePreferance.EMAIL) + "\n" + sharePreferance.getBasicDetails(SharePreferance.PHONE)));
                doc.add(table);

            } else {
                System.out.println("signature +  == els = " + sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE));
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new float[]{0.5f, 2});
                table.addCell(S.createImageCell(null));
                table.addCell(S.createTextCell(sharePreferance.getBasicDetails(SharePreferance.NAME) + "\n" + sharePreferance.getBasicDetails(SharePreferance.EMAIL) + "\n" + sharePreferance.getBasicDetails(SharePreferance.PHONE)));
                doc.add(table);
            }

            //for draw line
            S.createLineSeparatorWithColor(doc);

            if (!sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE).matches("H")) {
                Paragraph CareerObj = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(CareerObj, doc, "Career Objective : ");


                Paragraph CareerObjValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(CareerObjValue, doc, sharePreferance.getDateDeclaration(SharePreferance.OBJECTIVE));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H") || !sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H") || !sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H") || !sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                Paragraph AcademicRecords = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(AcademicRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EDUCATION_HEADING) + " : ");

                //for draw line


                if (!sharePreferance.getMaster(SharePreferance.M_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getMaster(SharePreferance.M_COURSE));

                    S.CreateTableWithColumn(doc, "Stream ", sharePreferance.getMaster(SharePreferance.M_STREAM));

                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getMaster(SharePreferance.M_CGPA));

                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getMaster(SharePreferance.M_INSTITUTE));

                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getMaster(SharePreferance.M_YEAR));
                }

                if (!sharePreferance.getBachelors(SharePreferance.B_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getBachelors(SharePreferance.B_COURSE));
                    S.CreateTableWithColumn(doc, "Stream ", sharePreferance.getBachelors(SharePreferance.B_STREAM));

                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getBachelors(SharePreferance.B_CGPA));

                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getBachelors(SharePreferance.B_INSTITUTE));

                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getBachelors(SharePreferance.B_YEAR));
                }

                if (!sharePreferance.getTwelth(SharePreferance.T_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getTwelth(SharePreferance.T_COURSE));

                    S.CreateTableWithColumn(doc, "Stream ", sharePreferance.getTwelth(SharePreferance.T_STREAM));


                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getTwelth(SharePreferance.T_CGPA));


                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getTwelth(SharePreferance.T_INSTITUTE));
                    Paragraph me = new Paragraph();
                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getTwelth(SharePreferance.T_YEAR));
                }

                if (!sharePreferance.getTenth(SharePreferance.H_COURSE).matches("H")) {
                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, sharePreferance.getTenth(SharePreferance.H_COURSE));

                    S.CreateTableWithColumn(doc, "Score ", sharePreferance.getTenth(SharePreferance.H_CGPA));

                    S.CreateTableWithColumn(doc, "Institute ", sharePreferance.getTenth(SharePreferance.H_INSTITUTE));

                    S.CreateTableWithColumn(doc, "Passing Year ", sharePreferance.getTenth(SharePreferance.H_YEAR));
                }
                S.createLineSeparatorWithColor(doc);
            }
            if (sharePreferance.getExperience(SharePreferance.LIST).length() != 0) {
                Paragraph ExperienceRecords = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(ExperienceRecords, doc, sharePreferance.getHeadingEducation(SharePreferance.EXPERIENCE_HEADING) + " : ");


                JSONArray array = new JSONArray(sharePreferance.getExperience(SharePreferance.LIST));

                Log.e("test : ", "exp dat :  " + array);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);
                    Log.e("test : ", "exp dat :  " + object.getString("Organization"));

                    S.CreateParagraphAlignLeftUnderLineSmall(new Paragraph(), doc, (i + 1) + ".");
                    S.CreateTableWithColumn(doc, "Organization", object.getString("Organization"));
                    S.CreateTableWithColumn(doc, "Designation", object.getString("Designation"));
                    S.CreateTableWithColumn(doc, "Duration", object.getString("Duration"));
                    S.CreateTableWithColumn(doc, "Technology", object.getString("Technology"));
                }
                S.createLineSeparatorWithColor(doc);
            }

            if (!sharePreferance.getTechnicalSkills(SharePreferance.SKILLS).matches("H")) {
                Paragraph ITSkills = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(ITSkills, doc, sharePreferance.getHeadingTechnical(SharePreferance.TECHNICAL_HEADING) + " : ");

                //for draw line


                Paragraph SkillsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(SkillsValue, doc, sharePreferance.getTechnicalSkills(SharePreferance.SKILLS));
            }


            //for draw line
            S.createLineSeparatorWithColor(doc);

            if (!sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL).matches("H")) {
                Paragraph IT = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(IT, doc, sharePreferance.getHeadingIndustry(SharePreferance.INDUSTRY_HEADING) + " : ");
                Paragraph tt = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(tt, doc, sharePreferance.getIndustrial(SharePreferance.INDUSTRIAL));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getProjectDetails(SharePreferance.TITLE).matches("H")) {
                Paragraph minorPro = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(minorPro, doc, sharePreferance.getHeadingProject(SharePreferance.PROJECTS_HEADING) + " : ");

                S.CreateTableWithColumn(doc, "Title", sharePreferance.getProjectDetails(SharePreferance.TITLE));
                S.CreateTableWithColumn(doc, "Description", sharePreferance.getProjectDetails(SharePreferance.DESCRIPTION));
                S.CreateTableWithColumn(doc, "Duration", sharePreferance.getProjectDetails(SharePreferance.DURATION2));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getActivity(SharePreferance.ACTIVITY).matches("H")) {
                Paragraph extraActivities = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(extraActivities, doc, sharePreferance.getHeadingActivity(SharePreferance.ACTIVITY_HEADING) + " : ");

                //for draw line


                Paragraph extraActivitiesValue = new Paragraph();

                //for draw line
                S.createLineSeparatorWithColor(doc);
                S.CreateParagraphAlignLeftWithTab(extraActivitiesValue, doc, sharePreferance.getActivity(SharePreferance.ACTIVITY));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getAwards(SharePreferance.AWARDS).matches("H")) {
                Paragraph achievements = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(achievements, doc, sharePreferance.getHeadingAwards(SharePreferance.AWARDS_HEADING) + " : ");

                //for draw line

                Paragraph achievementsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(achievementsValue, doc, sharePreferance.getAwards(SharePreferance.AWARDS));

                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS).matches("H")) {
                Paragraph strengths = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(strengths, doc, sharePreferance.getHeadingHobby(SharePreferance.HOBBY_HEADING) + " : ");

                //for draw line


                Paragraph strengthsValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(strengthsValue, doc, sharePreferance.getStrengthsandHobbies(SharePreferance.STRENGTHS));
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getPersonalInfo(SharePreferance.DOB).matches("H")) {
                Paragraph personalDetails = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(personalDetails, doc, sharePreferance.getHeadingPersonal(SharePreferance.PERSONAL_HEADING) + " : ");

                //for draw line


                S.CreateTableWithColumn(doc, "DOB", sharePreferance.getPersonalInfo(SharePreferance.DOB));
                S.CreateTableWithColumn(doc, "Nationality", sharePreferance.getPersonalInfo(SharePreferance.NATIONALITY));
                S.CreateTableWithColumn(doc, "Marital Status", sharePreferance.getPersonalInfo(SharePreferance.MARITAL_STATUS));
                S.CreateTableWithColumn(doc, "Language Known", sharePreferance.getPersonalInfo(SharePreferance.LANGUAGE));
                S.CreateTableWithColumn(doc, "Father's Name", "Mr. " + sharePreferance.getPersonalInfo(SharePreferance.FNAME));
                S.CreateTableWithColumn(doc, "Address", sharePreferance.getBasicDetails(SharePreferance.ADD_1) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_2) + " " + sharePreferance.getBasicDetails(SharePreferance.ADD_3));
                if (!sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES).matches("H")) {
                    S.CreateTableWithColumn(doc, "Hobbies", sharePreferance.getStrengthsandHobbies(SharePreferance.HOBBIES));
                }
                if (!sharePreferance.getInterests(SharePreferance.INTERESTS).matches("H")) {
                    S.CreateTableWithColumn(doc, "Interests", sharePreferance.getInterests(SharePreferance.INTERESTS));
                }
                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getReferenceDetails(SharePreferance.REF_NAME).matches("H")) {
                Paragraph resumeReferences = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(resumeReferences, doc, sharePreferance.getHeadingReference(SharePreferance.REFERENCE_HEADING) + " : ");

                //for draw line


                Paragraph resumeReferencesValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTabBol(resumeReferencesValue, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_ORG) + " - " + sharePreferance.getReferenceDetails(SharePreferance.REF_NAME));
                Paragraph resumeReferencesValue1 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue1, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_DESIG));
                Paragraph resumeReferencesValue2 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue2, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_EMAIL));
                Paragraph resumeReferencesValue3 = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(resumeReferencesValue3, doc, sharePreferance.getReferenceDetails(SharePreferance.REF_PHONE));

                S.createLineSeparatorWithColor(doc);
            }
            if (!sharePreferance.getDateDeclaration(SharePreferance.DECLARATION).matches("H")) {
                Paragraph declaration = new Paragraph();
                S.CreateParagraphAlignLeftUnderLineCenterWithColor(declaration, doc, sharePreferance.getHeadingDate(SharePreferance.DATE_HEADING) + " : ");

                //for draw line
                Paragraph declarationValue = new Paragraph();
                S.CreateParagraphAlignLeftWithTab(declarationValue, doc, sharePreferance.getDateDeclaration(SharePreferance.DECLARATION));


            }

            if (!sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).matches("H")) {
                byte[] encodeByte = Base64.decode(sharePreferance.getPhotoandSign(SharePreferance.SIGNATURE).getBytes(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                //remove Bitmap black background
                Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
                Canvas canvas = new Canvas(newBitmap);
                canvas.drawColor(Color.WHITE);
                canvas.drawBitmap(bitmap, 0, 0, null);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(newBitmap, 75, 50, false);
                resizedBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                myImg.setAlignment(Image.ALIGN_LEFT);
                doc.add(myImg);
                Paragraph lstName = new Paragraph(sharePreferance.getBasicDetails(SharePreferance.NAME));
                S.CreateParagraphAlignLeft(lstName, doc);
                if (!sharePreferance.getDateDeclaration(SharePreferance.DATE).matches("H")) {
                    Paragraph date = new Paragraph("Date : " + sharePreferance.getDateDeclaration(SharePreferance.DATE));
                    S.CreateParagraphAlignLeft(date, doc);
                }
                if (!sharePreferance.getDateDeclaration(SharePreferance.PLACE).matches("H")) {
                    Paragraph place = new Paragraph("Place : " + sharePreferance.getDateDeclaration(SharePreferance.PLACE));
                    S.CreateParagraphAlignLeft(place, doc);
                }


            }
            openPdf(i);
        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }

    }

}

